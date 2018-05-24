import com.sun.tools.javac.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * [17] Letter Combinations of a Phone Number
 *
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * algorithms
 * Medium (40.91%)
 * Total Accepted:    1.5K
 * Total Submissions: 3.6K
 * Testcase Example:  '"23"'
 *
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below. Note that 1 does not map to any letters.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 
 * Note:
 * 
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 * 
 */
class LetterCombinationOfAPhoneNumber017 {
    private final static HashMap<Integer, String> digit2StringMap = new HashMap<>();
    static  {
        digit2StringMap.put(2, "abc");
        digit2StringMap.put(3, "def");
        digit2StringMap.put(4, "ghi");
        digit2StringMap.put(5, "jkl");
        digit2StringMap.put(6, "mno");
        digit2StringMap.put(7, "pqrs");
        digit2StringMap.put(8, "tuv");
        digit2StringMap.put(9, "wxyz");
    }
    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        Integer first = Integer.valueOf(String.valueOf(digits.charAt(0)));

        if (first < 2 || first > 9) {
            throw new IllegalArgumentException();
        }
        String posibleStringA = digit2StringMap.get(first);
        List<String> result = new ArrayList<>();
        if (digits.length() == 1) {
            for (int i = 0; i < posibleStringA.length(); i++) {
                result.add(String.valueOf(posibleStringA.charAt(i)));
            }

            return result;
        }

        List<String> subResult = letterCombinations(digits.substring(1));

        for (int i = 0; i < subResult.size(); i++) {
            String curSubResult = subResult.get(i);
            for (int j = 0; j < posibleStringA.length(); j++) {
                String headString = String.valueOf(posibleStringA.charAt(j));
                String combine = headString + curSubResult;

                if (!result.contains(combine)) {
                    result.add(combine);
                }
            }


        }


        return result;
    }

    public static void main(String[] args) {
        String digits = "23";

        List<String> expected = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");

        LetterCombinationOfAPhoneNumber017 solution = new LetterCombinationOfAPhoneNumber017();

        List<String> actual = solution.letterCombinations(digits);

        actual.stream().forEach( e -> System.out.print(e +","));

        Assert.check(actual.containsAll(expected) && expected.containsAll(actual));

    }
}
