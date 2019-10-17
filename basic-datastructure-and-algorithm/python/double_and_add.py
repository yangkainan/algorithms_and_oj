
def double_and_add(x, y):
    if x == 0 or y == 0:
        return 0
    if x == 1:
        return y
    if y == 1:
        return x

    if x % 2 == 0:
        return double_and_add(x / 2, y + y)
    else:
        return y + double_and_add(x - 1, y)


def double_and_add_opt(x, y, acc):
    """
    尾递归优化
    """
    if x == 0 or y == 0:
        return acc
    if x == 1:
        return y + acc
    if y == 1:
        return x + acc

    if x % 2 == 0:
        return double_and_add_opt(x / 2, y + y, acc)
    else:
        return double_and_add_opt(x - 1, y, acc + y)


def test_double_and_add():
    for x in range(100000):
        for y in range(100000):
            assert x * y == double_and_add(x, y)


def test_double_and_add_opt():
    for x in range(100000):
        for y in range(100000):
            assert x * y == double_and_add_opt(x, y, 0)


if __name__ == "__main__":
    import timeit

    setup = "from __main__ import test_double_and_add"
    print(timeit.timeit("test_double_and_add", setup))
    setup = "from __main__ import test_double_and_add_opt"
    print(timeit.timeit("test_double_and_add_opt", setup))
