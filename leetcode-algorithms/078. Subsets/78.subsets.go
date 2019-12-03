package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
	"sort"
	"strings"
)

/*
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/
func subsets(nums []int) [][]int {

	sort.Ints(nums)

	tmp := [][]int{}

	for i := 0; i <= len(nums); i++ {
		tmp = append(tmp, combination(nums, i)...)
	}

	tmp = deduplicate(tmp)

	for index, _ := range tmp {
		sort.Ints(tmp[index])
	}
	return tmp
}

func makeKey(arr []int) string {

	sort.Ints(arr)
	return strings.Trim(strings.Join(strings.Split(fmt.Sprint(arr), " "), "_"), "[]")

}
func deduplicate(in [][]int) [][]int {

	existed := make(map[string]bool)

	var result [][]int

	for _, val := range in {
		key := makeKey(val)

		if _, ok := existed[key]; !ok {
			existed[key] = true
			result = append(result, val)
		}

	}
	return result
}

func combination(nums []int, pick int) [][]int {

	return combinationRecursive(nums, []int{}, pick)

}

func combinationRecursive(nums []int, candidate []int, pick int) [][]int {
	if pick == 0 {
		result := make([]int, len(candidate))
		copy(result, candidate)
		return [][]int{result}
	}

	if pick > len(nums) {
		return [][]int{}
	}

	if pick == len(nums) {
		result := make([]int, len(candidate))
		copy(result, candidate)
		return [][]int{append(result, nums...)}
	}

	cur := nums[0]

	remain := remove(nums, 0)

	excludeCur := combinationRecursive(remain, candidate, pick)
	includeCur := combinationRecursive(remain, append(candidate, cur), pick-1)

	return append(excludeCur, includeCur...)

}

func remove(nums []int, index int) []int {
	if nums == nil || len(nums) == 0 {
		return []int{}
	}
	if index < 0 || index >= len(nums) {
		return nums
	}
	var result = make([]int, len(nums))
	copy(result, nums)

	return append(result[0:index], result[index+1:]...)
}

func main() {
	tests := map[string]struct {
		input []int
		want  [][]int
	}{
		"1 2 3": {input: []int{1, 2, 3}, want: [][]int{{}, {3}, {2}, {1}, {2, 3}, {1, 3}, {1, 2}, {1, 2, 3}}},
		"9,0,3,5,7": {input: []int{9, 0, 3, 5, 7}, want: [][]int{{}, {9}, {7}, {5}, {3}, {0}, {7, 9}, {5, 9}, {5, 7}, {3, 9}, {3, 7}, {3, 5}, {0, 9}, {0, 7}, {0, 5}, {0, 3}, {5, 7, 9}, {3, 7, 9}, {3, 5, 9}, {3, 5, 7}, {0, 7, 9}, {0, 5, 9}, {0, 5, 7}, {0, 3, 9}, {0, 3, 7}, {0, 3, 5}, {3, 5, 7, 9}, {0, 5, 7, 9}, {0, 3, 7, 9}, {0, 3, 5, 9}, {0, 3, 5, 7}, {0, 3, 5, 7, 9}}},
	}

	for name, tc := range tests {
		got := subsets(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
