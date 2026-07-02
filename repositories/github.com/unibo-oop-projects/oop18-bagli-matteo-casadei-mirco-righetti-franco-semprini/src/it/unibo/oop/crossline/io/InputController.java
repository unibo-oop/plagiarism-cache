package it.unibo.oop.crossline.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import it.unibo.oop.crossline.debug.Debugger;
import it.unibo.oop.crossline.game.actor.player.PlayerImpl;
import it.unibo.oop.crossline.io.keyboard.KeyboardButton;
import it.unibo.oop.crossline.io.keyboard.KeyboardHandler;
import it.unibo.oop.crossline.io.mouse.MouseButton;
import it.unibo.oop.crossline.io.mouse.MouseHandler;

/**
 * This class handle the keyboard input.
 */
public class InputController implements InputProcessor {

    /**
     * The handler for mouse.
     */
    private static MouseHandler mouseHandler = new MouseHandler();
    /**
     * The handler for keyboard.
     */
    private static KeyboardHandler keyboardHandler = new KeyboardHandler();

    /**
     * Constructor of KeyboardController.
     * 
     * @param player the current player
     */
    public InputController(final PlayerImpl player) {
        /**
         * Keyboard handler
         */
        // WASD
        /*
         * keyboardHandler.registerObserver(new KeyboardButton(// Forward Keys.W,
         * MoveDirections.FORWARD.name(), () -> { if (Gdx.input.isKeyPressed(Keys.A)) {
         * player.move(MoveDirections.FORWARD_LEFT.getDirection()); } else if
         * (Gdx.input.isKeyPressed(Keys.D)) {
         * player.move(MoveDirections.FORWARD_RIGHT.getDirection()); } else {
         * player.move(MoveDirections.FORWARD.getDirection()); } }));
         */
        keyboardHandler.registerObserver(new KeyboardButton(// Left
                Keys.A, MoveDirections.LEFT.name(), () -> {
                    if (Gdx.input.isKeyPressed(Keys.W)) {
                        player.move(MoveDirections.FORWARD_LEFT.getDirection());
                    } else if (Gdx.input.isKeyPressed(Keys.S)) {
                        player.move(MoveDirections.BACKWARD_LEFT.getDirection());
                    } else {
                        player.move(MoveDirections.LEFT.getDirection());
                    }
                }));
        /*
         * keyboardHandler.registerObserver(new KeyboardButton(// Backward Keys.S,
         * MoveDirections.BACKWARD.name(), () -> { if (Gdx.input.isKeyPressed(Keys.A)) {
         * player.move(MoveDirections.BACKWARD_LEFT.getDirection()); } else if
         * (Gdx.input.isKeyPressed(Keys.D)) {
         * player.move(MoveDirections.BACKWARD_RIGHT.getDirection()); } else {
         * player.move(MoveDirections.BACKWARD.getDirection()); } }));
         */
        keyboardHandler.registerObserver(new KeyboardButton(// Right
                Keys.D, MoveDirections.RIGHT.name(), () -> {
                    if (Gdx.input.isKeyPressed(Keys.W)) {
                        player.move(MoveDirections.FORWARD_RIGHT.getDirection());
                    } else if (Gdx.input.isKeyPressed(Keys.S)) {
                        player.move(MoveDirections.BACKWARD_RIGHT.getDirection());
                    } else {
                        player.move(MoveDirections.RIGHT.getDirection());
                    }
                }));
        keyboardHandler.registerObserver(new KeyboardButton(// Space
                Keys.SPACE, "Jump", () -> player.jump()));
        // Arrows
        keyboardHandler.registerObserver(new KeyboardButton(// Left Arrow
                Keys.LEFT, ShootDirections.LEFT.name(), () -> {
                    if (Gdx.input.isKeyPressed(Keys.UP)) {
                        player.getWeapon().setDirection(ShootDirections.UP_LEFT.getDirection());
                    } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
                        player.getWeapon().setDirection(ShootDirections.DOWN_LEFT.getDirection());
                    } else {
                        player.getWeapon().setDirection(ShootDirections.LEFT.getDirection());
                    }
                    player.setShouldShoot(true);
                    player.shoot();
                }));
        keyboardHandler.registerObserver(new KeyboardButton(// Up Arrow
                Keys.UP, ShootDirections.UP.name(), () -> {
                    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                        player.getWeapon().setDirection(ShootDirections.UP_LEFT.getDirection());
                    } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                        player.getWeapon().setDirection(ShootDirections.UP_RIGHT.getDirection());
                    } else {
                        player.getWeapon().setDirection(ShootDirections.UP.getDirection());
                    }
                    player.setShouldShoot(true);
                    player.shoot();
                }));
        keyboardHandler.registerObserver(new KeyboardButton(// Right Arrow
                Keys.RIGHT, ShootDirections.RIGHT.name(), () -> {
                    if (Gdx.input.isKeyPressed(Keys.UP)) {
                        player.getWeapon().setDirection(ShootDirections.UP_RIGHT.getDirection());
                    } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
                        player.getWeapon().setDirection(ShootDirections.DOWN_RIGHT.getDirection());
                    } else {
                        player.getWeapon().setDirection(ShootDirections.RIGHT.getDirection());
                    }
                    player.setShouldShoot(true);
                    player.shoot();
                }));
        keyboardHandler.registerObserver(new KeyboardButton(// Down Arrow
                Keys.DOWN, ShootDirections.DOWN.name(), () -> {
                    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                        player.getWeapon().setDirection(ShootDirections.DOWN_LEFT.getDirection());
                    } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                        player.getWeapon().setDirection(ShootDirections.DOWN_RIGHT.getDirection());
                    } else {
                        player.getWeapon().setDirection(ShootDirections.DOWN.getDirection());
                    }
                    player.setShouldShoot(true);
                    player.shoot();
                }));
        keyboardHandler.registerObserver(new KeyboardButton(// Enter
                Keys.ENTER, "Enter", () -> Debugger.getInstance().printMessage("//TODO Implement ENTER")));
        /**
         * Mouse handler
         */
        mouseHandler.registerObserver(new MouseButton(// Left Mouse
                Buttons.LEFT, "Left mouse", () -> Debugger.getInstance().printMessage("//TODO Implement")));
        mouseHandler.registerObserver(new MouseButton(// Middle Mouse
                Buttons.MIDDLE, "Middle mouse", () -> Debugger.getInstance().printMessage("//TODO Implement")));
        mouseHandler.registerObserver(new MouseButton(// Right Mouse
                Buttons.RIGHT, "Right mouse", () -> Debugger.getInstance().printMessage("//TODO Implement")));

        Debugger.getInstance().printMessage(keyboardHandler.toString());
        Debugger.getInstance().printMessage(mouseHandler.toString());
    }

    /**
     * This is activated once when a key on the keyboard is pressed down.
     * 
     * @param keyCode is one of the constants in Input.keys
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(final int keyCode) {
        return false;
    }

    /**
     * This as activated once when the key that was pressed down is released.
     * 
     * @param keyCode is one of the constants in Input.keys
     * @return whether the input was processed
     */
    @Override
    public boolean keyUp(final int keyCode) {
        return false;
    }

    /**
     * This is activated everytime the keyboard sends a character. Unlike KeyUp and
     * KeyDown this will be called many times while the key is down
     * 
     * @param character the character
     * @return whether the input was processed
     */
    @Override
    public boolean keyTyped(final char character) {
        return keyboardHandler.updateKeyPressed(readKey());
    }

    /**
     * Called when a mouse button is pressed down.
     * 
     * @param x       the x coordinate, origin is in the upper left corner
     * @param y       the y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchDown(final int x, final int y, final int pointer, final int button) {
        return mouseHandler.updateButtonPressed(x, y, true, button);
    }

    /**
     * Called when a finger or mouse button is released.
     * 
     * @param x       the x coordinate, origin is in the upper left corner
     * @param y       the y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchUp(final int x, final int y, final int pointer, final int button) {
        return mouseHandler.updateButtonPressed(x, y, false, button);
    }

    /**
     * Called each time the finger moves while in the down state.
     * 
     * @param x       the x coordinate, origin is in the upper left corner
     * @param y       the y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event
     * @return whether the input was processed
     */
    @Override
    public boolean touchDragged(final int x, final int y, final int pointer) {
        return mouseHandler.updateMovedDragger(x, y);
    }

    /**
     * Called when the mouse moves whether a button is pressed or not.
     * 
     * @param x the x coordinate, origin is in the upper left corner
     * @param y the y coordinate, origin is in the upper left corner
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(final int x, final int y) {
        return mouseHandler.updateMoved(x, y);
    }

    /**
     * Called when a mouse scroll wheel is moved. Mouse down to increase, up to
     * decrease
     * 
     * @param amount the scroll amount
     * @return whether the input was processed
     */
    @Override
    public boolean scrolled(final int amount) {
        return mouseHandler.updateScrolled(amount);
    }

    /**
     * @return the mouseHandler
     */
    public static final MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    /**
     * @param mouseHandler the mouseHandler to set
     */
    public static final void setMouseHandler(final MouseHandler mouseHandler) {
        InputController.mouseHandler = mouseHandler;
    }

    /**
     * @return the keyboardHandler
     */
    public static final KeyboardHandler getKeyboardHandler() {
        return keyboardHandler;
    }

    /**
     * @param keyboardHandler the keyboardHandler to set
     */
    public static final void setKeyboardHandler(final KeyboardHandler keyboardHandler) {
        InputController.keyboardHandler = keyboardHandler;
    }

    /**
     * This method return keys without character.
     * 
     * @return keyCode
     */
    public static int readKey() {
        if (Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.LEFT)) {
            return Keys.LEFT;
        } else if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.UP)) {
            return Keys.UP;
        } else if (Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
            return Keys.RIGHT;
        } else if (Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.DOWN)) {
            return Keys.DOWN;
        } else if (Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isKeyPressed(Keys.SPACE)) {
            return Keys.SPACE;
        } else if (Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyPressed(Keys.ENTER)) {
            return Keys.ENTER;
        } else if (Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.W)) {
            return Keys.W;
        } else if (Gdx.input.isKeyJustPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.A)) {
            return Keys.A;
        } else if (Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.S)) {
            return Keys.S;
        } else if (Gdx.input.isKeyJustPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.D)) {
            return Keys.D;
        }
        return -1;
    }
}
