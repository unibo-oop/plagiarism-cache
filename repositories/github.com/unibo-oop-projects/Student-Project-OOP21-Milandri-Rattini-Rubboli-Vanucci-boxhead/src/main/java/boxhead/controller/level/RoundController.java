package boxhead.controller.level;

import boxhead.controller.game.GameLevelImpl;
import boxhead.model.level.Round;
import boxhead.model.level.RoundImpl;
import boxhead.view.RoundView;

/**
 * Controller that manages Round flow.
 */
public class RoundController{

	private final Round round;
	private final RoundView roundView;
	private boolean isRoundVital;
	public RoundController(final GameLevelImpl gameLevel) {
        this.round = new RoundImpl(gameLevel.getZombieController().getZombieModel());
        this.roundView = new RoundView(gameLevel.getGameView().getRoundLabel());
        this.isRoundVital = false;
    }
	
	/*
	 * Method to update the inner logic.
	 */
	public void update() {
		this.round.update();
		if (this.round.isRoundActive() && !this.isRoundVital) {
            this.isRoundVital = true;
            this.roundView.setRound(this.round.getCurrentRound());
           
        } else if (!this.round.isRoundActive()) {
                this.isRoundVital = false;
        }
	}
}
