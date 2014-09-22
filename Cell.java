import java.awt.Color;

/**
 * Cell class - represents a cell that is alive or dead and has a color
 */
public class Cell {
	private Color color;		// Color of the cell
	private int alive;			// State of the cell
	private static Color colorAlive = Color.BLUE;
	private static Color colorDead = Color.BLACK;
	
	/**
	 * Constructor - defaults cell to dead and black
	 */
	public Cell() {
		color = colorDead;
		alive = 0;
	}
	
	/**
	 * Sets a cell alive or dead and updates it's color
	 * @param alive - alive or dead, 0 = dead, 1 = alive.
	 * Would have used bool, but need to add alive/dead cells for game mechanics
	 */
	public void setAlive(int alive) {
		this.alive = alive;
		if (alive == 1)
			color = colorAlive;
		else 
			color = colorDead;
	}
	
	/**
	 * Returns state of cell, 0 = dead, 1 = alive
	 * @return int
	 */
	public int getAlive() {
		return alive;
	}
	
	/**
	 * Returns color of cell
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	
}
