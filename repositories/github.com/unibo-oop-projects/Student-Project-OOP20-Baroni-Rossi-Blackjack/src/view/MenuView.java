package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Game;
import utility.ImageLoader;
/**
 * the class for JPanel of menu
 * 
 *
 */
public class MenuView extends JPanel{

	private static final long serialVersionUID = 1L;
	//JButton for menu
	private JButton start,exit,credits;
	//label for Title and image of menu
	private JLabel title,iconImage;
	private final View view;
	private GameView gameV;
	private ImageLoader image;
	/**
	 * The view for menu
	 * @param view
	 * @param game
	 * @param gameV
	 * @param images
	 */
	public MenuView(final View view, Game game,GameView gameV,ImageLoader images) {

		super();
		this.gameV = gameV;
		this.image = images;
		this.view = view;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
		this.title = new JLabel("BlackJack");
		this.title.setFont(new Font("Book Antiqua", Font.PLAIN,90));
		this.title.setForeground(Color.YELLOW);
		this.title.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(title);
	    this.iconImage = new JLabel();
	    this.iconImage.setIcon(this.image.getMenuImage());
	    this.iconImage.setAlignmentX(Component.CENTER_ALIGNMENT);
	    this.add(iconImage);
	    
	    //button for start the game
	    this.start = new JButton("START");
		this.add(this.setButton(this.start));
		this.start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newPlay();	
			}
		});
		//button for the credits
		this.credits = new JButton("CREDITS");
		this.add(this.setButton(this.credits));
		this.credits.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Il progetto è stato realizzato dagli studenti:\n-Alberto Rossi Mat 0000849580\n-Enrico Baroni Mat 0000839986\nper il corso di programmazione ad oggeti");
			}
		});
		
		//button for Exit
		this.exit = new JButton("EXIT");
		this.add(this.setButton(this.exit));
		this.exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
	}
	//method to set each button
	private JButton setButton(JButton button) {
		button.setBackground(null);
		button.setFocusPainted(false);
		button.setForeground(Color.white);
		button.setFont(new Font("Book Antiqua", Font.PLAIN,36));
		button.setVisible(true);
		button.setActionCommand("exit");
		button.setContentAreaFilled(false);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		return button;
	}
	private void newPlay() {
		this.view.switchPanel(gameV);
	}
}
