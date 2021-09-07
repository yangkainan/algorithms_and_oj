from typing import Any, List, Optional


class Queue:
    def __init__(self):
        self.__inner_list: List[Any] = []

    def push(self, e: Any):
        self.__inner_list.append(e)

    def peek(self) -> Optional[Any]:
        if len(self.__inner_list) > 0:
            return self.__inner_list[0]
        return None

    def pop(self) -> Optional[Any]:
        # why not use peek?  As  None means either Queue is Empty or an None Element
        if len(self.__inner_list) > 0:
            re = self.__inner_list[0]
            del self.__inner_list[0]
            return re
        return None
