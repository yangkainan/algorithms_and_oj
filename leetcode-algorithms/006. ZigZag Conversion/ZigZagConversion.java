import com.sun.tools.javac.util.Assert;

/**
 * @author yankaina on 14/5/2018.
 */


public class ZigZagConversion {
    static class Solution {
        public String convert(String s, int numRows) {
            if (numRows <= 0 || s == null)
                throw new RuntimeException("Invalid input");

            if (numRows == 1 || s.length() == 0) return s;

            if (numRows == 2) {
                String row1= "";
                String row2= "";
                for (int i = 0; i < s.length(); i++) {
                    if ((i%2) == 0) {
                        row1 +=s.charAt(i);
                    } else {
                        row2 +=s.charAt(i);
                    }
                }
                return row1+row2;
            }

            int col,row=0;

            int numCols = (int) Math.ceil(Double.valueOf(s.length() * (numRows -1))/Double.valueOf(2 * numRows -2));

            String[][] result = initResult(numRows, numCols);

            int index = 0;

                for (col = 0; ; col ++) {
                    for (row = 0; row < numRows; row++) {
                        if (index >= s.length()) {
                            return printResult(result);
                        }
                        if ( (col % (numRows -1)) == 0 || (col + row) % (numRows -1) == 0) {
                            result[row][col] = String.valueOf(s.charAt(index));
                            index ++;
                        } else {
                            result[row][col] = "";
                        }
                    }

                }


        }

        private String[][] initResult(int numRows, int numCols) {
            String[][] result = new String[numRows][numCols];
            for(int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    result[i][j]= "";
                }
            }
            return result;
        }

        private String printResult(String[][] result) {
            String print="";
            int cols = result[0].length;
            int rows = result.length;

            for(int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    print += result[i][j];
                    if (result[i][j] == "") {
                        System.out.print(" ");
                    }
                    System.out.print(result[i][j]);
                }
                System.out.println();
            }



            return print;
        }
    }

    public static void main(String[] args) {

        String s;
        int numRows;
        String ret;

        Solution solution = new Solution();

        Assert.check("PAYPALISHIRING".equals(solution.convert("PAYPALISHIRING", 1)));
        Assert.check("PYAIHRNAPLSIIG".equals(solution.convert("PAYPALISHIRING", 2)));

        Assert.check("PAHNAPLSIIGYIR".equals(solution.convert("PAYPALISHIRING", 3)));
        Assert.check("PINALSIGYAHRPI".equals(solution.convert("PAYPALISHIRING", 4)));


    }
}