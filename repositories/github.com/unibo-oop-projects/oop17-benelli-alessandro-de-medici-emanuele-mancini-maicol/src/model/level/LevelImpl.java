package model.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import controller.Score;
import controller.ScoreImpl;
import controller.SoundManager;
import controller.SoundManagerImpl;
import controller.player.PlayerInput;
import controller.player.PlayerInputImpl;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleImpl;

/**
 * Class that is extended by each level's class.
 *
 */
public abstract class LevelImpl implements Level {

    private final AnchorPane root;
    private final Scene gameScene;
    private final GridPane grid;
    private final Obstacle obstacle;
    private final Score score;
    private final SoundManager soundManager;
    private PlayerInput playerInput;
    private ImageView background;
    private String backgroundPath;
    private String tilePath;
    private String stepSoundPath;
    private String musicPath;
    private int mapBoundMinX;
    private int mapBoundMaxX;
    private int mapBoundMinY;
    private int mapBoundMaxY;
    private int charStartCol;
    private int charStartRow;
    private int charFinishCol;
    private int charFinishRow;
    private Point endPosition;
    private final List<Point> playableMap;

    /**
     * Constructor.
     */
    public LevelImpl() {
        this.root = new AnchorPane();
        this.gameScene = new Scene(this.root);
        this.grid = new GridPane();
        this.obstacle = ObstacleImpl.get();
        this.score = ScoreImpl.get();
        this.soundManager = SoundManagerImpl.get();
        this.playableMap = new ArrayList<Point>();

        final int numRow = 21;
        final int numCol = 21;

        for (int i = 0; i < numRow; i++) {
            final RowConstraints row = new RowConstraints();
            row.setPercentHeight((double) 100 / numRow);
            this.grid.getRowConstraints().add(row);
        }

        for (int i = 0; i < numCol; i++) {
            final ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth((double) 100 / numCol);
            this.grid.getColumnConstraints().add(column);
        }

        this.grid.setGridLinesVisible(false);

        AnchorPane.setLeftAnchor(this.grid, 0.0);
        AnchorPane.setRightAnchor(this.grid, 0.0);
        AnchorPane.setTopAnchor(this.grid, 0.0);
        AnchorPane.setBottomAnchor(this.grid, 0.0);
    }

    @Override
    public final AnchorPane getRoot() {
        return this.root;
    }

    @Override
    public final Scene getGameScene() {
        return this.gameScene;
    }

    @Override
    public final GridPane getGrid() {
        return this.grid;
    }

    @Override
    public final void resetGrid() {
        this.grid.getChildren().clear();
        this.root.getChildren().remove(this.grid);
        this.root.getChildren().remove(this.score.getText());
    }

    /**
     * Setter of tilePath.
     * 
     * @param tilePath
     *            the path of the tile's image
     */
    protected void setTilePath(final String tilePath) {
        this.tilePath = tilePath;
    }

    @Override
    public final String getTilePath() {
        return this.tilePath;
    }

    @Override
    public final ImageView getBackground() {
        return this.background;
    }

    /**
     * Setter of backgroundPath. Sets level's background and its size.
     * 
     * @param backgroundPath
     *            background's image path
     */
    protected void setBackground(final String backgroundPath) {
        this.backgroundPath = backgroundPath;
        this.background = new ImageView(new Image(this.backgroundPath));

        this.background.fitWidthProperty().bind(this.root.widthProperty());
        this.background.fitHeightProperty().bind(this.root.heightProperty());
        this.background.preserveRatioProperty();
    }

    @Override
    public final String getBackgroundPath() {
        return this.backgroundPath;
    }

    /**
     * Setter of the bounds of the playable map.
     * 
     * @param mapBoundMinX
     *            minimum value of the x coordinate
     * @param mapBoundMaxX
     *            maximum value of the x coordinate
     * @param mapBoundMinY
     *            minimum value of the y coordinate
     * @param mapBoundMaxY
     *            maximum value of the y coordinate
     */
    protected void setMapBounds(final int mapBoundMinX, final int mapBoundMaxX, final int mapBoundMinY,
            final int mapBoundMaxY) {
        this.mapBoundMinX = mapBoundMinX;
        this.mapBoundMaxX = mapBoundMaxX;
        this.mapBoundMinY = mapBoundMinY;
        this.mapBoundMaxY = mapBoundMaxY;
    }

    /**
     * Sets all tiles which the player can step on.
     */
    protected void setPlayableMap() {
        for (int x = this.mapBoundMinX; x <= this.mapBoundMaxX; x++) {
            for (int y = this.mapBoundMinY; y <= this.mapBoundMaxY; y++) {
                this.playableMap.add(new Point(x, y));
            }
        }
        this.playableMap.removeAll(this.obstacle.getObstaclePositions().keySet());
    }

    @Override
    public final List<Point> getPlayableMap() {
        return this.playableMap;
    }

    @Override
    public final void printPlayableMap() {
        System.out.println(this.playableMap.toString());
    }

    /**
     * Setter of the player's starting and ending positions.
     * 
     * @param charStartCol
     *            starting column
     * @param charStartRow
     *            starting row
     * @param charFinishCol
     *            ending column
     * @param charFinishRow
     *            ending row
     */
    protected void setCharStartFinish(final int charStartCol, final int charStartRow, final int charFinishCol,
            final int charFinishRow) {
        this.charStartCol = charStartCol;
        this.charStartRow = charStartRow;
        this.charFinishCol = charFinishCol;
        this.charFinishRow = charFinishRow;
        this.endPosition = new Point(this.charFinishCol, this.charFinishRow);
    }

    @Override
    public final Point getEndPosition() {
        return this.endPosition;
    }

    @Override
    public final int getMapBoundMaxX() {
        return this.mapBoundMaxX;
    }

    @Override
    public final int getMapBoundMinX() {
        return this.mapBoundMinX;
    }

    @Override
    public final int getMapBoundMaxY() {
        return this.mapBoundMaxY;
    }

    @Override
    public final int getMapBoundMinY() {
        return this.mapBoundMinY;
    }

    @Override
    public final int getCharStartCol() {
        return this.charStartCol;
    }

    @Override
    public final int getCharStartRow() {
        return this.charStartRow;
    }

    @Override
    public final int getCharFinishCol() {
        return this.charFinishCol;
    }

    @Override
    public final int getCharFinishRow() {
        return this.charFinishRow;
    }

    @Override
    public final String getStepSoundPath() {
        return this.stepSoundPath;
    }

    @Override
    public final void setStepSoundPath(final String stepSoundPath) {
        this.stepSoundPath = stepSoundPath;
    }

    @Override
    public final String getMusicPath() {
        return this.musicPath;
    }

    @Override
    public final void setMusicPath(final String musicPath) {
        this.musicPath = musicPath;
    }

    @Override
    public final void playMusic() {
        this.soundManager.startMusic(this.musicPath, true);
    }

    /**
     * Handles player's input.
     */
    protected void inputEvent() {
        this.playerInput = PlayerInputImpl.get();

        this.getGameScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                playerInput.keyPressed(event);
            }
        });

        this.getGameScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                playerInput.keyReleased(event);
            }
        });
    }
}
