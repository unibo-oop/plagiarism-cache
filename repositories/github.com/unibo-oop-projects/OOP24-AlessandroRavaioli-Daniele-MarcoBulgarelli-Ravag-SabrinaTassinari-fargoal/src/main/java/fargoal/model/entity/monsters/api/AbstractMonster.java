package fargoal.model.entity.monsters.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import fargoal.commons.api.Position;
import fargoal.model.entity.commons.api.Health;
import fargoal.model.entity.commons.impl.HealthImpl;
import fargoal.model.manager.api.FloorManager;
import fargoal.model.map.api.FloorMap;
import fargoal.view.api.Renderer;
import fargoal.model.entity.monsters.ai.Ai;

/**
 * A Monster is an abstraction of anything that will try
 * to attack and kill the player.
 * Every object that will attack and kill the player must
 * extend this abstract class.
 */
public abstract class AbstractMonster implements Monster {

    private static final Integer RANDOM_SKILL = 4;
    private static final Integer RANDOM_HP = 6;
    private static final int DAMAGE_NUMBER = 15;
    private boolean isFighting;
    private long timer;
    private final List<Position> lastPositions = new ArrayList<>();
    private Renderer render;
    private MonsterType monsterType;
    private final Health health;
    private Position position;
    private final Integer skill;
    private Integer level;
    private FloorManager floorManager;
    private boolean isVisible;
    private final Random random;

    /**
     * A constructor to set the field that the monster needs
     * to be able to walk in the map, interacts with the items
     * and, in case, attacks the player.
     * 
     * @param position - the starting position
     * @param level - the level of the monster
     * @param floorManager - to get infos about the other entities/items
     */
    @SuppressFBWarnings(
        value = {"DMI_RANDOM_USED_ONLY_ONCE"},
        justification = "this random is NOT used only once"
    )

    public AbstractMonster(
                final Position position,
                final Integer level, 
                final FloorManager floorManager) {
        this.random = new Random();
        this.isFighting = false;
        this.position = position;
        lastPositions.addFirst(position);
        this.setFloorManager(floorManager);
        this.skill = floorManager.getFloorLevel() + random.nextInt(RANDOM_SKILL);
        this.setLevel(level);
        this.isVisible = true;
        this.health = new HealthImpl(floorManager.getFloorLevel() * 3 / 2 + random.nextInt(RANDOM_HP));
        this.timer = System.currentTimeMillis();
    }

    /** {@inheritDoc} */
    @Override
    public MonsterType getMonsterType() {
        return this.monsterType;
    }

    /** {@inheritDoc} */
    @Override
    public void decreaseHealth(final Integer amount) {
        this.health.decreaseHealth(amount);
    }

    /** {@inheritDoc} */
    @Override
    public void increaseHealth(final Integer amount) {
        this.health.increaseHealth(amount);
    }

    /** {@inheritDoc} */
    @Override
    public Integer getCurrentHealth() {
        return this.health.getCurrentHealth();
    }

    /** {@inheritDoc} */
    @Override
    public Integer getMaxHealth() {
        return this.health.getMaxHealth();
    }

    /** {@inheritDoc} */
    @Override
    public void setHealth(final Integer amount) {
        this.health.setHealth(amount);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isHealthy() {
        return this.health.isHealthy();
    }

    /** {@inheritDoc} */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getSkill() {
        return this.skill;
    }

    /** {@inheritDoc} */
    @Override
    public void receiveDamage() {
        this.isFighting = true;
        final int damage = this.getFloorManager().getPlayer().doDamage(this);
        this.decreaseHealth(damage);
    }

    /**
     * Method that returns the level of the
     * Monster selected.
     * 
     * @return the level of the Monster
     */
    protected Integer getLevel() {
        return this.level;
    }

    /** {@inheritDoc} */
    @Override
    public void setPosition(final Position position) {
        this.position = position;
    }

    /** {@inheritDoc} */
    @Override
    public void move() {
        Ai.move(this, floorManager.getPlayer(), floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Integer attack() {
        final int damage;
        var ratio = DAMAGE_NUMBER - this.getFloorManager().getFloorLevel() + 1;
        if (ratio <= 0 || this.getFloorManager().getPlayer().hasSword()) {
            ratio = 1;
        }
        damage = random.nextInt(this.getFloorManager().getPlayer().getCurrentHealth() / ratio + 1) + 1;
        return damage;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDead() {
        return this.health.getCurrentHealth() <= 0;
    }

    /** {@inheritDoc} */
    @Override
    public final void setVisibilityOn() {
        this.isVisible = true;
    }

    /** {@inheritDoc} */
    @Override
    public final void setVisibilityOff() {
        this.isVisible = false;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isVisible() {
        return this.isVisible;
    }

    /** {@inheritDoc} */
    @Override
    public List<Position> getLastPositions() {
        return List.copyOf(this.lastPositions);
    }

    /** {@inheritDoc} */
    @Override
    public void addFirstPosition(final Position position) {
        this.lastPositions.addFirst(position);
    }

    /** {@inheritDoc} */
    @Override
    public void removeLastPosition() {
        this.lastPositions.removeLast();
    }

    /**
     * Method to set the render field of the Monster.
     * 
     * @param render - the render that the monster will use
     */
    protected final void setRender(final Renderer render) {
        this.render = render;
    }

    /** {@inheritDoc} */
    @Override
    public void render() {
        if (this.isVisible) {
            render.render();
        }
    }

    /**
     * Method to set the level of the Monster.
     * 
     * @param level - the level that the monster has
     */
    private void setLevel(final Integer level) {
        this.level = level;
    }

    /**
     * Set the Floormanager of the Monster.
     * 
     * @param floorManager - the FloorManager of the Floor where the Monster is located
     */
    private void setFloorManager(final FloorManager floorManager) {
        this.floorManager = floorManager;
    }

    /**
     * Set the MonsterType of the Monster selected.
     * 
     * @param monsterType the MonsterType of the Monster
     */
    protected final void setMonsterType(final MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    /**
     * Return a random number between 0 and the Integer
     * before the given value.
     * 
     * @param num - the max number of the random
     * @return a random number
     */
    protected final Integer getRandom(final Integer num) {
        return random.nextInt(num);
    }

    /**
     * Method to set the last time when the monster
     * was created or moved.
     */
    protected void setTimer() {
        this.timer = System.currentTimeMillis();
    }

    /**
     * Method that return the time when the monster
     * last moved or was created.
     * 
     * @return a long indicating the last update
     */
    protected long getTimer() {
        return this.timer;
    }

    /** {@inheritDoc} */
    @Override
    public FloorMap getFloorMap() {
        return this.floorManager.getFloorMap();
    }

    /**
     * Return the Floormanager where the Monster is located.
     * 
     * @return the Floormanager
     */
    protected FloorManager getFloorManager() {
        return this.floorManager;
    }

    /** {@inheritDoc} */
    @Override
    public boolean areNeighbours(final FloorManager floorManager, final Integer amount) {
        return Math.abs(floorManager.getPlayer().getPosition().x() - this.getPosition().x()) <= amount
                && Math.abs(floorManager.getPlayer().getPosition().y() - this.getPosition().y()) <= amount;
    }

    /**
     * The Monster steals from the Player and takes
     * spells or gold from his inventory.
     */
    protected void steal() { }

    /** {@inheritDoc} */
    @Override
    public void setIsFighting(final boolean condition) {
        this.isFighting = condition;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFighting() {
        return this.isFighting;
    }
}
