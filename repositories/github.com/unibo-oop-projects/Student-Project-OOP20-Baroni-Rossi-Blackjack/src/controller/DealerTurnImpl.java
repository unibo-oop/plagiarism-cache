package controller;
/**
 * 
 * @author 
 *
 */
public class DealerTurnImpl implements DealerTurn {

	private static final int DEALER_MAX_VALUE = 17;
	private static final int DEALER_MAX_CARD = 5;
	private DealerDraw dealerDraw;
	private Game game;
	public DealerTurnImpl() {		
	}

	/*
	 * method to check a new turn
	 */
	@Override
	public void newTurnDealer(DealerDraw dealerDraw, Game game) {
		this.dealerDraw = dealerDraw;
		this.game = game;
		boolean turndealerfinish = true;
		
		while(turndealerfinish) {
			if(this.dealerDraw.getPointDealer() < DEALER_MAX_VALUE) {
				this.dealerDraw.DrawCard();
				
				if(this.dealerDraw.getDealerHand().size() == DEALER_MAX_CARD || this.dealerDraw.getPointDealer() >= DEALER_MAX_VALUE) {
					turndealerfinish = false;
				}
			}else {
				turndealerfinish = false;
			}
		}
		
		if(!turndealerfinish) {
			this.game.CheckResult();
		}
	}
}
