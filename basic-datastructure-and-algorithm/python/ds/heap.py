from typing import List, Optional


class MinHeap:
    """
    index from 1 in order to simplify order calculation
    """
    __ele_array: List[int]
    __ele_cnt: int

    def __init__(self):
        self.__ele_array = [None]
        self.__ele_cnt = len(self.__ele_array) - 1

    def get_parent_index(self, cur_index: int):
        if cur_index <= 1:
            return None
        else:
            return cur_index // 2

    def get_left_child(self, cur_index: int) -> Optional[int]:
        child_index = cur_index * 2
        if child_index >= self.__ele_cnt:
            return None
        return child_index

    def get_right_child_index(self, cur_index: int) -> Optional[int]:
        left_child_index = self.get_left_child(cur_index)
        if (left_child_index is None) or (left_child_index >= self.__ele_cnt):
            return None
        else:
            return left_child_index + 1

    def insert(self, e) -> None:
        self.__ele_array.append(e)
        self.__ele_cnt += 1
        self.__bubble_up(self.__ele_cnt)

    def __bubble_up(self, cur_index: int) -> None:
        p_index = self.get_parent_index(cur_index)
        if p_index is None:
            return

        if self.__ele_array[p_index] > self.__ele_array[cur_index]:
            self.__swap(p_index, cur_index)

        return self.__bubble_up(p_index)

    def extract_min(self) -> Optional[int]:
        print(self.__ele_array)
        if self.__ele_cnt < 1:
            return None

        _min = self.__ele_array[1]

        self.__ele_array[1] = self.__ele_array[self.__ele_cnt]
        del self.__ele_array[self.__ele_cnt]
        self.__ele_cnt -= 1

        self.__bubble_down(1)

        return _min

    def __swap(self, l, r: int) -> None:
        self.__ele_array[l], self.__ele_array[r] = \
            self.__ele_array[r], self.__ele_array[l]

    def __bubble_down(self, cur_index: int) -> None:
        l_index = self.get_left_child(cur_index)
        if l_index is None or l_index > self.__ele_cnt:
            return None

        r_index = self.get_right_child_index(cur_index)

        if r_index is None or r_index > self.__ele_cnt or self.__ele_array[l_index] <= self.__ele_array[r_index]:
            if self.__ele_array[l_index] < self.__ele_array[cur_index]:
                self.__swap(l_index, cur_index)
                return self.__bubble_down(l_index)

        if r_index is None or r_index > self.__ele_cnt:
            return None

        if self.__ele_array[r_index] < self.__ele_array[cur_index]:
            self.__swap(r_index, cur_index)
            return self.__bubble_down(r_index)

        return None


def make_min_heap(elems: List[int]) -> MinHeap:
    heap = MinHeap()
    for e in elems:
        heap.insert(e)
    return heap


def heap_sort(elems: List[int]) -> List[int]:
    minHeap = make_min_heap(elems)
    res = []
    for i in range(len(elems)):
        res.append(minHeap.extract_min())
    return res


if __name__ == '__main__':
    print(heap_sort([3, 2, 1, 5, 4, 6, 9, 8, 7]))
