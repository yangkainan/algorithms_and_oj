package main

import (
	"github.com/google/go-cmp/cmp"
	"strings"
	"strconv"
	"fmt"
)
func addBinary(a string, b string) string {

	arrayA := parse(a)
	arrayB := parse(b)
	arrayAPlusB := add(arrayA, arrayB)
	return translate(arrayAPlusB)

}

func parse(a string) []int {

	tmp := strings.Split(a,"")

	result := make([]int, len(tmp), len(tmp))

	for i:= 0; i < len(tmp); i++{
		tmpVal, err := strconv.Atoi(tmp[i])

		if err != nil || tmpVal > 1 || tmpVal < 0 {
			panic(fmt.Sprint("input is not valid: %v", a))
		}
		result[i] = tmpVal
	}
	return result
}

func add(a []int, b []int) []int{

	aLen := len(a)
	bLen := len(b)

	lenResult := 0

	if aLen >= bLen {
		lenResult = aLen + 1
	} else {
		lenResult = bLen + 1
	}

	result := make([]int, 0,lenResult)

	exceeded := 0
	aIndex := aLen -1
	bIndex := bLen - 1
	for ; aIndex >= 0 && bIndex >= 0;  {
		tmp := a[aIndex] + b[bIndex] + exceeded
		remaining := tmp % 2
		exceeded = (tmp / 2)

		result = append([]int{remaining}, result...)

		aIndex --
		bIndex --
	}

	for ; aIndex >=0; aIndex -- {
		tmp := a[aIndex] + exceeded
		remaining := tmp % 2
		exceeded = (tmp / 2)

		result = append([]int{remaining}, result...)
	}

	for ; bIndex >=0; bIndex -- {
		tmp := b[bIndex] + exceeded
		remaining := tmp % 2
		exceeded = (tmp / 2)

		result = append([]int{remaining}, result...)
	}

	if exceeded > 0 {
		result = append([]int{exceeded}, result...)
	}

	return result
}

func translate(a []int) string {

	result := ""
	for i:= 0; i < len(a); i ++ {
		result = result + strconv.Itoa(a[i])
	}
	return result
}

func main() {
	tests := map[string]struct {
		input []string
		want  string
	}{
		"11 + 1":       {input: []string{"11", "1"}, want: "100"},
		"0 + 0":       {input: []string{"0", "0"}, want: "0"},
		"1010 + 1011":       {input: []string{"1010", "1011"}, want: "10101"},
	}

	for name, tc := range tests {
		got := addBinary(tc.input[0], tc.input[1])
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
