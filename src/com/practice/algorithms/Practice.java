package com.practice.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Practice {

	public static void main(String[] args) throws FileNotFoundException {
Scanner scan = new Scanner(new File("CLUSTERING_BITS.txt"));
BitSet s1 = new BitSet(24);
BitSet s2 = new BitSet(24);
BitSet s3 = (BitSet)s1.clone();
s3.set(1);
HashSet<BitSet> s = new HashSet<>();
s.add(s1);

scan.nextLine();



System.out.println(s3);
	}

}
