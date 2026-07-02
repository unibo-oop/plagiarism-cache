package zombieversity.controller.entities;

import java.util.Optional;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Pair;
import zombieversity.controller.core.GameWorld;
import zombieversity.controller.input.InputHandler;
import zombieversity.controller.input.InputHandler.MouseInput;
import zombieversity.model.entities.AbstractActiveLivingEntity;
import zombieversity.model.entities.Player;
import zombieversity.model.entities.PlayerImpl;
import zombieversity.view.entities.PlayerView;
import zombieversity.view.entities.PlayerViewImpl;

/**
 * 
 * The implementation of {@link PlayerController}.
 *
 */
public class PlayerControllerImpl implements PlayerController {

    private final Player player;
    private final PlayerView playerView;
    private final ImageView playerImage;
    private final GameWorld gameWorld;
    private final InputHandler input;

    /**
     * 
     * Construct a {@link PlayerController}.
     * 
     * @param gameWorld the gameWorld.
     */
    public PlayerControllerImpl(final GameWorld gameWorld) {

        this.playerView = new PlayerViewImpl();
        this.player = new PlayerImpl();
        this.playerImage = this.playerView.getImageView();
        this.input = gameWorld.getInputHandler();
        this.gameWorld = gameWorld;
        this.initBoundingBox();
    }

    /**
     * Used to set player bouningbox.
     */
    private void initBoundingBox() {
        this.player.setBBox(this.playerImage.getFitWidth(), this.playerImage.getFitHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateInput(final Point2D offset) {
        keyBoardInput();
        mouseInput(offset);
        mouseClicked();
    }

    /**
     * Used to calcute player velocity based on keyboard input.
     */
    private void keyBoardInput() {
        final Set<KeyCode> keys = this.input.getKeyPressed();
        if (!keys.isEmpty()) {
            this.player.checkCollision(PlayerKeyboardInputUtils.calculateVelocity(keys));
        } else {
            this.player.setVelocity(Point2D.ZERO);
        }
    }

    /**
     * Used to compute player direction based on mouse position.
     * 
     * @param offset.
     */
    private void mouseInput(final Point2D offset) {
        final Point2D mousePosition = this.input.getMousePosition();
        if (mousePosition != null) {
            this.player.computeAngle(mousePosition, offset);
        }
    }

    /**
     * 
     */
    private void mouseClicked() {
        final Optional<Pair<Point2D, MouseInput>> click = this.input.getMouseClicked();
        if (!click.isEmpty()) {
            final Point2D camOffset = this.gameWorld.getCamera().getOffset();
            if (click.get().getValue().equals(MouseInput.PRIMARY)) {
                this.gameWorld.getAttackController()
                        .addAttack(this.player.getFirstWeapon().attack(click.get().getKey().add(camOffset)));
            } else {
                this.gameWorld.getAttackController()
                        .addAttack(this.player.getSecondWeapon().attack(click.get().getKey().add(camOffset)));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Player getEntity() {
        return this.player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final PlayerView getEntityView() {
        return this.playerView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        this.player.update();
        updateView();
    }

    /**
     * Used to update player position and direction.
     */
    private void updateView() {
        this.playerView.setPosition(this.player.getPosition());

        if (this.player instanceof AbstractActiveLivingEntity) {
            this.playerView.setDirection(((AbstractActiveLivingEntity) this.player).getDirection());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Image getView() {
        return this.playerView.getImage();
    }
}
