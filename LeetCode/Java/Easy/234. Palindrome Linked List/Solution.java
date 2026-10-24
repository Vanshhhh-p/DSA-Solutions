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
    static ListNode reverseList(ListNode head) {
        // Base case
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively reverse the remaining list
        ListNode newHead = reverseList(head.next);

        // Reverse the link
        head.next.next = head;
        head.next = null;

        return newHead;
    }
    static ListNode getMiddleNode (ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode head2 = getMiddleNode(head);
        ListNode temp1 = head;
        while (temp1 .next != head2) temp1 = temp1.next;
        temp1.next = null;
        reverseList(head2);

        temp1 = head;
        ListNode temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val != temp2.val) return false;
            else {
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
        }
        return true;
    }
}