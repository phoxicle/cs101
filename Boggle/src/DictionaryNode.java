import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DictionaryNode {
	
	private char letter;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + letter;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DictionaryNode other = (DictionaryNode) obj;
		if (letter != other.letter)
			return false;
		return true;
	}
	public void addChild(char currentChar) {
		addChild(new DictionaryNode(currentChar));
	}
	

}
