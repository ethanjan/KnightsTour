import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class KnightsTour {
	// This creates the chessboard and generates various movesets for the knight.
	private int[][] chessBoard = new int[9][9];
	private int[] vertical = {0, -2, -1, 1, 2, 2, 1, -1, -2};
	private int[] horizontal = {0, 1, 2, 2, 1, -1, -2, -2, -1};
	// This sets the initial position of the knights.
	private int currentRow = 1;
	private int currentColumn = 1;
	
	// This creates an array for the accessibility values for the Warnsdorff's implemntation.
	private int[][] accessibilityValues = new int[9][9];
	ArrayList<Integer> numbers = new ArrayList <Integer>();
	public KnightsTour() {
		loadAccessFile();
		for (int i = 0; i < 9; i++) {
			numbers.add(i);
		}
		
	}
	
	public int tour() {
		// This sets up the initial variables necessary to complete the Knight's Tour.
		int count = 1;
		int moveChoice = 0;
		int moveChoiceIndex = 0;
		Random rand = new Random();
		int lowestAccessibilityValueIndex;
		int lowestAccessibilityValue;
		ArrayList<Integer> potentialMatches = new ArrayList <Integer>();
		// This ensures that the program recognizes that the first position on the chessboard and the accessibility values 2D-array is taken.
		chessBoard[currentRow][currentColumn]+=count;
		accessibilityValues[currentRow][currentColumn] = 0;
		
		while (count < 64) {
			// This ensures that the program can find the lowest accessibility value.
			ArrayList<Integer> numbers2 = new ArrayList <Integer>(numbers);
			lowestAccessibilityValueIndex = Integer.MAX_VALUE;
			lowestAccessibilityValue = Integer.MAX_VALUE;
			
			for (int i = 1; i < 9; i++) {
				// This ensures that the moves would not move the knight outside of the chessboarrd.
				if ((currentRow+vertical[i]) > 0 && (currentColumn+horizontal[i]) > 0 && (currentRow+vertical[i])<chessBoard.length && (currentColumn+horizontal[i])<chessBoard.length) {
					// This ensures that the knight does not move into a location where it has already been.
					if (accessibilityValues[(currentRow+vertical[i])][(currentColumn+horizontal[i])] >= 0) {
						// This checks if it meets the condition for being the lowest accessibility value.
						if (accessibilityValues[(currentRow+vertical[i])][(currentColumn+horizontal[i])] == lowestAccessibilityValue) {
							potentialMatches.add(i);
						}
						// This checks if there is a lower accesibility value;
						if (accessibilityValues[(currentRow+vertical[i])][(currentColumn+horizontal[i])] < lowestAccessibilityValue) {
							lowestAccessibilityValueIndex = i;
							lowestAccessibilityValue = accessibilityValues[(currentRow+vertical[i])][(currentColumn+horizontal[i])];
							potentialMatches.clear();
							potentialMatches.add(i);
						}
					}
				}
			}
			
			// This stops the program if the knight cannot move anywhere.
			if (lowestAccessibilityValue == Integer.MAX_VALUE) {
				break;
			}
			
			// If there is a tie, the knight should choose a random location to ensure that the program does not infinitely loop.
			if (potentialMatches.size() > 1) {
				
				int chosenIndex = potentialMatches.get(rand.nextInt(potentialMatches.size()));
				
				lowestAccessibilityValueIndex = chosenIndex;
				potentialMatches.clear();
			}
			
			// This moves the knight and alters the values on the chessboard and accessibility values board.
			currentRow+=vertical[lowestAccessibilityValueIndex];
			currentColumn+=horizontal[lowestAccessibilityValueIndex];
			count++;
			chessBoard[currentRow][currentColumn]+=count;
			accessibilityValues[currentRow][currentColumn] = 0;
			
			//This decreases all of the remaining squares on the accessibility values 2D-array.
			for (int i = 1; i < vertical.length; i++) {
				if (currentRow+vertical[i] > 0 && currentRow+vertical[i] < accessibilityValues.length && currentColumn+horizontal[i] > 0 && currentColumn+horizontal[i] < accessibilityValues.length) {
					accessibilityValues[currentRow+vertical[i]][currentColumn+horizontal[i]]--;
				}	
			}
		}
			return count;
		}
	// This resets the entire chessboard and the knight's position if a solution cannot be found.
	public void clearBoard() {
		chessBoard = new int[9][9];
		loadAccessFile();
		currentRow = 1;
		currentColumn = 1;
	}
	
	// This loads the accessibility values from the access.txt file into the 2D-array.
	public void loadAccessFile() {
		try {
			Scanner inFile = new Scanner (new File("access.txt"));
			for (int row = 1; row < accessibilityValues.length; row++) {
				for (int col = 1; col < accessibilityValues[row].length; col++) {
					accessibilityValues[row][col] = inFile.nextInt();
				}
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	// This prints the chessboard.
	public void printBoard() {
		System.out.printf("%1s", "");
		for (int i = 0; i < chessBoard.length-1; i++) {
			System.out.printf("%5d", i+1);
		}
		System.out.println("\n");
		for (int row = 1; row < chessBoard.length; row++) {
			System.out.printf("%-1d", row);
			for (int col = 1; col < chessBoard[row].length; col++) {
				if (col == 1) {
					System.out.printf("%5d", chessBoard[row][col]);
				} else {
					System.out.printf("%5d", chessBoard[row][col]);
				}
			}
			System.out.println("\n");
		}
	}
	
	public static void main(String[] args) {
		//This ensures that the program showcases solutions instead of failures.
		KnightsTour theKnightsTour = new KnightsTour();
		int count = 0;
		while (count < 64) {
			theKnightsTour.clearBoard();
			count = theKnightsTour.tour();
			
		}
		theKnightsTour.printBoard();
		System.out.println(count + " squares were visited.");
		System.out.println("The board has been filled.");
	}
}