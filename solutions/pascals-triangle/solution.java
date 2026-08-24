class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            long val = 1; 
            for (int j = 0; j <= i; j++) {
                row.add((int) val);
                val = val * (i - j) / (j + 1);
            }
            triangle.add(row);
        }

        return triangle;
    }
}