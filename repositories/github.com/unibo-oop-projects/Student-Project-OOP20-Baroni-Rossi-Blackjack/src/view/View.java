package view;

import java.util.List;
import javax.swing.JPanel;

import model.CardImpl;
import model.State;
/**
 * 
 * @author bon98
 *
 */
public interface View {
	/**
	 * 
	 * @param scoreDealer
	 * @param scorePlayer
	 * @param playerHand
	 * @param dealerHand
	 * @param state
	 * @param balance
	 * @param bet
	 */
	void draw(int scoreDealer,int scorePlayer,List<CardImpl> playerHand,List<CardImpl> dealerHand,State state, int balance, int bet);
	/**
	 * 
	 * @param windows
	 */
	void switchPanel(JPanel windows);
	
	
	
}
