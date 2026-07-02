package model.gamemap;

import javafx.util.Pair;
import model.construction.Construction;

import java.util.Map;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * This class is used to create and handle the GameMap.
 */
public class GameMapImpl implements GameMap {

    private final Map<Pair<Integer, Integer>, Construction> planGame;

    /**
     * Constructor of the GameMap.
     */
    public GameMapImpl() {
        this.planGame = new LinkedHashMap<Pair<Integer, Integer>, Construction>();
    }

    /*
     * (non-Javadoc)
     * @see model.gamemap.GameMap#getMap()
     */
    @Override
    public Map<Pair<Integer, Integer>, Construction> getMap() {
        return Collections.unmodifiableMap(this.planGame);
    }

    /*
     * (non-Javadoc)
     * @see model.gamemap.GameMap#addBuilding(model.building.Construction)
     */
    @Override
    public void addBuilding(final Construction build) {
        if (isBuilding(build.getPosition())) {
            this.planGame.put(build.getPosition(), build);
        }
    }

    /*
     * (non-Javadoc)
     * @see model.gamemap.GameMap#deleteBuilding(model.building.Construction)
     */
    @Override
    public void deleteBuilding(final Construction build) {
        this.planGame.remove(build.getPosition());
    }

    /*
     * (non-Javadoc)
     * @see model.gamemap.GameMap#isBuilding(javafx.util.Pair)
     */
    @Override
    public boolean isBuilding(final Pair<Integer, Integer> position) {
        return !this.planGame.containsKey(position);
    }

    /*
     * (non-Javadoc)
     * @see model.gamemap.GameMap#getBuilding(javafx.util.Pair)
     */
    @Override
    public Construction getBuilding(final Pair<Integer, Integer> position) {
        return this.planGame.get(position);
    }
}
