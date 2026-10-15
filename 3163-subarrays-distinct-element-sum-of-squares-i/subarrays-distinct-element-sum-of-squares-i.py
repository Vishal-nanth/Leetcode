class Solution:
    def sumCounts(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)

        # Step 1: every index i is a start of a subarray
        for i in range(n):
            # a NEW empty set for every start
            seen = set()

            # Step 2: grow the end j to the right
            for j in range(i, n):
                seen.add(nums[j])            # duplicates are ignored
                count = len(seen)            # distinct count of nums[i..j]
                ans += count * count         # add its square

        return ans