package com.practice.algorithms.dp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Node implements Comparable<Node>{
	int v;
	int w;
	Node(int v,int w){
		this.v=v;
		this.w=w;
	}
	@Override
	public int compareTo(Node o) {
		if(this.w>o.w){
			return 1;
		}
		if(this.w<o.w){
			return -1;
		}
		return 0;
	}
}
public class KnapSackBig {
	private int numItems;
	private int capacity;
	private int[] values;
	private int[] weights;
	private HashMap<String, Integer> map;

	public KnapSackBig(int numItems, int capacity, int[] values, int[] weights) {
		this.numItems = numItems;
		this.capacity = capacity;
		this.values = values;
		this.weights = weights;
		map = new HashMap<>();
		for (int j = 0; j <= capacity; j++) {
			//String s0 = "0" + "xyz" + j;
			String s1 = "1" + "xyz" + j;
			//map.put(s0, 0L);
			if (values[1] > j) {
				map.put(s1, 0);
			} else {
				map.put(s1, values[1]);
			}
		}
	}

	public int maxValue(int i, int c) {
		int ans;
		String key = String.valueOf(i) + "xyz" + String.valueOf(c);
		if (map.containsKey(key)) {
			ans = map.get(key);
		} else {
			//long val;
			int ans1 = maxValue(i-1,c);
			ans = ans1;
			if (c - weights[i] >= 0) {
				int val = maxValue(i - 1, c - weights[i]) + values[i];
				ans = Math.max(ans, val);
			}		
			map.put(key, ans);
		}

		return ans;
	}

	public long max() {
		return maxValue(numItems, capacity);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("KnapSackSmall.txt"));
		int capacity = scan.nextInt();
		int numItems = scan.nextInt();
		int[] values = new int[numItems + 1];
		int[] weights = new int[numItems + 1];
		List<Node> list  = new ArrayList<Node>();
		for (int i = 0; i < numItems; i++) {
			list.add(new Node(scan.nextInt(),scan.nextInt()));
		}
		scan.close();
		Collections.sort(list);
		for(int i = 1 ; i<list.size();i++){
			values[i] = list.get(i-1).v;
			weights[i] = list.get(i-1).w;
		}
		System.out.println(values[1]);
		System.out.println(weights[1]);
		KnapSackBig ks = new KnapSackBig(numItems, capacity, values, weights);
		System.out.println(ks.max());

	}

}
