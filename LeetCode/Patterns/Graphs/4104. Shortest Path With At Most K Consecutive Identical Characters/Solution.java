
class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {

        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        // dist[node][last character][consecutive count]
        int[][][] dist = new int[n][26][k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[3], b[3])
        );

        int startChar = labels.charAt(0) - 'a';

        dist[0][startChar][1] = 0;
        pq.offer(new int[]{0, startChar, 1, 0});

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int node = curr[0];
            int lastChar = curr[1];
            int count = curr[2];
            int cost = curr[3];

            if (cost != dist[node][lastChar][count]) {
                continue;
            }

            if (node == n - 1) {
                return cost;
            }

            for (int[] edge : adj[node]) {

                int next = edge[0];
                int weight = edge[1];

                int ch = labels.charAt(next) - 'a';

                int newCount;

                if (ch == lastChar) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                if (newCount > k) {
                    continue;
                }

                int newCost = cost + weight;

                if (newCost < dist[next][ch][newCount]) {

                    dist[next][ch][newCount] = newCost;

                    pq.offer(new int[]{
                        next, ch, newCount, newCost
                    });
                }
            }
        }

        return -1;
    }
}
