/*
 * @lc app=leetcode.cn id=86 lang=golang
 *
 输入: head = 1->4->3->2->5->2, x = 3
输出: 1->2->2->4->3->5
 * [86] 分隔链表
*/
package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

type ListNode struct {
	Val  int
	Next *ListNode
}

// @lc code=start
func partition(head *ListNode, x int) *ListNode {
	if x <= 0 || head == nil {
		return head
	}
	var pivot = head

	for i:= 1; i < x; i ++ {
		if pivot.Next == nil {
			return head
		}
		pivot = pivot.Next
	}

	var smallHalfHead, smallHalfTail, bigHalfHead, bigHalfTail *ListNode

	var cur = head

	for cur != nil {
		if cur.Val < pivot.Val {
			if smallHalfHead == nil {
				smallHalfHead = cur
				smallHalfTail = cur
			} else {
				smallHalfTail.Next = cur
				smallHalfTail = cur
			}

		} else {

			if bigHalfHead == nil {
				bigHalfHead = cur
				bigHalfTail = cur
			} else {
				bigHalfTail.Next = cur
				bigHalfTail = cur
			}
		}
		cur, cur.Next = cur.Next, nil
	}

	if smallHalfHead == nil {
		return bigHalfHead
	} else {
		smallHalfTail.Next = bigHalfHead
	}


	return smallHalfHead
}

// @lc code=end

func main() {

	firstCase := &ListNode{
		Val: 1,
		Next: &ListNode{
			Val: 4,
			Next: &ListNode{
				Val: 3,
				Next: &ListNode{
					Val: 2,
					Next: &ListNode{
						Val: 5,
						Next: &ListNode{
							Val: 2,
							Next: nil,
						},
					},
				},
			},
		},
	}

	secondCase := &ListNode{
		Val: 2,
		Next: &ListNode{
			Val: 1,
			Next: nil,
		},
	}

	tests := map[string]struct {
		input struct{
			head *ListNode
			x int
		}
		want  *ListNode
	}{
		"1->4->3->2->5->2, x=3":       {input: struct {
			head *ListNode
			x    int
		}{head: firstCase, x: 3}, want: firstCase},
		"2->1, x=1": {
			input: struct {
				head *ListNode
				x    int
			}{head: secondCase, x: 1}, want: secondCase,
		},
	}

	for name, tc := range tests {
		got := partition(tc.input.head, tc.input.x)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
