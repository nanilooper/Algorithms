package com.practice.algorithms.graph;

public class DFS {

	private boolean[] marked; // marked[v] = is there path from s to v
	private int[] parentOf; // parentOf[v] = parent of v;
	// public int[] topo;// for topological sort
	// private int cur; // for topological sort

	public DFS(Graph G, int s) {
		marked = new boolean[G.V() + 1];
		parentOf = new int[G.V() + 1];
		validateVertex(s);
		dfs(G, s);
	}

	public DFS(Graph G) {
		marked = new boolean[G.V() + 1];
		parentOf = new int[G.V() + 1];
		// topo = new int[G.V() + 1];
		// cur = G.V();
		for (int i = 1; i <= G.V(); i++) {
			if (!marked[i]) {
				dfs(G, i);
			}
		}
	}

	private void dfs(Graph G, int s) {
		marked[s] = true;
		for (int w : G.adj(s)) {
			if (!marked[w]) {
				parentOf[w] = s;
				dfs(G, w);
			}
		}
		// topo[cur] = s;
		// cur--;
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

	public static void main(String[] args) {
		Graph g = new Graph(8);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(1, 4);
		g.addEdge(3, 4);
		g.addEdge(2, 6);
		g.addEdge(4, 5);
		g.addEdge(7, 8);
		DFS dfs = new DFS(g);
		// dfs.topo
		// System.out.println());
		System.out.println(dfs.parentOf(8));
	}

}
