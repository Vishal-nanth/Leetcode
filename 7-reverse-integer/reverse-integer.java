class Solution {

    public int reverse(int x) {

        int rev = 0;

        while (x != 0) {

            // Extract last digit
            int digit = x % 10;

            /*
             * Overflow Check
             *
             * Integer.MAX_VALUE = 2147483647
             * Integer.MIN_VALUE = -2147483648
             */

            if (rev > Integer.MAX_VALUE / 10 ||
               (rev == Integer.MAX_VALUE / 10 && digit > 7)) {

                return 0;
            }

            if (rev < Integer.MIN_VALUE / 10 ||
               (rev == Integer.MIN_VALUE / 10 && digit < -8)) {

                return 0;
            }

            // Build reversed number
            rev = rev * 10 + digit;

            // Remove last digit
            x /= 10;
        }

        return rev;
    }
}