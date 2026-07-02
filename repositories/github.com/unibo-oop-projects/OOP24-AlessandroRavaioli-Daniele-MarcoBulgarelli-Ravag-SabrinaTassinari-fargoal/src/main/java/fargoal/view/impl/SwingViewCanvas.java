package fargoal.view.impl;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This class is a {@link JPanel} in which elements can register themselves to be drawn.
 */
@SuppressFBWarnings(
    value = {"Se"},
    justification = "Nothing of this entire application is meant to be serializable"
)
public class SwingViewCanvas extends JPanel {
    private static final long serialVersionUID = 5678472183267292378L;

    /** 
     * the list of all the registered renderers.
     */
    private final List<Consumer<Graphics2D>> list;
    /**
     * A value that is needed to reduce flickering while resizing.
     */
    private boolean canDraw;

    /**
     * Constructor that create a new list for the {@link Graphics2D}
     * and set the local field {@link #canDraw} to true.
     */
    SwingViewCanvas() {
        this.list = new LinkedList<>();
        this.canDraw = true;
    }

    /**
     * Method that adds in the queue the {@link Consumer}.
     * 
     * @param g2d - the {@link Consumer} {@link Graphics2D} to add in the {@link #list}
     */
    public void addToList(final Consumer<Graphics2D> g2d) {
        SwingUtilities.invokeLater(() -> list.add(g2d));
    }

    /** {@inheritDoc} */
    @SuppressFBWarnings(
        value = {"BC_UNCONFIRMED_CAST"},
        justification = "This application uses only Graphics2D"
    )
    @Override
    public void paintComponent(final Graphics g) {
        if (this.canDraw) {
            this.canDraw = false;
            super.paintComponent(g);
            final Graphics2D g2d = (Graphics2D) g;
            this.list.forEach(d -> d.accept(g2d));
            this.list.clear(); 
        }
    }

    /**
     * Set the local field {@link #canDraw}.
     * 
     * @param canDraw - the boolean value that {@link #canDraw} assume
     */
    public void enableDraw(final boolean canDraw) {
        this.canDraw = canDraw;
    }
}
