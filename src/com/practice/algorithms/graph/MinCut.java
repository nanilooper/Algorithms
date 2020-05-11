package com.practice.algorithms.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MinCut {

	private static final String pathfile = "KagerMincut.txt";

	private static List<Integer> vList;
	private static HashMap<Integer, List<Integer>> adjList;
	private static int size;
	private static Random r = new Random();
	private static int mincut = Integer.MAX_VALUE;
	private static BufferedReader br;

	public static void GraphConstruct(String filepath) throws IOException {
		FileReader fr = new FileReader(filepath);
		br = new BufferedReader(fr);
		vList = new ArrayList<>();
		adjList = new HashMap<>();
		for (int j = 0; j < 200; j++) {
			String s = br.readLine();
			String num[] = s.split("\\s+");
			List<Integer> list = new ArrayList<>();
			for (int i = 1; i < num.length; i++) {
				list.add(Integer.parseInt(num[i]));
			}
			vList.add(Integer.parseInt(num[0]));
			adjList.put(Integer.parseInt(num[0]), list);
		}
		size = vList.size();

	}
	
	public static void findMinCut(){
		while(adjList.size()>2){
			
			//randomly choose first vertex
			int u =r.nextInt(vList.size());
			Integer U = vList.get(u);
			//randomly choose second vertext from adjlist of U
			List<Integer> adjListU = adjList.get(U);
			int v = r.nextInt(adjListU.size());
			Integer V = adjListU.get(v);
			
			
			//get adjlist of V
			List<Integer> adjListV = adjList.get(V);
			
			//iterate adjListV and remove V from their adjList and add it to U
			Iterator<Integer> I = adjListV.iterator();
			while(I.hasNext()){
				Integer adjV = I.next();
				while(adjList.get(adjV).remove(V));
				adjList.get(adjV).add(U);
				adjList.get(U).add(adjV);
			}
			adjList.remove(V);
			vList.remove(V);
			while(adjList.get(U).remove(U));
		
		}
		mincut = Math.min(mincut,adjList.get(vList.get(0)).size());
		//System.out.println(adjList.get(vList.get(0)).size());
	}

	public static void main(String[] args) throws IOException {
		GraphConstruct(pathfile);
		//findMinCut();
		double d = vList.size();
		long itr =  (long) (d*d*Math.log(d));
		System.out.println(itr);
		for(int i = 0 ; i<200 ; i++){
			findMinCut();
			GraphConstruct(pathfile);
		}
		
		System.out.println(mincut);
	}

}
