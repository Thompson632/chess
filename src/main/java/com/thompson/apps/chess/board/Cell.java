package com.thompson.apps.chess.board;

import com.thompson.apps.chess.pieces.AbstractPiece;

/**
 * Cell Class
 * 
 * @author RobertThompson
 */
public class Cell {
	/* Represents the Chess Piece at the Given Position */
	private AbstractPiece piece = null;

	/* Cell X Position */
	private int x = 0;
	/* Cell Y Position */
	private int y = 0;

	/**
	 * Default constructor for the Cell
	 * 
	 * Represents a position on the chess board. This cell will either be occupied
	 * by an AbstractPiece or be empty.
	 * 
	 * @param int           x - X Position of the Piece
	 * @param int           y - Y Position of the Piece
	 * @param AbstractPiece piece - Piece at the given x, y position
	 */
	public Cell(int x, int y, AbstractPiece piece) {
		this.x = x;
		this.y = y;
		this.piece = piece;
	}

	/**
	 * Returns the Piece at the given cell
	 * 
	 * @return AbstractPiece piece
	 */
	public AbstractPiece getPiece() {
		return piece;
	}

	/**
	 * Returns the X Position of the Cell
	 * 
	 * @return int x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the Y Position of the Cell
	 * 
	 * @return int y
	 */
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		if (null != piece) {
			return piece.toString();
		} else {
			return "Empty";
		}
	}
}