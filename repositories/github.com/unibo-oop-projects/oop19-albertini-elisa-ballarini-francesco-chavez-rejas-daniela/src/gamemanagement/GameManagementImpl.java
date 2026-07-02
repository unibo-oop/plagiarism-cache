package gamemanagement;

import level.Levels;
import playcontroller.PlayController;

/**
 * This class implements the interface {@link GameManagement}.
 */
public final class GameManagementImpl implements GameManagement {
    private final PlayController playController;
    private final TimerController timer;
    private Levels currentLevel;

    /**
     * Constructor of class GameManagementImpl.
     * 
     * @param playController is used to get every objects required by
     *                       this class.
     */
    public GameManagementImpl(final PlayController playController) {
        this.playController = playController;
        this.timer = new TimerControllerImpl(this.playController);
        this.currentLevel = this.playController.getGame().getScore().getLevel();
    }

    @Override
    public void startPlay() {
        // draw the piece
        this.playController.getView().drawPiece(this.playController.getGame().getCurrent().getPosition(),
                this.playController.getGame().getCurrent().getColor());
        // initializing the playground
        this.initPlaygroud();
        this.timer.start();
    }

    @Override
    public void stopPlay() {
        this.timer.stop();
    }

    @Override
    public void resumePlay() {
        // set new delay to the timer
        this.timer.setSpeed(this.playController.getSpeed().getPause());
        this.timer.restart();
    }

    @Override
    public void gameOver() {
        this.timer.stop();
        // sound
        this.playController.getSound().play("game_over_sound.wav");
        // game over menu
        this.playController.getView().gameOverMenu(this.playController.getGame().getScore().getScore());
        // if the player is present I set the score
        if (this.playController.getManager().getPlayer().isPresent()) {
            this.playController.getManager().getPlayer().get()
                    .setBestScore(this.playController.getGame().getScore().getScore());
        }
    }

    @Override
    public void speedChange() {
        this.timer.stop();
        this.resumePlay();
    }

    /*
     * Control if there is level up, in that case it plays a sound.
     */
    private void levelUp() {
        if (!this.currentLevel.equals(this.playController.getGame().getScore().getLevel())) {
            // change current level
            this.currentLevel = this.playController.getGame().getScore().getLevel();
            // sound
            this.playController.getSound().play("level_up_sound.wav");
        }
    }

    @Override
    public void eliminationRow() {
        // redraw the board
        this.playController.getView().drawBoard(this.playController.getGame().getBoard().getBoard());
        // level up sound
        this.levelUp();
        // initializing the playground
        this.initPlaygroud();
    }

    @Override
    public void placePiece() {
        this.timer.stop();
        // notify the death of the piece to the game
        this.playController.getGame().placePiece();
        // rows elimination
        this.eliminationRow();
        // initialing the speed
        this.playController.getSpeed().setSpeedToCurrentLevel();
        this.resumePlay();
    }

    /*
     * Initialize the playground drawing preview, projection and score.
     */
    private void initPlaygroud() {
        // draw the preview
        this.playController.getView().drawPreview(this.playController.getGame().getNext().getCoordinates(),
                this.playController.getGame().getNext().getColor());
        // draw the projection
        this.playController.getView().drawProjection(this.playController.getProjection().newProjection(),
                this.playController.getProjection().getColor());
        // draw score
        this.playController.getView().setScore(this.playController.getGame().getScore().getScore());
    }
}
