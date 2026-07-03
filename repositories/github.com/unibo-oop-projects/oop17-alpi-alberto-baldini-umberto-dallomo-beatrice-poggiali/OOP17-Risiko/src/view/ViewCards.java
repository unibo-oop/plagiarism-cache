package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * 
 * This class returns a JPanel showing a player's bonus cards.
 * It contains the update method.
 *
 */
public final class ViewCards {
	
	private static final int PROPORTIONALWIDTH = 9;
	private static final int TWO = 2;
	private static final int THREE = 3;
    
	private static JPanel pannello = new JPanel();
    private static JPanel cards = new JPanel();
    private static ViewCards istanza;
 
    private ViewCards() {	 
    	pannello.setPreferredSize(new Dimension(ScreenDimension.getWidth()/PROPORTIONALWIDTH,ScreenDimension.getHeight()));
        JTextField text=new JTextField("BONUS CARDS");
        text.setEditable(false);
        text.setBackground(Color.green);
        pannello.add(text);
        pannello.add(cards);
        pannello.setEnabled(true);   
    }
    /**
     * Update method to show the bonus cards about to the actual player
     * @param A list of strings indicating the cards
     */
    public static void updatePanel(final List<String> carte) {
    	cards.removeAll();
    	if(carte.size() != 0) {
	    	GridLayout layout=new GridLayout(5,1);
	    	cards.setLayout(layout);
	    	cards.setPreferredSize(new Dimension(ScreenDimension.getWidth()/PROPORTIONALWIDTH,ScreenDimension.getHeight()/THREE*TWO));
	        for(int i=0;i<carte.size();i++) {
		        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource(carte.get(i)+".png"));
		        Border blackLine=BorderFactory.createLineBorder(Color.black);
		        JLabel label=new JLabel(img);
		        label.setBorder(blackLine);
		        cards.add(label);
	        }
        }
        cards.updateUI();
        pannello.validate();
    }

    /**
     * 
     * @return the JPanel containing the bonus cards
     */
    public static JPanel getViewCards() {
		 if(istanza == null) {
			 istanza = new ViewCards();
		 }
		 return pannello;
	 }
   
}
