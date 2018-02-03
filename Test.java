import javax.swing.*;
import java.awt.*;
//import java.awt.GraphicsEnvironment;

public class Test {
	public static void main (String[] args) {
		JFrame mainFrame = new JFrame();
		GamePanel gamePanel = new GamePanel();
		mainFrame.add(gamePanel);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(700, 700);

		/*
	    String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	    for ( int i = 0; i < fonts.length; i++ ) {
	      System.out.println(fonts[i]);
	    }*/
	}
}