import java.util.Stack;

/*
 * [20] Valid Parentheses
 *
 * https://leetcode-cn.com/problems/valid-parentheses/description/
 *
 * algorithms
 * Easy (30.47%)
 * Total Accepted:    4.4K
 * Total Submissions: 14.2K
 * Testcase Example:  '"()"'
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * An input string is valid if:
 * 
 * 
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * 
 * 
 * Note that an empty string is also considered valid.
 * 
 * Example 1:
 * 
 * 
 * Input: "()"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "()[]{}"
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "(]"
 * Output: false
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: "([)]"
 * Output: false
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: "{[]}"
 * Output: true
 * 
 * 
 */
class ValidParentheses020 {

    Stack<String> braceStack = new Stack<>();

    public boolean isValid(String s) {
        if (s == null || s.length() == 0){
            return true;
        }

        for (int i = 0; i < s.length(); i++) {
            String cur = String.valueOf(s.charAt(i));
            if (braceStack.empty() || isOpenBrace(cur)) {
                braceStack.push(cur);
            } else {
                String openBrace = braceStack.peek();
                if (isPairBrace(cur, openBrace)) {
                    braceStack.pop();
                } else {
                    return false;
                }
            }
        }

        if (braceStack.empty()) {
            return true;
        } else {
            return false;
        }

    }

    private boolean isPairBrace(String closeBrace, String openBrace) {
        if (openBrace.equals("(") && closeBrace.equals(")") ||
                openBrace.equals("{") && closeBrace.equals("}") ||
                openBrace.equals("[") && closeBrace.equals("]")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isOpenBrace(String cur) {
        if (cur.equals("(") ||
                cur.equals("{") ||
                cur.equals("[")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        ValidParentheses020 solution = new ValidParentheses020();

        String input;

        input = "()";
        System.out.println(String.format("Input: %s is: %s", input, solution.isValid(input)));

        input = "()[]{}";
        System.out.println(String.format("Input: %s is: %s", input, solution.isValid(input)));

        input = "{[()]}";
        System.out.println(String.format("Input: %s is: %s", input, solution.isValid(input)));

        input = "([)";
        System.out.println(String.format("Input: %s is: %s", input, solution.isValid(input)));

        input = "(})";
        System.out.println(String.format("Input: %s is: %s", input, solution.isValid(input)));
    }
}
