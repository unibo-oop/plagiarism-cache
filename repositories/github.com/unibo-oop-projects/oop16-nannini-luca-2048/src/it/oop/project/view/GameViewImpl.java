package it.oop.project.view;

import it.oop.project.controller.GameController;
import it.oop.project.util.Direction;
import it.oop.project.util.ResourceLoader;
import it.oop.project.util.ResourceLoaderImpl;
import it.oop.project.util.ScreenSize;
import it.oop.project.util.SquareMatrix;
import it.oop.project.util.SquareMatrixImpl;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.common.base.Optional;

/**
 * {@inheritDoc}
 *
 */
public class GameViewImpl implements GameView {

    private static final String EMPTY = " ";
    private static final String NEW_GAME_BUTTON = "New Game";
    private static final String KEEP_PLAYING_BUTTON = "Keep playing";
    private static final String TRY_AGAIN_BUTTON = "Try again";
    private static final String VOLUME_ON_PATH = "images/volumeOn.png";
    private static final String VOLUME_OFF_PATH = "images/volumeOff.png";
    private GameController controller;
    private final ViewFactory viewFactory;
    private final ColorProvider colors;
    private final AnimationFactory animations;
    private final JFrame frame;
    private final JPanel frameWrapper;
    private final JPanel northPanel;
    private final JPanel titlePanel;
    private final JLabel title;
    private final JPanel scoreWrapper;
    private final JPanel scorePanel;
    private final JLabel score;
    private final JPanel bestScorePanel;
    private final JLabel bestScore;
    private final JPanel menuWrapper;
    private final JButton volume;
    private final JButton newGame;
    private final JPanel board;
    private final KeyListener keyListener;
    private JPanel dialog;
    private SquareMatrix tiles;
    private int size;

    /**
     * Constructs a new game view with classic 2048 appearance.
     * 
     * @param size
     *            board size.
     */
    public GameViewImpl(final int size) {
        this.viewFactory = new ClassicViewFactory();
        this.colors = new ClassicColorProvider();
        this.animations = new AnimationFactoryImpl();

        this.frame = this.viewFactory.createFrame();

        this.frameWrapper = this.viewFactory.createFrameWrapper();

        this.titlePanel = this.viewFactory.createFlowLayoutPanel();

        this.title = this.viewFactory.createTitleLabel();

        this.scoreWrapper = this.viewFactory.createFlowLayoutPanel();

        this.scorePanel = this.viewFactory.createScorePanel();
        this.score = this.viewFactory.createScoreLabel();
        this.scorePanel.add(this.score);

        this.bestScorePanel = this.viewFactory.createBestScorePanel();
        this.bestScore = this.viewFactory.createScoreLabel();
        this.bestScorePanel.add(this.bestScore);

        this.scoreWrapper.add(this.scorePanel);
        this.scoreWrapper.add(this.bestScorePanel);

        this.menuWrapper = this.viewFactory.createFlowLayoutPanel();

        this.volume = this.viewFactory.createIconButton();
        this.volume.addActionListener(l -> {
            this.controller.setVolume();
        });
        this.menuWrapper.add(this.volume);

        this.newGame = this.viewFactory.createTextButton(NEW_GAME_BUTTON);
        this.newGame.addActionListener(new TryAgainListener());
        this.menuWrapper.add(this.newGame);

        this.titlePanel.add(this.title);
        this.titlePanel.add(this.scoreWrapper);

        this.northPanel = this.viewFactory.createBorderLayoutPanel();
        this.northPanel.add(titlePanel, BorderLayout.NORTH);
        this.northPanel.add(menuWrapper, BorderLayout.CENTER);

        this.size = size;
        this.board = this.viewFactory.createBoard(this.size);
        this.tiles = new SquareMatrixImpl(this.size);
        for (int row = 0; row < this.size; row++) {
            List<Optional<Object>> rowTiles = new ArrayList<>();
            for (int col = 0; col < this.size; col++) {
                JLabel tile = this.viewFactory.createTile();
                rowTiles.add(Optional.of(tile));
                this.board.add(tile);
            }
            this.tiles.writeRow(row, rowTiles);
        }

        this.frameWrapper.add(this.northPanel, BorderLayout.NORTH);
        this.frameWrapper.add(this.board, BorderLayout.CENTER);

        this.keyListener = new GameKeyListener();
        this.frame.addKeyListener(this.keyListener);

        this.frame.getContentPane().add(this.frameWrapper);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final SquareMatrix stateMatrix,
            final List<Point> spawnedCoordinates,
            final List<Point> fusionCoordinates, final int score,
            final int bestScore) {
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                Point coordinates = new Point(row, col);
                JLabel tile = (JLabel) this.tiles.get(coordinates).get();
                Optional<Object> stateValObj = stateMatrix.get(coordinates);
                Optional<Integer> stateVal = castOptional(stateValObj);
                String valToWrite = EMPTY;
                if (stateVal.isPresent()) {
                    valToWrite = Integer.toString(stateVal.get());
                }
                tile.setText(valToWrite);
                tile.setBackground(this.colors.getTileBg(stateVal));
                if (!valToWrite.equals(EMPTY)) {
                    tile.setForeground(this.colors.getTileFont(stateVal));
                }
                tile.setFont(tile.getFont().deriveFont(
                        ClassicTileFontSize.getSizeFor(valToWrite)));
            }
        }
        for (Point p : spawnedCoordinates) {
            this.animations
                    .createSpawnAnimation((JLabel) this.tiles.get(p).get())
                    .start();
        }
        for (Point p : fusionCoordinates) {
            this.animations
                    .createFusionAnimation((JLabel) this.tiles.get(p).get())
                    .start();
        }
        this.score.setText(Integer.toString(score));
        this.bestScore.setText(Integer.toString(bestScore));
    }

    private static Optional<Integer> castOptional(Optional<Object> obj) {
        Optional<Integer> result = Optional.absent();
        if (obj.isPresent()) {
            result = Optional.of((Integer) obj.get());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display() {
        this.frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showWinDialog() {
        this.dialog = this.viewFactory.createWinDialog(this.board);
        JPanel buttons = this.viewFactory.createTransparentPanel();
        JButton keepPlaying = this.viewFactory
                .createTextButton(KEEP_PLAYING_BUTTON);
        keepPlaying.addActionListener(new KeepPlayingListener());
        JButton tryAgain = getTryAgainButton();
        buttons.add(keepPlaying);
        buttons.add(tryAgain);
        this.dialog.add(buttons);
        displayDialog();
    }

    private JButton getTryAgainButton() {
        JButton tryAgain = this.viewFactory.createTextButton(TRY_AGAIN_BUTTON);
        tryAgain.addActionListener(new TryAgainListener());
        return tryAgain;
    }

    private void displayDialog() {
        this.frameWrapper.remove(this.board);
        this.frameWrapper.add(this.dialog, BorderLayout.CENTER);
        this.frameWrapper.revalidate();
        this.frameWrapper.repaint();
        this.frame.removeKeyListener(this.keyListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLoseDialog() {
        this.dialog = this.viewFactory.createLoseDialog(this.board);
        JButton tryAgain = getTryAgainButton();
        JPanel buttons = this.viewFactory.createTransparentPanel();
        buttons.add(tryAgain);
        dialog.add(buttons);
        displayDialog();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(GameController controller) {
        this.controller = controller;
    }

    private class KeepPlayingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (frameWrapper.isAncestorOf(dialog)) {
                frameWrapper.remove(dialog);
                frameWrapper.add(board, BorderLayout.CENTER);
                frameWrapper.revalidate();
                frameWrapper.repaint();
                frame.addKeyListener(keyListener);
            }
            controller.buttonPressed();
        }
    }

    private class TryAgainListener extends KeepPlayingListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            super.actionPerformed(arg0);
            controller.newGame();
            controller.buttonPressed();
        }
    }

    private class GameKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                controller.shift(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                controller.shift(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                controller.shift(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                controller.shift(Direction.RIGHT);
                break;
            default:
                break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVolumeIcon(boolean volumeOn) {
        final String path;
        if (volumeOn) {
            path = VOLUME_ON_PATH;
        } else {
            path = VOLUME_OFF_PATH;
        }
        try {
            ResourceLoader loader = new ResourceLoaderImpl();
            URL imgUrl = loader.load(path);
            BufferedImage iconStream = ImageIO.read(imgUrl);
            iconStream = (BufferedImage) getScaledImage(iconStream,
                    ScreenSize.getSmallerSide() / 25,
                    ScreenSize.getSmallerSide() / 25);
            Icon icon = new ImageIcon(iconStream);
            this.volume.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
