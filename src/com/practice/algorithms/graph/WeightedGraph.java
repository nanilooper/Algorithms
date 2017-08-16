package com.practice.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

public class WeightedGraph {

	private int V; // num of vertices
	private int E; // num of edges;
	private List<Edge>[] adj;

	@SuppressWarnings("unchecked")
	public WeightedGraph(int V) {
		this.V = V;
		E = 0;
		adj = new ArrayList[V + 1];
		for (int v = 1; v < V + 1; v++) {
			adj[v] = new ArrayList<>();
		}
	}

	public void addEdge(int v, int w, int length) {
		Edge e = new Edge(w, length);
		Edge e2 = new Edge(v, length); // undirected
		adj[w].add(e2); // undirected
		adj[v].add(e);
		E++;
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public List<Edge> adj(int v) {
		return adj[v];
	}

}
