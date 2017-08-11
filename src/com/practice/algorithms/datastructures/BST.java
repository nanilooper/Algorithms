package com.practice.algorithms.datastructures;

/**
 * bst to store values assosiated with keys
 */

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		public Key key;
		public Value value;
		private Node left, right;
		private int size;

		public Node(Key k, Value v, int s) {
			key = k;
			value = v;
			size = s;
		}
	}

	private Node insert(Node x, Key k, Value v) {
		if (x == null) {
			return new Node(k, v, 1);
		}
		if (k.compareTo(x.key) <= 0) {
			x.left = insert(x.left, k, v);
		} else {
			x.right = insert(x.right, k, v);
		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void insert(Key k, Value v) {
		root = insert(root, k, v);
	}

	public Value getValueOf(Key k) {
		if (k == null) {
			throw new IllegalArgumentException("key value should not be null");
		}
		return get(root, k);
	}

	private Value get(Node x, Key k) {
		if (x == null) {
			return null;
		}
		if (k.compareTo(x.key) < 0) {
			return get(x.left, k);
		} else if (k.compareTo(x.key) > 0) {
			return get(x.right, k);
		} else {
			return x.value;
		}
	}

	private int size(Node x) {
		if (x == null) {
			return 0;
		}
		return x.size;
	}

	public Value getMin() {
		Node min = getMin(root);
		return getValueOf(min.key);
	}

	private Node getMin(Node x) {
		if (x.left == null) {
			return x;
		} else {
			return getMin(x.left);
		}
	}

	public Value getMax() {
		Key max = getMaxKey(root);
		return getValueOf(max);
	}

	private Key getMaxKey(Node x) {
		if (x.right == null) {
			return x.key;
		} else {
			return getMaxKey(x.right);
		}
	}

	public void deleteKey(Key k) {
		root = deleteKey(root, k);
	}

	private Node deleteMin(Node x) {
		if (x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.size = size(x.left) + size(x.right) + 1;
		return x;

	}

	private Node deleteKey(Node x, Key k) {
		if (x == null) {
			return null;
		}
		int flag = k.compareTo(x.key);
		if (flag > 0) {
			x.right = deleteKey(x.right, k);
		} else if (flag < 0) {
			x.left = deleteKey(x.left, k);
		} else {
			if (x.left == null) {
				return x.right;
			}
			if (x.right == null) {
				return x.left;
			}
			Node t = x;
			Node min = getMin(x.right);
			x = min;
			x.left = t.left;
			x.right = deleteMin(t.right);

		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	public static void main(String[] args) {
		BST<Integer, String> bst = new BST<>();
		bst.insert(1, "rayudu");
		bst.insert(10, "r");
		bst.insert(3, "y");
		bst.deleteKey(1);
		System.out.println(bst.getMin());
	}
}
