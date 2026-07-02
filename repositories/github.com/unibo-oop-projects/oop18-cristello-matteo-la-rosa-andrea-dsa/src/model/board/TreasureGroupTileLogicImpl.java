
package model.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import model.players.Player;

/**
 * This is a TreasureGroupLogic implementation.
 */
public class TreasureGroupTileLogicImpl implements TreasureGroupTileLogic {

    /**
     * The group of threasure should be made from three fallen tiles.
     */
    private static final int TREASUREGROUP_SIZE = 3;
    /**
     * The second tile to count. 
     */
    private static final int FIRST = 1;
    /**
     * The third tile to count.
     */
    private static final int SECOND = 2;
    /**
     * List of treasureGroupTile of the player.
     */
    private final List<Tile> treasureGroupTileList;

    /**
     * @param playersLosingTreasureList
     *                                      The list of players that should loose their threasure.
     */
    public TreasureGroupTileLogicImpl(final List<Player> playersLosingTreasureList) {
        this.treasureGroupTileList = this.manageFallenTreasure(playersLosingTreasureList);
    }

    /**
     * @param playersLosingTreasureList
     *                                      The list of players that should lose their threasure.
     * @return the list of treasuretile grouped three by three 
     */
    private List<Tile> manageFallenTreasure(final List<Player> playersLosingTreasureList) {

        final List<Tile> treasureDistinctFallingList = new ArrayList<Tile>();
        Map<Integer, List<Tile>> treasureGroupListed = new HashMap<Integer, List<Tile>>();
        final List<Tile> treasureOfGroupTile = new ArrayList<Tile>();
        final AtomicInteger counter = new AtomicInteger(0);
        int i = 0;
        playersLosingTreasureList.stream().forEach(x -> treasureDistinctFallingList.addAll(x.getPlayerTreasures()));
        final Integer emptyPlacesToFill = TREASUREGROUP_SIZE - treasureDistinctFallingList.size() % TREASUREGROUP_SIZE;

        if (emptyPlacesToFill < TREASUREGROUP_SIZE) {
            for (i = 0; i < emptyPlacesToFill; i++) {
                treasureDistinctFallingList.add(new EmptyTile());
            }
        }

        treasureGroupListed = treasureDistinctFallingList.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / TREASUREGROUP_SIZE));

        treasureGroupListed.entrySet().stream()
                .forEach(x -> treasureOfGroupTile.add(new TreasureGroupTile(x.getValue().get(0).getValue(),
                        x.getValue().get(FIRST).getValue(), x.getValue().get(SECOND).getValue())));

        return treasureOfGroupTile;
    }

    @Override
    public final List<Tile> getFallenTreasureGroupedList() {

        return this.treasureGroupTileList;

    }

}
