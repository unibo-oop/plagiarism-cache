/**
 * A class representing the controller associated to each player currently present on screen
 * @author Lorenzo Massone
 */
package controllers.commands;

import model.Character;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerController {

    private final Character player ;
    private final KeyCode leftKey ;
    private final KeyCode rightKey ;
    private final KeyCode upKey ;
    private final KeyCode downKey ;
    
    public PlayerController(Character player, KeyCode leftKey, KeyCode rightKey, KeyCode upKey, KeyCode downKey) {
        this.player = player;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    /**
     * The method used to register keyboard inputs and to redirect the associated player.
     * @param scene used to catch inputs for the character controller istantiated
     */
    public void register(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == leftKey) player.setLeft(true);;
            if (e.getCode() == rightKey) player.setRight(true);
            if (e.getCode() == upKey) player.setUp(true);;
            if (e.getCode() == downKey) player.setDown(true);;
        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            if (e.getCode() == leftKey) player.setLeft(false);;
            if (e.getCode() == rightKey) player.setRight(false);
            if (e.getCode() == upKey) player.setUp(false);
            if (e.getCode() == downKey) player.setDown(false);;
        });
    }
}