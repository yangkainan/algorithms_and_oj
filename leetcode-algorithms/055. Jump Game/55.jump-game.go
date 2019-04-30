package main

import "fmt"

func canJump(nums []int) bool {
	if nums == nil {
		return false
	}
	if len(nums) == 0 {
		return true
	}
	return canJumpDP(nums, 0)
}

func canJumpDP(nums []int, index int) bool {
	if index >= (len(nums) - 1) {
		return true
	}
	for maxJumpNumOfSteps := nums[index]; maxJumpNumOfSteps > 0; maxJumpNumOfSteps-- {
		if canJumpDP(nums, index+maxJumpNumOfSteps) {
			return true
		}
	}
	return false
}

type testCase struct {
	input    []int
	expected bool
}

func main() {

	var testCases = []testCase{
		{
			[]int{2, 3, 1, 1, 4},
			true,
		},
		{
			[]int{3, 2, 1, 0, 4},
			false,
		},
	}

	for _, test := range testCases {
		var actual = canJump(test.input)
		if actual != test.expected {
			fmt.Printf("input: %v, expected: %v, actual: %v\n", test.input, test.expected, actual)
		}

	}

}
