package com.practice.algorithms.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SCC {
	
	  private boolean[] marked;     // marked[v] = has vertex v been visited?
	  private int count;            //num of scc
	  private int[] leader;         //leader[i] = leader of scc containing i
	  private List<Integer> sizes;  //sizes of scc
	  private int size ;
	
	@SuppressWarnings("unchecked")
	public SCC(Graph G){
		DfsStack dfs = new DfsStack(G.reverse(G));
		int[] order = dfs.getTopoLogicalOrder();
		marked = new boolean[G.V()+1];
		leader = new int[G.V()+1];
		count = 0 ;
		sizes = new ArrayList<Integer>();
		Iterator<Integer>[] adj = new Iterator[G.V()+1];
		for(int i=1 ; i<G.V()+1 ; i++){
			adj[i] = G.adj(i).iterator();
		}
		Stack<Integer> stack = new Stack<>();
		for(int i : order){	
			if(!marked[i]){
				size = 0;
				count++;
				leader[i] = i;
				dfsStack(G,i,adj,stack);
				sizes.add(size);
			}
		}
		
	}
	
	private void dfsStack(Graph G , int s , Iterator<Integer>[] adj , Stack<Integer> stack){		
		stack.push(s);
		size++;
		marked[s] = true;
		while(!stack.isEmpty()){
			int v = stack.peek();
		    if(adj[v].hasNext()){
		    	int w = adj[v].next();
		    	    if(!marked[w]){
		    		   marked[w]  = true;
		    		   leader[w] = s;
		    		   stack.push(w);
		    		   size++;
		    	}	    	
		    }else{
		    	stack.pop();
		    }
		}
		
	}
	
	public int getNumberOfScc(){
		return count;
	}
	
	public List<Integer> getSizesOfScc(){
		return sizes;
	}

	public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
		Graph g = new Graph(875714);			
		File f = new File("SCC.txt");
		Scanner s = new Scanner(f);
		while(s.hasNextInt()){
			g.addEdge(s.nextInt(), s.nextInt());
		}
		s.close();
		SCC scc = new SCC(g);
		List<Integer> list = scc.sizes;
		Collections.sort(list);
		Collections.reverse(list);
		System.out.println(scc.count);
		for(int i = 0 ; i<5 ; i++){
			System.out.println(list.get(i));
		}
		

	}

}
