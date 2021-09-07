from __future__ import annotations
from typing import List, Optional, Dict


class Node:
    _val: int
    _next_node: Node

    def __init__(self, val: int):
        self._val = val

    def __eq__(self, other: Node):
        if other is None:
            return False
        return other._val == self._val

    def __hash__(self):
        return hash(self._val)

    def __repr__(self):
        return self.__str__()

    def __str__(self):
        return str(self._val)


class Edge:
    _from: Node
    _to: Node
    _weight: int

    def __init__(self, f: Node, t: Node, w: int = -1):
        self._from = f
        self._to = t
        self._weight = w

    def get_from(self):
        return self._from

    def get_to(self):
        return self._to

    def __str__(self):
        return "{} -[{}]-> {}".format(self._from, self._weight, self._to)

    def __repr__(self):
        return self.__str__()


class Graph:
    _all_nodes: Dict[Node, List[Node]]
    _all_edges: List[Edge]
    _directed: bool

    def __init__(self, is_directed: bool = False):
        self._all_nodes = {}
        self._directed = is_directed
        self._all_edges = []

    def add_node(self, n: Node):
        if n not in self._all_nodes:
            self._all_nodes[n] = []

    def add_edge(self, e: Edge):
        self._all_edges.append(e)
        f = e.get_from()
        t = e.get_to()
        self.add_node(f)
        self.add_node(t)
        self._all_nodes[f].append(t)
        if not self._directed:
            self._all_nodes[t].append(f)

    def add_raw_edge(self, from_val: int, to_val: int):
        f = Node(from_val)
        t = Node(to_val)
        e = Edge(f, t)
        self.add_edge(e)

    def get_connected_node_list(self, s: Node) -> List[Node]:
        return self._all_nodes[s]

    def __process_edge(self, s: Node, e: Node):
        if s is None or e is None:
            return
        print(Edge(s, e))

    def __dfs(self, s: Node, discovered: List[Node], process: List[Node], parent: Dict[Node, Node]):
        ...

    def dfs(self, s: Node):
        discovered = [s]
        parent = {}
        processed = []

        while len(discovered) > 0:
            cur = discovered.pop(-1)
            for n in self.get_connected_node_list(cur):
                if n not in processed and n not in discovered:
                    discovered.append(n)
                    parent[n] = cur
            processed.append(cur)
            if cur in parent:
                self.__process_edge(parent[cur], cur)

        print(processed)

    def bfs(self, s: Node):
        discovered = [s]
        parent = {}
        processed = []
        while len(discovered) > 0:
            c = discovered.pop(0)
            processed.append(c)

            for n in self.get_connected_node_list(c):
                if n not in discovered and n not in processed:
                    discovered.append(n)
                    parent[n] = c
                    self.__process_edge(c, n)
        print(processed)
        print(parent)

    def find_cycle(self, s:Node) -> bool:
        discovered = [s]
        processed = []
        while len(discovered) > 0:
            c = discovered.pop(0)
            processed.append(c)
            for n in self.get_connected_node_list(c):
                if n in discovered or n in processed:
                    print("Found Cycle ", n)
                    return True
                else:
                    discovered.append(n)

        return False

    def minimum_span_tree(self):
        ...

    def find_shortest_path(self, s: Node):
        ...

    def prim_minimum_span_tree(self):
        ...

    def dijkstra_shortest_path(self, s: Node):
        ...


if __name__ == '__main__':
    g = Graph()
    g.add_raw_edge(1, 2)
    g.add_raw_edge(2, 3)
    g.add_raw_edge(1, 3)
    g.add_raw_edge(3, 4)
    g.add_raw_edge(4, 5)
    # g.dfs(Node(1))
    # g.bfs(Node(1))

    g.add_raw_edge(3, 1)
    g.find_cycle(Node(1))

