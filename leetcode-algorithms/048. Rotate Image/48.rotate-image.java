/*
 * [48] Rotate Image
 *
 * https://leetcode-cn.com/problems/rotate-image/description/
 *
 * algorithms
 * Medium (55.09%)
 * Total Accepted:    4.1K
 * Total Submissions: 7.2K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 
 * 将图像顺时针旋转 90 度。
 * 
 * 说明：
 * 
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 
 * 示例 1:
 * 
 * 给定 matrix = 
 * [
 * ⁠ [1,2,3],
 * ⁠ [4,5,6],
 * ⁠ [7,8,9]
 * ],
 * 
 * 原地旋转输入矩阵，使其变为:
 * [
 * ⁠ [7,4,1],
 * ⁠ [8,5,2],
 * ⁠ [9,6,3]
 * ]
 * 
 * 
 * 示例 2:
 * 
 * 给定 matrix =
 * [
 * ⁠ [ 5, 1, 9,11],
 * ⁠ [ 2, 4, 8,10],
 * ⁠ [13, 3, 6, 7],
 * ⁠ [15,14,12,16]
 * ], 
 * 
 * 原地旋转输入矩阵，使其变为:
 * [
 * ⁠ [15,13, 2, 5],
 * ⁠ [14, 3, 4, 1],
 * ⁠ [12, 6, 8, 9],
 * ⁠ [16, 7,10,11]
 * ]
 * 
 * 
 */
class RotateImage048 {
    public static void main(String[] args) {
        RotateImage048 solution = new RotateImage048();
        int dim = 5;
        int[][] matrix = new int[dim][dim];
        int cnt = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = cnt ++;
                System.out.print(matrix[i][j] +",");
            }
            System.out.println();
        }


        solution.rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] +",");
            }
            System.out.println();
        }

    }

    public void rotate(int[][] matrix) {
        if (matrix.length <= 1) {
            return;
        }

        int matrixDimension = matrix.length;
        int startPointRowIndex = -1;
        int startPointColumnIndex = -1;

        for (int dim = matrixDimension; dim > 0; dim = dim - 2) {
            startPointRowIndex ++;
            startPointColumnIndex ++;
            rotateOutline(matrix, dim, new Point(startPointRowIndex, startPointColumnIndex));
        }

    }

    private void rotateOutline(int[][] matrix, int matrixDimension, Point matrixStartLeftUpperPoint) {
        for( int i = 0; i < matrixDimension -1; i++) {
            Point startPointWithRelativeIndex = new Point(0, i);
            startPointWithRelativeIndex.setValue(getValueOfMatrixPoint(matrix, startPointWithRelativeIndex, matrixStartLeftUpperPoint));
            rotateOnePositionOfOutline(matrix, matrixDimension, matrixStartLeftUpperPoint, startPointWithRelativeIndex);
        }
    }

    private void rotateOnePositionOfOutline(int[][] matrix, int matrixDimension, Point matrixStartLeftUpperPoint, Point toRotateRelativePoint) {
        int initDirection = 0;
        for (int swapIteration = 0; swapIteration < 4; swapIteration ++) {
            int[] newPosition = getNewPointAfterRotating90(toRotateRelativePoint, matrixDimension, initDirection);
            int newRowIndex = newPosition[0];
            int newColumnIndex = newPosition[1];
            initDirection = newPosition[2];

            Point afterRotating90 = new Point(newRowIndex, newColumnIndex);
            afterRotating90.setValue(getValueOfMatrixPoint(matrix, afterRotating90, matrixStartLeftUpperPoint));
            setValueOfMatrixPointTo(matrix, afterRotating90, matrixStartLeftUpperPoint, toRotateRelativePoint.getValue());

            toRotateRelativePoint = afterRotating90;

        }
    }

    private void setValueOfMatrixPointTo(int[][] matrix, Point currentRelativePoint, Point matrixStartLeftUpperPoint, int
            newValue) {
        int absRowIndex = currentRelativePoint.getRowIndex() + matrixStartLeftUpperPoint.getRowIndex();
        int absColumnIndex = currentRelativePoint.getColumnIndex() + matrixStartLeftUpperPoint.getColumnIndex();
        matrix[absRowIndex][absColumnIndex] = newValue;
    }

    private int getValueOfMatrixPoint(int[][] matrix, Point currentRelativePoint, Point matrixStartLeftUpperPoint) {
        int absRowIndex = currentRelativePoint.getRowIndex() + matrixStartLeftUpperPoint.getRowIndex();
        int absColumnIndex = currentRelativePoint.getColumnIndex() + matrixStartLeftUpperPoint.getColumnIndex();
        return matrix[absRowIndex][absColumnIndex];
    }

    private int[] getNewPointAfterRotating90(Point originPoint, int dimension, int direction) {
        int newRowIndex = originPoint.getRowIndex();
        int newColumnIndex = originPoint.getColumnIndex();
        int steps = dimension -1;

        // 0 -> right
        // 1 -> down
        // 2 -> left
        // 3 -> up
        while (steps > 0) {
            while (reachTheEnd(newRowIndex, newColumnIndex, direction, dimension)) {
                direction = (direction+ 1) % 4;
            } 

            switch (direction) {
                case 0: newColumnIndex ++; break;
                case 1: newRowIndex ++; break;
                case 2: newColumnIndex --; break;
                case 3: newRowIndex --; break;
            }

            steps --;
        }



        int[] newPosition = new int[] {newRowIndex, newColumnIndex, direction};
        return newPosition;
    }


    private boolean reachTheEnd(int rowIndex, int columnIndex, int direction, int dimension) {
        boolean theEnd = false;
        switch (direction) {
            case 0: theEnd = (columnIndex == dimension - 1); break;
            case 1: theEnd = (rowIndex == dimension - 1); break;
            case 2: theEnd = (columnIndex == 0); break;
            case 3: theEnd = (rowIndex == 0) ; break;
        }
        return theEnd;
    }

    class Point {
        int rowIndex;
        int columnIndex;
        int value;

        public Point(int rowIndex, int columnIndex) {
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }

        public int getRowIndex() {
            return rowIndex;
        }

        public int getColumnIndex() {
            return columnIndex;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

}
