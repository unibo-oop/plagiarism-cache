package zombietsunami.model.mapmodel.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;

import zombietsunami.Pair;
import zombietsunami.model.MapData;
import zombietsunami.model.mapmodel.api.GameMap;
import zombietsunami.model.mapmodel.api.MapPosList;
import zombietsunami.model.mapmodel.api.TileElement;

/**
 * This class implements the GameMap interface
 * {@link zombietsunami.model.mapmodel.api.GameMap}.
 */
public final class GameMapImpl implements GameMap {

    private static final String SEP = "/";
    private static final String MAP1 = "maps/map01.txt";
    private static final String OBST = "obstaclemap/obstacleMap.txt";
    private static final String PRSN = "personmap/personsMap.txt";
    private static final String ROOT = SEP + "zombietsunami" + SEP;

    private static final String FILE_PATH_MAP = ROOT + MAP1;
    private static final String FILE_PATH_OBST = ROOT + OBST;
    private static final String FILE_PATH_PRSN = ROOT + PRSN;

    private final List<Integer> mapOfNumberTile;
    private final List<Integer> mapOfNumberObstacle;
    private final List<Integer> mapOfNumberPersons;

    private final MapPosList mapPos;
    private final TileElement tileElement;

    /**
     * Assigns the List of Integers with the map values at a new List.
     */
    public GameMapImpl() {
        this.mapPos = new MapPosListImpl();
        this.tileElement = new TileElementImpl();

        this.mapOfNumberTile = loadMap(FILE_PATH_MAP);
        this.mapOfNumberObstacle = loadMap(FILE_PATH_OBST);
        this.mapOfNumberPersons = loadMap(FILE_PATH_PRSN);
    }

    /**
     * This method reads the map's txt file and take all the numbers written into it
     * to then register them firstly into a List of integer's List (like a matrix),
     * then transform it into a List of Integers.
     * 
     * @param filePath is the path where to catch the txt file where the map is
     *                 written
     * @return a List of Integers with all the item's numbers of the map
     */
    private List<Integer> loadMap(final String filePath) {
        final List<List<Integer>> mapTileNum = new ArrayList<>();
        final Logger logger = Logger.getLogger(GameMapImpl.class.getName());
        try (InputStream is = getClass().getResourceAsStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {

            for (int row = 0; row < MapData.getMaxWorldRow(); row++) {
                final String line = br.readLine();
                final List<Integer> list = new ArrayList<>();

                for (int col = 0; col < MapData.getMaxWorldCol(); col++) {
                    final List<String> numbers = List.of(line.split(" "));
                    list.add(Integer.parseInt(numbers.get(col)));
                }

                mapTileNum.add(row, list);
            }

        } catch (IOException e) {
            logger.severe("Error reading the input file: " + e.getMessage());
        }

        return mapTileNum.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getLoadedMapList() {
        return new ArrayList<>(this.mapOfNumberTile);
    }

    @Override
    public List<Integer> getLoadedObstacleList() {
        return new ArrayList<>(this.mapOfNumberObstacle);
    }

    @Override
    public void removeObstacleListItem(final int index) {
        this.mapOfNumberObstacle.set(index, 0);
    }

    @Override
    public List<Integer> getLoadedPersonList() {
        return new ArrayList<>(this.mapOfNumberPersons);
    }

    @Override
    public void removePersonListItem(final int index) {
        this.mapOfNumberPersons.set(index, 0);
    }

    @Override
    public List<Pair<Integer, Integer>> getScreenTilePos(final int maxWorldRow, final int maxWorldCol,
            final int titleSize, final int zombieWorldX, final int zombieWorldY, final int zombieScreenX,
            final int zombieScreenY) {
        return this.mapPos.getScreenTilePosition(maxWorldRow, maxWorldCol, titleSize, zombieWorldX, zombieWorldY,
                zombieScreenX, zombieScreenY);
    }

    @Override
    public List<String> getTileElem() {
        return this.tileElement.getTileElement();
    }
}
