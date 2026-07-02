package towerDefense.entities.impl;

import java.awt.Point;

import towerDefense.Constants;
import towerDefense.entities.api.MovingEntity;

public class Knight extends MovingEntity{

    private static int cost = 60;

    /**
     * Creates a new Knight entity
     */
    public Knight() {
        super(new Point(50,500), 3, 2000, 30,Constants.knight, 60);
    }

    public static int getCost() {
        return cost;
    }
}
