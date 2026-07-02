package controller;

import model.Enemy;
import model.EntityType;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates a number of enemies each time it is called, using an algorithm that always leave a free road
 *
 * @author Roberto Di Lena
 */
public class ControllerEnemies {

    private static List<Enemy> enemiesList = new ArrayList<>();
    private int n = 0;
    private static int nspawn = 0;
    private static double increase = 0;
    private static final int NEXT_LEVEL = 3;
    private static final double CarSpeed = 4;
    private static final double MotorBikeSpeed = 5;
    private static final double TruckSpeed = 3.5;


    /**
     * Algorithm for creating enemies
     *
     * @return enemiesList
     */
    public List<Enemy> spawnEnemies() {
        enemiesList.clear();
        for (int x = 1; x < 4; x++) {
            if ((Math.random() * 3) < 2) {
                if (n < 2) {
                    switch ((int) (Math.random() * 4)) {
                        case 1:
                            spawn(x, CarSpeed, EntityType.CAR, (int) ((Math.random() * 6) + 1));
                            break;
                        case 2:
                            if ((Math.random() * 10) < 6) {
                                spawn(x, MotorBikeSpeed, EntityType.MOTORBIKE, (int) ((Math.random() * 4) + 1));
                            } else {
                                spawn(x, CarSpeed, EntityType.CAR, (int) ((Math.random() * 6) + 1));
                            }
                            break;
                        case 3:
                            if ((Math.random() * 10) < 3) {
                                spawn(x, TruckSpeed, EntityType.TRUCK, (int) ((Math.random() * 3) + 1));
                            } else {
                                spawn(x, CarSpeed, EntityType.CAR, (int) ((Math.random() * 6) + 1));
                            }
                            break;
                    }
                }
            }
        }
        n = 0;
        nspawn++;
        if (nspawn == NEXT_LEVEL) {
            nspawn = 0;
            increase += 2;
        }
        return enemiesList;
    }

    /**
     * Add enemy to enemiesList
     *
     * @param x     X position of the enemy
     * @param speed Speed of the enemy
     * @param type  Type of the enemy
     * @param id    Id photo
     */
    private void spawn(int x, double speed, EntityType type, int id) {
        Enemy e = new Enemy(type, x * 100, speed + increase, id);
        enemiesList.add(e);
        n++;
    }

    /**
     * Start initial game
     */
    public void start() {
        n = 0;
        nspawn = 0;
        increase = 0;
    }
}
