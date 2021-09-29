# Definition for a binary tree node.
class Solution(object):
    def zigzagLevelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        result = []
        if root is None:
            return result
        _queue = [root]

        _cur_level = []
        _cur_level_node_left = len(_queue)
        _level_order = 1

        while len(_queue) > 0:
            _cur_node = _queue.pop(0)
            _cur_level.append(_cur_node.val)
            _cur_level_node_left -= 1

            if _cur_node.left is not None:
                _queue.append(_cur_node.left)

            if _cur_node.right is not None:
                _queue.append(_cur_node.right)

            if _cur_level_node_left == 0:
                if _level_order % 2 == 0:
                    _cur_level.reverse()
                result.append(_cur_level)

                _cur_level = []
                _cur_level_node_left = len(_queue)
                _level_order += 1

        return result




class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

if __name__ == "__main__":
    s = Solution()
    root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
    print(s.zigzagLevelOrder(root))