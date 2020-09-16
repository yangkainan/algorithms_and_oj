package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

func merge(nums1 []int, m int, nums2 []int, n int)  {
	var tmp []int = make([]int, m+n)

	var i = 0
	var j = 0
	var index = 0

	for i <m && j < n {
		if nums1[i] <= nums2[j] {
			tmp[index] = nums1[i]
			i++
		} else {
			tmp[index] = nums2[j]
			j++
		}
		index++
	}

	for i < m {
		tmp[index] = nums1[i]
		index ++
		i++
	}

	for j < n {
		tmp[index] = nums2[j]
		index ++
		j++
	}

	for k :=0; k < len(tmp); k++ {
		nums1[k] = tmp[k]
	}
   
}
func shiftRight(nums []int, index int) {
	var i = len(nums) -1
	for i > index {
		nums[i] = nums[i-1]
		i--
	}
}

func mergeInPlace(nums1 []int, m int, nums2 []int, n int)  {
	var i = 0
	var j = 0
	var index = 0

	for i < m && j < n {
		if nums1[index] <= nums2[j] {
			i++
		} else {
			shiftRight(nums1, index)
			nums1[index] = nums2[j]
			j++
		}
		index++
	}

	for j < n {
		nums1[index] = nums2[j]
		index++
		j++
	}
}

func main() {
	tests := map[string]struct {
		input struct{
			nums1 []int
			m int
			nums2 []int
			n int
		}
		want  []int
	}{
		"simple":       {input: struct {
			nums1 []int
			m     int
			nums2 []int
			n     int
		}{nums1: []int{1, 2, 3, 0, 0, 0}, m: 3, nums2: []int{2, 5, 6}, n: 3}, want: []int{1,2,2,3,5,6}},
	}

	for name, tc := range tests {
		mergeInPlace(tc.input.nums1, tc.input.m, tc.input.nums2, tc.input.n)
		got := tc.input.nums1
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
