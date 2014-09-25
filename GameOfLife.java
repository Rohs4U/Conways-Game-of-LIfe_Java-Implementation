import java.awt.*;
import java.awt.event.*;

import javax.swing.event.*;
import javax.swing.*;

import views.Mosaic;
import models.MosaicCellManager;

public class GameOfLife extends JPanel {
	public static Mosaic mosaic;				// Mosaic - the game box
	public static MosaicCellManager manager;	// Manages the Mosaic



	public static int xCells;					// Number of XCells
	public static int yCells;					// Number of YCells
	
	public static void main(String[] args)
	{
		// Create content panels
		GameOfLife content = new GameOfLife();

	}
	
	/**
	 * Game Constructor
	 */
	public GameOfLife() {

		
		
		// Create Objects
		JPanel centerPanel = new JPanel();
		JPanel eastPanel= new JPanel();
		mosaic = new Mosaic (xCells, yCells, cellSize, cellGap);
		manager = new MosaicCellManager(mosaic);
		JButton btn = new JButton("Reset");
		JButton step = new JButton("Step");
		timerBtn = new JButton("Start");
		timeSlider = new JSlider(lowerTimeStep, upperTimeStep, delay);
		
		// Set Layout, labels, and sizes
		setLayout(new BorderLayout());
		timeSlider.setLabelTable( timeSlider.createStandardLabels(75) );
		timeSlider.setPaintLabels(true);
		eastPanel.setLayout(new GridLayout(3,1));
		eastPanel.setPreferredSize(new Dimension(100, 25));
		
		// Setup Listeners
		ButtonHandler listener = new ButtonHandler();
		TimerHandler timerListener = new TimerHandler();
		btn.addActionListener(listener);
		timerBtn.addActionListener(listener);
		step.addActionListener(listener);
		ChangeHandler changeListener = new ChangeHandler(); 
		timeSlider.addChangeListener(changeListener);
		timer = new Timer(delay, timerListener);
		
		// Add Content
		centerPanel.add(mosaic);
		eastPanel.add(btn);
		eastPanel.add(timerBtn);
		eastPanel.add(step);
		add(centerPanel, BorderLayout.CENTER);
		add(eastPanel, BorderLayout.EAST);
		add(timeSlider, BorderLayout.SOUTH);
	}
	
	/**
	 * Paint the graphics for the window, buttons, etc.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	/**
	 * Handles all button press events
	 */
	private static class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			// Regenerate a new random grid
			if (command.equals("Reset")) {
				manager.regenGrid();
			}
			
			// Proceed the game forward one step
			else if (command.equals("Step")) {
				manager.stepGame();
			}
			
			// Start or stop the timer
			else {
				if (!timer.isRunning()) {
					timer.start();
					timerBtn.setText("Pause");
				}
				
				else {
					timer.stop();
					timerBtn.setText("Start");
				}
			}
			
			// Repaint the game grid
			mosaic.repaint();
		}
	}
	
	/**
	 * Handles the timer event - steps the game forward each time the timer goes off
	 *
	 */
	private static class TimerHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			manager.stepGame();
		}
	}
	
	/**
	 * Handles the events generated by the JSlider
	 * Adjusts the game step timer delay on the fly
	 */
	private static class ChangeHandler implements ChangeListener {
		public void stateChanged(ChangeEvent evnt) {
			timer.setDelay(timeSlider.getValue());
		}
	}
	

}
