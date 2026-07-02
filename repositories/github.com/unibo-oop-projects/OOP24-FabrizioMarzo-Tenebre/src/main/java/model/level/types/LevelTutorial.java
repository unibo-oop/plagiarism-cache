package model.level.types;

import java.util.ArrayList;
import java.util.List;

import model.armory.munition.Munition;
import model.bounding_box.BoundingBox;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import model.level.manager.LevelManager;
import model.level.manager.LevelManagerBase;
import model.physics.physics_level.PhysicsLevelComponent;

/**
 * Concrete implementation of a tutorial-level in the domain model.
 * <p>
 * {@code LevelTutorial} defines a bounded space with entities such as
 * a survivor, zombies, and munitions. It manages state updates via physics
 * and logic components.
 * </p>
 */
public class LevelTutorial implements Level {

    private double lvlWidth;
    private double lvlHeight;
    private BoundingBox bbox;
    private PhysicsLevelComponent physicLvComp;
    private LevelManager levelManager;
    private boolean isLevelCompleted;
    private Survivor surLv;
    private List<Munition> activeMunitions = new ArrayList<>();
    private List<Zombie> listZombie;

    /**
     * Constructs a new {@code LevelTutorial} instance.
     *
     * @param lvlWidth   the width of the level space
     * @param lvlHeight  the height of the level space
     * @param bbox       the bounding box that defines level boundaries
     * @param physcLevel the physics component used for updating entities
     */
    public LevelTutorial(final double lvlWidth, final double lvlHeight,
            final BoundingBox bbox,
            final PhysicsLevelComponent physcLevel) {
        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;
        this.bbox = bbox;
        this.physicLvComp = physcLevel;
        this.isLevelCompleted = false;
        this.listZombie = new ArrayList<>();
        this.levelManager = new LevelManagerBase(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLevelState(final int dt) {
        this.physicLvComp.updateLevel(this, dt);
        this.levelManager.update(dt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLevelWidth() {
        return this.lvlWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLevelHeight() {
        return this.lvlHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoundingBox getLevelBBox() {
        return this.bbox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Survivor getSurvivorOnLevel() {
        return this.surLv;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Zombie> getZombieOnLevel() {
        return this.listZombie;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Munition> getProjectilesOnLevel() {
        return this.activeMunitions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSurvivorOnLevel(final Survivor sur) {
        this.surLv = sur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLevelCompleted() {
        return this.isLevelCompleted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLevelCompleted(final boolean completed) {
        this.isLevelCompleted = completed;

    }
}
