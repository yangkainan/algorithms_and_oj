import java.util.ArrayList;
import java.util.List;

/*
 * [51] N-Queens
 *
 * https://leetcode-cn.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (46.65%)
 * Total Accepted:    772
 * Total Submissions: 1.4K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 
 * 
 * 上图为 8 皇后问题的一种解法。
 * 
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 
 * 示例:
 * 
 * 输入: 4
 * 输出: [
 * ⁠[".Q..",  // 解法 1
 * ⁠ "...Q",
 * ⁠ "Q...",
 * ⁠ "..Q."],
 * 
 * ⁠["..Q.",  // 解法 2
 * ⁠ "Q...",
 * ⁠ "...Q",
 * ⁠ ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * 
 * 
 */
class NQueens051 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (n < 0) {
            return result;
        }

        List<String> board = initBoard(n);

        recursiveFindAllResult(n, 0, board, result);
        List<List<String>> solutions = new ArrayList<>();
        for (List<String> list: result) {
            int currentIndex = 0;
            List<String> solution = new ArrayList<>();
            String row = "";
            for (String s : list) {
                row = row + s;
                currentIndex ++;
                if (currentIndex % n == 0) {
                    solution.add(row);
                    row = "";
                }
            }
            solutions.add(solution);
        }

        return solutions;
    }

    private List<String> initBoard(int nXn) {

        List<String> board = new ArrayList<String>(nXn * nXn);
        for (int i = 0; i < nXn * nXn; i++) {
            board.add(".");
        }
        return board;
    }

    private void recursiveFindAllResult(int nQueens, int ithQueen, List<String> currentBoard, List<List<String>> allSolution) {
        if (ithQueen >= nQueens) {
            if (!containSameSolution(allSolution, currentBoard)) {
                allSolution.add(new ArrayList<>(currentBoard));
            }
            return;
        }

        for (int i = 0; i < nQueens; i++) {
            placeQueen(nQueens, currentBoard, ithQueen, i);
            if (isValidBoard(nQueens, currentBoard)) {
                recursiveFindAllResult(nQueens, ithQueen + 1, currentBoard, allSolution);
            } 
            removeQueen(nQueens, currentBoard, ithQueen, i);
        }
    }

    private boolean containSameSolution(List<List<String>> allSolution, List<String> currentBoard) {
        return false;
//        for (List<String> board : allSolution) {
//            if (isSameSolution(board, currentBoard)) {
//                return true;
//            }
//        }
//        return false;
    }

    private boolean isSameSolution(List<String> board, List<String> currentBoard) {
        boolean allSame = true;
        for (int i = 0; i < board.size(); i++) {
            if (!board.get(i).equals(currentBoard.get(i))) {
                allSame = false;
                break;
            }
        }
        if (allSame) {
            return true;
        }
        allSame = true;

        for (int i = 0, j = board.size() - 1; i < board.size() && j >= 0; i++, j--) {
            if (!board.get(i).equals(currentBoard.get(j))) {
                allSame = false;
                break;
            }
        }
        return allSame;
    }

    private boolean isValidBoard(int nQueens, List<String> board) {
        String[][] _board = new String[nQueens][nQueens];
        for(int i = 0; i < nQueens; i++) {
            for (int j = 0; j < nQueens; j++) {
                int index = i * nQueens + j;
                _board[i][j] = board.get(index);
            }
        }
        // no need to check whether more than 1 queen in same row.
        return !isMoreThan1QueenInSameColumn(_board) && !isMoreThan1QueenInSameDiag(_board);
    }

    private boolean isMoreThan1QueenInSameColumn(String[][] board) {
        for (int col = 0; col < board.length; col ++) {
            int queenCnt = 0;
            for (int row = 0; row < board.length; row ++) {
                if("Q".equals(board[row][col])) {
                    queenCnt ++;
                }
            }
            if ( queenCnt > 1) {
                return true;
            }
        }

        return false;
    }

    private boolean isMoreThan1QueenInSameDiag(String[][] board) {
        for (int row = 0; row < board.length ; row++) {
            for (int col = 0; col < board.length; col ++) {
                if ("Q".equals(board[row][col])) {
                    for (int checkRow = row + 1; checkRow < board.length; checkRow ++) {
                        int step = checkRow - row;
                        int checkCol = col + step;
                        if (checkCol < board.length) {
                            if ("Q".equals(board[checkRow][checkCol])) {
                                return true;
                            }
                        }
                        checkCol = col - step;
                        if (checkCol >= 0) {
                            if ("Q".equals(board[checkRow][checkCol])) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }



    private void placeQueen(int nQueens, List<String> board, int ithQueen, int placement) {
        int index = (ithQueen * nQueens) + placement;
        board.set(index, "Q");
    }

    private void removeQueen(int nQueens, List<String> board, int ithQueen, int placement) {
        int index = (ithQueen * nQueens) + placement;
        board.set(index, ".");
    }

    public static void main(String[] args) {
        NQueens051 solution = new NQueens051();
        int n = 5;
        List<List<String>> result = solution.solveNQueens(n);
        for (List<String> list : result) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println("******************");
        }
    }
}
