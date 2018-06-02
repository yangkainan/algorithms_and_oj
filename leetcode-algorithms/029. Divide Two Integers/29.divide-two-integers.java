/*
 * [29] Divide Two Integers
 *
 * https://leetcode-cn.com/problems/divide-two-integers/description/
 *
 * algorithms
 * Medium (13.93%)
 * Total Accepted:    887
 * Total Submissions: 6.2K
 * Testcase Example:  '10\n3'
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 
 * 示例 1:
 * 
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 
 * 示例 2:
 * 
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 
 * 说明:
 * 
 * 
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * 
 * 
 */
class DivideTwoIntegers029 {
    public int divide(int dividend, int divisor) {

        int min = 0x80000000;
        int max = 0x7fffffff;

        boolean isSameSign;

        if (dividend == 0) {
            return 0;
        }
        if (divisor == 0) {
            throw new IllegalArgumentException("divisor is 0");
        }

        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor< 0)) {
            isSameSign = true;
        } else {
            isSameSign = false;
        }


        if (divisor == 1) {
            return dividend;
        }

        if (divisor == -1) {
            if (dividend == min) {
                return max;
            }
            return changeSign(dividend);
        }

        long absDividen = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        if (absDividen < absDivisor) {
            return 0;
        }

        int times = 1;
        long timesDivisor = absDivisor;
        while ((timesDivisor << 1) <= absDividen) {
            timesDivisor = timesDivisor << 1;
            times += times;
        }

        absDividen -= timesDivisor;


        while (absDividen >= absDivisor) {
            absDividen -= absDivisor;
            times ++;
        }

        if (isSameSign) {
            return times;
        } else {
            return changeSign(times);
        }


    }


    private int changeSign(int result) {
        return (result ^ 0xffffffff) + 1;
    }

    public static void main(String[] args) {
        DivideTwoIntegers029 solution = new DivideTwoIntegers029();

        System.out.println(solution.divide(-2147483648, 2));
        System.out.println(solution.divide(1, 2));
        System.out.println(solution.divide(-1, 1));
        System.out.println(solution.divide(-8, -2));
        System.out.println(solution.divide(-2147483648, -1));
        System.out.println(solution.divide(2147483647, 3));

        System.out.println(solution.changeSign( -1));
        System.out.println(solution.changeSign( 1));
    }
}
