package com.practice.algorithms;

public class MergeSort {

	public static void mergeSort(int[] array) {

		if (array.length < 2) {
			return;
		}

		int[] first = new int[array.length / 2];
		int[] second = new int[array.length - first.length];
		System.arraycopy(array, 0, first, 0, first.length);
		System.arraycopy(array, first.length, second, 0, second.length);
		mergeSort(first);
		mergeSort(second);
		merge(first, second, array);
		// return array;

	}

	public static void merge(int[] first, int[] second, int[] array) {

		int i = 0;
		int j = 0;
		int k = 0;

		while (i < first.length && j < second.length) {
			if (first[i] <= second[j]) {
				array[k] = first[i];
				i++;
				k++;
			} else {
				array[k] = second[j];
				j++;
				k++;
			}
		}

		while (j < second.length) {
			array[k] = second[j];
			k++;
			j++;
		}

		while (i < first.length) {
			array[k] = first[i];
			k++;
			i++;
		}

		return;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a = { 8, 5, 9, 2, 2, 4, 6, 8, 22, 1 };
		mergeSort(a);
		System.out.println(a[0]);

	}

}
