import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TitlePanel extends JPanel
{
   final String corp = "Tim Hortons";
   boolean select = false;
   String currCorp;
   String name;
   SpringLayout layout = new SpringLayout();
   JPanel currPanel;
  
   
   public TitlePanel()
   {
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
      
      layout.putConstraint(SpringLayout.NORTH, text, 220,SpringLayout.NORTH, this);
      layout.putConstraint(SpringLayout.WEST, text, 130, SpringLayout.NORTH, this);
      
      layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, this);
      layout.putConstraint(SpringLayout.WEST, title, 100, SpringLayout.NORTH, this);
      
      layout.putConstraint(SpringLayout.NORTH, choose, 180, SpringLayout.NORTH, this);
      layout.putConstraint(SpringLayout.WEST, choose, 40, SpringLayout.NORTH, this);
      
      layout.putConstraint(SpringLayout.NORTH, button, 250, SpringLayout.NORTH, this);
      layout.putConstraint(SpringLayout.WEST, button, 180, SpringLayout.NORTH, this);
            
      button.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               if (text.getText().equals(corp))
               {
                  currCorp = text.getText();
                  setVisible(false);
                  StartPanel s = new StartPanel();
                  
               }
            }
         }); 
      
      add(title);
      add(text);
      add(choose);
      add(button);
      setPreferredSize(new Dimension (500,500)); 
      setLayout(layout);
   }
   
}