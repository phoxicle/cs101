package matrix;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	private static int[][] m;

	public static void main(String[] args) {
		System.out.println("hi");
		
		// setup matrix.
		// invariant: below and right are larger 
		m = new int[][] {
			{0, 2, 4, 20},
			{1, 3, 9, 13},
			{2, 4, 10, 15},
			{5, 6, 15, 16}
		};
		
		System.out.println("6? (should be yes): " + String.valueOf(find(6,0,0)));
		System.out.println("7? (should be no): " + String.valueOf(find(7,0,0)));
		System.out.println("13? (should be yes): " + String.valueOf(find(13,0,0)));
		
	}
	
	public static boolean find(int needle, int i, int j) {
		if (i > m.length - 1 || j > m[0].length - 1) {
			return false;
		}
		
		// check current number
		int current = m[i][j];
		
		// if equal to needle, return true
		if (current == needle) {
			return true;
		}
		
		// if larger than needle, return false
		if (current > needle) {
			return false;
		}
		
		// else recurse on below and right
		return find(needle, i+1, j) || find(needle, i, j+1);
	}
	

}
