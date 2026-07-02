package input;

import model.AbstractEntity;
import physics.Direction;

/**
 * This is an Abstract Input Component's implementation.
 *  It summarizes the behavior of all Input Component, generating a new command and adding it to the command queue.
 */
public abstract class AbstractInputComponent implements InputComponent {

    private final AbstractEntity entity;

    /**
     * Builds a generic Input Component.
     * @param entity : entity that receives the new generated command.
     */
    public AbstractInputComponent(final AbstractEntity entity) {
        this.entity = entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCommand(final Command newCommand) {
        entity.getCommandQueue().add(newCommand);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void createCommand(final Direction direction, final double distance) {
        switch (direction) {
            case UP:
                this.entity.getCommandQueue().add(() -> {
                    entity.setSpaceX(0);
                    entity.setSpaceY(-distance);
                });
            case DOWN:
                this.entity.getCommandQueue().add(() -> {
                    entity.setSpaceX(0);
                    entity.setSpaceY(distance);
                });
            case LEFT:
                this.entity.getCommandQueue().add(() -> {
                    entity.setSpaceX(-distance);
                    entity.setSpaceY(0);
                });
            case RIGHT:
                this.entity.getCommandQueue().add(() -> {
                    entity.setSpaceX(+distance);
                    entity.setSpaceY(0);
                });
            default:
                this.entity.getCommandQueue().add(() -> {
                    entity.setSpaceX(0);
                    entity.setSpaceY(0);
                });
        }
    }

    /**
     * Entity getter.
     * @return the entity
     */
    public AbstractEntity getEntity() {
        return this.entity;
    }
}
