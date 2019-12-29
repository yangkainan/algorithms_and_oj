package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)
/*

心得:
1. 界定 开启一轮 移动的时机: 发现了 Duplicate 串, 且已经到达的 Duplicate 串的"尾"
2. 在 移动的时候, 要明确移动的范围:  新的起点, 原起点, 移动多少个元素.
	移动多少个元素可以放松到, 每次都移动到 Nums 的尾.
3. 确定 算法的结束条件(确保一定结束) (金字塔原则: 不重, 不漏): "遍历了整个 Nums 数组". 但是,
	因为, Nums 的内容会不停的"向前"移动, 所以, "当前位置"在移动后,也要同步的"前移". (否则会 "漏"元素)
	同时, 因为, 移动后,"尾部"元素相当于已经删除了, 一定不要访问(因为, 删除部分也可能包含 Duplicate 串, 会重复计算).(否则会 "重复")

4. 还有要注意, "循环体结束后", 会不会出现 "遗留"的, 没有被计算的 Duplicate 串(只时, 已经不需要 移动了).

在写的过程中, 最开始没有识别出 "RemovedLen" 这个变量, 它的核心是标识"尾部被删除的范围", 同时也可以用来 计算 "新长度".
之后, RemoveLen 的计算是基于"DupEnd 和 DupStart"来做的, 但是, 没有想明白 "当这两个 Pos 相同的时候,要怎么计算" (DupEnd-DupStart 后要不要+1. 加与不加都不对... )
细致的分析一下, 发现, 应该直接使用 DupLen 来计算.  DupEnd,DupStart 只是在移动的时候使用.

*/

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

const DefinitionOfDuplicate = 2
func removeDuplicates(nums []int) int {
	if len(nums) <= DefinitionOfDuplicate {
		return len(nums)
	}

	removedLen := 0

	curPos := 1

	dupStart, dupEnd := curPos, curPos

	dupLen := 1
	for ; curPos < len(nums) - removedLen; curPos++ {

		if nums[curPos- 1] == nums[curPos] {
			dupLen ++
			if dupLen == (DefinitionOfDuplicate + 1) {
				dupStart = curPos
			}
		} else {
			dupEnd = curPos
			if dupLen > DefinitionOfDuplicate {
				numsOfElemToMove := len(nums) - removedLen - dupEnd
				move(nums, dupStart, dupEnd, numsOfElemToMove)
				removedLen += dupLen - DefinitionOfDuplicate
				curPos = dupStart
			}
			dupLen = 1
		}
	}

	// 最后一个连续串, 长度大于3
	if dupLen > DefinitionOfDuplicate {
		removedLen += dupLen - DefinitionOfDuplicate
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
