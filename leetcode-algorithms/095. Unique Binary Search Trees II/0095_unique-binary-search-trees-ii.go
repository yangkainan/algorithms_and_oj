package main

import (
	"github.com/google/go-cmp/cmp"
	"fmt"
)

/*

Input: 3
Output:
[
  [1,null,2,null,3]
  [1,null,3,2],
  [3,2,1,null],
  [3,1,null,2],
  [2,1,3],
]

*/


func generateTrees(n int) []*TreeNode {
	if n <= 0 || n > 8 {
		return []*TreeNode{}
	}

	var in []int
	for i := 1; i <= n; i++ {
		in = append(in, i)
	}


	return generateTreesRecursive(in)
}

/*  DP: 如何划分问题成一个更小的范围.*/
/*  1. 选取, 每个元素做为 二叉查找树的根, 将剩余的元素做为子问题.*/
/*  2. 子问题的解求出来后, 要如何与根合并?*/
/*  	这个子问题不行, 不能合并.*/
/*  3. 修改划分子问题的方式为, 将所有小于根的元素划分一组, 所有大于等根的元素划分一组.*/
/*  	第一组的结果, 做为根的左节点, 第二组的结果做为根的右节点.*/
func generateTreesRecursive(in []int) []*TreeNode {

	if len(in) == 0 {
		return []*TreeNode{}
	}

	if len(in) == 1 {
		var n = MakeTreeNode(in[0])
		return []*TreeNode{n}
	}


	var res []*TreeNode

	for i := 0; i < len(in); i++ {

		var leftSub = generateTreesRecursive(in[:i])
		var rightSub = generateTreesRecursive(in[(i+1):])


		if len(leftSub) == 0 {
			for _, r := range rightSub {
				var root = MakeTreeNode(in[i])
				root.Right = r
				res = append(res, root)
			}

		} else if len(rightSub) == 0 {

			for _, l := range leftSub {
				var root = MakeTreeNode(in[i])
				root.Left = l
				res = append(res, root)
			}

		} else {
			for _, l := range leftSub {
				for _, r := range rightSub {
					var root = MakeTreeNode(in[i])
					root.Left = l
					root.Right = r
					res = append(res, root)
				}
			}
		}

	}

	return res

}


func main() {
	tests := map[string]struct {
		input int
		want  [][]string
	}{
		"3":       {input: 3, want: [][]string{
			{"1", "null", "2", "null", "3", "null", "null"},
			{"1", "null", "3", "2", "null","null","null"},
			{"3", "2", "1", "null", "null", "null", "null"},
			{"3", "1", "null", "2", "null", "null", "null"},
			{"2", "1", "null","null", "3", "null", "null"},
		}},
	}

	for name, tc := range tests {
		res := generateTrees(tc.input)

		var got [][]string

		for _, value := range res {
			got = append(got, ConvertTreeToArray(value))
		}

		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
