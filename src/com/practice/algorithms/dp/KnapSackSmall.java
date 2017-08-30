package com.practice.algorithms.dp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
//class Node implements Comparable<Node>{
//	int v;
//	int w;
//	Node(int v,int w){
//		this.v=v;
//		this.w=w;
//	}
//	@Override
//	public int compareTo(Node o) {
//		if(this.w>o.w){
//			return 1;
//		}
//		if(this.w<o.w){
//			return -1;
//		}
//		return 0;
//	}
//}
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
		
//		
//		Scanner scan = new Scanner(new File("KnapSackSmall.txt"));
//		int capacity = scan.nextInt();
//		int numItems = scan.nextInt();
//		int[] values = new int[numItems + 1];
//		int[] weights = new int[numItems + 1];
//		List<Node> list  = new ArrayList<Node>();
//		for (int i = 0; i < numItems; i++) {
//			list.add(new Node(scan.nextInt(),scan.nextInt()));
//		}
//		scan.close();
//		Collections.sort(list);
//		for(int i = 1 ; i<list.size();i++){
//			values[i] = list.get(i-1).v;
//			weights[i] = list.get(i-1).w;
//		}
//		KnapSackSmall ks = new KnapSackSmall(numItems, values, weights, capacity);
//		System.out.println(ks.getMaxValue());

	}

}
