class Solution {
    public void recursiveSubsets(int[] nums, List<List<Integer>> allSubsets, List<Integer> ans, int i) 
    {
        if (i == nums.length)
        {
            allSubsets.add(new ArrayList<>(ans));
            return;
        }
        ans.add(nums[i]);
        recursiveSubsets(nums, allSubsets, ans, i + 1);

        ans.remove(ans.size() - 1);
        int idx = i + 1;
        while (idx < nums.length && nums[idx] == nums[idx - 1])
        {
            idx++;
        }