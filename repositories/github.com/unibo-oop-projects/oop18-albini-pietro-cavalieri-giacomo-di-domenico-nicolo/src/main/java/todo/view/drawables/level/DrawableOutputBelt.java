package todo.view.drawables.level;

import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.drawables.screens.Textures;
import todo.view.entities.level.OutputBelt;

/**
 * This class represents a drawable output belt.
 */
public final class DrawableOutputBelt extends BaseDrawableBelt<OutputBelt> {
    /**
     * Create a drawable output belt.
     *
     * @param entity is the output belt entity associated with this drawable
     */
    public DrawableOutputBelt(final OutputBelt entity) {
        super(entity,
                ResolutionManagerImpl.getInstance()
                                     .getCurrentAspectRatio()
                                     .getTextures()
                                     .getTexture(Textures.BELT_OUTPUT)
                                     .getRegion());
    }
}
