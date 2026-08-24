class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int top=0;
        int bottom=n-1;

        //Reverse
        while(top<bottom){
            for(int col=0;col<n;col++){
                int temp = matrix[top][col];
                matrix[top][col] = matrix[bottom][col];
                matrix[bottom][col] = temp;
            }
            top++;
            bottom--;
        }

        //Transpose
        for(int row=0;row<n;row++){
            for