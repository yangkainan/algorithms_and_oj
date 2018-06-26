/*
 * [43] Multiply Strings
 *
 * https://leetcode-cn.com/problems/multiply-strings/description/
 *
 * algorithms
 * Medium (30.68%)
 * Total Accepted:    984
 * Total Submissions: 3.1K
 * Testcase Example:  '"2"\n"3"'
 *
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 
 * 示例 1:
 * 
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 
 * 示例 2:
 * 
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 
 * 说明：
 * 
 * 
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * 
 * 
 */
class MultiplyStrings043 {
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0
                || num2 == null || num2.length() == 0) {
            return "0";
        }

        String result = "";
        for (int i = num1.length() -1; i >= 0; i--) {
            String oneDigit = String.valueOf(num1.charAt(i));
            String oneDigitMultipyResult = oneDigitMultiply(oneDigit, num2);

            if (!oneDigitMultipyResult.equals("0")) {
                oneDigitMultipyResult = fillRigthZero(oneDigitMultipyResult, num1.length() - 1 - i);
            }

            result = addTwoNumer(oneDigitMultipyResult, result);
        }

        return result;
    }

    private String oneDigitMultiply(String oneDigit, String multiplyee) {
        Integer mul = Integer.valueOf(String.valueOf(oneDigit.charAt(0)));
        if (mul == 0) {
            return "0";
        }

        Integer accu = 0;
        String result = "";
        for (int i = multiplyee.length() - 1; i >= 0; i--) {
            Integer cur = Integer.valueOf(String.valueOf(multiplyee.charAt(i)));
            Integer tmp = cur * mul + accu;
            result = String.valueOf( tmp % 10) + result;
            accu = tmp / 10;
        }
        if (accu > 0) {
            result = String.valueOf(accu) + result;
        }
        return result;
    }

    private String fillRigthZero(String target, int numOfZeroToFill) {
        for (int i = 0; i < numOfZeroToFill; i++) {
            target += "0";
        }
        return target;
    } 

    private String addTwoNumer(String num1, String num2) {
        Integer accu = 0;
        int len = Math.min(num1.length(), num2.length());

        String result = "";
        for (int i = 0 ; i < len ; i++) {
            Integer n1 = Integer.valueOf(String.valueOf(num1.charAt( (num1.length() - 1) - i)));
            Integer n2 = Integer.valueOf(String.valueOf(num2.charAt( (num2.length() - 1) - i)));
            Integer tmp = n1 + n2 + accu;
            result = String.valueOf(tmp % 10) + result;
            accu = tmp / 10;
        }

        String longerNum = "";
        if (num1.length() > len) {
            longerNum = num1;
        } else if (num2.length() > len) {
            longerNum = num2;
        }

        if (longerNum.length() > 0) {
            for (int i = longerNum.length() - len - 1; i >= 0 ; i--) {
                Integer n = Integer.valueOf(String.valueOf(longerNum.charAt(i)));
                Integer tmp = n + accu;
                result = String.valueOf(tmp % 10) + result;
                accu = tmp / 10;
            }
        }

        if (accu > 0) {
            result = String.valueOf(accu) + result;
        }
        return result;
    }

    public static void main(String[] args) {
        MultiplyStrings043 solution = new MultiplyStrings043();
        System.out.println(solution.multiply("123", "0"));
    }
}
