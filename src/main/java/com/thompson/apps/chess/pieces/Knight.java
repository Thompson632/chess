package com.thompson.apps.chess.pieces;

import java.util.List;

import com.thompson.apps.chess.board.Cell;

/**
 * Knight Chess Piece Class
 * 
 * @author RobertThompson
 */
public class Knight extends AbstractPiece {

	/**
	 * Default constructor for the Knight Chess Piece that takes in a color and an
	 * initial position that is passed to the super class AbstractPiece.
	 * 
	 * @param boolean isWhite - true if white, false otherwise
	 * @param int     x - Initial X Position
	 * @param int     y - Initial Y Position
	 */
	public Knight(boolean isWhite, int x, int y) {
		super(isWhite, x, y);
	}

	@Override
	public List<Cell> getValidMoves(Cell[][] board) {
		/**
		 * Knight Logic per Wikipedia: 
		 * 
		 * A knight moves to any of the closest squares that
		 * are not on the same rank, file, or diagonal. (Thus the move forms an
		 * "L"-shape: two squares vertically and one square horizontally, or two squares
		 * horizontally and one square vertically.) The knight is the only piece that
		 * can leap over other pieces.
		 */
		
		// Step 1. Clean-up Existing Moves
		validMoves.clear();

		// Step 2. Create Array of X-Position Moves with the understanding that
		// we can have max 8 valid moves.
		int xPositions[] = {
				getX() + 1,
				getX() + 1,
				getX() + 2,
				getX() + 2,
				getX() - 1,
				getX() - 1,
				getX() - 2,
				getX() - 2
		};
		
		// Step 3. Create Array of Y-Positions Moves with the understanding that
		// we can have max 8 valid moves.
		int yPositions[] = {
				getY() - 2,
				getY() + 2,
				getY() - 1,
				getY() + 1,
				getY() - 2,
				getY() + 2,
				getY() - 1,
				getY() + 1
				
		};

		// Step 4. Iterate over all possible moves
		validateMoves(board, xPositions, yPositions);

		return validMoves;
	}

	@Override
	public PieceEnum getPieceEnum() {
		return PieceEnum.KNIGHT;
	}
}