/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    private ListNode appendOneNodeOntoList(ListNode head, int val) {
        ListNode newNode = new ListNode(val % 10);
        if (head == null){
            head = newNode;
        } else {
            ListNode tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = newNode;
        }
        
        return head;
    }
    
    private ListNode append2ndListOnto1stList(ListNode firstListhead,ListNode secondListHead, int extra) {
        while(secondListHead!= null) {
            int sum = secondListHead.val + extra;
            extra = sum/10;
            firstListhead = appendOneNodeOntoList(firstListhead,sum);
            secondListHead= secondListHead.next;
        }
        if (extra > 0) {
            firstListhead = appendOneNodeOntoList(firstListhead,extra);
        }
        return firstListhead;
        
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int extra = 0;
        
        ListNode firstNode = l1;
        ListNode secondNode = l2;
        
        ListNode resultHead = null;
        while (firstNode != null && secondNode != null) {
            int sum = firstNode.val + secondNode.val + extra;
            extra = sum/10;
            
            resultHead = appendOneNodeOntoList(resultHead, sum);
            
            firstNode = firstNode.next;
            secondNode = secondNode.next;
            
        }
        ListNode restListHead= null;
        if (firstNode != null) {
            // in case firstNode is longer;
            restListHead = firstNode;
        } 
        if (secondNode != null) {
            // in case secondNode is longer;
            restListHead = secondNode;
        }
        resultHead = append2ndListOnto1stList(resultHead,restListHead, extra);
        
        
        return resultHead;

    }
        
        
}
