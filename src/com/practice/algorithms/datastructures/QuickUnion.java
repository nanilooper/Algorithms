package com.practice.algorithms.datastructures;

public class QuickUnion {

	private int[] id;
	private int[] size;

	public QuickUnion(int N) {
		id = new int[N + 1];
		size = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			id[i] = i;
			size[i] = 1;
		}

	}

	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if (i == j) {
			return;
		}
		if (size[i] >= size[j]) {
			id[j] = i;
			size[i] += size[j];
		} else {
			id[i] = j;
			size[j] += size[i];
		}
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	private int root(int i) {
		while (id[i] != i) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}

	public static void main(String[] args) {
		QuickUnion qu = new QuickUnion(10);
		qu.union(1, 2);
		qu.union(10, 2);
		qu.union(10, 7);
		qu.union(7, 6);
		System.out.println(qu.root(6));
	}

}
