package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
	"strings"
	"strconv"
)

func restoreIpAddresses(s string) []string {

	if len(s) < 4 || len(s) > 12 {
		return nil
	}



	return recurRestore(strings.Split(s, ""), 4)

}

func isValid(s string) bool {

	if len(s) == 0 || len(s) > 3 {
		return false
	}

	i, err := strconv.Atoi(s)

	if err != nil {
		return false
	}

	if len(s) == 2 && i < 10 {
		return false
	}

	if len(s) == 3 && i < 100 {
		return false
	}

	if i > 255 {
		return false
	}

	return true


}


func recurRestore(s []string, remain int) []string{

	if remain == 1   {
		var tmp = strings.Join(s, "")
		if isValid(tmp) {
			return []string{tmp}
		}
		return nil
	}

	if len(s) < remain || len(s) > (remain * 3) {
		return nil
	}

	var res []string

	var atLeastOneTrySuccess = false
	for i := 1; i <= 3; i++ {

		if i > len(s) {
			break
		}

		var cur = strings.Join(s[0:i],"")

		if !isValid(cur) {
			continue
		}

		var subRes = recurRestore(s[i:], remain - 1)

		if subRes == nil {
			continue
		}


		for j := 0; j <len(subRes); j++ {

			res = append(res, cur + "." + subRes[j])

		}

		atLeastOneTrySuccess = true

	}

	if !atLeastOneTrySuccess {
		return nil
	}

	return res


}


func main() {
	tests := map[string]struct {
		input string
		want  []string
	}{

		"25525511135":       {input: "25525511135", want: []string{"255.255.11.135", "255.255.111.35"}},
		"0000":       {input: "0000", want: []string{ "0.0.0.0"}},
		"11111":       {input: "1111", want: []string{ "1.1.1.1"}},
		"010010":       {input: "010010", want: []string{ "0.10.0.10", "0.100.1.0"}},
		"101023":       {input: "101023", want: []string{ "1.0.10.23", "1.0.102.3", "10.1.0.23","10.10.2.3", "101.0.2.3"}},
	}

	for name, tc := range tests {
		got := restoreIpAddresses(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
