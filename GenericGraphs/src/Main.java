import java.util.ArrayList;
import java.util.List;

public class Main {

	/*
	 * A string consists of ‘0’, ‘1’ and '?'.
	 * The question mark can be either '0' or '1'.
	 * Find all possible combinations for a string.
	 */
	public static void main(String[] args) {
		System.out.println("hi");
		
		/*
		 * Unidirectional.
		 *     A
		 *    / \
		 *   B   C
		 *   |  / \
		 *   D E   F
		 *   |
		 *   G
		 */
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		Node f = new Node("F");
		Node g = new Node("G");
		a.getNeighbors().add(b);
		a.getNeighbors().add(c);
		b.getNeighbors().add(d);
		d.getNeighbors().add(g);
		c.getNeighbors().add(e);
		c.getNeighbors().add(f);
		
		
	}
	

}
