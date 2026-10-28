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
    public ListNode partition(ListNode head, int x) {

        if (head == null || head.next == null) return head;
        ListNode lesserHead = new ListNode(-1);
        ListNode lesserTail = lesserHead;

        ListNode greaterHead = new ListNode(-1);
        ListNode greaterTail = greaterHead;

        ListNode temp = head;

        while (temp != null) {
            if (temp.val < x) {
                // Separate this node and append to the lesser list
                // also before doing this temp points to the head of remaining lists
                ListNode nodeToInsert = temp;
                temp = temp.next;
                nodeToInsert.next = null;

                // insert at tail 
                lesserTail.next = nodeToInsert;
                lesserTail = nodeToInsert;
            }else {
                ListNode nodeToInsert = temp;
                temp = temp.next;
                nodeToInsert.next = null;

                // insert at tail 
                greaterTail.next = nodeToInsert;
                greaterTail = nodeToInsert;
            }
        }
        // Join both lists
        lesserTail.next = greaterHead.next;
        greaterTail.next = null;
        lesserHead = lesserHead.next;

        return lesserHead;
    }
}