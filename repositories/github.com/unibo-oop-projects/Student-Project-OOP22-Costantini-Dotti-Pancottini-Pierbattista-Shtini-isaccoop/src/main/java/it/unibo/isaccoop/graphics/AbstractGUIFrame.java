package it.unibo.isaccoop.graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Abstract class that implements common methods of {@link GUIFrame}.
 */
public abstract class AbstractGUIFrame extends JFrame implements GUIFrame {

    private static final long serialVersionUID = -8514863486959474914L;
    private static final int FRAME_RESIZE_PROPORTION = 2;

    /**
     * Constructor.
     * Creates a default main panel (that uses a {@link BorderLayout} for this {@link JFrame}.
     * @param title the title of this {@link JFrame}
     */
    protected AbstractGUIFrame(final String title) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle(title);
        addMainPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display() {
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        final int frameW = sw / FRAME_RESIZE_PROPORTION;
        final int frameH = sh / FRAME_RESIZE_PROPORTION;
        this.setSize(frameW, frameH);
        this.setPreferredSize(new Dimension(frameW, frameH));
        this.setMinimumSize(new Dimension(frameW, frameH));
        this.setResizable(true);

        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        this.setLocationByPlatform(true);
        this.pack();
        this.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addElementToMainPanel(final Component comp, final Object position) {
        this.add(comp, position);
    }

    /**
     * Add the main panel to the {@link JFrame}.
     */
    private void addMainPanel() {
        // the main panel
        final JPanel mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel);
    }
}
