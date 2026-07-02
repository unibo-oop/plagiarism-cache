package it.unibo.monoopoly.controller.data.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.monoopoly.model.gameboard.api.Buildable;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * DTO for view update.
 * 
 * @param playerPositions     map of name of {@link Player} in game and its
 *                            position
 *                            as a number
 * @param cellsOwners         map of {@link Buyable} cells expressed as a number
 *                            and
 *                            the name of its owner
 * @param nBuiltHouses        map of {@link Buildable} cell expressed as a
 *                            number
 *                            and the number of houses built in it
 * @param prisonedPlayers     list of the name of the {@link Player}s in prison
 * @param mortgagedProperties list of the {@link Cell}s mortgaged.
 * @param playersMoney        the actual money amount of the {@link Player}s in
 *                            game
 * @param actualPlayer        the name of the actual {@link Player}
 */
public record ViewUpdateDTO(
        Map<String, Integer> playerPositions,
        Map<Integer, Optional<String>> cellsOwners,
        Map<Integer, Integer> nBuiltHouses,
        List<String> prisonedPlayers,
        List<Integer> mortgagedProperties,
        Map<String, Integer> playersMoney,
        String actualPlayer) {

    /**
     * Explicit constructor to ensure unmodifiable collections are returned.
     * 
     * @param playerPositions     map of name of {@link Player} in game and its
     *                            position as a number
     * @param cellsOwners         map of {@link Buyable} cells expressed as a number
     *                            and
     *                            the name of its owner
     * @param nBuiltHouses        map of {@link Buildable} cell expressed as a
     *                            number
     *                            and the number of houses built in it
     * @param prisonedPlayers     list of the name of the {@link Player}s in prison
     * @param mortgagedProperties list of the {@link Cell}s mortgaged.
     * @param playersMoney        the actual money amount of the {@link Player}s in
     *                            game
     * @param actualPlayer        the name of the actual {@link Player}
     */
    public ViewUpdateDTO(final Map<String, Integer> playerPositions,
            final Map<Integer, Optional<String>> cellsOwners,
            final Map<Integer, Integer> nBuiltHouses,
            final List<String> prisonedPlayers,
            final List<Integer> mortgagedProperties,
            final Map<String, Integer> playersMoney,
            final String actualPlayer) {
        this.playerPositions = Collections.unmodifiableMap(playerPositions);
        this.cellsOwners = Collections.unmodifiableMap(cellsOwners);
        this.nBuiltHouses = Collections.unmodifiableMap(nBuiltHouses);
        this.prisonedPlayers = Collections.unmodifiableList(prisonedPlayers);
        this.mortgagedProperties = Collections.unmodifiableList(mortgagedProperties);
        this.playersMoney = Collections.unmodifiableMap(playersMoney);
        this.actualPlayer = actualPlayer;
    }
}
