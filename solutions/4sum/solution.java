class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, (long) target, 4, 0);
    }

    
    private ArrayList<List<Integer>> kSum(int[] nums, long target, int k, int index){
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = nums.length;
        if(index >= len){
            return res;
        }

        if(k == 2){
            int i = index, j = len - 1;
            while(i <