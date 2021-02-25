package com.thompson.apps.chess.pieces;

import java.util.List;

import com.thompson.apps.chess.board.Cell;

/**
 * Queen Chess Piece Class
 * 
 * @author RobertThompson
 */
public class Queen extends AbstractPiece {

	/**
	 * Default constructor for the Queen Chess Piece that takes in a color and an
	 * initial position that is passed to the super class AbstractPiece.
	 * 
	 * @param boolean isWhite - true if white, false otherwise
	 * @param int     x - Initial X Position
	 * @param int     y - Initial Y Position
	 */
	public Queen(boolean isWhite, int x, int y) {
		super(isWhite, x, y);
	}

	@Override
	public List<Cell> getValidMoves(Cell[][] board) {
		/**
		 * Queen Logic per Wikipedia:
		 * 
		 * A queen combines the power of a rook and bishop and can move any number of
		 * squares along a rank, file, or diagonal, but cannot leap over other pieces.
		 */

		// Step 1. Clean-up Existing Moves
		validMoves.clear();

		// Step 2. Get Horizontal Moves
		validateHorizontalMoves(board);

		// Step 3. Get Vertical Moves
		validateVerticalMoves(board);

		// Step 4. Diagonal Moves
		validateDiagonalMoves(board);

		return validMoves;
	}

	@Override
	public PieceEnum getPieceEnum() {
		return PieceEnum.QUEEN;
	}
}