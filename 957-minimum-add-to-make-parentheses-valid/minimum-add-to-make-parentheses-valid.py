class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        while True:
            pos = s.find("()")

            if pos == -1:
                return len(s)

            s = s[:pos] + s[pos + 2:]