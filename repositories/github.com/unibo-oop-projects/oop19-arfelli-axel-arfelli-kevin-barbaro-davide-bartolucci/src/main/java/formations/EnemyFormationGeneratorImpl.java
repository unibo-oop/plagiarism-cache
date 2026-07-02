package formations;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/*import gameengine.GameLogger;
import gameengine.GameLoggerAdaptor;
import gameengine.GameLogger.OutputLevel;*/

/**
 * An implementation of the EnemyFormationGenerator interface.
 * This class is able to generate random positions for each
 * enemy, as well as randomly choosing an enemy type between the 3 available.
 * The randomly generated coordinates are then converted in coordinates
 * relative to the VirtualMap's size and put in an map, used to create
 * an EnemyFormation object.
 */
public class EnemyFormationGeneratorImpl implements EnemyFormationGenerator {

    /**
     * The number of rows in the formation grid.
     */
    private static final Integer NUM_ROWS = 5;
    /**
     * The number of columns in the formation grid.
     */
    private static final Integer NUM_COLUMNS = 5;
    /**
     * The spawn chance of a Drone-type enemy.
     */
    private static final Integer SPAWN_CHANCE_DRONE = 45;
    /**
     * The spawn chance of a Turret-type enemy.
     */
    private static final Integer SPAWN_CHANCE_TURRET = 35;
    /**
     * The height of a box that makes up the formation grid.
     */
    private final int boxHeight;
    /**
     * The width of a box that makes up the formation grid.
     */
    private final int boxWidth;

    /**
     * An instance of GameLogger, used for logging and debug purposes.
     */
    //private final GameLogger logger = new GameLoggerAdaptor();

    /**
     * This constructor needs the VirtualMap width and height to set
     * up the class parameters and calculate the enemies positions 
     * relative to the VirtualMap.
     * @param width the VirtualMap width
     * @param height the VirtualMap height
     */
    public EnemyFormationGeneratorImpl(final Integer width, final Integer height) {
        this.boxWidth = width / NUM_COLUMNS;
        this.boxHeight = height / NUM_ROWS;
    }

    @Override
    public final EnemyFormation getEnemyFormation(final Integer quantity) {
        final Map<Pair<Integer, Integer>, EnemyType> enemyMap = new HashMap<>();
        final List<Pair<Integer, Integer>> positionList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Pair<Integer, Integer> coordinates;
            Pair<Integer, Integer> absolutePosition;
            coordinates = this.generateRandomPosition(positionList);
            absolutePosition = this.calculateAbsolutePosition(coordinates);
            positionList.add(coordinates);
            enemyMap.put(absolutePosition, this.generateEnemy());
        }
        return new EnemyFormationImpl(enemyMap);
    }

    @Override
    public final EnemyFormation getBossWave() {
        final Map<Pair<Integer, Integer>, EnemyType> enemyMap = new HashMap<>();
        final Pair<Integer, Integer> coordinates = new Pair<>(NUM_COLUMNS / 2, NUM_ROWS / 2);
        final Pair<Integer, Integer> absolutePosition = this.calculateAbsolutePosition(coordinates);
        enemyMap.put(absolutePosition, EnemyType.BOSS);
        return new EnemyFormationImpl(enemyMap);
    }

    /**
     * This method randomly picks an EnemyType out of the 3 available.
     * Each EnemyType has a different chance to be picked.
     * @return a randomly picked EnemyType.
     */
    private EnemyType generateEnemy() {
        final Random random = new Random();
        final int high = 100;
        final int low = 1;
        final Integer randomizedEnemyNumber = random.nextInt(high - low + 1) + low;
        EnemyType enemyType = null;
        if (randomizedEnemyNumber <= SPAWN_CHANCE_DRONE) {
            enemyType = EnemyType.DRONE;
        } else if (randomizedEnemyNumber <= SPAWN_CHANCE_DRONE + SPAWN_CHANCE_TURRET) {
            enemyType = EnemyType.TURRET;
        } else {
            enemyType = EnemyType.MUNCHER;
        }
        /*logger.logLine("Randomized enemy generated. It's a " + enemyType.toString() 
                            + ". RNG: " + randomizedEnemyNumber, OutputLevel.LOG);*/
        return enemyType;
    }

    /**
     * This method generates random positions.
     * If the generated position had already been generated previously 
     * (if it was in the positionList), it'll be discarded.
     * If the position is new, then it is returned
     * @param positionList the list of already picked positions.
     * @return a new randomly generated position.
     */
    private Pair<Integer, Integer> generateRandomPosition(final List<Pair<Integer, Integer>> positionList) {
        int randomizedX;
        int randomizedY;
        Random random = new Random();
        final int low = 0;
        //il fatto che highX = 5, rende il 5 escluso dal range di numeri che possono essere generati (da 0 a 4), quindi funziona
        final int highX = NUM_COLUMNS;
        final int highY = NUM_ROWS - 1;
        Pair<Integer, Integer> coordinates;
        do {
            random = new Random();
            randomizedX = random.nextInt(highX - low) + low;
            randomizedY = random.nextInt(highY - low) + low;
            coordinates = new Pair<>(randomizedX, randomizedY);
        } while (positionList.contains(coordinates));
        //logger.logLine("ITERATION. Random coordinates have been added: " + randomizedX + "; " + randomizedY, OutputLevel.LOG);
        return coordinates;
    }

    /**
     * This method is used to calculate the coordinates in relation
     * to the size of the VirtualMap.
     * @param relativePosition the relative position, based on a 5x4 grid.
     * @return the final coordinates.
     */
    private Pair<Integer, Integer> calculateAbsolutePosition(final Pair<Integer, Integer> relativePosition) {
        final Pair<Integer, Integer> boxCenter = getBoxCenter();
        final Integer absoluteX = relativePosition.getKey() * this.boxWidth + boxCenter.getKey();
        final Integer absoluteY = relativePosition.getValue() * this.boxHeight + boxCenter.getValue();
        return new Pair<>(absoluteX, absoluteY);
    }

    /**
     * This method is used to calculate the center's 
     * relative coordinates of a virtual grid box.
     * @return a Pair containing the X and Y coordinates, 
     * describing the center of the box.
     */
    private Pair<Integer, Integer> getBoxCenter() {
        return new Pair<>(this.boxWidth / 2, this.boxHeight / 2);
    }

}
