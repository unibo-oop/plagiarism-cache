package model.physics.physics_level;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import model.ai.behavior.AINPCBehavior;
import model.ai.behavior.FactoryAINPCBehavior;
import model.armory.munition.Munition;
import model.bounding_box.BoundingBox;
import model.bounding_box.RectBoundingBox;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import model.level.types.Level;

/**
 * Physics component that updates the game level's physics state.
 * <p>
 * This implementation handles the updating of munitions, zombies' AI and
 * movement,
 * survivor movement, collision detection between projectiles and zombies,
 * and ensures the survivor stays within the level bounds.
 * </p>
 */
public class PhysicsLevelTutComponent implements PhysicsLevelComponent {

    private static final Pair<Double, Double> ZERO_VELOCITY = Pair.of(0.0, 0.0);

    FactoryAINPCBehavior factAINPC = new FactoryAINPCBehavior();
    AINPCBehavior<Survivor, Zombie> baseAIZombie = factAINPC.createBaseNPCBehavior();

    /**
     * Updates the physics state of the level by advancing the positions of
     * entities,
     * running AI behaviors, checking collisions, and enforcing level boundaries.
     *
     * @param lv the Level to update
     * @param dt the time delta in milliseconds since the last update
     */
    @Override
    public void updateLevel(final Level lv, final int dt) {

        this.updateMunitions(dt, lv);
        this.checkCollisionsProjectilesZombies(lv);

        lv.getSurvivorOnLevel().updatePhysics(dt);
        lv.getZombieOnLevel().stream()
                .forEach(zombie -> {
                    this.baseAIZombie.updateAINPC(lv.getSurvivorOnLevel(), zombie, lv.getZombieOnLevel());
                    zombie.updatePhysics(dt);
                });
        this.checkCollisionZombiesSurvivor(lv);

        if (isOutsideLevelBounds(lv)) {
            resetSurvivorToValidPosition(lv);
        }
    }

    /**
     * Checks if the survivor is outside the bounds of the level.
     *
     * @param lv the Level containing the survivor and bounds
     * @return true if survivor is outside level bounds, false otherwise
     */
    private boolean isOutsideLevelBounds(final Level lv) {

        Pair<Double, Double> checkUL = Pair.of(
                lv.getSurvivorOnLevel().getBBox().getULcorner().getLeft() + lv.getSurvivorOnLevel().getWidth(),
                lv.getSurvivorOnLevel().getBBox().getULcorner().getRight() - lv.getSurvivorOnLevel().getHeight());

        Pair<Double, Double> checkBR = Pair.of(
                lv.getSurvivorOnLevel().getBBox().getBRcorner().getLeft() - lv.getSurvivorOnLevel().getWidth(),
                lv.getSurvivorOnLevel().getBBox().getBRcorner().getRight() + lv.getSurvivorOnLevel().getHeight());

        var bbox = new RectBoundingBox(checkUL, checkBR);

        return !lv.getLevelBBox().isColliding(bbox);
    }

    /**
     * Resets the survivor's position to a valid location inside the level bounds,
     * and sets velocity to zero.
     *
     * @param lv the Level containing the survivor
     */
    private void resetSurvivorToValidPosition(final Level lv) {
        System.out.println("Final level Box");

        double posX = lv.getSurvivorOnLevel().getCurrentPos().getLeft();
        double posY = lv.getSurvivorOnLevel().getCurrentPos().getRight();

        double levelWidth = lv.getLevelWidth();
        double levelHeight = lv.getLevelHeight();

        int survivorWidth = lv.getSurvivorOnLevel().getWidth();
        int survivorHeight = lv.getSurvivorOnLevel().getHeight();

        posX = Math.max(0, Math.min(posX, levelWidth - survivorWidth));
        posY = Math.max(0, Math.min(posY, levelHeight - survivorHeight));

        lv.getSurvivorOnLevel().setPosition(Pair.of(posX, posY));
        lv.getSurvivorOnLevel().setVelocity(ZERO_VELOCITY);
    }

    /**
     * Checks if a munition is outside the bounds of the level.
     *
     * @param munition the Munition to check
     * @param lv       the Level containing the bounding box
     * @return true if munition is outside the level, false otherwise
     */
    private Boolean checkIfMunitionIsOut(final Munition munition, final Level lv) {
        BoundingBox levelBox = lv.getLevelBBox();
        BoundingBox munBox = munition.getBBox();

        boolean isInside = levelBox.isColliding(munBox);

        return !isInside;
    }

    /**
     * Updates the positions of all projectiles on the level and removes those
     * outside bounds.
     *
     * @param dt the elapsed time in milliseconds
     * @param lv the Level containing the projectiles
     */
    private void updateMunitions(final int dt, final Level lv) {
        lv.getProjectilesOnLevel().stream()
                .forEach(munition -> munition.moveShoot(dt));

        lv.getProjectilesOnLevel().removeIf(munition -> this.checkIfMunitionIsOut(munition, lv));
    }

    /**
     * Checks collisions between projectiles and zombies.
     * Applies damage to zombies and removes projectiles that collide.
     * Removes zombies that are dead.
     *
     * @param lv the Level containing zombies and projectiles
     */
    private void checkCollisionsProjectilesZombies(final Level lv) {

        var zombies = lv.getZombieOnLevel();
        var projectiles = lv.getProjectilesOnLevel();

        Set<Munition> toRemoveProjectiles = new HashSet<>();

        projectiles.stream().forEach(munition -> zombies.stream()
                .filter(zob -> munition.getBBox().isColliding(zob.getBBox()))
                .forEach(zob -> {
                    zob.damageSuffer(munition.getDamage());
                    toRemoveProjectiles.add(munition);
                }));

        projectiles.removeIf(toRemoveProjectiles::contains);

        zombies.removeIf(Zombie::isZombieDead);
    }

    /**
     * Checks for collisions between all zombies in the level and the survivor.
     * <p>
     * If a zombie collides with the survivor, the survivor takes damage
     * equal to the value returned by the zombie's {@code attack()} method.
     * </p>
     * <p>
     * This method should be called on every physics update of the level
     * to properly handle damage to the survivor when in contact with zombies.
     * </p>
     *
     * @param lv the current level containing the survivor and zombies
     */
    private void checkCollisionZombiesSurvivor(final Level lv) {
        var survivor = lv.getSurvivorOnLevel();
        var zombies = lv.getZombieOnLevel();

        for (Zombie zombie : zombies) {
            if (zombie.getBBox().isColliding(survivor.getBBox())) {
                int damage = zombie.attack();
                survivor.damageSuffer(damage);
            }
        }
    }

}
