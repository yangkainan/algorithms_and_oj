package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

/*
给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：


	插入一个字符
	删除一个字符
	替换一个字符


示例 1:

输入: word1 = "horse", word2 = "ros"
输出: 3
解释:
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')


示例 2:

输入: word1 = "intention", word2 = "execution"
输出: 5
解释:
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
*/

func minDistance(word1 string, word2 string) int {
	visited := map[string]int{}
	return minDistanceWrap(word1, word2, &visited, 0, len(word1) + len(word2))
}

func minDistanceWrap(word1 string, word2 string, visited *map[string]int, currentSteps int, limit int) int {

	cacheKey := word1+":"+word2

	if distance, ok := (*visited)[cacheKey]; ok {
		return distance
	}


	if currentSteps >= limit {
		return limit
	}

	if word1 == "" {
		return len(word2)
	}

	if word2 == "" {
		return len(word1)
	}

	if word1 == word2 {
		return 0
	}


	var  res1, res2, res3 int

	firstCharOfWord1 := word1[0:1]
	firstCharOfWord2 := word2[0:1]

	cost := 0
	//if firstCharOfWord1 == firstCharOfWord2 {
	//	//res1 = minDistanceWrap(word1[1:], word2[1:], visited, currentSteps , limit)
	//	cost = 0
	//
	//} else {
	//	// 替换
	//	//res1 = 1 + minDistanceWrap(word1[1:], word2[1:], visited, currentSteps + 1, limit)
	//	cost = 1
	//}

	if firstCharOfWord1 != firstCharOfWord2 {
		cost = 1
	}

	res1 = cost + minDistanceWrap(word1[1:], word2[1:], visited, currentSteps + cost, limit)

	// 删除1
	res2 =  1 + minDistanceWrap(word1[1:], word2, visited, currentSteps + 1, limit)
	// 删除2
	res3 =  1 + minDistanceWrap(word1, word2[1:], visited, currentSteps + 1, limit)

	result := min(res1, res2, res3)

	(*visited)[cacheKey] = result

	return result

}

func min(a,b,c int) int {
	result := a
	if result > b {
		result = b
	}

	if result > c {
		result = c
	}
	return result
}


func main() {
	tests := map[string]struct {
		input [] string
		want  int
	}{
		"horse -> ros":           {input: []string{"horse", "ros"}, want: 3},
		"intention -> execution": {input: []string{"intention", "execution"}, want: 5},
		"dinitrophenylhydrazine -> benzalphenylhydrazone": {input:[]string{"dinitrophenylhydrazine","benzalphenylhydrazone"}, want:7},
		"teacher -> tepache": {input:[]string{"teacher", "tepache"}, want:2},
		"pneumonoultramicroscopicsilicovolcanoconiosis -> ultramicroscopically": {input:[]string{"pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically"}, want:27},
		"really long": {input:[]string{
			"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef",
			"bcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefg"},
			want:2},
	}

	for name, tc := range tests {
		got := minDistance(tc.input[0], tc.input[1])
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
