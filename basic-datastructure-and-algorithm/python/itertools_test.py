import itertools
from collections import Counter


for i in itertools.count({1,2,3}):
    if i >= 20:
        break
    print(i)

