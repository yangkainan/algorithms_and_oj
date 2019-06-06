package main

import (
	"fmt"

	"github.com/google/go-cmp/cmp"
)

func uniquePaths(m int, n int) int {
	if m <= 1 || n <= 1 {
		return 1
	}
	return uniquePaths(m-1, n) + uniquePaths(m, n-1)

}

func main() {
	tests := map[string]struct {
		inputM int
		inputN int
		want   int
	}{
		"1x1": {inputM: 1, inputN: 1, want: 1},
		"3x2": {inputM: 3, inputN: 2, want: 3},
		"3x3": {inputM: 7, inputN: 3, want: 28},
		"4x2": {inputM: 7, inputN: 3, want: 28},
		"4x3": {inputM: 7, inputN: 3, want: 28},
		"7x3": {inputM: 7, inputN: 3, want: 28},
		// "51x9": {inputM: 51, inputN: 9, want: 1916797311},
	}

	for name, tc := range tests {
		got := uniquePaths(tc.inputM, tc.inputN)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input MxN: %v x %v, want: %v, got: %v, diff: %v",
				name, tc.inputM, tc.inputN, tc.want, got, diff)
		}

	}

}
