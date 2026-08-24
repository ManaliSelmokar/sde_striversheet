class Solution {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int low, int high) {
        if (low >= high) return 0;
        
        int mid = low + (high - low) / 2;
        int count = 0;
        
        // Count pairs in left and right halves
        count += mergeSort(nums, low, mid);
        count += mergeSort(nums, mid + 1, high);
        
        // Count reverse pairs across the two halves