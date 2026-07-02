package giocoscudetto.view.impl.match;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.view.api.GameObserver;
import giocoscudetto.view.api.ImageBoardLoader;
import giocoscudetto.view.impl.ImageBoardLoaderImpl;
import giocoscudetto.view.impl.initialize.DefaultPanelImpl;
import giocoscudetto.controller.api.MatchController;

/**
 * This class represents the panel where the board is drawn,
 * it has methods to draw the boxes and the pawns of the players, 
 * it also has an animation loop to animate the movement of the pawns when the dice are rolled.
 */
public class BoardPanel extends DefaultPanelImpl implements GameObserver {

    private static final long serialVersionUID = 1L;
    private static final int BORDER_SIZE = 5;
    private static final Color CENTER_COLOR = new Color(223, 189, 138);
    private static final double OFFSET_HOME_PAWN = 1.0 / 3.0;
    private static final double OFFSET_GUEST_PAWN = 2.0 / 3.0;
    private static final int BOX_SIDE = 9;
    private static final int DRAWABLE_BOX_COUNT = 32;
    private static final int TOP_ROW_FIRST_POSITION = 0;
    private static final int TOP_ROW_END_POSITION = 8;
    private static final int RIGHT_COLUMN_START_POSITION = 9;
    private static final int RIGHT_COLUMN_END_POSITION = 16;
    private static final int BOTTOM_ROW_START_POSITION = 17;
    private static final int BOTTOM_ROW_END_POSITION = 24;
    private static final int LEFT_COLUMN_START_POSITION = 25;
    private static final int LEFT_COLUMN_END_POSITION = 32;
    private static final int RIGHT_COLUMN_DRAW_OFFSET = 7;
    private static final int BOTTOM_ROW_DRAW_OFFSET = 16;
    private static final int LEFT_COLUMN_DRAW_OFFSET = 24;
    private static final int RIGHT_PAWN_POSITION_OFFSET = 8;
    private static final int BOTTOM_PAWN_POSITION_OFFSET = 15;
    private static final int LEFT_PAWN_POSITION_OFFSET = 24;
    private static final int PAWN_RADIUS_DIVISOR = 6;
    private static final int PAWN_SHADOW_OFFSET = 3;
    private static final int PAWN_INNER_MARGIN = 2;
    private static final int TITLE_X_DIVISOR = 3;
    private static final int TITLE_FONT_DIVISOR = 2;
    private static final int LABEL_FONT_DIVISOR = 3;
    private static final int SCORE_Y_MULTIPLIER = 5;
    private static final int SCORE_TEXT_Y_MULTIPLIER = 6;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final int SLEEP_TIME = 300;
    private static final int SLEEP_TIME2 = 50;
    private static final Color SHADOW_COLOR = new Color(0, 0, 0, 80);

    private final ImageBoardLoader imageLoaded;
    private final MatchController matchController;
    private final Thread animationThread;
    private volatile boolean checkBoxDone;
    private volatile int boardSizeh;
    private volatile int boardSizew;
    private volatile int boxW;
    private volatile int boxH;
    private int animatedHomePos;
    private int animatedGuestPos;

    /**
     * Constructor of the BoardPanel class.
     * 
     * @param matchController the match controller.
     * @throws IOException if loading an image fails.
     */
    @SuppressFBWarnings //not expose internal representation.
    public BoardPanel(final MatchController matchController) throws IOException {
        this.matchController = matchController;
        this.matchController.addObserver(this);
        this.imageLoaded = new ImageBoardLoaderImpl(matchController);
        setBackground(BACKGROUND_COLOR); //NOPMD
        this.addComponentListener(new java.awt.event.ComponentAdapter() { //NOPMD
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {
                repaint();
            }
        });

        this.animationThread = new Thread(this::animationLoop);
        this.animationThread.setDaemon(true);
    }

    /**
     * this method starts the animation thread, 
     * to animate the movement of the pawns when the dice are rolled.
     */
    public void start() {
        this.animationThread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings 
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.setSizes();
        final Graphics2D g2d = (Graphics2D) g;

        drawCenter(g2d);
        drawAllBoxes(g2d);
        drawAllPawns(g2d);
    }

    /**
     * This method is the animation loop that animates the movement of the pawns.
     */
    private void animationLoop() {
        boolean wasAnimating = false;
        while (true) {
            try {
                final int targetHome = matchController.getHomePosition();
                final int targetGuest = matchController.getGuestPosition();

                final boolean homeMoving = animatedHomePos != targetHome;
                final boolean guestMoving = animatedGuestPos != targetGuest;

                if (homeMoving || guestMoving) {
                    wasAnimating = true;
                    if (homeMoving) {
                        animatedHomePos += (animatedHomePos < targetHome) ? 1 : -1;
                    }
                    if (guestMoving) {
                        animatedGuestPos += (animatedGuestPos < targetGuest) ? 1 : -1;
                    }

                    SwingUtilities.invokeLater(this::repaint);
                    Thread.sleep(SLEEP_TIME);
                } else {
                    if (wasAnimating) {
                        wasAnimating = false;

                        if (!this.checkBoxDone) {
                            if (this.matchController.isHelpFlag()) {
                            JOptionPane.showMessageDialog(this, this.matchController.getBoxDescript(),
                                                            "Event of " + this.matchController.getBoxName(), 
                                                            JOptionPane.INFORMATION_MESSAGE); 
                            }
                            this.checkBoxDone = true;
                            this.matchController.checkBox();
                        }
                        this.matchController.notifyViews();
                    }
                    Thread.sleep(SLEEP_TIME2);
                }

            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * This method resets the checkBoxDone flag to false.
     */
    public void resetCheckBoxDone() {
        this.checkBoxDone = false;
    }

    private void drawAllPawns(final Graphics2D g2d) {
        this.drawPawn(g2d, new Color(this.matchController.getHomePawnRGB()), this.animatedHomePos, OFFSET_HOME_PAWN);
        this.drawPawn(g2d, new Color(this.matchController.getGuestPawnRGB()), this.animatedGuestPos, OFFSET_GUEST_PAWN);

    }

    /**
     * This method draws all the boxes of the board.
     * 
     * @param g2d the graphics context.
     */
    private void drawAllBoxes(final Graphics2D g2d) {

        for (int i = 0; i < DRAWABLE_BOX_COUNT; i++) {
            this.drawBox(g2d, this.imageLoaded.getImage(i), i);
        }
    }

    /**
     * This method draws a single box on the board based on its position and the associated image.
     * 
     * @param g2d the graphics context.
     * @param image the image to draw.
     * @param position the position of the box.
     */
    private void drawBox(final Graphics2D g2d, final Image image, final int position) {

        final int x = this.boardSizew;
        final int y = this.boardSizeh;

        if (position >= TOP_ROW_FIRST_POSITION && position <= TOP_ROW_END_POSITION) {
            g2d.drawImage(image, x - (position + 1) * boxW, y - boxH, boxW, boxH, null);
        }
        if (position >= RIGHT_COLUMN_START_POSITION && position <= RIGHT_COLUMN_END_POSITION) {
            g2d.drawImage(image, 0, y - (position - RIGHT_COLUMN_DRAW_OFFSET) * boxH, boxW, boxH, null);
        }
        if (position >= BOTTOM_ROW_START_POSITION && position <= BOTTOM_ROW_END_POSITION) {
            g2d.drawImage(image, (position - BOTTOM_ROW_DRAW_OFFSET) * boxW, 0, boxW, boxH, null);
        }
        if (position >= LEFT_COLUMN_START_POSITION && position <= LEFT_COLUMN_END_POSITION) {
            g2d.drawImage(image, x - boxW, (position - LEFT_COLUMN_DRAW_OFFSET) * boxH, boxW, boxH, null);
        }
    }

    /**
     * This method draws the center of the board, it also draws the score and the names of the players.
     * 
     * @param g2d the graphics context.
     */
    private void drawCenter(final Graphics2D g2d) {
        final int x = boxW;
        final int y = boxH;
        final int w = boardSizew - 2 * x;
        final int h = boardSizeh - 2 * y;
        final int scoreY = boxH * SCORE_Y_MULTIPLIER;
        final int center = x + (w / 2);
        final String homeName = this.matchController.getHomeName();
        final String guestName = this.matchController.getGuestName();
        final int scoreTextY = y * SCORE_TEXT_Y_MULTIPLIER;

        g2d.setColor(BACKGROUND_COLOR);
        g2d.fillRect(x, y, w, h);
        g2d.drawRect(x, y, w, h);

        g2d.setColor(CENTER_COLOR);
        g2d.fillRect(x + BORDER_SIZE, y + BORDER_SIZE, w - 2 * BORDER_SIZE, h - 2 * BORDER_SIZE);
        g2d.drawRect(x + BORDER_SIZE, y + BORDER_SIZE, w - 2 * BORDER_SIZE, h - 2 * BORDER_SIZE);

        g2d.setColor(Color.red);
        g2d.setFont(new Font("Boh", Font.BOLD, x / TITLE_FONT_DIVISOR));
        g2d.drawString("GIOCO DELLO SCUDETTO", x + x / TITLE_X_DIVISOR, y * 2);

        g2d.setColor(Color.black);
        g2d.setFont(new Font("Boh", Font.BOLD, x / 3));
        g2d.drawString("SCORE", center - g2d.getFontMetrics().stringWidth("SCORE") / 2, scoreY);
        g2d.drawString(this.matchController.getScore(),
                        center - g2d.getFontMetrics().stringWidth(this.matchController.getScore()) / 2, scoreTextY);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, x / LABEL_FONT_DIVISOR));
        g2d.drawString(homeName, center - g2d.getFontMetrics().stringWidth(this.matchController.getScore()) / 2 
                        - g2d.getFontMetrics().stringWidth(homeName) / 2 - x, scoreTextY);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, x / 3));
        g2d.drawString(guestName, center + g2d.getFontMetrics().stringWidth(this.matchController.getScore()) / 2
                         + x / 2, scoreTextY);
    }

    /**
     * This method draws a single pawn.
     * 
     * @param g2d the graphics context.
     * @param pawnColor the color of the pawn.
     * @param position the position of the pawn.
     * @param offset the offset.
     */
    private void drawPawn(final Graphics2D g2d, final Color pawnColor, final int position, final double offset) {
        final int x = boardSizew;
        final int y = boardSizeh;
        final int r = this.boxW / PAWN_RADIUS_DIVISOR;

        int pawnX = 0;
        int pawnY = 0;

        if (position >= TOP_ROW_FIRST_POSITION && position <= TOP_ROW_END_POSITION) {
            pawnX = x - (position * boxW + (int) (boxW * offset));
            pawnY = y - boxH / 2;
        } else if (position >= RIGHT_COLUMN_START_POSITION && position <= RIGHT_COLUMN_END_POSITION) {
            pawnX = boxW / 2;
            pawnY = y - ((position - RIGHT_PAWN_POSITION_OFFSET) * boxH + (int) (boxH * offset));
        } else if (position >= BOTTOM_ROW_START_POSITION && position <= BOTTOM_ROW_END_POSITION) {
            pawnX = (position - BOTTOM_PAWN_POSITION_OFFSET) * boxW - (int) (boxW * offset);
            pawnY = boxH / 2;
        } else if (position >= LEFT_COLUMN_START_POSITION && position <= LEFT_COLUMN_END_POSITION) {
            pawnX = x - boxW / 2;
            pawnY = (position - LEFT_PAWN_POSITION_OFFSET) * boxH + (int) (boxH * offset);
        }
        drawCircle(g2d, pawnX, pawnY, r, pawnColor);
    }

    /**
     * This method draws a circle with a shadow effect to represent the pawn.
     * 
     * @param g2d the graphics context.
     * @param x the x-coordinate of the center of the circle.
     * @param y the y-coordinate of the center of the circle.
     * @param r the radius of the circle.
     * @param color the color of the circle.
     */
    private void drawCircle(final Graphics2D g2d, final int x, final int y, final int r, final Color color) {

        g2d.setColor(SHADOW_COLOR);
        g2d.fillOval(x - r + PAWN_SHADOW_OFFSET, y - r + PAWN_SHADOW_OFFSET, r * 2, r * 2);

        g2d.setColor(Color.BLACK);
        g2d.fillOval(x - r, y - r, r * 2, r * 2);

        g2d.setColor(color);
        g2d.fillOval(x - r + PAWN_INNER_MARGIN, y - r + PAWN_INNER_MARGIN, (r - PAWN_INNER_MARGIN) * 2,
                    (r - PAWN_INNER_MARGIN) * 2);
    }

    /**
     * This method sets the sizes of the board and the boxes based on the current size of the panel.
     */
    private void setSizes() {
        this.boardSizeh = this.getHeight();
        this.boardSizew = this.getWidth();
        this.boxH = this.boardSizeh / BOX_SIDE;
        this.boxW = this.boardSizew / BOX_SIDE;
    }

    /**
     * This method returns the current animated position of the home pawn.
     * 
     * @return the animated position of the home pawn
     */
    public int getAnimatedHomePosition() {
        return this.animatedHomePos;
    }

    /**
     * This method returns the current animated position of the guest pawn.
     * 
     * @return the animated position of the guest pawn
     */
    public int getAnimatedGuestPosition() {
        return this.animatedGuestPos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState() {
        this.repaint();
    }
}
