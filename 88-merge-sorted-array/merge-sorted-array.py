class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        nums11=nums1[:m]
        nums21=nums2[:n]
        
        
        res=nums11+nums21
        res.sort()
        nums1[:]=res
        

