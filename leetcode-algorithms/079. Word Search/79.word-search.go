package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

/*
给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true.
给定 word = "SEE", 返回 true.
给定 word = "ABCB", 返回 false.
*/

func exist(board [][]byte, word string) bool {
	if board == nil || len(board) == 0 {
		return false
	}

	if len(word) == 0 {
		return true
	}

	startNode := node {0, 0}

	for row, _ := range board {
		for col, val:= range board[row] {

			if val == word[0] {
				startNode = node{row, col}
				visited:= map[string]bool{}
				setVisited(&visited, &startNode)

				if searchRecursively(board, word[1:], &visited, &startNode) {
					return true
				}
			}
		}
	}
	return false
}

type node struct {
	row int
	col int
}

func setVisited(visited *map[string]bool, node *node) {
	key := fmt.Sprintf("%d_%d", node.row, node.col)
	(*visited)[key] = true
}
func setUnVisited(visited *map[string]bool, node *node) {
	key := fmt.Sprintf("%d_%d", node.row, node.col)
	(*visited)[key] = false
}

func isVisited(visited *map[string]bool, node *node) bool{
	key := fmt.Sprintf("%d_%d", node.row, node.col)
	return (*visited)[key]

}

func searchRecursively(board [][]byte, word string,  visited *map[string]bool, prevNode *node) bool {
	if len(word) == 0 {
		return true
	}

	nextNodeList, subWord := findNext(board, word, visited, prevNode)
	for _, nextNode := range nextNodeList {
		setVisited(visited, nextNode)

		if searchRecursively(board, subWord,  visited, nextNode) {
			return true
		}
		setUnVisited(visited, nextNode)

	}
	return false
}

func findNext(board [][]byte, word string,  visited *map[string]bool, curNode *node) (allPossibleNextNodes []*node, subWord string) {


	row := curNode.row
	col := curNode.col
	subWord = word[1:]
	allPossibleNextNodes = []*node{}

	// left

	if col - 1 >=0 {
		nextNode := node {row , col - 1}
		if !isVisited(visited, &nextNode) && board[nextNode.row][nextNode.col]==word[0] {
			allPossibleNextNodes = append(allPossibleNextNodes, &nextNode)
		}

	}
	// right

	if (col + 1) < len(board[row]) {
		nextNode := node {row , col + 1}
		if !isVisited(visited, &nextNode) && board[nextNode.row][nextNode.col]==word[0] {
			allPossibleNextNodes = append(allPossibleNextNodes, &nextNode)
		}

	}


	// up
	if row - 1 >= 0 {
		nextNode := node {row - 1 , col}
		if !isVisited(visited, &nextNode) && board[nextNode.row][nextNode.col]==word[0] {
			allPossibleNextNodes = append(allPossibleNextNodes, &nextNode)
		}
	}
	// down

	if row + 1 < len(board) {
		nextNode := node {row + 1 , col}
		if !isVisited(visited, &nextNode) && board[nextNode.row][nextNode.col]==word[0] {
			allPossibleNextNodes = append(allPossibleNextNodes, &nextNode)
		}
	}

	if len(allPossibleNextNodes) > 0 {
		return allPossibleNextNodes, subWord
	} else {
		return allPossibleNextNodes, word
	}

}



func main() {


	board := [][]byte{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}
	tests := map[string]struct {
		input struct{
			board [][]byte
			word string
		}
		want  bool
	}{
		"ABCCED":       {input: struct {
			board [][]byte
			word  string
		}{board: board, word: string("ABCCED")}, want: true},
		"SEE":       {input: struct {
			board [][]byte
			word  string
		}{board: board, word: string("SEE")}, want: true},
		"ABCB":       {input: struct {
			board [][]byte
			word  string
		}{board: board, word: string("ABCB")}, want: false},
		"ABCESEEEFS":       {input: struct {
			board [][]byte
			word  string
		}{board: [][]byte{{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}}, word: string("ABCESEEEFS")}, want: true},

	}

	for name, tc := range tests {
		got := exist(tc.input.board, tc.input.word)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
