package main

import (
	"fmt"
	"strings"

	"github.com/google/go-cmp/cmp"
)

func lengthOfLastWord(s string) int {
	if len(s) == 0 {
		return 0
	}

	var trimedStr = strings.Trim(s, " ")
	if len(trimedStr) == 0 {
		return 0
	}

	var startOfLastWord = strings.LastIndex(trimedStr, " ")

	if startOfLastWord >= 0 {
		return len(trimedStr[startOfLastWord+1:])
	}
	return len(trimedStr)

}

func main() {
	tests := map[string]struct {
		input string
		want  int
	}{
		"empty":       {input: "", want: 0},
		"space":       {input: "  ", want: 0},
		"one world":   {input: "a", want: 1},
		"hello world": {input: "hello world", want: 5},
	}

	for name, tc := range tests {
		got := lengthOfLastWord(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("Test Name: %v, Input: %v, Want: %v, Got: %v, diff:%v\n", name, tc.input, tc.want, got, diff)
		}
	}

}
