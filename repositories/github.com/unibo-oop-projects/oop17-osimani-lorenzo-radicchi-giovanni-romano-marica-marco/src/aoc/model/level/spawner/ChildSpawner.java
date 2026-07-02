package aoc.model.level.spawner;

import aoc.controller.GameConstants;
import aoc.model.WorldConstants;
import aoc.model.entity.child.ChildFactory;
import aoc.model.entity.child.ChildFactoryInterface;
import aoc.model.entity.child.ChildInterface;
import aoc.model.entity.child.Children;
import aoc.model.level.Level;
import aoc.utilities.Pair;
import aoc.utilities.Vector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

/**
 * This class implements the methods of a spawner of children.
 */
public class ChildSpawner implements Spawner {
    
    private long counter;
    private long delay;
    private final List<Pair<Map<Children, Integer>, Double>> childrenToSpawn;
    private List<ChildInterface> currentList = new ArrayList<>();
    private final ChildFactoryInterface factory = new ChildFactory();
    private final Level level;
    
    /**
     * Constructor for ChildSpawner.
     * @param children
     *          list representing all the children to spawn.
     * @param level
     *          the current level.
     */
    public ChildSpawner(final List<Pair<Map<Children, Integer>, Double>> children, final Level level) {
	    childrenToSpawn = children;
	    this.level = level;
	    setNextMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
	counter++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean readyToSpawn() {
	if (!this.isEmpty()) {
	    return counter >= delay;
	}
	return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
	return childrenToSpawn.isEmpty() && currentList.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChildInterface entityToSpawn() {
	if (!this.isEmpty() && this.readyToSpawn()) {
	    if (currentList.isEmpty()) {
		this.setNextMap();
	    }
	    this.resetCounter();
	    final Random rnd = new Random();
	    return currentList.remove(rnd.nextInt(currentList.size()));
	}
	return null;
    }
    
    /**
     * This method set the next map of
     * children to spawn (the next wave of enemies).
     */
    private void setNextMap() {
	if (!childrenToSpawn.isEmpty()) {
	    final Map<Children, Integer> currentMap = childrenToSpawn.get(0).getFirst();
	    delay = (long) (childrenToSpawn.get(0).getSecond() * GameConstants.UPDATES_PER_SECOND);
	    this.currentList = generateChildrenList(currentMap);
	    childrenToSpawn.remove(0);
	    this.resetCounter();
	}
    }
    
    /**
     * This method creates a new child.
     * @param type
     *          the type of the new child.
     * @return a reference to the new child
     */
    private ChildInterface spawnChild(final Children type) {
	return (ChildInterface) factory.spawnChild(type, this.randomPosition(), this.level);
    }
    
    /**
     * This method returns a random position near the border of the world (spawn zone)
     * @return the random position.
     */
    private Vector randomPosition() {
        final Random rnd = new Random();
        return new Vector((WorldConstants.WORLD_WIDTH + 1) * (WorldConstants.CELL_WIDTH),
                WorldConstants.ROW_CENTERS.get(rnd.nextInt(WorldConstants.WORLD_HEIGHT)));
    }
    
    /**
     * This method creates a list containing al the children that need to be spawned.
     * @param currentMap
     *          children that need to be spawned
     * @return the list
     */
    private List<ChildInterface> generateChildrenList(final Map<Children, Integer> currentMap) {
	if (!currentMap.isEmpty()) {
	    final List<ChildInterface> newChildren = new ArrayList<>();
	    currentMap.keySet().forEach(key -> {
                newChildren.addAll(
                        Stream.generate(() -> this.spawnChild(key)).limit(currentMap.get(key))
                           .collect(Collectors.toList())
                           );
           });
		return newChildren;
	} else {
	    return Collections.emptyList();
	}
    }
    
    /**
     * This method resets the counter of the spawner.
     */
    private void resetCounter() {
	this.counter = 0L;
    }
}
