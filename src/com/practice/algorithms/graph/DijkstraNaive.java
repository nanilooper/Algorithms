package com.practice.algorithms.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DijkstraNaive {
	
	private static final int MAX=1000000;
	private boolean[] marked;
	private List<Integer> list; //explored vertices
	private int[] distance;     //shortest distances
	private int[] parent;
	
	public DijkstraNaive(WeightedGraph G , int s){
		marked = new boolean[G.V()+1];
		distance = new int[G.V()+1];
		parent = new int[G.V()+1];
		Arrays.fill(distance, MAX);
		marked[s] = true;
		distance[s] = 0;
		list = new ArrayList<Integer>();
		list.add(s);
		int nextVertex;
		int d;
		int parent;
		while(true){
            nextVertex = 0;
            d = MAX;
            parent = 0;
            for(int v : list){
            	  for(Edge e : G.adj(v)){
            		  if(!marked[e.v] && (distance[v]+e.l<d)){
            			  nextVertex = e.v;
                      d = distance[v]+e.l;
                      parent = v;
            		  }
            	  }
            }
            if(nextVertex == 0){
            	    break;
            }else{
            	    marked[nextVertex] = true;
            	    list.add(nextVertex);
            	    distance[nextVertex] = d;
            }
		}
		
	}
	
	public int  distanceOf(int v){
		return distance[v];
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		WeightedGraph g = new WeightedGraph(200);
		File f = new File("DIJKSTRAS.txt");
		Scanner scan = new Scanner(f);
		while(scan.hasNextLine()){
			String s = scan.nextLine();
		    String[] a = s.split("\\s+");
		    for(int i = 1 ;i< a.length ; i++){
		    	   String[] b = a[i].split(",");
		    	   g.addEdge(Integer.parseInt(a[0]),Integer.parseInt(b[0]), Integer.parseInt(b[1]));
		    }
		}
//		g.addEdge(1, 2, 2);
//		g.addEdge(2, 3, 3);
//		g.addEdge(1, 4, 1);
//		g.addEdge(4, 3, 2);
//		g.addEdge(2, 5, 7);
//		g.addEdge(3, 5, 1);
		String s = "7,37,59,82,99,115,133,165,188,197";
		String[] arr = s.split(",");
		DijkstraNaive dij = new DijkstraNaive(g,1);
		for(String i : arr){
			System.out.print(dij.distanceOf(Integer.parseInt(i))+",");
		}
	}

}
