package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

/**
 * This class creates the page that shows the game's rules
 */
public class Info extends JFrame {
  
	private static final long serialVersionUID = 1L;

	public Info(JFrame back) {

	JFrame rules = new JFrame();
	String text = "The purpose of the game is to move everyone’s own pawn from the home-base to the base of arrival, "
			+ "proceeding clockwise along a path made up of boxes depending on the points scored rolling a dice. "
			+ "One player at the time, clockwise, rolls the dice. To start the way a six needs to be scored, "
			+ "this gives the right and the duty to position a path on your starting box, situated in the right and of the same color. "
			+ "If a number different from six is scored, the pawn can proceed, otherwise another pawn has to come into play. "
			+ "If your pawn is on the starting box and six comes out again, "
			+ "the new pawn won’t come into play and another roll of the dice will be needed. "
			+ "Once that all pawns are at stake, every time that a six is scored, you will get to move forward of six boxes. "
			+ "If there are still pawns on the base-home and a six comes out, positioning one on the starting box is compulsory, "
			+ "even if  by proceeding with another one you could ‘eat’ an opposing. "
			+ "When moving forward you arrive to a box when there’s another pawn, you eat it and this one has to go back to the home-base. "
			+ "A player wins when he first places the four pawns on the box of arrival. ";
	rules.setSize(600, 600);
	rules.getContentPane().setLayout(null);
	rules.getContentPane().setBackground(Color.LIGHT_GRAY);
	rules.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	rules.addWindowListener(new WindowAdapter() {
    	public void windowClosing (WindowEvent e)
    	{	
    		back.setEnabled(true);
    		rules.dispose(); 		
    	}
    });
	
	JLabel title = new JLabel("Game's rules");
	title.setFont(new Font("Talking to the Moon", Font.BOLD, 32));
	title.setBounds(198, 13, 206, 41);
	rules.getContentPane().add(title);
	
	JTextPane textPane = new JTextPane();
	textPane.setBounds(67, 67, 453, 462);
	textPane.setFont(new Font("Footlight MT Light", Font.PLAIN, 18));
	textPane.setEditable(false);
	textPane.setText(text);
	
	rules.getContentPane().add(textPane);
	rules.setLocationRelativeTo(null);
	rules.setResizable(false);
	rules.setVisible(true);
    }
}
