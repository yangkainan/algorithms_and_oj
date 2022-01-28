class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """

        max_profit_list = [0] * len(prices)
        min_buy_list = [-1] * len(prices)
        min_buy_list[0] = prices[0]
        last_sell_index = -1
        operation_list = ["REST"] * len(prices)

        for i in range(0, len(prices)):
            if min_buy_list[i - 1] >=0:
                sell_at_i_profit = prices[i] - min_buy_list[i - 1]
                if sell_at_i_profit > 0:
                    min_buy_list[i - 1] = -1
                    max_profit_list[i] = max_profit_list[i - 1] + sell_at_i_profit
                    operation_list[i - 1] = "buy"
                    operation_list[i] = "sell"
                    last_sell_index = i
                else:
                    min_buy_list[i] = min(prices[i], min_buy_list[i - 1])
                    max_profit_list[i] = max_profit_list[i - 1]
            else:
                tmp = 0
                if last_sell_index >= 0:
                    tmp = prices[i] - prices[last_sell_index]

                if tmp > 0:
                    max_profit_list[i] = max_profit_list[i - 1] + tmp
                    print("===")
                    print(operation_list)
                    if last_sell_index >= 0:
                        operation_list[last_sell_index] = "REST"
                    operation_list[i] = "sell"
                    last_sell_index = i
                    print(operation_list)
                    print("===")
                else:
                    max_profit_list[i] = max_profit_list[i - 1]
                    min_buy_list[i] = prices[i]

        print(prices)
        print(operation_list)
        print(min_buy_list)
        print(max_profit_list)
        return max_profit_list[-1]


if __name__ == '__main__':
    s = Solution()
    assert 7 == s.maxProfit([7,1,5,3,6,4])

    assert 2 == s.maxProfit([2, 1, 2, 0, 1])

    assert 7 == s.maxProfit([6, 1, 3, 2, 4, 7])

    assert 7 == s.maxProfit([7, 1, 5, 3, 6, 4])

    assert 4 == s.maxProfit([1, 2, 3, 4, 5])

    assert 0 == s.maxProfit([7, 6, 4, 3, 1])
