//EcoHacks 2018
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class TrashLeaderboard extends JPanel{
   
   private String secretCode;
   private final int CODE_LEN = 6;
   private final String FILE_NAME = "TrashLeaderboard.txt";
   private ArrayList<TrashPlayer> players;
   private final int LENGTH = 700;
   private final int HEIGHT = 700;
   private final String TITLE = "The Leaders";
   private final int TITLE_LEN = LENGTH / 5 * 4;
   private final int TITLE_HEI = HEIGHT / 6;
   private final int EMPTY_HEI = 150;
   private final int NUM_DISPLAY = 10;
   private final String CODE_FILE = "TheCode.txt";
   
   public TrashLeaderboard(){
      generateCode();
      
      try{
         BufferedReader f = new BufferedReader(new FileReader(CODE_FILE));
         ArrayList<String> codes = new ArrayList<String>();
         String s;
         while( (s = f.readLine()) != null) codes.add(s);
         f.close();
         
         BufferedWriter out = new BufferedWriter(new FileWriter(CODE_FILE));
         for(String code : codes){
            out.write(code);
            out.newLine();
         }
         out.write(secretCode);
         out.newLine();
         out.close();
         
      } catch(IOException e){
         System.out.println("Error writing secret code to file");
      }
      
      showMessage("Your code is " + secretCode + ". Click OK to continue to Leaderboard.");
      
      players = new ArrayList<TrashPlayer>();
      loadFromFile();
      Collections.sort(players, new Comparator<TrashPlayer>(){
         public int compare(TrashPlayer t1, TrashPlayer t2){
            return t2.score - t1.score;
         }
      });
      //for(TrashPlayer each: players)System.out.println(each.name + "  " + each.score);
      display();
   }
   
   private void display(){
      setPreferredSize(new Dimension(LENGTH, HEIGHT));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      
      JLabel tl = new JLabel(TITLE);
      tl.setAlignmentX(Component.CENTER_ALIGNMENT);
      tl.setPreferredSize(new Dimension(TITLE_LEN / 2, TITLE_HEI));
      tl.setFont(new Font("Arial", Font.BOLD, 35));
      add(tl);
      
      JPanel ll = new JPanel();
      ll.setPreferredSize(new Dimension(TITLE_LEN, HEIGHT - TITLE_HEI - EMPTY_HEI));
      ll.setLayout(new GridLayout(NUM_DISPLAY, 0));
      int eachHeight = (HEIGHT - TITLE_HEI - EMPTY_HEI) / NUM_DISPLAY;
      for(int i = 0; i < NUM_DISPLAY; i++){
         if(i == players.size()) break;
         
         TrashPlayer cur = players.get(i);
         
         JLabel temp = new JLabel();
         temp.setPreferredSize(new Dimension(TITLE_LEN, eachHeight));
         temp.setLayout(new BorderLayout());
         
         temp.setOpaque(true);
         if(i == 0){
            temp.setBackground(Color.orange);
         } else if (i % 2 == 1){
            temp.setBackground(Color.white);
         } else {
            temp.setBackground(Color.pink);
         }
         
         JLabel nameLabel = new JLabel("     " + cur.name);
         nameLabel.setPreferredSize(new Dimension(TITLE_LEN / 3 * 2, eachHeight));
         nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
         temp.add(BorderLayout.WEST, nameLabel);
         
         JLabel scoreLabel = new JLabel(cur.score + "");
         scoreLabel.setPreferredSize(new Dimension(TITLE_LEN / 6, eachHeight));
         scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
         scoreLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
         temp.add(BorderLayout.EAST, scoreLabel);
         
         ll.add(temp);
      }
      add(ll);
      
      JButton backButton = new JButton("Back");
      backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      add(backButton);
      
      /*JFrame frame = new JFrame();
      frame.add(this);
      frame.setVisible(true);*/
   }
   
   private void loadFromFile(){
      try{
         BufferedReader f = new BufferedReader(new FileReader(FILE_NAME));
         String s;
         while( (s = f.readLine()) != null){
            players.add(new TrashPlayer(s, Integer.parseInt(f.readLine())));
         }
         
      } catch (IOException e){
         System.out.println("Error loading player file");
      }
   }
   
   private void generateCode(){
      secretCode = "";
      for(int i = 0; i < CODE_LEN; i++){
         secretCode += (int)(Math.random() * 10); 
      }
   }
   
   public void showMessage(String q){
      JLabel label= new JLabel(q);
      label.setFont(new Font("Arial", Font.PLAIN, 15));
      JOptionPane.showMessageDialog(null, label);
   }
}

class TrashPlayer{
   String name;
   int score;
   
   public TrashPlayer(String n, int s){
      name = n;
      score = s;
   }
   
}

class Tester{
   public static void main(String[] args){
      TrashLeaderboard t = new TrashLeaderboard();
   }
}

