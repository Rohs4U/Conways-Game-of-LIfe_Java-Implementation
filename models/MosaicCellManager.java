package models;
import views.Mosaic;


public class MosaicCellManager {
	private Mosaic mosaic;
	private static int xCells;
	private static int yCells;
	
	public MosaicCellManager(Mosaic mosaic) {
		this.mosaic = mosaic;
		xCells = mosaic.getXCells();
		yCells = mosaic.getYCells();
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
					count = mosaic.getCurCell(i+1, j) + mosaic.getCurCell(i+1,j+1) +
							mosaic.getCurCell(i,j+1);
				}
				
				// Top Right
				else if (i == (xCells-1) && j == 0) {
					count = mosaic.getCurCell(i-1, j) + mosaic.getCurCell(i-1, j+1) +
							mosaic.getCurCell(i, j+1);
				}
				
				// Bottom Right
				else if (i == (xCells-1) && j == (yCells-1)) {
					count = mosaic.getCurCell(i-1, j) + mosaic.getCurCell(i-1, j-1) +
							mosaic.getCurCell(i, j-1);
				}
				
				// Bottom Left
				else if (i == 0 && j == (yCells-1)) {
					count = mosaic.getCurCell(i, j-1) + mosaic.getCurCell(i+1, j-1) +
							mosaic.getCurCell(i+1, j);
				}
				
				// Left Edge
				else if (i == 0 && j > 0) {
					count = mosaic.getCurCell(i,j+1) + mosaic.getCurCell(i+1,j+1) +
							mosaic.getCurCell(i+1,j)+ mosaic.getCurCell(i+1, j-1) +
							mosaic.getCurCell(i,j-1);
				}
				
				// Top Edge
				else if (i > 0 && j == 0) {
					count = mosaic.getCurCell(i-1, j) + mosaic.getCurCell(i-1, j+1) +
							mosaic.getCurCell(i, j+1) + mosaic.getCurCell(i+1, j+1) +
							mosaic.getCurCell(i+1, j);
				}

				// Right Edge
				else if (i == (xCells-1) && j > 0) {
					count = mosaic.getCurCell(i, j+1) + mosaic.getCurCell(i-1, j+1) +
							mosaic.getCurCell(i-1, j) + mosaic.getCurCell(i-1, j-1) +
							mosaic.getCurCell(i, j-1);
				}
	
				// Bottom Edge
				else if (i > 0 && j == (yCells-1)) {
					count = mosaic.getCurCell(i-1, j) + mosaic.getCurCell(i-1, j-1) +
							mosaic.getCurCell(i, j-1) + mosaic.getCurCell(i+1, j-1) +
							mosaic.getCurCell(i+1, j);
				}
				
				// Anywhere in the middle, not touching an edge
				else {
					count = mosaic.getCurCell(i-1, j) + mosaic.getCurCell(i-1, j-1) +
							mosaic.getCurCell(i, j-1) + mosaic.getCurCell(i+1, j-1) +
							mosaic.getCurCell(i+1, j) + mosaic.getCurCell(i+1, j+1) +
							mosaic.getCurCell(i, j+1) + mosaic.getCurCell(i-1, j+1);
				} // End counting cells
				
				
				// Check Game Rules and set next state of game board
				if (count < 2) {
					mosaic.setNextCell(i, j, 0);
				}
				
				else if ((count == 2 || count == 3) && mosaic.getCurCell(i, j) == 1) {
					mosaic.setNextCell(i, j, 1);
				}
				
				else if (count > 3) {
					mosaic.setNextCell(i, j, 0);
				}
				
				else if (count == 3) {
					mosaic.setNextCell(i, j, 1);
				}
				
				else {
					mosaic.setNextCell(i, j, 0);

				} // End game rules				
			}
		}	
		
		// Set currentArray equal to nextArray to move the game forward one time step 
		for (int k = 0; k < xCells; k++) {
			for (int l = 0; l < yCells; l++) {
				mosaic.setCurCell(k, l, mosaic.getNextCell(k, l));
			}
		}
		
		mosaic.repaint();
	}
	
	/**
	 * Regenerates the game with a new random grid
	 */
	public void regenGrid() {
		for (int i = 0; i < xCells; i++) {
			for (int j = 0; j < yCells; j++) {
				mosaic.setCurCell(i, j, (int)Math.round(Math.random()));
			}
		}
		
		mosaic.repaint();
	}
	
}
