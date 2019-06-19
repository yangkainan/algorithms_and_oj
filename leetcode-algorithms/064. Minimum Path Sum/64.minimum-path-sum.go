package main

import (
	"fmt"

	"github.com/google/go-cmp/cmp"
)

type point struct {
	x int
	y int
}

const UintSize = 32 << (^uint(0) >> 32 & 1) // 32 or 64

const (
	MaxInt  = 1<<(UintSize-1) - 1 // 1<<31 - 1 or 1<<63 - 1
	MinInt  = -MaxInt - 1         // -1 << 31 or -1 << 63
	MaxUint = 1<<UintSize - 1     // 1<<32 - 1 or 1<<64 - 1
)

func minPathSum(grid [][]int) int {
	if (len(grid)) == 0 {
		return grid[0][0]
	}
	var startPoint = point{0, 0}

	var endPoint = point{len(grid) - 1, len(grid[0]) - 1}

	var minLength = MaxInt

	var path = make([]point, 0)

	recursiveSearch(grid, startPoint, endPoint, path, &minLength)

	return minLength
}

func isSamePoint(p1, p2 point) bool {
	return p1.x == p2.x && p1.y == p2.y
}

func isExceeded(start, end point) bool {
	return start.x > end.x || start.y > end.y
}

func countLength(grid [][]int, path []point) int {
	var length = 0
	for i := 0; i < len(path); i++ {
		length += grid[path[i].x][path[i].y]
	}

	return length
}

func recursiveSearch(grid [][]int, start point, end point, path []point, minLength *int) {
	if isExceeded(start, end) {
		return
	}

	path = append(path, start)

	if isSamePoint(start, end) {
		var length = countLength(grid, path)
		if length < *minLength {
			*minLength = length
			// fmt.Printf("Path: %v\n", path)
		}
		return
	}

	var nextDownPoint = point{x: start.x + 1, y: start.y}
	recursiveSearch(grid, nextDownPoint, end, path, minLength)

	var nextRightPoint = point{x: start.x, y: start.y + 1}
	recursiveSearch(grid, nextRightPoint, end, path, minLength)

	path = path[:len(path)-1]
}

func main() {
	tests := map[string]struct {
		input [][]int
		want  int
	}{
		"1x1": {input: [][]int{{1}}, want: 1},
		"3x3": {input: [][]int{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}, want: 7},
	}

	for name, tc := range tests {
		got := minPathSum(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
