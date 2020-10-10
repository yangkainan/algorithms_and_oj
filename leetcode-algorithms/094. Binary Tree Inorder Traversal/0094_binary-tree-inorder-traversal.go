package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}



func inorder(root *TreeNode, queue *[]*TreeNode) {

	if root == nil {
		return
	}

	if root.Left != nil {
		inorder(root.Left, queue)
	}
	(*queue) = append( *queue, root)

	if root.Right != nil {
		inorder(root.Right, queue)
	}

}

func inorderTraversal(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}

	var res []int

	var queue []*TreeNode

	inorder(root, &queue)


	for i := 0; i < len(queue); i++ {
		res = append(res, queue[i].Val)
	}


	return res
}



func main() {
	tests := map[string]struct {
		input *TreeNode
		want  []int
	}{
		"simple":       {input: &TreeNode{
			Val : 0,
			Left : nil,
			Right : &TreeNode{
				Val: 1,
				Left : &TreeNode{
					Val : 2,
					Left : nil,
					Right : nil,
				},
				Right : nil,
			},
		}, want: []int{0, 2, 1}},
	}

	for name, tc := range tests {
		got := inorderTraversal(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
