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
    static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode forward = curr.next;

            curr.next = prev;
            prev = curr;
            curr = forward;
        }

        return prev;
    }
    public ListNode doubleIt(ListNode head) {
        // 1. Reverse LinkedList
        head = reverse(head);
        // 2. Do some maths
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        int carry = 0; 
        ListNode temp = head;
        while (temp != null) {
            int value = temp.val;
            int sum = value + value + carry;
            int digit = sum % 10;
            // add this node to dummy list
            curr.next = new ListNode(digit);
            curr = curr.next;

            carry =sum / 10;
            temp = temp.next;
        }

        // extra conditions
        if (temp == null && carry != 0) {
            curr.next = new ListNode(carry);
        }
        // 3. Reverse again
        // removing dummy node
        dummy = dummy.next;
        head = reverse(dummy);

        return head;
    }
}