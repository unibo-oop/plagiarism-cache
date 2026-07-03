package it.oop.project.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A factory for initialized components of the view.
 *
 */
public interface ViewFactory {

    /**
     * Creates a new Tile.
     * 
     * @return a new Tile.
     */
    JLabel createTile();

    /**
     * Creates the label for game title.
     * 
     * @return the label with game title.
     */
    JLabel createTitleLabel();

    /**
     * Creates a label for game score.
     * 
     * @return the label for game score.
     */
    JLabel createScoreLabel();

    /**
     * Creates an empty Board with square matrix shaped layout.
     * 
     * @param size
     *            size of the board.
     * @return a new Board.
     */
    JPanel createBoard(final int size);

    /**
     * Creates a panel for lose dialog with board as background.
     * 
     * @param board
     *            the board to put in background.
     * @return a new LoseDialog.
     */
    JPanel createLoseDialog(final JPanel board);

    /**
     * Creates a panel for win dialog with board as background.
     * 
     * @param size
     *            the board to put in background.
     * @return a new WinDialog.
     */
    JPanel createWinDialog(final JPanel board);

    /**
     * Creates a transparent panel with flow layout.
     * 
     * @return a transparent panel with flow layout.
     */
    JPanel createTransparentPanel();

    /**
     * Creates the panel for score with score title label already added.
     * 
     * @return the panel for score.
     */
    JPanel createScorePanel();

    /**
     * Creates the panel for best score with best score title label already
     * added.
     * 
     * @return the panel for best score.
     */
    JPanel createBestScorePanel();

    /**
     * Creates a square button where to put an icon.
     * 
     * @return a square button for icons.
     */
    JButton createIconButton();

    /**
     * Creates a button with the specified text.
     * 
     * @param s
     *            specified text for button.
     * @return a button with the specified text.
     */
    JButton createTextButton(final String s);

    /**
     * Creates a panel with border layout and same background color as frame.
     * 
     * @return a panel with border layout and same background color as frame.
     */
    JPanel createBorderLayoutPanel();

    /**
     * Creates a panel with flow layout and same background color as frame.
     * 
     * @return a panel with flow layout and same background color as frame.
     */
    JPanel createFlowLayoutPanel();

    /**
     * Creates the frame wrapper.
     * 
     * @return the frame wrapper.
     */
    JPanel createFrameWrapper();

    /**
     * Creates an initialized Frame.
     * 
     * @return a new Frame.
     */
    JFrame createFrame();

}
