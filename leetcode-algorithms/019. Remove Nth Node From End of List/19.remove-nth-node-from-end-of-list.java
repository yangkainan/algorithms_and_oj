/*
 * [19] Remove Nth Node From End of List
 *
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
 *
 * algorithms
 * Medium (28.21%)
 * Total Accepted:    3.6K
 * Total Submissions: 12.7K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * Given a linked list, remove the n-th node from the end of list and return
 * its head.
 * 
 * Example:
 * 
 * 
 * Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes
 * 1->2->3->5.
 * 
 * 
 * Note:
 * 
 * Given n will always be valid.
 * 
 * Follow up:
 * 
 * Could you do this in one pass?
 * 
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }

class RemovalNthNodeFromEndOfList019 {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        List<ListNode> array = new ArrayList<>();

        ListNode current = head;
        while (current != null){
            array.add(current);
            current = current.next;
        }

        if (n == 0 || n > array.size()) {
            return head;
        }


        if (n < 0) {
            throw new IllegalArgumentException();
        }

        int toDeleteIndex = array.size() - n;

        int preIndex = toDeleteIndex - 1;
        int nextIndex = toDeleteIndex + 1;

        if (preIndex == -1) {
            return head.next;
        }

        if (nextIndex == array.size()) {
            array.get(preIndex).next = null;
        } else {
            array.get(preIndex).next = array.get(nextIndex);
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        RemovalNthNodeFromEndOfList019 solution = new RemovalNthNodeFromEndOfList019();
        int n = 1;
        ListNode listNode = solution.removeNthFromEnd(head, n);

        while(listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }
}
