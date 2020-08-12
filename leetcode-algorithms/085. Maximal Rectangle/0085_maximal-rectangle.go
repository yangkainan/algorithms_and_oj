package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

func maximalRectangle(matrix [][]byte) int {
	if matrix == nil || len(matrix) == 0 || len(matrix[0]) == 0 {
		return 0
	}

	var startP = point{
		X: 0,
		Y: 0,
	}

	return maximalRecR(&matrix, &startP, 0)
}

func maximalRecR(matrix *[][]byte, startP *point, curMaxSize int) int{

	var maxSize = curMaxSize
	if exceedMatrix(matrix, startP) {
		return maxSize
	}

	if isValidP(matrix, startP) {
		//fmt.Printf("%v, maxSize: %v \n", startP, maxSize)
		var endP = startP
		for endP != nil {
			var tmpSize = calcRecSize(matrix, startP, endP)
			if tmpSize > maxSize{
				maxSize = tmpSize
			}
			endP = findNextEndP(matrix, startP, endP)
		}
	}

	var nextStartP = findNextStartP(matrix, startP)
	return maximalRecR(matrix, nextStartP, maxSize)
}

func isValidP(matrix *[][]byte, p *point) bool {
	if p == nil {
		return false
	}

	if exceedMatrix(matrix, p) {
		return false
	}

	if (*matrix)[p.X][p.Y] == '1' {
		return true
	}

	return false

}

func findNextStartP(matrix *[][]byte, curP *point) *point {
	var x = curP.X
	var y = curP.Y + 1

	for x < len(*matrix) {
		for y < len((*matrix)[0]) {
			var p = &point{
				X: x,
				Y: y,
			}
			if isValidP(matrix, p) {
				return p
			}
			y++
		}
		x ++
		y = 0
	}
	return nil
}

func findNextEndP(matrix *[][]byte, startP, curP *point) *point {

	var x = curP.X
	var y = curP.Y + 1

	for x < len(*matrix) {
		/*
		可以增加启发式规则, 例如, 优先同行向右, 如果遇到一个'0'的节点, 直接变换方向.

		同时, 可以根据, 当前最大的矩形面积, 直接增加 endP 的起始位置

		*/
		for y < len((*matrix)[0]) {
			var p = &point{
				X: x,
				Y: y,
			}
			if isValidP(matrix, p) {
				return p
			}
			y++
		}
		x ++
		y = startP.Y
	}

	return nil

}

func calcRecSize(matrix *[][]byte, startP, endP *point)  int {
	var size = 0
	var x = startP.X
	var y = startP.Y

	for x <= endP.X {
		if isValidP(matrix, &point{
			X: x,
			Y: y,
		}) {
			size ++
		} else {
			return 0
		}

		y ++
		if y > endP.Y {
			x ++
			y = startP.Y
		}
	}

	return  size
}



func exceedMatrix(matrix *[][]byte, p *point) bool {
	if p == nil {
		return true
	}

	if p.X < 0 || p.X >= len(*matrix) {
		return true
	}

	if p.Y < 0 || p.Y >= len((*matrix)[0]) {
		return true
	}
	return false
}

type point struct {
	X, Y int
}

func main() {
	tests := map[string]struct {
		input [][]byte
		want  int
	}{
		"case 1":       {input: [][]byte{
			{'1', '0', '1', '0', '0'},
			{'1', '0', '1', '1', '1'},
			{'1', '1', '1', '1', '1'},
			{'1', '0', '0', '1', '0'},
		}, want: 6},
		"case 2" :  {input: [][]byte{
			{'1'},
		}, want: 1},
		"case 3" :  {input: [][]byte{
			{'1', '0'},
			{'1', '0'},
		}, want: 2},
	}

	for name, tc := range tests {
		got := maximalRectangle(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
