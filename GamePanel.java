/**
* game shit
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel {
	private static final String CONFIG_FILE = "config.txt";
	private static final String PAPER = "Paper";
	private static final String RECYCLABLES = "Recyclables";
	private static final String WASTE = "Waste";

	private static final int TIME_LIMIT = 10;

	private ImageIcon[] images;
	private String[] answers;
	private int currentImageIndex;
	private JLabel imageLabel;

	private JButton paperButton;
	private JButton recyclablesButton;
	private JButton wasteButton;
	private Font buttonFont = new Font("Optima", Font.BOLD, 35);
	private ButtonHandler buttonHandler;

	private SpringLayout layout;

	private Font labelFont = new Font("Optima", Font.BOLD, 35);
	private int score;
	private int combo = 0;
	private JLabel scoreLabel;

	private JLabel timerLabel;
	private Stopwatch stopwatch;

	private JLabel messageLabel;

	public GamePanel () {
		layout = new SpringLayout();
		setLayout(layout);
		currentImageIndex = -1;

		createButtons();
		createScoreLabel();
		createTimerLabel();
		loadImages();
		createImageLabel();
		createMessageLabel();
		setImage();
	}

	public void newGame () {
		score = 0;
		combo = 0;
		setImage();
		stopwatch.setCountdownInterval(TIME_LIMIT);
		stopwatch.startTimer();
	}

	private void createImageLabel () {
		imageLabel = new JLabel();
		add(imageLabel);
		layout.putConstraint(SpringLayout.WEST, imageLabel, 40, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, imageLabel, 100, SpringLayout.NORTH, this);
	}

	private void setImage () {
		int temp = currentImageIndex;
		while (currentImageIndex == temp) {
			currentImageIndex = (int)(Math.random() * images.length);
		}
		imageLabel.setIcon(images[currentImageIndex]);
	}

	private void loadImages () {
		try {
			BufferedReader in = new BufferedReader(new FileReader(CONFIG_FILE));

			String input = in.readLine();
			int numImages = Integer.parseInt(input);

			images = new ImageIcon[numImages];
			answers = new String[numImages];

			in.readLine();
			for (int i = 0; i < numImages; i++) {
				input = in.readLine();
				images[i] = new ImageIcon(input);
				answers[i] = in.readLine();
				in.readLine();
			}
		} catch (IOException iox) {
			System.out.println("GamePanel: Error loading images from file.");
		}
	}

	private void createButtons () {
		buttonHandler = new ButtonHandler();

		paperButton = new JButton(PAPER);
		paperButton.addActionListener(buttonHandler);
		paperButton.setFont(buttonFont);
		paperButton.setBackground(new Color (35, 137, 238));
		paperButton.setForeground(Color.WHITE);
		paperButton.setBorderPainted(false);
		paperButton.setOpaque(true);
		paperButton.setPreferredSize(new Dimension(250, 55));
		layout.putConstraint(SpringLayout.WEST, paperButton, 410, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, paperButton, 170, SpringLayout.NORTH, this);
		add(paperButton);

		recyclablesButton = new JButton(RECYCLABLES);
		recyclablesButton.addActionListener(buttonHandler);
		recyclablesButton.setFont(buttonFont);
		recyclablesButton.setBackground(new Color (35, 137, 238));
		recyclablesButton.setForeground(Color.WHITE);
		recyclablesButton.setBorderPainted(false);
		recyclablesButton.setOpaque(true);
		recyclablesButton.setPreferredSize(new Dimension(250, 55));
		layout.putConstraint(SpringLayout.WEST, recyclablesButton, 410, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, recyclablesButton, 270, SpringLayout.NORTH, this);
		add(recyclablesButton);

		wasteButton = new JButton(WASTE);
		wasteButton.addActionListener(buttonHandler);
		wasteButton.setFont(buttonFont);
		wasteButton.setBackground(new Color (103, 53, 54));
		wasteButton.setForeground(Color.WHITE);
		wasteButton.setBorderPainted(false);
		wasteButton.setOpaque(true);
		wasteButton.setPreferredSize(new Dimension(250, 55));
		layout.putConstraint(SpringLayout.WEST, wasteButton, 410, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, wasteButton, 370, SpringLayout.NORTH, this);
		add(wasteButton);
	}

	private void createScoreLabel () {
		score = 0;
		scoreLabel = new JLabel("Score: " + score);
		scoreLabel.setFont(labelFont);
		scoreLabel.setForeground(Color.DARK_GRAY);
		add(scoreLabel);
		layout.putConstraint(SpringLayout.WEST, scoreLabel, 40, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, scoreLabel, 20, SpringLayout.NORTH, this);
	}

	private void createTimerLabel () {
		timerLabel = new JLabel();
		timerLabel.setFont(labelFont);
		timerLabel.setForeground(Color.DARK_GRAY);
		add(timerLabel);
		layout.putConstraint(SpringLayout.WEST, timerLabel, 596, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, timerLabel, 20, SpringLayout.NORTH, this);

		stopwatch = new Stopwatch(TIME_LIMIT);
		stopwatch.startTimer();
	}

	private void createMessageLabel () {
		messageLabel = new JLabel("Where does this go?");
		messageLabel.setFont(labelFont);
		messageLabel.setForeground(Color.DARK_GRAY);
		add(messageLabel);
		layout.putConstraint(SpringLayout.WEST, messageLabel, 40, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, messageLabel, 600, SpringLayout.NORTH, this);
	}

	private void showEndGameMessage () {
		JOptionPane.showMessageDialog(null, "Time's up! You ended the game with " + score + " points.", "Game over!", JOptionPane.INFORMATION_MESSAGE);
		saveScore();
	}

	private void saveScore () {
		try {
			BufferedWriter out = new BufferedWriter (new FileWriter ("TrashLeaderboard.txt", true));
			out.newLine();
			out.write(score + "");
			out.close();
		} catch (Exception e) {

		}
	}

	private class ButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			String choice = ((JButton)event.getSource()).getText();
			if (choice.equals(answers[currentImageIndex])) {
				messageLabel.setText("That's right!");
				if (combo > 0) {
					score += 15;
				} else {
					score += 10;
				}
				scoreLabel.setText("Score: " + score);
				combo++;
				setImage();
			} else {
				messageLabel.setText("Incorrect! That item goes in " + answers[currentImageIndex].toLowerCase() + ".");
				combo = 0;
				setImage();
			}
		}
	}

	public class Stopwatch {
	    private Timer timer;
	    private int countdownInterval;
	    private int delay = 1000;
	    private int period = 1000;

	    public Stopwatch (int seconds) {
	        countdownInterval = seconds;
	        timer = new Timer();
	    }

	    public void setCountdownInterval (int seconds) {
	    	countdownInterval = seconds;
	    }

	    public void startTimer () {
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					int currentTime = decrementTime();
					timerLabel.setText(formatTime(currentTime));
				}
	        }, delay, period);
	    }

		private int decrementTime () {
			if (countdownInterval == 0) {
				timerLabel.setText("0:00");
				showEndGameMessage();
				timer.cancel();
			}
			return countdownInterval--;
		}

		private String formatTime (int timeInSeconds) {
			int minutes = timeInSeconds / 60;
			int seconds = timeInSeconds % 60;

			return String.format("%01d:%02d", minutes, seconds);
		}
	}
}