package main

import (
	"fmt"
	"testing"
)


func Test_ConvertTreeToArray(t *testing.T) {
	var root = &TreeNode{
		Val : 0,
		Left : nil,
		Right : &TreeNode{
			Val : 1,
			Left : &TreeNode{
				Val : 2,
				Left : nil,
				Right : nil,
			},
			Right : &TreeNode{
				Val : 3,
				Left : nil,
				Right : nil,
			},
		},
	}

	fmt.Println(ConvertTreeToArray(root))

}

func Test_ConstructTreeFromArray(t *testing.T) {
	var in = []string{"0", "null", "1", "2", "null", "null", "3", "null", "null"}

	var index = 0
	fmt.Println(ConvertTreeToArray(ConstructTreeFromArray(in, &index)))

}
