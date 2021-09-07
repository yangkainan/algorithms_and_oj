import math


def exp_and_square(x, y):
    """
    计算  x^y

    """
    if x == 0:
        return 0
    if y == 0:
        return 1
    if y == 1:
        return x

    if y < 0:
        return exp_and_square(1 / x, -y)

    if y % 2 == 0:
        return exp_and_square(x * x, y / 2)
    else:
        return x * exp_and_square(x, y - 1)


if __name__ == '__main__':

    for x in range(100):
        for y in range(-100, 100):
            print(exp_and_square(x, y))
