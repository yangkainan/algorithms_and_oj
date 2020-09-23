package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
	"sort"
)

/*
Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/
func subsetsWithDup(nums []int) [][]int {

	var res = [][]int{}
	res = recursiveSubset(nums,0)

	return removeDup(res)

}

func removeDup(res [][]int) [][]int {
	if res == nil {
		return res
	}
	if len(res) <= 1 {
		return res
	}

	sort2D(res)

	var dedup [][]int


	var hash = make(map[string]int)
	for i := 0; i < len(res); i++ {
		var key = ""
		for j := 0; j < len(res[i]); j++ {
			key += fmt.Sprintf("_%d", res[i][j])
		}

		if _, ok := hash[key]; !ok {
			hash[key] = 0
			dedup = append(dedup, res[i])
		}

	}

	return dedup
}



func combine(to [][]int, new [][]int) [][]int {
	var res [][]int

	for _, val := range new{
		res = append(res, val)
	}

	for _, val := range to {
		res = append(res, val)
	}


	return res

}


func recursiveSubset(nums []int,  index int) [][]int {

	if index >= len(nums) {
		return [][]int{{}}
	}



	var excludeTmpRes = recursiveSubset(nums, index + 1)
	var includeTmpRes [][]int

	var cur = nums[index]
	for i := 0; i < len(excludeTmpRes); i++ {
		var tmp = make([]int, len(excludeTmpRes[i]))
		copy(tmp, excludeTmpRes[i])
		includeTmpRes = append(includeTmpRes, append(tmp, cur))
	}

	var res = combine(includeTmpRes, excludeTmpRes)
	return res

}


func sort2D(s [][]int) {
	for i := 0; i < len(s); i++ {
		sort.Ints(s[i])
	}

	sort.SliceStable(s, func(i, j int) bool {
		var left = s[i]
		var right = s[j]

		if len(left)  != len(right) {
			return len(left) < len(right)
		}

		for k := 0; k < len(left); k++ {
			if left[k] != right[k] {
				return left[k] < right[k]
			}
		}

		return false
	})
}

func main() {
	tests := map[string]struct {
		input []int
		want  [][]int
	}{
		"[]":       {input: []int{}, want: [][]int{{}}},
		"[1]":       {input: []int{1}, want: [][]int{{},{1}}},
		"[1,2]":       {input: []int{1, 2}, want: [][]int{{},{1}, {2}, {1,2}}},
		"[1,2,2]":       {input: []int{1,2,2}, want: [][]int{{}, {1}, {2}, {1,2},{2,2},{1,2,2}}},
		"[1,2,2,3]":       {input: []int{1,2,2,3}, want: [][]int{{}, {1}, {2},{3}, {1,2}, {1, 3},{2,2}, {2, 3},{1,2,2}, {1,2,3}, {2, 2, 3}, {1, 2, 2, 3}}},
	}

	for name, tc := range tests {
		got := subsetsWithDup(tc.input)

		sort2D(tc.want)

		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}


