package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

/*
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：


	每行中的整数从左到右按升序排列。
	每行的第一个整数大于前一行的最后一个整数。


示例 1:

输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
输出: true


示例 2:

输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
输出: false

*/

type index struct {
	row int
	col int

	matrix * [][]int


}

func (i index) toNumber() int {
	totalCols := len((*i.matrix)[0])
	return i.row*totalCols+i.col
}

func parseNumber(num int, matrix *[][]int) index {

	totalCols := len((*matrix)[0])

	row := num / totalCols

	col := num - totalCols * row


	return index{row:row, col:col,matrix:matrix}

}


func findMid(start, end index) index {

	if start.toNumber() == end.toNumber() {
		return start
	}
	return parseNumber((start.toNumber() + end.toNumber())/2, start.matrix)

}

func searchMatrix(matrix [][]int, target int) bool {

	if len(matrix) == 0 || len(matrix[0]) == 0 {
		return false
	}

	totalRows := len(matrix)
	totalCols := len(matrix[0])

	return search(matrix, target, index{row:0, col:0, matrix:&matrix},
	index{row:(totalRows - 1), col:(totalCols -1), matrix:&matrix })
}

func getValue(matrix [][]int, i index) int {
	return matrix[i.row][i.col]
}

func search(matrix [][]int, target int, startIndex index, endIndex index) bool {

	if endIndex.toNumber() - startIndex.toNumber() <= 1 {
		left := getValue(matrix, startIndex)
		right := getValue(matrix, endIndex)

		if target == left || target == right {
			return true
		} else {
			return false
		}
	}

	mid := findMid(startIndex, endIndex)

	midVal := getValue(matrix, mid)
	if target >  midVal{
		return search(matrix, target, mid, endIndex)
	}else if target == midVal {
		return true
	} else {
		return search(matrix, target, startIndex, mid)
	}

}

func main() {
	tests := map[string]struct {
		input [][]int
		target int
		want  bool
	}{
		"found":       {input: [][]int{{1,3,5,7},{10,11,16,20},{23,30,34,50}}, target:3, want: true},
		"not found":       {input: [][]int{{1,3,5,7},{10,11,16,20},{23,30,34,50}},target:13, want: false},
		"not found another":       {input: [][]int{{1}},target:0, want: false},
	}

	for name, tc := range tests {
		got := searchMatrix(tc.input, tc.target)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}



