package giocoscudetto.view.impl;

import java.awt.CardLayout;
import java.awt.Window;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.view.api.ViewManager;

/**
 * This class manages the different views of the game, it uses a CardLayout to switch between the different panels.
 */
public class ViewManagerImpl implements ViewManager {

    private final CardLayout cardLayout;
    private final JPanel container;

    /**
     * Constructor of the ViewManagerImpl class, it initializes the CardLayout and the container panel.
     */
    public ViewManagerImpl() {
        this.cardLayout = new CardLayout();
        this.container = new JPanel(cardLayout);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addView(final JPanel panel, final String name) {
        container.add(panel, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showView(final String name) {
        this.cardLayout.show(container, name);
        container.revalidate();
        container.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings
    public JPanel getContainer() {
        return this.container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        final Window frame = SwingUtilities.getWindowAncestor(this.container);
        frame.dispose();
    }
}
