package com.practice.algorithms.graph;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import com.practice.algorithms.datastructures.IndexMinPQ;

public class DijKstrasPQ {

	private IndexMinPQ<Integer> pq;
	private int[] dist;
	private boolean[] marked;

	public DijKstrasPQ(WeightedGraph G, int s) throws Exception {
		pq = new IndexMinPQ<>(G.V() + 1);
		dist = new int[G.V() + 1];
		marked = new boolean[G.V() + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for (int i = 1; i <= G.V(); i++) {
			pq.insert(i, Integer.MAX_VALUE);
		}
		pq.delete(s);
		pq.insert(s, 0);
		while (!pq.isEmpty()) {
			int next = pq.extractMin();
			marked[next] = true;
			dist[next] = pq.getKey(next);
			for (Edge e : G.adj(next)) {
				if (!marked[e.v]) {
					int score = Math.min(pq.getKey(e.v), dist[next] + e.l);
					pq.delete(e.v);
					pq.insert(e.v, score);
				}
			}
		}
	}

	public int distanceOf(int index) {
		return dist[index];
	}

	public static void main(String[] args) throws Exception {
		// WeightedGraph g = new WeightedGraph(4);
		// g.addEdge(1, 2, 1);
		// g.addEdge(1, 3, 2);
		// g.addEdge(2, 4, 10);
		// g.addEdge(3, 4, 4);
		WeightedGraph g = new WeightedGraph(200);
		File f = new File("DIJKSTRAS.txt");
		Scanner scan = new Scanner(f);
		while (scan.hasNextLine()) {
			String s = scan.nextLine();
			String[] a = s.split("\\s+");
			for (int i = 1; i < a.length; i++) {
				String[] b = a[i].split(",");
				g.addEdge(Integer.parseInt(a[0]), Integer.parseInt(b[0]), Integer.parseInt(b[1]));
			}
		}
		DijKstrasPQ dij = new DijKstrasPQ(g, 1);
		// System.out.println(Arrays.toString(dij.dist));
		String s = "7,37,59,82,99,115,133,165,188,197";
		String[] arr = s.split(",");
		// DijkstraNaive dij = new DijkstraNaive(g,1);
		for (String i : arr) {
			System.out.print(dij.distanceOf(Integer.parseInt(i)) + ",");
		}
	}
}
