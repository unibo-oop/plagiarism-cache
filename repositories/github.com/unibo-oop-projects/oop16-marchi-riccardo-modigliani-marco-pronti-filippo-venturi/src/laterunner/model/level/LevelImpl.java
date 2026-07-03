package laterunner.model.level;

import java.util.LinkedList;
import java.util.List;

import laterunner.model.vehicle.Obstacle;

/**
 * The class in witch is implemented the leves.
 *
 */
public class LevelImpl implements Level {

    private List<Obstacle> level = new LinkedList<>();

    /**
     * 
     */
    public LevelImpl() { };

    @Override
    public void setLevel(final List<Obstacle> list) {
        this.level.addAll(list);
    }

    @Override
    public List<Obstacle> getLevel() {
        return level;
    }

}
