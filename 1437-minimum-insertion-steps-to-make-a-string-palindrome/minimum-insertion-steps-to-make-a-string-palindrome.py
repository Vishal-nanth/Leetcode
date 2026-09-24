class Solution:
    def minInsertions(self, s: str) -> int:
        n = len(s)

        dp = [[0] * (n + 1) for _ in range(n + 1)]

        for i in range(n):
            dp[i][i] = 1

        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    if j == i + 1:
                        dp[i][j] = 2
                    else:
                        dp[i][j] = 2 + dp[i + 1][j - 1]
                else:
                    dp[i][j] = max(dp[i][j - 1], dp[i + 1][j])

        return n - dp[0][n - 1]