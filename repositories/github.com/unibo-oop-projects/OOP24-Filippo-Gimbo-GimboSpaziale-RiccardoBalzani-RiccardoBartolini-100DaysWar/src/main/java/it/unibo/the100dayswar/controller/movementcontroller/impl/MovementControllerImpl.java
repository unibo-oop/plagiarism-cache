package it.unibo.the100dayswar.controller.movementcontroller.impl;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.commons.utilities.impl.Direction;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.controller.movementcontroller.api.MovementController;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * The implementation of the movement controller of the game.
 */
public class MovementControllerImpl implements MovementController {
    /** 
     * {@inheritDoc}
     */
    @Override
    public void moveUp() {
        move(Direction.UP);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void moveDown() {
        move(Direction.DOWN);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void moveLeft() {
        move(Direction.LEFT);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void moveRight() {
        move(Direction.RIGHT);
    }
    /**
     * Moves the selected soldier in the specified direction.
     * @param direction the direction in which the soldier should move
     */
    private void move(final Direction direction) {
        final Pair<Unit, Cell> selectedCell = The100DaysWar.CONTROLLER.getMapController().getSelectedCell();
        final Unit unit = selectedCell.getFirst();
        final Cell currentCell = selectedCell.getSecond();

        if (unit instanceof Movable) {
            final Position currentPosition = unit.getPosition().getPosition();
            final Position targetPosition = new PositionImpl(
                currentPosition.getX() + direction.getDeltaX(),
                currentPosition.getY() + direction.getDeltaY()
            );
            ((Movable) unit).movementRequest(
                new CellImpl(
                    targetPosition,
                    currentCell.isBuildable(),
                    currentCell.isSpawn()
                ));
            The100DaysWar.CONTROLLER.getGameController().skip();
        }
    }
}
