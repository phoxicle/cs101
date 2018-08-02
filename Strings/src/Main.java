import java.util.ArrayList;
import java.util.List;

public class Main {

	/*
	 * A string consists of ‘0’, ‘1’ and '?'.
	 * The question mark can be either '0' or '1'.
	 * Find all possible combinations for a string.
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("hi");
		
//		String in = "01?10?";
//		System.out.println(getPossibleStrings("", in));
		
		String[] ins = new String[] {
			"12+5+6", "12+-5+6", "12+5-", "+5+6",
			"(1+2)*{[90*3]-[67+8-9]}", 
			"(1+2){[90*3]-[67+8-9]}", "(1+2)*{[90*3-[67+8-9]}"
		};
		
		for (String in : ins) {
			System.out.println(in + ": " + validateExpression(in));
		}
	}

	private static boolean validateExpression(String string) throws Exception {
//		System.out.println("Checking exp: " + string);
		
		String isNumber = "[0-9]";
		String isOpen = "[\\(\\[\\{]";
		String isOp = "[\\+\\-\\*/]";
		for (int i=0; i < string.length(); i++) {
			String c = String.valueOf(string.charAt(i));
			String cNext = "";
			boolean atEnd = true;
			boolean atBegin = true;
			if (i > 0) {
				atBegin = false;
			}
			if (i + 1 < string.length()) {
				atEnd = false;
				cNext = String.valueOf(string.charAt(i+1));
			}
				
			// if number, next can be number or +,-,*, or end
			if (c.matches(isNumber)) {
				if (atEnd) {
					return true;
				}
				if (!(cNext.matches(isNumber) || cNext.matches(isOp))) {
					return false;
				}
			}
			
			// if op, cannot be at beginning, next must be number or open
			if (c.matches(isOp)) {
				if (atBegin || !(cNext.matches(isNumber) || cNext.matches(isOpen))) {
					return false;
				}
			}
			
			// if open bracket, then content between matching close must be valid.
			if (c.matches(isOpen)) {
				if (atEnd) return false;
				// Find index of next matching close
				int idxMatchingClose = -1;
				if (c.equals("(")) {
					idxMatchingClose = string.substring(i).indexOf(")");
				} else if (c.equals("{")) {
					idxMatchingClose = string.substring(i).indexOf("}");
				} else if (c.equals("[")) {
					idxMatchingClose = string.substring(i).indexOf("]");
				} else {
					throw new Exception("unhandled open bracket found");
				}
				if (idxMatchingClose == -1) {
					return false;
				}
				
				String leftSubExp = string.substring(i+1, idxMatchingClose);
				boolean leftValid = validateExpression(leftSubExp);
				if (!leftValid) return false;
				
				boolean closeAtEnd = (string.length() == idxMatchingClose + 1);
				// Left is valid and no right side to check
				if (closeAtEnd) return true;
				
				// Check right. Next character must be op and right exp valid
				boolean enoughCharsLeft = (string.length() > idxMatchingClose + 3);
				if (enoughCharsLeft) {
					return String.valueOf(string.charAt(idxMatchingClose+1)).matches(isOp)
							&& validateExpression(string.substring(idxMatchingClose + 2));
				}
				return false;
			}
		}
		return true;
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
