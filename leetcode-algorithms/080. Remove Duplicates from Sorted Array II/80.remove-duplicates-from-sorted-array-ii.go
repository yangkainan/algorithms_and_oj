package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

/*
给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

示例 1:

给定 nums = [1,1,1,2,2,3],

函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。

你不需要考虑数组中超出新长度后面的元素。

示例 2:

给定 nums = [0,0,1,1,1,1,2,3,3],

函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。

你不需要考虑数组中超出新长度后面的元素。
*/
func removeDuplicates(nums []int) int {
	if len(nums) <= 2 {
		return len(nums)
	}

	removedLen := 0

	curPos := 1

	startPos, endPos := curPos, curPos

	dupLen := 1
	for ; curPos < len(nums) - removedLen; curPos++ {

		if nums[curPos- 1] == nums[curPos] {
			dupLen ++
			if dupLen == 3 {
				startPos = curPos
			}
		} else {
			endPos = curPos
			if dupLen > 2 {
				numsOfElemToMove := len(nums) - removedLen - endPos
				move(nums, startPos, endPos, numsOfElemToMove)
				removedLen += dupLen - 2
				curPos = startPos
			}
			dupLen = 1
		}
	}

	// 最后一个连续串, 长度大于3
	if dupLen > 2 {
		removedLen += dupLen - 2
	}

	return len(nums) - removedLen
}

func move(nums[]int, start, end, numsElementsToMove int) {

	if end <= start {
		return
	}

	for cnt:=0; cnt < numsElementsToMove ; cnt ++ {
		nums[start+ cnt] = nums[end+cnt]
	}

}

func main() {
	tests := map[string]struct {
		input []int
		want  int
	}{
		"[1,1,1]":       {input: []int{1,1,1}, want: 2},
		"[1,1,1,2,2,3]":       {input: []int{1,1,1,2,2,3}, want: 5},
		"[0,0,1,1,1,1,2,3,3]":       {input: []int{0,0,1,1,1,1,2,3,3}, want: 7},
	}

	for name, tc := range tests {
		got := removeDuplicates(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
