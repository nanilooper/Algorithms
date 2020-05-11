package com.practice.algorithms.general;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountingInversions {

	public static int[] readFile(String path) throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		int num = 100000;
		int[] arr = new int[100000];
		for (int i = 0; i < num; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		return arr;
	}

	public static long countInversions(int[] array) {
		if (array.length < 2) {
			return 0;
		}

		int[] first = new int[array.length / 2];
		int[] second = new int[array.length - first.length];
		System.arraycopy(array, 0, first, 0, first.length);
		System.arraycopy(array, first.length, second, 0, second.length);
		long a = countInversions(first);
		long b = countInversions(second);
		long c = splitInversions(first, second, array);
		return a + b + c;
	}

	public static long splitInversions(int[] first, int[] second, int[] array) {
		int i = 0;
		int j = 0;
		int k = 0;

		long count = 0;

		while (i < first.length && j < second.length) {
			if (first[i] <= second[j]) {
				array[k] = first[i];
				i++;
				k++;
			} else {
				array[k] = second[j];
				count += first.length - i;
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

		return count;

	}

	public static void main(String[] args) throws IOException {
		int[] a = readFile("/home/rayudu/Desktop/_bcb5c6658381416d19b01bfc1d3993b5_IntegerArray.txt");
		System.out.println(countInversions(a));

	}

}
