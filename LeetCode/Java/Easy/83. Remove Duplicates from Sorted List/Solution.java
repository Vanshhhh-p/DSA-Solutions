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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = head;
        ListNode curr = head.next;
        if (head == null || head.next == null) return head;
        while (curr != null) {
            if (prev.val != curr.val) {
                prev = prev.next;
                curr= curr.next;
            }
            else {
                prev.next = curr.next;
                curr = curr.next;
            }
        }
        return head;
    }
}