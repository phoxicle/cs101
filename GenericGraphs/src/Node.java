import java.util.Set;

public class Node {
	
	String value;
	Set<Node> neighbors;
	public Node(String string) {
		value = string;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Set<Node> getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(Set<Node> neighbors) {
		this.neighbors = neighbors;
	}

}
