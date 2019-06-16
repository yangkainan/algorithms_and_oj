package main

import (
	"fmt"

	"github.com/google/go-cmp/cmp"
)

type point struct {
	x int
	y int
}

func uniquePathsWithObstacles(obstacleGrid [][]int) int {

	var endPoint = point{}
	endPoint.x = len(obstacleGrid) - 1

	if endPoint.x < 0 {
		return 0
	}

	endPoint.y = len(obstacleGrid[0]) - 1

	var startPoint = point{0, 0}

	return uniqPaths(obstacleGrid, startPoint, endPoint)

}

func samePoint(p1, p2 point) bool {
	return p1.x == p2.x && p1.y == p2.y
}

func isBlocked(grid [][]int, p point) bool {
	return grid[p.x][p.y] == 1
}

func isExceed(current, end point) bool {
	return current.x > end.x || current.y > end.y
}

func uniqPaths(grid [][]int, currentPoint point, endPoint point) int {
	if isExceed(currentPoint, endPoint) {
		return 0
	}
	if isBlocked(grid, currentPoint) {
		return 0
	}
	if samePoint(currentPoint, endPoint) {
		return 1
	}

	var nextDownPoint = point{currentPoint.x + 1, currentPoint.y}
	var nextRightPoint = point{currentPoint.x, currentPoint.y + 1}

	return uniqPaths(grid, nextRightPoint, endPoint) + uniqPaths(grid, nextDownPoint, endPoint)

}

func main() {
	tests := map[string]struct {
		input [][]int
		want  int
	}{
		"3*3": {input: [][]int{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}},
			want: 2},
		"1 block": {input: [][]int{{1}}, want: 0},
	}

	for name, tc := range tests {
		got := uniquePathsWithObstacles(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
