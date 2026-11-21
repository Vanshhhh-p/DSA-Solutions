class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // Build original tree
        int[] head = new int[n];
        int[] next = new int[n];

        java.util.Arrays.fill(head, -1);

        for (int i = 1; i < n; i++) {
            next[i] = head[parent[i]];
            head[parent[i]] = i;
        }

        // Find updated parents using DFS
        int[] newParent = new int[n];
        int[] last = new int[26];
        int[] oldLast = new int[n];

        java.util.Arrays.fill(last, -1);
        newParent[0] = -1;

        int[] stack = new int[2 * n];
        int top = 0;

        stack[top++] = 0;

        while (top > 0) {
            int v = stack[--top];

            // Exit DFS node
            if (v < 0) {
                int node = -v - 1;
                int c = s.charAt(node) - 'a';
                last[c] = oldLast[node];
                continue;
            }

            // Enter DFS node
            int c = s.charAt(v) - 'a';

            oldLast[v] = last[c];

            if (v != 0) {
                newParent[v] = (last[c] != -1)
                               ? last[c]
                               : parent[v];
            }

            last[c] = v;

            stack[top++] = -v - 1;

            for (int child = head[v]; child != -1; child = next[child]) {
                stack[top++] = child;
            }
        }

        // Build modified tree and calculate subtree sizes
        java.util.Arrays.fill(head, -1);

        for (int i = 1; i < n; i++) {
            next[i] = head[newParent[i]];
            head[newParent[i]] = i;
        }

        int[] order = new int[n];
        top = 0;
        int size = 0;

        order[size++] = 0;

        while (top < size) {
            int node = order[top++];

            for (int child = head[node];
                 child != -1;
                 child = next[child]) {

                order[size++] = child;
            }
        }

        int[] ans = new int[n];
        java.util.Arrays.fill(ans, 1);

        // Process children before parents
        for (int i = n - 1; i > 0; i--) {
            int node = order[i];
            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}
