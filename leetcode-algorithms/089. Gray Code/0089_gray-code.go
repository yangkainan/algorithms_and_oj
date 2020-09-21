package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

func grayCode(n int) []int {
	if n < 0 {
		return nil
	}

	return recursiveGrayCode(n, []int{0})
}

func recursiveGrayCode(n int, visited []int) []int {

	var max = (1 << n)
	if len(visited) == max {
		return visited
	}

	var pre = visited[len(visited) - 1]
	for i := 0; i < max; i++ {
		if !contained(visited, i) && isValid(pre , i) {
			visited = append(visited, i)
			var tmp = recursiveGrayCode(n, visited)

			if tmp != nil {
				return tmp
			}
		}

	}

	return nil
}

func isValid(i int, i2 int) bool {
	var tmp = (i ^ i2)

	return (tmp & (tmp -1)) == 0

}


func contained(visited []int, i int) bool {
	for _, val := range visited {
		if val == i {
			return true
		}
	}
	return false
}

func main() {
	tests := map[string]struct {
		input int
		want  []int
	}{
		"2":       {input: 10, want: []int{0, 1,3,2}},
		//"3":       {input: 3, want: []int{0, 1,3,2}},
	}

	for name, tc := range tests {
		got := grayCode(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}

