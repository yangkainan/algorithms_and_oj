package main

import (
	"strconv"
)


type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}


/*   Array,  from left to right, every 3 elements,  [root, left, right]
*/

func ConstructTreeFromArray(in []string, index *int) *TreeNode {
	if in == nil || (*index) >= len(in) {
		return nil
	}

	var tmpVal = in[*index]
	*index = (*index) + 1


	var val int
	var err error
	if val, err = strconv.Atoi(tmpVal); err != nil {
		return nil
	}

	var root = &TreeNode {
		Val : val,
		Left : nil,
		Right : nil,
	}

	var tmpLeft = ConstructTreeFromArray(in, index)
	var tmpRight = ConstructTreeFromArray(in, index)

	root.Left = tmpLeft
	root.Right = tmpRight


	return root
}


/*   Array,  from left to right, every 3 elements,  [root, left, right]
*/
func ConvertTreeToArray(root *TreeNode) []string{

	var res []string

	if root == nil {
		return []string{"null"}
	}

	res = append(res, strconv.Itoa(root.Val))

	var subLeft = ConvertTreeToArray(root.Left)
	var subRight = ConvertTreeToArray(root.Right)

	res = append(res, subLeft...)

	res = append(res, subRight...)

	return res

}


func MakeTreeNode(val int) *TreeNode {
	return &TreeNode {
		Val : val,
			Left : nil,
			Right : nil,
		}
}
