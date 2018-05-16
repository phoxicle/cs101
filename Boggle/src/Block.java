import java.util.ArrayList;
import java.util.List;

class Block {
		private char letter;
		private List<Block> neighbors;
		private boolean seenAsFirstLetter;
		
		public Block(char letter) {
			this.letter = letter;
			neighbors = new ArrayList<Block>();
		}
		
		public void addNeighbor(Block b) {
			neighbors.add(b);
		}
		
		public List<Block> getNeighbors() {
			return neighbors;
		}
		
		private Object getLetter() {
			return letter;
		}

		@Override
		public String toString() {
			return String.valueOf(letter);
		}
		
		public static String listToString(List<Block> blocks) {
			StringBuilder builder = new StringBuilder();
			for (Block b : blocks) {
				builder.append(b.getLetter());
			}
			return builder.toString();
		}
	}