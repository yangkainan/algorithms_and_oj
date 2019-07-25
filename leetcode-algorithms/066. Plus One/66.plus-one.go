package main

import (
	"github.com/google/go-cmp/cmp"
	"fmt"
)

func plusOne(digits []int) []int {
	if digits == nil || len(digits) == 0 {
		return []int{}
	}

	result := make([]int, len(digits), len(digits))

	plusOneByStep(digits, len(digits) - 1, &result)

	return result
    
}

func plusOneByStep(digits []int, index int, result *[]int) {

	tmp := digits[index] + 1

	toPreceed := ((tmp/10) > 0)

	(*result)[index] = (tmp % 10)


	if (index == 0 && toPreceed) {
		(*result) = append((*result), 0)
		for j:= len(digits); j >0; j-- {
			(*result)[j] = (*result)[j - 1]
		}
		(*result)[0] = 1
		return
	}

	if (toPreceed) {
		plusOneByStep(digits, index -1, result)
		return
	}

	for i:= 0; i < index; i++ {
		(*result)[i] = digits[i]
	}
}

func main() {
	tests := map[string]struct {
		input []int
		want  []int
	}{
		"empty":       {input: []int{}, want: []int{}},
		"0 + 1":       {input: []int{0}, want: []int{1}},
		"9 + 1":    {input: []int{9}, want: []int{1,0}},
	}

	for name, tc := range tests {
		got := plusOne(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}