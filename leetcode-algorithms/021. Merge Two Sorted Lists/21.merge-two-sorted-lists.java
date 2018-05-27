/*
 * [21] Merge Two Sorted Lists
 *
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
 *
 * algorithms
 * Easy (47.65%)
 * Total Accepted:    5K
 * Total Submissions: 10.4K
 * Testcase Example:  '[1,2,4]\n[1,3,4]'
 *
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 
 * Example:
 * 
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 * 
 * 
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class MergeTwoSortedList021 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode curr= null;
        ListNode first= l1;
        ListNode second = l2;
        while (first != null && second != null) {
            ListNode next = null;
            if (first.val <= second.val) {
                next = first;
                first = first.next;
            } else {
                next = second;
                second = second.next;
            }
            if (head == null) {
                head = next;
                curr = next;
            } else {
                curr.next = next;
                curr = next;
            }
        }

        ListNode rest = null;
        if (first != null) {
            rest = first;
        }else if (second != null) {
            rest = second;
        }
        if (rest != null) {
            if (head == null) {
                head = rest;
            } else {
                curr.next = rest;
            }
        }
        
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 =new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 =new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        MergeTwoSortedList021 solution = new MergeTwoSortedList021();
        ListNode mergeTwoLists = solution.mergeTwoLists(l1, l2);

        while(mergeTwoLists != null) {
            System.out.println(mergeTwoLists.val);
            mergeTwoLists =mergeTwoLists.next;
        }

    }
}
