from __future__ import annotations
from enum import Enum


class Color(Enum):
    RED: int = 1
    BLACK: int = 2


root: Node


class Node:
    parent: Node
    left: Node
    right: Node
    color: Color
    val: int

    def __init__(self, val: int, color: Color, parent: Node = None, left: Node = None, right: Node = None):
        self.val = val
        self.color = color
        self.parent = parent
        self.left = left
        self.right = right

    def get_grand_parent(self):
        if self.parent is None:
            return None

        return self.parent.parent

    def get_uncle(self):
        gp = self.get_grand_parent()
        if gp is None:
            return None

        if gp.left == self.parent:
            return gp.right
        else:
            return gp.left

    def get_sibling(self):
        if self.parent is None:
            return None

        if self.parent.left == self:
            return self.parent.right
        else:
            return self.parent.left

    def rotate_right(self):
        if self is None:
            return

        p = self.parent
        gp = self.get_grand_parent()
        n = self

        if p is None:
            return

        n.parent = gp
        if gp is not None:
            if gp.left == p:
                gp.left = n
            else:
                gp.right = n

        p.left = n.right

        if n.right is not None:
            n.right.parent = p

        n.right = p
        p.parent = n

        # if root == p:
        #     root = n

    def rotate_left(self):
        if self is None:
            return
        p = self.parent
        gp = self.get_grand_parent()
        n = self
        if p is None:
            return

        n.parent = gp
        if gp is not None:
            if gp.left == p:
                gp.left = n
            else:
                gp.right = n

        p.right = n.left
        if n.left is not None:
            n.left.parent = p

        n.left = p
        p.parent = n

        # if root == p:
        #     root =n
