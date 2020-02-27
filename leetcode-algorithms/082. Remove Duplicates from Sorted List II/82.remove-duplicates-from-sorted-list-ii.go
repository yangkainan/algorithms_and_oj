/*
给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:

输入: 1->2->3->3->4->4->5
输出: 1->2->5


示例 2:

输入: 1->1->1->2->3
输出: 2->3
*/
package main
import (
    "fmt"
    "github.com/google/go-cmp/cmp"
)

 type ListNode struct {
     Val int
     Next *ListNode
 }

func deleteDuplicates(head *ListNode) *ListNode {

	if head == nil {
	    return nil
    }

    valCounter := map[int]int{}

    curNode := head

    for curNode != nil {
        key := curNode.Val
        if val, ok := valCounter[key]; ok {
            valCounter[key] = val + 1
        } else {
            valCounter[key] = 1
        }
        curNode = curNode.Next
    }

    curNode = head

    for curNode != nil {
        nextNode := curNode.Next

        if nextNode != nil {
            key := nextNode.Val

            if cnt, ok := valCounter[key]; ok && (cnt > 1) {
                curNode.Next = nextNode.Next
                continue
            }
        }
        curNode = curNode.Next
    }


    if cnt, ok := valCounter[head.Val]; ok && (cnt > 1) {
    	head = head.Next
    }


    return head

}
func  parse(input []int) *ListNode {
	var head *ListNode
    var preNode *ListNode

    for i:= 0; i< len(input) ; i++  {

        curNode := &ListNode{
            Val:  input[i],
            Next: nil,
        }

        if head == nil {
            head = curNode
        }

        if preNode == nil {
            preNode = curNode
        } else {
            preNode.Next = curNode
            preNode = curNode
        }

    }


    return head
}
func encode(input *ListNode) []int{
    var res []int

	for input != nil {
	    res = append(res, input.Val)
	    input = input.Next
    }

    return res
}
func main() {


    tests := map[string]struct {
        input []int
        want  []int
    }{
        "1->1->2":       {input: []int{1,1,2}, want:[]int{2} },
        "1->2->3->3->4->4->5":       {input: []int{1,2,3,3,4,4,5}, want:[]int{1,2,5} },
        "1->1":       {input: []int{1,1}, want:[]int{} },
    }

    for name, tc := range tests {
        in := parse(tc.input)
        res := deleteDuplicates(in)
        got := encode(res)

        diff := cmp.Diff(tc.want, got)
        if diff != "" {
            fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
                name, tc.input, tc.want, got, diff)
        }

    }

}