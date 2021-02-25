package com.thompson.apps.chess.board;

/**
 * Tile Enum
 * 
 * @author RobertThompson
 */
public enum Tile {
    A1(0, 0, "A1"),
    A2(1, 0, "A2"),
    A3(2, 0, "A3"),
    A4(3, 0, "A4"),
    A5(4, 0, "A5"),
    A6(5, 0, "A6"),
    A7(6, 0, "A7"),
    A8(7, 0, "A8"),
    
    B1(0, 1, "B1"),
    B2(1, 1, "B2"),
    B3(2, 1, "B3"),
    B4(3, 1, "B4"),
    B5(4, 1, "B5"),
    B6(5, 1, "B6"),
    B7(6, 1, "B7"),
    B8(7, 1, "B8"),
    
    C1(0, 2, "C1"),
    C2(1, 2, "C2"),
    C3(2, 2, "C3"),
    C4(3, 2, "C4"),
    C5(4, 2, "C5"),
    C6(5, 2, "C6"),
    C7(6, 2, "C7"),
    C8(7, 2, "C8"),
    
    D1(0, 3, "D1"),
    D2(1, 3, "D2"),
    D3(2, 3, "D3"),
    D4(3, 3, "D4"),
    D5(4, 3, "D5"),
    D6(5, 3, "D6"),
    D7(6, 3, "D7"),
    D8(7, 3, "D8"),
    
    E1(0, 4, "E1"),
    E2(1, 4, "E2"),
    E3(2, 4, "E3"),
    E4(3, 4, "E4"),
    E5(4, 4, "E5"),
    E6(5, 4, "E6"),
    E7(6, 4, "E7"),
    E8(7, 4, "E8"),
    
    F1(0, 5, "F1"),
    F2(1, 5, "F2"),
    F3(2, 5, "F3"),
    F4(3, 5, "F4"),
    F5(4, 5, "F5"),
    F6(5, 5, "F6"),
    F7(6, 5, "F7"),
    F8(7, 5, "F8"),
    
    G1(0, 6, "G1"),
    G2(1, 6, "G2"),
    G3(2, 6, "G3"),
    G4(3, 6, "G4"),
    G5(4, 6, "G5"),
    G6(5, 6, "G6"),
    G7(6, 6, "G7"),
    G8(7, 6, "G8"),
    
    H1(0, 7, "H1"),
    H2(1, 7, "H2"),
    H3(2, 7, "H3"),
    H4(3, 7, "H4"),
    H5(4, 7, "H5"),
    H6(5, 7, "H6"),
    H7(6, 7, "H7"),
    H8(7, 7, "H8");

    private int x;
    private int y;
    private String name;

	/**
	 * Constructor for the Chess Tile Enum
	 * 
	 * @param int    x
	 * @param int    y
	 * @param String name
	 */
	private Tile(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	/**
	 * Returns the x (row) of the Chess Tile
	 * 
	 * @return int x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y (column) of the Chess Tile
	 * 
	 * @return int y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the name of the Chess Tile
	 * 
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the String representation of a tile at given X, Y Position
	 * 
	 * NOTE: This is used as a utility function instead of converting column
	 * characters to integer values
	 * 
	 * @param int x - X Position
	 * @param int y - Y Position
	 * @return String Name of Tile
	 */
	public static String getTileAtPosition(int x, int y) {
		for (Tile t : Tile.values()) {
			if ((t.getX() == x) && (t.getY() == y)) {
				return t.getName();
			}
		}

		return null;
	}
}