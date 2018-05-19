import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Dictionary {
	
	DictionaryNode root;
	
	public Dictionary() {
		// Empty root node, has 26 children
		root = new DictionaryNode();
		
//		List<String> words = new ArrayList<String>();
//		words.add("hello");
//		words.add("hi");
//		words.add("hip");
//		words.add("grab");
//		words.add("low");
//		for (String word : words) {
//			this.add(word);
//		}
//		System.out.println(this.toString());
		
		System.out.println("Getting all the words");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("/usr/share/dict/words"));
			String word;
			while((word = reader.readLine()) != null) {
				this.add(word);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void add(String word) {
		DictionaryNode currentNode = root;
		for (int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			DictionaryNode nextNode;
			
			// If char not in children of current position, create it
			if (!currentNode.getChildren().containsKey(currentChar)) {
				nextNode = new DictionaryNode(currentChar);
				currentNode.addChild(nextNode);
			} else {
				nextNode = currentNode.getChildren().get(currentChar);
			}
			
			// Traverse
			currentNode = nextNode;
		}
		// The current node is the end of the word
		currentNode.setIsWord(true);
	};
	
	public boolean isWord(List<Block> blocksTillNow) {
		return pathExists(blocksTillNow, true);
	}
	
	private boolean pathExists(List<Block> blocksTillNow, boolean wordOnly) {
		DictionaryNode currentNode = root;
		for (int i=0; i < blocksTillNow.size(); i++) {
			char currentChar = blocksTillNow.get(i).getLetter();
			if (!currentNode.getChildren().containsKey(currentChar)) {
				return false;
			}
			currentNode = currentNode.getChildren().get(currentChar);
		}
		
		// For exact word match, current node must be leaf
		return !wordOnly || currentNode.isWord();
	}

	public boolean isPrefix(List<Block> blocksTillNow) {
		return pathExists(blocksTillNow, false);
	}
	
	@Override
	public String toString() {
		HashSet<DictionaryNode> seen = new HashSet<DictionaryNode>();
		Queue<DictionaryNode> toPrint = new LinkedList<DictionaryNode>();
		toPrint.add(root);
		StringBuilder builder = new StringBuilder();
		while (!toPrint.isEmpty()) {
			DictionaryNode node = toPrint.remove();
			if (seen.contains(node)) continue;
			
			builder.append(node + " has children: ");
			for (DictionaryNode child : node.getChildren().values()) {
				builder.append(child);
				toPrint.add(child);
			}
			builder.append("\n");
		}
		
		return builder.toString();
	}
	
}
