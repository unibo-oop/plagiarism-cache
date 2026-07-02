package it.unibo.jtrs.game.core.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.jtrs.controller.api.Application;
import it.unibo.jtrs.model.api.GameModel.GameState;
import it.unibo.jtrs.model.api.GameModel.Interaction;
import it.unibo.jtrs.utils.AudioEngine;
import it.unibo.jtrs.utils.Chronometer;

/**
 * This class implements a KeyListener to define the correct action to perform.
 */
public class KeyboardReader implements KeyListener {

    private static final int SCAN_RATE = 50;

    private final Chronometer chrono;
    private final Application application;

    /**
     * Constructor.
     *
     * @param application the application to send the commands to
     */
    public KeyboardReader(final Application application) {
        this.application = application;
        this.chrono = new Chronometer();
    }

    /**
     * {@inheritDoc}<br><br>
     *
     * Directional keys or (W, A, S, D) moves the Tetromino, SPACE sends an interrupt
     * signal to the application, M mutes/unmutes the playing soundtrack and ESC
     * terminates the application.
     *
     */
    @Override
    public void keyPressed(final KeyEvent e) {

        if (this.chrono.elapsed() > SCAN_RATE) {

            this.chrono.reset();
            if (this.application.getState() == GameState.RUNNING) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        application.getGameController().advance(Interaction.ROTATE);
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        application.getGameController().advance(Interaction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        application.getGameController().advance(Interaction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        application.getGameController().advance(Interaction.RIGHT);
                        break;
                    default:
                }
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    application.interrupt();
                    break;
                case KeyEvent.VK_M:
                    if (AudioEngine.isPlaying()) {
                        AudioEngine.mute();
                    } else {
                        AudioEngine.unmute();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    application.terminate();
                    break;
                default:
            }
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) { }

    @Override
    public void keyTyped(final KeyEvent e) { }

}
