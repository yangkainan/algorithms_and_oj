package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
	"strconv"
	"strings"
)

/**
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5

 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
type ListNode struct {
	Val int
	Next *ListNode
}
func partition(head *ListNode, x int) *ListNode {
	if head == nil {
		return head
	}

	// make a new list.  make sure all elements less than sentinel are moved to new list.

	var prev, cur, newHead, newTail, originHead *ListNode
	prev = nil
	cur = head


	for cur != nil {

		if cur.Val < x {

			// append cur onto new list
			if newHead == nil {
				newHead = cur
			} else {
				newTail.Next = cur
			}
			newTail = cur


			// remove cur from origin list
			if prev != nil {
				prev.Next = cur.Next
			}
			var tmp = cur.Next
			cur.Next = nil
			cur = tmp

		} else {
			if originHead == nil {
				originHead = cur
			}

			prev = cur
			cur = cur.Next
		}

	}

	// append original list onto tail of new list.
	if newHead != nil  {
		newTail.Next = originHead
	} else {
		newHead = head
	}

	return newHead

}

func printList(head *ListNode) string {
	var result = ""
	for head != nil {
		result += fmt.Sprintf("%d->", head.Val)
		head = head.Next
	}
	return result
}

func constructList(str string) *ListNode {
	var strNodeList = strings.Split(str, "->")

	var head, tail *ListNode

	for _, str := range strNodeList {
		tmpVal, _ := strconv.Atoi(str)
		var tmp = &ListNode{
			Val: tmpVal,
			Next: nil,
		}

		if head == nil {
			head = tmp
		} else {
			tail.Next = tmp
		}

		tail = tmp

	}

	return head
}

func main() {
	tests := map[string]struct {
		input struct{
			list string
			x int
		}
		want string
	}{
		//"1->4->3->2->5->2, x=3":       {input: struct {
		//	list string
		//	x    int
		//}{list: "1->4->3->2->5->2", x: 3}, want: "1->2->2->4->3->5->"},
		"5->-5->0->-8->3->8->-8->0->-1->3->-8->7->-4->-8->2->5->9->4->-5->-6, -4":       {input: struct {
			list string
			x    int
		}{list: "5->-5->0->-8->3->8->-8->0->-1->3->-8->7->-4->-8->2->5->9->4->-5->-6", x: -4}, want: "1->2->2->4->3->5->"},
	}

	for name, tc := range tests {
		got := partition(constructList(tc.input.list), tc.input.x)
		diff := cmp.Diff(tc.want, printList(got))
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, printList(got), diff)
		}

	}

}
