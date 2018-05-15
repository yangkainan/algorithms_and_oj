import com.sun.tools.javac.util.Assert;

/**
 * @author yankaina on 15/5/2018.
 */
public class ReverseInteger {
    public int reverse(int x) {
        String sign= "";
        int start, end = 0;

        if (x < 0) {
            sign = "-";
            end = 1;
        }

        String val = String.valueOf(x);

        String target = sign;

        for (start = val.length() -1; start >= end; start --) {
            target += val.charAt(start);
        }

        try {
            return Integer.parseInt(target);
        } catch (NumberFormatException e) {
            // in case the value is out of scope of [- 2^32, 2^32 -1]
            return 0;
        }

    }

    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();

        Assert.check(reverseInteger.reverse(123) == 321);
        Assert.check(reverseInteger.reverse(-123) == -321);
        Assert.check(reverseInteger.reverse(120) == 21);
    }
}
