class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0, count2 = 0; 
        int candidate1 = 0, candidate2 = 0; 

        for (int i = 0; i < nums.length; i++) {
            if (count1 == 0 && nums[i] != candidate2) {
                count1 = 1;
                candidate1 = nums[i];
                System.out.println("after -> count1 = "+count1+"  candidate1 = "+candidate1);
            } 
            
            else if (count2 == 0 && nums[i] != candid