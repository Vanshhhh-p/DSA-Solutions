class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> free = new TreeSet<>();
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int[] count = new int[k];

        // Initially all servers are free
        for (int i = 0; i < k; i++) {
            free.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            // Release completed servers
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                long[] server = busy.poll();
                free.add((int) server[1]);
            }

            // If all servers are busy, skip request
            if (free.isEmpty()) {
                continue;
            }

            // Find the next available server
            int id = i % k;
            Integer server = free.ceiling(id);

            // Wrap around if necessary
            if (server == null) {
                server = free.first();
            }

            // Assign request
            free.remove(server);
            count[server]++;

            busy.add(new long[]{
                (long) arrival[i] + load[i],
                server
            });
        }

        // Find maximum requests handled
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}
