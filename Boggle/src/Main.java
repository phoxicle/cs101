
public class Main {

	
	public static void main(String[] args) {
		System.out.println("Made board:");
		
		char[][] letters = new char[][]{
				{'h', 'g', 'r', 'i'},
				{'i', 'e', 'l', 'a'},
				{'c', 'l', 'p', 'b'},
				{'t', 'o', 'w', 'f'}
				};
				
		Board board = Board.fromCharArray(letters);
		
		System.out.println(board.findWords());
		
	}
}
