class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        val=list(set(nums))
        val.sort()
        nums[:]=val
        
        