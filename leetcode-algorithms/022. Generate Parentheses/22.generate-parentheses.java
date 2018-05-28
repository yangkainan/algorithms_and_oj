import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

*/
class GenerateParenthese022 {

    public List<String> generateParenthesis(int n) {
        Set<String> result = new HashSet<>();
        if (n <= 0 ) {
            return new ArrayList<>();
        }
        if (n == 1) {
            result.add("()");
            return new ArrayList<>(result);
        }

        List<String> subResult = generateParenthesis((n - 1));

        for (int i = 0; i < subResult.size(); i++) {
            String subString = subResult.get(i);

            int candidateInsertPosition = 2 * subString.length() + 1;
            for (int j = 0; j < candidateInsertPosition; j++) {
                for (int k = j + 1; k < candidateInsertPosition; k++) {
                    String newSubString = addOneBraceInto(subString, j, k,candidateInsertPosition);
                    result.add(newSubString);
                }
            }

        }
        return new ArrayList<>(result);

    }

    private String addOneBraceInto(String subString, int leftBracePosition, int rightBracePosition, int totalLength) {
        char[] newCharArray = new char[totalLength];
        newCharArray[leftBracePosition] = '(';
        newCharArray[rightBracePosition] = ')';
        for (int i = 0; i < subString.length(); i++) {
            for (int j = i; j < newCharArray.length; j++) {
                if (newCharArray[j] != Character.MIN_VALUE) {
                    continue;
                } else {
                    newCharArray[j] = subString.charAt(i);
                    break;
                }
            }
        }

        String result = "";
        for (int i = 0; i < newCharArray.length; i++) {
            char cur = newCharArray[i];
            if (cur != Character.MIN_VALUE) {
                result += String.valueOf(cur);
            }

        }
        return result;
    }

    public static void main(String[] args) {
        GenerateParenthese022 solution = new GenerateParenthese022();
        List<String> strings = solution.generateParenthesis(3);
        for (int i = 0; i < strings.size(); i++) {
            System.out.println(strings.get(i));
        }
    }

}
