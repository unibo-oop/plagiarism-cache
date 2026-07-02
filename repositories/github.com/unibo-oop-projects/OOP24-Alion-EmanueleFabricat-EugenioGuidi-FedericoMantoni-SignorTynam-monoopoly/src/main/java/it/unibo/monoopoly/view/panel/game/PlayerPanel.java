package it.unibo.monoopoly.view.panel.game;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import org.apache.commons.lang3.tuple.Triple;

import it.unibo.monoopoly.controller.data.impl.ViewUpdateDTO;
import it.unibo.monoopoly.view.panel.UpdatablePanel;

/**
 * Panel for the visualization of players and some panels for interaction of the
 * user.
 */
public final class PlayerPanel extends JPanel implements UpdatablePanel {

    private static final long serialVersionUID = 1L;
    /**
     * This field is used to change the {@link InteractivePanel} to the desired
     * {@link JPanel}.
     */
    private final InteractivePanel interactivePanel;
    /**
     * This field is used to update the {@link VisualizePlayerPanel}.
     */
    private final VisualizePlayerPanel visualizePlayerPanel;

    /**
     * Constructor of the class,
     * that prepare the {@link JPanel} to be shown.
     * 
     * @param mainFrameHeight the height of main frame
     * @param firstPlayer     the name of the player starting the game
     * @param initializedList the starting data of the state of the game
     */
    public PlayerPanel(final int mainFrameHeight, final String firstPlayer,
            final List<Triple<String, Integer, Color>> initializedList) {
        this.interactivePanel = new InteractivePanel(new DefaultInteractivePanel(mainFrameHeight));
        this.visualizePlayerPanel = new VisualizePlayerPanel(mainFrameHeight, firstPlayer, initializedList);
        setLayout(new GridLayout(2, 1));
        add(visualizePlayerPanel);
        add(interactivePanel);
    }

    /**
     * Set the new {@link JPanel} for the {@link InteractivePanel}.
     * 
     * @param panel the new {@link JPanel}
     */
    public void setInteractivePanel(final JPanel panel) {
        this.interactivePanel.remove(0);
        this.interactivePanel.add(panel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final ViewUpdateDTO updateData) {
        this.visualizePlayerPanel.update(updateData);
    }
}
