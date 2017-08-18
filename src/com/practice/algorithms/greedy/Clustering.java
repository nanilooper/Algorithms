package com.practice.algorithms.greedy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.practice.algorithms.datastructures.QuickUnion;

class Edge implements Comparable<Edge> {
	int v;
	int u;
	int dist;

	public Edge(int v, int u, int dist) {
		this.v = v;
		this.u = u;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge o) {
		if (dist < o.dist) {
			return -1;
		} else if (dist > o.dist) {
			return 1;
		}
		return 0;
	}

}

public class Clustering {

	private List<Edge> edges;
	private QuickUnion qu;
	private int spacing;

	public Clustering(int numPoints, int numClusters, List<Edge> edges) {
		List<Edge> spanEdges = new ArrayList<>();
		qu = new QuickUnion(numPoints);
		this.edges = edges;
		Collections.sort(edges);
		int curClusters = numPoints;
		for (int i = 0; i < edges.size(); i++) {
			Edge e = edges.get(i);
			if (!qu.connected(e.u, e.v)) {
				if (curClusters == 4) {
					System.out.println(i);
					spacing = e.dist;
					break;
				}
				qu.union(e.u, e.v);
				spanEdges.add(e);
				curClusters--;
			}
		}
		assert curClusters == 4;

	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("clustering1.txt"));
		int num = scan.nextInt();
		scan.nextLine();
		List<Edge> edges = new ArrayList<>();
		while (scan.hasNextLine()) {
			edges.add(new Edge(scan.nextInt(), scan.nextInt(), scan.nextInt()));
			scan.nextLine();
		}
		Clustering cl = new Clustering(num, 4, edges);
		System.out.println(cl.spacing);

	}

}
