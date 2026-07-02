package model.lane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.mod.ModAlreadyPresentException;
import model.mod.ModObstacle;
import model.obstacle.GameObject;

/**
 * This class is a template that can handle Mods.
 * Only one mod per lane can be added.
 */
public abstract class AbstractLane implements Lane {

    private final List<ModObstacle> mods;
    private final LaneType laneType;

    /**
     * @param laneType is the type of the lane.
     */
    protected AbstractLane(final LaneType laneType) {
        this.laneType = laneType;
        mods = new ArrayList<>();
    }

    @Override
    public abstract void update();

    @Override
    public abstract List<GameObject> getObstacle();

    @Override
    public abstract double getSpeed();

    @Override
    public abstract void setSpeed(double newSpeed);

    /**
     * @return the type of the lane.
     */
    public LaneType getLaneType() {
        return this.laneType;
    }

    /**
     * @return a list of present mods in the lane.
     */
    public List<ModObstacle> getMods() {
        return Collections.unmodifiableList(mods);
    }

    /**
     * Adds a mod to the lane.
     * @param m The mod to add.
     * @throws ModAlreadyPresentException 
     */
    public void addMod(final ModObstacle m) throws ModAlreadyPresentException {
        if (this.mods.isEmpty()) {
            this.mods.add(m);
        } else {
            throw new ModAlreadyPresentException();
        }
    }

    /**
     * Removes a mod from the lane.
     * @param m The mod to remove.
     */
    public void removeMod(final ModObstacle m) {
        if (this.mods.contains(m)) {
            this.mods.remove(m);
        } else {
            throw new IllegalStateException();
        }
    }

}
