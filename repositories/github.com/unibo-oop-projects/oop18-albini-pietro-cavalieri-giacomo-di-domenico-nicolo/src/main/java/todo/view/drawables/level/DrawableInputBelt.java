package todo.view.drawables.level;

import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.drawables.screens.Textures;
import todo.view.entities.level.InputBelt;

/**
 * This class represents a drawable input belt.
 */
public final class DrawableInputBelt extends BaseDrawableBelt<InputBelt> {
    /**
     * Create a drawable input belt.
     *
     * @param entity is the input belt entity associated with this drawable
     */
    public DrawableInputBelt(final InputBelt entity) {
        super(entity,
                ResolutionManagerImpl.getInstance()
                                     .getCurrentAspectRatio()
                                     .getTextures()
                                     .getTexture(Textures.BELT_INPUT)
                                     .getRegion());
    }
}
