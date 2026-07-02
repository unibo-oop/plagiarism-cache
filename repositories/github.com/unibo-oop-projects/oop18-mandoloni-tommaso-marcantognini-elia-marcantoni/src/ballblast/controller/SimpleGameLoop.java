package ballblast.controller;

import ballblast.model.Model;
import ballblast.view.View;

/**
 * The class represents a simple {@link GameLoop}.
 */
public class SimpleGameLoop extends AbstractGameLoop {

    /**
     * Creates a new instance of a simple {@link GameLoop}.
     * 
     * @param view      The view to render on each frame.
     * @param model     The model to update the world on each frame.
     * @param frameRate The refresh rate of the loop.
     */
    public SimpleGameLoop(final Model model, final View view, final int frameRate) {
        super(model, view, frameRate);
    }

    @Override
    protected final void processSounds(final Model model) {
    }

}
