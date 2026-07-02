package models;

/**
 * EnemyImpl is a class that implements Enemy and its contracts.
 * It generates Enemy entities, each initialized with a position, set amount of health and an Ai.
 */
public class EnemyImpl implements Enemy {

    private Point2D position;
    private int health;
    private static final int MAXHEALTH = 1;
    private AiEnemy aiEnemy;

    /**
     * This is the constructor of EnemyImpl
     * 
     * @param position the initial position of enemy
     * @param health the health of the enemy
     * @param aiEnemy the Ai of the enemy
     */
    public EnemyImpl(final Point2D position, final int health, final AiEnemy aiEnemy) {
        this.position = position;
        this.health = health;
        this.aiEnemy = aiEnemy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHealth(final int value) {
        if (this.health < MAXHEALTH) {
            this.health = this.health + value;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point2D position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AiEnemy getAi() {
        return this.aiEnemy;
    }

}
