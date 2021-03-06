package binary;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("hi");
		
		// setup BST
		BSTNode bst = new BSTNode(0);
		List<Integer> toAdd = Arrays.asList(1,-5,10,-2,3,-3, -8);
		for (int i=0; i < toAdd.size(); i++) {
			bst.add(new BSTNode(toAdd.get(i)));
		}
		//bst.print();
		
		// Invert
		//bst.invert();
		//bst.print();
		
		// Print left view
		//bst.printLeftView();
		
		// setup regular tree
		/*
		 *             4
		 *       -5         9
		 *    10    6     1
		 *         7
		 */
		Node tree = new Node(4);
		tree.left = new Node(-5);
		tree.right = new Node(9);
		tree.left.left = new Node(10);
		tree.left.right = new Node(6);
		tree.right.left = new Node(1);
		tree.left.right.left = new Node(7);
		tree.print();
		System.out.println("Inorder: ");
		tree.printInorder();
		System.out.println("\nPreorder: ");
		tree.printPreorder();
		Node fromInorderAndPreorder = tree.fromInorderAndPreorder(tree.inorder(), tree.preorder());
		fromInorderAndPreorder.print();

		// Find LCAs
		//System.out.println("LCA was: " + tree.findLCA(7, 1));
	}
	

}
