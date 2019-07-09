package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
	"math"
	"sort"
)

/*
题目 ID：1092
题目详情
标题：生成最理想输入流打开任意密码锁
描述信息
题目背景
存在一个四位密码锁，每位可以是0-9中的一个数字，键盘上有0-9十个按键，类似于健身房或者保险箱的密码锁；开始的时候屏幕上面有四个"0"，即"0000"，每次按键时，新输入的数字出现在屏幕最后侧的格子里，最左侧的数字被移除，例如：屏幕上是"0000"，输入"1"后变成"0001"，再输入"2"则变成"0012"；如果屏幕上的组合和密码相同，则密码箱被打开。 我们有一只猴子，可以按照给定的顺序按键盘，需要为它生成一个输入流，这个输入流能够打开任意一个箱子。

问题1
最理想情况下（即不考虑是否实际可行），这个输入流最小的长度是多少？

问题2
写一段程序，生成这个最理想的输入流，或者返回结果这样的输入流不存在。

问题3
这种最理想的输入流真的存在嘛？ 如果存在的话，起始状态屏幕上的组合是否影响这个结论。

参考答案
问题1
10^4，在考虑一开始屏幕上已经验证了"0000"的情况下，回答10^4-1也可；若考虑起始为空屏幕，则可以答"10^+3。视沟通情况而定。 最理想情况是，没按一次按键，屏幕上便出现一个全新的组合，所以输入流长度与组合数量有关。

问题2
DFS。将每一个组合看成一个节点，如果通过一次按键可以从组合A变成组合B，则A到B存在一条有向边，于是问题可以抽象成在有向右环有自闭的图中的深度优先搜索；可以通过递归等方式实现代码，注意代码中的回溯。

问题3
存在的，但不要应试者给出正确的结论，重点考察思路和沟通；如果存在的话，起始状态应不影响是否存在的结论，因为输入流首尾相接可以成环。

*/

func generateStream(length int) []int{

	mod :=  int(math.Pow10(length))

	start := 0

	tmp := make([]int, 0)
	result := &tmp
	visited := make(map[int]bool)

	fmt.Printf("length: %v, mod: %v \n", length, mod)


	found := search(start, visited, mod, result)


	fmt.Printf("found?: %v, %v\n",found, *result)
	if found {
		return *result
	}

	return nil

}


func pushNodeInPath(path *[]int, node int, visitedNode *map[int]bool) {
	*path = append(*path, node)

	(*visitedNode)[node] = true

}

func popPath(path *[]int, visitedNode *map[int]bool) {

	lastIndex := len(*path) -1

	poped := (*path)[lastIndex]
	*path = (*path)[:lastIndex]
	(*visitedNode)[poped] = false
}


func search(current int, visitedNode map[int]bool, totalNumerOfCombination int, path *[]int) bool {


	pushNodeInPath(path, current, &visitedNode)

	if len(*path) == totalNumerOfCombination {
		return true
	}


	triedNextNode := make(map[int]bool)


	for {
		ok, next := findNextNode(current, totalNumerOfCombination, triedNextNode)

		if !ok {
			return false
		}

		if visitedNode[next] {
			continue
		}

		found := search(next, visitedNode, totalNumerOfCombination, path)

		if found {
			return true
		}


		popPath(path, &visitedNode)

	}

	return false

}



func findNextNode(current int, mod int, tried map[int]bool) (found bool, next int) {
	tmp := current * 10

	for i:= 0; i< 10; i++ {
		try := (tmp + i) % mod
		//fmt.Printf("mod: %v, currnt: %v, i: %v, try: %v, visited: %v \n",
		//	mod, current, i, try, visitedNode)
		if !tried[try] {
			tried[try] = true
			return true, try
		}
	}
	return false, 0
}


func main() {
	tests := map[string]struct {
		input int
		want  []int
	}{
		"0~9":       {input: 4, want: []int{1,2,3,4,5,6,7,8,9,0}},
		//"00~99":       {input: 2, want: []int{1,2,3,4,5,6,7,8,9,0}},
	}

	for name, tc := range tests {
		got := generateStream(tc.input)

		sort.Ints(got)
		sort.Ints(tc.want)

		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			//fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
			//	name,  tc.input, tc.want, got, diff)
			fmt.Printf("name: %v, input: %v", name,  tc.input)
		}

	}

}
