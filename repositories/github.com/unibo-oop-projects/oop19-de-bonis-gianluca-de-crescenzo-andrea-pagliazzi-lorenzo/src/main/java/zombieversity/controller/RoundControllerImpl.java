package zombieversity.controller;

import zombieversity.controller.core.GameWorld;
import zombieversity.model.rounds.Round;
import zombieversity.model.rounds.RoundImpl;
import zombieversity.view.RoundView;
import zombieversity.view.RoundViewImpl;

/**
 * Implementation of {@link RoundController}.
 *
 */
public class RoundControllerImpl implements RoundController {

    private final Round roundModel;
    private final RoundView roundView;
    private boolean isRoundSet;
    private boolean isBreak;

    /**
     * Instantiates a {@link RoundControllerImpl}.
     * @param gameWorld world controller.
     */
    public RoundControllerImpl(final GameWorld gameWorld) {
        this.roundModel = new RoundImpl(gameWorld.getZombieController().getZombieModel());
        this.roundView = new RoundViewImpl(gameWorld.getGameView().getTimerLabel(),
                gameWorld.getGameView().getRoundLabel());
        this.isRoundSet = false;
        this.isBreak = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        this.roundModel.update();
        /*
         * If round has just started sets the round label.
         */
        if (this.roundModel.isRoundRunning() && !this.isRoundSet) {
            this.isRoundSet = true;
            this.roundView.setRound(this.roundModel.getCurrentRound());
            this.isBreak = false;
            /*
             * During break updates timer.
             */
        } else if (!this.roundModel.isRoundRunning()) {
            if (!this.isBreak) {
                this.roundView.breakRound();
                this.isRoundSet = false;
                this.isBreak = true;
            }
            this.roundView.updateTimerLabel(this.roundModel.getTimeToStart() / 1000);
        }
    }
}
