package controls;

import booster.Booster;
import booster.Booster1Impl;
import booster.Booster2Impl;
import booster.Booster3Impl;
import gamegraphics.ViewGame;
import gamelogic.GameLogic;
import movements.Movements;
import piece.Type;
import playcontroller.PlayController;
import projection.Projection;
import sound.Sound;
import speed.Speed;

/**
 * Logics of the TetrisKeyListener.
 *
 */
public class TetrisKeyListenerLogicsImpl implements TetrisKeyListenerLogics {
    private final GameLogic game;
    private final Projection projection;
    private final Sound sound;
    private final PlayController playController;
    private final Booster booster1, booster2, booster3;
    private ViewGame view;
    private Speed speed;
    private Movements movements;

    private static final String SOUNDNAMEPOSITIONING = "movements_sound_1.wav";
    private static final String SOUNDNAMEMOVEMENTS = "movements_sound_2.wav";

    /**
     * @param playController : PlayController Object
     */
    public TetrisKeyListenerLogicsImpl(final PlayController playController) {
        this.playController = playController;
        this.sound = this.playController.getSound();
        this.movements = this.playController.getMovements();
        this.game = this.playController.getGame();
        this.speed = this.playController.getSpeed();
        this.projection = this.playController.getProjection();

        this.booster1 = new Booster1Impl(true);
        this.booster2 = new Booster2Impl(true);
        this.booster3 = new Booster3Impl(true);
    }

    @Override
    public final void moveLeft() {
        if (this.view != null) {
            this.movements.moveLeft();
            this.view.drawPiece(this.game.getCurrent().getPosition(), this.game.getCurrent().getColor());
            this.view.drawProjection(this.projection.newProjection(), this.projection.getColor());
            this.sound.play(SOUNDNAMEMOVEMENTS);
        }
    }

    @Override
    public final void moveRight() {
        if (this.view != null) {
            this.movements.moveRight();
            this.view.drawPiece(this.game.getCurrent().getPosition(), this.game.getCurrent().getColor());
            this.view.drawProjection(this.projection.newProjection(), this.projection.getColor());
            this.sound.play(SOUNDNAMEMOVEMENTS);
        }
    }

    @Override
    public final void rotateClockwise() {
        if (this.game.getCurrent().getType() != Type.O && this.view != null) {
            this.movements.rotateClockwise();
            this.view.drawPiece(this.game.getCurrent().getPosition(), this.game.getCurrent().getColor());
            this.view.drawProjection(this.projection.newProjection(), this.projection.getColor());
            this.sound.play(SOUNDNAMEMOVEMENTS);
        }
    }

    @Override
    public final void rotateCounterClockwise() {
        if (this.game.getCurrent().getType() != Type.O && this.view != null) {
            this.movements.rotateCounterclockwise();
            this.view.drawPiece(this.game.getCurrent().getPosition(), this.game.getCurrent().getColor());
            this.view.drawProjection(this.projection.newProjection(), this.projection.getColor());
            this.sound.play(SOUNDNAMEMOVEMENTS);
        }
    }

    @Override
    public final void useHoldbox() {
        if (this.game.getHoldBox().canHold() && this.view != null) {
            this.view.drawHoldbox(this.game.getCurrent().getCoordinates(), this.game.getCurrent().getColor());
            this.game.hold();
            this.view.drawPiece(this.game.getCurrent().getPosition(), this.game.getCurrent().getColor());
            this.view.drawProjection(this.projection.newProjection(), this.projection.getColor());
            this.view.drawPreview(this.game.getNext().getCoordinates(), this.game.getNext().getColor());
            this.sound.play(SOUNDNAMEMOVEMENTS);
        }
    }

    @Override
    public final void beginAcceleration() {
        this.speed.acceleratedSpeed();
    }

    @Override
    public final void endAcceleration() {
        this.speed.setSpeedToCurrentLevel();
    }

    @Override
    public final void instantPositioning() {
        if (this.view != null) {
            this.playController.stop();
            this.speed.istantPositioning();
            this.playController.getGameManagement().placePiece();
            this.view.drawPiece(this.game.getCurrent().getPosition(), this.game.getCurrent().getColor());
            this.sound.play(SOUNDNAMEPOSITIONING);
        }
    }

    @Override
    public final void muteVolume() {
        if (this.sound.isEnabled()) {
            this.sound.setEnable(false);
        } else {
            this.sound.setEnable(true);
        }
    }

    @Override
    public final void pauseGame() {
        if (this.view != null) {
            this.view.pauseMenu(this.game.getScore().getScore());
        }
    }

    @Override
    public final void cancelMultipleLines() {
        if (this.view != null) {
            this.booster1.useBooster(this.playController);
        }
    }

    @Override
    public final void cancelTopLine() {
        if (this.view != null) {
            this.booster2.useBooster(this.playController);
        }
    }

    @Override
    public final void switchPiece() {
        if (this.view != null) {
            this.booster3.useBooster(this.playController);
        }
    }

    @Override
    public final void setMovements(final Movements movements) {
        this.movements = movements;
    }

    @Override
    public final void setView(final ViewGame view) {
        this.view = view;
    }

    @Override
    public final void setSpeed(final Speed speed) {
        this.speed = speed;
    }
}
