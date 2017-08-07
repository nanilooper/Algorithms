package com.practice.algorithms.datastructures;

public class IndexMinPQ<Key extends Comparable<Key>> {

	private int capacity;
	private int size;
	private int[] heap; // array based min heap for indexs
	private int[] position; // position[i] = position of i in heap array;
	private Key[] keys; // keys[i] = key of ith index

	@SuppressWarnings("unchecked")
	public IndexMinPQ(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException("capcity canot be negative");
		}
		size = 0;
		this.capacity = capacity;
		heap = new int[capacity + 1];
		position = new int[capacity + 1];
		keys = (Key[]) new Comparable[capacity + 1];
		for (int i = 0; i <= capacity; i++) {
			position[i] = -1; // nothing is there in heap
		}
	}

	public void insert(int i, Key key) {
		position[i] = ++size;
		heap[size] = i;
		keys[i] = key;
		int k = size;
		while (k > 1) {
			int parent = k / 2;
			if (keys[heap[k]].compareTo(keys[heap[parent]]) < 0) {
				swap(k, parent);
			} else {
				break;
			}
		}
	}

	private void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
		position[heap[i]] = j;
		position[heap[j]] = i;
	}

	private int getMin() {
		return heap[1];
	}

	public static void main(String[] args) {
		IndexMinPQ<Integer> pq = new IndexMinPQ<>(4);
		pq.insert(1, 10);
		pq.insert(2, 5);
		pq.insert(3, 50);
		System.out.println(pq.getMin());
	}

}
