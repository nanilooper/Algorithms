package com.practice.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	private int V; // num of vertices
	private int E; // num of edges
	private List<Integer>[] adj;

	/* initializing a graph with V vertices */
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		if (V < 0) {
			throw new IllegalArgumentException("num vertices cant be zero");
		}
		this.V = V;
		E = 0;
		adj = new ArrayList[V + 1]; // vertices labeled from 1 to V
		adj[0] = null;
		for (int v = 1; v < V + 1; v++) {
			adj[v] = new ArrayList<>();
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	private void validateVertex(int v) {
		if (v < 1 || v > V) {
			throw new IllegalArgumentException("vertex should be between 1 and V");
		}
	}

	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		E++;
		adj[v].add(w);
		// adj[w].add(v); uncomment for undirected graph
	}

	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

}
