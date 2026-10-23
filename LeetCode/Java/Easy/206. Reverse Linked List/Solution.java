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
    static ListNode solve (ListNode prev , ListNode curr) {
        // Base case 
        if (curr == null) return prev;

        // 1 case main karega , baaki recursion sambhal lega
        ListNode forward = curr.next;
        curr.next = prev;

        // move prev and curr one step ahead
        prev = curr;
        curr = forward;

        // recursion call
        ListNode ans = solve(prev , curr);
        return ans;
    }
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode ans = solve (prev , curr);
        return ans;
    }
}