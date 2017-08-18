package com.practice.algorithms.greedy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.practice.algorithms.datastructures.MaxHeap;

class Job implements Comparable<Job> {

	public double w;
	public double l;

	public Job(double weight, double length) {
		w = weight;
		l = length;
	}

	// greedy for w/l
	@Override
	public int compareTo(Job j) {
		double d = w / l - j.w / j.l;
		if (d > 0) {
			return 1;
		} else if (d < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	// @Override
	// public int compareTo(Job j) {
	// double d = w - l - (j.w - j.l);
	// if (d > 0) {
	// return 1;
	// } else if (d == 0 && w > j.w) {
	// return 1;
	// } else if (d < 0) {
	// return -1;
	// } else if (d == 0 && w < j.w) {
	// return -1;
	// } else {
	// return 0;
	// }
	// }
}

public class JobsScheduler {

	private MaxHeap<Job> heap;

	public JobsScheduler(Scanner s) {
		int numJobs = s.nextInt();
		heap = new MaxHeap<>(numJobs);
		for (int i = 0; i < numJobs; i++) {
			double w = s.nextDouble();
			double l = s.nextDouble();
			heap.insert(new Job(w, l));
		}
		System.out.println(heap.getSize());
	}

	public long minWeightedSum() {
		long sum = 0;
		long finishTime = 0;
		while (!heap.isEmpty()) {
			Job j = heap.extractMax();
			finishTime += j.l;
			sum += j.w * finishTime;
		}
		return sum;
	}

	public static void main(String[] args) throws FileNotFoundException {
		JobsScheduler sch = new JobsScheduler(new Scanner(new File("JOBS.txt")));
		System.out.println(sch.minWeightedSum());
	}

}
