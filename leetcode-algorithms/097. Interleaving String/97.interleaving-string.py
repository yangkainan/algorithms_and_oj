class Solution(object):

    def _make_key(self, s1, s2, s3):
        return "_".join([s1, s2, s3])

    def __init__(self):
        self._cache = {}

    def isInterleave(self, s1, s2, s3):
        """
        :type s1: str
        :type s2: str
        :type s3: str
        :rtype: bool
        """
        if len(s1) + len(s2) != len(s3):
            return False

        return self._is_interleave_in_order(s1, s2, s3) or self._is_interleave_in_order(s2, s1, s3)

    def _is_interleave_in_order(self, s1, s2, s3):

        key = self._make_key(s1, s2, s3)
        if key in self._cache:
            return self._cache[key]

        if s1 == "":
            if s2 == s3:
                self._cache[key] = True
                return True
            else:
                self._cache[key] = False
                return False

        if s3 == "":
            self._cache[key] = False
            return False

        for index in reversed(range(1, len(s1) + 1)):
            if s3.startswith(s1[:index]) and self._is_interleave_in_order(s2, s1[index:], s3[index:]):
                self._cache[key] = True
                return True

        self._cache[key] = False
        return False


if __name__ == '__main__':
    s = Solution()

    assert s.isInterleave("", "", "")
    assert s.isInterleave("aa", "ab", "abaa")
    assert not s.isInterleave("aabcc", "dbbca", "aadbbbaccc")
    assert s.isInterleave("aabcc", "dbbca", "aadbbcbcac")
    assert not s.isInterleave("bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa", "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab", "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab")
