package com.practice.algorithms.graph;

import java.util.Arrays;

public class DFS {

	private boolean[] marked; // marked[v] = is there path from s to v
	private int[] parentOf; // parentOf[v] = parent of v;

	public DFS(Graph G, int s) {
		marked = new boolean[G.V() + 1];
		parentOf = new int[G.V() + 1];
		validateVertex(s);
		dfs(G, s);
	}

	public DFS(Graph G) {
		marked = new boolean[G.V() + 1];
		parentOf = new int[G.V() + 1];
		for (int i = 1; i<=G.V(); i++) {
			if (!marked[i]) {
				dfs(G, i);
			}
		}
	}

	private void dfs(Graph G, int s) {
		marked[s] = true;
		for(int w : G.adj(s)) {
			if (!marked[w]) {
				parentOf[w] = s;
				dfs(G, w);
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public int parentOf(int v) {
		return parentOf[v];
	}

	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 1 || v >= V) {
			throw new IllegalArgumentException("vertex should be between 1 and V");
		}
	}

}
