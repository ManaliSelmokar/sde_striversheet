<String>> ans = new ArrayList<>();
        List<StringBuilder> board = new ArrayList<>();
        for (int i = 0; i < n; i++)
        {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++)
            {
                row.append('.');
            }
            board.add(row);
        }
        nQueens(ans, board, 0, n);
        return ans;
    }
}