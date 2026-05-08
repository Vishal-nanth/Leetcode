class Solution {

    public int minJumps(int[] nums) {

        int n = nums.length;

        // Find maximum value in nums
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        // Smallest Prime Factor (SPF) sieve
        int[] spf = new int[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            spf[i] = i;
        }

        for (int i = 2; i * i <= maxVal; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= maxVal; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }

        // Map prime factor -> indices divisible by it
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int val = nums[i];
            Set<Integer> factors = new HashSet<>();

            while (val > 1) {
                int p = spf[val];
                factors.add(p);

                while (val % p == 0) {
                    val /= p;
                }
            }

            for (int p : factors) {
                map.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }

        // BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        queue.offer(0);
        visited[0] = true;

        int steps = 0;

        // To avoid reusing same prime teleport many times
        Set<Integer> usedPrime = new HashSet<>();

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int s = 0; s < size; s++) {

                int i = queue.poll();

                // Reached destination
                if (i == n - 1) {
                    return steps;
                }

                // Move left
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    queue.offer(i - 1);
                }

                // Move right
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }

                // Prime teleportation
                int val = nums[i];

                if (isPrime(val, spf) && !usedPrime.contains(val)) {

                    usedPrime.add(val);

                    List<Integer> next = map.getOrDefault(val, new ArrayList<>());

                    for (int idx : next) {
                        if (!visited[idx]) {
                            visited[idx] = true;
                            queue.offer(idx);
                        }
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    private boolean isPrime(int x, int[] spf) {
        return x >= 2 && spf[x] == x;
    }
}