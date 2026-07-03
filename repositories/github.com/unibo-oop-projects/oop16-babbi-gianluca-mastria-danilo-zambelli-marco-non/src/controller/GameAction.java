package controller;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.Pair;
import view.Start4Impl;
import view.Start6Impl;

/**
 * Interface of the most important action of the game
 *
 */
public interface GameAction {

	/**
	 * This method contains the most important action when a player choose the new cell for a token
	 * 
	 * @param b
	 * 		instance of the changing button
	 * @param nPlayers
	 * 		number of players
	 * @param button
	 * 		instance of the changing button
	 * @param start4
	 * 		instance of Start4Impl used to refresh the correct view
	 * @param start6
	 * 		instance of Start6Impl used to refresh the correct view
	 * @param n
	 * 		last cell of the game board
	 */
	public void routeAction(JButton b, int nPlayers,  JButton button, Start4Impl start4, Start6Impl start6, int n);
	
	/**
	 * This method contains the most important action when a token arrive to the finish cell
	 * 
	 * @param image
	 * 		image of the token 
	 * @param b
	 * 		instance of the changing button 
	 * @param list
	 * 		instance of the changing list
	 * @param nPlayers
	 * 		number of players
	 * @param start4
	 * 		instance of Start4Impl used to refresh the correct view
	 * @param start6
	 * 		instance of Start6Impl used to refresh the correct view
	 * @param n
	 * 		last cell of the game board
	 */
	public void arriveAction(ImageIcon image, JButton b, List<Pair<JButton, Boolean>> list, int nPlayers, Start4Impl start4, Start6Impl start6, int n);
	
	/**
	 * This method contains the most important action of a game with four players
	 * 
	 * @param nPlayers
	 * 		number of players
	 * @param start4
	 * 		instance of Start4Impl used to refresh the correct view
	 */
	public void game4(int nPlayers, Start4Impl start4);
	
	/**
	 * This method contains the most important action of a game with six players
	 * 
	 * @param nPlayers
	 * 		number of players
	 * @param start6
	 * 		instance of Start6Impl used to refresh the correct view
	 */
	public void game6(int nPlayers, Start6Impl start6);
}
