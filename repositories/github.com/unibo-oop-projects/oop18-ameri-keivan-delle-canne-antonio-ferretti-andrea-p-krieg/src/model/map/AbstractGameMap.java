package model.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.util.Pair;
import model.objects.GameObject;
import model.objects.structures.Structure;
import model.objects.terrains.Terrain;
import model.objects.unit.Unit;
import util.Coordinates;

/**
 * the abstract class for the game map.
 * package protected.
 */
abstract class AbstractGameMap implements ModifiableGameMap {
    private final Map<Coordinates, Case> map;
    private final Pair<Integer, Integer> size;

    /**
     * creates a game map of size width*height.
     * 
     * @param size the size of the map will be size*size
     */
    AbstractGameMap(final Pair<Integer, Integer> size) {
        this.size = size;
        this.map = generateGameMap();
    }

    /**
     * 
     * @return generates the game map
     */
    protected abstract Map<Coordinates, Case> generateGameMap();

    /** {@inheritDoc} **/
    @Override
    public Pair<Integer, Integer> getMapSize() {
        return this.size;
    }

    /** {@inheritDoc} **/
    @Override

    public Optional<Unit> getUnit(final Coordinates cords) {
        return this.map.get(cords).getUnit();
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canStep(final Coordinates cords, final Unit unit) {
        return this.map.get(cords).canStep(unit);
    }

    /** {@inheritDoc} **/
    @Override
    public Terrain getTerrain(final Coordinates cords) {
        return this.map.get(cords).getTerrain();
    }

    /** {@inheritDoc} **/
    @Override
    public void setStructure(final Coordinates cords, final Structure structure) {
        runIllegalSateToIllegalArgument(() -> this.map.get(cords).setStructure(structure));
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Structure> getStructure(final Coordinates cords) {
        return this.map.get(cords).getStructure();
    }

    /** {@inheritDoc} **/
    @Override
    public void removeStructure(final Coordinates cords) {
        runIllegalSateToIllegalArgument(() -> this.map.get(cords).removeStructure());
    }

    /** {@inheritDoc} **/
    @Override
    public void setUnit(final Coordinates cords, final Unit unit) {
        runIllegalSateToIllegalArgument(() -> this.map.get(cords).setUnit(unit));
    }

    /** {@inheritDoc} **/
    @Override
    public void removeUnit(final Coordinates cords) {
        runIllegalSateToIllegalArgument(() -> this.map.get(cords).removeUnit());
    }

    /** {@inheritDoc} **/
    @Override
    public Map<Coordinates, List<GameObject>> toMap() {
        return this.map.entrySet().stream().flatMap(e -> {
            final List<Pair<Coordinates, GameObject>> result = new ArrayList<>();
            result.add(new Pair<Coordinates, GameObject>(e.getKey(), e.getValue().getTerrain()));
            if (e.getValue().getStructure().isPresent()) {
                result.add(new Pair<Coordinates, GameObject>(e.getKey(), e.getValue().getStructure().get()));
            }
            if (e.getValue().getUnit().isPresent()) {
                result.add(new Pair<Coordinates, GameObject>(e.getKey(), e.getValue().getUnit().get()));

            }
            return result.stream();
        }).collect(Collectors.groupingBy(e -> e.getKey(),
                Collector.of(() -> new ArrayList<>(), (r, p) -> r.add(p.getValue()), (r1, r2) -> {
                    r1.addAll(r2);
                    return r1;
                })));
    }

    /** {@inheritDoc} **/
    @Override
    public String toString() {
        return this.toMap().toString();
    }

    private void runIllegalSateToIllegalArgument(final Runnable action) {
        try {
            action.run();
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
