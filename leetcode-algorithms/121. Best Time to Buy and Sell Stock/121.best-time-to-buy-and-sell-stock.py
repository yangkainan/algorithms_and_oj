class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        result = 0

        if len(prices) == 0:
            return result

        min_price_list_since = [0]*len(prices)
        min_prices = prices[0]
        for i in range(len(prices)):
            if prices[i] <= min_prices:
                min_prices = prices[i]
            min_price_list_since[i] = min_prices

        max_profit_list_since = [0]*len(prices)

        for i in range(1, len(prices)):
            _sell_profit = prices[i] - min_price_list_since[i-1]
            max_profit_list_since[i] = max(max_profit_list_since[i-1],_sell_profit)

        return max_profit_list_since[-1]


if __name__ == '__main__':
    s = Solution()

    assert 0 == s.maxProfit([7, 6, 5, 4, 3, 2, 1])

    assert 5 == s.maxProfit([7, 1, 5, 3, 6, 4])
