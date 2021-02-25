package com.thompson.apps.chess.pieces;

/**
 * Piece Enum
 * 
 * @author RobertThompson
 */
public enum PieceEnum {
	BISHOP("B"), 
	KING("K"), 
	KNIGHT("N"), 
	PAWN("P"), 
	QUEEN("Q"), 
	ROOK("R");

	private String name;

	/**
	 * Represents a Chess Piece.
	 * 
	 * Note: toString returns the name of enum value and getName returns the single
	 * character of the piece
	 * 
	 * @param String name - Character representation of the Chess Piece
	 */
	private PieceEnum(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the Piece
	 * 
	 * @return String name - Character representation of the Chess Piece
	 */
	public String getName() {
		return name;
	}
}
