package controller.entities;

import common.events.CoinEvent;
import common.events.CollisionEvent;
import model.components.CoinValueImpl;
import model.entities.EntityModel;
import view.entities.EntityView;

/**
 * A new entity of Coin type.
 */
public class Coin extends AbstractEntity {

    /**
     * Creates a new coin and adds it to the view.
     * 
     * @param model the coin model
     * @param view  the coin view
     */
    public Coin(final EntityModel model, final EntityView view) {
        super(model, view);
    }

    @Override
    protected void handleCollisionEvent(final CollisionEvent collisionEvent) {

    }

    @Override
    public final void updateEntity() {
        super.updateEntity();
        // if dead send an event to add the coin value to the user score
        if (!this.getModel().isAlive() && this.getModel().contain(CoinValueImpl.class)) {
            final int value = this.getModel().getComponent(CoinValueImpl.class).getValue();
            this.getBus().post(new CoinEvent(value));
        }
    }
}
