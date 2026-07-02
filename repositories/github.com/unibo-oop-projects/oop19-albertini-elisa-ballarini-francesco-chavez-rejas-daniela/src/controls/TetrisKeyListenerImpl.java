package controls;

import java.awt.event.KeyEvent;
import gamegraphics.ViewGame;
import movements.Movements;
import playcontroller.PlayController;
import speed.Speed;

/**
 * KeyListener for the Game.
 * 
 */
public class TetrisKeyListenerImpl implements TetrisKeyListener {
    private final TetrisKeyListenerLogics logics;

    /**
     * @param playController : PlayController Object
     */
    public TetrisKeyListenerImpl(final PlayController playController) {
        this.logics = new TetrisKeyListenerLogicsImpl(playController);
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public final void keyPressed(final KeyEvent e) {
        // LEFT MOVEMENT
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.logics.moveLeft();
        }
        // RIGHT MOVEMENT
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.logics.moveRight();
        }
        // ROTATE CLOCKWISE
        if (e.getKeyCode() == KeyEvent.VK_E) {
            this.logics.rotateClockwise();
        }
        // ROTATE COUNTERCLOCKWISE
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            this.logics.rotateCounterClockwise();
        }
        // HOLDBOX
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            this.logics.useHoldbox();
        }
        // BEGIN ACCELERATION
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.logics.beginAcceleration();
        }
        // INSTANT POSITIONING
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.logics.instantPositioning();
        }
        // MUTE VOLUME
        if (e.getKeyCode() == KeyEvent.VK_M) {
            this.logics.muteVolume();
        }
        // ESC - PAUSE
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.logics.pauseGame();
        }
        // BOOSTER 1
        // When pressed, deletes all the rows with 80% or more blocks in it
        if (e.getKeyCode() == KeyEvent.VK_1) {
            this.logics.cancelMultipleLines();
        }
        // BOOSTER 2
        // When pressed, deletes the top row
        if (e.getKeyCode() == KeyEvent.VK_2) {
            this.logics.cancelTopLine();
        }
        //BOOSTER 3
        // When pressed, deletes the first row from the button
        if (e.getKeyCode() == KeyEvent.VK_3) {
            this.logics.switchPiece();
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {
        // END ACCELERATION
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.logics.endAcceleration();
        }
    }

    @Override
    public final void setMovements(final Movements movements) {
        this.logics.setMovements(movements);
    }

    @Override
    public final void setView(final ViewGame view) {
        this.logics.setView(view);
    }

    @Override
    public final void setSpeed(final Speed speed) {
        this.logics.setSpeed(speed);
    }
}
