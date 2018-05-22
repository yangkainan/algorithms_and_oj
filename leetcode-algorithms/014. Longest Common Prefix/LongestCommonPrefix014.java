import com.sun.tools.javac.util.Assert;

/**
 * @author yankaina on 22/5/2018.
 */
public class LongestCommonPrefix014 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        if (strs.length == 2) {
            return longestCommonPrefix(strs[0], strs[1]);
        } else {
            String[] subStrs = new String[strs.length -1];
            subStrs[0] = longestCommonPrefix(strs[0], strs[1]);
            for (int i = 1; i < subStrs.length; i++) {
                subStrs[i] = strs[i +1];
            }
            return longestCommonPrefix(subStrs);
        }


    }

    private String longestCommonPrefix(String a, String b) {
        if (a == null || a.length() == 0
                || b == null || b.length() == 0) {
            return "";
        }

        int iter = Math.min(a.length(), b.length());
        String result = "";

        for (int i = 0; i < iter; i++){
            if (a.charAt(i) == b.charAt(i)) {
                result += String.valueOf(a.charAt(i));
            } else {
                break;
            }
        }

        return result;

    }

    public static void main(String[] args) {
        LongestCommonPrefix014 solution = new LongestCommonPrefix014();
        String[] input;

        input = new String[]{"flower", "flow", "flight"};

        Assert.check(solution.longestCommonPrefix(input).equals("fl"));

        input = new String[]{"dog","racecar","car"};
        Assert.check(solution.longestCommonPrefix(input).equals(""));

        input = new String[]{"dog"};
        Assert.check(solution.longestCommonPrefix(input).equals("dog"));
    }
}
