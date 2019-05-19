package main

import (
	"fmt"

	"github.com/google/go-cmp/cmp"
)

func generateMatrix(n int) [][]int {

	if n <= 0 {
		return [][]int{}

	}

	var result = make([][]int, n)

	for index := range result {
		result[index] = make([]int, n)
	}
	var direction = 0
	var rowIndex = 0
	var colIndex = 0
	for cnt := 1; cnt <= (n * n); cnt++ {
		if colIndex >= n || rowIndex >= n ||
			colIndex < 0 || rowIndex < 0 ||
			result[rowIndex][colIndex] != 0 {
			switch direction % 4 {
			case 0:
				colIndex--
				rowIndex++
			case 1:
				rowIndex--
				colIndex--
			case 2:
				colIndex++
				rowIndex--
			case 3:
				rowIndex++
				colIndex++

			}
			direction++
		}

		result[rowIndex][colIndex] = cnt

		switch direction % 4 {
		case 0:
			colIndex++
		case 1:
			rowIndex++
		case 2:
			colIndex--
		case 3:
			rowIndex--

		}

	}

	return result

}

func main() {
	tests := map[string]struct {
		input int
		want  [][]int
	}{
		"empty":    {input: 0, want: [][]int{}},
		"negative": {input: -1, want: [][]int{}},
		"3x3": {input: 3, want: [][]int{
			{1, 2, 3},
			{8, 9, 4},
			{7, 6, 5},
		}},
		"4x4": {input: 4, want: [][]int{
			{1, 2, 3, 4},
			{12, 13, 14, 5},
			{11, 16, 15, 6},
			{10, 9, 8, 7},
		}},
	}

	for name, tc := range tests {
		got := generateMatrix(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("Test Name: %v, Input: %v, Want: %v, Got: %v, Diff:%v\n",
				name, tc.input, tc.want, got, diff)
		}
	}

}
