import java.util.HashSet;
import java.util.Set;

/*
 * [36] Valid Sudoku
 *
 * https://leetcode-cn.com/problems/valid-sudoku/description/
 *
 * algorithms
 * Medium (45.21%)
 * Total Accepted:    3.2K
 * Total Submissions: 6.8K
 * Testcase Example:  '[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]'
 *
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 
 * 
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 
 * 
 * 
 * 
 * 上图是一个部分填充的有效的数独。
 * 
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * 
 * 示例 1:
 * 
 * 输入:
 * [
 * ⁠ ["5","3",".",".","7",".",".",".","."],
 * ⁠ ["6",".",".","1","9","5",".",".","."],
 * ⁠ [".","9","8",".",".",".",".","6","."],
 * ⁠ ["8",".",".",".","6",".",".",".","3"],
 * ⁠ ["4",".",".","8",".","3",".",".","1"],
 * ⁠ ["7",".",".",".","2",".",".",".","6"],
 * ⁠ [".","6",".",".",".",".","2","8","."],
 * ⁠ [".",".",".","4","1","9",".",".","5"],
 * ⁠ [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * [
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: false
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 * ⁠    但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 * 
 * 说明:
 * 
 * 
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 * 
 * 
 */
class Solution {
    private static boolean debug = false;
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

        for(int i = 0; i < array.length; i++) {
            if ('.' == (array[i])){
                continue;
            }

            Integer num = Integer.valueOf(array[i]);

            if (numbers.contains(num)) {
                printArray(array);
                return false;
            } else {
                numbers.add(num);
            }
        }
        return true;
    }

    private void printArray(char[] array) {
        if (!debug) {
            return;
        }
        System.out.println("Invalid Row or Column");
        System.out.println("=====================");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
        System.out.println("=====================");
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

        for (int i = 0; i < cell.length; i ++) {
            for (int j = 0; j < cell[i].length; j++) {
                if ('.' == (cell[i][j])){
                    continue;
                }

                Integer num = Integer.valueOf(cell[i][j]);

                if (numbers.contains(num)) {

                    printCell(cell);

                    return false;
                } else {
                    numbers.add(num);
                }

            }
        }
        return true;
    }

    private void printCell(char[][] cell) {
        if (!debug) {
            return;
        }
        System.out.println("Cell is invalid");
        System.out.println("=================");
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[i].length; j++) {
                System.out.print(cell[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println("=================");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {
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

        System.out.println(solution.isValidSudoku(board));

        char[][] board1 = {
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println(solution.isValidSudoku(board1));

    }

}
