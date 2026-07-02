package it.unibo.monoopoly.view.main.impl;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.view.main.api.View;

/**
 * Abstract class that represents a {@link View} used by the application.
 */
public abstract class AbstractView implements View {

    private static final List<Color> COLORS = List.of(Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE);

    private final JFrame mainFrame = new JFrame("MONOOPOLY");

    /**
     * initialize the main frame of a {@link View}.
     */
    public AbstractView() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display() {
        this.getMainFrame().add(this.getMainPanel());
        this.mainFrame.setVisible(true);
        this.getMainFrame().requestFocusInWindow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public final JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * Gets the list of colors used in the game.
     * 
     * @return the list of colors used in the game
     */
    protected static List<Color> getColors() {
        return COLORS;
    }

}
