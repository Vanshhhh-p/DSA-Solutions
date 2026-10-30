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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode temp = head;
        int len = 0;

        while (temp != null) {
            len++;
            temp = temp.next;
        }

        if (len < k) {
            return head;
        }

        // first k len k groups ka reverse krna h
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 1; i <= k; i++) {
            ListNode forward = curr.next;
            curr.next = prev;

            prev = curr;
            curr = forward;
        }

        // Remaining list should solved through recursion
        ListNode recursiveAnsHead = reverseKGroup(curr , k);
        

        // Join both list;
        head.next = recursiveAnsHead;
        return prev;
    }
}