package fargoal.model.interactable.stair.impl;

import fargoal.commons.api.Position;
import fargoal.model.interactable.api.Interactable;
import fargoal.model.interactable.stair.api.AbstractStairs;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;
import fargoal.view.api.Renderer;

/**
 * A class that models an object that allows to go further down the dungeon.
 */
public class DownStairs extends AbstractStairs {

    private Renderer renderer;
    /**
     * This is the constructor of the class. It set the position of the stair.
     * @param pos - the position of the stairs.
     * @param renderFactory - a factory to create the renderer of all the elements of the floor.
     */
    public DownStairs(final Position pos, final RenderFactory renderFactory) {
        super(pos);
        setRenderer(renderFactory);
    }

    /** {@inheritDoc} */
    @Override
    public final Interactable interact(final FloorManager floorManager) {
        floorManager.increaseFloorLevel();
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final String getTag() {
        return "Stairs going down";
    }

    /** {@inheritDoc} */
    @Override
    public final void render() {
        this.renderer.render();
    }

    private void setRenderer(final RenderFactory renderFactory) {
        this.renderer = renderFactory.downstairRenderer(this);
    }
}
