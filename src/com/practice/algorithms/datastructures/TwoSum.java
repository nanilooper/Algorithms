package com.practice.algorithms.datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class TwoSum {

	public static boolean findTwoSum(long[] numbers, long target, HashSet<Long> set) {
		for (int i = 0; i < numbers.length; i++) {
			long diff = target - numbers[i];
			if (set.contains(diff) && !set.contains(numbers[i])) {
				set.clear();
				return true;
			}
			set.add(numbers[i]);
		}
		set.clear();
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("2SUM.txt");
		Scanner scan = new Scanner(f);
		long[] num = new long[1000000];
		for (int i = 0; i < 1000000; i++) {
			num[i] = scan.nextLong();
		}
		HashSet<Long> set = new HashSet<>();
		long t = -10000;
		int count = 0;
		while (t <= 10000) {
			if (findTwoSum(num, t, set)) {
				count++;
			}
			t++;
		}
		scan.close();
		System.out.println(count);

	}

}
