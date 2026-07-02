package frogger.model.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.PickableObject;

/**
 * {@inheritDoc}.
 */
public class LevelImpl implements Level {

    private final List<Lane> lanes = new ArrayList<>();
    private final List<Eagle> eagles = new ArrayList<>();
    private final List<PickableObject> pickableObjects = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Lane> getLanes() {
        return new ArrayList<>(lanes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MovingObject> getAllObstacles() {
        return Stream.concat(lanes.stream().flatMap(elem -> elem.getLaneObstacles().stream()),
        this.eagles.stream()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLane(final Lane lane) {
        lanes.add(lane);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEagle(final Eagle eagle) {
        this.eagles.add(eagle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Eagle> getEagles() {
        return new ArrayList<>(this.eagles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PickableObject> getPickableObjects() {
        return new ArrayList<>(this.pickableObjects);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPickableObject(final PickableObject p) {
        this.pickableObjects.add(p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePickableObject(final PickableObject p) {
        this.pickableObjects.remove(p);
    }
}
