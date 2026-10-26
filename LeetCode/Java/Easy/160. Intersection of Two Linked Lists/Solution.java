/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        while (a != null && b != null) {
            a = a.next;
            b = b.next;
        }

        if (a == null) {
            // Either b's length is bigger or equal to a
            int bExtraLength = 0;
            while (b != null) {
                bExtraLength++;
                b = b.next;
            }
            while (bExtraLength-- > 0) headB = headB.next;
        }
        else {
            // Either a's length is bigger or equal to b
            int aExtraLength = 0;
            while (a != null) {
                aExtraLength++;
                a = a.next;
            }
            while (aExtraLength-- > 0) headA = headA.next;
        }

        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            else {
                headA = headA.next;
                headB = headB.next;
            }
        }
        return null;
    }
}