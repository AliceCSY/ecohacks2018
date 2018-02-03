import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class StartPanel extends JPanel
{
   final String corp = "Tim Hortons";
   boolean select = false;
   String currCorp;
   String name;
   SpringLayout layout = new SpringLayout();
   JPanel currPanel;

   public StartPanel()
   {
      JButton title = new JButton("ORGANIZER: THE GAME");
      JButton start = new JButton("START GAME");
      JButton leader = new JButton("LEADERBOARDS");
      
      layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, this);
      layout.putConstraint(SpringLayout.WEST, title, 100, SpringLayout.NORTH, this);
      
      layout.putConstraint(SpringLayout.NORTH, start, 150, SpringLayout.NORTH, this);
      layout.putConstraint(SpringLayout.WEST, start, 190, SpringLayout.NORTH, this);
      
      layout.putConstraint(SpringLayout.NORTH, leader, 200, SpringLayout.NORTH, this);
      layout.putConstraint(SpringLayout.WEST, leader, 180, SpringLayout.NORTH,this);
      
      start.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               setVisible(false);
            }
         }); 
   
      leader.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               setVisible(false);
            }
         }); 
   
      
      setLayout(layout);
      add(start);
      add(leader);
      add(title);
   }
}