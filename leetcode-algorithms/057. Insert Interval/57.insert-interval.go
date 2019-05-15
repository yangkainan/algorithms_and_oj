package main

import (
	"fmt"

	"github.com/google/go-cmp/cmp"
)

func insert(intervals [][]int, newInterval []int) [][]int {
	if newInterval == nil || len(newInterval) == 0 {
		return intervals
	}

	if intervals == nil || len(intervals) == 0 {
		return [][]int{newInterval}
	}

	var intervalsWithoutMerge = insertWithoutMerge(intervals, newInterval)

	return mergeIntervals(intervalsWithoutMerge)

}

func insertWithoutMerge(intervals [][]int, newInterval []int) [][]int {
	var result [][]int

	var inserted = false
	for index := 0; index < len(intervals); index++ {
		var pivot = intervals[index]
		if !inserted && pivot[0] > newInterval[0] {
			result = append(result, newInterval)
			inserted = true
		}
		result = append(result, pivot)

	}

	if !inserted {
		result = append(result, newInterval)
	}

	return result
}

func mergeIntervals(intervals [][]int) [][]int {
	if intervals == nil || len(intervals) == 0 {
		return [][]int{}
	}

	var one = intervals[0]
	var subMerged = mergeIntervals(intervals[1:])
	return leftMerge(one, subMerged)
}

func leftMerge(one []int, intervals [][]int) [][]int {

	if one == nil || len(one) < 2 {
		return intervals
	}

	if intervals == nil || len(intervals) == 0 {
		return [][]int{one}
	}

	if canMerge(one, intervals[0]) {
		return leftMerge(merge(one, intervals[0]), intervals[1:])
	}

	return append([][]int{one}, intervals...)

}

func merge(one []int, another []int) []int {
	var left = min(one[0], another[0])
	var right = max(one[1], another[1])
	return []int{left, right}
}

func canMerge(one []int, another []int) bool {
	var oneLeft = one[0]
	var oneRight = one[1]
	var anotherLeft = another[0]
	var anotherRight = another[1]

	if oneLeft >= anotherLeft && oneLeft <= anotherRight {
		return true
	}

	if anotherLeft >= oneLeft && anotherLeft <= oneRight {
		return true
	}

	return false
}

func min(a int, b int) int {
	if a <= b {
		return a
	}

	return b
}

func max(a int, b int) int {
	if a >= b {
		return a
	}
	return b
}

func main() {
	tests := map[string]struct {
		intervals   [][]int
		newInterval []int
		want        [][]int
	}{
		"simple":  {intervals: [][]int{{1, 3}, {6, 9}}, newInterval: []int{2, 5}, want: [][]int{{1, 5}, {6, 9}}},
		"complex": {intervals: [][]int{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, newInterval: []int{4, 8}, want: [][]int{{1, 2}, {3, 10}, {12, 16}}},
		"last":    {intervals: [][]int{{1, 5}}, newInterval: []int{2, 7}, want: [][]int{{1, 7}}},
	}

	for name, tc := range tests {
		got := insert(tc.intervals, tc.newInterval)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("test name: %s, intervals: %v, newInterval: %v, expected: %v, got: %v, diff: %v\n", name, tc.intervals, tc.newInterval, tc.want, got, diff)
		}
	}
}
