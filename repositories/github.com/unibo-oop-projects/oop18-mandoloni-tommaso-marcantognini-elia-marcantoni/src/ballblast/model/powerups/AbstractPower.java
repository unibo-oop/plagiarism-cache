package ballblast.model.powerups;

import ballblast.model.gameobjects.AbstractGameObject;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectType;
import ballblast.model.gameobjects.Player;

/**
 * The abstract class representing a {@link Power}.
 */
public abstract class AbstractPower extends AbstractGameObject implements Power {

    private static final double AVAILABLE_TIME = 10;
    private static final double LIFE_TIME = 15;
    private static final double DEFAULT_HEIGHT = 6;
    private static final double DEFAULT_WIDTH = 6;

    private GameObject player;
    private boolean active;
    private final PowerTypes powerType;
    private double lifeTime;
    private double availableTime;

    /**
     * Constructor for a generic {@link Power}.
     * 
     * @param powerType The type of the {@link Power}.
     */
    protected AbstractPower(final PowerTypes powerType) {
        super(GameObjectType.POWERUP);
        this.powerType = powerType;
        this.setHeight(DEFAULT_HEIGHT);
        this.setWidth(DEFAULT_WIDTH);
    }

    @Override
    public final void update(final double elapsed) {
        super.update(elapsed);
        if (this.isActive()) {
            this.lifeTime += elapsed;
            this.checkLife();
        } else {
            this.availableTime += elapsed;
            this.checkAvailable();
        }
    }

    @Override
    public final void activate(final GameObject player) {
        if (!this.isActive()) {
            this.active = true;
            this.player = player;
            this.performPower();
        }
    }

    @Override
    public final void deactivate() {
        if (this.isActive()) {
            this.stopPerforming();
            this.active = false;
        }
    }

    @Override
    public final boolean isActive() {
        return this.active;
    }

    @Override
    public final PowerTypes getPowerType() {
        return this.powerType;
    }

    /**
     * The specific action performed by a {@link Power}.
     */
    protected abstract void performPower();

    /**
     * Stops the performed action of the {@link Power}.
     */
    protected abstract void stopPerforming();

    /**
     * Gets the {@link Player} who gets the {@link Power}.
     * 
     * @return The {@link Player} who gets the {@link Power}.
     */
    protected Player getPlayer() {
        return (Player) this.player;
    }

    private void checkLife() {
        if (this.lifeTime >= LIFE_TIME) {
            this.deactivate();
            this.destroy();
        }
    }

    private void checkAvailable() {
        if (this.availableTime >= AVAILABLE_TIME) {
            this.destroy();
        }
    }
}
