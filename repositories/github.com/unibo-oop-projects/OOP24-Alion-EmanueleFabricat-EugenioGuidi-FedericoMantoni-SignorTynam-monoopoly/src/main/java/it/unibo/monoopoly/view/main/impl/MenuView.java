package it.unibo.monoopoly.view.main.impl;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.menu.api.MenuController;
import it.unibo.monoopoly.view.panel.menu.MenuPanel;

/**
 * Initial view shown at the start of application.
 */
public class MenuView extends AbstractView {

    private static final int MIN_SIZE_X = 600;
    private static final int MIN_SIZE_Y = MIN_SIZE_X;
    private final JPanel menuPanel;

    /**
     * Construct and initialize thr frame and panels of {@link MenuView}.
     * 
     * @param menuController the controller of the application
     */
    public MenuView(final MenuController menuController) {
        super();
        final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.getMainFrame().setSize((int) screenDimension.getWidth() / 2, (int) screenDimension.getHeight() / 2);
        this.getMainFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getMainFrame().setMinimumSize(new Dimension(MIN_SIZE_X, MIN_SIZE_Y));
        menuPanel = new MenuPanel(menuController, super.getColors());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public JPanel getMainPanel() {
        return this.menuPanel;
    }

}
