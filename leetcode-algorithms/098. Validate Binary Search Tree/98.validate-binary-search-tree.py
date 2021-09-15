# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution(object):
    def isValidBST(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        if root is None:
            return True
        _min = - (2**31) -1
        _max = 2 ** 31

        return self._is_valid_bst(_min, _max, root)

    def _is_valid_bst(self, _min, _max, _root):
        if _root is None:
            return True

        if _root.val <= _min or _root.val >= _max:
            return False

        left_child_max = min(_max, _root.val)
        right_child_min = max(_min, _root.val)

        return self._is_valid_bst(_min, left_child_max, _root.left) and self._is_valid_bst(right_child_min, _max, _root.right)


if __name__ == '__main__':
    s = Solution()
    root = TreeNode(1, TreeNode(2), TreeNode(3))
    assert not s.isValidBST(root)

    root = TreeNode(2, TreeNode(1), TreeNode(3))
    assert s.isValidBST(root)

    assert s.isValidBST(None)

    root = TreeNode(5, TreeNode(1), TreeNode(4, TreeNode(3), TreeNode(6)))
    assert not s.isValidBST(root)

    root = TreeNode(5, TreeNode(4), TreeNode(6, TreeNode(3), TreeNode(7)))
    assert not s.isValidBST(root)

    assert s.isValidBST(TreeNode(2147483647))
