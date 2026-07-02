package controller;

import model.Chip;
import model.State;
import utility.ActionHandler;
import view.View;
/**
 * 
 * @author
 *
 */
public interface Game {

	void setView(View view);

	void newGame();

	void UpdateView();

	State getState();

	void setState(State state);

	void CheckResult();

	void setBet(Chip chip);

	int getBet();

	void setBalanceAfterBet();

	boolean checkBalance();

	boolean checkbet(Chip chip);

	void ResetAll();

	void playAgain();
	
	ActionHandler getActionHandler();

}