
import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {

        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        if (k == 0) return -1;

        // Store only visited states
        HashMap<Long, Long> dist = new HashMap<>();

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[2], b[2])
        );

        // State: node, consecutive count, cost
        long key = (long) (k + 1) * 0 + 1;

        dist.put(key, 0L);
        pq.offer(new long[]{0, 1, 0});

        while (!pq.isEmpty()) {

            long[] curr = pq.poll();

            int node = (int) curr[0];
            int count = (int) curr[1];
            long cost = curr[2];

            long currKey = (long) node * (k + 1L) + count;

            if (dist.getOrDefault(currKey, Long.MAX_VALUE) != cost) {
                continue;
            }

            if (node == n - 1) {
                return (int) cost;
            }

            for (int[] edge : adj[node]) {

                int next = edge[0];
                int weight = edge[1];

                int newCount;

                if (labels.charAt(node) == labels.charAt(next)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                if (newCount > k) continue;

                long newCost = cost + weight;
                long newKey = (long) next * (k + 1L) + newCount;

                if (newCost < dist.getOrDefault(newKey, Long.MAX_VALUE)) {

                    dist.put(newKey, newCost);

                    pq.offer(new long[]{
                        next, newCount, newCost
                    });
                }
            }
        }

        return -1;
    }
}
