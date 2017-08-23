package com.practice.algorithms.dp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class KnapSackBig {
	private int numItems;
	private int capacity;
	private long[] values;
	private int[] weights;
	private HashMap<String, Long> map;

	public KnapSackBig(int numItems, int capacity, long[] values, int[] weights) {
		this.numItems = numItems;
		this.capacity = capacity;
		this.values = values;
		this.weights = weights;
		map = new HashMap<>();
		for (int j = 0; j <= capacity; j++) {
			String s0 = "0" + "x" + j;
			String s1 = "1" + "x" + j;
			map.put(s0, 0L);
			if (values[1] > j) {
				map.put(s1, 0L);
			} else {
				map.put(s1, values[1]);
			}
		}
	}

	public long maxValue(int i, int c) {
		long ans;
		String key = String.valueOf(i) + "x" + String.valueOf(c);
		if (map.containsKey(key)) {
			ans = map.get(key);
		} else {
			long val;
			if (c - weights[i] >= 0) {
				val = maxValue(i - 1, c - weights[i]) + values[i];
			} else {
				val = Long.MIN_VALUE;
			}
			ans = Math.max(maxValue(i - 1, c), val);
			map.put(key, ans);
		}

		return ans;
	}

	public long max() {
		return maxValue(numItems, capacity);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("KnapSackBig.txt"));
		int capacity = scan.nextInt();
		int numItems = scan.nextInt();
		long[] values = new long[numItems + 1];
		int[] weights = new int[numItems + 1];
		for (int i = 1; i <= numItems; i++) {
			values[i] = scan.nextLong();
			weights[i] = scan.nextInt();
		}
		scan.close();
		KnapSackBig ks = new KnapSackBig(numItems, capacity, values, weights);
		System.out.println(ks.max());

	}

}
