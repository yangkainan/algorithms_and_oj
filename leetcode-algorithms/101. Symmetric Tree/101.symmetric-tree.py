# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def __str__(self):
        return self.val

    def __repr__(self):
        return self.val

class Solution(object):
    def isSymmetric(self, root):
        if root is None:
            return True
        return self._is_equal(root.left, root.right)

    def _is_equal(self, l, r):

        if l is None and r is None:
            return True

        if l is None or r is None:
            return False

        if l.val != r.val:
            return False

        return self._is_equal(l.left, r.right) and self._is_equal(l.right, r.left)





    def _isSymmetric(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        if root is None:
            return True

        # _order_list = self._mid_root_order(root)
        _level_order = self._level_order(root)
        print(_level_order)
        for _order_list in _level_order:
            if not self._is_symmetric_list(_order_list):
                return False
        return True

    # def _mid_root_order(self, root):
    #     if root is None:
    #         return [None]
    #     if root.left is None and root.right is None:
    #         return [root.val]
    #     return self._mid_root_order(root.left) + [root.val] + self._mid_root_order(root.right)

    def _level_order(self, root):
        level_order_list = []
        _queue = [root]
        _level_len = len(_queue)
        _cur_level = []
        level_order_list.append(_cur_level)
        while len(_queue) > 0:
            _cur = _queue[0]
            _queue = _queue[1:]

            if _cur is not None:
                _cur_level.append(_cur.val)
            else:
                _cur_level.append(None)

            _level_len -= 1

            if _cur is not None:
                _queue.append(_cur.left)
                _queue.append(_cur.right)

            if _level_len == 0:
                _cur_level = []
                _level_len = len(_queue)
                level_order_list.append(_cur_level)

        return level_order_list




    def _is_symmetric_list(self, order_list):
        if len(order_list) <= 1:
            return True

        left = order_list[0]
        right = order_list[-1]

        if (left is None and right is None) or (left == right):
            return self._is_symmetric_list(order_list[1:len(order_list)-1])
        else:
            return False


if __name__ == '__main__':
    s = Solution()
    root1 = TreeNode(1, TreeNode(2, TreeNode(3, TreeNode(5), None), TreeNode(4, TreeNode(6), None)), TreeNode(2, TreeNode(4, None, TreeNode(6)), TreeNode(3, None, TreeNode(5))))
    assert True == s.isSymmetric(root1)

    root2 = TreeNode(1, TreeNode(2,None, TreeNode(3)), TreeNode(2, None, TreeNode(3)))
    assert False == s.isSymmetric(root2)

    root3 = TreeNode(1, TreeNode(2, TreeNode(2), None), TreeNode(2, TreeNode(2), None))
    assert False == s.isSymmetric(root3)

    root4 = TreeNode(5, TreeNode(4, None, TreeNode(1, TreeNode(2), None)), TreeNode(1, None, TreeNode(4, TreeNode(2), None)))
    assert False == s.isSymmetric(root4)

    root5= TreeNode(2, TreeNode(3, TreeNode(4), TreeNode(5,TreeNode(8), TreeNode(9))), TreeNode(3, TreeNode(5), TreeNode(4, TreeNode(9), TreeNode(8))))
    assert False == s.isSymmetric(root5)
