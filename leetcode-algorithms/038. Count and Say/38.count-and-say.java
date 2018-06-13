/*
 * [38] Count and Say
 *
 * https://leetcode-cn.com/problems/count-and-say/description/
 *
 * algorithms
 * Easy (45.15%)
 * Total Accepted:    3.6K
 * Total Submissions: 7.8K
 * Testcase Example:  '1'
 *
 * 报数序列是指一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * 
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 
 * 
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * 
 * 给定一个正整数 n ，输出报数序列的第 n 项。
 * 
 * 注意：整数顺序将表示为一个字符串。
 * 
 * 示例 1:
 * 
 * 输入: 1
 * 输出: "1"
 * 
 * 
 * 示例 2:
 * 
 * 输入: 4
 * 输出: "1211"
 * 
 * 
 */
class Solution {
    public String countAndSay(int n) {
        if (n <= 0) {
            throw new RuntimeException("invalid input");
        }
        return iterate("1", 1, n);
    }

    private String iterate(String input, int currentInteration, int targetInteration) {
        if (currentInteration == targetInteration) {
            return input;
        }
        String nextInput = nextRound(input);
        return iterate(nextInput, currentInteration + 1, targetInteration);
    }

    private String nextRound(String input) {
        String prevChar = "";
        int occurrence = 0;
        String result ="";
        for (int i = 0; i < input.length(); i++) {
            String ch = String.valueOf(input.charAt(i));
            if (prevChar.equals(ch)) {
                occurrence ++;
            } else {
                if (prevChar != "") {
                    result += String.valueOf(occurrence) + prevChar;
                }
                prevChar = ch;
                occurrence = 1;
            }
        }

        if (prevChar != "") {
            result += String.valueOf(occurrence) + prevChar;
        }

        return result;
    }
}
