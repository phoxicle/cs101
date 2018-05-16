import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Dictionary {
	
	DictionaryNode root;
	
	public Dictionary() {
		// Empty root node, has 26 children
		root = new DictionaryNode();
		
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
			
			// If char not in tree at current position, create it
			if (!currentNode.getChildren().containsKey(currentChar)) {
				nextNode = new DictionaryNode(currentChar);
				currentNode.addChild(currentChar);
			} else {
				nextNode = currentNode.getChildren().get(currentChar);
			}
			
			// Traverse
			currentNode = nextNode;
		}
	};
	
	public boolean isWord(List<Block> blocksTillNow) {
		return isWord(blocksTillNow, false);
	}
	
	private boolean isWord(List<Block> blocksTillNow, boolean prefixAlsoGood) {
		DictionaryNode currentNode = root;
		for (int i=0; i < blocksTillNow.size(); i++) {
			char currentChar = blocksTillNow.get(i).getLetter();
			if (!currentNode.getChildren().containsKey(currentChar)) {
				return false;
			}
			currentNode = currentNode.getChildren().get(currentChar);
		}
		
		// Current node must be leaf
		return prefixAlsoGood || currentNode.getChildren().isEmpty();
	}

	public boolean isPrefix(List<Block> blocksTillNow) {
		return isWord(blocksTillNow, true);
	}
}
