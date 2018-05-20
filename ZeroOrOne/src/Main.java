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
		
		String in = "01?10?";
		
		System.out.println(getPossibleStrings("", in));
		
	}
	
	public static List<String> getPossibleStrings(String prefix, String pattern) {
		List<String> list = new ArrayList<String>();
		
		// if pattern is empty, return prefix
		if (pattern.length() == 0) {
			list.add(prefix);
			return list;
		}
		// recurse with next character. If it's a ?, we get two new strings.
		char c = pattern.charAt(0);
		if (c == '?') {
			list.addAll(getPossibleStrings(prefix + "0", pattern.substring(1)));
			list.addAll(getPossibleStrings(prefix + "1", pattern.substring(1)));
		} else {
			list.addAll(getPossibleStrings(prefix + c, pattern.substring(1)));
		}
		return list;
	}
}
