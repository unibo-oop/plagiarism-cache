package powpaw.player.controller.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.scene.input.KeyCode;
import powpaw.config.Parser;
import powpaw.player.controller.api.AttackController;
import powpaw.player.model.api.Player;
import powpaw.player.view.api.KeyObserver;

/**
 * Is an implementation of the {@code KeyObserver} interface, which provides
 * methods for handling key press and release events. This class is used to
 * observe the keyboard input and update the state of the associated
 * {@code Player} object accordingly.
 * 
 * @author Alessia Carfì
 */
public final class KeyObserverImpl implements KeyObserver {

    private final Player player;

    private final KeyCode keyJump;
    private final KeyCode keyLeft;
    private final KeyCode keyRight;
    private final KeyCode keyAttack;
    private final KeyCode keyDodge;
    private final Set<KeyCode> keys = new HashSet<>();
    private final AttackController attackController;

    /**
     * Constructs a new KeyObserverImpl.
     * 
     * @param player the Player to observe.
     * @param parser the Parser used to parse the keyboard input commands.
     * @param ac     the AttackController object used to check for hits during
     *               attacks.
     */
    public KeyObserverImpl(final Player player, final Parser parser, final AttackController ac) {
        this.player = player;
        final Map<String, KeyCode> commands = parser.getCommands(player.getNumber());
        this.attackController = ac;

        this.keyJump = commands.get("jump");
        this.keyLeft = commands.get("left");
        this.keyRight = commands.get("right");
        this.keyAttack = commands.get("attack");
        this.keyDodge = commands.get("dodge");

        this.keys.add(keyJump);
        this.keys.add(keyLeft);
        this.keys.add(keyRight);
        this.keys.add(keyAttack);
        this.keys.add(keyDodge);
    }

    @Override
    public void keyPressed(final KeyCode event) {
        if (!this.keys.contains(event)) {
            return;
        }

        if (event == this.keyJump && !this.player.isFalling()) {
            this.player.setIsJumping(true);
        }
        if (event == this.keyRight) {
            this.player.setIsMovingRight(true);
        }
        if (event == this.keyLeft) {
            this.player.setIsMovingLeft(true);
        }
        if (event == this.keyAttack) {
            this.player.setIsAttacking(true);
            this.attackController.checkHit(this.player);
        }
        if (event == this.keyDodge) {
            this.player.setIsDodging(true);
        }
    }

    @Override
    public void keyReleased(final KeyCode event) {

        if (!this.keys.contains(event)) {
            return;
        }

        if (event == this.keyJump) {
            this.player.setIsJumping(false);
        }
        if (event == this.keyRight) {
            this.player.setIsMovingRight(false);
        }
        if (event == this.keyLeft) {
            this.player.setIsMovingLeft(false);
        }
        if (event == this.keyAttack) {
            this.player.setIsAttacking(false);
        }
        if (event == this.keyDodge) {
            this.player.setIsDodging(false);
        }
    }
}
