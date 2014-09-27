package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;


/**
 * Class representing a 2D array of cells
 */
public class Mosaic extends JPanel {
	private int xCells;										// # of x Cells
	private int yCells;										// # of y Cells
	private int squareSize;									// Cell Size in px
	private int cellGap;									// Gap between cells
	private Color color = Color.BLUE;						// Default color
	private DrawShape drawShape = DrawShape.DRAW_3DRECT;	// Default Shape to Draw
	private List<List<Cell>> state = new ArrayList<>();		// 2D List of cells that defines state

	/**
	 * Constructor
	 * @param xCells Number of Cells in the x Direction
	 * @param yCells Number of Cells in the y Direction
	 * @param squareSize Pixel width & height of each cell
	 */
	public Mosaic(int xCells, int yCells, int cellSize) {
		this.cellGap = 0;										// Default gap between cells
		this.xCells = xCells;
		this.yCells = yCells;
		this.squareSize = cellSize;
		int sizeX = xCells * squareSize + (xCells-1)*cellGap;	// xSize of the game board
		int sizeY = yCells * squareSize + (yCells-1)*cellGap;	// ySize of the game board
		setPreferredSize(new Dimension(sizeX, sizeY));
		
		// Initialize the 2D ArrayList of Cells
		for (int i = 0; i < xCells; i++) {
			List<Cell> tempList = new ArrayList<>();			// Create temp inner ArrayList
			state.add(tempList);
			for (int j = 0; j < yCells; j++) {
				state.get(i).add(new Cell(this.squareSize, this.drawShape, this.color)); // Add Cell
			}
		}
	}
	
	/**
	 * Does all of the painting for the grid
	 */
	public void paintComponent(Graphics g) {
		for (int i = 0; i < xCells; i++) {
			for (int j = 0; j < yCells; j++) {
				g.setColor(state.get(i).get(j).getColor());
				switch(state.get(i).get(j).getShape()) {
					case DRAW_3DRECT:
						g.fill3DRect(i * (squareSize+cellGap), j * (squareSize+cellGap), state.get(i).get(j).getSize(), state.get(i).get(j).getSize(),true);
						break;
					case DRAW_2DRECT:
						g.fillRect(i * (squareSize+cellGap), j * (squareSize+cellGap), state.get(i).get(j).getSize(), state.get(i).get(j).getSize());
						break;
					case DRAW_OVAL:
						g.fillOval(i * (squareSize+cellGap), j * (squareSize+cellGap), state.get(i).get(j).getSize(), state.get(i).get(j).getSize());
						break;
				}	
			}
		}
	}

	public Color getCellColor(int x, int y) {
		return state.get(x).get(y).getColor();
	}
	
	public void setCellColor(int x, int y, Color color) {
		state.get(x).get(y).setColor(color);
	}
	
	public DrawShape getCellShape(int x, int y) {
		return state.get(x).get(y).getShape();
	}
	
	public void setCellShape(int x, int y, DrawShape shape) {
		state.get(x).get(y).setShape(shape);
	}
	
	public int getCellSize(int x, int y) {
		return state.get(x).get(y).getSize();
	}
	
	public void setCellSize(int x, int y, int size) {
		state.get(x).get(y).setSize(size);
	}

	public List<List<Cell>> getState() {
		return state;
	}

	public void setState(List<List<Cell>> state) {
		this.state = state;
	}
	
/**
 * Nested Cell Class that describes each cell of the mosaic 
 * @author GeOff
 *
 */
	public class Cell {
		private Color color;		// Color of the cell
		private DrawShape shape;				// Shape to draw in the cell
		private int size;
		
		/**
		 * Constructor - defaults cell to dead and black
		 */
		public Cell(int size, DrawShape shape, Color color) {
			this.size = size;
			this.shape = shape;
			this.color = color;
		}
		
		/**
		 * Returns color of cell
		 * @return color
		 */
		public Color getColor() {
			return color;
		}
		
		public void setColor(Color color) {
			this.color = color;
		}
		
		public DrawShape getShape() {
			return shape;
		}

		public void setShape(DrawShape shape) {
			this.shape = shape;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}


		
	}

	public enum DrawShape {
		DRAW_OVAL, DRAW_3DRECT, DRAW_2DRECT;
		
		public static DrawShape getRandomShape() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}

	
}


