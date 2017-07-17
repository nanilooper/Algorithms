package com.practice.algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MinCut {

	private static final String pathfile = "/home/rayudu/Desktop/_f370cd8b4d3482c940e4a57f489a200b_kargerMinCut.txt";

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

	public static void main(String[] args) throws IOException {
		GraphConstruct(pathfile);
		System.out.println(size);
	}

}
