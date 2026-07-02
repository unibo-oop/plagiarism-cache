package gamemanagement;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import playcontroller.PlayController;
import speed.Speed;

/**
 * This class implements the interface {@link TimerController}.
 */
public final class TimerControllerImpl implements TimerController {
    private final PlayController playController;
    private final Timer timer;

    /**
     * Constructor of class TimerController.
     * 
     * @param playController is used to get every objects required by
     *                       this class.
     */
    public TimerControllerImpl(final PlayController playController) {
        this.playController = playController;
        // create the action listener for the timer
        final ActionListener taskPerformer = (actionListener) -> {
            this.action();
        };
        this.timer = new Timer(this.playController.getSpeed().getPause(), taskPerformer);
    }

    @Override
    public void start() {
        this.timer.start();
    }

    @Override
    public void stop() {
        this.timer.stop();
    }

    @Override
    public void restart() {
        this.timer.restart();
    }

    /*
     * Defines the mechanics of the game in relationship with the time.
     */
    private void action() {
        // drop down the piece
        if (this.playController.getMovements().dropDown()) {
            // sound
            this.playController.getSound().play("movements_sound_1.wav");
        } else {
            // it can't drop down so I place the piece
            this.playController.getGameManagement().placePiece();
        }
        // check if the game is not over
        if (!this.playController.getGame().isOver()) {
            // if current speed is changed I manage it
            if (this.playController.getSpeed().getPause() != this.timer.getDelay()
                    && this.playController.getSpeed().getPause() == Speed.ACCELERATED_SPEED
                    || this.playController.getSpeed().getPause() != timer.getDelay() && this.playController.getSpeed()
                            .getPause() == this.playController.getGame().getScore().getLevel().getSpeed()) {
                this.playController.getGameManagement().speedChange();
            }
            this.playController.getView().drawPiece(this.playController.getGame().getCurrent().getPosition(),
                    this.playController.getGame().getCurrent().getColor());
        } else {
            // is over
            this.playController.getGameManagement().gameOver();
        }
    }

    @Override
    public void setSpeed(final int newSpeed) {
        // set initial delay
        this.timer.setInitialDelay(newSpeed);
        // set delay
        this.timer.setDelay(newSpeed);
    }
}
