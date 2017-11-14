/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizkampen;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameMenuScreen extends JPanel{
   
    JButton randomPlayer = new JButton("Random player"); 
    JButton search = new JButton("Search player"); 
    JButton back = new JButton("Back"); 
    JButton exit = new JButton("Exit"); 
    
    GameMenuScreen() {
     
   setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
      
    add(randomPlayer);
    add(search);
    add(back);
    add(exit); 
    
    setVisible(true);
    
    }
}
