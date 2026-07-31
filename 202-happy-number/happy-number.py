class Solution:
    def isHappy(self, n: int) -> bool:
        if n==1 or n==7:
            return True
        elif n>=2 and n<=9:
            return False
        else:
            sum=0
            while n!=0:
                rem=0
                rem=n%10
                n=n//10

                sum+=rem*rem
            return self.isHappy(sum)
        
        


        
           




        