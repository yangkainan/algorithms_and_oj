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
    def recoverTree(self, root):
        """
        :type root: TreeNode
        :rtype: None Do not return anything, modify root in-place instead.
        """
        if root is None:
            return root

        _in_order = [n for n in Solution.mid_root_order(root) if n is not None]

        if len(_in_order) < 2:
            return root

        _swap_left_index = 0
        for i in range(1, len(_in_order)):
            if _in_order[i].val < _in_order[_swap_left_index].val:
                break
            else:
                _swap_left_index = i

        _swap_right_index = _swap_left_index + 1
        for j in range(_swap_right_index + 1, len(_in_order)):
            if _in_order[j].val > _in_order[_swap_right_index].val:
                break
            else:
                _swap_right_index = j

        left = _in_order[_swap_left_index]
        right = _in_order[_swap_right_index]

        return Solution._swap_2_nodes(root, left, right)

    @staticmethod
    def _swap_2_nodes(root, left, right):
        Solution.print_list_nodes(Solution.mid_root_order(root))

        nodes = [root]
        parent_of_left_left = None
        parent_of_left_right = None

        parent_of_right_left = None
        parent_of_right_right = None
        for current_root in nodes:
            if current_root.left == left:
                parent_of_left_left = current_root
            if current_root.right == left:
                parent_of_left_right = current_root

            if current_root.left == right:
                parent_of_right_left = current_root
            if current_root.right == right:
                parent_of_right_right = current_root

            if current_root.left is not None:
                nodes.append(current_root.left)
            if current_root.right is not None:
                nodes.append(current_root.right)

        if left != root:
            if parent_of_left_left is not None:
                parent_of_left_left.left = right
            if parent_of_left_right is not None:
                parent_of_left_right.right = right

            if right != root:
                if parent_of_right_left is not None:
                    parent_of_right_left.left = left
                if parent_of_right_right is not None:
                    parent_of_right_right.right = left
            else:
                root = left

        else:
            root = right
            if parent_of_right_left is not None:
                parent_of_right_left.left = left
            if parent_of_right_right is not None:
                parent_of_right_right.right = left

        left.left, left.right, right.left, right.right = right.left, right.right, left.left, left.right

        Solution.print_list_nodes(Solution.mid_root_order(root))

        return root


    @staticmethod
    def mid_root_order(root):
        order_list = []
        if root is None:
            return [None]

        left_child_order_list = Solution.mid_root_order(root.left)
        right_child_order_list = Solution.mid_root_order(root.right)
        return left_child_order_list + [root] + right_child_order_list

    @staticmethod
    def level_order(root):
        order_list = []
        _queue = [root]
        for n in _queue:
            if n is None:
                order_list.append(None)
            else:
                order_list.append(n)
                _queue.append(n.left)
                _queue.append(n.right)

        return order_list


    @staticmethod
    def print_list_nodes(nodes):
        if nodes is None:
            print("[]")
        else:
            print([n.val if n is not None else "Null" for n in nodes])

if __name__ == '__main__':
    s = Solution()
    # root1 = TreeNode(3, TreeNode(1), TreeNode(4, TreeNode(2)))
    #
    # r1 = s.recoverTree(root1)
    # s.print_list_nodes(Solution.mid_root_order(r1))
    root2 = TreeNode(1, TreeNode(3, None, TreeNode(2)))
    # Solution.print_list_nodes(Solution.mid_root_order(s.recoverTree(root2)))
    Solution.print_list_nodes(Solution.level_order(root2))
    Solution.print_list_nodes(Solution.level_order(s.recoverTree(root2)))

