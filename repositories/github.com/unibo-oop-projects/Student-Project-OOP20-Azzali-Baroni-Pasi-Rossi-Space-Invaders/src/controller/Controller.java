package controller;

import controller.view.ViewObserver;
import model.GameStatus;
import view.View;

/**
 * The Interface Controller.
 */
public interface Controller extends ViewObserver{
	
	
	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	//Setta la view
	void setView(View view);
	
	
	/**
	 * Check game status.
	 *
	 * @return the game status
	 */
	//Controlla lo status del GameLoop
	GameStatus checkGameStatus();

}
