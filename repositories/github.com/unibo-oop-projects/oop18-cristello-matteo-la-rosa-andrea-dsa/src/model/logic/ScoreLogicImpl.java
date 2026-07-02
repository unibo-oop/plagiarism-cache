package model.logic;

import java.util.List;

import model.board.GameBoard;
import model.board.Tile;
import model.board.TileType;
import model.players.Player;
import model.utils.Boat;

/**
 * Implementation of ScoreLogic.
 *
 */
public class ScoreLogicImpl implements ScoreLogic {

    @Override
    public final void playerPickUpTreasure(final GameBoard tileLine, final Player player) {
        tileLine.giveAndSubstituteTreasureTile(player);
    }

    @Override
    public final void playerReleaseTreasure(final GameBoard tileLine, final Player player, final Integer index) {
        final Tile tmp = tileLine.getTilesLine().get(tileLine.getPlayerIndex(player)).getTile();
        if (tmp.getType().equals(TileType.EMPTY.tileType())) {
            final Integer tileIndex = tileLine.getPlayerIndex(player);
            tileLine.replaceTile(tileIndex, player.chooseTileToRelease(index));
        } else {
            System.out.println("Error Can't release a treasure here"); // ECCEZIONE DA GESTIRE!!! con popup
        }

    }

    @Override
    public final void giveScoreToPlayersOnBoat(final Boat<Player> boat) {
        for (final Player player : boat) {
            for (final Tile t : player.getPlayerTreasures()) {
                player.setPlayerScore(player.getPlayerScore() + t.getValue());
            }
            player.resetTreasureCarried();
        }
    }

    @Override
    public final void removeAllTreasureToPlayerOnTile(final List<Player> playerList) {

        playerList.forEach(x -> x.resetTreasureCarried());

    }
}
