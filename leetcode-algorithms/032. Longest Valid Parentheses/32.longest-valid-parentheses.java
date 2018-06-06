import java.util.Stack;

/*
 * [32] Longest Valid Parentheses
 *
 * https://leetcode-cn.com/problems/longest-valid-parentheses/description/
 *
 * algorithms
 * Hard (19.11%)
 * Total Accepted:    738
 * Total Submissions: 3.7K
 * Testcase Example:  '"(()"'
 *
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 
 * 示例 1:
 * 
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 
 * 
 * 示例 2:
 * 
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * 
 * 
 */
class LongestValidParentheses032 {
    boolean debug = false;

    LongestValidParentheses032(boolean debug) {
        this.debug = debug;
    }

    LongestValidParentheses032() {

    }

    private static final String CLOSE = ")";
    private static final String OPEN = "(";

    public int longestValidParentheses(String s) {
        Stack<String> stack = new Stack<>();

        if (s == null || s.length() <= 1) {
            return 0;
        }

        for (int i = 0; i < s.length(); i++) {
            addBraceIntoStack(stack, String.valueOf(s.charAt(i)));
        }

        String longest = "";
        for (String s1 : stack) {
            if (s1.length() > longest.length() && (s1.length() % 2) == 0) {
                longest = s1;
            }
        }


        if (debug) {
            System.out.println(String.format("Input: %s, longest: %s, length: %s", s, longest, longest.length()));
        }
        return longest.length();

    }

    private void addBraceIntoStack(Stack<String> stack, String brace) {
        if (isOpenBrace(brace)) {
            stack.push(brace);
            return;
        }

        if (stack.empty()) {
            stack.push(brace);
            return;
        }

        String top = stack.peek();

        if (isOpenBrace(top)) {
            stack.pop();
            stack.push(OPEN + CLOSE);
            combineNextPair(stack);
            return;
        }

        if (isCloseBrace(top)) {
            stack.push(brace);
            return;
        }

        if (isPair(top)) {
            String pair = stack.pop();

            if (stack.empty()) {
                stack.push(pair);
                stack.push(brace);
            } else {
                if (isOpenBrace(stack.peek())) {
                    stack.pop();
                    stack.push(OPEN + pair + CLOSE);
                    combineNextPair(stack);
                } else {
                    stack.push(pair);
                    stack.push(brace);
                }
            }

        }
    }

    private void combineNextPair(Stack<String> stack) {
        if (stack.empty()) {
            return;
        }

        if (isPair(stack.peek())) {
            String pair = stack.pop();
            if (stack.empty()) {
                stack.push(pair);
            } else if (isPair(stack.peek())) {
                String nextPair = stack.pop();
                stack.push(nextPair + pair);
                combineNextPair(stack);
            } else {
                stack.push(pair);
            }
        }

    }

    private boolean isPair(String peek) {
        return peek.length() > 1;
    }

    private boolean isCloseBrace(String brace) {
        return CLOSE.equals(brace);
    }

    private boolean isOpenBrace(String brace) {
        return OPEN.equals(brace);
    }

    public static void main(String[] args) {
        LongestValidParentheses032 solution = new LongestValidParentheses032(true);

        solution.longestValidParentheses(")(");
        solution.longestValidParentheses("()");
        solution.longestValidParentheses("(((()))))))");
        solution.longestValidParentheses(")()())");
        solution.longestValidParentheses("()(");
        solution.longestValidParentheses("(((((()())()()))()(()))(");
        solution.longestValidParentheses("()(()");
    }

}
