package it.unibo.isaccoop.controller.input;

import it.unibo.isaccoop.model.common.Direction;
import it.unibo.isaccoop.model.player.Player;

/**
 * ShotInputComponent class to update player shoot state.
 */
public final class ShotInputComponent implements InputComponent {

    @Override
    public void update(final Player player) {
        final InputController ctrl = player.getShootingController();

        if (ctrl.isUp()) {
            player.hit(Direction.UP);
        } else if (ctrl.isDown()) {
            player.hit(Direction.DOWN);
        } else if (ctrl.isLeft()) {
            player.hit(Direction.LEFT);
        } else if (ctrl.isRight()) {
            player.hit(Direction.RIGHT);
        }
    }

}
