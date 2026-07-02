package it.unibo.monoopoly.view.panel.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import it.unibo.monoopoly.controller.data.impl.ViewUpdateDTO;
import it.unibo.monoopoly.view.panel.UpdatablePanel;
import it.unibo.monoopoly.view.position.api.PositionAllocator;
import it.unibo.monoopoly.view.position.impl.NumberAndCirclePosition;
import it.unibo.monoopoly.view.position.impl.PositionAllocatorImpl;

/**
 * The class is used to realize the vision of the main gameBoard and
 * dynamically update the game implementing {@link JPanel}.
 */
public final class GameBoardPanel extends JPanel implements UpdatablePanel {

    private static final long serialVersionUID = 1L;

    private static final double CIRCLE_DIAMETER_RATIO = 0.025;
    private static final double NUMBER_SIZE_RATIO = 0.025;

    /**
     * the height of the frame.
     */
    private final int mainFrameHeight;
    private final transient Image backgroundImage;
    private transient List<NumberAndCirclePosition> numberAndCirclePositions;
    private final transient PositionAllocator positionAllocator;

    /**
     * initialize all the information to create the initial gameBoard.
     * 
     * @param mainFrameHeight height of main frame
     * @param playersColors   data to associate colors to players
     * @param colors          all possible colors
     */
    public GameBoardPanel(final int mainFrameHeight, final Map<String, Color> playersColors, final List<Color> colors) {
        this.mainFrameHeight = mainFrameHeight;
        this.positionAllocator = new PositionAllocatorImpl(mainFrameHeight, playersColors, colors);

        final URL imgURL = ClassLoader.getSystemResource("images/MONOOPOLY_GAMEBOARD_IMAGE.jpg");
        final ImageIcon icon = new ImageIcon(imgURL);
        this.backgroundImage = icon.getImage();
        setPreferredSize(new Dimension(this.mainFrameHeight, this.mainFrameHeight));
        setLayout(new BorderLayout());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final ViewUpdateDTO updateData) {
        this.numberAndCirclePositions = this.positionAllocator.createListCircleNumberPosition(
                updateData.playerPositions(),
                updateData.cellsOwners(),
                updateData.prisonedPlayers(),
                updateData.nBuiltHouses(),
                updateData.mortgagedProperties());
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, this.mainFrameHeight, this.mainFrameHeight, this);
        final int circleDiameter = (int) (this.mainFrameHeight * CIRCLE_DIAMETER_RATIO);
        final int numberSize = (int) (this.mainFrameHeight * NUMBER_SIZE_RATIO);

        for (final var position : this.numberAndCirclePositions) {
            if (position.isCircle()) {
                g.setColor(position.getColor());
                g.fillOval(position.getX(), position.getY(), circleDiameter, circleDiameter);
            } else {
                g.setColor(position.getColor());
                g.setFont(new Font("Arial", Font.BOLD, numberSize));
                g.drawString(position.getNumber(), position.getX(), position.getY());
            }
        }
    }

}
