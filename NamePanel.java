import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NamePanel extends JPanel
{
   final String corp = "Tim Hortons";
   boolean select = false;
   String currCorp;
   String name;
   SpringLayout layout = new SpringLayout();
   
   public NamePanel()
   {
      JLabel enter = new JLabel("ENTER NAME:");
      JTextField text = new JTextField("",20);
      JButton begin = new JButton("BEGIN");
      
      enter.setFont(new Font("Arial", Font.BOLD,40));
      enter.setForeground(Color.gray);
      
      layout.putConstraint(SpringLayout.NORTH, enter, 50, SpringLayout.NORTH, this);
      layout.putConstraint(SpringLayout.WEST, enter, 100, SpringLayout.NORTH, this);
      
      layout.putConstraint(SpringLayout.NORTH, text, 150, SpringLayout.NORTH,this);
      layout.putConstraint(SpringLayout.WEST, text, 130, SpringLayout.NORTH, this);
      
      layout.putConstraint(SpringLayout.NORTH, begin, 210, SpringLayout.NORTH,this);
      layout.putConstraint(SpringLayout.WEST, begin, 200, SpringLayout.NORTH,this);
      
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
                  setVisible(false);
               }
            }
         }); 
      
      add(enter);
      add(text);
      add(begin);
      setLayout(layout);
   }
}
