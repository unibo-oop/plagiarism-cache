package it.unibo.aknightstale.controllers.entity;

import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.utils.BordersImpl;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;

public class ObstacleController<M extends Character, V extends AnimatedEntityView> extends EntityControllerImpl<M, V> {

    public ObstacleController(final M model, final V view) {
        super(model, view);
    }


    /**
     * Adapt position to screen size.
     *
     * @param traslX the trasl x.
     * @param traslY the trasl y.
     * @param width  the new width.
     * @param height the new height.
     */
    public void adaptPositionToScreenSize(final double traslX, final double traslY, final double width, final double height) {
        super.adaptPositionToScreenSize(traslX, traslY);
        getModel().setBorders(new BordersImpl(getModel().getPosition().getX(), getModel().getPosition().getY(), width, height));
    }
}
