package view;

import controller.GameStatus;
import controller.GameStatusImpl;
import javafx.animation.Animation;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.player.Player;
import model.player.PlayerImpl;
import utilities.Utilities;
import view.animations.SpriteAnimation;

/**
 * Class that manages player's graphic position and movement.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class PlayerGraphicImpl implements PlayerGraphic {

    private static final PlayerGraphicImpl SINGLETON = new PlayerGraphicImpl();
    private final GameStatus gameStatus;
    private final Player player;
    private final ImageView charFrames;
    private Animation character;

    /**
     * Constructor.
     */
    private PlayerGraphicImpl() {
        this.gameStatus = GameStatusImpl.get();
        this.player = PlayerImpl.get();
        this.charFrames = new ImageView(new Image("/charAnimationFrame/charMovement.png"));
    }

    @Override
    public void charUp() {
        this.character = new SpriteAnimation(this.getCharFrames(), Duration.millis(1), 0, 5, 6, 112, 288, 56, 96);
        this.character.play();
        this.character = new SpriteAnimation(this.getCharFrames(),
                Duration.millis(Utilities.PLAYER_ANIMATION_DURATION_Y), 1, 5, 6, 112, 288, 56, 96);
        this.character.play();
    }

    @Override
    public void charDown() {
        this.character = new SpriteAnimation(this.getCharFrames(), Duration.millis(1), 0, 5, 6, 112, 0, 56, 96);
        this.character.play();
        this.character = new SpriteAnimation(this.getCharFrames(),
                Duration.millis(Utilities.PLAYER_ANIMATION_DURATION_Y), 1, 5, 6, 112, 0, 56, 96);
        this.character.play();
    }

    @Override
    public void charLeft() {
        this.character = new SpriteAnimation(this.getCharFrames(), Duration.millis(1), 0, 4, 6, 112, 96, 56, 96);
        this.character.play();
        this.character = new SpriteAnimation(this.getCharFrames(),
                Duration.millis(Utilities.PLAYER_ANIMATION_DURATION_X), 1, 4, 6, 112, 96, 56, 96);
        this.character.play();
    }

    @Override
    public void charRight() {
        this.character = new SpriteAnimation(this.getCharFrames(), Duration.millis(1), 0, 4, 6, 112, 192, 56, 96);
        this.character.play();
        this.character = new SpriteAnimation(this.getCharFrames(),
                Duration.millis(Utilities.PLAYER_ANIMATION_DURATION_X), 1, 4, 6, 112, 192, 56, 96);
        this.character.play();
    }

    @Override
    public void playerSetup(final int column, final int row, final GridPane grid) {
        this.charFrames.setFitWidth(Utilities.W / 21.5);
        this.charFrames.setFitHeight(Utilities.H / 12);

        if (this.gameStatus.isGamePause()) {
            grid.add(this.charFrames, this.player.getPlayerPosition().x, this.player.getPlayerPosition().y);
        } else {
            grid.add(this.charFrames, column, row);
        }

        GridPane.setValignment(this.charFrames, VPos.BOTTOM);

        this.player.setPlayerPosition(GridPane.getColumnIndex(this.charFrames), GridPane.getRowIndex(this.charFrames));

        this.character = new SpriteAnimation(this.charFrames, Duration.millis(1), 0, 5, 6, 112, 0, 56, 96);
        this.character.play();
    }

    @Override
    public void stopAnimation() {
        this.character.stop();
    }

    @Override
    public ImageView getCharFrames() {
        return this.charFrames;
    }

    /**
     * Returns an instance of PlayerGraphicImpl.
     * 
     * @return an instance of PlayerGraphicImpl
     */
    public static PlayerGraphicImpl get() {
        return SINGLETON;
    }
}
