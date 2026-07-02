package it.unibo.monoopoly.view.position.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.monoopoly.view.position.impl.NumberAndCirclePosition;
import it.unibo.monoopoly.view.position.impl.Position;

/**
 * It is used to create a class that takes lists of player {@link Position},
 * cells, prison and houses and creates a list of objects that will be displayed
 * on the gameBoard.
 */
public interface PositionAllocator {

    /**
     * The method creates a single list for everything that needs to appear on the game board.
     * 
     * @param newPlayersPositions the position index updated of all players who have not yet gone bankrupt
     * @param cellsOwners for each purchasable cell, associate, if it exists, the name of the owner
     * @param nBuiltHouses for each building cells with owner associate the number of houses built
     * @param prisonedPlayers list of possible player in prison
     * @param mortgagedProperties list of mortgaged cells
     * @return the list of all position to be displayed in gameBoard.
     */
    List<NumberAndCirclePosition> createListCircleNumberPosition(Map<String, Integer> newPlayersPositions,
            Map<Integer, Optional<String>> cellsOwners,
            List<String> prisonedPlayers,
            Map<Integer, Integer> nBuiltHouses,
            List<Integer> mortgagedProperties);
}
