package com.practice.algorithms.datastructures;

public class MinHeap<T extends Comparable<T>> {

	private int size;
	private int capacity;
	private T[] heap;

	@SuppressWarnings("unchecked")
	public MinHeap(int capacity) {
		this.capacity = capacity;
		size = 0;
		heap = (T[]) new Comparable[capacity + 1];
	}

	@SuppressWarnings("unchecked")
	public MinHeap(T[] arr, int capacity) {
		this.capacity = capacity;
		heap = (T[]) new Comparable[capacity + 1];
		System.arraycopy(arr, 0, heap, 1, arr.length);
		size = arr.length;
		for (int i = size / 2; i > 0; i--) {
			heapify(i);
		}

	}

	public void insert(T t) throws Exception {
		if (size + 1 > capacity) {
			throw new Exception("heap is full");
		} else {
			heap[++size] = t;
		}

		int i = size;
		while (i > 1) {
			int parent = i / 2;
			if (heap[parent].compareTo(heap[i]) > 0) {
				swap(parent, i);
				i = parent;
			} else {
				break;
			}

		}

	}

	public T removeMin() {
		T min = heap[1];
		heap[1] = heap[size--];
		heapify(1);
		return min;
	}

	// all subtress of i already holds min-heap property,call this method to heapify entire array
	public void heapify(int i) {
		if (2 * i > size) {
			return;
		} else if (2 * i + 1 > size) {
			if (heap[i].compareTo(heap[2 * i]) > 0) {
				swap(i, 2 * i);
				heapify(2 * i);
			}
		} else {
			if (heap[i].compareTo(heap[2 * 1]) < 0 && heap[i].compareTo(heap[2 * 1 + 1]) < 0) {
				return;
			} else if (heap[i].compareTo(heap[2 * 1]) > 0 && heap[2 * i].compareTo(heap[2 * 1 + 1]) < 0) {
				swap(i, 2 * i);
				heapify(2 * i);
			} else {
				swap(i, 2 * i + 1);
				heapify(2 * i + 1);
			}
		}

	}

	public T getMin() {
		return heap[1];
	}

	public T get(int i) {
		return heap[i];
	}

	public boolean isEmpty() {
		return size < 1;
	}

	public void swap(int t1, int t2) {
		T temp = heap[t1];
		heap[t1] = heap[t2];
		heap[t2] = temp;
	}

	public static void main(String[] args) throws Exception {

		// minheap.insert(5);
		// minheap.insert(1);
		// minheap.insert(4);
		// minheap.insert(0);

		Integer[] a = { 2, 4, 6, 7, 5, 1, 1 };

		MinHeap<Integer> minheap = new MinHeap<>(a, 10);
		minheap.insert(0);

		System.out.println(minheap.removeMin());
		System.out.println(minheap.removeMin());
	}

}
