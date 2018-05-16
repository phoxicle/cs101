import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {
	
	Set<String> words;
	
	public Dictionary() {
		words = new HashSet<String>();
		words.add("hi");
		words.add("hello");
		words.add("grab");
	}
	
	
	public boolean isWord(String w) {
		if (w.equals("hi") || w.equals("hello")
				|| w.equals("grab")) {
			return true;
		}
		return false;
	}
	
	public boolean isPrefix(String w) {
		// TODO
		if (w.equals("h") || w.equals("he") || w.equals("hel") || w.equals("hell")
				|| w.equals("g") || w.equals("gr") || w.equals("gra")) {
			System.out.println("Valid prefix? " + w + ": TRUE");
			return true;
		}
		System.out.println("Valid prefix? " + w + ": FALSE");
		return false;
	}


	// TODO use prefix tree
	public boolean isWord(List<Block> blocksTillNow) {
		return isWord(Block.listToString(blocksTillNow));
	}

	// TODO use prefix tree
	public boolean isPrefix(List<Block> blocksTillNow) {
		return isPrefix(Block.listToString(blocksTillNow));
	}
}
