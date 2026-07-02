package powpaw.player.controller.impl;

import java.util.List;
import java.util.Optional;

import powpaw.common.DirectionVector;
import powpaw.player.controller.StaticStats;
import powpaw.player.controller.api.AttackController;
import powpaw.player.model.api.Player;
import powpaw.player.model.impl.PlayerImpl.PlayerState;
import powpaw.world.controller.ScreenController;

/**
 * Class AttackController that controll if a player hit another player and their
 * respective deaths.
 * 
 * @author Giacomo Grassetti
 */
public final class AttackControllerImpl implements AttackController {

    private Player playerOne;
    private Player playerTwo;

    /**
     * Player setters for the class.
     * 
     * @param players A list of Player objects that contains players.
     */
    @Override
    public void setPlayers(final List<Player> players) {
        this.playerOne = players.get(0);
        this.playerTwo = players.get(1);
    }

    @Override
    public Optional<Player> checkDeath() {
        if (ScreenController.isOutOfScreen(playerOne.getHitbox())) {
            return Optional.of(playerTwo);
        }
        if (ScreenController.isOutOfScreen(this.playerTwo.getHitbox())) {
            return Optional.of(playerOne);
        }
        return Optional.empty();
    }

    @Override
    public void checkHit(final Player player) {

        if (this.playerTwo.getState() != PlayerState.DODGE
                && this.playerOne.getHitbox().checkCollision(this.playerTwo.getHitbox().getShape())
                && player.getNumber() == 1) {
            if (this.playerOne.getWeapon().isPresent()) {
                this.playerOne.getWeapon().get().decrementDurability();
            }
            if (this.playerOne.getDirectionState().equals(PlayerState.WALK_LEFT)) {
                this.playerTwo.receiveAttack(DirectionVector.LEFT.getPoint(), StaticStats.getStatsP1().getAttack());
            } else {
                this.playerTwo.receiveAttack(DirectionVector.RIGHT.getPoint(), StaticStats.getStatsP1().getAttack());
            }
        }
        if (this.playerOne.getState() != PlayerState.DODGE
                && this.playerTwo.getHitbox().checkCollision(this.playerOne.getHitbox().getShape())
                && player.getNumber() == 2) {
            if (this.playerTwo.getWeapon().isPresent()) {
                this.playerTwo.getWeapon().get().decrementDurability();
            }
            if (this.playerTwo.getDirectionState().equals(PlayerState.WALK_LEFT)) {
                this.playerOne.receiveAttack(DirectionVector.LEFT.getPoint(), StaticStats.getStatsP2().getAttack());
            } else {
                this.playerOne.receiveAttack(DirectionVector.RIGHT.getPoint(), StaticStats.getStatsP2().getAttack());
            }

        }
    }

}
