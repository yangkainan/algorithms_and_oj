package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

/*

给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

示例 1:

输入:
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出:
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]


示例 2:

输入:
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出:
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]

进阶:


	一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
	一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
	你能想出一个常数空间的解决方案吗？

*/

func setZeroes(matrix [][]int) {
	setZeroes_N_plus_M(matrix)
	//setZeroesConstExtraSpace(matrix)
}


func setZeroesConstExtraSpace(matrix [][]int) {
	nonExitsElement := 1 << 31 -1
	for row := 0; row < len(matrix); row ++ {
		for col:=0; col < len(matrix[row]); col ++ {
			if matrix[row][col] == 0 {

				for _row:= 0; _row < len(matrix); _row ++ {
					for _col:= 0; _col < len(matrix[_row]); _col ++ {
						if (_row == row || _col == col) && matrix[_row][_col] != 0  {
							matrix[_row][_col] = nonExitsElement
						}
					}
				}

			}
		}
	}

	for _row:= 0; _row < len(matrix); _row ++ {
		for _col:= 0; _col < len(matrix[_row]); _col ++ {
			if matrix[_row][_col] == nonExitsElement {
				matrix[_row][_col] = 0
			}
		}
	}


}

func setZeroes_N_plus_M(matrix [][]int)  {

	rowMap := map[int]bool{}
	colMap := map[int]bool{}

	for row := 0; row < len(matrix); row ++ {
		for col:=0; col < len(matrix[row]); col ++ {
			if matrix[row][col] == 0 {
				rowMap[row] = true
				colMap[col] = true
			}
		}
	}

	for row := 0; row < len(matrix); row ++ {
		for col:=0; col < len(matrix[row]); col ++ {
			if rowMap[row] || colMap[col] {
				matrix[row][col] = 0
			}
		}
	}

}

func main() {
	tests := map[string]struct {
		input [][]int
		want  [][]int
	}{
		"1":       {input: [][]int{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}, want: [][]int{{1,0,1},{0,0,0},{1,0,1}}},
		"2":       {input: [][]int{{0,1, 2, 0}, {3,4,5,2}, {1, 3,1,5}}, want: [][]int{{0,0,0,0},{0,4,5,0},{0,3,1,0}}},
	}

	for name, tc := range tests {
		setZeroes(tc.input)
		got := tc.input
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
