package main

import (
	"github.com/google/go-cmp/cmp"
	"fmt"
	"strings"
	"regexp"
)

func isNumber(s string) bool {

	trimed := strings.TrimSpace(s)

	if (len(trimed) == 0) {
		return false
	}
	// (+/-)实数部分(e)(+/-)实数部分
	// 3 部分组成  看有没有 e 这个科学计数

	indexOfE := strings.Index(trimed, "e")

	if indexOfE < 0 {
		return isRealNumber(trimed)
	}

	leftReal := trimed[:indexOfE]
	rightReal := trimed[indexOfE+1:]

	return isRealNumber(leftReal) && isInteger(rightReal)
}

func isInteger(s string) bool {
	var validInteger = regexp.MustCompile(`^(\+|-)?[0-9]+$`)

	return validInteger.MatchString(s)
}

func isRealNumber(s string) bool {

	var validRealNumber = regexp.MustCompile(`^(\+|-)?[0-9]+(\.)?[0-9]*$`)
	var leadingPointNumber = regexp.MustCompile(`^(\+|-)?\.[0-9]+$`)

	return validRealNumber.MatchString(s) || leadingPointNumber.MatchString(s)
}

func main() {
	tests := map[string]struct {
		input string
		want  bool
	}{
		"only digit":       {input: "0", want: true},
		"space and point":       {input: " 0.1 ", want: true},
		"leading point":       {input: " .1 ", want: true},
		"ending point":       {input: " 3. ", want: true},
		"illegal alphabeta 1":       {input: "abc", want: false},
		"illegal alphabeta 2":       {input: "1 a", want: false},
		"science number 1":       {input: "2e10", want: true},
		"science number 2":       {input: "2e-10", want: true},
		"illegal science number 1":       {input: "2e", want: false},
		"illegal science number 2":       {input: "e10", want: false},
		"illegal science number 3":       {input: "2e10.1", want: false},
		"sign -":       {input: " -2e10", want: true},
		"sign +":       {input: " +2e10", want: true},
		"sign illegal 1":       {input: " ++2e10", want: false},
		"sign illegal 2":       {input: " --2e10", want: false},
		"sign illegal 3":       {input: " -+2e10", want: false},
	}

	for name, tc := range tests {
		got := isNumber(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
