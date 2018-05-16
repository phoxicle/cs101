import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Board {
	
	private Block origin;
	private int dimension;
	
	public static Board fromCharArray(char[][] letters) {
		int dim = letters.length; // Assume square
		
		// First pass: convert to blocks.
		Block[][] blocks = new Block[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				blocks[i][j] = new Block(letters[i][j]);
				System.out.print(letters[i][j] + " ");
			}
			System.out.println();
		}
		
		// Second pass: make graph
		Board board = new Board();
		board.dimension = dim;
		board.origin = blocks[0][0];
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				Block b = blocks[row][col]; 

				if (row != 0) { // add up node
					b.addNeighbor(blocks[row-1][col]);
					if (col != 0) { // add up-left
						b.addNeighbor(blocks[row-1][col-1]);
					}
					if (col != dim-1) { // add up-right
						b.addNeighbor(blocks[row-1][col+1]);
					}
				}
				
				if (row != dim-1) { // add down node
					b.addNeighbor(blocks[row+1][col]);
					if (col != 0) { // add down-left
						b.addNeighbor(blocks[row+1][col-1]);
					}
					if (col != dim-1) { // add down-right
						b.addNeighbor(blocks[row+1][col+1]);
					}
				}
				if (col != 0) { // add left
					b.addNeighbor(blocks[row][col-1]);
				}
				if (col != dim - 1) { // add right
					b.addNeighbor(blocks[row][col+1]);
				}
			}
		}
		
		return board;
	}

	public List<String> findWords() {
		System.out.println("Finding words...");
		List<String> foundWords = new ArrayList<String>();
		Set<Block> originBlocksProcessed = new HashSet<Block>();
		Queue<Block> originBlocksToProcess = new LinkedList<Block>();
		Dictionary dictionary = new Dictionary();
		
		// Cycle through each block
		originBlocksToProcess.add(origin);
		while (!originBlocksToProcess.isEmpty()) {
			// Don't process the same block twice
			Block origin = originBlocksToProcess.remove();
			if (originBlocksProcessed.contains(origin)) continue;
			
			List<Block> blocksTillNow = new ArrayList<Block>();
			blocksTillNow.add(origin);
			foundWords.addAll(findWords(blocksTillNow, dictionary));
			
			// Traverse the graph
			originBlocksProcessed.add(origin);
			originBlocksToProcess.addAll(origin.getNeighbors());
		}
		
		return foundWords;
	}
	
	// Given the current chain of letters, return valid words
	public List<String> findWords(List<Block> blocksTillNow, Dictionary dict) {
		String currentString = Block.listToString(blocksTillNow);
		System.out.println("Checking blocks till now: " + currentString);
		
		List<String> foundWords = new ArrayList<String>();
		if (dict.isWord(blocksTillNow)) {
			System.out.println("Found word: " + currentString);
			foundWords.add(currentString);
		}
		if (dict.isPrefix(blocksTillNow)) {
			// recurse with neighbors
			Block last = blocksTillNow.get(blocksTillNow.size() - 1);
			for (Block neighbor : last.getNeighbors()) {
				System.out.println("Checking neighbor of " + last + ": " + neighbor);
				// TODO don't copy list
				List<Block> withNeighbor = new ArrayList<Block>(blocksTillNow);
				withNeighbor.add(neighbor);
				foundWords.addAll(this.findWords(withNeighbor, dict));
			}
		}
		
		return foundWords;
	}
	


	
}
