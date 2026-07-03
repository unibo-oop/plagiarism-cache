package it.unibo.tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class {@link KeyHandler} that handles key events.
 * implements {@link KeyListener}.
 */
public class KeyHandler implements KeyListener {
    
    public static boolean upPressed,
            downPressed,
            leftPressed,
            rightPressed,
            pausePressed;

    @Override
    public void keyTyped(KeyEvent e) {
        /*
         * Not implemented
         */
    }

    /**
     * Method that manage what key is being pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        /*
         * Check waht key is being pressed.
         */
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        /*
         * Pause if spacebar is pressed. 
         */
        if(code == KeyEvent.VK_SPACE) {
            if (pausePressed) {
                pausePressed = false;
                GamePanel.music.play(0, true);
                GamePanel.music.loop();
                GamePanel.music.setVolume(-15.0f);
            } else {
                pausePressed = true;
                GamePanel.music.stop();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e){
        /*
         * Not implemented
         */
    }
}
