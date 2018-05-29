/*
 * [23] Merge k Sorted Lists
 *
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (35.79%)
 * Total Accepted:    1.3K
 * Total Submissions: 3.6K
 * Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 
 * 示例:
 * 
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
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
class MergeKSortedLists023 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }

        ListNode a = lists[0];
        ListNode b = lists[1];

        ListNode[] rest = new ListNode[lists.length -1];

        rest[0] = merge2Lists(a, b);
        for (int i = 1; i < rest.length; i++) {
            rest[i] = lists[i + 1];
        }

        return mergeKLists(rest);
    }

    private ListNode merge2Lists(ListNode a, ListNode b) {
        ListNode head = null;
        ListNode current = null;
        while (a != null && b != null) {
            ListNode next = null;
            if (a.val <= b.val) {
                next = a;
                a = a.next;
            } else {
                next = b;
                b = b.next;
            }

            if (head == null) {
                head = next;
                current = next;
            } else {
                current.next = next;
                current = next;
            }
        }

        ListNode rest = null;

        if (a != null) {
            rest = a;
        } else if (b != null) {
            rest = b;
        }

        if (head == null) {
            head = rest;
        } else {
            current.next = rest;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(3);

        ListNode b = new ListNode(1);
        b.next = new ListNode(4);
        b.next.next = new ListNode(5);

        ListNode[] listNodes = {a, b};

        MergeKSortedLists023 solution = new MergeKSortedLists023();
        ListNode mergedListNode = solution.mergeKLists(listNodes);

        while (mergedListNode != null) {
            System.out.println(mergedListNode.val);
            mergedListNode = mergedListNode.next;
        }

    }

}
