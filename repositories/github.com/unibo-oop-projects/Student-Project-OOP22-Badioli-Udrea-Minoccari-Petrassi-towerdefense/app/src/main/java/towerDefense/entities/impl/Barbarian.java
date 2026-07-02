package towerDefense.entities.impl;
import java.awt.Point;

import towerDefense.entities.api.MovingEntity;
import towerDefense.Constants;

public class Barbarian extends MovingEntity{

    static int cost = 40;

    /**
     * Creates a new Barbarian entity
     */
    public Barbarian() {
        super(new Point(50,500), 1, 3000, 25, Constants.barbarian, cost);
    }

    /**
     * @return the cost of the unit
     */
    public static int getCost() {
        return cost;
    }

}
