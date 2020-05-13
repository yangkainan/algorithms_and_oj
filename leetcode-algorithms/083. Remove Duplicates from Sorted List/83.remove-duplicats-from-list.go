package main

/*
给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:

输入: 1->1->2
输出: 1->2
示例 2:

输入: 1->1->2->3->3
输出: 1->2->3


*/


 type ListNode struct {
     Val int
     Next *ListNode
 }

 func deleteNextNode(cur *ListNode) {
 	if cur == nil || cur.Next == nil {
 		return
	}
	cur.Next = cur.Next.Next
 }

func deleteDuplicates(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}

	cur := head

	for cur != nil  && cur.Next != nil {
		if cur.Val == cur.Next.Val {
			deleteNextNode(cur)
		} else {
			cur = cur.Next
		}
	}

	deleteDuplicates(head.Next)

	return head
}

func main() {
	head := &ListNode{
		Val: 1,
		Next: &ListNode{
			Val: 1,
			Next: &ListNode{
				Val: 2,
				Next: nil,
			},
		},
	}

	deleteDuplicates(head)
}