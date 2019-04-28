package main

import "fmt"

func spiralOrder(matrix [][]int) []int {

	var visited = make(map[int]bool)

	if matrix == nil || len(matrix) == 0 {
		return []int{}
	}
	var result = []int{}
	var start_x, start_y int = 0, 0

	var end_x, end_y int = len(matrix) - 1, len(matrix[0]) - 1

	for {
		if !(start_x <= end_x && start_x >= 0 && start_y <= end_y && start_y >= 0) {
			break
		}
		var x = start_x
		var y = start_y
		for direction := 0; direction <= 3; direction++ {

			// out of scope, need to adjust
			if y > end_y {
				y = end_y
			}

			if y < start_y {
				y = start_y
			}

			if x > end_x {
				x = end_x
			}

			if x < start_x {
				x = start_x
			}

			// for each direction, check and append until out of scope
			for x <= end_x && x >= start_x && y <= end_y && y >= start_y {

				var element = matrix[x][y]
				if !visited[element] {
					result = append(result, element)
					visited[element] = true
				}
				switch direction {
				case 0:
					y++
				case 1:
					x++
				case 2:
					y--
				case 3:
					x--

				}
			}

		}

		start_x += 1
		start_y += 1

		end_x -= 1
		end_y -= 1

	}

	return result
}

func outlineOfMatrix(matrix [][]int, layer int) {

}

type testCase struct {
	input    [][]int
	expected []int
}

func sameArray(left []int, right []int) bool {
	if len(left) != len(right) {
		return false
	}

	for index, leftValue := range left {
		if leftValue != right[index] {
			return false
		}
	}

	return true
}

func main() {

	var testCaseList = []testCase{
		testCase{
			[][]int{
				[]int{1, 2, 3},
				[]int{4, 5, 6},
				[]int{7, 8, 9},
			},
			[]int{1, 2, 3, 6, 9, 8, 7, 4, 5},
		},
		testCase{
			[][]int{
				[]int{1, 2, 3, 4},
				[]int{5, 6, 7, 8},
				[]int{9, 10, 11, 12},
			},
			[]int{1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7},
		},
		testCase{
			[][]int{
				[]int{1, 2, 3},
				[]int{4, 5, 6},
				[]int{7, 8, 9},
				[]int{10, 11, 12},
			},
			[]int{1, 2, 3, 6, 9, 12, 11, 10, 7, 4, 5, 8},
		},
		testCase{
			[][]int{},
			[]int{},
		},
	}

	for _, test := range testCaseList {
		var actual = spiralOrder(test.input)
		if !sameArray(test.expected, actual) {
			fmt.Printf("input : %v, expected: %v, actual: %v\n", test.input, test.expected, actual)
		}

	}

}
