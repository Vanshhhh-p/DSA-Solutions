class Solution {
    static ListNode reverseList(ListNode head) {
        // Base case
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }

    static ListNode getMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode middle = getMiddleNode(head);

        // Reverse the second half
        ListNode secondHalf = reverseList(middle);

        ListNode first = head;
        ListNode second = secondHalf;

        while (second != null) {
            if (first.val != second.val) {
                return false;
            }

            first = first.next;
            second = second.next;
        }

        return true;
    }
}
