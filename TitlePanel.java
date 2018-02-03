import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TitlePanel
{
   final String corp = "Tim Hortons";
   boolean select = false;
   String currCorp;
   String name;
   SpringLayout layout = new SpringLayout();
   
   public void initPane()
   {
      JFrame frame = new JFrame();
      JPanel panel = new JPanel();
      JLabel title = new JLabel("ORGANIZER: THE GAME");
      JButton button = new JButton("Submit Name");
      
   
         
      JTextField text = new JTextField("",20);
      JLabel choose = new JLabel("Please choose your organization:");
      
      text.setText("");
      text.setEditable(true);
      text.setBorder(new LineBorder(Color.black));
      
      title.setFont(new Font("Arial", Font.BOLD, 25));
      choose.setFont(new Font("Arial", Font.BOLD, 25));
      
      title.setForeground(Color.gray);
      choose.setForeground(Color.gray);
      
      layout.putConstraint(SpringLayout.NORTH, text, 220,SpringLayout.NORTH, panel);
      layout.putConstraint(SpringLayout.WEST, text, 130, SpringLayout.NORTH, panel);
      
      layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, panel);
      layout.putConstraint(SpringLayout.WEST, title, 100, SpringLayout.NORTH, panel);
      
      layout.putConstraint(SpringLayout.NORTH, choose, 180, SpringLayout.NORTH, panel);
      layout.putConstraint(SpringLayout.WEST, choose, 40, SpringLayout.NORTH, panel);
      
      layout.putConstraint(SpringLayout.NORTH, button, 250, SpringLayout.NORTH, panel);
      layout.putConstraint(SpringLayout.WEST, button, 180, SpringLayout.NORTH, panel);
            
      button.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               if (text.getText().equals(corp))
               {
                  currCorp = text.getText();
                  frame.setVisible(false);
                  initStart();
               }
            }
         }); 
      
      panel.add(title);
      panel.add(text);
      panel.add(choose);
      panel.add(button);
      panel.setPreferredSize(new Dimension (500,500)); 
      panel.setLayout(layout);
      frame.add(panel);
      frame.setContentPane(panel);
      frame.setSize(500, 500);
      frame.setVisible(true);
   }
   
   public void initStart()
   {
      JFrame frame = new JFrame();
      JPanel panel = new JPanel();
      JLabel title = new JLabel("ORGANIZER: THE GAME");
      JButton start = new JButton("START GAME");
      JButton leader = new JButton("LEADERBOARDS");
      
      title.setFont(new Font("Arial", Font.BOLD, 25));
      title.setForeground(Color.gray);
      
      layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, panel);
      layout.putConstraint(SpringLayout.WEST, title, 100, SpringLayout.NORTH, panel);
      
      layout.putConstraint(SpringLayout.NORTH, start, 150, SpringLayout.NORTH, panel);
      layout.putConstraint(SpringLayout.WEST, start, 190, SpringLayout.NORTH, panel);
      
      layout.putConstraint(SpringLayout.NORTH, leader, 200, SpringLayout.NORTH, panel);
      layout.putConstraint(SpringLayout.WEST, leader, 180, SpringLayout.NORTH,panel);
      
      start.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               frame.setVisible(false);
               initName();
            }
         }); 
   
      leader.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               frame.setVisible(false);
            }
         }); 
   
      
      panel.setLayout(layout);
      panel.add(start);
      panel.add(leader);
      panel.add(title);
      frame.add(panel);
      frame.setContentPane(panel);
      frame.setSize(500, 500);
      frame.setVisible(true);
   }
  
   public void initName()
   {
      JFrame frame = new JFrame();
      JPanel panel = new JPanel();
      JLabel enter = new JLabel("ENTER NAME:");
      JTextField text = new JTextField("",20);
      JButton begin = new JButton("BEGIN");
      
      enter.setFont(new Font("Arial", Font.BOLD,40));
      enter.setForeground(Color.gray);
      
      layout.putConstraint(SpringLayout.NORTH, enter, 50, SpringLayout.NORTH, panel);
      layout.putConstraint(SpringLayout.WEST, enter, 100, SpringLayout.NORTH, panel);
      
      layout.putConstraint(SpringLayout.NORTH, text, 150, SpringLayout.NORTH,panel);
      layout.putConstraint(SpringLayout.WEST, text, 130, SpringLayout.NORTH, panel);
      
      layout.putConstraint(SpringLayout.NORTH, begin, 210, SpringLayout.NORTH,panel);
      layout.putConstraint(SpringLayout.WEST, begin, 200, SpringLayout.NORTH,panel);
      
      begin.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               if (!text.getText().equals(""))
               {
                  try
                  {
                     BufferedWriter out = new BufferedWriter(new FileWriter("idontcare.txt",true));
                     out.write(text.getText());
                     out.close();
                  }
                  catch (IOException iox)
                  {
                  }
                  frame.setVisible(false);
               }
            }
         }); 
      
      panel.add(enter);
      panel.add(text);
      panel.add(begin);
      panel.setLayout(layout);
      frame.add(panel);
      frame.setContentPane(panel);
      frame.setSize(500, 500);
      frame.setVisible(true);
   }
   
   public static void main(String[] args)
   {
      TitlePanel t = new TitlePanel();
      t.initPane();
   }
   
}