class Solution(object):
    def __init__(self):
        self._cache = {}

    def numTrees(self, n):
        """
        :type n: int
        :rtype: int
        """
        return self.makeTree([e for e in range(n)])

    def _makeKey(self, candidates):
        return "_".join([str(e) for e in candidates])

    def makeTree(self, candidates):
        """
        :param candidates:  List[int]  allowed elements
        :return: int number of different tree
        """
        key = self._makeKey(candidates)
        if key in self._cache:
            return self._cache[key]

        num_of_trees = 0

        for root in candidates:
            left_candidates = [e for e in candidates if e < root]
            right_candidates = [e for e in candidates if e > root]
            num_left_trees = self.makeTree(left_candidates)
            num_right_trees = self.makeTree(right_candidates)
            if num_left_trees == 0:
                num_left_trees = 1
            if num_right_trees == 0:
                num_right_trees = 1

            num_of_trees += num_left_trees * num_right_trees

        self._cache[key] = num_of_trees

        return num_of_trees


if __name__ == '__main__':
    s = Solution()
    assert s.numTrees(3) == 5
    assert s.numTrees(0) == 0
    assert s.numTrees(1) == 1
    assert s.numTrees(2) == 2
