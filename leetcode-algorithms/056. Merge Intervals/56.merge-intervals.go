package main

import (
	"fmt"
	"reflect"
)

func merge(intervals [][]int) [][]int {
	if intervals == nil || len(intervals) <= 1 {
		return intervals
	}
	var interval = intervals[len(intervals)-1]
	var mergedIntervals = merge(intervals[:len(intervals)-1])

	return mergeOneIntervalToOthers(interval, mergedIntervals)

}

func mergeOneIntervalToOthers(one []int, others [][]int) [][]int {

	var result = [][]int{}
	var pivot = one

	for _, toCheck := range others {
		if canMerge(pivot, toCheck) {
			pivot = merge2Intervals(pivot, toCheck)
		} else {
			result = append(result, toCheck)
		}
	}
	result = append(result, pivot)
	return result
}

func canMerge(one []int, another []int) bool {
	if one == nil || another == nil || len(one) != 2 || len(another) != 2 {
		return false
	}
	if one[0] >= another[0] && one[0] <= another[1] ||
		one[1] >= another[0] && one[1] <= another[1] {
		return true
	}

	if another[0] >= one[0] && another[0] <= one[1] ||
		another[1] >= one[0] && another[1] <= one[1] {
		return true
	}

	return false
}
func merge2Intervals(one []int, another []int) []int {

	left := min(one[0], another[0])
	right := max(one[1], another[1])
	return []int{left, right}
}

func min(left int, right int) int {
	if left <= right {
		return left
	} else {
		return right
	}
}

func max(left int, right int) int {
	if left >= right {
		return left
	} else {
		return right
	}
}

type testCase struct {
	Input    [][]int
	Expected [][]int
}

func main() {

	var testCases = []testCase{
		{
			[][]int{{1, 3}, {2, 6}, {8, 10}, {15, 18}},
			[][]int{{1, 6}, {8, 10}, {15, 18}},
		},
		{
			[][]int{{1, 4}, {4, 5}},
			[][]int{{1, 5}},
		},
	}

	for _, test := range testCases {
		var got = merge(test.Input)

		if !reflect.DeepEqual(got, test.Expected) {
			fmt.Printf("input: %v, want: %v, got: %v \n", test.Input, test.Expected, got)
		}
	}

}
