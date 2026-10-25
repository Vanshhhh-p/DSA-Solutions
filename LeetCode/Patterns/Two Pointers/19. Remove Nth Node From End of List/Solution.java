/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {   
        if (head == null || head.next == null) return null;
        ListNode prev = head;
        ListNode curr = head;
        ListNode temp;
        
        // curr ko abhi k jumps karwane h 
        for (int i = 1; i <= n; i++) {
            if (curr != null) curr = curr.next;
        }
        
        // first window is ready
        // now i can move my window
        temp = head;
        while (curr != null) {
            curr = curr.next;
            prev = prev.next;
        }
        while (temp.next != prev) temp = temp.next;

        temp.next = prev.next;
        prev.next = null; 
        return head;
    }
}