package game.buffs;

import game.ID;
import game.Player;
import utilities.Clamp;
import utilities.Pair;

/**
 * A powerUp which affects only a player.
 */
public final class PlayerPowerUp extends PowerUpImpl {
    private static final int STANDARD_HEALTH_RECOVERY = 20;
    private static final int STANDARD_SPEED_BOOST = 2;
    private static final int STANDARD_FIRE_RATE_BOOST = 2;
    private static final int STANDARD_SHIELD = 100;
    private final BuffingStrategy strategy;

    /**
     * @param id id of this powerUp.
     * @param position position of this powerUp.
     * @param velocityX velocity X of this powerUp.
     * @param velocityY velocity Y of this powerUp.
     * @param type type of this powerUp.
     * @param strategy the strategy used to calculate how powerful the powerUp needs to be.
     */
    public PlayerPowerUp(final ID id, final Pair<Integer, Integer> position, final int velocityX, final int velocityY, final PowerUpType type, final BuffingStrategy strategy) {
        super(id, position, velocityX, velocityY, type);
        this.strategy = strategy;
    }

    @Override
    protected void applyEffect() {
        final Player player = this.getEntityToBuff();
        if (this.getType().equals(PowerUpType.HEALTH_RECOVERY)) {
            player.setHealth(Clamp.clamp(player.getHealth() + this.strategy.multiplyEffect(STANDARD_HEALTH_RECOVERY), 0, 100));
        } else if (this.getType().equals(PowerUpType.SPEED_BOOST)) {
            player.setVelocity(player.getVelocity().getX() * STANDARD_SPEED_BOOST, player.getVelocity().getY() * STANDARD_SPEED_BOOST);
        } else if (this.getType().equals(PowerUpType.FIRE_RATE_BOOST)) {
            player.setShotCD(player.getShotCD() - this.strategy.multiplyEffect(STANDARD_FIRE_RATE_BOOST));
        } else {
            if (!this.isActivated()) {
                player.setShield(this.strategy.multiplyEffect(STANDARD_SHIELD));
            } else if (player.getShield() == 0) {
                this.setDead();
            }
        }
    }

    @Override
    public void reset() {
        final Player player = this.getEntityToBuff();
        if (this.getType().equals(PowerUpType.FIRE_RATE_BOOST)) {
            player.setShotCD(player.getShotCD() + this.strategy.multiplyEffect(STANDARD_FIRE_RATE_BOOST));
        } else if (this.getType().equals(PowerUpType.SHIELD)) {
            player.setShield(0);
        }
    }

    @Override
    protected void setState() {
        this.setPosition(this.getEntityToBuff().getPosition());
        this.setHitbox(this.getEntityToBuff().getHitbox());
    }
}
