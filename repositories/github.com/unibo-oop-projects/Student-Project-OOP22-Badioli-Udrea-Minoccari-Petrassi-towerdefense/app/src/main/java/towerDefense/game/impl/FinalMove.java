package towerDefense.game.impl;

import java.util.Timer;
import java.util.TimerTask;
import towerDefense.entities.impl.TowerSingleton;

public class FinalMove{

    private boolean isActive = false;
    private Timer timer = new Timer();
    private final int DELAY = 15000;

    /**
     * @return  the current state of the class, whether if it's active or not
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * On call switches isActive state to true for a fixed amount of time
     */
    public void trigger() {
        isActive = true;
        TowerSingleton.getInstance().removeMoney(80);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isActive = false;
            }    
        }, DELAY);    
    }
        
}
