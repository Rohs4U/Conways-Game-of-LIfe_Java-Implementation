package views;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

import models.Model;
import models.MosaicCellManager;


public class View extends JFrame {
	public static int windowX;					// Window Size X
	public static int windowY;					// Window Size Y
	public static Timer timer;					// Time for game step
	public static JSlider timeSlider;			// Slider to adjust delay
	public static JButton timerBtn;				// Button to start/stop game
	private Mosaic mosaic;
	private Model model;

	public View() {
		// Create the model object
		model = new Model();
		
		// Setup the window and add content to it
		JFrame window = new JFrame("Conways Game of Life");
		setContentPane(this);
		setSize(windowX, windowY);
		setLocation(100, 100);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		// Create Objects
		
		JPanel centerPanel = new JPanel();
		JPanel eastPanel= new JPanel();
		mosaic = new Mosaic (model.getxCells(), model.getyCells(), model.getSquareSize(), model.getCellGap());
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
	

}
