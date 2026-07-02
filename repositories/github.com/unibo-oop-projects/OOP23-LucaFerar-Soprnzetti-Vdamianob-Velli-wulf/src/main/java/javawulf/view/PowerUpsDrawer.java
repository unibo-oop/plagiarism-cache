package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javawulf.controller.PlayerStatus;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.powerup.PowerUp;
import javawulf.model.powerup.PowerUpAttack;
import javawulf.model.powerup.PowerUpDoublePoints;
import javawulf.model.powerup.PowerUpInvincibility;
import javawulf.model.powerup.PowerUpSpeed;

/**
 *  Implementation for drawing all Power Ups.
 */
public final class PowerUpsDrawer extends AbstractDrawer {

        private final Map<Class<? extends PowerUp>, BufferedImage> images = new HashMap<>();
        private final List<PowerUp> powerUps;

        /**
         * Builds the power ups passed from the Controller.
         * 
         * @param gamePanel the Game Panel where the power-ups must be drawn
         * @param powerUps a list of all the power-ups to be drawn
         * @param playerStatus the current status of the Player character
         */
        public PowerUpsDrawer(final GamePanel gamePanel, final List<PowerUp> powerUps, final PlayerStatus playerStatus) {
            super(gamePanel, playerStatus);
            this.powerUps = new ArrayList<>(powerUps);
            try {
                images.put(PowerUpAttack.class, this.imageLoader(ImagePath.POWERUP_STRENGTH));
                images.put(PowerUpInvincibility.class, this.imageLoader(ImagePath.POWERUP_INVINCIBILITY));
                images.put(PowerUpDoublePoints.class, this.imageLoader(ImagePath.POWERUP_DOUBLEPOINTS));
                images.put(PowerUpSpeed.class, this.imageLoader(ImagePath.POWERUP_SPEED));
            } catch (IOException exception) {
                Logger.getLogger(PowerUp.class.getName()).fine(exception.getMessage());
            }
        }

        @Override
        public void draw(final Graphics2D graphics) {
            for (final PowerUp powerUp : powerUps) {
                final BufferedImage image = images.get(powerUp.getClass());
                if (!powerUp.getBounds().getCollisionType().equals(CollisionType.INACTIVE)) {
                    this.drawImage(graphics, image, (int) powerUp.getBounds().getCollisionArea().getX(),
                        (int) powerUp.getBounds().getCollisionArea().getY());
                }
            }
        }

}
