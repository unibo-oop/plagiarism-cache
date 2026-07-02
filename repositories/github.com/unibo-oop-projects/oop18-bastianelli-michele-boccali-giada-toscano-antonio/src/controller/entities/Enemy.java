package controller.entities;

import common.events.CollisionEvent;
import model.entities.EntityModel;
import view.entities.EntityView;

public class Enemy extends AbstractEntity {

    public Enemy(final EntityModel model, final EntityView view) {
        super(model, view);
    }

    @Override
    protected void handleCollisionEvent(final CollisionEvent collisionEvent) {
        // TODO Auto-generated method stub
    }
}
