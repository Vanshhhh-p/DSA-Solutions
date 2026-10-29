
import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] tin, tout, depth, parent;
    private int[] bit;
    private int timer;
    private Fenwick fenwick;

    class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int i, int val) {
            while (i <= n) {
                tree[i] ^= val;
                i += i & -i;
            }
        }

        int query(int i) {
            int res = 0;
            while (i > 0) {
                res ^= tree[i];
                i -= i & -i;
            }
            return res;
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        bit = new int[n];
        timer = 0;

        int LOG = 17;
        while ((1 << LOG) <= n) LOG++;

        int[][] up = new int[LOG][n];

        // Iterative DFS to avoid stack overflow
        int[] stack = new int[2 * n + 5];
        int[] iter = new int[n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int v = stack[top - 1];

            if (iter[v] == 0) {
                tin[v] = ++timer;
                bit[v] = 1 << (s.charAt(v) - 'a');
                up[0][v] = parent[v];

                for (int j = 1; j < LOG; j++) {
                    up[j][v] = up[j - 1][up[j - 1][v]];
                }
            }

            if (iter[v] < adj[v].size()) {
                int u = adj[v].get(iter[v]++);

                if (u == parent[v]) continue;

                parent[u] = v;
                depth[u] = depth[v] + 1;
                stack[top++] = u;
            } else {
                tout[v] = timer;
                top--;
            }
        }

        fenwick = new Fenwick(n);

        for (int v = 0; v < n; v++) {
            fenwick.update(tin[v], bit[v]);
            fenwick.update(tout[v] + 1, bit[v]);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                int newBit = 1 << (parts[2].charAt(0) - 'a');

                int change = bit[u] ^ newBit;

                fenwick.update(tin[u], change);
                fenwick.update(tout[u] + 1, change);

                bit[u] = newBit;
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v, up, LOG);

                int mask = prefix(u) ^ prefix(v) ^ bit[lca];

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private int prefix(int u) {
        return fenwick.query(tin[u]);
    }

    private int getLCA(int u, int v, int[][] up, int LOG) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }
}
