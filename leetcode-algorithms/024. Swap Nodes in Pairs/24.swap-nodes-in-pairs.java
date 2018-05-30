/*
 * [24] Swap Nodes in Pairs
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
 *
 * algorithms
 * Medium (47.37%)
 * Total Accepted:    1.2K
 * Total Submissions: 2.6K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 
 * 示例:
 * 
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * 
 * 说明:
 * 
 * 
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
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
class SwapNodesInPairs024 {
    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode prevA = null;
        ListNode a = head;
        ListNode b = a.next;
        ListNode nextB = b.next;

        while (a != null && b != null) {
            swapNodeAB(prevA, a, b, nextB);
            if (prevA == null) {
                head = b;
            }
            prevA = a;
            a = nextB;
            if (a != null) {
                b = a.next;
                if (b != null) {
                    nextB = b.next;
                } else {
                    nextB = null;
                }
            } else {
                b = null;
                nextB = null;
            }
        }


        return head;
    }

    private void swapNodeAB(ListNode prevA, ListNode a, ListNode b, ListNode nextB) {
        if (prevA != null) {
            prevA.next = b;
        }
        b.next = a;
        a.next = nextB;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);

        SwapNodesInPairs024 solution = new SwapNodesInPairs024();
        listNode = solution.swapPairs(listNode);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
