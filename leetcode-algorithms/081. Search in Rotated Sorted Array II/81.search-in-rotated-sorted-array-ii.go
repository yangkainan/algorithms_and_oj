package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

/*

思路:
	核心还是使用2 分查找. 但是, 难点是, "中位数"取出来以后, 中位数的右侧 可能有一部分递增,之后还会遇到"极值后"还会递减.
	即, 就算是 知道 当前"中间位置"的数据, 与 Target 的大小了, 还是要向左和右都继续查找.
	因为, "旋转点"只有一个, 所以, 每次通过比较 "中间位置" 与 "边界"元素的大小可以判断 "旋转点"是在哪个区间.
	注意的是, 当前的mid 就可能是 旋转点. 即 mid 的"左侧是有序"且 "右侧也是有序的"
	如果, 当前区间中没有"旋转点", 可以回退到普通的2 分查找算法.



实现细节:
	通过 start, end 来计算 Mid 并且判断中止的时候要小心"不收敛". 例如  start == 2, end == 3  -> mid = 2 ==>

*/

/*
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。

编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。

示例 1:

输入: nums = [2,5,6,0,0,1,2], target = 0
输出: true


示例 2:

输入: nums = [2,5,6,0,0,1,2], target = 3
输出: false

进阶:


	这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
	这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？

*/
func search(nums []int, target int) bool {
	if len(nums) == 0 {
		return false
	}
	return binSearch(nums, 0, len(nums) - 1, target)

}

func binSearch(nums []int, start, end int, target int) bool {
	if end > start + 1 {
		mid := (start + end) / 2

		leftRangeSorted := false
		rightRangeSorted := false


		if nums[start] <= nums[mid] {
			leftRangeSorted = true
		}

		if nums[end] >= nums[mid] {
			rightRangeSorted = true
		}


		if nums[mid] == target {
			return true
		} else if nums[mid] < target {

			if leftRangeSorted && rightRangeSorted {

				if nums[mid] > nums[mid + 1] {
					// mid是最大值
					return false
				}
				if nums[mid - 1] > nums[mid] {
					// mid 是最小值.
					if target <= nums[end] {
						return binSearch(nums, mid +1, end, target)
					}

					if target <= nums[mid -1] {
						return binSearch(nums, start, mid - 1, target)
					}

					return false
				}
				// 全局有序
				return binSearch(nums, mid + 1, end, target)
			}

			if rightRangeSorted {
				if target <= nums[end] {
					return binSearch(nums, mid + 1, end, target)
				} else {
					return binSearch(nums, start, mid - 1, target)
				}
			}

			if leftRangeSorted {
				return binSearch(nums, mid + 1, end, target)
			}

			return false

		} else {

			if leftRangeSorted && rightRangeSorted {

				if nums[mid] > nums[mid + 1] {
					// mid是最大值
					if target <= nums[end] {
						return binSearch(nums, mid +1, end, target)
					}

					if target <= nums[mid -1] {
						return binSearch(nums, start, mid - 1, target)
					}
					return false

				}
				if nums[mid - 1] > nums[mid] {
					// mid 是最小值.
					return false
				}
				// 全局有序
				return binSearch(nums, start, mid - 1, target)
			}

			if rightRangeSorted {
				return binSearch(nums, start, mid - 1, target)
			}

			if leftRangeSorted {

				if target >= nums[start] {
					return binSearch(nums, start, mid - 1, target)
				} else {
					return binSearch(nums, mid+1, end, target)
				}
			}

			return false

		}

	} else {
		return nums[start] == target || nums[end] == target
	}

}

func main() {
	tests := map[string]struct {
		input struct{
					nums []int
					target int
		}
		want  bool
	}{
		"2,5,6,0,0,1,2: 0":       {input: struct {
			nums   []int
			target int
		}{nums: []int{2,5,6,0,0,1,2}, target: 0}, want: true},

		"2,5,6,0,0,1,2: 3":       {input: struct {
			nums   []int
			target int
		}{nums: []int{2,5,6,0,0,1,2}, target: 3}, want: false},
		"1,1,1,1,1,1,1: 3":       {input: struct {
			nums   []int
			target int
		}{nums: []int{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, target: 3}, want: false},
	}

	for name, tc := range tests {
		got := search(tc.input.nums, tc.input.target)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
