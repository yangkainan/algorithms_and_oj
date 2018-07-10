/*
 * [50] Pow(x, n)
 *
 * https://leetcode-cn.com/problems/powx-n/description/
 *
 * algorithms
 * Medium (24.51%)
 * Total Accepted:    2.1K
 * Total Submissions: 8K
 * Testcase Example:  '2.00000\n10'
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * 
 * 示例 1:
 * 
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 
 * 
 * 示例 2:
 * 
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 
 * 
 * 示例 3:
 * 
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 
 * 说明:
 * 
 * 
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 * 
 * 
 */
class Solution {
    public double myPow(double x, int n) {
        if ( x >= 100.0 || x <= -100.0 ) {
            throw new RuntimeException("invalid x: " + x);
        }
        if (n == 0) return 1;
        if (x == 0.0) return 0;

        boolean isResultNegtive = false;
        if (x < 0) {
            if ( ( n % 2 ) == 1) {
                isResultNegtive = true;
            }
            x = -1 * x;
        }
        if (x == 1.0) {
            if (isResultNegtive) {
                return -1.0;
            } else {
                return 1.0;
            }
        }

        boolean isNegtivePow = (n < 0);
        boolean divOneMore = false;
        if (isNegtivePow) {
            if (n == Integer.MIN_VALUE) {
                n = Integer.MAX_VALUE;
                divOneMore = true;
            } else {
                n = -1 * n;
            }
        }
        if ( n == Integer.MAX_VALUE) {
            if (x >= 2.0) {
                if (isNegtivePow) {
                    return 0;
                } else {
                    if (isResultNegtive) {
                        return Double.MIN_VALUE;
                    } else {
                        return Double.MAX_VALUE;
                    }
                }

            }
        }


        double result = x;
        while ( n > 1) {
            n --;
            result = result * x;

            if (isOverFlow(result)) {
                if (isNegtivePow) {
                    return 0;
                } else {
                    if (isResultNegtive) {
                        return Double.MIN_VALUE;
                    } else {
                        return Double.MAX_VALUE;
                    }
                }
            }

            if (isAlmostZero(result)) {
                if (isNegtivePow) {
                    if (isResultNegtive) {
                        return Double.MIN_VALUE;
                    } else {
                        return Double.MAX_VALUE;
                    }
                } else {
                    return 0.0;
                }

            }
        }

        if (isNegtivePow) {
            result = 1 / result;
            if (divOneMore) {
                result = result * (1 / x);
            }
        }
        if (isResultNegtive) {
            result = -1 * result;
        }

        return result;
    }

    private boolean isOverFlow(double toCheck) {
        return toCheck < 0;
    }
    private boolean isAlmostZero(double toCheck) {
        return toCheck < 0.0000000000000000001;
    }
}
