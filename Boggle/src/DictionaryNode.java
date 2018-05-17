import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DictionaryNode {
	
	private char letter;
	private boolean isWord;
	private Map<Character, DictionaryNode> children;
	
	
	public DictionaryNode(char c) {
		letter = c;
		children = new HashMap<Character, DictionaryNode>();
	}
	public DictionaryNode() {
		children = new HashMap<Character, DictionaryNode>();
	}
	public char getLetter() {
		return letter;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	}
	public Map<Character, DictionaryNode> getChildren() {
		return children;
	}
	public void addChild(DictionaryNode child) {
		this.children.put(child.getLetter(), child);
	}
	
	@Override
	public String toString() {
		return String.valueOf(letter);
	}
	public boolean isWord() {
		return isWord;
	}
	public void setIsWord(boolean isWord) {
		this.isWord = isWord;
	}
	
	

}
