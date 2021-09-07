import sys
from typing import List


class UnionFind(object):
    weight: List[int]
    fa: List[int]
    MAXN: int = 100

    def __init__(self):
        for i in range(self.MAXN):
            self.fa[i] = i
            self.weight[i] = 1

    def find(self, x: int) -> int:
        if self.fa[x] == x:
            return x
        else:
            # 路径压缩. 把所有节点的 Parent 都设置成根节点
            self.fa[x] = self.find(self.fa[x])
            return self.fa[x]

    def merge(self, x: int, y: int) -> None:
        xP = self.find(x)
        yP = self.find(y)
        self.fa[self.find[x]] = self.find(y)
