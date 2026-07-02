package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import model.obstacle.GameObjectImpl.GameObjectType;
import model.world.World;
import model.world.WorldImpl;

/**
 * world creator.
 */
public class WorldCreatorFactoryImpl implements WorldCreatorFactory {

    private Random rand;
    private List<GameObjectType> riverObst;
    private List<GameObjectType> roadObst;

    /**
     * factory constructor.
     */
    public WorldCreatorFactoryImpl() {
        rand = new Random();
        riverObst = new ArrayList<>();
        roadObst = new ArrayList<>();
        riverObst.addAll(Arrays.asList(GameObjectType.BIG_LOG, GameObjectType.MEDIUM_LOG, GameObjectType.SMALL_LOG, GameObjectType.TURTLE));
        roadObst.addAll(Arrays.asList(GameObjectType.PURPLE_CAR, GameObjectType.RACE_CAR, GameObjectType.TRUCK, GameObjectType.WHITE_CAR));
    }

    /**
     * @return easy world.
     */
    public World createEasyWorld() {
        return new WorldImpl.Builder()
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addSafeLane()
                .addRiver(0, riverObst.get(rand.nextInt(riverObst.size())), this.getRandomInRange(2, 4))
                .addRiverWithTurtle(0, this.getRandomInRange(2, 4))
                .addRiver(0, riverObst.get(rand.nextInt(riverObst.size())), this.getRandomInRange(2, 4))
                .addRiverWithTurtle(0, this.getRandomInRange(2, 4))
                .addRiver(0, riverObst.get(rand.nextInt(riverObst.size())), this.getRandomInRange(2, 4))
                .build();
    }

    /**
     * @return normal world.
     */
    public World createNormalWorld() {
        return new WorldImpl.Builder()
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addSafeLane()
                .addRiver(0, riverObst.get(rand.nextInt(riverObst.size())), this.getRandomInRange(2, 4))
                .addRiverWithTurtle(0, this.getRandomInRange(2, 4))
                .addRiver(0, riverObst.get(rand.nextInt(riverObst.size())), this.getRandomInRange(2, 4))
                .addRiverWithTurtle(0, this.getRandomInRange(2, 4))
                .addRiver(0, riverObst.get(rand.nextInt(riverObst.size())), this.getRandomInRange(2, 4))
                .build();
    }
    /**
     * @return hard world.
     */
    public World createHardWorld() {
        return new WorldImpl.Builder()
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addStreet(0, roadObst.get(rand.nextInt(roadObst.size())), this.getRandomInRange(2, 4))
                .addSafeLane()
                .addRiver(0, riverObst.get(rand.nextInt(riverObst.size())), this.getRandomInRange(2, 4))
                .addRiverWithTurtle(0, this.getRandomInRange(2, 4))
                .addRiver(0, riverObst.get(rand.nextInt(riverObst.size())), this.getRandomInRange(2, 4))
                .addRiverWithTurtle(0, this.getRandomInRange(2, 4))
                .addRiver(0, riverObst.get(rand.nextInt(riverObst.size())), this.getRandomInRange(2, 4))
                .build();
    }

    /**
     * return a random number in range.
     * @param lower bound
     * @param upper bound
     * @return random in range
     */
    private int getRandomInRange(final int lower, final int upper) {
        return rand.nextInt(upper - lower) + lower;
    }
}
