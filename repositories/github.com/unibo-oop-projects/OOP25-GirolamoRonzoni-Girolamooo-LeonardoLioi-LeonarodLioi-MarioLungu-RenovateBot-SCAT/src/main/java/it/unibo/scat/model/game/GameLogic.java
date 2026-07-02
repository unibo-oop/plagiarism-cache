package it.unibo.scat.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Constants;
import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.GameResult;
import it.unibo.scat.model.api.EntityFactory;
import it.unibo.scat.model.game.entity.AbstractEntity;
import it.unibo.scat.model.game.entity.Invader;
import it.unibo.scat.model.game.entity.Player;
import it.unibo.scat.model.game.entity.Shot;

/**
 * Handles the game rules and updates.
 * Move entities, checks collisions, manage shots, and determinate when the,
 * game ends.
 * 
 */
@SuppressFBWarnings({ "EI2", "DMI_RANDOM_USED_ONLY_ONCE" })
public class GameLogic {
    private final GameWorld gameWorld;
    private final EntityFactory entityFactory;
    private final DifficultyManager difficultyManager;

    /**
     * GameLogic constructor.
     *
     * @param gWorld        the game world.
     * @param entityFactory the entity factory.
     */
    public GameLogic(final GameWorld gWorld, final EntityFactory entityFactory) {
        this.gameWorld = gWorld;
        this.entityFactory = entityFactory;
        this.difficultyManager = new DifficultyManager();
    }

    /**
     * Checks if every shot (player/invader shot) has hit some other entity.
     * The entities that got hit get added to a list.
     * 
     * @return the list of entities and shots engaged in every hit.
     */
    public CollisionReport checkCollisions() {
        final List<AbstractEntity> entitiesThatGotShot = new ArrayList<>();
        final List<Shot> shotList = List.copyOf(gameWorld.getShots());
        final List<AbstractEntity> entityList = List.copyOf(gameWorld.getEntities());

        for (final Shot shot : shotList) {
            for (final AbstractEntity entity : entityList) {
                final boolean isDead = !entity.isAlive();
                final boolean isSameEntity = entity.equals(shot);
                final boolean isCollision = areColliding(shot, entity);
                final boolean areOnSameTeam = areOnSameTeam(shot, entity);

                if (isSameEntity || !isCollision || areOnSameTeam || isDead) {
                    continue;
                }
                entitiesThatGotShot.add(shot);
                entitiesThatGotShot.add(entity);
            }
        }
        return new CollisionReport(entitiesThatGotShot);
    }

    /**
     * Resets the invaders.
     */
    public void resetInvaders() {
        gameWorld.getInvaders().forEach(Invader::reset);
    }

    /**
     * This functions returns true if two entities (the first argument is assumed to
     * be always a shot) are on the same team.
     * 
     * @param shot   the shot that can either come from the player or the invaders.
     * @param entity the second entity envolved in the "collision".
     * @return whether the shot was a "friendly fire" one.
     */
    private boolean areOnSameTeam(final Shot shot, final AbstractEntity entity) {
        if (entity instanceof Shot) {
            return isPlayerShot(shot) && isPlayerShot((Shot) entity)
                    || isInvaderShot(shot) && isInvaderShot((Shot) entity);
        } else {
            return isPlayerShot(shot) && entity instanceof Player
                    || isInvaderShot(shot) && entity instanceof Invader;
        }
    }

    /**
     * Checks if the shot is a player's shot.
     * 
     * @param shot shot that needs to be checked.
     * @return true if the shot is a player's shot, false otherwise.
     */
    private boolean isPlayerShot(final Shot shot) {
        return shot.getDirection() == Direction.UP;
    }

    /**
     * Checks if the shot is a invader's shot.
     * 
     * @param shot shot that needs to be checked.
     * @return true if the shot is a invader's shot, false otherwise.
     */

    private boolean isInvaderShot(final Shot shot) {
        return shot.getDirection() == Direction.DOWN;
    }

    /**
     * Checks if two entities (a shot and another one) are colliding.
     * 
     * @param shot the shot.
     * @param e    the entity that needs to be checked if it was hit or not.
     * @return true if there is a collision, false otherwise.
     */
    private boolean areColliding(final AbstractEntity shot, final AbstractEntity e) {
        return checkX(shot, e) && checkY(shot, e);
    }

    /**
     * Checks if the horizontal bounds (X-axis) of the shot and the target entity
     * overlap.
     * 
     * @param shot the shot.
     * @param e    the entity.
     * @return true if the two entities intersect horizontally.
     */
    private boolean checkX(final AbstractEntity shot, final AbstractEntity e) {
        return shot.getPosition().getX() < e.getPosition().getX() + e.getWidth()
                && e.getPosition().getX() < shot.getPosition().getX() + shot.getWidth();
    }

    /**
     * Checks if the vertical bounds (Y-axis) of the shot and the target entity
     * overlap.
     * 
     * @param shot the shot.
     * @param e    the entity.
     * @return true if the two entities intersect vertically.
     */
    private boolean checkY(final AbstractEntity shot, final AbstractEntity e) {
        return shot.getPosition().getY() < e.getHeight() + e.getPosition().getY()
                && e.getPosition().getY() < shot.getHeight() + shot.getPosition().getY();
    }

    /**
     * Calculates and returns the amount of points that were gained.
     * 
     * @param cr the collision report.
     * @return the points gained.
     */
    public int handleCollisionReport(final CollisionReport cr) {
        int points = 0;

        for (final AbstractEntity entity : cr.getEntities()) {
            if (entity.isAlive()) {
                points += entity.onHit();
            }
        }
        return points;
    }

    /**
     * Creates the new shot onject, given the player's coordinates.
     */
    public void addPlayerShot() {
        if (!canPlayerShoot()) {
            return;
        }

        final Player player = gameWorld.getPlayer();
        final int shotX = player.getPosition().getX() + (player.getWidth() / 2);
        final int shotY = player.getPosition().getY() - Constants.SHOT_HEIGHT + 1;

        final Shot newShot = (Shot) entityFactory.createEntity(EntityType.PLAYER_SHOT, shotX, shotY);

        gameWorld.addEntity(newShot);
        Player.setLastShotTime(System.currentTimeMillis());
    }

    /**
     * Reset all entities in the game world to their initial state.
     * Also removes every shot currently present in the world.
     */
    public void resetEntities() {
        for (final AbstractEntity e : List.copyOf(gameWorld.getEntities())) {
            e.reset();
        }
    }

    /**
     * Checks wheter the game has ended and returns the corresponding result.
     * The game ends if: the player is dead, all invaders are dead, or at least one
     * alive invader reaches the bottom limit.
     * 
     * @return the current {@link GameResult}
     */
    public GameResult checkGameEnd() {
        if (invadersReachedBottom(gameWorld.getInvaders()) || isPlayerDead(gameWorld.getPlayer())) {
            return GameResult.INVADERS_WON;
        }
        if (!areInvadersAlive(gameWorld.getInvaders())) {
            return GameResult.PLAYER_WON;
        }
        return GameResult.PLAYING;

    }

    /**
     * Checks wheter the player is dead.
     * 
     * @param player the player entity
     * @return true if the player is not alive, false otherwise
     * 
     */
    private boolean isPlayerDead(final Player player) {
        return !player.isAlive();
    }

    /**
     * Checks wheter there is at least one alive invader.
     * 
     * @param invaders the list of invaders to check
     * @return true if at least one invader is alive, false otherwise
     * 
     */
    public boolean areInvadersAlive(final List<Invader> invaders) {
        for (final Invader x : invaders) {
            if (x.isAlive()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks wheter at least one alive invader has reached the bottom limit.
     * 
     * @param invader the list of invaders to check
     * @return true if an alive invader reached the bottom limit, false otherwise
     * 
     */
    private boolean invadersReachedBottom(final List<Invader> invader) {
        for (final Invader x : invader) {
            if (x.isAlive() && x.getPosition().getY() + x.getHeight() >= Constants.INVADER_BOTTOM_LIMIT) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all shots from the game world ( from both the entities list and the
     * shot list).
     * 
     */
    public void removeAllShots() {
        gameWorld.getEntities().removeIf(e -> e instanceof Shot);
        gameWorld.getShots().clear();
    }

    /**
     * Moves all movable entities in the game world by updating their positions.
     * 
     */
    public void moveInvaders() {
        for (final Invader invader : gameWorld.getInvaders()) {
            invader.move();
        }
    }

    /**
     * Updates the position of all active shots and removes the ones that are no
     * longer alive from the game world.
     */
    public void moveShots() {
        final List<Shot> snapshot = List.copyOf(gameWorld.getShots());
        for (final Shot shot : snapshot) {
            shot.move();
            if (!shot.isAlive()) {
                gameWorld.removeEntity(shot);
            }
        }
    }

    /**
     * Checks if enough time has passed since the last invader shot.
     * 
     * @return true if invaders can shoot, false otherwise
     * 
     */
    private boolean canInvadersShoot() {
        final long currTime = System.currentTimeMillis();

        final int aliveInvaders = (int) gameWorld.getInvaders().stream()
                .filter(Invader::isAlive)
                .count();

        return (currTime - Invader.getLastShotTime()) >= difficultyManager
                .getInvadersShootingCooldown(aliveInvaders);
    }

    /**
     * Checks if the shooting conditions are met and, if so, generates new enemy
     * projectiles
     * and updates the shooting cooldown.
     */
    public void handleInvadersShooting() {
        if (!canInvadersShoot()) {
            return;
        }

        for (int i = 0; i < 1; i++) {
            addInvaderShot();
        }

        updateLastInvadersShotTime();
    }

    /**
     * Generates a new shot fired by a random alive invader and adds it to the game
     * world.
     */
    public void addInvaderShot() {
        final Invader invader = getRandomInvader();

        if (invader == null) {
            return;
        }

        final int shotX = invader.getPosition().getX();
        final int shotY = invader.getPosition().getY() + 2;

        final Shot newShot = (Shot) entityFactory.createEntity(EntityType.INVADER_SHOT, shotX, shotY);
        gameWorld.addEntity(newShot);
    }

    /**
     * Returns a random alive invader from the game world.
     * 
     * @return a random alive invader, or null if no invaders are alive.
     */
    private Invader getRandomInvader() {
        final List<Invader> aliveInvaders = gameWorld.getInvaders().stream()
                .filter(Invader::isAlive)
                .toList();

        if (aliveInvaders.isEmpty()) {
            return null;
        }

        return aliveInvaders.get(new Random().nextInt(aliveInvaders.size()));
    }

    /**
     * Updates the last shot time of invaders to the current time.
     */
    public void updateLastInvadersShotTime() {
        Invader.setLastShotTime(System.currentTimeMillis());
    }

    /**
     * Updates the last shot time of the player.
     */
    public void updateLastPlayerShotTime() {
        Player.setLastShotTime(System.currentTimeMillis());
    }

    /**
     * Removes every dead shot.
     * A shot is dead if:
     * - has no health (isAlive is false).
     * - or if it is out of border (isOutOfBorder is true).
     */
    public void removeDeadShots() {
        final List<Shot> snapshot = List.copyOf(gameWorld.getShots());
        snapshot.stream()
                .filter(shot -> !shot.isAlive() || isOutOfBorder(shot))
                .forEach(gameWorld::removeEntity);
    }

    /**
     * Handles the bonus invader lifecycle.
     * If the bonus invader is alive, checks whether it is out of bounds and removes
     * it if necessary.
     * If no bonus invader is present, spawns a new one with a 5%
     * probability.
     * 
     * @param bonusInvaderAccMs the accumulated time in milliseconds used to
     *                          regulate the bonus invader's movement frequency.
     */
    public void handleBonusInvader(final int bonusInvaderAccMs) {
        final boolean isAlive = gameWorld.isBonusInvaderAlive();

        if (isAlive) {
            if (bonusInvaderAccMs >= Constants.BONUSINVADER_STEP_MS) {
                if (isOutOfBorder(gameWorld.getBonusInvader())) {
                    gameWorld.removeEntity(gameWorld.getBonusInvader());
                } else {
                    gameWorld.getBonusInvader().move();
                }
            }
            return;
        }

        final boolean respawn = new Random().nextInt(20) == 0;
        if (respawn) {
            gameWorld.spawnBonusInvader();
        }
    }

    /**
     * @param invadersAccMs the accumulated time in milliseconds used to control the
     *                      invaders' movement frequency.
     */
    public void handleInvadersMovement(final int invadersAccMs) {
        if (invadersAccMs >= difficultyManager.getInvadersStepMs()) {
            moveInvaders();

            if (gameWorld.shouldInvadersChangeDirection()) {
                gameWorld.changeInvadersDirection();
            }
        }
    }

    /**
     * @param shotAccMs the accumulated time in milliseconds used to regulate
     *                  projectile movement.
     */
    public void handleShotsMovement(final int shotAccMs) {
        if (shotAccMs >= Constants.SHOT_STEP_MS) {
            moveShots();
        }
    }

    /**
     * Checks if enough time has passed since the last shot of the player.
     * 
     * @return true if the player can shoot, false otherwise.
     */
    public boolean canPlayerShoot() {
        if (!gameWorld.getPlayer().isAlive()) {
            return false;
        }

        final long actualTime = System.currentTimeMillis();
        return actualTime - Player.getLastShotTime() >= Constants.PLAYER_SHOOTING_COOLDOWN;
    }

    /**
     * Checks whether the player can move in the given direction, considering world
     * borders.
     * 
     * @param direction the desired movement direction
     * @return true if the player can move in that direction, false otherwisex
     */
    public boolean canPlayerMove(final Direction direction) {
        if (!gameWorld.getPlayer().isAlive()) {
            return false;
        }

        return direction == Direction.RIGHT && canPlayerMoveRight()
                || direction == Direction.LEFT && canPlayerMoveLeft();

    }

    /**
     * Checks whether the player can move right without crossing the right border.
     * 
     * @return true if the player can move right, false otherwise
     */
    private boolean canPlayerMoveRight() {
        return (gameWorld.getPlayer().getPosition().getX() + gameWorld.getPlayer().getWidth()
                + 1) <= Constants.BORDER_RIGHT;
    }

    /**
     * Checks whether the player can move left without crossing the left border.
     * 
     * @return true if the player can move left, false otherwise
     */
    private boolean canPlayerMoveLeft() {
        return gameWorld.getPlayer().getPosition().getX() >= Constants.BORDER_LEFT;
    }

    /**
     * Checks if the entity is completely out of border.
     * 
     * @param entity the entity.
     * @return true if the entity is out of border, false otherwise.
     */
    public boolean isOutOfBorder(final AbstractEntity entity) {
        return isOverTopBorder(entity) || isOverBottomBorder(entity) || isOverLeftBorder(entity)
                || isOverRightBorder(entity);
    }

    /**
     * Checks if the entity is completely above the top border.
     * 
     * @param entity the entity.
     * @return true if the entity is fully out of bounds (top).
     */
    private boolean isOverTopBorder(final AbstractEntity entity) {
        return entity.getPosition().getY() + entity.getHeight() < Constants.BORDER_UP;
    }

    /**
     * Checks if the entity is completely below the bottom border.
     * 
     * @param entity the entity.
     * @return true if the entity is fully out of bounds (bottom).
     */
    private boolean isOverBottomBorder(final AbstractEntity entity) {
        return entity.getPosition().getY() > Constants.BORDER_BOTTOM;
    }

    /**
     * Checks if the entity is completely to the left of the game area.
     * 
     * @param entity the entity.
     * @return true if the entity is fully out of bounds (left).
     */
    private boolean isOverLeftBorder(final AbstractEntity entity) {
        return entity.getPosition().getX() + entity.getWidth() < Constants.BORDER_LEFT;
    }

    /**
     * Checks if the entity is completely to the right of the game area.
     * 
     * @param entity the entity.
     * @return true if the entity is fully out of bounds (right).
     */
    private boolean isOverRightBorder(final AbstractEntity entity) {
        return entity.getPosition().getX() > Constants.BORDER_RIGHT;
    }

    /**
     * @return the {@link DifficultyManager} instance responsible for handling game
     *         difficulty and progression.
     */
    public DifficultyManager getDifficultyManager() {
        return difficultyManager;
    }
}
