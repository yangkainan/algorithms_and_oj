import com.sun.tools.javac.util.Assert;

import java.util.HashMap;

/**
 * @author yankaina on 22/5/2018.
 */
public class RomanToInteger013 {
    private HashMap<String, Integer> romanCharValue = new HashMap<>();
    {
        romanCharValue.put("I",1);
        romanCharValue.put("IV",4);
        romanCharValue.put("V",5);
        romanCharValue.put("IX",9);
        romanCharValue.put("X",10);
        romanCharValue.put("XL",40);
        romanCharValue.put("L",50);
        romanCharValue.put("XC",90);
        romanCharValue.put("C",100);
        romanCharValue.put("CD",400);
        romanCharValue.put("D",500);
        romanCharValue.put("CM",900);
        romanCharValue.put("M",1000);
    }

    int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int result = 0;
        for(int i = 0; i < s.length(); i ++) {
            char curr = s.charAt(i);
            char next = Character.UNASSIGNED;

            int j = i +1;
            if (j < s.length()) {
                next = s.charAt(j);
            }
            String key = String.format("%s%s",curr,next);
            if (next != Character.UNASSIGNED &&
                    romanCharValue.keySet().contains(key)) {
                    result += romanCharValue.get(key);
                    i++;
            } else {
                result += romanCharValue.get(String.valueOf(curr));
            }

        }

        return result;
    }


    public static void main(String[] args) {
        RomanToInteger013 solution = new RomanToInteger013();
        Assert.check(solution.romanToInt("III") == 3);
        Assert.check(solution.romanToInt("IV") == 4);
        Assert.check(solution.romanToInt("IX") == 9);
        Assert.check(solution.romanToInt("LVIII") == 58);
        Assert.check(solution.romanToInt("MCMXCIV") == 1994);
    }
}
