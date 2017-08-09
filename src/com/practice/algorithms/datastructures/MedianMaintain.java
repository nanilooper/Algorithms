package com.practice.algorithms.datastructures;

import java.io.File;
import java.util.Scanner;

public class MedianMaintain {

	public static void main(String[] args) throws Exception {
		MinHeap<Integer> minH = new MinHeap<>(10000); //used for maximums
		MaxHeap<Integer> maxH = new MaxHeap<>(10000); //used for minimums
		File f = new File("MedianMaintain.txt");
		Scanner scan = new Scanner(f);
		int[] a = new int[10000];
		for(int i = 0 ; i<10000 ; i++){
			a[i] = scan.nextInt();
		}
	     long sum = 0;
	     maxH.insert(a[0]);
	     sum += a[0];
		for(int i = 1 ; i<a.length;i++){
			if(a[i]<maxH.getMax()){
				maxH.insert(a[i]);
				if(maxH.getSize()>minH.getSize()+1){
					minH.insert(maxH.extractMax());
				}
			}else{
				minH.insert(a[i]);
				if(minH.getSize()>maxH.getSize()){
					maxH.insert(minH.removeMin());
				}
			}
			sum+=maxH.getMax();
		}
		System.out.println(sum%10000);

	}

}
