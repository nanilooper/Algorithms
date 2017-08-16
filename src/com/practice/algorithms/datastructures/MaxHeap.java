package com.practice.algorithms.datastructures;

public class MaxHeap<T extends Comparable<T>> {

	private int capacity;
	private int size;
	private T[] heap;

	@SuppressWarnings("unchecked")
	public MaxHeap(int capacity) {
		this.capacity = capacity;
		size = 0;
		heap = (T[]) new Comparable[capacity + 1];
	}

	public void insert(T t) {
		heap[++size] = t;
		swim(size);
	}

	public T extractMax() {
		T max = getMax();
		swap(1, size--);
		sink(1);
		heap[size + 1] = null;
		return max;
	}

	public T getMax() {
		return heap[1];
	}

	public int getSize() {
		return size;
	}

	private void swim(int i) {
		while (i > 1) {
			if (heap[i].compareTo(heap[i / 2]) > 0) {
				swap(i, i / 2);
				i = i / 2;
			} else {
				break;
			}
		}
	}

	private void sink(int i) {
		while (2 * i <= size) {
			int child = 2 * i;
			if (child < size && heap[child].compareTo(heap[child + 1]) < 0) {
				child++;
			}
			if (heap[child].compareTo(heap[i]) > 0) {
				swap(child, i);
				i = child;
			} else {
				break;
			}
		}
	}

	private void swap(int i, int j) {
		T temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	public boolean isEmpty() {
		return size <= 0;
	}

	public static void main(String[] args) {
		MaxHeap<Integer> heap = new MaxHeap(10);
		heap.insert(10);
		heap.insert(26);
		heap.insert(5);
		System.out.println(heap.getMax());
		System.out.println(heap.extractMax());
		System.out.println(heap.getMax());
	}

}
