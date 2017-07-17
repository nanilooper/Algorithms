package com.practice.algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuickSort {
private static final String path = "QuickSort.txt";
	
	public static long[] readFile(String path) throws NumberFormatException, IOException{
		BufferedReader br = null;
		FileReader fr = null;
		fr = new FileReader(path);
		br = new BufferedReader(fr);
		long[] arr = new long[10000];
		for(int i = 0 ; i<10000 ; i++){
			arr[i] = Long.parseLong(br.readLine());
		}
		br.close();
		fr.close();
		return arr;	
	}
	
	public static void swap(long[] arr , int i , int j){
		long temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static int selectPivot(long[] arr , int l , int r){
		int m = (l+r)/2 ;
		if((arr[m]>arr[l] && arr[m]<arr[r]) ||(arr[m]>arr[r] && arr[m]<arr[l])){
			return m;
		}else if((arr[l]>arr[m] && arr[l]<arr[r]) ||(arr[l]>arr[r] && arr[l]<arr[m])){
			return l;
		}else{
			return r;
		}
	}
	
	public static int partition(long[] arr ,int l , int r){
		int pivot =l + (int)(Math.random() * ((r - l) + 1));
		swap(arr,l,pivot);
		long pElem = arr[l];
		int i = l;
		for(int j = l+1 ; j < r+1 ; j++){
			if(arr[j]<pElem){
				swap(arr,j,i+1);
				i++;
			}
		}
		swap(arr,l,i);
		return i;
	}
	
	public static long quickSort(long[] arr , int l,int r){
		if(l>=r){
			return 0;
		}
		long count = r-l;
		int p = partition(arr,l,r);
		//System.out.println(p);
		count += quickSort(arr, l, p-1);
		count += quickSort(arr, p+1, r);
		return count;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		long[] arr = readFile(path);
	     long r=quickSort(arr, 0,arr.length-1);
		System.out.println(r);
		
	}


}
