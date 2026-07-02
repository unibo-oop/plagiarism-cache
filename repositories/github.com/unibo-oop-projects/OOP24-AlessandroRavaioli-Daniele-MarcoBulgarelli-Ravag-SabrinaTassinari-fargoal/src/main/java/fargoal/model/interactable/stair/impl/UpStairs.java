package fargoal.model.interactable.stair.impl;

import fargoal.commons.api.Position;
import fargoal.model.interactable.api.Interactable;
import fargoal.model.interactable.stair.api.AbstractStairs;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;
import fargoal.view.api.Renderer;

/**
 * A class that implements an object that allows to go back up the dungeon.
 */
public class UpStairs extends AbstractStairs {

    private Renderer renderer;

    /**
     * This is the constructor of the class. It set the position of the stair.
     * @param pos - the position of the stairs.
     * @param renderFactory - a factory to create the renderer of all the elements of the floor.
     */
    public UpStairs(final Position pos, final RenderFactory renderFactory) {
        super(pos);
        setRenderer(renderFactory);
    }

    /** {@inheritDoc} */
    @Override
    public Interactable interact(final FloorManager floorManager) {
        floorManager.decreaseFloorLevel();
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public String getTag() {
        return "Stairs going up";
    }

    /** {@inheritDoc} */
    @Override
    public void render() {
        this.renderer.render();
    }

    private void setRenderer(final RenderFactory rf) {
        this.renderer = rf.upstairRenderer(this);
    }
}
