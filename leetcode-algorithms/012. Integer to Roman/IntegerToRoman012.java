import com.sun.tools.javac.util.Assert;

/**
 * @author yankaina on 21/5/2018.
 */
/*
*
* 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

示例 1:

输入: 3
输出: "III"
示例 2:

输入: 4
输出: "IV"
示例 3:

输入: 9
输出: "IX"
示例 4:

输入: 58
输出: "LVIII"
解释: C = 100, L = 50, XXX = 30, III = 3.
示例 5:

输入: 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.
* */
public class IntegerToRoman012 {
    public String intToRoman(int num) {
        Assert.check(num >=1 && num <= 3999);
        String result = dealWithThousand(num) + dealWithHundred(num) + dealWithTen(num) + dealWithNum(num);

//        System.out.println(String.format("Input: %s, output: %s", num, result));
        return result;
    }

    private String dealWithNum(int num) {
       int number = (num %10);

       return dealWithNormal(number, "I", "X", "V");

//        if (number == 9) {
//            return "IX";
//        }
//
//        if (number == 4) {
//            return "IV";
//        }
//
//        if (number == 5) {
//            return "V";
//        }
//
//        if (number > 5) {
//            String result= "V";
//            for (int i = 1; i <= (number - 5); i++) {
//                result +="I";
//            }
//            return result;
//        }else {
//            String result= "";
//            for (int i = 1; i <= number; i++) {
//                result +="I";
//            }
//            return result;
//        }
    }


    private String dealWithTen(int num) {
        int numOfTen = (num % 100)/10;

        return dealWithNormal(numOfTen, "X", "C", "L");

//        if (numOfTen == 9) {
//            return "XC";
//        }
//
//        if (numOfTen == 4) {
//            return "XL";
//        }
//
//        if (numOfTen == 5) {
//            return "L";
//        }
//
//        if (numOfTen > 5) {
//            String result= "L";
//            for (int i = 1; i <= (numOfTen - 5); i++) {
//                result +="X";
//            }
//            return result;
//        }else {
//            String result= "";
//            for (int i = 1; i <= numOfTen; i++) {
//                result +="X";
//            }
//            return result;
//        }

    }

    private String dealWithHundred(int num) {
        int numOfHundred = (num % 1000)/100;

        return dealWithNormal(numOfHundred, "C", "M", "D");

//        if (numOfHundred == 9) {
//            return "CM";
//        }
//
//        if (numOfHundred == 4) {
//            return "CD";
//        }
//
//        if (numOfHundred == 5) {
//            return "D";
//        }
//
//        if (numOfHundred > 5) {
//            String result= "D";
//            for (int i = 1; i <= (numOfHundred - 5); i++) {
//                result +="C";
//            }
//            return result;
//        }else {
//            String result= "";
//            for (int i = 1; i <= numOfHundred; i++) {
//                result +="C";
//            }
//            return result;
//        }

    }

    private String dealWithNormal(int number, String unit, String tenUnit, String fiveUnit) {
        if (number == 9) {
            return unit + tenUnit;
        }

        if (number == 4) {
            return unit + fiveUnit;
        }

        if (number == 5) {
            return fiveUnit;
        }

        if (number > 5) {
            String result= fiveUnit;
            for (int i = 1; i <= (number - 5); i++) {
                result +=unit;
            }
            return result;
        }else {
            String result= "";
            for (int i = 1; i <= number; i++) {
                result +=unit;
            }
            return result;
        }
    }

    private String dealWithThousand(int num) {
        if (num < 1000) {
            return "";
        }

        int numOfThousand = num/1000;
        String result ="";
        for (int i = 1; i<= numOfThousand; i++) {
            result +="M";
        }
        return result;
    }

    public static void main(String[] args) {

        IntegerToRoman012 solution = new IntegerToRoman012();

        Assert.check(solution.intToRoman(3).equals("III"));
        Assert.check(solution.intToRoman(4).equals("IV"));
        Assert.check(solution.intToRoman(9).equals("IX"));
        Assert.check(solution.intToRoman(58).equals("LVIII"));
        Assert.check(solution.intToRoman(1994).equals("MCMXCIV"));

    }
}
