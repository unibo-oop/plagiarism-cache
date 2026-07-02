package it.unibo.javajump.model.collision;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.model.entities.platforms.BouncePlatform;
import it.unibo.javajump.model.entities.platforms.BreakablePlatform;
import it.unibo.javajump.model.entities.platforms.Platform;

import java.util.List;

import static it.unibo.javajump.utility.Constants.COIN_SCORE_VALUE;
import static it.unibo.javajump.utility.Constants.NULL_DIRECTION;

/**
 * Implementation of the CollisionManager interface.
 */
public class CollisionManagerImpl implements CollisionManager {
    /**
     * Method that checks for collisions between GameObjects and calls their
     * respective onCollision methods. cycles through all the game objects and
     * checks if they collide, then calls their respective handler methods.
     *
     * @param model the GameModel
     */
    @Override
    public void checkCollisions(final GameModel model) {
        final Character player = model.getPlayer();
        boolean foundPlatformCollision = false;
        final List<GameObject> objects = model.getGameObjects();

        for (int i = 0; i < objects.size(); i++) {
            final GameObject a = objects.get(i);
            for (int j = i + 1; j < objects.size(); j++) {

                final GameObject b = objects.get(j);
                if (isColliding(a, b)) {
                    a.onCollision(b);
                    b.onCollision(a);

                    if (a instanceof Character && b instanceof Coin) {
                        handleCharacterCoinCollision((Coin) b, model);
                    } else if (b instanceof Character && a instanceof Coin) {
                        handleCharacterCoinCollision((Coin) a, model);
                    }

                    if (a instanceof Character && b instanceof Platform) {
                        if (handleCharacterPlatformCollision((Character) a, (Platform) b)) {
                            foundPlatformCollision = true;
                        }
                    } else if (b instanceof Character && a instanceof Platform
                            && handleCharacterPlatformCollision((Character) b, (Platform) a)) {

                            foundPlatformCollision = true;

                    }
                }
            }
        }
        if (!foundPlatformCollision) {
            player.goInAir();
        }
    }

    /**
     * Basic Bounding-Box check (AABB), that checks if two GameObjects are
     * colliding.
     *
     * @param a the first GameObject
     * @param b the second GameObject
     * @return true if the two GameObjects are colliding, false otherwise
     */
    private boolean isColliding(final GameObject a, final GameObject b) {
        return a.getX() < b.getX() + b.getWidth()
                && a.getX() + a.getWidth() > b.getX()
                && a.getY() < b.getY() + b.getHeight()
                && a.getY() + a.getHeight() > b.getY();
    }

    /**
     * Private method that specifies how to handle character-coin collisions.
     *
     *
     * @param coin      the coinImpl, GameObject that the player can collect
     * @param model     the GameModel which contains player & coinImpl
     */
    private void handleCharacterCoinCollision(final Coin coin, final GameModel model) {
        if (coin.getState() == CoinState.IDLE) {
            coin.collect();
            model.addPointsToScore(COIN_SCORE_VALUE);
        }
    }

    /**
     * Private method that specifies how to handle character-platform
     * collisions: - The player jumps on the platform, and its velocity is
     * changed based on the platform type; - The platform is handled based on
     * its type.
     *
     * @param player   the Character, GameObject controlled by the player
     * @param platform the Platform, GameObject that the player can jump on
     * @return true if the player jumps on the platform, false otherwise.
     */
    private boolean handleCharacterPlatformCollision(final Character player, final Platform platform) {
        if (player.getVelocityY() > NULL_DIRECTION) {
            final float playerOldBottom = player.getOldY() + player.getHeight();
            final float platformTop = platform.getY();

            if (playerOldBottom <= platformTop) {
                float jumpForce = player.getJumpForce();

                if (platform instanceof BouncePlatform bp) {
                    jumpForce *= bp.getBounceFactor();
                }

                player.setVelocityY(-jumpForce);
                player.setY(platformTop - player.getHeight());
                player.landOnPlatform();
                platform.triggerTouched();

                if (platform instanceof BreakablePlatform breakablePlatform) {
                    breakablePlatform.breakPlatform();
                }
            }
            return true;
        }
        return false;
    }
}
