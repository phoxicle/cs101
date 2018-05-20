package binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node {
	
	int value;
	Node left;
	Node right;
	
	public Node(int string) {
		value = string;
	}
	
	public void printLeftView() {
		if (this.left != null) {
			System.out.print(this.left.toString() + ", ");
			this.left.printLeftView();
		}
		if (this.right != null) {
			this.right.printLeftView();
		}
	}
	
	public void invert() {
		if (this.left != null)
			this.left.invert();
		if (this.right != null)
			this.right.invert();
		
		Node tmp = this.left;
		this.left = this.right;
		this.right = tmp;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
	
	public void print() {
		Node[] firstLayer = new Node[80];
		firstLayer[40] = this;
		print(firstLayer, firstLayer.length / 4);
	}
	
	public void print(Node[] layer, int step) {
		Node[] nextLayer = new Node[layer.length];
		
		boolean moreNodes = false;
		
		for (int i = 0; i < layer.length; i++) {
			Node n = layer[i];
			if (n == null) {
				System.out.print(" ");
			} else {
				System.out.print(n);
				
				// Add children to next layer
				if (n.getLeft() != null) {
					nextLayer[i - step] = n.getLeft();
					moreNodes = true;
				}
				if (n.getRight() != null) {
					nextLayer[i + step] = n.getRight();
					moreNodes = true;
				}
			}
		}
		
		System.out.println();
		if (moreNodes) {
			print(nextLayer, step / 3);
		}
	}

	public Node findGCD(int i, int j) {
		// Searching is linear, so expect linear solution.
		
		// Find i and j. Then find intersection of parents.
		List<Node> iNodes = this.find(i);
		List<Node> jNodes = this.find(j);
		if (iNodes != null) {
			for (Node n : iNodes) System.out.print(n + "->");
			System.out.println(". Found iNode with value " + i);
			
		}
		if (jNodes != null) {
			for (Node n : jNodes) System.out.print(n + "->");
			System.out.println(". Found jNode with value " + j);
		}
		
		// Traverse from top of parents to find divergence
		for (int idx = 0; idx < iNodes.size() && idx < jNodes.size(); idx++) {
			if (iNodes.get(idx).equals(jNodes.get(idx))) {
				continue;
			}
			return iNodes.get(idx - 1);
		}
		
		return null;
	}

	/*
	 * Returns list of node plus parents.
	 */
	private List<Node> find(int i) {
		List<Node> nodes = new LinkedList<Node>();
		nodes.add(this);
		if (this.value == i) {
			return nodes;
		}
		if (this.left != null) {
			List<Node> chain = this.left.find(i);
			if (chain != null) {
				nodes.addAll(chain);
				return nodes;
			}
		}
		if (this.right != null) {
			List<Node> chain = this.right.find(i);
			if (chain != null) {
				nodes.addAll(chain);
				return nodes;
			}
		}
		
		return null;
	}

}
