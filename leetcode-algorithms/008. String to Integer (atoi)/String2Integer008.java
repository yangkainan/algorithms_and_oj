import com.sun.tools.javac.util.Assert;

/**
 * @author yankaina on 16/5/2018.
 */
public class String2Integer008 {

    public int myAtoi(String str) {


        if (str == null || str.trim().length() == 0) {
            return 0;
        }
        String tmp = str.trim();
        String sign = "";
        int startIndex = 0;
        if (tmp.charAt(0) == '-' || tmp.charAt(0) == '+') {
            sign = String.valueOf(tmp.charAt(0));
            startIndex ++;
        }

        String numSubString = "";
        int endIndex = 0;
        for (endIndex = startIndex; endIndex < tmp.length(); endIndex ++) {
            char currentChar = tmp.charAt(endIndex);
            if (currentChar > '9' || currentChar < '0') {
                break;
            }
        }

        numSubString = tmp.substring(startIndex, endIndex);

//        System.out.println(String.format("Input: %s, startIndex: %s, endIndex: %s, sign: '%s', numSubString: '%s'",
//                                         tmp, startIndex, endIndex, sign, numSubString));
        if (numSubString.length() == 0) {
            return 0;
        }

        try {
            Integer result = Integer.parseInt(sign + numSubString);

            return result;

        } catch (NumberFormatException e) {
            if (sign.equals("-")) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }

        }


    }

    public static void main(String[] args) {
        String2Integer008 solution = new String2Integer008();

        Assert.check(solution.myAtoi("42") == 42);
        Assert.check(solution.myAtoi("       -42") == -42);
        Assert.check(solution.myAtoi("4193 with words") == 4193);
        Assert.check(solution.myAtoi("words and 987") == 0);
        Assert.check(solution.myAtoi(Integer.MIN_VALUE + "" +"1") == Integer.MIN_VALUE);
        Assert.check(solution.myAtoi(Integer.MAX_VALUE +"" +"1") == Integer.MAX_VALUE);
    }
}
