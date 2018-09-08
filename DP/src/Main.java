import java.util.ArrayList;
import java.util.List;

public class Main {

	
	public static void main(String[] args) throws Exception {
		System.out.println("hi");
		
		testMaxProfit();
		
		testLongestCommonSubsequence();
	}
	
	private static void testLongestCommonSubsequence() {
		// LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
		//LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
		char[] seqA = {'a', 'b', 'c', 'd', 'g', 'h'};
		char[] seqB = {'a', 'e', 'd', 'f', 'h', 'r'};
		longestCommonSubsequence(seqA, seqB);
		System.out.println(" Expected: ADH");
		
//		char[] seqA_2 = {'a', 'g', 'g', 't', 'a', 'b'};
//		char[] seqB_2 = {'g', 'x', 't', 'x', 'a', 'y', 'b'};
//		longestCommonSubsequence(seqA_2, seqB_2);
//		System.out.println(" Expected: GTAB");
		
	}

	private static void longestCommonSubsequence(char[] seqA, char[] seqB) {
		// Current subseq is taking current A and checking if also in B.
		// Then check if longer than existing subsequence
		
		List<Character> currentSeq = new ArrayList<Character>();
		int lcsLen = 0;
		int lcsStart = 0;
		int lcsEnd = 0;
		
		for (int i = 0; i < seqA.length; i++) {
			
		}
		
	}

	private static void testMaxProfit() throws Exception {
		int[] profits = {9, 2, 3, 8, 5, 7, 3};
		maxProfit(profits);
		System.out.println(" (Expected: 1,3)");
		
		int[] profits2 = {2, 3, 1, 8, 5, 7, 9};
		maxProfit(profits2);
		System.out.println(" (Expected: 2,6)");
		
		int[] profits3 = {1, 3, 1, 8, 6, 7, 9};
		maxProfit(profits3);
		System.out.println(" (Expected: 0,6)");
	}

	/*
	 * Stock prices:
	 * [9 2 3 8 5 8 3]
	 * 
	 * Find buy/sell dates with max profit.
	 */
	private static void maxProfit(int[] prices) throws Exception {
		// Keep track of minprice, since current - minprice is potential at current.
		// Also keep track of: bought/sell dates and maxprofit
		int maxProfit = 0;
		int buyDate = 0;
		int sellDate = 0;
		int minDate = 0;
		int minPrice = prices[0];
		
		int currentProfit;
		for (int i = 1; i < prices.length; i++) {
			// Check current - minprice
			currentProfit = prices[i] - minPrice;
			if (currentProfit > maxProfit) {
				maxProfit = currentProfit;
				buyDate = minDate;
				sellDate = i;
			}
			
			// Update minprice if needed
			if (prices[i] < minPrice) {
				minPrice = prices[i];
				minDate = i;
			}
		}
		
		System.out.print("Buy: " + buyDate + " Sell: " + sellDate);
	}
	
	
	
}
