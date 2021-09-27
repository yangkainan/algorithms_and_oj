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
    def levelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        _level_order_list = []
        if root is None:
            return _level_order_list

        _queue = [root]

        _cur_level_length_left = len(_queue)
        _cur_level_order = []

        _level_order_list.append(_cur_level_order)

        while len(_queue) > 0:
            _cur_node = _queue.pop(0)
            _cur_level_order.append(_cur_node.val)
            _cur_level_length_left -= 1

            if _cur_node.left is not None:
                _queue.append(_cur_node.left)

            if _cur_node.right is not None:
                _queue.append(_cur_node.right)

            if _cur_level_length_left == 0:
                _cur_level_order = []
                _cur_level_length_left = len(_queue)
                _level_order_list.append(_cur_level_order)

        # remove last empty list
        _level_order_list.pop()

        return _level_order_list


if __name__ == '__main__':
    s = Solution()
    root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))

    print(s.levelOrder(root))
