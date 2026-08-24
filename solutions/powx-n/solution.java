class Solution {
    public double myPow(double x, int n) {
        long N = n; 
        
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        double result = 1.0;
        double currentProduct = x;
        
        while (N > 0) {
            // If the current power is odd, multiply the result by the current product
            if (N % 2 == 1) {
                result *= currentProduct;
            }
            // Square the base and halve the power