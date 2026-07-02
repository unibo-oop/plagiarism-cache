package model.logic;

import java.util.ArrayList;
import java.util.List;

import model.board.GameBoard;
import model.board.TileType;
import model.players.Player;
import model.utils.Direction;
import model.utils.PossibleActionAfterMove;

/**
 * This class implements PlayerOptions.
 */
public class PlayerOptionsImpl implements PlayerOptions {

    @Override
    public final List<String> whereCanIMove(final Player player, final GameBoard tileLine,
            final MovementLogic movementLogic) {
        final List<String> possibleMoveDirection = new ArrayList<String>();

        if (tileLine.isPlayerOnBoard(player) && player.getDirection().equals(Direction.DEEP)
                && movementLogic.canIGoDeepFromHere(tileLine, tileLine.getPlayerIndex(player))) {
            possibleMoveDirection.add(Direction.DEEP.toString());
            possibleMoveDirection.add(Direction.TO_BOAT.toString());
            return possibleMoveDirection;
        } else if (tileLine.isPlayerOnBoard(player) && player.getDirection().equals(Direction.DEEP)
                && !movementLogic.canIGoDeepFromHere(tileLine, tileLine.getPlayerIndex(player))) {
            possibleMoveDirection.add(Direction.TO_BOAT.toString());
            return possibleMoveDirection;
        } else if (tileLine.isPlayerOnBoard(player) && player.getDirection().equals(Direction.TO_BOAT)) {
            possibleMoveDirection.add(Direction.TO_BOAT.toString());
            return possibleMoveDirection;
        } else if (!tileLine.isPlayerOnBoard(player) && player.getDirection().equals(Direction.DEEP)) {
            possibleMoveDirection.add(Direction.DEEP.toString());
            return possibleMoveDirection;
        } else if (!tileLine.isPlayerOnBoard(player) && player.getDirection().equals(Direction.TO_BOAT)) {
            possibleMoveDirection.add(Direction.NOWHERE.toString());
            return possibleMoveDirection;
        }
        possibleMoveDirection.add(Direction.NOWHERE.toString()); //
        return possibleMoveDirection;
    }

    @Override
    public final List<String> whatICanDoAfterMovement(final Player player, final GameBoard tileLine) {
        final List<String> possibleActionsAfterMove = new ArrayList<String>();
        if (tileLine.isPlayerOnBoard(player)) {
            if (!tileLine.getTilesLine().get(tileLine.getPlayerIndex(player)).getTile().getType()
                    .equals(TileType.EMPTY.tileType())) {
                possibleActionsAfterMove.add(PossibleActionAfterMove.PICK_UP.toString());
                possibleActionsAfterMove.add(PossibleActionAfterMove.PASS_TURN.toString());
                return possibleActionsAfterMove;
            } else if (tileLine.getTilesLine().get(tileLine.getPlayerIndex(player)).getTile().getType()
                    .equals(TileType.EMPTY.tileType())
                    && !player.getPlayerTreasures().isEmpty()) {
                possibleActionsAfterMove.add(PossibleActionAfterMove.RELEASE.toString());
                possibleActionsAfterMove.add(PossibleActionAfterMove.PASS_TURN.toString());
                return possibleActionsAfterMove;
            } else if (tileLine.getTilesLine().get(tileLine.getPlayerIndex(player)).getTile().getType()
                    .equals(TileType.EMPTY.tileType())
                    && player.getPlayerTreasures().isEmpty()) {
                possibleActionsAfterMove.add(PossibleActionAfterMove.PASS_TURN.toString());
                return possibleActionsAfterMove;
            }
        } else {
            possibleActionsAfterMove.add(PossibleActionAfterMove.PASS_TURN.toString());
            return possibleActionsAfterMove;
        }
        return null;
    }

    @Override
    public final String toString() {

        return "I am PlayerOptionsImplementation of Deep Sea Adventure.";
    }

}
