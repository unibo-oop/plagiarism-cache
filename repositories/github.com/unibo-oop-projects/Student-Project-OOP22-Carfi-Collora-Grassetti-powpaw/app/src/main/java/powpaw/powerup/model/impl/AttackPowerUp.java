package powpaw.powerup.model.impl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import powpaw.player.model.api.PlayerStats;

/**
 * PowerUp for increase attack.
 * 
 * @author Simone Collorà
 */
public class AttackPowerUp extends PowerUpImpl {

    /**
     * increase attack by POWNUMBER for 7 seconds.
     * 
     * @param stats
     */
    @Override
    public void statPowerUp(final PlayerStats stats) {
        final double oldAttack = stats.getAttack();
        stats.setAttack(oldAttack + POWNUMBER);
        new Timeline(new KeyFrame(Duration.seconds(POWERUPDURATION), event -> {
            stats.setAttack(oldAttack);
        })).play();
    }
}
