import com.sun.tools.javac.util.Assert;

/**
 * @author yankaina on 17/5/2018.
 */
public class PalindromeNumber009 {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        // remove + sign if have
        String str = String.valueOf(x);
        if (String.valueOf(str.charAt(0)).equals("+")) {
            str = str.substring(1);
        }

        int headIndex = 0;
        int tailIndex = str.length() -1;

        while (headIndex < tailIndex) {
            String leftCharacter = String.valueOf(str.charAt(headIndex ++));
            String rightCharacter = String.valueOf(str.charAt(tailIndex --));

            if (! leftCharacter.equals(rightCharacter)) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        PalindromeNumber009 solution = new PalindromeNumber009();

        Assert.check(solution.isPalindrome(10) == false);
        Assert.check(solution.isPalindrome(121) == true);
        Assert.check(solution.isPalindrome(-10) == false);
    }
}
