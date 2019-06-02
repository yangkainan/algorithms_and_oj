package main

type ListNode struct {
	Val  int
	Next *ListNode
}

func rotateRight(head *ListNode, k int) *ListNode {
	var length = findLength(head)
	if length <= 1 {
		return head
	}

	for i := 1; i <= (k % length); i++ {
		head = rotateRightOneNode(head)
	}

	return head
}

func findLength(head *ListNode) int {
	if head == nil {
		return 0
	}

	var cnt = 1

	for head.Next != nil {
		cnt++
		head = head.Next
	}

	return cnt
}

func rotateRightOneNode(head *ListNode) *ListNode {
	var prevTail = findPrevOfTail(head)

	if prevTail == nil {
		return head
	}

	var tail = prevTail.Next

	tail.Next = head
	prevTail.Next = nil

	return tail

}

func findPrevOfTail(head *ListNode) *ListNode {

	if head == nil || head.Next == nil {
		return nil
	}
	var prevTail = head

	for prevTail.Next != nil && prevTail.Next.Next != nil {
		prevTail = prevTail.Next
	}
	return prevTail

}

func main() {

}
