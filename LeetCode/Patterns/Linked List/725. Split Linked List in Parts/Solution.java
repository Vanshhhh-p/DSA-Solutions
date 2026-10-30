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
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] ans = new ListNode[k];

        // find out length
        ListNode temp = head;
        int len = 0;
        while (temp != null) {
            len++;
            temp = temp.next;
        } 

        int baseSize = (len / k);
        int extraNode = (len % k);

        // Main logic
        ListNode prev = null;
        ListNode curr = head;

        for (int parts = 0; parts < k; parts++) {
            if (curr == null) {
                ans[parts] = curr;
                continue;
            }

            // insert curr into array
            ans[parts] = curr;

            // find width of the current sublist 
            int width = baseSize + (extraNode > 0 ? 1 : 0);

            // extraNode to be decrement
            extraNode--;

            for (int i = 1; i <= width; i++) {
                prev = curr;
                curr = curr.next;
            }
            prev.next = null;

            // for new iteration
            prev = null; 
        }
        return ans;
    }
}