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
        int count = 0;
        ListtNode temp = head;
    
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        if (k > count) return -1;
        ListNode prev = head;
        ListNode curr = head;
        
        // curr ko abhi k jumps karwane h 
        for (int i = 1; i <= k; i++) {
            if (curr != null) curr = curr.next;
        }
        
        // first window is ready
        // now i can move my window
        while (curr != null) {
            curr = curr.next;
            prev = prev.next;
        }
        return prev.val;
    }
}