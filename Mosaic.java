import java.awt.*;

import javax.swing.*;

/**
 * Class representing a 2D array of cells. Holds current and next game state.
 * Draws itself
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
	 * Regenerates the game with a new random grid
	 */
	public void regenGrid() {
		for (int i = 0; i < xCells; i++) {
			for (int j = 0; j < yCells; j++) {
				currentArray[i][j].setAlive((int)Math.round(Math.random()));
			}
		}	
	}

	/**
	 * Completes game mechanicals to move the game forward one time step
	 * Counts the number of cells alive surrounding a given cell and computes the next state
	 * for that cell.
	 */
	public void stepGame() {
		for (int i = 0; i < xCells; i++) {
			for (int j = 0; j < yCells; j++) {
				int count = 0;
				
				// Counts the number of cells which are alive around the current cell
				// 9 possible cases to evaluate: Corners, Edges, Center Somewhere
				
				// Top Left
				if (i == 0 && j == 0) {
					count = currentArray[i+1][j].getAlive() + currentArray[i+1][j+1].getAlive() +
							currentArray[i][j+1].getAlive();
				}
				
				// Top Right
				else if (i == (xCells-1) && j == 0) {
					count = currentArray[i-1][j].getAlive() + currentArray[i-1][j+1].getAlive() +
							currentArray[i][j+1].getAlive();
				}
				
				// Bottom Right
				else if (i == (xCells-1) && j == (yCells-1)) {
					count = currentArray[i-1][j].getAlive() + currentArray[i-1][j-1].getAlive() +
							currentArray[i][j-1].getAlive();
				}
				
				// Bottom Left
				else if (i == 0 && j == (yCells-1)) {
					count = currentArray[i][j-1].getAlive() + currentArray[i+1][j-1].getAlive() +
							currentArray[i+1][j].getAlive();
				}
				
				// Left Edge
				else if (i == 0 && j > 0) {
					count = currentArray[i][j+1].getAlive() + currentArray[i+1][j+1].getAlive() +
							currentArray[i+1][j].getAlive() + currentArray[i+1][j-1].getAlive() +
							currentArray[i][j-1].getAlive();
				}
				
				// Top Edge
				else if (i > 0 && j == 0) {
					count = currentArray[i-1][j].getAlive() + currentArray[i-1][j+1].getAlive() +
							currentArray[i][j+1].getAlive() + currentArray[i+1][j+1].getAlive() +
							currentArray[i+1][j].getAlive();
				}

				// Right Edge
				else if (i == (xCells-1) && j > 0) {
					count = currentArray[i][j+1].getAlive() + currentArray[i-1][j+1].getAlive() +
							currentArray[i-1][j].getAlive() + currentArray[i-1][j-1].getAlive() +
							currentArray[i][j-1].getAlive();
				}
	
				// Bottom Edge
				else if (i > 0 && j == (yCells-1)) {
					count = currentArray[i-1][j].getAlive() + currentArray[i-1][j-1].getAlive() +
							currentArray[i][j-1].getAlive() + currentArray[i+1][j-1].getAlive() +
							currentArray[i+1][j].getAlive();
				}
				
				// Anywhere in the middle, not touching an edge
				else {
					count = currentArray[i-1][j].getAlive() + currentArray[i-1][j-1].getAlive() +
							currentArray[i][j-1].getAlive() + currentArray[i+1][j-1].getAlive() +
							currentArray[i+1][j].getAlive() + currentArray[i+1][j+1].getAlive() +
							currentArray[i][j+1].getAlive() + currentArray[i-1][j+1].getAlive();
				} // End counting cells
				
				
				// Check Game Rules and set next state of game board
				if (count < 2) {
					nextArray[i][j].setAlive(0);
				}
				
				else if ((count == 2 || count == 3) && currentArray[i][j].getAlive() == 1) {
					nextArray[i][j].setAlive(1);
				}
				
				else if (count > 3) {
					nextArray[i][j].setAlive(0);
				}
				
				else if (count == 3) {
					nextArray[i][j].setAlive(1);
				}
				
				else {
					nextArray[i][j].setAlive(0);

				} // End game rules				
			}
		}	
		
		// Set currentArray equal to nextArray to move the game forward one time step 
		for (int k = 0; k < xCells; k++) {
			for (int l = 0; l < yCells; l++) {
				currentArray[k][l].setAlive(nextArray[k][l].getAlive());
			}
		}
	}
}
