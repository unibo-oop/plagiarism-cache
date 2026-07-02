package javawulf.model.powerup;

import java.util.Optional;

import javawulf.model.player.Player;

/**
 * PowerUpHandler is used for making interact player and powerUps.
 */
public final class PowerUpHandlerImpl implements PowerUpHandler {

    private Optional<PowerUp> powerUpActive;

    /**
     * Creates the powerUpHandler.
     */
    public PowerUpHandlerImpl() {
        this.powerUpActive = Optional.empty();
    }

    @Override
    public void collectPowerUp(final PowerUp powerUpPicked, final Player player) {
        if (powerUpActive.isPresent()) {
            powerUpActive.get().finishEffect(player);
        }
        this.powerUpActive = Optional.of(powerUpPicked);
    }

    @Override
    public void update(final Player player) {
        if (powerUpActive.isPresent()) {
            powerUpActive.get().updateDuration();
            if (checkUpFinished()) {
                powerUpActive.get().finishEffect(player);
                powerUpActive = Optional.empty();
            }
        }
        changePlayerColor(player);
    }

    @Override
    public Optional<PowerUp> getPowerUpActive() {
        return powerUpActive;
    }

    private boolean checkUpFinished() {
        return !powerUpActive.get().stillActive();
    }

    private void changePlayerColor(final Player player) {
        if (this.getPowerUpActive().isPresent()) {
            if (PowerUpType.ATTACK.getType().equals(powerUpActive.get().getType())) {
                player.setColor(Player.PlayerColor.STRENGTH);
            }
            if (PowerUpType.DOUBLE_POINTS.getType().equals(powerUpActive.get().getType())) {
                player.setColor(Player.PlayerColor.DOUBLE_POINTS);
            }
            if (PowerUpType.INVINCIBILITY.getType().equals(powerUpActive.get().getType())) {
                player.setColor(Player.PlayerColor.INVULNERABILITY);
            }
            if (PowerUpType.SPEED.getType().equals(powerUpActive.get().getType())) {
                player.setColor(Player.PlayerColor.SPEED);
            }
        } else {
            player.setColor(Player.PlayerColor.NONE);
        }
    }

}
