package it.unibo.isaccoop.controller.input;
import it.unibo.isaccoop.model.boundingbox.RectBoundingBox;
import it.unibo.isaccoop.model.common.Direction;
import it.unibo.isaccoop.model.player.Player;
import it.unibo.isaccoop.model.room.Room;

/**
 * PlayerInputComponent class to update player movement state.
 */
public final class PlayerInputComponent implements InputComponent {

    private final RectBoundingBox roomBoundingBox;

    /**
     * PlayerInputComponent Constructor.
     * @param room reference to Room
     */
    public PlayerInputComponent(final Room room) {
        this.roomBoundingBox = (RectBoundingBox) room.getBox();
    }

    @Override
    public void update(final Player player) {
        final InputController ctrl = player.getMovementController();

        if (ctrl.isUp() && !player.getBox()
                .isCollidingWithRecPerimeter(player.getMovePreview(Direction.UP), this.roomBoundingBox)) {
            player.update(Direction.UP);
        } else if (ctrl.isDown() && !player.getBox()
                .isCollidingWithRecPerimeter(player.getMovePreview(Direction.DOWN), this.roomBoundingBox)) {
            player.update(Direction.DOWN);
        } else if (ctrl.isLeft() && !player.getBox()
                .isCollidingWithRecPerimeter(player.getMovePreview(Direction.LEFT), this.roomBoundingBox)) {
            player.update(Direction.LEFT);
        } else if (ctrl.isRight() && !player.getBox()
                .isCollidingWithRecPerimeter(player.getMovePreview(Direction.RIGHT), this.roomBoundingBox)) {
            player.update(Direction.RIGHT);
        }
    }

}
