package aoc.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import aoc.controller.GameConstants;
import aoc.model.Model.GameStatus;
import aoc.model.Model;
import aoc.model.Model.ShootingStyle;
import aoc.model.ModelImpl;
import aoc.model.WorldConstants;
import aoc.model.entity.EntityInterface;
import aoc.model.entity.child.ChildInterface;
import aoc.model.entity.child.Children;
import aoc.model.entity.mother.*;
import aoc.model.level.*;
import aoc.model.level.spawner.*;
import aoc.utilities.Direction;
import aoc.utilities.Pair;

/**
 * Testing of Model features.
 */
public class WorldTests {
    
    private static final int DEBUG_LEVEL = 0;
    private static final int DEBUG_MODEL = -1;
    
    @Test
    public void spawner() {
	final List<Pair<Map<Children, Integer>, Double>> children = new ArrayList<>();
	final Map<Children, Integer> map1 = new HashMap<>();
	final double delay = 10;
	map1.put(Children.DUMB_CHILD, 2);
	map1.put(Children.FAT_CHILD, 4);
	map1.put(Children.ATHLETIC_CHILD, 2);
	final List<Children> entitiesToSpawn = new ArrayList<>();
	map1.keySet().forEach(type -> {
	    Stream.generate(() -> null).limit(map1.get(type))
	    	.forEach(x -> entitiesToSpawn.add(type));
	});
	final List<Children> entitiesSpawned = new ArrayList<>();
	children.add(new Pair<>(map1, delay));
	final Spawner spawner = new ChildSpawner(children, null);
	assertFalse(spawner.readyToSpawn());
	int k = 0;

	while (!spawner.isEmpty()) {
	    k++;
	    Stream.generate(() -> null).limit((long) (delay * GameConstants.UPDATES_PER_SECOND - 1))
	    	.forEach(x -> {
	    	    spawner.tick();
	    	    assertFalse(spawner.readyToSpawn());
	    	});
	    spawner.tick();
	    assertTrue(spawner.readyToSpawn());
	    entitiesSpawned.add(Children.valueOf(spawner.entityToSpawn().getName()));
	}
	assertEquals(k, map1.entrySet().stream().mapToInt(x -> x.getValue()).sum());
	assertTrue(entitiesSpawned.containsAll(entitiesToSpawn));
    }
    
    @Test
    public void storyLevel() {
	final Level level = new StoryLevel(DEBUG_LEVEL);
	int entities = 0;
	int i = 0;
	assertEquals(level.getCurrentLevel(), DEBUG_LEVEL);
	assertEquals(level.getGameStatus(), GameStatus.PLAYING);
	assertTrue(level.getEntityList().isEmpty());	
	while (!level.getGameStatus().equals(GameStatus.LOST)) {
	    i++;
	    assertSame(level.getGameStatus(), GameStatus.PLAYING);
	    level.update();
	    if(i % (2 * GameConstants.UPDATES_PER_SECOND) == 0 && i < 12 * GameConstants.UPDATES_PER_SECOND) {
		assertEquals(level.getEntityList().size(), entities);
		entities++;
	    }
	}
	final List<EntityInterface> whoMadeYouLost = level.getEntityList().stream().filter(x -> x.getPosition().getX() <= WorldConstants.GAMEOVER_LINE).collect(Collectors.toList());
	assertEquals(1, whoMadeYouLost.size());
	final ChildInterface thatChild = (ChildInterface) whoMadeYouLost.get(0);
	assertTrue(Math.abs(WorldConstants.GAMEOVER_LINE - thatChild.getPosition().getX()) <= Math.abs(Children.valueOf(thatChild.getName()).getXSpeed()));
    }
    
    @Test
    public void model() {
	final Model model = new ModelImpl(Optional.of(DEBUG_MODEL));
        assertEquals(model.getCurrentLevel(), DEBUG_MODEL);
        assertEquals(model.getGameStatus(), GameStatus.PLAYING);
        assertTrue(model.getEntityList().size() == 1 && model.getEntityList().get(0) instanceof Mother);
        final Mother mother = (Mother) model.getEntityList().get(0);
        
        Stream.generate(() -> null).limit(2 * GameConstants.UPDATES_PER_SECOND + 1).forEach(x -> model.update());
        
        assertEquals(2, model.getEntityList().size());
        final double childRow = model.getEntityList().stream().filter(x -> x instanceof ChildInterface).findFirst().get().getPosition().getY();
        
        //Move the mother in the row where the child just spawned
        switch (WorldConstants.ROW_CENTERS.indexOf(childRow)) {
	case 4:
	    model.moveMother(Direction.DOWN);
	    model.moveMother(Direction.DOWN);
	    break;
	case 3:
	    model.moveMother(Direction.DOWN);
	    break;
	case 1:
	    model.moveMother(Direction.UP);
	    break;
	case 0:
	    model.moveMother(Direction.UP);
	    model.moveMother(Direction.UP);
	    break;
	default:
	    break;
	}
        assertTrue(mother.getPosition().getY().equals(childRow));
        Stream.generate(() -> null).limit(9).forEach(x -> {
            model.shoot(ShootingStyle.RAPID);
            Stream.generate(() -> null).limit(30).forEach(y -> model.update());
            try {
		Thread.sleep(150);
	    } catch (InterruptedException e) {
		e.printStackTrace();
		System.out.println("errore!");
	    }
        });
//      assertEquals(11, model.getEntityList().size());
//      Stream.generate(() -> null).limit(2).forEach(x -> {
//      model.shoot(ShootingStyle.SINGLE);
//      model.update();
//      });
        
//        assertEquals(13, model.getEntityList().size());
        final ChildInterface child = (ChildInterface) model.getEntityList().stream()
                .filter(x -> x instanceof ChildInterface).findFirst().get();
        
        while (model.getGameStatus().equals(GameStatus.PLAYING)) {
            model.update();
            if (!child.isHit(model.getEntityList()).isEmpty()) {
        	model.getEntityList().stream().forEach(x -> System.out.println(x.getName() + " " + x.getPosition()));
        	System.out.println("\n");
            }
        }
//        assertTrue(model.getGameStatus().equals(GameStatus.WON));
        
        //Only the mother and a slipper are still in the game
        assertEquals(2, model.getEntityList().size());
    }
    
}
