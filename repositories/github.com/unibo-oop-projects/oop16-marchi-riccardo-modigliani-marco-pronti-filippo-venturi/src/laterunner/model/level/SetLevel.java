package laterunner.model.level;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import laterunner.model.vehicle.Vehicles;
import laterunner.physics.S2d;

/**
 * The class that set the levels' features.
 *
 */
public class SetLevel {

    private final CreateLevel creator = new CreateLevelImpl();
    private Level level = new LevelImpl();
    private int distance;
    private int numberCars;
    private S2d speed;
    private List<Vehicles> list = new LinkedList<>();
    private Random random = new Random();

    /**
     * 
     */
    public SetLevel() { };

    /**
     * Based on the level create the level.
     * 
     * @param levelNum
     *          the level to be played
     *
     * @return
     *          the level
     */
    public Level getLevel(final int levelNum) {
        switch(levelNum) {
        case 1:
                this.setLevelFeatures(300, 90, 50, levelNum);
                level = creator.generateLevel(list, speed, distance);
                return level;
        case 2:
                this.setLevelFeatures(350, 75, 70, levelNum);
                level = creator.generateLevel(list, speed, distance);
                return level;
        case 3:
                this.setLevelFeatures(400, 60, 90, levelNum);
                level = creator.generateLevel(list, speed, distance);
                return level;
        case 4:
                this.setLevelFeatures(450, 50, 110, levelNum);
                level = creator.generateLevel(list, speed, distance);
                return level;
        case 5:
                this.setLevelFeatures(500, 40, 120, levelNum);
                level = creator.generateLevel(list, speed, distance);
                return level;
        case 6:
                this.setLevelFeatures(550, 40, 130, levelNum);
                level = creator.generateLevel(list, speed, distance);
                return level;
        case 7:
                this.setLevelFeatures(600, 30, 140, levelNum);
                level = creator.generateLevel(list, speed, distance);
                return level;
        case 8:
                this.setLevelFeatures(650, 20, 150, levelNum);
                level = creator.generateLevel(list, speed, distance);
                return level;
        case 9:
                this.setLevelFeatures(700, 10, 175, levelNum);
                level = creator.generateLevel(list, speed, distance);
                return level;
        case 10:
                this.setLevelFeatures(800, 1, 200, levelNum);
                level = creator.generateLevel(list, speed, distance);
                return level;
        default: throw new IllegalStateException();
        }
    }

    private void setLevelFeatures(final int oSpeed, final int oDistance, final int nCars, final int levelNum) {
        this.speed = new S2d(0, oSpeed);
        this.distance = oDistance;
        this.numberCars = nCars;
        while (numberCars > 0) {
            list.add(selectVehicle(levelNum));
            numberCars--;
        }
    }

    private Vehicles selectVehicle(final int levelNum) {
        final double rateTruck = 0.03;
        final double rateCar = 0.1;
        double rand = this.random.nextDouble();
        Vehicles obstacle;

        if (this.random.nextDouble() <= rateTruck * levelNum) {
            obstacle = Vehicles.BUS;
        } else if (rand > rateTruck * levelNum && rand <= rateCar * levelNum) {
            obstacle = Vehicles.OBSTACLE_CAR;
        } else {
            obstacle = Vehicles.MOTORBIKE;
        }
        return obstacle;
    }

}
