package controller.entities;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.google.common.eventbus.Subscribe;

import common.EventBusConnection;
import common.events.CollisionEvent;
import enumerators.EntityType;
import model.entities.EntityModel;
import model.physics.Size2D;
import view.entities.EntityView;

/**
 * This Entity can handle multiple type of events. Call the super() constructor
 * to register on the bus.
 */
public abstract class AbstractEntity extends EventBusConnection implements Entity {

    private final EntityModel model;
    private final EntityView view;

    /**
     * @param model the entity model instance
     * @param view the entity view instance 
     */
    public AbstractEntity(final EntityModel model, final EntityView view) {
        super();
        this.model = model;
        this.view = view;
    }

    /**
     * @param collisionEvent is received when a collision occurs.
     */
    @Subscribe
    protected abstract void handleCollisionEvent(CollisionEvent collisionEvent);

    @Override
    public void updateEntity() {
        model.update();
        view.updateView();
    }

    @Override
    public final EntityModel getModel() {
        return model;
    }

    @Override
    public final EntityView getView() {
        return view;
    }

    @Override
    public final EntityType getEntityType() {
        return model.getEntityType();
    }

    @Override
    public final Vec2 getPhysicPosition() {
        return model.getPhysicPosition();
    }

    @Override
    public final Vec2 getViewPosition() {
        return view.getViewPosition();
    }

    @Override
    public final Size2D getDimension() {
        return model.getDimension();
    }

    @Override
    public final Body getBody() {
        return model.getPhysicEntity().getBody();
    }

    @Override
    public final void destroy() {
        this.unregister();
        model.destroyModel();
        view.remove();
    }
}
