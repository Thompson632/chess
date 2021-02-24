package com.thompson.apps.chess.pieces;

import java.util.List;

import com.thompson.apps.chess.board.Cell;

public class Rook extends AbstractPiece {

	/**
	 * FUNCTION_ABSTRACT: Rook
	 * 
	 * PURPOSE: Default constructor for the Rook Chess Piece that takes in a color
	 * and an initial position that is passed to the super class AbstractPiece.
	 * 
	 * @param boolean isWhite - true if white, false otherwise
	 * @param int     x - Initial X Position
	 * @param int     y - Initial Y Position
	 * 
	 *                END FUNCTION_ABSTRACT
	 */
	public Rook(boolean isWhite, int x, int y) {
		super(isWhite, x, y);
	}

	@Override
	public List<Cell> getValidMoves(Cell[][] board) {
		/**
		 * Rook Logic per Wikipedia
		 * 
		 * A rook can move any number of squares along a rank or file, but cannot leap
		 * over other pieces. Along with the king, a rook is involved during the king's
		 * castling move.
		 */

		validMoves.clear();

		// Horizontal Moves
		validateHorizontalMoves(board);

		// Vertical Moves
		validateVerticalMoves(board);

		return validMoves;
	}

	@Override
	public PieceEnum getPieceEnum() {
		return PieceEnum.ROOK;
	}
}