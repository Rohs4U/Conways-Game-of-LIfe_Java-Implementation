package models;

import java.awt.Color;


public class Model {
	private int xCells;					// # of x Cells
	private int yCells;					// # of y Cells
	private int squareSize;				// Cell Size in px
	private int cellGap;				// Gap between cells
	private Cell[][] currentArray;		// Represents state of current cell array
	private Cell[][] nextArray;			// Represents state of next cell array
	public static int delay;			// Delay for game step in ms
	
	public Model() {
		// Set All Game Defaults
		delay = 100;						// Default time step (ms)
		xCells = 80;					// Number of cells in X
		yCells = 80;					// Number of cells in Y
		int cellSize = 7;					// cell size in px
		int cellGap = 0;					// gap between cells (not good with light background)
		int lowerTimeStep = 20;				// Fastest time step
		int upperTimeStep = 500;			// Slowest time step
		windowX = xCells * (cellSize + cellGap) + 110;		// Set Window size to fit everything
		windowY = yCells * (cellSize + cellGap) + 110;		// Set Window size to fit everything
		
		
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

	public Color getCurCellColor(int i, int j) {
		return currentArray[i][j].getColor();
	}
	
	public int getxCells() {
		return xCells;
	}

	public void setxCells(int xCells) {
		this.xCells = xCells;
	}

	public int getyCells() {
		return yCells;
	}

	public void setyCells(int yCells) {
		this.yCells = yCells;
	}

	public int getSquareSize() {
		return squareSize;
	}

	public void setSquareSize(int squareSize) {
		this.squareSize = squareSize;
	}

	public int getCellGap() {
		return cellGap;
	}

	public void setCellGap(int cellGap) {
		this.cellGap = cellGap;
	}

	public Cell[][] getCurrentArray() {
		return currentArray;
	}

	public void setCurrentArray(Cell[][] currentArray) {
		this.currentArray = currentArray;
	}

	public Cell[][] getNextArray() {
		return nextArray;
	}

	public void setNextArray(Cell[][] nextArray) {
		this.nextArray = nextArray;
	}

	public static int getDelay() {
		return delay;
	}

	public static void setDelay(int delay) {
		Model.delay = delay;
	}
	
}
