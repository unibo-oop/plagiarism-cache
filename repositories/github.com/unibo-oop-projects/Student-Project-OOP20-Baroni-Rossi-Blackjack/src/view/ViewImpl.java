package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.Game;
import model.CardImpl;
import model.State;
import utility.ImageLoader;
/**
 * 
 * 
 *
 */
public class ViewImpl extends JFrame implements View {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;

	private Game game;
	private GameView gameV;
	/**
	 * 
	 * @param game
	 * @param image
	 */
	public ViewImpl(Game game,ImageLoader image) {
		
		super();
		this.game = game;
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setTitle("BLACKJACK");
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("icon.jpg")).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(0,81,0));   
		this.gameV = new GameView(this, game,image);
		this.switchPanel(new MenuView(this,this.game,this.gameV,image));
		this.pack();
		this.setLocationRelativeTo(null);
		
	}



	@Override
	public void switchPanel(JPanel windows) {
		this.getContentPane().removeAll();
		this.setLayout(new BorderLayout());
		this.add(windows,BorderLayout.CENTER);
		//this.pack();
		this.setVisible(true);
		
	}

	@Override
	public void draw(int scoreDealer, int scorePlayer, List<CardImpl> playerHand, List<CardImpl> dealerHand, State state, int balance, int bet) {
		this.gameV.render(scoreDealer, scorePlayer, playerHand, dealerHand, state, balance, bet);
	}



}
