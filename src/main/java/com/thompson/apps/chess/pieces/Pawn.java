package com.thompson.apps.chess.pieces;

import java.util.List;

import com.thompson.apps.chess.board.Cell;

/**
 * Pawn Chess Piece Class
 * 
 * @author RobertThompson
 */
public class Pawn extends AbstractPiece {

	/**
	 * Default constructor for the Pawn Chess Piece that takes in a color and an
	 * initial position that is passed to the super class AbstractPiece.
	 * 
	 * @param boolean isWhite - true if white, false otherwise
	 * @param int     x - Initial X Position
	 * @param int     y - Initial Y Position
	 */
	public Pawn(boolean isWhite, int x, int y) {
		super(isWhite, x, y);
	}

	@Override
	public List<Cell> getValidMoves(Cell[][] board) {
		/**
		 * Pawn Logic per Wikipedia:
		 * 
		 * A pawn can move forward to the unoccupied square immediately in front of it
		 * on the same file, or on its first move it can advance two squares along the
		 * same file, provided both squares are unoccupied (white dots in the diagram
		 * above); or the pawn can capture an opponent's piece on a square diagonally in
		 * front of it on an adjacent file, by moving to that square (black "getX()"s).
		 * When a pawn makes a two-step advance from its starting position and there is
		 * an opponent's pawn on a square next to the destination square on an adjacent
		 * file, then the opponent's pawn can capture it en passant ("in passing"),
		 * moving to the square the pawn passed over. This can be done only on the very
		 * next turn; otherwise, the right to do so is forfeited. For example, in the
		 * animated diagram, the black pawn advances two steps from g7 to g5, and the
		 * white pawn on f5 can take it en passant on g6 (but only on White's next
		 * move).
		 * 
		 */

		// Step 1. Clean-up Existing Moves
		validMoves.clear();

		// Step 2. Check to see if current piece is White
		if (isWhite()) {

			// Step 3. If our x value is 7, we cannot move any further, return
			if (getX() == 7) {
				return validMoves;
			}

			// Step 4. If there is not a piece one space North, we can move forward
			if (null == board[getX() + 1][getY()].getPiece()) {
				validMoves.add(board[getX() + 1][getY()]);

				// Step 5. If we are at our default starting position
				if (getX() == 1) {
					// Step 6. If there is not a piece two spaces in front, we can move.
					// NOTE: This will only be done if the pawn has not moved
					if (null == board[3][getY()].getPiece()) {
						validMoves.add(board[3][getY()]);
					}
				}
			}

			// Step 7. If we are not on the left corner
			if (getY() > 0) {
				// Step 8. Get the cell one space North and one space West
				Cell c1 = board[getX() + 1][getY() - 1];

				// Step 9. If the space has a piece and that piece is not white, we can move and
				// capture
				if ((null != c1.getPiece()) && (this.isWhite() != c1.getPiece().isWhite())) {
					validMoves.add(c1);
				}
			}

			// Step 10. If we are not on the right corner
			if (getY() < 7) {
				// Step 11. Get the cell one space North and one space East
				Cell c2 = board[getX() + 1][getY() + 1];

				// Step 12. If the space has a piece and that piece is not white, we can move
				// and capture
				if ((null != c2.getPiece()) && (this.isWhite() != c2.getPiece().isWhite())) {
					validMoves.add(c2);
				}
			}

		}
		// Step 3. The current piece is black
		else {
			// Step 4. If our x value is 0, we cannot move any further, return
			if (getX() == 0) {
				return validMoves;
			}

			// Step 5. If there is not a piece one space South, we can move forward
			if (null == board[getX() - 1][getY()].getPiece()) {
				validMoves.add(board[getX() - 1][getY()]);

				// Step 6. If we are at our default starting position
				if (getX() == 6) {
					// Step 7. If there is not a piece two spaces in front, we can move.
					// NOTE: This will only be down if the pawn has not moved
					if (null == board[4][getY()].getPiece()) {
						validMoves.add(board[4][getY()]);
					}
				}
			}

			// Step 8. If we are not on the left corner
			if (getY() > 0) {
				// Step 9. Get the cell one space South and one space West
				Cell c1 = board[getX() - 1][getY() - 1];

				// Step 10. If the space has a piece and that piece is not black, we can move and
				// capture
				if ((null != c1.getPiece()) && (this.isWhite() != c1.getPiece().isWhite())) {
					validMoves.add(c1);
				}
			}

			// Step 11. If we are not on the right corner
			if (getY() < 7) {
				// Step 12. Get the cell one space South and one space East
				Cell c2 = board[getX() - 1][getY() + 1];

				// Step 13. If the space has a piece and that piece is not black, we can move
				// and capture
				if ((null != c2.getPiece()) && (this.isWhite() != c2.getPiece().isWhite())) {
					validMoves.add(c2);
				}
			}
		}

		return validMoves;
	}

	@Override
	public PieceEnum getPieceEnum() {
		return PieceEnum.PAWN;
	}
}