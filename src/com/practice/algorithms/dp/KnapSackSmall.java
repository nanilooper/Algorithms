package com.practice.algorithms.dp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KnapSackSmall {
	private int numItems;
	private int capacity;
	private int[][] dp;

	public KnapSackSmall(int numItems, int[] values, int[] weights, int capacity) {
		this.numItems = numItems;
		this.capacity = capacity;
		dp = new int[numItems + 1][capacity + 1];
		for (int j = 0; j <= capacity; j++) {
			dp[0][j] = 0;
			if (values[1] > j) {
				dp[1][j] = 0;
			} else {
				dp[1][j] = values[1];
			}
		}
		for (int i = 2; i <= numItems; i++) {
			for (int j = 0; j <= capacity; j++) {
				int val;
				if (j - weights[i] >= 0) {
					val = dp[i - 1][j - weights[i]];
				} else {
					val = Integer.MIN_VALUE;
				}
				dp[i][j] = Math.max(dp[i - 1][j], val + values[i]);
			}
		}
	}

	public int getMaxValue() {
		return dp[numItems][capacity];
	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File("KnapSackSmall.txt"));
		int capacity = scan.nextInt();
		int numItems = scan.nextInt();
		int[] values = new int[numItems + 1];
		int[] weights = new int[numItems + 1];
		for (int i = 1; i <= numItems; i++) {
			values[i] = scan.nextInt();
			weights[i] = scan.nextInt();
		}
		scan.close();
		KnapSackSmall ks = new KnapSackSmall(numItems, values, weights, capacity);
		System.out.println(ks.getMaxValue());

	}

}
