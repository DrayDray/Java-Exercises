package Exercises3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TASK - noted below the code.

//My solution:
//Time complexity: 2O(n) => O(n) 
//Space complexity: O(constant number of variables) => O(1)

public class AppleStock {

	public static void main(String[] args) {
		List<Integer> stock_prices_yesterday = new ArrayList<Integer>(Arrays.asList(3, 7, 21, 5, 25, 1, 0, 30, 20));
		
		//The Arrays.asList replaces the need to add each item separately:
		//		ArrayList<Integer> stock_prices_yesterday = new ArrayList<Integer>();
		//		stock_prices_yesterday.add(3);
		//		stock_prices_yesterday.add(7);
		//		stock_prices_yesterday.add(21);
		
		try {
			System.out.println(getMaxProfit(stock_prices_yesterday));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getMaxProfit(List<Integer> stock_prices_yesterday) throws Exception {
		if(stock_prices_yesterday.size() < 2){
			throw new Exception("Cannot calculate profit with one price.");
		}
		
		//initialize values
		int largestProfit = 0;
		int minimumPrice = stock_prices_yesterday.get(0);
		int maximumPrice = stock_prices_yesterday.get(0);
	
		for (int index = 1; index < stock_prices_yesterday.size(); index++) {
			int currentStockPrice = stock_prices_yesterday.get(index);
			
			if(currentStockPrice > maximumPrice){
				maximumPrice = currentStockPrice;
				if(maximumPrice - minimumPrice > largestProfit){
					largestProfit = maximumPrice - minimumPrice;
				}
			}
			
			if(currentStockPrice < minimumPrice){
				minimumPrice = currentStockPrice;
				maximumPrice = currentStockPrice;
			}
		}
		
		//if stock prices dropped all day:
		if(largestProfit == 0){
			//find the smallest difference
			largestProfit = stock_prices_yesterday.get(1) - stock_prices_yesterday.get(0);
			for (int index = 2; index < stock_prices_yesterday.size(); index++) {
				int previousStockPrice = stock_prices_yesterday.get(index-1);
				int currentStockPrice = stock_prices_yesterday.get(index);
				if(currentStockPrice - previousStockPrice > largestProfit){
					largestProfit = currentStockPrice - previousStockPrice;
				}
			}
		}
		
		return largestProfit;
	}
}

/* TASK:
Suppose we could access yesterday's stock prices as a list, where:

The indices are the time in minutes past trade opening time, which was 9:30am local time.
The values are the price in dollars of Apple stock at that time.
So if the stock cost $500 at 10:30am, stock_prices_yesterday[60] = 500.

Write an efficient function that takes stock_prices_yesterday and returns the best profit I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.
 
For example:

stock_prices_yesterday = [10, 7, 5, 8, 11, 9]

get_max_profit(stock_prices_yesterday)
# returns 6 (buying for $5 and selling for $11)

No "shorting"—you must buy before you sell. You may not buy and sell in the same time step (at least 1 minute must pass).
 */


