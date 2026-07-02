package factories;

import org.jbox2d.common.Vec2;

import controller.entities.Entity;
import enumerators.SpecificType;
import model.entities.EntityModel;
import view.entities.EntityView;
import view.entities.EntityViewImpl;

/**
 * Abstract base factory.
 */
abstract class GenericFactory {

    protected abstract Entity createEntity(SpecificType type, Vec2 position);

    protected abstract EntityModel createModel(SpecificType type, Vec2 position);

    /**
     * @param eModel the {@link EntityModel} of the entity to create
     * @return the entity view
     */
    protected EntityView createView(final EntityModel eModel) {
        final EntityView view = new EntityViewImpl(eModel);
        view.fitViewToDimension(false);
        return view;
    }

    /**
     * Throw an exception for a bad entity character.
     * 
     * @param msg
     */
    protected void throwErrorBadCharacter(final String msg) {
        throw new IllegalArgumentException("Character not found: " + msg);
    }

    /**
     * Throw an exception for a bad Class type.
     * 
     * @param msg
     */
    protected <T> void throwErrorBadClass(final Class<T> type) {
        throw new IllegalArgumentException("Class type not valid: " + type.toString());
    }
}
