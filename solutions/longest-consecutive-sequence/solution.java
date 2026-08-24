class Solution {
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int cnt=1, ans=1;
        for(int i=0;i<nums.length-1;i++) {
            if(nums[i]==nums[i+1]) continue;
            if(nums[i]==nums[i+1]-1) {
                cnt++;
                ans=Math.max(cnt, ans);
            } else cnt=1;
        }
        if (nums.length <= 1) {
            return nums.length;
        } else {
            return ans;
        } 
    }
}