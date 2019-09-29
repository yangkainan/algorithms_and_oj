package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

/*
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

示例 1：

输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶

示例 2：

输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶
*/
func main() {
	tests := map[string]struct {
		input int
		want  int
	}{
		"2":       {input: 2, want: 2},
		"3":       {input: 3, want: 3},
	}

	for name, tc := range tests {
		got := climbStairs(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}

var cache_inter_result map[int]int = make(map[int]int)

func climbStairs(n int) int {

	if val, ok := cache_inter_result[n]; ok {
		return val
	}


	if n <= 0 {
		return 1
	}

	if n == 1 {
		return 1
	}

	result := climbStairs(n -1) + climbStairs( n - 2)
	cache_inter_result[n] = result
	return result

}




