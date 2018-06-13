import java.util.HashSet;
import java.util.Set;

/*
 * [37] Sudoku Solver
 *
 * https://leetcode-cn.com/problems/sudoku-solver/description/
 *
 * algorithms
 * Hard (43.73%)
 * Total Accepted:    415
 * Total Submissions: 924
 * Testcase Example:  '[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]'
 *
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 *
 * 空白格用 '.' 表示。
 *
 *
 *
 * 一个数独。
 *
 *
 *
 * 答案被标成红色。
 *
 * Note:
 *
 *
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 *
 *
 */
class SudokuSolver037 {
    private static boolean debug = false;

    class Cell {
        int rowIndex;
        int columnIndex;

        public Cell(int rowIndex, int columnIndex) {
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }

        public int getRowIndex() {
            return rowIndex;
        }

        public int getColumnIndex() {
            return columnIndex;
        }
    }

    public void solveSudoku(char[][] board) {
        if (isValidSudoku(board) && isFinished(board)) {
            return;
        }


        Cell candidateCell = findFirstCandidateCell(board);

        char originCellValue = board[candidateCell.getRowIndex()][candidateCell.getColumnIndex()];
        while (nextOneStep(board, candidateCell)) {
            try {
                solveSudoku(board);
                return;
            } catch (RuntimeException e) {
                // iterate next possible step
            }

        }

        board[candidateCell.getRowIndex()][candidateCell.getColumnIndex()] = originCellValue;
        throw new RuntimeException("Cannot find any SudokuSolver037");

    }

    private Cell findFirstCandidateCell(char[][] board) {
        int candidateCellRowIndex = -1;
        int candidateCellColumnIndex = -1;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if ('.' == board[row][column]) {
                    candidateCellColumnIndex = column;
                    candidateCellRowIndex = row;
                    return new Cell(candidateCellRowIndex, candidateCellColumnIndex);
                }
            }
        }

        throw new RuntimeException("All Cell are filled");
    }

    private boolean nextOneStep(char[][] board, Cell candidateCell) {
        int row = candidateCell.getRowIndex();
        int col = candidateCell.getColumnIndex();

        char cur = board[row][col];
        Integer val = null;
        if ('.' == cur) {
            val = 0;
        } else {
            val = Integer.valueOf(String.valueOf(cur));
        }

        if (val == 9) {
            return false;
        }


        for (val += 1; val <= 9; val++) {
            board[row][col] = String.valueOf(val).charAt(0);
            if (isValidSudoku(board)) {
                return true;
            }
        }

        board[row][col] = cur;
        return false;
    }

    private boolean isFinished(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if ('.' == cell) {
                    return false;
                }

            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {

        return areAllRowsValid(board) &&
                areAllColumnsValid(board) &&
                areAllCellValid(board);
    }

    private boolean areAllRowsValid(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            if (!isValid(board[row])) {
                return false;
            }
        }
        return true;
    }

    private boolean areAllColumnsValid(char[][] board) {
        char[] column = new char[board.length];

        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < column.length; j++) {
                column[j] = board[j][i];
            }
            if (!isValid(column)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(char[] array) {
        Set<Integer> numbers = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            if ('.' == (array[i])) {
                continue;
            }

            Integer num = Integer.valueOf(array[i]);

            if (numbers.contains(num)) {
                return false;
            } else {
                numbers.add(num);
            }
        }
        return true;
    }

    private boolean areAllCellValid(char[][] board) {

        for (int rowIndex = 0; rowIndex < board.length; rowIndex += 3) {
            for (int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex += 3) {

                char[][] cell = new char[3][3];
                for (int i = 0; i < 3; i++) {
                    int cellRowIndex = (i + rowIndex) % 3;
                    for (int j = 0; j < 3; j++) {
                        int cellColumnIndex = (j + columnIndex) % 3;
                        cell[cellRowIndex][cellColumnIndex] = board[rowIndex + i][columnIndex + j];
                    }
                }

                if (!isCellValid(cell)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCellValid(char[][] cell) {
        Set<Integer> numbers = new HashSet<>();

        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[i].length; j++) {
                if ('.' == (cell[i][j])) {
                    continue;
                }

                Integer num = Integer.valueOf(cell[i][j]);

                if (numbers.contains(num)) {
                    return false;
                } else {
                    numbers.add(num);
                }

            }
        }
        return true;
    }


    public static void main(String[] args) {
        SudokuSolver037 sudokuSolver037 = new SudokuSolver037();

        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        sudokuSolver037.solveSudoku(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + ",");
            }
            System.out.println();
        }

    }
}
