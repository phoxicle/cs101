package binary;

public class BSTNode extends Node {
	
	public BSTNode(int string) {
		super(string);
	}

	public void add(BSTNode n) {
		// Left is <= current.
		if (n.getValue() <= this.getValue()) {
			if (this.left != null) ((BSTNode) this.left).add(n);
			else this.left = n;
		} else {
			if (this.right != null) ((BSTNode) this.right).add(n);
			else this.right = n;
		}
	}

}
