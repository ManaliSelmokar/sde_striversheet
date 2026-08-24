class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0],b[0])); // sorting acc to the starting pts
        List<int[]> merged = new ArrayList<>();
        int[] prev = intervals[0];

        for(int i=1;i<intervals.length;i++){
            int[] current = intervals[i];
            if(current[0]<=prev[1]){
                //we are comparing start of current with end of previous
                prev[1] = Math.max(prev[1],current[1])