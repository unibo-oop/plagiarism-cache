package view.mainmenu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.SetupGame;
import data.Colors;

/**
 * 
 * This class instantiates the main menu at the beginning of the game
 *
 */
    public class MenuIni {

        private static JFrame frame;
        private static JButton exit=new JButton("EXIT");
        private JButton newgame=new JButton("NEW GAME");
        private static JPanel panel=new JPanel(new FlowLayout());
        private static Map<String,Integer> listPlayers=new HashMap<>();
     
        private MenuIni() {
        	frame = new JFrame();
            frame.setTitle("RISIKO!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            
            int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    	    int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    	    frame.setSize((int) Toolkit.getDefaultToolkit().
    	    		getScreenSize().getWidth()/2, (int) Toolkit.
    	    		getDefaultToolkit().getScreenSize().getHeight()/3);
    	    frame.setLocation ( (width/2) - (frame.getWidth()/2), (height/2) - (frame.getHeight()/2));
    	    JPanel logo=new JPanel() {
            	private Image img=new ImageIcon(ClassLoader.getSystemResource("sfondoMenuIn.jpg")).getImage();
          
            	public void paintComponent(Graphics g) {
            		  g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),null);
            	  }
            };
              
            JComboBox<String> numPlayers = new JComboBox<>();
        	numPlayers.addItem("3");
        	numPlayers.addItem("4");
        	numPlayers.addItem("5");
        	numPlayers.addItem("6");
        	
        	JLabel num = new JLabel("Number of Players");
            num.setLabelFor(numPlayers);
            panel.add(numPlayers);
            panel.add(newgame);
            panel.add(exit);
            
            frame.add(logo);
            frame.add(panel,BorderLayout.SOUTH);
            exit.addActionListener(e -> System.exit(0));
            frame.setVisible(true);
            this.newgame.addActionListener(e-> {
            	frame.remove(logo);
            	panel.removeAll();
            	MenuPlayers.menuPlayers(Integer.parseInt(numPlayers.getSelectedItem().toString()));
            });
        }
        /**
         * 
         * @return panel 
         */
        public static JPanel getPanel() {
        	return panel;
        }
        /**
         * 
         * @return frame
         */
        public static JFrame getFrame() {
        	return frame;
        }
        /**
         * 
         * @return exit's button
         */
        public static JButton getExit() {
        	return exit;
        }
        /**
         * 
         * @return map of players
         */
        public static  Map<String,Integer> getPlayers() {
        	return listPlayers;
        }
        /**
         * 
         * start the game from menu.
         */
        public static void startMenu() {
            new MenuIni();
        }
}
