package main

type TreeNode struct {
	Value int
	left *TreeNode
	right *TreeNode
}


func FindSibling(root *TreeNode, current *TreeNode, target *TreeNode) *TreeNode {
	if current == nil || target == nil {
		return nil
	}

	if current == target {
		return nil
	}



	switch target {
	case current.left:
		if current.right != nil {
			return current.right
		}

		for siblingOfCurrent := FindSibling(root, root, current); siblingOfCurrent != nil; siblingOfCurrent = FindSibling(root, root, siblingOfCurrent) {
			if siblingOfCurrent == nil {
				return nil
			}

			if siblingOfCurrent.left != nil {
				return siblingOfCurrent.left
			}

			if siblingOfCurrent.right != nil {
				return siblingOfCurrent.right
			}
		}

		return nil


	case current.right:
		for siblingOfCurrent := FindSibling(root, root, current); siblingOfCurrent != nil; siblingOfCurrent = FindSibling(root, root, siblingOfCurrent) {
			if siblingOfCurrent == nil {
				return nil
			}

			if siblingOfCurrent.left != nil {
				return siblingOfCurrent.left
			}

			if siblingOfCurrent.right != nil {
				return siblingOfCurrent.right
			}
		}

		return nil


	default:
		result := FindSibling(root, current.left, target)
		if result != nil {
			return result
		} else {
			return FindSibling(root, current.right, target)
		}
	}
}