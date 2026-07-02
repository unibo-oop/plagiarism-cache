package controller;


import model.EntityType;
import model.PowerUp;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * This class creates a number of PoweUP each time it is called
 * @author Federico Benzi
 */
public class ControllerPowerUp {
    private static PowerUp p;
    private static final double PowerUpSpeed = 2;
    private static List<PowerUp> powerUpList = new ArrayList<>();
    private static Random random = new Random();

    /**
     * Algorithm for creating PowerUP
     *
     * @return powerupList
     */
    public synchronized List<PowerUp> spawnPowerUp() {
        powerUpList.clear();

        if ((Math.random() * 2) < 1) {
            p = new PowerUp(EntityType.SHIELD, 100 * (random.nextInt(2) + 1), PowerUpSpeed, 1);
        } else {
            p = new PowerUp(EntityType.LIFE, 100 * (random.nextInt(2) + 1), PowerUpSpeed, 2);
        }
        powerUpList.add(p);
        return powerUpList;
    }

}
