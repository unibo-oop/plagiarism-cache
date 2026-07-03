package it.oop.project.view;

import it.oop.project.util.ResourceLoader;
import it.oop.project.util.ResourceLoaderImpl;
import it.oop.project.util.ScreenSize;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * {@inheritDoc}
 *
 */
public class ClassicViewFactory implements ViewFactory {

    private static ColorProvider colors;
    private static Font font;
    private static BufferedImage icon;
    private static int smallerSide;

    /**
     * Constructs a classic 2048 game view factory of scalable components.
     */
    public ClassicViewFactory() {
        colors = new ClassicColorProvider();
        smallerSide = ScreenSize.getSmallerSide();
        loadResources();
    }

    private void loadResources() {
        ResourceLoader loader = new ResourceLoaderImpl();
        try {
            URL fontUrl = loader.load("fonts/ClearSans-Bold.ttf");
            ClassicViewFactory.font = Font.createFont(Font.TRUETYPE_FONT,
                    fontUrl.openStream());
            URL imgUrl = loader.load("images/icon.png");
            ClassicViewFactory.icon = ImageIO.read(imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JLabel createTile() {
        return new Tile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JLabel createTitleLabel() {
        return new TitleLabel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JLabel createScoreLabel() {
        return new ScoreLabel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createBoard(final int size) {
        return new Board(size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createLoseDialog(final JPanel board) {
        return new LoseDialog(board);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createWinDialog(final JPanel board) {
        return new WinDialog(board);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createTransparentPanel() {
        return new TransparentPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createScorePanel() {
        return new ScorePanel(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createBestScorePanel() {
        return new ScorePanel(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JButton createIconButton() {
        return new IconButton();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JButton createTextButton(final String s) {
        return new TextButton(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createBorderLayoutPanel() {
        return new BorderLayoutPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createFlowLayoutPanel() {
        return new FlowLayoutPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createFrameWrapper() {
        return new FrameWrapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JFrame createFrame() {
        return new Frame();
    }

    private static class Tile extends JLabel {

        private static final long serialVersionUID = 1L;
        private static final Dimension TILE_DIMENSION = new Dimension(
                (int) smallerSide / 7, (int) smallerSide / 7);
        private static final Font TILE_FONT = font;

        private Tile() {
            this.setMaximumSize(TILE_DIMENSION);
            this.setMinimumSize(TILE_DIMENSION);
            this.setPreferredSize(TILE_DIMENSION);
            this.setOpaque(true);
            this.setHorizontalAlignment(SwingConstants.CENTER);
            this.setVerticalAlignment(SwingConstants.CENTER);
            this.setFont(TILE_FONT);
        }

    }

    private static class TitleLabel extends JLabel {

        private static final long serialVersionUID = 1L;
        private static final String TITLE = "2048";
        private static final Font TITLE_FONT = font
                .deriveFont((float) smallerSide / 10);

        private TitleLabel() {
            this.setFont(TITLE_FONT);
            this.setText(TITLE);
            this.setForeground(colors.getDarkFont());
            this.setOpaque(true);
            this.setBackground(colors.getFrameBg());
            this.setHorizontalAlignment(SwingConstants.LEFT);
            this.setVerticalAlignment(SwingConstants.NORTH);
        }
    }

    private static class ScoreLabel extends JLabel {

        private static final long serialVersionUID = 1L;
        private static final Font SCORE_FONT = font
                .deriveFont((float) smallerSide / 45);

        private ScoreLabel() {
            this.setHorizontalAlignment(SwingConstants.CENTER);
            this.setVerticalAlignment(SwingConstants.NORTH);
            this.setFont(SCORE_FONT);
            this.setForeground(colors.getScoreFont());
        }

    }

    private static class ScoreTitleLabel extends JLabel {
        private static final long serialVersionUID = 1L;
        private static final Font SCORE_FONT = font
                .deriveFont((float) smallerSide / 55);
        private static final String SCORE = "SCORE";
        private static final String BEST_SCORE = "BEST";

        private ScoreTitleLabel(boolean isBestScore) {
            this.setText(isBestScore ? BEST_SCORE : SCORE);
            this.setHorizontalAlignment(SwingConstants.CENTER);
            this.setVerticalAlignment(SwingConstants.BOTTOM);
            this.setFont(SCORE_FONT);
            this.setForeground(colors.getScoreTitleFont());
        }
    }

    private static abstract class BgColoredPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        /**
         * Constructs a panel with classic 2048 background color with no layout.
         */
        protected BgColoredPanel() {
            this.setOpaque(true);
            this.setBackground(colors.getFrameBg());
        }
    }

    private static class BorderLayoutPanel extends BgColoredPanel {

        private static final long serialVersionUID = 1L;

        private BorderLayoutPanel() {
            super();
            this.setLayout(new BorderLayout());
        }

    }

    private static class FlowLayoutPanel extends BgColoredPanel {

        private static final long serialVersionUID = 1L;

        private FlowLayoutPanel() {
            super();
            this.setLayout(new FlowLayout());
        }

    }

    private static class Board extends JPanel {

        private static final long serialVersionUID = 1L;
        private static final int TILE_GAP = (int) smallerSide / 70;

        private Board(int size) {
            GridLayout boardLayout = new GridLayout(size, size, TILE_GAP,
                    TILE_GAP);
            this.setLayout(boardLayout);
            this.setOpaque(true);
            this.setBackground(colors.getBoardBg());
            this.setBorder(
                    new EmptyBorder(TILE_GAP, TILE_GAP, TILE_GAP, TILE_GAP));
        }
    }

    private static abstract class AbstractDialog extends JPanel {

        private static final long serialVersionUID = 1L;
        private static final float OPACITY = 0.5f;
        private static final Font FONT = font
                .deriveFont((float) smallerSide / 13);
        private static final Border BORDER = new EmptyBorder(
                (int) smallerSide / 6, 0, (int) smallerSide / 8, 0);
        /**
         * Image to put in background.
         */
        protected Image image;
        /**
         * Message to show
         */
        protected JLabel message;

        /**
         * Constructs an initialized dialog.
         * 
         * @param board
         *            board to put in background.
         */
        protected AbstractDialog(final JPanel board) {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.image = new BufferedImage(board.getWidth(), board.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            this.message = new JLabel();
            this.message.setFont(FONT);
            this.message.setHorizontalAlignment(SwingConstants.CENTER);
            this.message.setBorder(BORDER);
            this.message.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(message);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void paintComponent(Graphics g) {
            setOpaque(false);
            g.drawImage(image, 0, 0, null);
            super.paintComponent(g);
        }

        /**
         * Returns opaque graphics.
         * 
         * @return opaque graphics.
         */
        protected Graphics2D getOpaqueScreen() {
            Graphics2D g = (Graphics2D) this.image.getGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    OPACITY));
            return g;
        }

    }

    private static class LoseDialog extends AbstractDialog {

        private static final long serialVersionUID = 1L;
        private static final String LOSE_MESSAGE = "Game over!";

        private LoseDialog(JPanel board) {
            super(board);
            board.paint(getOpaqueScreen());
            this.message.setText(LOSE_MESSAGE);
            this.message.setForeground(colors.getDarkFont());
        }

    }

    private static class WinDialog extends AbstractDialog {

        private static final long serialVersionUID = 1L;
        private static final String WIN_MESSAGE = "You win!";

        private WinDialog(JPanel board) {
            super(board);
            Graphics2D g = (Graphics2D) this.image.getGraphics();
            g.setPaint(colors.getWinDialogSaturation());
            g.fillRect(0, 0, board.getWidth(), board.getHeight());
            board.paint(getOpaqueScreen());
            this.message.setText(WIN_MESSAGE);
            this.message.setForeground(colors.getBrightFont());
        }

    }

    private static class TransparentPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        private TransparentPanel() {
            this.setLayout(new FlowLayout());
            this.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.setOpaque(false);
        }
    }

    private static class ScorePanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private static final Dimension SCORE_DIMENSION = new Dimension(
                (int) smallerSide / 7, (int) smallerSide / 15);

        private ScorePanel(boolean isBestScore) {
            this.setLayout(new GridLayout(2, 0));
            this.setMaximumSize(SCORE_DIMENSION);
            this.setMinimumSize(SCORE_DIMENSION);
            this.setPreferredSize(SCORE_DIMENSION);
            this.setOpaque(true);
            this.setBackground(colors.getScoreBg());
            this.add(new ScoreTitleLabel(isBestScore));
        }

    }

    private static abstract class EmptyButton extends JButton {

        private static final long serialVersionUID = 1L;

        /**
         * Constructs an empty button with classic 2048 appearance.
         */
        protected EmptyButton() {
            this.setOpaque(true);
            this.setFocusPainted(false);
            this.setBorderPainted(false);
            this.setContentAreaFilled(false);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.setBorderPainted(false);
            this.setFocusable(false);
            this.setMinimumSize(getDimension());
            this.setMaximumSize(getDimension());
            this.setPreferredSize(getDimension());
        }

        /**
         * Returns the dimension of this button.
         * 
         * @return the dimension of this button.
         */
        protected abstract Dimension getDimension();

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(colors.getButtonBg());
            } else if (getModel().isRollover()) {
                g.setColor(colors.getButtonBg());
            } else {
                g.setColor(colors.getButtonBg());
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
    }

    private static class IconButton extends EmptyButton {

        private static final long serialVersionUID = 1L;

        private static final Dimension BUTTON_DIMENSION = new Dimension(
                (int) smallerSide / 14, (int) smallerSide / 14);

        private IconButton() {
            super();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Dimension getDimension() {
            return BUTTON_DIMENSION;
        }

    }

    private static class TextButton extends EmptyButton {

        private static final long serialVersionUID = 1L;
        private static final Font BUTTON_FONT = font
                .deriveFont((float) smallerSide / 40);
        private static final Dimension BUTTON_DIMENSION = new Dimension(
                (int) smallerSide / 5, (int) smallerSide / 14);

        private TextButton(final String s) {
            super();
            this.setText(s);
            this.setForeground(colors.getButtonFont());
            this.setContentAreaFilled(false);
            this.setFont(BUTTON_FONT);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Dimension getDimension() {
            return BUTTON_DIMENSION;
        }

    }

    private static class FrameWrapper extends BgColoredPanel {

        private static final long serialVersionUID = 1L;
        private static final int PADDING = (int) smallerSide / 140;

        private FrameWrapper() {
            this.setLayout(new BorderLayout());
            this.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        }

    }

    private static class Frame extends JFrame {

        private static final long serialVersionUID = 1L;
        private static String FRAME_TITLE = "2048";

        private Frame() {
            this.setIconImage(icon);
            this.setTitle(FRAME_TITLE);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setFocusable(true);
            this.setResizable(false);
        }
    }
}
