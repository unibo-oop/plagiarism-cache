package controller;

import model.State;

public class PlayerTurnImpl implements PlayerTurn {
	
	private final static int MIN_PLAYER_HAND_SIZE = 1;
	private final static int MAX_PLAYER_HAND_SIZE = 5;
	private PlayerDraw playerDraw;
	private Game game;
	
	public PlayerTurnImpl() {
	}
	
	/**
	 *method to check a new turn
	 */
	@Override
	public void newTurnPlayer(PlayerDraw playerDraw2, Game game) {
		this.playerDraw = playerDraw2;
		this.game = game;
		this.playerDraw.DrawCard();
		
		if(this.playerDraw.getPointPlayer() > 21) {
			if(this.game.checkBalance()) {
				this.game.setState(State.broke);
			}else {
				this.game.setState(State.lose);
			}
			this.game.UpdateView();
		}else if (this.playerDraw.getPointPlayer() < 22) {
			if(this.playerDraw.getPlayerHand().size() > MIN_PLAYER_HAND_SIZE && this.playerDraw.getPlayerHand().size() <= MAX_PLAYER_HAND_SIZE) {
				this.game.setState(State.playerTurn);
				this.game.UpdateView();
			}
			else if(this.playerDraw.getPlayerHand().size() == MAX_PLAYER_HAND_SIZE) {
				this.game.setState(State.dealerTurn);
				this.game.UpdateView();
			}
		}else if (this.playerDraw.getPlayerHand().size() == MAX_PLAYER_HAND_SIZE) {
			this.game.setState(State.dealerTurn);
			this.game.UpdateView();
		}
	}



}
