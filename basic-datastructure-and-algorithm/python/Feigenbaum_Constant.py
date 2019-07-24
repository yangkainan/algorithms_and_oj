'''
https://blog.stephenwolfram.com/2019/07/mitchell-feigenbaum-1944-2019-4-66920160910299067185320382/
'''

import numpy as np
import matplotlib.pyplot as plt


xrange = 200
xlist = np.arange(1, xrange, 1, int)

ylist = []

y = 1/3
a = 3.4

for i in xlist:
    y = a * y * (1 - y)
    ylist.append(y)


plt.plot(xlist,ylist)

plt.show()

