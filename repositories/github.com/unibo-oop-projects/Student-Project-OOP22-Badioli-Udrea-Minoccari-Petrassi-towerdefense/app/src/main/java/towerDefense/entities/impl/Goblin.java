package towerDefense.entities.impl;

import java.awt.Point;

import towerDefense.Constants;
import towerDefense.entities.api.MovingEntity;

public class Goblin extends MovingEntity{

    /**
     * Creates a new Goblin entity
     */
    public Goblin() {
        super(new Point((int)Constants.width ,540), -2 , 2500, 8,Constants.goblin, 0);
    }

}
