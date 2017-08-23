package com.practice.algorithms.greedy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.practice.algorithms.datastructures.QuickUnion;

public class ClusteringBits {

	public static Set<BitSet> friends(BitSet s) {
		Set<BitSet> set = new HashSet<>();
		for (int i = 0; i < 24; i++) {
			BitSet s1 = (BitSet) s.clone();
			s1.flip(i);
			set.add(s1);
			for (int j = i + 1; j < 24; j++) {
				BitSet s2 = (BitSet) s.clone();
				s2.flip(i);
				s2.flip(j);
				set.add(s2);
			}
		}
		return set;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("CLUSTERING_BITS.txt"));
		int n = scan.nextInt();
		int m = scan.nextInt();
		scan.nextLine();
		HashMap<BitSet, Integer> map = new HashMap<>();
		int num = 1;
		for (int i = 0; i < n; i++) {
			BitSet s = new BitSet(m);
			for (int j = 0; j < m; j++) {
				if (scan.next().equals("1")) {
					s.set(j);
				}
			}
			if (map.containsKey(s) == false) {
				map.put(s, num);
				num++;
			}
		}
		QuickUnion uf = new QuickUnion(map.size());
		Iterator<BitSet> itr = map.keySet().iterator();
		int numClusters = map.size();
		while (itr.hasNext()) {
			BitSet s1 = itr.next();
			Set<BitSet> set = friends(s1);
			for (BitSet s2 : set) {

				if (map.containsKey(s2)) {
					if (!uf.connected(map.get(s1), map.get(s2))) {
						uf.union(map.get(s1), map.get(s2));
						numClusters--;
					}
				}
			}
		}
		System.out.println(numClusters);

	}

}
