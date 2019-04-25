package main

import (
	"fmt"
	"math"
)

func maxSubArray(nums []int) int {
	var max = math.MinInt32
	if nums == nil || len(nums) <= 0 {
		return 0
	}

	for startIndex := 0; startIndex < len(nums); startIndex++ {
		var sum = 0

		for endIndex := startIndex; endIndex < len(nums); endIndex++ {

			sum += nums[endIndex]

			if sum > max {
				max = sum
			}

		}

	}

	return max

}

type TestCase struct {
	input    []int
	expected int
}

func main() {

	var testCases = []TestCase{
		{
			[]int{-2, 1, -3, 4, -1, 2, 1, -5, 4},
			6,
		},
		{
			nil,
			0,
		},
		{
			[]int{},
			0,
		},
		{
			[]int{-1},
			-1,
		},
	}

	for _, test := range testCases {

		actual := maxSubArray(test.input)

		if actual != test.expected {
			fmt.Printf("input: %v, expected: %v, actual: %v\n", test.input, test.expected, actual)
		}
	}

}
