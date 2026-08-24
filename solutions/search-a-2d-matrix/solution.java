class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for(int i=0;i<matrix.length;i++){
            boolean b = searchInARow(matrix[i],target);
            if(b==true){
                return true;
            }
        }
        return false;
    }

    private boolean searchInARow(int[] matrix, int target){
        int start=0,end=matrix.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(matrix[mid]==target){