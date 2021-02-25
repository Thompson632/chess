package com.thompson.apps.chess.pieces;

import java.util.List;

import com.thompson.apps.chess.board.Cell;

/**
 * King Chess Piece Class
 * 
 * @author RobertThompson
 */
public class King extends AbstractPiece {

	/**
	 * Default constructor for the King Chess Piece that takes in a color and an
	 * initial position that is passed to the super class AbstractPiece.
	 * 
	 * @param boolean isWhite - true if white, false otherwise
	 * @param int     x - Initial X Position
	 * @param int     y - Initial Y Position
	 */
	public King(boolean isWhite, int x, int y) {
		super(isWhite, x, y);
	}

	@Override
	public List<Cell> getValidMoves(Cell[][] board) {
		/**
		 * King Logic per Wikipedia:
		 * 
		 * A king moves one square in any direction. Once in every game, each king can
		 * make a special move, known as castling. Castling consists of moving the king
		 * two squares along the first rank toward a rook on the player's first rank,
		 * and then placing the rook on the last square that the king crossed. Castling
		 * is permissible if the following three conditions are met: 1. neither the king
		 * nor the rook has previously moved during the game 2. there are no pieces
		 * between the king and the rook 3. the king is not in check, and will not pass
		 * through or land on any square attacked by an enemy piece. (Note that castling
		 * is permitted if the rook is under attack, or if the rook crosses an attacked
		 * square.)
		 */
		
		// Step 1. Clean-up Existing Moves
		validMoves.clear();
		
		// Step 2. Create Array of X-Position Moves with the understanding that 
		// we can have max 8 valid moves.
		int xPositions[] = {
				getX(),
				getX(),
				getX() + 1,
				getX() + 1,
				getX() + 1,
				getX() - 1,
				getX() - 1,
				getX() - 1
		};
		
		// Step 3. Create Array of Y-Position Moves with the understanding that
		// we can have max 8 valid moves.
		int yPositions[] = {
				getY() - 1,
				getY() + 1,
				getY() - 1,
				getY(),
				getY() + 1,
				getY() - 1,
				getY(),
				getY() + 1
		};
		
		// Step 4. Iterate over all possible moves
		validateMoves(board, xPositions, yPositions);
		
		return validMoves;
	}

	@Override
	public PieceEnum getPieceEnum() {
		return PieceEnum.KING;
	}
}