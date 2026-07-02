package controller.player;

import controller.ControllerImpl;
import controller.GameStatusImpl;
import controller.LevelManager;
import controller.LevelManagerImpl;
import controller.SoundManager;
import controller.SoundManagerImpl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.player.Player;
import model.player.PlayerImpl;
import utilities.Directions;
import view.PlayerGraphic;
import view.PlayerGraphicImpl;

/**
 * Class that manages player input while playing.
 *
 */
public final class PlayerInputImpl implements PlayerInput {

    private static final PlayerInputImpl SINGLETON = new PlayerInputImpl();
    private final Player player;
    private final PlayerGraphic playerGraphic;
    private final SoundManager soundManager;
    private final LevelManager levelManager;
    private final boolean isUnix;
    private boolean repeat;

    /**
     * Constructor.
     */
    private PlayerInputImpl() {
        this.player = PlayerImpl.get();
        this.playerGraphic = PlayerGraphicImpl.get();
        this.soundManager = SoundManagerImpl.get();
        this.levelManager = LevelManagerImpl.get();
        this.isUnix = new ControllerImpl().isUnix();
    }

    @Override
    public void keyPressed(final KeyEvent event) {
        if (!this.isRepeat()) {
            switch (event.getCode()) {
            case W:
            case UP:
                this.stepSound();
                this.playerGraphic.stopAnimation();
                this.player.setCharFrame(this.playerGraphic.getCharFrames());
                this.player.move(Directions.UP);
                this.playerGraphic.charUp();
                this.setRepeat(true);
                break;
            case S:
            case DOWN:
                this.stepSound();
                this.playerGraphic.stopAnimation();
                this.player.setCharFrame(this.playerGraphic.getCharFrames());
                this.player.move(Directions.DOWN);
                this.playerGraphic.charDown();
                this.setRepeat(true);
                break;
            case A:
            case LEFT:
                this.stepSound();
                this.playerGraphic.stopAnimation();
                this.player.setCharFrame(this.playerGraphic.getCharFrames());
                this.player.move(Directions.LEFT);
                this.playerGraphic.charLeft();
                this.setRepeat(true);
                break;
            case D:
            case RIGHT:
                this.stepSound();
                this.playerGraphic.stopAnimation();
                this.player.setCharFrame(this.playerGraphic.getCharFrames());
                this.player.move(Directions.RIGHT);
                this.playerGraphic.charRight();
                this.setRepeat(true);
                break;
            case ESCAPE:
                GameStatusImpl.get().gamePause();
                break;
            default:
                break;
            }
        }
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        final KeyCode key = event.getCode();

        if (key == KeyCode.W || key == KeyCode.S || key == KeyCode.A || key == KeyCode.D || key == KeyCode.UP
                || key == KeyCode.DOWN || key == KeyCode.LEFT || key == KeyCode.RIGHT) {
            this.setRepeat(false);
        }
    }

    // Returns true if a button has been pressed, false otherwise.
    private boolean isRepeat() {
        return this.repeat;
    }

    @Override
    public void setRepeat(final boolean repeat) {
        this.repeat = repeat;
    }

    // Starts the current level's step sound.
    private void stepSound() {
        if (!isUnix) {
            this.soundManager.startEffect(this.levelManager.chooseLevel().getStepSoundPath());
        }
    }

    /**
     * Returns an instance of PlayerInputImpl.
     * 
     * @return an instance of PlayerInputImpl
     */
    public static PlayerInputImpl get() {
        return SINGLETON;
    }
}
