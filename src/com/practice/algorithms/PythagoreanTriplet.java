package com.practice.algorithms;

import java.util.Arrays;

public class PythagoreanTriplet {

	public static boolean findPythagoreanTriplet(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i] * arr[i];
		}

		Arrays.sort(arr);

		for (int i = arr.length - 1; i > 0; i--) {
			// System.out.println(arr[i]);
			int right = i - 1;
			int left = 0;
			while (left < right) {
				int sum = arr[left] + arr[right];
				if (sum == arr[i]) {
					System.out.printf("%d + %d = %d \n", arr[left], arr[right], arr[i]);
					return true;
				} else if (sum > arr[i]) {
					right -= 1;
				} else {
					left += 1;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int[] a = { 3, 4, 2, 1, 9 };
		System.out.println(findPythagoreanTriplet(a));
	}

}
