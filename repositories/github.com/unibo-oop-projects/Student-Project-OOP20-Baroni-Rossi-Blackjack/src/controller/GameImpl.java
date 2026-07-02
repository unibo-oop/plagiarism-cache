package controller;

import model.Chip;
import model.State;
import utility.ActionHandler;
import view.View;;

/*
 * main class that controls the entire game
 */

public class GameImpl implements Game {
	
	private int balance = 0;
	private int bet = 0;
	private DealerDraw dealerDraw = new DealerDrawImpl();
	private PlayerDraw playerDraw = new PlayerDrawImpl();
	private PlayerTurn playerTurn = new PlayerTurnImpl();
	private DealerTurn dealerTurn = new DealerTurnImpl();
	public ActionHandler actionHandler = new ActionHandler(this,dealerDraw,playerDraw,dealerTurn,playerTurn);
	private State currentState;
	private View view;
	
	
	public GameImpl() {
		this.balance = 1000;
	}
	
	@Override
	public void setView(View view) {
		this.view = view;
	}
	
	/*
	 * method that controls the first turn of the game
	 */
	@Override
	public void newGame() {
		this.dealerDraw.DrawCard();
		this.playerDraw.DrawCard();
		this.dealerDraw.DrawCard();
		this.playerDraw.DrawCard();
		
		
		if(this.playerDraw.getPlayerHand().size() == 2 && this.playerDraw.getPointPlayer() == 21) { //&& this.dealerDraw.getDealerHand().get(0).getValues().getV() != 10) {
			this.currentState = State.natural;
			this.balance += this.bet*2.5;
		}else if(this.playerDraw.getPointPlayer() < 22) {
			this.currentState = State.playerTurn;
		}

		this.UpdateView();
	}

	/*
	 * method to update the view
	 */
	@Override
	public void UpdateView() {		
		this.view.draw(this.dealerDraw.getPointDealer(), this.playerDraw.getPointPlayer(),
				this.playerDraw.getPlayerHand(), this.dealerDraw.getDealerHand(), this.currentState, this.balance, this.bet);
	}
	
	@Override
	public State getState() {
		return this.currentState;
	}
	
	@Override
	public void setState(State state) {
		this.currentState = state;
	}
	
	/*
	 * method that checks the final result
	 */
	@Override
	public void CheckResult() {
		if(this.dealerDraw.getPointDealer() > this.playerDraw.getPointPlayer() && this.dealerDraw.getPointDealer() < 22 || this.playerDraw.getPointPlayer() > 21) {
			if(this.checkBalance()) {
				this.setState(State.broke);
			}else {
				this.setState(State.lose);
			}
		}else if(this.dealerDraw.getPointDealer() < this.playerDraw.getPointPlayer()) {
			this.setState(State.win);
			this.balance += this.bet*2;
		}else if(this.dealerDraw.getPointDealer() == this.playerDraw.getPointPlayer() && this.playerDraw.getPointPlayer() < 21 && this.dealerDraw.getPointDealer() < 21) {
			this.setState(State.drow);
			this.balance += this.bet;
		}else if(this.dealerDraw.getPointDealer() > 21) {
			this.setState(State.win);
			this.balance += this.bet*2;
		}
		
		this.UpdateView();
	}
	
	
	@Override
	public void setBet(Chip chip) {
		this.bet += Chip.getChipValue(chip);
		this.setState(State.bet);
		this.UpdateView();
	}
	
	@Override
	public int getBet() {
		return this.bet;
	}
	
	@Override
	public void setBalanceAfterBet() {
		this.balance -= this.bet;
		this.UpdateView();
	}
	
	@Override
	public boolean checkBalance() {
		if(this.balance <= 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * method that checks that the bet made is valid
	 */
	@Override
	public boolean checkbet(Chip chip) {	
		boolean doit = true;
		
		switch (chip) {
		case twenty:
			if(this.balance - this.bet - Chip.getChipValue(Chip.twenty) < 0) {
				this.setState(State.nobet);
				this.UpdateView();
				doit = false;
			}
			return doit;
		case fifty:
			if(this.balance - this.bet - Chip.getChipValue(Chip.fifty) < 0) {
				this.setState(State.nobet);
				this.UpdateView();
				doit = false;
			}
			return doit;
		case hundred:
			if(this.balance - this.bet - Chip.getChipValue(Chip.hundred) < 0) {
				this.setState(State.nobet);
				this.UpdateView();
				doit = false;
			}
			return doit;
		case five:
			if(this.balance - this.bet - Chip.getChipValue(Chip.five) < 0) {
				this.setState(State.nobet);
				this.UpdateView();
				doit = false;
			}
			return doit;
		}
		return doit;
	}
	
	/**
	 * method that cleans the table
	 */
	@Override
	public void ResetAll() {
		this.playerDraw.ResetPlayer();
		this.dealerDraw.ResetDealer();
		this.bet = 0;
		this.setState(State.bet);
		this.UpdateView();
		//this.newGame();
	}


	/**
	 *method that sets up a new game
	 */
	@Override
	public void playAgain() {
		this.playerDraw.ResetPlayer();
		this.dealerDraw.ResetDealer();
		this.balance = 1000;
		this.bet = 0;
		this.setState(State.bet);
		this.UpdateView();
	}

	@Override
	public ActionHandler getActionHandler() {
		// TODO Auto-generated method stub
		return this.actionHandler;
	}
}
