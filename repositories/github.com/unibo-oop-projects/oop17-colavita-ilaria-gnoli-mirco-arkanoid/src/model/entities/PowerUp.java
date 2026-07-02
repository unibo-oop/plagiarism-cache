package model.entities;

import javafx.util.Pair;
import model.IAdvancedGame;
import model.ModelCostant;
/**
 * Classe che rappresenta i Power-Up ottenibili dalla rottura dei mattoncini. Implementa {@link IEntityThatMoves}
 * 
 * @author Gnoli Mirco
 *
 */
public class PowerUp implements IEntityThatMoves {

    private Pair<Integer, Integer> pos;
    private int width;
    private int height;
    private int speedY;
    private PowerUpType type;
    /**
     * 
     * @param pos - Pair(int, int), posizione iniziale
     * @param type - {@link PowerUpType}
     */
    public PowerUp(final Pair<Integer, Integer> pos, final PowerUpType type) {
        this.pos = pos;
        this.width = ModelCostant.POWERUP_WIDTH;
        this.height = ModelCostant.POWERUP_HEIGHT;
        this.speedY = ModelCostant.DEFAULT_SPEED;
        this.type = type;
    }

    @Override
    public final int getMaxX() {
        return this.pos.getKey() + this.width;
    }

    @Override
    public final int getMinX() {
        return this.pos.getKey();
    }

    @Override
    public final int getMaxY() {
        return this.pos.getValue() + this.height;
    }

    @Override
    public final int getMinY() {
        return this.pos.getValue();
    }

    @Override
    public final Pair<Integer, Integer> getPosition() {
        return this.pos;
    }

    @Override
    public final void setPosition(final int newX, final int newY) {
        this.pos = new Pair<>(newX, newY);
    }

    @Override
    public final void refreshPosition() {
        setPosition(this.pos.getKey(), this.pos.getValue() + this.speedY);
    }

    /**
     * Attiva l'effetto del power-up.
     * @param game - {@link IBasicGame} su cui applicare gli effetti
     */
    public void active(final IAdvancedGame game) {
        this.type.effect(game);
    }
}
