package com.practice.algorithms.datastructures;

import java.util.Arrays;

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
		swim(size);
	}
	
	public int extractMin() throws Exception{
		if(size<1){
			throw new Exception("pq is empty");
		}
		int min = heap[1];
		swap(1,size--);
		assert min == heap[size+1];
		sink(1);
		position[min] = -1;
		heap[size+1] = -1;
		keys[min]=null;
		//size--;
	//	sink(1);
		return min;
	}
	
	 private boolean greater(int i, int j) {
	        return keys[heap[i]].compareTo(keys[heap[j]]) > 0;
	    }
	 
	 private void sink(int k) {
	        while (2*k <= size) {
	            int j = 2*k;
	            if (j < size && greater(j, j+1)) j++;
	            if (!greater(k, j)) break;
	            swap(k, j);
	            k = j;
	        }
	    }
	
	public void decreaseKey(int index , Key newKey){
		keys[index] = newKey;
		int temp = position[index];
	    swim(temp);
	}
	
	public void delete(int i , Key min) throws Exception{
		// int index = position[i];
	       // swap(index, size--);
	       // swim(index);
	       // sink(index);
	       // keys[i] = null;
	       // position[i] = -1;
		decreaseKey(i, min);
		extractMin();
//		int i = position[index];
//		swap(i,size);
//		swim(i);
//		sink(i);
//		keys[size--] = null;
//		position[index] = -1;
	}
	
	public void swim(int k){
		 while (k > 1 && greater(k/2, k)) {
	            swap(k, k/2);
	            k = k/2;
	        }

	}
	
//	public void sink(int i) {
//		if (2 * i > size) {
//			return;
//		} else if (2 * i + 1 > size) {
//			if (keys[heap[i]].compareTo(keys[heap[2 * i]]) > 0) {
//				swap(i, 2 * i);
//				sink(2 * i);
//			}
//		} else {
//			if (keys[heap[i]].compareTo(keys[heap[2 * 1]]) < 0 && keys[heap[i]].compareTo(keys[heap[2 * 1 + 1]]) < 0) {
//				return;
//			} else if (keys[heap[i]].compareTo(keys[heap[2 * 1]]) > 0 && keys[heap[2 * i]].compareTo(keys[heap[2 * 1 + 1]]) < 0) {
//				swap(i, 2 * i);
//				sink(2 * i);
//			} else {
//				swap(i, 2 * i + 1);
//				sink(2 * i + 1);
//			}
//		}
//
//	}

	private void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
		position[heap[i]] = i;
		position[heap[j]] = j;
	}

	private int getMin() {
		return heap[1];
	}

	public static void main(String[] args) throws Exception {
		IndexMinPQ<Integer> pq = new IndexMinPQ<>(10);
		pq.insert(1, 10);
		pq.insert(2, 5);
		pq.insert(3, 50);
		pq.insert(4, 500);
		pq.insert(5, 6);
		pq.decreaseKey(4, 1);
	    pq.delete(4,-1000);
		pq.decreaseKey(2, 2);
		System.out.println(Arrays.toString(pq.heap));
		System.out.println(pq.extractMin());
		System.out.println(pq.extractMin());
		System.out.println(Arrays.toString(pq.heap));
	//	System.out.println(pq.keys[5]);
		//System.out.println(pq.extractMin());
	}

}
