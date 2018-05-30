/*
 * [25] Reverse Nodes in k-Group
 *
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/description/
 *
 * algorithms
 * Hard (42.56%)
 * Total Accepted:    689
 * Total Submissions: 1.6K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 * 
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 * 
 * 示例 :
 * 
 * 给定这个链表：1->2->3->4->5
 * 
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 
 * 说明 :
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
class ReverseNodesInKGroup025 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null || head.next == null) {
            return head;
        }

        ListNode newHead = null;
        ListNode preStart = null;
        ListNode startNode = head;
        ListNode endNode;
        ListNode nextEnd;

        while (startNode != null) {
            endNode = findEndNode(k, startNode);

            if (startNode == endNode) {
                break;
            }

            nextEnd = endNode.next;

            ListNode headOfRange = swapNodesBetweenAB(preStart, startNode, endNode, nextEnd);
            if (newHead == null) {
                newHead = headOfRange;
            }

            preStart = startNode;
            startNode = nextEnd;
        }

        if (newHead == null) {
            // means there is no reverse at all
            newHead = head;
        }


        return newHead;
    }

    private ListNode findEndNode(int k, ListNode startNode) {
        int tmpCount = k;
        ListNode tmpNode = startNode;
        while (tmpCount > 1 && tmpNode.next != null) {
            tmpNode = tmpNode.next;
            tmpCount --;
        }

        // if the number of rest nodes is less than k, then stop.
        if (tmpCount == 1) {
            return tmpNode;
        } else {
            return startNode;
        }
    }

    private ListNode swapNodesBetweenAB(ListNode prevA, ListNode a, ListNode b, ListNode nextB) {
        if (prevA != null) {
            prevA.next = b;
        }

        ListNode cur = a;
        ListNode next = cur.next;

        while (next != nextB) {
            ListNode tmpCur = cur;
            ListNode tmpNext = next.next;
            next.next = tmpCur;
            cur = next;
            next = tmpNext;
        }
        a.next = nextB;

        return b;
    }

//    public static void main(String[] args) {
//        ListNode listNode = new ListNode(1);
//        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//
//        ReverseNodesInKGroup025 solution = new ReverseNodesInKGroup025();
//
//
////        ListNode head = solution.swapNodesBetweenAB( null, listNode, listNode.next.next, null);
//        ListNode head = solution.reverseKGroup(listNode, 2);
//        while (head != null) {
//            System.out.println(head.val);
//            head = head.next;
//        }
//    }
}
