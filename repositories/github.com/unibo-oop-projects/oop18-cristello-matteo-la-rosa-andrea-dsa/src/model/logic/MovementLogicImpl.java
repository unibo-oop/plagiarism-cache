package model.logic;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import model.board.GameBoard;
import model.players.EmptyPlayerSingleton;
import model.players.Player;
import model.players.PlayerCircularQueue;
import model.utils.Boat;
import model.utils.Direction;

/**
 * Implementation of movement Logic.
 */
public class MovementLogicImpl implements MovementLogic {

    private Integer calculateArrivalPosition(final GameBoard board, final Integer startPosition,
            final Integer dicesValue, final Direction direction) {
        if (dicesValue.equals(0)) {
            return startPosition;
        }

        if (direction.equals(Direction.DEEP)) {
            return board.getTilesLine().entrySet().stream()
                    .filter(x -> x.getKey().intValue() >= startPosition.intValue())
                    .filter(x -> !x.getValue().getPlayer().isPresent()).limit(dicesValue)
                    .reduce((first, second) -> second)
                    .orElse(null).getKey().intValue();
        } else if (direction.equals(Direction.TO_BOAT)) {

            return board.getTilesLine().entrySet().stream()
                    .filter(x -> x.getKey().intValue() < startPosition.intValue())
                    .filter(x -> !x.getValue().getPlayer().isPresent()).map(x -> x.getKey())
                    .sorted(Comparator.reverseOrder()).limit(dicesValue).reduce((first, second) -> second)
                    .get().intValue();

        }

        return null;
    }

    private boolean isPlayerOnBoard(final GameBoard board, final Player player) throws NullPointerException {

        return board.getTilesLine()
                .values()
                .stream()
                .map(x -> x.getPlayer().orElse(EmptyPlayerSingleton.getInstance()))
                .collect(Collectors.toList())
                .contains((player));
    }

    private Integer playerOnBoardPosition(final GameBoard board, final Player player) throws NullPointerException {
        return board.getTilesLine()
                .entrySet()
                .stream()
                .filter(x -> x.getValue()
                        .getPlayer()
                        .equals(Optional.of(player)))
                .findFirst()
                .get()
                .getKey();
    }
    @Override
    public final Boolean canIGoDeepFromHere(final GameBoard board, final Integer position) {

        return board.getTilesLine().entrySet().stream().filter(x -> x.getKey().intValue() >= position.intValue())
                .anyMatch(x -> !x.getValue().getPlayer().isPresent());

    }
    @Override
    public final Boolean isThereSomeSpaceFromMeToBoat(final GameBoard board, final Integer position) {

        return board.getTilesLine().entrySet().stream().filter(x -> x.getKey().intValue() < position.intValue())
                .anyMatch(x -> !x.getValue().getPlayer().isPresent());

    }
    @Override
    public final void moveAllPlayerToBoat(final GameBoard board, final Boat<Player> boat) {
        boat.addAll(board.getTilesLine().entrySet().stream().filter(x -> x.getValue().getPlayer().isPresent())
                .map(x -> x.getValue().getPlayer().get()).collect(Collectors.toList()));
        board.getTilesLine().entrySet().stream().filter(x -> x.getValue().getPlayer().isPresent())
                .forEach(x -> x.getValue().setPlayer(null));
    }
    @Override
    public final void movePlayer(final GameBoard board, final Player player, final Integer dicesValue,
            final PlayerCircularQueue pCQ, final Boat<Player> boat) {

        if (!Optional.ofNullable(player).isPresent()) { 
            return;
        }
        if (player.getDirection().equals(Direction.DEEP)) {
            if (isPlayerOnBoard(board, player)) {

                final Integer startPosition = playerOnBoardPosition(board, player);
                if (canIGoDeepFromHere(board, startPosition)) {

                    final Integer arrivalPlace = calculateArrivalPosition(board, startPosition, dicesValue, player.getDirection());
                    board.getTilesLine().get(startPosition).setPlayer(null);
                    board.getTilesLine().get(arrivalPlace).setPlayer(player);
                } else {
                    player.changeDirectionToBoat();
                    movePlayer(board, player, dicesValue, pCQ, boat);
                }
            } else {

                final Integer startPosition = 0;
                if (this.canIGoDeepFromHere(board, startPosition)) {
                    final Integer arrivalPlace = calculateArrivalPosition(board, startPosition, dicesValue, player.getDirection());
                    board.getTilesLine().get(arrivalPlace).setPlayer(player);
                } else {

                    player.changeDirectionToBoat();
                    movePlayer(board, player, dicesValue, pCQ, boat);
                }
            }

        } else if (player.getDirection().equals(Direction.TO_BOAT)) {
            if (isPlayerOnBoard(board, player)) {
                final Integer startPosition = this.playerOnBoardPosition(board, player);
                if (this.isThereSomeSpaceFromMeToBoat(board, startPosition)) {
                    Integer arrivalPlace = this.calculateArrivalPosition(board, startPosition, dicesValue,
                            player.getDirection());
                    if ((arrivalPlace + dicesValue) > startPosition) {

                        boat.add(pCQ.removeFromQueue(player));
                        board.getTilesLine().get(startPosition).setPlayer(null);

                    } else {
                        arrivalPlace = this.calculateArrivalPosition(board, startPosition, dicesValue,
                                player.getDirection());
                        board.getTilesLine().get(startPosition).setPlayer(null);
                        board.getTilesLine().get(arrivalPlace).setPlayer(player);

                    }
                } else {

                    boat.add(pCQ.removeFromQueue(player));
                    board.getTilesLine().get(startPosition).setPlayer(null);

                }
            } else {
                    return;
            }
        }
    }

}
