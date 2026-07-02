package controller.entities;

import com.google.common.eventbus.Subscribe;

import common.events.CollisionEvent;
import common.events.JumpEvent;
import common.events.MovementEvent;
import model.components.ComandableMovement;
import model.entities.EntityModel;
import view.entities.EntityView;

public class Player extends AbstractEntity implements Comandable, Jumper {

    public Player(final EntityModel model, final EntityView view) {
        super(model, view);
    }

    @Override
    protected void handleCollisionEvent(final CollisionEvent event) {

    }

    @Subscribe
    public final void handleInputEvent(final MovementEvent event) {
        this.getModel().getComponent(ComandableMovement.class).move(event.getMovementValue());
    }

    @Subscribe
    public final void handleJumpEvent(final JumpEvent event) {

    }

}