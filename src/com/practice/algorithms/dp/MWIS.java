package com.practice.algorithms.dp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MWIS {
	private int[] weights;
	private long[] dp;
	public List<Integer> mwisList;

	public MWIS(Scanner scan) {
		int numVertices = scan.nextInt();
		weights = new int[numVertices + 1];
		dp = new long[numVertices + 1];
		for (int i = 1; i <= numVertices; i++) {
			weights[i] = scan.nextInt();
		}
		dp[1] = Math.max(weights[1], 0);
		dp[2] = Math.max(weights[1], weights[2]);
		for (int i = 3; i <= numVertices; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + weights[i]);
		}
	}

	public List<Integer> findmwisList() {
		List<Integer> list = new ArrayList<>();
		int i = dp.length - 1;
		while (i > 0) {
			if (dp[i] > dp[i - 1]) {
				list.add(i);
				i -= 2;
			} else {
				i -= 1;
			}
		}
		return list;
	}

	public static void main(String[] args) throws FileNotFoundException {
		MWIS mwis = new MWIS(new Scanner(new File("MWIS.txt")));
		System.out.println(mwis.findmwisList());
	}
}
