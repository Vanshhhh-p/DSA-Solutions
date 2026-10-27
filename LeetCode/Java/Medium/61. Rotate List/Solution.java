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
    public ListNode rotateRight(ListNode head, int k) {
        // Make list circular
        if (head == null || k == 0) return head;

        int len = 1;
        ListNode temp = head;
        while (temp.next != null) {
            len++;
            temp = temp.next;
        }

        temp.next = head;

        // Updating k
        k = k % len;

        // breaking the list and set up the forward variable
        temp = head;
        for (int i = 1; i <= len - k - 1; i++) {
            temp = temp.next;
        }

        ListNode forward = temp.next;
        temp.next = null;

        return forward;

    }
}