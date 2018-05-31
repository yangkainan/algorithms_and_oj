/*
 * [28] Implement strStr()
 *
 * https://leetcode-cn.com/problems/implement-strstr/description/
 *
 * algorithms
 * Easy (33.81%)
 * Total Accepted:    4.7K
 * Total Submissions: 13.8K
 * Testcase Example:  '"hello"\n"ll"'
 *
 * 实现 strStr() 函数。
 * 
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置
 * (从0开始)。如果不存在，则返回  -1。
 * 
 * 示例 1:
 * 
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 
 * 
 * 示例 2:
 * 
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 
 * 
 * 说明:
 * 
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * 
 */
class ImplementStrStr028 {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }

        int index = 0;

        while (index < haystack.length()) {
            int endIndex = index + needle.length();
            if (endIndex > haystack.length()) {
                return -1;
            }

            String substring = haystack.substring(index, endIndex);
            if (substring.equals(needle)) {
                return index;
            } else {
                index ++;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        ImplementStrStr028 solution = new ImplementStrStr028();
        System.out.println(solution.strStr("hello","ll"));


    }
}
