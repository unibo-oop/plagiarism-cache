package fargoal.view.impl;

import java.awt.Graphics2D;
import java.util.function.Consumer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import fargoal.view.api.Renderer;
import fargoal.view.api.View;

/**
 * Renderer for the top part of the screen.
 */
public class SwingRendererTop implements Renderer {
    private final Consumer<Graphics2D> drawingAction;
    private final SwingView view;

    /**
     * Constructor that assigns the local fields {@link #drawingAction} and {@link #view}.
     * 
     * @param drawing - that specifies what to draw
     * @param view - the general view then casted to a {@link SwingView}
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2"},
        justification = "The class needs to work on the same view as the one given"
            + "so if the one given changes the reference also needs to change"
    )
    public SwingRendererTop(final Consumer<Graphics2D> drawing, final View view) {
        this.drawingAction = drawing;
        this.view = (SwingView) view;
    }

    /** {@inheritDoc} */
    @Override
    public void render() {
        this.view.registerDrawingActionTop(drawingAction);
    }
}
