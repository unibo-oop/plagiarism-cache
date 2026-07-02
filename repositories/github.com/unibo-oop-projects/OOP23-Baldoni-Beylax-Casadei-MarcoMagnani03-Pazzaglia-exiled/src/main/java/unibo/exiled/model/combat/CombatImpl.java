package unibo.exiled.model.combat;

import java.util.Optional;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.utilities.Position;

/**
 * The implementation of the combat.
 */
public final class CombatImpl implements Combat {
    private Player player;
    private Enemy enemy;
    private Position combatPosition;
    private CombatStatus combatStatus;

    /**
     * Constructor for CombatImpl.
     */
    public CombatImpl() {
        this.player = null;
        this.enemy = null;
        this.combatPosition = null;
        this.combatStatus = CombatStatus.IDLE;
    }

    @Override
    public Position getCombatPosition() {
        return this.combatPosition;
    }

    @Override
    public void setCombatPosition(final Position combatPosition) {
        this.combatPosition = combatPosition;
    }

    @Override
    public CombatStatus getCombatStatus() {
        return this.combatStatus;
    }

    @Override
    public void setCombatStatus(final CombatStatus status) {
        this.combatStatus = status;
    }

    @Override
    public Optional<Player> getPlayer() {
        return Optional.of(this.player);
    }

    @Override
    public void setPlayer(final Optional<Player> player) {
        this.player = player.get();
    }

    @Override
    public boolean needsPlayerToChangeMove() {
        return this.player.getExceedingMagicMove().isPresent();
    }

    @Override
    public Optional<Enemy> getEnemy() {
        return Optional.of(this.enemy);
    }

    @Override
    public void setEnemy(final Enemy enemy) {
        this.enemy = enemy;
    }
}
