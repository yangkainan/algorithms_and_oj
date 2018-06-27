/*
 * [44] Wildcard Matching
 *
 * https://leetcode-cn.com/problems/wildcard-matching/description/
 *
 * algorithms
 * Hard (14.98%)
 * Total Accepted:    540
 * Total Submissions: 3.2K
 * Testcase Example:  '"aa"\n"a"'
 *
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * 
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 
 * 
 * 两个字符串完全匹配才算匹配成功。
 * 
 * 说明:
 * 
 * 
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 
 * 示例 2:
 * 
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 
 * 
 * 示例 3:
 * 
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 
 * 
 * 示例 4:
 * 
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 
 * 
 * 示例 5:
 * 
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 * 
 */
class WildcardMatching044 {
    public boolean isMatch(String s, String p) {

        if (p == null || s == null) {
            return false;
        }

        String pattern = mergeContinuesWildCardStar(p);
        return recursiveMatch(s, pattern);
    }

    private String mergeContinuesWildCardStar(String p) {
        String pattern = p;
        while (pattern.indexOf("**") >= 0) {
            pattern = pattern.replaceAll("\\*\\*", "*");
        }
        return pattern;
    }

    private boolean isMatchSingleChar(String s, String p) {
        if (p.equals("?")) {
            return true;
        }
        if (p.equals(s)) {
            return true;
        }
        return false;
    }

    private boolean recursiveMatch(String s, String p) {
        if (p.equals("*")) {
            return true;
        }
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        if (s.length() == 0) {
            return false;
        }

        if (p.length() == 0) {
            return false;
        }

        String curS = s.substring(0,1);
        String curP = p.substring(0,1);

        if (curP.equals("*")) {
            // greedy
            int nextMatchStartPoint = findNextMatchStartPoint(s.substring(1), p.substring(1));
            return recursiveMatch(s.substring(nextMatchStartPoint + 1), p) || recursiveMatch(s, p.substring(1));
        } else {
            return isMatchSingleChar(curS, curP) && recursiveMatch(s.substring(1), p.substring(1));
        }
    }

    private int findNextMatchStartPoint(String s, String p) {
        int matchPoint = 0;

        String toMatch = "";
        for (int i = 0; i < p.length(); i++) {
            String cur = String.valueOf(p.charAt(i));
            if (cur.equals("?") || cur.equals("*")) {
                break;
            } else {
                toMatch = toMatch + cur;
            }
        }

        if (toMatch.length() > 0) {
            matchPoint = s.indexOf(toMatch);
        }

        if (matchPoint >=0) {
            return matchPoint;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        WildcardMatching044 solution = new WildcardMatching044();

        System.out.println(solution.isMatch("ho", "ho**"));
        System.out.println(solution.isMatch("", ""));
        System.out.println(solution.isMatch("", "*"));
        System.out.println(solution.isMatch("aa", "a"));
        System.out.println(solution.isMatch("abcdea", "a*?a"));
        System.out.println(solution.isMatch("abcdea", "**a**a"));

        System.out.println(solution.isMatch
                ("abbaabbbbababaababababbabbbaaaabbbbaaabbbabaabbbbbabbbbabbabbaaabaaaabbbbbbaaabbabbbbababbbaaabbabbabb","***b**a*a*b***b*a*b*bbb**baa*bba**b**bb***b*a*aab*a**"));
    }
}
