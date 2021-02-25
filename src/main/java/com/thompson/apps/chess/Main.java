package com.thompson.apps.chess;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.thompson.apps.chess.board.Cell;
import com.thompson.apps.chess.board.ChessBoard;
import com.thompson.apps.chess.board.Tile;
import com.thompson.apps.chess.pieces.AbstractPiece;
import com.thompson.apps.chess.pieces.Bishop;
import com.thompson.apps.chess.pieces.King;
import com.thompson.apps.chess.pieces.Knight;
import com.thompson.apps.chess.pieces.Pawn;
import com.thompson.apps.chess.pieces.Queen;
import com.thompson.apps.chess.pieces.Rook;

/**
 * Main Class
 * 
 * @author RobertThompson
 */
public class Main {
	/* Chess Board Reference */
	private ChessBoard board = null;

	/* Default White Pieces and Locations */
	private String defaultWhitePieces = null;

	/* Default Black Pieces and Locations */
	private String defaultBlackPieces = null;

	/* Scanner */
	private Scanner scanner = null;

	/* Flag to Continue Running */
	private boolean continueRunning = true;

	/**
	 * Default constructor for the Main class. It initializes the ChessBoard, sets
	 * default pieces, and initializes a Scanner object for user-input.
	 */
	public Main() {
		board = new ChessBoard();

		defaultWhitePieces = "Ra1, Nb1, Bc1, Qd1, Ke1, Bf1, Ng1, Rh1, Pa2, Pb2, Pc2, Pd2, Pe2, Pf2, Pg2, Ph2";
		defaultBlackPieces = "Ra8, Nb8, Bc8, Qd8, Ke8, Bf8, Ng8, Rh8, Pa7, Pb7, Pc7, Pd7, Pe7, Pf7, Pg7, Ph7";

		scanner = new Scanner(System.in, "UTF-8");
	}

	/**
	 * Prompts the user with two choices for the process: 
	 * (1) Validation of the entire default Chess Board at the start. 
	 * (2) Custom Chess Board Validation based on user-input
	 * 
	 * Based on user selection, different prompts/output will be provided
	 * 
	 * NOTE: Exception handling is in place for bad / incorrect user input from the
	 * start of the program to the end of the program.
	 */
	private void start() {
		do {
			try {
				System.out.println("Welcome to SE-567 Chess Validation!");

				System.out.println("Please choose one of the following options:");
				System.out.println("(1) Validation for the Default Chess Board");
				System.out.println("(2) Custom Chess Board Validation");

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					validateDefaultChessBoard();
					break;
				case 2:
					validateCustomChessBoard();
					break;
				default:
					System.out.println("Invalid Input - please enter 1 or 2.\n");
					break;
				}

				promptRunAgain();
			} catch (InputMismatchException e) {
				System.err.println("Please enter an integer value.\n");
				scanner.nextLine();
			} catch (Exception e) {
				System.err.println(e);
				// Call reset here because we caught an exception during one of the following
				// cases and need to clean-up the data for future runs:
				// (1) Empty user input for White or Black Pieces
				// (2) No piece being entered to validate
				// (3) Piece entered to be validated is not in the List of White or Black Pieces
				reset();
			}
		} while (continueRunning);
	}

	/**
	 * Method that does the following actions: 
	 * (1) Prompts the user for input for the White and Black Pieces for the custom board 
	 * (2) Prompts the user for the piece to validate. 
	 * (3) Calls validation methods to verify and print out the piece's valid moves.
	 * 
	 * @throws Exception
	 */
	private void validateCustomChessBoard() throws Exception {
		// Step 1. Prompt user for white pieces and read the value
		System.out.println("Please enter White Pieces in a comma-separated format: ");
		String customWhitePieces = scanner.nextLine();

		// Step 2. Set the local list of white pieces
		setPieces(customWhitePieces, true);

		// Step 3. Prompt user for black pieces and read the value
		System.out.println("Please enter Black Pieces in a comma-separated format: ");
		String customBlackPieces = scanner.nextLine();

		// Step 4. Set the local list of black pieces
		setPieces(customBlackPieces, false);

		// Step 5. Set the custom chess board
		board.setCustomBoard();

		// Step 6. Prompt the user for piece to validate and read the value
		System.out.println("Please enter a piece to get its moves: ");
		String stringPiece = scanner.nextLine();

		// Step 7. Print the current state of the board
		System.out.println("\n " + board.printBoard());

		// Step 8. Set the piece to move based on the user input
		AbstractPiece piece = getPieceToMove(stringPiece);

		// Step 9. Get the valid moves for the current piece
		List<Cell> moves = piece.getValidMoves(board.getChessBoard());

		// Step 10. Print the valid moves in human-readable format
		printMoves(piece, moves);
	}

	/**
	 * Method that does the following actions: 
	 * (1) Creates a new instance of the ChessBoard and sets the default pieces and their locations.
	 * (2) Sets the list of White and Black Pieces stored in the ChessBoard object to have the default
	 * pieces and their locations. 
	 * (3) Calls validation methods to verify and print out each piece's valid moves
	 * 
	 * @throws Exception
	 */
	private void validateDefaultChessBoard() throws Exception {
		// Step 1. Create new Chess Board and set it to the default layout
		board = new ChessBoard();
		board.setDefaultBoard();

		// Step 2. Print current state of the board
		System.out.println("\n" + board.printBoard());

		// Step 3. Set local list of white pieces to the default
		System.out.println("WHITE PIECES: ");
		setPieces(defaultWhitePieces, true);

		// Step 4. Get valid moves for all default layout white pieces
		validateDefaultWhitePieces();

		// Step 5. Set the local list of black pieces to the default
		System.out.println("BLACK PIECES: ");
		setPieces(defaultBlackPieces, false);

		// Step 6. Get valid moves for all default layout black pieces
		validateDefaultBlackPieces();
	}

	/**
	 * Validates each white piece's move by calling the abstract getValidMoves
	 * method with the current state of the board
	 * 
	 * @throws Exception
	 */
	private void validateDefaultWhitePieces() throws Exception {
		// Rook
		String pieceToValidate = "Ra1";
		AbstractPiece piece = getPieceToMove(pieceToValidate);
		List<Cell> moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Knight
		pieceToValidate = "Nb1";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Bishop
		pieceToValidate = "Bc1";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Bishop
		pieceToValidate = "Qd1";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// King
		pieceToValidate = "Ke1";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Bishop
		pieceToValidate = "Bf1";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Knight
		pieceToValidate = "Ng1";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Rook
		pieceToValidate = "Rh1";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Pawns
		pieceToValidate = "Pa2";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pb2";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pc2";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pd2";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pe2";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pf2";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pg2";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Ph2";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);
	}

	/**
	 * Validates each black piece's move by calling the abstract getValidMoves
	 * method with the current state of the board
	 * 
	 * @throws Exception
	 */
	private void validateDefaultBlackPieces() throws Exception {
		// Rook
		String pieceToValidate = "Ra8";
		AbstractPiece piece = getPieceToMove(pieceToValidate);
		List<Cell> moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Knight
		pieceToValidate = "Nb8";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Bishop
		pieceToValidate = "Bc8";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Bishop
		pieceToValidate = "Qd8";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// King
		pieceToValidate = "Ke8";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Bishop
		pieceToValidate = "Bf8";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Knight
		pieceToValidate = "Ng8";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Rook
		pieceToValidate = "Rh8";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		// Pawns
		pieceToValidate = "Pa7";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pb7";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pc7";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pd7";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pe7";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pf7";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Pg7";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);

		pieceToValidate = "Ph7";
		piece = getPieceToMove(pieceToValidate);
		moves = piece.getValidMoves(board.getChessBoard());
		printMoves(piece, moves);
	}

	/**
	 * Parses the comma-separated String and converts it to a List of Strings. Then
	 * converts the String to an Abstract Piece based on the boolean value passed in
	 * as a parameter.
	 * 
	 * @param String  pieces - Comma-Separated String of Pieces
	 * @param boolean isWhite - true if white, false otherwise
	 * @throws Exception
	 */
	protected void setPieces(String pieces, boolean isWhite) throws Exception {
		// Step 1. Check to see if pieces is null or is equal to an empty string
		if (null != pieces && !pieces.equals("")) {
			// Step 2. Convert comma-separated String to List of Strings
			List<String> listPieces = Stream.of(pieces.split(",", -1)).map(String::trim).collect(Collectors.toList());

			// Step 3. Iterate over each String in the List
			for (String s : listPieces) {
				// Step 4. Convert the String to an AbstractPiece
				AbstractPiece p = convertStringToPiece(s, isWhite);

				// Step 5. If the piece is not null, add to either the local list of white or
				// black pieces
				if (null != p) {
					if (isWhite) {
						board.addWhitePiece(p);
					} else {
						board.addBlackPiece(p);
					}
				} else {
					String message = "Piece " + s + " was unable to be converted to a Chess Piece. Invalid entry!\n";
					throw new Exception(message);
				}
			}

			// Step 6. Print the white or black pieces based on the isWhite parameter
			if (isWhite) {
				System.out.println(board.getWhitePieces());
			} else {
				System.out.println(board.getBlackPieces());
			}
		}
		// Step 2. If the pieces parameter is null or an empty string, throw an
		// exception so we can prompt the user to restart the program
		else {
			String message = "No " + (isWhite ? "White" : "Black") + " pieces have been entered!\n";
			throw new Exception(message);
		}
	}

	/**
	 * Converts the String to a Piece and then checks to see if the piece is white
	 * or black. If neither, nothing will be validated
	 * 
	 * @param String pieceString
	 * @throws Exception
	 */
	protected AbstractPiece getPieceToMove(String pieceString) throws Exception {
		// Step 1. Check to see if pieces is null or is equal to an empty string
		if (null != pieceString && !pieceString.equals("")) {
			// Step 2. Create two temp white and black pieces based on the pieceString
			AbstractPiece wp = convertStringToPiece(pieceString, true);
			AbstractPiece bp = convertStringToPiece(pieceString, false);

			// Step 3. If the temporary white and black piece are both not null,
			// check to see if the local lists contain the temporary pieces. Otherwise,
			// throw an exception if they are null or if the lists do not contain
			// the temporary values
			if (null != wp && null != bp) {
				if (board.getWhitePieces().contains(wp)) {
					return wp;
				} else if (board.getBlackPieces().contains(bp)) {
					return bp;
				} else {
					String message = "Piece " + wp.toString()
							+ " is not found in either the White or Black List of Pieces!\n";
					throw new Exception(message);
				}
			} else {
				String message = "Piece " + pieceString
						+ " was unable to be converted to a Chess Piece. Invalid entry!";
				throw new Exception(message);
			}
		}
		// Step 2. If the pieces parameter is null or an empty string, throw an
		// exception so we can prompt the user to restart the program
		else {
			String message = "No piece has been entered!\n";
			throw new Exception(message);
		}
	}

	/**
	 * Converts the String to a piece based on the first character (Piece Type) and
	 * then iterating over the Tiles to get the location provided in the String. 
	 * 
	 * Ex. Ra1 would return Rook at Tile A1
	 * 
	 * @param String  piece - Piece to be converted
	 * @param boolean isWhite - True if white, false otherwise
	 * @return AbstractPiece p
	 */
	protected AbstractPiece convertStringToPiece(String piece, boolean isWhite) {
		AbstractPiece p = null;

		// Step 1. Check to see if the String is of length 3
		// to ensure we have the combination of a Piece and a tile
		// where it is placed
		if (piece.length() == 3) {
			// Step 2. Get First Letter of Piece (Ex. Ra1 - would return R)
			String pieceString = piece.substring(0, 1).toUpperCase();
			int row = -1;
			int column = -1;

			// Step 3. Get Tile Location
			String tileString = piece.substring(1).toUpperCase();
			for (Tile t : Tile.values()) {
				if (t.getName().equalsIgnoreCase(tileString)) {
					row = t.getX();
					column = t.getY();
					break;
				}
			}

			// Step 4. If the row or column is -1, then the user entered an invalid tile
			// that is not on the chess board. Return null
			if (row == -1 || column == -1) {
				return null;
			}

			// Step 5. Create Piece Based on First Letter of String
			if (pieceString.equals("K")) {
				p = new King(isWhite, row, column);
			} else if (pieceString.equals("Q")) {
				p = new Queen(isWhite, row, column);
			} else if (pieceString.equals("R")) {
				p = new Rook(isWhite, row, column);
			} else if (pieceString.equals("B")) {
				p = new Bishop(isWhite, row, column);
			} else if (pieceString.equals("N")) {
				p = new Knight(isWhite, row, column);
			} else if (pieceString.equals("P")) {
				p = new Pawn(isWhite, row, column);
			}
		}

		// Step 2/6. Returns Valid Piece or Null
		return p;
	}

	/**
	 * Prints the valid moves for the piece in human-readable format to align with
	 * an actual chess board piece designation
	 * 
	 * @param List<Cell> validMoves - List of Valid Moves
	 */
	protected void printMoves(AbstractPiece piece, List<Cell> validMoves) {
		boolean hasMoves = true;
		List<Tile> moves = new ArrayList<Tile>();

		String color = (piece.isWhite() ? "WHITE" : "BLACK");
		System.out.println("LEGAL MOVES FOR " + color + " " + piece.toString() + ":");

		if (null == validMoves || validMoves.isEmpty()) {
			System.out.println("No legal moves!");
			hasMoves = false;
		} else {
			for (Cell c : validMoves) {
				for (Tile s : Tile.values()) {
					if ((s.getX() == c.getX()) && (s.getY() == c.getY())) {
						moves.add(s);
					}
				}
			}
		}

		// Only print moves if they are available
		if (hasMoves) {
			System.out.println(moves);
		}

		System.out.println();
	}

	/**
	 * Prompts the user to run the program again
	 */
	private void promptRunAgain() {
		// Step 1. Prompt user to validate another board
		System.out.println("Would you like to validate another Chess Board?");
		String c = scanner.nextLine();

		// Step 2. If the input is not null or an empty String, we need to check the
		// input
		if (null != c && !c.equals("")) {
			// Step 3. If the input is 'yes' or 'y', we clean-up the local attributes
			// and re-prompt the user
			if (c.equalsIgnoreCase("yes") || c.equalsIgnoreCase("y")) {
				reset();
			}
			// Step 4. If the input is not 'yes' or 'y', we set continueRunning to false
			// which will trigger the program to break out of the do/while loop that is
			// dependent on this variable
			else {
				System.out.println("\nProgram Complete!");
				scanner.close();
				continueRunning = false;
			}
		}
		// Step 3. If the input is not null or an empty String, we set continueRunning
		// to false which will trigger the program to break out of the do/while loop
		// that is dependent on this variable
		else {
			System.out.println("\nProgram Complete!");
			scanner.close();
			continueRunning = false;
		}
	}

	/**
	 * Returns an instance of the ChessBoard for testing purposes.
	 * 
	 * @return ChessBoard board
	 */
	protected ChessBoard getChessBoard() {
		return board;
	}

	/**
	 * Cleans up the local class attributes by creating a new instance of the
	 * ChessBoard object.
	 */
	private void reset() {
		// Garbage clean up for the ChessBoard and then new instance for the ChessBoard
		board = null;
		board = new ChessBoard();
	}

	/**
	 * Main method that starts the program
	 * 
	 * @param String[] args
	 */
	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}