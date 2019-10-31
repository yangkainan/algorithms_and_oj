package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)
func sortColors(nums []int)  {
	if nums == nil {
		return
	}

	sortColorsWithHash(nums)
}

func sortColorsWithHash(nums []int) {
	hashCount := map[int]int {}

	for i:= 0; i < len(nums); i++ {
		key := nums[i]

		if val , ok := hashCount[key]; ok {
			hashCount[key] = val + 1
		} else {
			hashCount[key] = 1
		}
	}

	index := 0
	for i:= 0; i <= 2; i++ {
		cnt := hashCount[i]

		for j := 0; j< cnt; j++ {
			nums[index] = i
			index++
		}
	}

}

func sortColorsWithSort(nums []int)  {
	startIndex := 0
	length := len(nums)

	for ; startIndex < length ; startIndex++ {
		endIndex := length - 1

		for ;startIndex < endIndex; endIndex -- {
			if nums[startIndex] > nums[endIndex] {
				nums[startIndex], nums[endIndex] = nums[endIndex], nums[startIndex]
			}
		}
	}

}

func main() {
	tests := map[string]struct {
		input []int
		want  []int
	}{
		"simple":       {input: []int{0,2,1,1,2,0}, want: []int{0,0,1,1,2,2}},
	}

	for name, tc := range tests {
		sortColors(tc.input)
		got := tc.input
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
