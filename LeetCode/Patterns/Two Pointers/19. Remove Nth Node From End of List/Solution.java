
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;

        ListNode prev = head;
        ListNode curr = head;
        ListNode temp;

        // curr ko abhi k jumps karwane h
        for (int i = 1; i <= n; i++) {
            if (curr != null) curr = curr.next;
        }

        // If the head itself needs to be removed
        if (curr == null) {
            if (n == 1 && head.next == null) return null;

            temp = head;
            for (int i = 1; i < n; i++) {
                temp = temp.next;
            }

            if (temp == head) return head.next;
        }

        // first window is ready
        // now i can move my window
        temp = head;

        while (curr != null) {
            curr = curr.next;
            prev = prev.next;
        }

        if (prev == head) return head.next;

        while (temp.next != prev) temp = temp.next;

        temp.next = prev.next;
        prev.next = null;

        return head;
    }
}
