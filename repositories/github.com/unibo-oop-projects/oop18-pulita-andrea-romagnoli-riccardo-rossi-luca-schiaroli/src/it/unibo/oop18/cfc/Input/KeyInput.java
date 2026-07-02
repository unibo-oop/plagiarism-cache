package it.unibo.oop18.cfc.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

import it.unibo.oop18.cfc.GameState.GameState;
import it.unibo.oop18.cfc.GameState.GameStates;
import it.unibo.oop18.cfc.GameState.MenuState;
import it.unibo.oop18.cfc.Manager.GameStateManager;
import it.unibo.oop18.cfc.Objects.Entity.Player;
import it.unibo.oop18.cfc.Physics.Direction;
import it.unibo.oop18.cfc.Util.JukeBox;

/**
 * KeyInput that implements {@link KeyListener} to manage the running state
 * input.
 */
public class KeyInput implements KeyListener {

    private final Player player;
    private GameState currentState;
    private final GameStateManager gsm;

    /**
     * Creates {@code KeyInput}.
     *
     * @param player reference to move
     * @param game   state manager used to set state Pause
     */
    public KeyInput(final GameStateManager gsm) {
        this.gsm = gsm;
        this.player = gsm.getPlayState().getPlayer();
    }

    /**
     * It generates a {@link Direction} when specific key is pressed and call
     * specific method on specific Game State.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        currentState = gsm.getCurrentGameState();
        switch (currentState.getGameStateName()) {
        case PLAY:
            playKeyInput(e);
            break;
        case INTRO:
            introKeyInput(e);
            break;
        case GAMEOVER:
            gameOverKeyInput(e);
            break;
        case MENU:
            menuKeyInput(e);
            break;
        case PAUSE:
            pauseKeyInput(e);
            break;
        default:
            break;
        }
    }

    //key pressed during play state
    private void playKeyInput(final KeyEvent e) {
        final Optional<Direction> way;
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            way = Optional.ofNullable(Direction.LEFT);
            break;
        case KeyEvent.VK_DOWN:
            way = Optional.ofNullable(Direction.DOWN);
            break;
        case KeyEvent.VK_RIGHT:
            way = Optional.ofNullable(Direction.RIGHT);
            break;
        case KeyEvent.VK_UP:
            way = Optional.ofNullable(Direction.UP);
            break;
        case KeyEvent.VK_SPACE:
            this.doAction();
            way = Optional.empty();
            break;
        case KeyEvent.VK_P:
        case KeyEvent.VK_ESCAPE:
            JukeBox.stop("music1");
            this.launchPause();
        default:
            way = Optional.ofNullable(Direction.STOP);
            break;
        }
        this.moveEntity(way);
    }

    //key pressed during intro state
    private void introKeyInput(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gsm.setState(GameStates.MENU);
            JukeBox.play("collect");
        }
    }

    //key pressed during game over state
    private void gameOverKeyInput(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gsm.setState(GameStates.MENU);
            JukeBox.play("collect");
        }
    }

    //key pressed during menu state
    private void menuKeyInput(final KeyEvent e) {
        MenuState menu = (MenuState) currentState;
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            menu.goUp();
            break;
        case KeyEvent.VK_DOWN:
            menu.goDown();
            break;
        case KeyEvent.VK_ENTER:
            menu.select();
        }
    }
    
    //key pressed during pause state
    private void pauseKeyInput(final KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:
        case KeyEvent.VK_P:
            gsm.setState(GameStates.PLAY);
            JukeBox.resumeLoop("music1");
            break;
        case KeyEvent.VK_F1:
            gsm.setState(GameStates.MENU);
            break;
        }
    }

    /**
     * It stops the player when a key button is released.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() != KeyEvent.VK_SPACE) {
            this.player.getInput().stop();
        }
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    // to do
    private void doAction() {
        this.player.doAction();
    }

    private void launchPause() {
        this.gsm.setState(GameStates.PAUSE);
    }

    private void moveEntity(final Optional<Direction> way) {
        if (way.isPresent()) {
            this.player.getInput().move(way.get());
        }
    }

}
