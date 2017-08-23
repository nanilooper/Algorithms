package com.practice.algorithms.greedy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.practice.algorithms.datastructures.MinHeap;

class Node implements Comparable<Node> {
	public int i;
	public long w;
	public Node left;
	public Node right;

	public Node(int charIndex, long charWeight) {
		i = charIndex;
		w = charWeight;
	}

	@Override
	public int compareTo(Node other) {
		if (w > other.w) {
			return 1;
		}
		if (w < other.w) {
			return -1;
		}
		return 0;
	}
}

public class HufmanCoding {
	private Node root;
	private MinHeap<Node> heap;

	public HufmanCoding(Scanner scan) throws Exception {
		int numChar = scan.nextInt();
		heap = new MinHeap<>(numChar);
		for (int i = 1; i <= numChar; i++) {
			heap.insert(new Node(i, scan.nextLong()));
		}
		assert numChar == heap.getSize() : "heap contruction failed";

		root = constructHufmanTree(heap);

	}

	private Node constructHufmanTree(MinHeap<Node> heap) throws Exception {
		while (heap.getSize() > 1) {
			Node left = heap.removeMin();
			Node right = heap.removeMin();
			Node inter = new Node(0, left.w + right.w);
			inter.left = left;
			inter.right = right;
			heap.insert(inter);
		}
		assert heap.getSize() == 1 : "tree contruction failed";
		return heap.removeMin();
	}

	public int minCharLength() {
		return minHeight(root);
	}

	private int minHeight(Node x) {
		if (x == null) {
			return -1;
		}
		return 1 + Math.min(minHeight(x.left), minHeight(x.right));
	}

	public int maxCharLength() {
		return maxHeight(root);
	}

	private int maxHeight(Node x) {
		if (x == null) {
			return -1;
		}
		return 1 + Math.max(maxHeight(x.left), maxHeight(x.right));
	}

	public static void main(String[] args) throws FileNotFoundException, Exception {
		HufmanCoding hc = new HufmanCoding(new Scanner(new File("HUFMAN.txt")));
		System.out.println(hc.minCharLength());
		System.out.println(hc.maxCharLength());
	}

}
