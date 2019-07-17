package main

import (
	"reflect"
	"testing"
)


func TestFindSibling(t *testing.T) {

	t0 := &TreeNode{
		Value:0,
	}

	t1 := &TreeNode{
		Value:1,
	}
	t2 := &TreeNode{
		Value:2,
	}

	t3 := &TreeNode{
		Value:3,
	}
	t5 := &TreeNode{
		Value:5,
	}
	t6 := &TreeNode{
		Value:6,
	}
	t7 := &TreeNode{
		Value:7,
	}
	t8 := &TreeNode{
		Value:8,
	}
	t9 := &TreeNode{
		Value:9,
	}

	t0.left = t1
	t0.right = t2

	t1.left = t3
	t2.left = t5
	t2.right = t6

	t3.left = t7
	t6.left  = t8
	t6.right = t9


	tests := []struct {
		name string
		root *TreeNode
		target *TreeNode
		want *TreeNode
	}{
		{
			name : "Find Sibling of 3",
			root : t0,
			target : t3,
			want : t5,
		},
		{
			name : "Find Sibling of 7",
			root : t0,
			target : t7,
			want : t8,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := FindSibling(tt.root, tt.root, tt.target); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("root = %v, target = %v, want= %v, got = %v", tt.root, tt.target, tt.want, got)
			}
		})
	}
}

