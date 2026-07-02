package it.unibo.monoopoly.view.panel.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.apache.commons.lang3.tuple.Triple;

import it.unibo.monoopoly.controller.data.impl.ViewUpdateDTO;
import it.unibo.monoopoly.view.panel.UpdatablePanel;

/**
 * The class implements the principal panel {@link UpdatablePanel} that contain
 * the
 * game board panel and player panel.
 */
public final class GamePanel extends JPanel implements UpdatablePanel {

    private static final long serialVersionUID = 1L;
    private static final Color GREEN_MONOPOLY = new Color(0xecfcf4);

    /**
     * The istance {@link PlayerPanel}. Panel for the visualization of players and
     * some panels for interaction of the
     * user.
     */
    private final PlayerPanel playerPanel;
    /**
     * It is the panel where the cells, the current positions of the players, the
     * owners of the properties and the houses built are located.
     */
    private final GameBoardPanel gameBoardPanel;

    /**
     * initialize all the fields needed and set the preferred size based on
     * dimension of frame.
     * 
     * @param mainFrameHeight height of the frame
     * @param mainFrameWidth  width of the frame
     * @param firstPlayer     the player that starts the game
     * @param initializedList the list of players with their respective color, name,
     *                        and initial money.
     * @param playersColors   data to associate colors to players
     * @param colors          all possible colors
     */
    public GamePanel(final int mainFrameHeight,
            final int mainFrameWidth, final String firstPlayer,
            final List<Triple<String, Integer, Color>> initializedList,
            final Map<String, Color> playersColors, final List<Color> colors) {

        this.gameBoardPanel = new GameBoardPanel(mainFrameHeight, playersColors, colors);
        this.playerPanel = new PlayerPanel(mainFrameHeight, firstPlayer, List.copyOf(initializedList));

        final JPanel eastPanel = new JPanel();
        final JPanel westPanel = new JPanel();
        final JPanel centerPanel = new JPanel();

        final int centerHeight = mainFrameHeight;
        final int centerWidth = centerHeight + (centerHeight / 2);
        final int eastWestHeight = mainFrameHeight;
        final int eastWestWidth = (mainFrameWidth - centerWidth) / 2;

        eastPanel.setPreferredSize(new Dimension(eastWestWidth, eastWestHeight));
        westPanel.setPreferredSize(new Dimension(eastWestWidth, eastWestHeight));
        centerPanel.setPreferredSize(new Dimension(centerWidth, centerHeight));

        eastPanel.setBackground(GREEN_MONOPOLY);
        westPanel.setBackground(GREEN_MONOPOLY);

        this.setLayout(new BorderLayout());

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(this.gameBoardPanel, BorderLayout.WEST);
        centerPanel.add(playerPanel, BorderLayout.CENTER);
    }

    /**
     * is the method that takes care of setting the current panel dynamically during
     * the game.
     * 
     * @param panel is the panel to be set dynamically during the game
     */
    public void setInteractivePanel(final JPanel panel) {
        this.playerPanel.setInteractivePanel(panel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final ViewUpdateDTO updateData) {
        this.gameBoardPanel.update(updateData);
        this.playerPanel.update(updateData);
    }

}
