package utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.DealerDraw;
import controller.DealerTurn;
import controller.Game;
import controller.PlayerDraw;
import controller.PlayerTurn;
import model.Chip;

public class ActionHandler implements ActionListener{

	private final static int MAX_CARD_HAND = 5;
	private Game game;
	private PlayerTurn playerTurn;
	private DealerTurn dealerTurn;
	private PlayerDraw playerDraw;
	private DealerDraw dealerDraw;

	/**
	 * 
	 * @param game
	 * @param dealerDraw
	 * @param playerDraw
	 * @param dealerTurn
	 * @param playerTurn
	 */
	public ActionHandler(Game game, DealerDraw dealerDraw, PlayerDraw playerDraw, DealerTurn dealerTurn, PlayerTurn playerTurn) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.playerTurn = playerTurn;
		this.playerDraw = playerDraw;
		this.dealerTurn = dealerTurn;
		this.dealerDraw = dealerDraw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		switch (command) {
		case "1"://pesca
				if(this.playerDraw.getPlayerHand().size() < MAX_CARD_HAND) {
					this.playerTurn.newTurnPlayer(this.playerDraw,this.game);
				}else {
					this.dealerTurn.newTurnDealer(this.dealerDraw,this.game);
				}
			break;
		case "2"://stai
				this.dealerTurn.newTurnDealer(this.dealerDraw,this.game);
			break;
		case "0"://rigioca
				this.game.ResetAll();
			break;
		case "3"://gioca
			if(this.game.getBet() != 0) {
				this.game.newGame();
				this.game.setBalanceAfterBet();
			}
			break;
		case "4":
			this.game.playAgain();
			break;
		case "chips0"://bet 20
			if(this.game.checkbet(Chip.twenty)) {
				this.game.setBet(Chip.twenty);
			}
 			

			break;
		case "chips1"://bet50
			if(this.game.checkbet(Chip.fifty)) {
				this.game.setBet(Chip.fifty);
			}

			break;
		case "chips2"://bet100
			if(this.game.checkbet(Chip.hundred)) {
				this.game.setBet(Chip.hundred);
			}
			break;
		case "chips3"://bet5
			if(this.game.checkbet(Chip.five)) {
				this.game.setBet(Chip.five);
			}
		}
	}

}
