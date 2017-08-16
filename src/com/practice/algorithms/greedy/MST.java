package com.practice.algorithms.greedy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.practice.algorithms.graph.Edge;
import com.practice.algorithms.graph.WeightedGraph;

public class MST {

	private boolean[] marked;
	private List<Integer> explored; // list of vertices explored
	private List<Integer> edges; // lengths of selected edges
	private static final int MAX = Integer.MAX_VALUE;

	public MST(WeightedGraph G) {
		marked = new boolean[G.V() + 1];
		mst(G, 1);
	}

	private void mst(WeightedGraph G, int s) {
		explored = new LinkedList<>();
		edges = new LinkedList<>();
		marked[s] = true;
		explored.add(s);
		int nextVertex;
		int edge;
		while (true) {
			nextVertex = 0;
			edge = MAX;
			for (int v : explored) {
				for (Edge e : G.adj(v)) {
					if (!marked[e.v] && e.l < edge) {
						nextVertex = e.v;
						edge = e.l;
					}
				}
			}
			if (nextVertex == 0) {
				break;
			} else {
				marked[nextVertex] = true;
				explored.add(nextVertex);
				edges.add(edge);
			}

		}

	}

	public long lengthOfMST() {
		long l = 0;
		for (int i : edges) {
			l += i;
		}
		return l;
	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File("MST.txt"));
		int numNodes = scan.nextInt();
		int numEdges = scan.nextInt();
		WeightedGraph G = new WeightedGraph(numNodes);
		for (int i = 0; i < numEdges; i++) {
			G.addEdge(scan.nextInt(), scan.nextInt(), scan.nextInt());
		}
		MST mst = new MST(G);
		System.out.println(mst.lengthOfMST());

	}

}
