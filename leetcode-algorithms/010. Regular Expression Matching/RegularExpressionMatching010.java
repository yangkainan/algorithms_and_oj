import com.sun.tools.javac.util.Assert;

import java.util.HashMap;

/**
 * @author yankaina on 17/5/2018.
 */
public class RegularExpressionMatching010 {
    private HashMap<String, Boolean> isVisited = new HashMap<>();
    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return  false;
        }

        if (s.length() == 0) {
            if (p.length() == 0) {
                return true;
            }
            if (p.length() == 2 && p.charAt(p.length() -1) == '*') {
                return true;
            }
        }

        if (p.length() == 0) {
            return false;
        }

        if (p.length() > 0 && p.charAt(0) == '*') {
            throw new IllegalArgumentException("Pattern can be start with '*', input Patter is: " + p);
        }
        String visitKey = s + "||" + p;

        if (isVisited.get(visitKey) != null) {
            return isVisited.get(visitKey);
        }


        String pattern = normalizePattern(p);


        char patternCurrentChar = pattern.charAt(0);
        boolean isStarPattern = false;
        boolean matchAnyChar = (patternCurrentChar == '.');

        if (pattern.length() >=2 && (pattern.charAt(1) == '*')) {
            isStarPattern = true;
        }


        System.out.println(String.format("input Str: %s, pattern: %s, currentPattern:%s, isStar:%s,  matchAnyChar: %s",
                                         s, pattern, patternCurrentChar, isStarPattern,  matchAnyChar ));

        boolean result;
        if (s.length() == 0) {
            if (isStarPattern) {
                result = isMatch(s, pattern.substring(2));
            } else {
                result = false;
            }

        } else if (matchAnyChar || s.charAt(0)== patternCurrentChar) {
            if (! isStarPattern) {
                // (ab, aa) => (a,b)
                // (ab, .b) => (b, b)
                result = isMatch(s.substring(1), pattern.substring(1));
            } else {
                // (ab, a*a) => (b, a*a) || (b, a) || (ab, a)
                // (ab, .*) => (b, .*) || (b, )
                result = isMatch(s.substring(1), pattern) || isMatch(s.substring(1), pattern.substring(2))
                        || isMatch(s, pattern.substring(2));
            }
        } else {
            if (! isStarPattern) {
                // (a, b)
                result = false;
            } else {
                // (a, b*a) => (a, a)
                result = isMatch(s, pattern.substring(2));
            }

        }

        isVisited.put(visitKey, result);
        return result;


    }

    private String normalizePattern(String p) {
        // normalize pattern, transform  continues * into one.
        // e.g.  a******b ==> a*b
        return p;
    }

    public static void main(String[] args) {
        RegularExpressionMatching010 solution = new RegularExpressionMatching010();

        Assert.check(solution.isMatch("aa", "a") == false);
        Assert.check(solution.isMatch("", "c*c*") == true);
        Assert.check(solution.isMatch("a", "a*a") == true);
        Assert.check(solution.isMatch("aa", "a*") == true);
        Assert.check(solution.isMatch("aa", "a*b") == false);
        Assert.check(solution.isMatch("ab", ".*") == true);
        Assert.check(solution.isMatch("abc", ".*b") == false);
        Assert.check(solution.isMatch("aab", "c*a*b") == true);
        Assert.check(solution.isMatch("mississippi", "mis*is*p*") == false);
        Assert.check(solution.isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*c") == false);
    }
}
