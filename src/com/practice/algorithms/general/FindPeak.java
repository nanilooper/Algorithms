package com.practice.algorithms.general;

public class FindPeak {

	public static int findPeak(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			if (left == right) {
				return arr[left];
			}
			int mid = (left + right) / 2;
			if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
				return arr[mid];
			} else if (arr[mid] > arr[mid - 1]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 4, 8, 23, 54, 16, 13, 1 };
		System.out.println(findPeak(arr));
		// TODO Auto-generated method stub

	}

}
