import java.awt.*;

import javax.swing.*;

/**
 * Class representing a 2D array of cells. Holds current and next game state.
 */
public class Mosaic extends JPanel {
	private int xCells;					// # of x Cells
	private int yCells;					// # of y Cells
	private int squareSize;				// Cell Size in px
	private int cellGap;				// Gap between cells
	private Cell[][] currentArray;		// Represents state of current cell array
	private Cell[][] nextArray;			// Represents state of next cell array

	
	/**
	 * Constructor
	 * @param xCells Number of Cells in the x Direction
	 * @param yCells Number of Cells in the y Direction
	 * @param squareSize Pixel width & height of each cell
	 */
	public Mosaic(int xCells, int yCells, int squareSize, int cellGap) {
		this.cellGap = cellGap	;								// Default gap between cells
		this.xCells = xCells;
		this.yCells = yCells;
		this.squareSize = squareSize;
		int sizeX = xCells * squareSize + (xCells-1)*cellGap;	// xSize of the game board
		int sizeY = yCells * squareSize + (yCells-1)*cellGap;	// ySize of the game board
		setPreferredSize( new Dimension(sizeX, sizeY) );
		
		// Setup Cell Arrays
		currentArray = new Cell[xCells][yCells];				// Current game state
		nextArray = new Cell[xCells][yCells];					// Next game state after applying rules
		
		// Initialize arrays - create a Cell in each location
		for (int i = 0; i < xCells; i++) {
			for (int j = 0; j < yCells; j++) {
				currentArray[i][j] = new Cell();
				currentArray[i][j].setAlive((int)Math.round(Math.random()));
				nextArray[i][j] = new Cell();
			}
		}
	}
	
	/**
	 * Does all of the painting for the game grid
	 */
	public void paintComponent(Graphics g) {
		for (int i = 0; i < xCells; i++) {
			for (int j = 0; j < yCells; j++) {
					g.setColor(currentArray[i][j].getColor());
					g.fill3DRect(i * (squareSize+cellGap), j * (squareSize+cellGap), squareSize, squareSize,true);	
			}
		}
	}
	
	/**
	 * Returns state of cell in currentArray
	 * @param x X Offset in grid
	 * @param y Y Offset in grid
	 * @return int - state of cell, 0 = dead, 1 = alive
	 */
	public int getCurCell(int x, int y) {
		return currentArray[x][y].getAlive();
	}

	/**
	 * Returns state of cell in nextArray
	 * @param x X Offset in grid
	 * @param y Y Offset in grid
	 * @return int - state of cell, 0 = dead, 1 = alive
	 */
	public int getNextCell(int x, int y) {
		return nextArray[x][y].getAlive();
	}
	
	/**
	 * Sets state of cell in currentArray
	 * @param x X Offset in grid
	 * @param y Y Offset in grid
	 */	
	public void setCurCell(int x, int y, int alive) {
		currentArray[x][y].setAlive(alive);
	}
	
	/**
	 * Sets state of cell in nextArray
	 * @param x X Offset in grid
	 * @param y Y Offset in grid
	 */	
	public void setNextCell(int x, int y, int alive) {
		nextArray[x][y].setAlive(alive);
	}

	/**
	 * Returns number of cells in X Direction
	 * @return int
	 */
	public int getXCells() {
		return xCells;
	}
	
	/**
	 * Returns number of cells in Y Direction
	 * @return int
	 */
	public int getYCells() {
		return yCells;
	}

	
}
