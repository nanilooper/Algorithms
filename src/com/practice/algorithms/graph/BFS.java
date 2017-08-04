package com.practice.algorithms.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;// marked[v] = is there path between s-v
	private int[] distTo; // distTo[v] = shortest distance to v from s;
	private int[] parentOf; // v was found from parentOf[v]

	public BFS(Graph G, int s) {
		marked = new boolean[G.V() + 1];
		distTo = new int[G.V() + 1];
		parentOf = new int[G.V() + 1];
		validateVertex(s);
		bfs(G, s);
	}

	private void bfs(Graph G, int s) {
		Queue<Integer> Q = new LinkedList<>();
		for (int i = 0; i < G.V() + 1; i++) {
			distTo[i] = INFINITY;
		}
		parentOf[s] = 0;
		distTo[s] = 0;
		marked[s] = true;
		Q.add(s);
		while (!Q.isEmpty()) {
			int v = Q.poll();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					Q.add(w);
					marked[w] = true;
					parentOf[w] = v;
					distTo[w] = distTo[v] + 1;
				}
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public int distTo(int v) {
		return distTo[v];
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
		Graph g = new Graph(6);
		g.addEdge(1, 4);
		g.addEdge(2, 3);
		g.addEdge(1, 2);
		g.addEdge(4, 3);
		g.addEdge(5, 6);
		BFS bfs = new BFS(g, 1);
		System.out.println(bfs.hasPathTo(6));
		System.out.println(bfs.parentOf(5));
	}

}
