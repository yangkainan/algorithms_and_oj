package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
type ListNode struct {
	Val  int
	Next *ListNode
}

func reverseBetween(head *ListNode, m int, n int) *ListNode {

	if m == n {
		return head
	}

	var  pre, cur, next *ListNode


	cur = head
	next = cur.Next

	var i = 1
	for i < m {
		pre = cur
		cur = next
		next = next.Next
		i = i + 1
	}

	var tailOfPrev = pre

	var subTail = cur

	pre = nil
	for i <= n {
		cur.Next = pre
		pre = cur
		cur = next
		if next != nil {
			next = next.Next
		}
		i = i + 1
	}

	if tailOfPrev != nil {
		tailOfPrev.Next = pre
	}

	subTail.Next = cur

	if m == 1 {
		return pre
	} else {
		return head
	}


}


func toArray(head *ListNode) []int{
	var res  []int

	for head != nil {
		res = append(res, head.Val)
		head = head.Next
	}

	return res

}

func fromArray(in []int) *ListNode {
	var head *ListNode
	var pre *ListNode

	for i := 0; i < len(in); i++ {
		cur := &ListNode{
			Val : in[i],
			Next : nil,
		}

		if head == nil {
			head = cur
			pre = cur
		} else {
			pre.Next = cur
			pre = cur
		}

	}

	return head
}

func main() {
	tests := map[string]struct {
		input struct {
			head []int
			m    int
			n    int
		}
		want []int
	}{
		"[1,2,3,4,5,], 2, 4": {input:
		struct {
			head []int
			m    int
			n    int
		}{head: []int{1,2,3,4,5},
			m: 2, n: 4},
			want: []int{1,4,3,2,5}},

		"[1,2,3,4,5,], 1, 1": {input:
		struct {
			head []int
			m    int
			n    int
		}{head: []int{1,2,3,4,5},
			m: 1, n: 1},
			want: []int{1,2,3,4,5}},
		"[1,2,3,4,5,], 1, 5": {input:
		struct {
			head []int
			m    int
			n    int
		}{head: []int{1,2,3,4,5},
			m: 1, n: 5},
			want: []int{5,4,3,2,1}},
	}

	for name, tc := range tests {
		var in = tc.input
		got := reverseBetween(fromArray(in.head), in.m, in.n)
		diff := cmp.Diff(tc.want, toArray(got))
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}
	}

}
