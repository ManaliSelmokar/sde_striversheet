class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int HashLen = 256;
        int[] hash = new int[HashLen];
        Arrays.fill(hash, -1);
        int l = 0, r = 0, maxLen = 0;
        while (r < n) {
            /* If current character s.charAt(r) is already in the substring */
            if (hash[s.charAt(r)] >= l) {
                /* Move left pointer to the right of the last occurrence of s.charAt(r) */
                l = Math.max(has