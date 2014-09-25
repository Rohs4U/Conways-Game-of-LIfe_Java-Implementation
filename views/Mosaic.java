package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


/**
 * Class representing a 2D array of cells
 */
public class Mosaic extends JPanel {
	private static int xCells;						// # of x Cells
	private static int yCells;						// # of y Cells
	private static int squareSize;					// Cell Size in px
	private static int cellGap;						// Gap between cells
	private boolean showBorders;					// Whether to show borders
	private int borderThickness;					// Border thickness
	private Color color = Color.BLUE;				
	private static DrawType drawType = DrawType.DRAW_3DRECT;
	private static List<List<Object>> state = new ArrayList<>();

	/**
	 * Constructor
	 * @param xCells Number of Cells in the x Direction
	 * @param yCells Number of Cells in the y Direction
	 * @param squareSize Pixel width & height of each cell
	 */
	public Mosaic(int xCells, int yCells, int cellSize) {
		this.cellGap = 0;									// Default gap between cells
		this.xCells = xCells;
		this.yCells = yCells;
		this.squareSize = cellSize;
		int sizeX = xCells * squareSize + (xCells-1)*cellGap;	// xSize of the game board
		int sizeY = yCells * squareSize + (yCells-1)*cellGap;	// ySize of the game board
		setPreferredSize(new Dimension(sizeX, sizeY) );
		
		// Initialize List Array
	}
	
	/**
	 * Does all of the painting for the game grid
	 */
	public void paintComponent(Graphics g) {
		for (int i = 0; i < xCells; i++) {
			for (int j = 0; j < yCells; j++) {
				g.setColor(color);
				switch(drawType) {
					case DRAW_3DRECT:
						g.fill3DRect(i * (squareSize+cellGap), j * (squareSize+cellGap), squareSize, squareSize,true);
						break;
					case DRAW_2DRECT:
						g.fillRect(i * (squareSize+cellGap), j * (squareSize+cellGap), squareSize, squareSize);
						break;
					case DRAW_OVAL:
						g.fillOval(i * (squareSize+cellGap), j * (squareSize+cellGap), squareSize, squareSize);
						break;
				}	
			}
		}
	}

	public DrawType getDrawType() {
		return drawType;
	}

	public void setDrawType(DrawType drawType) {
		this.drawType = drawType;
	}
}


