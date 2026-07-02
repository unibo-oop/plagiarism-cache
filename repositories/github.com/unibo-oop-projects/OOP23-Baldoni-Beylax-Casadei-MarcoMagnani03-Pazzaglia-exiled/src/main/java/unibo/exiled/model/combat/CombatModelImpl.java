package unibo.exiled.model.combat;

import java.util.Optional;
import java.util.Set;

import javax.annotation.concurrent.Immutable;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.utilities.Position;

/**
 * The implementation of the combat model.
 */
@Immutable
public final class CombatModelImpl implements CombatModel {
    private final GameModel model;
    private final Combat combat;

    /**
     * The constructor of CombatModelImpl.
     * 
     * @param model the game model.
     */
    public CombatModelImpl(final GameModel model) {
        this.model = model;
        this.combat = new CombatImpl();
    }

    @Override
    public void newCombat() {
        final Position combatPosition = this.model.getCharacterModel().getPlayerPosition();
        final Player player = this.model.getCharacterModel().getPlayer().get();
        final Enemy enemy = (Enemy) this.model.getCharacterModel().getCharacterFromPosition(combatPosition).get();

        this.combat.setPlayer(Optional.of(player));
        this.combat.setEnemy(enemy);
        this.combat.setCombatPosition(combatPosition);
        this.setCombatStatus(CombatStatus.IDLE);
    }

    @Override
    public Optional<Player> getPlayer() {
        return this.combat.getPlayer();
    }

    @Override
    public Optional<Enemy> getEnemy() {
        return this.combat.getEnemy();
    }

    @Override
    public CombatStatus getCombatStatus() {
        return this.combat.getCombatStatus();
    }

    @Override
    public void setCombatStatus(final CombatStatus status) {
        this.combat.setCombatStatus(status);
    }

    @Override
    public String getEnemyName() {
        return this.combat.getEnemy().get().getName();
    }

    @Override
    public double getEnemyHealth() {
        return this.combat.getEnemy().get().getHealth();
    }

    @Override
    public double getEnemyHealthCap() {
        return this.combat.getEnemy().get().getHealthCap();
    }

    @Override
    public Set<MagicMove> getEnemyMoves() {
        return this.combat.getEnemy().get().getMoveSet().getMagicMoves();
    }

    @Override
    public String getEnemyClassName() {
        return this.combat.getEnemy().get().getType().getName();
    }

    @Override
    public Position getCombatPosition() {
        return this.combat.getCombatPosition();
    }

    @Override
    public boolean needsPlayerToChangeMove() {
        return this.combat.needsPlayerToChangeMove();
    }
}
