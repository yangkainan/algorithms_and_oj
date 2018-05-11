import com.sun.tools.javac.util.Assert;

import java.io.IOException;

/**
 * @author yankaina on 11/5/2018.
 */


public class LongestPalindromicSubstring {
    static class Solution {
        public String longestPalindrome(String s) {
            if (s == null) return null;
            if (s.trim().length() == 0) return "";
            if (isPalindrome(s)) return s;

            String maxPalindrome="";

            int start=0;
            int end=0;
            while (start < s.length()) {
                end = start;
                while (end < s.length()) {
                    String subString = s.substring(start, end + 1);
                    System.out.println(String.format("%s is palindrome %s", subString, isPalindrome(subString)));
                    if (isPalindrome(subString)) {
                        if (subString.length() >= maxPalindrome.length()) {
                            System.out.println(String.format(" prev max: %s, current max: %s", maxPalindrome, subString));
                            maxPalindrome = subString;
                        }
                    }
                    end ++;
                }
                start ++;
            }

            return maxPalindrome;

        }

        public static boolean isPalindrome(String s) {
            if (s == null) return false;
            if (s.length() == 0) return  true;
            int start = 0;
            int end = s.length() -1;

            while(start <= end) {
                if (s.charAt(start) != s.charAt(end)) {
                    return false;
                }
                start ++;
                end --;
            }

            return true;

        }
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        System.out.println("================================");
        Assert.check(solution.longestPalindrome("a").equals("a"));
        System.out.println("================================");

        Assert.check(solution.longestPalindrome("babad").equals("bab") ||
                        solution.longestPalindrome("babad").equals("aba"));
        System.out.println("================================");
        Assert.check(solution.longestPalindrome("cbbd").equals("bb"));
        System.out.println("================================");
    }
}
