package model.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.players.EmptyPlayerSingleton;
import model.players.Player;
import model.utils.TilePlayerPair;
import model.utils.TilePlayerPairImpl;

/**
 * This class implement a line of tiles and it's mechanism.
 */
public class GameBoardImpl extends LinkedHashMap<Integer, TilePlayerPair> implements GameBoard {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final int GROUPTILEDIVISION = 4;
    private final List<Integer> valueList = Collections.synchronizedList(new ArrayList<Integer>());
    private final List<Integer> valueListForTipe = Collections.synchronizedList(new ArrayList<Integer>());

    /**
     * 
     */
    public GameBoardImpl() {
        super();
        this.populateValue(GROUPTILEDIVISION);
        this.createBoard(this.valueList);
    }

    /**
     * This method create the right sequence of value to populate the board tiles, it also provide the randomization of
     * values. It's called at the creation of the boardImpl
     */
    private void populateValue(final Integer groupTilesDivision) {

        Integer index = null;
        Integer index2 = null;

        for (index = 0; index < groupTilesDivision; index++) {

            for (index2 = 0; index2 < groupTilesDivision; index2++) {

                this.valueListForTipe.add(index2 + groupTilesDivision * index);
                this.valueListForTipe.add(index2 + groupTilesDivision * index);

            }

            Collections.shuffle(this.valueListForTipe);
            this.valueList.addAll(this.valueListForTipe);
            this.valueListForTipe.clear();
        }

    }

    /**
     * This method create the board at the start of the game. A line of coordinate from 0 to the max number of tiles
     * that are populated of tiles with a specific (hide from player) value.
     */
    @Override
    public void createBoard(final List<Integer> valueList) {

        IntStream.range(1, valueList.size() + 1)
                .forEach(i -> {
                    this.put(i, new TilePlayerPairImpl(new TreasureTile(valueList.get(i - 1))));
                });

    }

    /**
     * This method recreate the board when is necessary removing all the tile that are of TileTipe.EmptyTile.
     */
    @Override
    public void recreateBoard(final Map<Integer, TilePlayerPair> linkedHash) {
        final LinkedHashMap<Integer, TilePlayerPair> linkedHashTmp = new LinkedHashMap<Integer, TilePlayerPair>();
        linkedHashTmp.putAll(linkedHash);
        this.keySet().clear();

        final List<Tile> tmpTiles = linkedHashTmp.values()
                .stream()
                .filter(x -> x.getTile().getType().equals(TileType.TREASURE.tileType())
                        || x.getTile().getType().equals(TileType.TREASUREGROUP.tileType()))
                .map(x -> x.getTile()).collect(Collectors.toList());

        IntStream.range(1, tmpTiles.size() + 1)
                .forEach(i -> {
                    this.put(i, new TilePlayerPairImpl(tmpTiles.get(i - 1)));
                });

    }

    @Override
    public final boolean isPlayerOnBoard(final Player player) {

        return this.values().stream().filter(x -> x.getPlayer().isPresent()).map(x -> x.getPlayer().get())
                .collect(Collectors.toList()).contains(player);
    }

    @Override
    public final Integer getPlayerIndex(final Player player) {

        if (this.isPlayerOnBoard(player)) {
            return this.entrySet().stream()
                    .filter(x -> x.getValue().getPlayer().orElse(EmptyPlayerSingleton.getInstance()).equals(player))
                    .findFirst().get().getKey();
        }
        return null;
    }

    /**
     * With this method a player can release a treasure where there is an EmptyTile.
     *
     * @param playerInTurn
     * @param board
     */
    @Override
    public Tile giveAndSubstituteTreasureTile(final Player player) {
        Tile tmp = null;

        tmp = this.get(this.getPlayerIndex(player)).getTile();
        player.pickUpTreasure(tmp);
        this.replaceTile(this.getPlayerIndex(player), new EmptyTile());

        return tmp;

    }

    @Override
    public final void replaceTile(final int index, final Tile tile) {
        this.get(index).setTile(tile);
    }

    @Override
    public final LinkedHashMap<Integer, TilePlayerPair> getTilesLine() {
        return this;
    }

}
