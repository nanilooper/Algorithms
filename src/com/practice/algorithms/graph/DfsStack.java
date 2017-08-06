package com.practice.algorithms.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class DfsStack {
	private boolean[] marked;
	private int[] parentOf;
	private int[] topoLogicalOrder;
	
	public void DfsStack(Graph G , int s){
		marked = new boolean[G.V()+1];
		parentOf = new int[G.V()+1];			
		Iterator<Integer>[] adj = new Iterator[G.V()+1];
		for(int i=1 ; i<G.V()+1 ; i++){
			adj[i] = G.adj(i).iterator();
		}
		Stack<Integer> stack = new Stack<>();
		stack.push(s);
		marked[s] = true;
		while(!stack.isEmpty()){
			int v = stack.peek();		
		    if(adj[v].hasNext()){
		    	int w = adj[v].next();
		    	    if(!marked[w]){
		    		   marked[w]  = true;
		    		   parentOf[w] = v;
		    		   stack.push(w);
		    	}	    	
		    }else{
		    	stack.pop();
		    }
		}
		
	}
	
	public DfsStack(Graph G){
		marked = new boolean[G.V()+1];
		parentOf = new int[G.V()+1];	
		topoLogicalOrder = new int[G.V()+1];
		@SuppressWarnings("unchecked")
		Iterator<Integer>[] adj = new Iterator[G.V()+1];
		for(int i=1 ; i<G.V()+1 ; i++){
			adj[i] = G.adj(i).iterator();
		}
		Stack<Integer> stack = new Stack<>();
		int cur = G.V();
		for(int i = 1 ; i<G.V()+1 ; i++){
			if(!marked[i]){
				stack.push(i);
				marked[i] = true;
				while(!stack.isEmpty()){
					int v = stack.peek();		
				    if(adj[v].hasNext()){
				    	int w = adj[v].next();
				    	    if(!marked[w]){
				    		   marked[w]  = true;
				    		   parentOf[w] = v;
				    		   stack.push(w);
				    	}	    	
				    }else{
				           int x =  	stack.pop();
				           topoLogicalOrder[cur] = x;
				           cur--;			           
				    }
				}
				
			}
		}
		
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public int parentOf(int v){
		return parentOf[v];
	}
	
	public int[] getTopoLogicalOrder(){
		int[] topo = Arrays.copyOfRange(topoLogicalOrder, 1, topoLogicalOrder.length);
		return topo;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
//		Graph g = new Graph(4);
//		g.addEdge(1, 2);
//		g.addEdge(2, 3);
//		g.addEdge(1, 4);
		Graph g = new Graph(875714);			
		File f = new File("SCC.txt");
		Scanner s = new Scanner(f);
		while(s.hasNextInt()){
			g.addEdge(s.nextInt(), s.nextInt());
		}
		
		DfsStack dfs = new DfsStack(g);
		dfs.getTopoLogicalOrder();
		
		System.out.println(dfs.parentOf(6565));
		//System.out.println(dfs.parentOf(3));
	}

}
