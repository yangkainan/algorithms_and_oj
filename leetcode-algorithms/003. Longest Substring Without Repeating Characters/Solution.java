class Solution {
    public int lengthOfLongestSubstring(String s) {
         if (s == null ||s.length() == 0)
            return 0;

        int startPos = 0;
        int endPos = 0;
        HashSet<String> occurence = new HashSet<>();
        int maxLength = 0;

        int totalLength = s.length();

        while(startPos < totalLength) {
            endPos = startPos;
            while (endPos < totalLength) {
                char c = s.charAt(endPos);
                if (occurence.contains(String.valueOf(c))) {
                    int length = s.substring(startPos, endPos).length();
                    if (length > maxLength) {
                        maxLength = length;
                    }
                    startPos ++;
                    occurence.clear();
                    break;
                } else {
                    occurence.add(String.valueOf(c));
                    endPos ++;
                }

            }

            if (endPos == totalLength) {
                int length = s.substring(startPos).length();
                if (length > maxLength) {
                    maxLength = length;
                }
            }
        }

        return maxLength;
    }
}
