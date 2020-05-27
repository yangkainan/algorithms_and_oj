/*
 * @lc app=leetcode.cn id=84 lang=golang
 *
 * [84] 柱状图中最大的矩形

	思路:
	1. 什么叫柱状图中的矩形?
		给定一个范围, 以最低的那个柱为高度, 与 X 轴, 在范围内构成的的图形)
	2. 怎么样找到最大?
		a. 设计一个计算函数, 给定范围, 算出来面积.
		b. 对于一个已经求出最大"矩形"的图中, 再增加一个柱要怎么处理?
			相当于, 计算一下, 包含这个新柱的, 最大矩形, 与之前的矩形相比.
			包含这个新柱就是 计算到这个柱长度为 1,2,3,4 .... 的柱的矩形.
 */
package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
	"sync"
)

// @lc code=start
func largestRectangleArea(heights []int) int {
	if len(heights) == 0 {
		return 0
	}
	return findLargestRectangleArea(heights, 0, len(heights) - 1)
}

func findLargestRectangleArea(heights []int, start, end int) int {
	if start == end {
		return calcRectangleArea(heights, start, end)
	}

	largest := findLargestRectangleArea(heights, start + 1, end)

	var wg sync.WaitGroup

	resChannel := make(chan int, len(heights))

	for tmpEnd := start; tmpEnd <= end; tmpEnd ++ {
		wg.Add(1)
		go func(s, e int) {
			defer wg.Done()
			resChannel <- calcRectangleArea(heights, s, e)
		}(start, tmpEnd)
	}

	//wg.Wait()
	//close(resChannel)
	go func() {
		wg.Wait()
		close(resChannel)
	}()
	for subLargest := range resChannel {
		if subLargest > largest{
			largest = subLargest
		}
	}

	return largest

}

func calcRectangleArea (heights[]int, start, end int) int {
	if (start > end) || start < 0 || end > len(heights) -1 {
		panic("out of scope of heights")
	}

	if start == end {
		return heights[start]
	}

	var height = findSmallestHeight(heights, start, end)
	var length = end - start + 1

	return height*length

}

func findSmallestHeight(heights[] int, start, end int) int {
	if (start > end) || start < 0 || end > len(heights) -1 {
		panic("out of scope of heights")
	}
	var smallest = heights[start]
	start ++
	for start <= end {
		if smallest > heights[start] {
			smallest = heights[start]
		}
		start ++
	}

	return smallest
}

// @lc code=end

func main() {
	hugeInput := make([]int, 2000)
	for i:= range hugeInput {
		hugeInput[i] = i
	}

	tests := map[string]struct {
		input []int
		want  int
	}{
		"输入: [2,1,5,6,2,3]\n输出: 10":       {input: []int{2,1,5,6,2,3}, want: 10},
		"输入: [1...2000]\n输出: 10":       {input: hugeInput, want: 1000000},
	}

	for name, tc := range tests {
		got := largestRectangleArea(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}

