import java.util.ArrayList;

/*
 * [52] N-Queens II
 *
 * https://leetcode-cn.com/problems/n-queens-ii/description/
 *
 * algorithms
 * Hard (66.43%)
 * Total Accepted:    1.1K
 * Total Submissions: 1.6K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 
 * 
 * 上图为 8 皇后问题的一种解法。
 * 
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * 
 * 示例:
 * 
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * 
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 
 * 
 */
class NQueensII052 {
    class Queen {
        int row;
        int col;

        public Queen(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Queen{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
        Queen prevRowQueen;
    }



    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }

        int[][] board = initBoard(n);
        int currentRow = 0;
        ArrayList<Integer> totalSolver = new ArrayList();
        recursiveNQueens(board,currentRow, n, totalSolver);
        return totalSolver.size();
    }

    int[][] initBoard(int totalQueens) {
        int[][] board = new int[totalQueens][totalQueens];
        return board;
    }

    void recursiveNQueens(int[][] board, int currentRow, int totalRow, ArrayList<Integer> totalSolver) {
        if (currentRow == totalRow) {
            totalSolver.add(1);
            return;
        }

        for (int currentColumn = 0; currentColumn < totalRow; currentColumn++) {
            if (isValidPosition(board, currentRow, currentColumn)) {
                placeQueenInPosition(board, currentRow, currentColumn);
                recursiveNQueens(board, currentRow + 1, totalRow, totalSolver);
                removeQueenFromPosition(board, currentRow, currentColumn);
            }
        }
    }

    boolean isValidPosition(int[][] board, int row, int col) {
        if (hasQueenInSameColumn(board, row, col)) {
            return false;
        }
        if (hasQueenInSameDiag(board, row, col)) {
            return false;
        }
        return true;
    }

    boolean hasQueenInSameColumn(int[][] board, int row, int col) {
        int len = board[0].length;

        for (int i = 0; i < len; i ++) {
            if (i == row) {
                continue;
            }

            if (board[i][col] == 1) {
                return true;
            }
        }
        return false;
    }

    boolean hasQueenInSameDiag(int[][] board, int row, int col) {
        int len = board[0].length;
        for (int _row = 0; _row < len; _row ++) {
            for (int _col = 0; _col < len; _col ++) {
                if (_row == row && _col == col) {
                    continue;
                }
                if ( (row - col) == (_row - _col) || (row + col) == (_row + _col)) {
                    if (board[_row][_col] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    void placeQueenInPosition(int[][] board, int row, int col) {
        board[row][col] = 1;
    }

    void removeQueenFromPosition(int[][] board, int row, int col) {
        board[row][col] = 0;
    }

    public static void main(String[] args) {
        NQueensII052 NQueensII052 = new NQueensII052();
        int totalNQueens = NQueensII052.totalNQueens(4);
        System.out.println(totalNQueens);
    }
}
