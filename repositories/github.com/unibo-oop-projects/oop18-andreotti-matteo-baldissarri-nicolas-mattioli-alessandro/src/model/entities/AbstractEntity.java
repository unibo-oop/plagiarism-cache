package model.entities;

/**
 * This class provides a skeletal implementation of the {@link Entity}
 * interface, to minimize the effort required to implement this interface.
 */
public abstract class AbstractEntity implements Entity {

    private final Environment environment;

    /**
     * @param environment The entity's environment
     */
    protected AbstractEntity(final Environment environment) {
        this.environment = environment;
    }

    @Override
    public abstract void moveUp();

    @Override
    public abstract void moveDown();

    @Override
    public abstract void moveLeft();

    @Override
    public abstract void moveRight();

    @Override
    public abstract double getHeight();

    @Override
    public abstract double getWidth();

    /**
     * @return The entity's position
     */
    @Override
    public Position getPosition() {
        return this.environment.getPosition();
    }

    /**
     * @return The entity's environment
     */
    @Override
    public Environment getEnvironment() {
        return this.environment;
    }

}
