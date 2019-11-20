package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
	"sort"
)

/*
给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:

输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/
func combine(n int, k int) [][]int {

	if n <= 0 || k <= 0 || k > n {
		return [][]int{}
	}

	var all []int

	for i:=1; i <= n; i++ {
		all = append(all, i)
	}

	if n == k {
		return [][]int{all}
	}


	tmp:=combineRecursive(all, []int{}, k)

	return deDuplicate(tmp)

}

func deDuplicate(in [][]int) [][]int{

	result := [][]int{}
	existed := map[string]bool{}

	for _, val := range in {
		sort.Ints(val)

		key := ""
		for _, ele := range val {
			key += "_" + fmt.Sprintf("%d", ele)
		}

		if _, ok := existed[key]; !ok {
			result = append(result, val)
			existed[key] = true
		}


	}
	return result
}

func removeByIndex (tgt []int, index int) []int {

	array := make([]int, len(tgt))

	copy(array, tgt)

	if index < 0 || index >= len(array) {
		return array
	}

	return append(array[:index], array[index+1:]...)

}

func combineRecursive(candidate []int, partial []int, k int) [][]int{
	if k == 0 {
		return [][]int{partial}
	}

	if len(candidate) <= 0  || len(candidate) < k {
		return [][]int{}
	}

	result := [][]int{}

	for i:= 0; i < len(candidate); i ++ {

		cur := candidate[i]
		subCandidate := removeByIndex(candidate, i)

		subInclude := combineRecursive(subCandidate,  append(partial, cur), k - 1)
		subExclude := combineRecursive(subCandidate,  partial, k)

		result = append(result, subInclude...)
		result = append(result, subExclude...)
	}

	return result

}

func main() {
	tests := map[string]struct {
		input struct {
			n int
			k int
		}
		want  [][]int
	}{
		"C_4_2":       {input: struct {
			n int
			k int
		}{n: 4, k: 2} , want: [][]int{{2,4},{3,4},{2,3},{1,2},{1,3},{1,4}}},
		"C_10_7":       {input: struct {
			n int
			k int
		}{n: 10, k: 7} , want: [][]int{}},
	}

	for name, tc := range tests {
		got := combine(tc.input.n, tc.input.k)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
