package fargoal.entity.monster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import fargoal.model.core.GameEngine;
import fargoal.model.entity.monsters.api.Monster;
import fargoal.model.entity.monsters.api.MonsterFactory;
import fargoal.model.entity.monsters.impl.MonsterFactoryImpl;
import fargoal.model.manager.api.FloorManager;
import fargoal.model.manager.api.MatchType;
import fargoal.model.manager.impl.FloorManagerImpl;

/**
 * Test class to check if monsters are generated correctly,
 * and if they spawn at the right floor.
 */
class TestMonsterGeneration {

    private static final int CONSTANT_FIFTY = 50;
    private static final int CONSTANT_TWENTYFIVE = 25;
    private static final int CONSTANT_FIVE = 5;
    private static final int CONSTANT_SIX = 6;
    private static final int CONSTANT_SEVEN = 7;
    private static final int CONSTANT_TWELVE = 12;
    private static final int CONSTANT_THIRTYFIVE = 35;
    private static final int CONSTANT_FORTYFIVE = 45;

    private static FloorManager manager = new FloorManagerImpl(new GameEngine(), MatchType.NORMAL);
    private static MonsterFactory factory = new MonsterFactoryImpl(1);
    private List<String> monsters = Stream.of(" ").collect(Collectors.toList());
    private Monster monster;

    @Test
    void firstFloors() {
        monsters.clear();
        monsters = Stream.of("ROGUE", "BARBARIAN").collect(Collectors.toList());
        for (int i = 0; i < CONSTANT_FIFTY; i++) {
            monster = factory.generate(manager.getFloorMap().getRandomTile(), 
                    manager, 
                    manager.getRenderFactory());
            if (i == 10) {
                manager.increaseFloorLevel();
            } else if (i == CONSTANT_TWENTYFIVE) {
                manager.increaseFloorLevel();
            }
            assertTrue(monsters.stream().anyMatch(p -> p.equals(monster.getTag())));
        }
        for (int i = 0; i < 2; i++) {
            manager.decreaseFloorLevel();
        }
    }
    @Test
    void fourthFloor() {
        for (int i = 0; i < 3; i++) {
            manager.increaseFloorLevel();
        }
        for (int i = 0; i < CONSTANT_FIFTY; i++) {
            monster = factory.generate(manager.getFloorMap().getRandomTile(), 
                    manager, 
                    manager.getRenderFactory());
            assertEquals("MONK", monster.getTag());
        }
        for (int i = 0; i < 3; i++) {
            manager.decreaseFloorLevel();
        }
    }

    @Test
    void fifthAndSixthFloor() {
        monsters.clear();
        monsters = Stream.of("MONK", "WAR_LORD", "SPIDER").collect(Collectors.toList());
        for (int i = 0; i < 4; i++) {
            manager.increaseFloorLevel();
        }
        for (int i = 0; i < CONSTANT_FIFTY; i++) {
            monster = factory.generate(manager.getFloorMap().getRandomTile(), 
                    manager, 
                    manager.getRenderFactory());
            if (i == CONSTANT_TWENTYFIVE) {
                manager.increaseFloorLevel();
            }
            assertTrue(monsters.stream().anyMatch(p -> p.equals(monster.getTag())));
        }
        for (int i = 0; i < CONSTANT_FIVE; i++) {
            manager.decreaseFloorLevel();
        }
    }

    @Test
    void seventhAndEighthFloor() {
        monsters.clear();
        monsters = Stream.of("WAR_LORD", "SPIDER", "MAGE").collect(Collectors.toList());
        for (int i = 0; i < CONSTANT_SIX; i++) {
            manager.increaseFloorLevel();
        }
        for (int i = 0; i < CONSTANT_FIFTY; i++) {
            monster = factory.generate(manager.getFloorMap().getRandomTile(),
                    manager, 
                    manager.getRenderFactory());
            if (i == CONSTANT_TWENTYFIVE) {
                manager.increaseFloorLevel();
            }
            assertTrue(monsters.stream().anyMatch(p -> p.equals(monster.getTag())));
        }
        for (int i = 0; i < CONSTANT_SEVEN; i++) {
            manager.decreaseFloorLevel();
        }
    }

    @Test
    void ninthPlusFloor() {
        monsters.clear();
        monsters = Stream.of("WAR_LORD", "SPIDER", "MAGE", "ASSASSIN").collect(Collectors.toList());
        for (int i = 0; i < 8; i++) {
            manager.increaseFloorLevel();
        }
        for (int i = 0; i < CONSTANT_FIFTY; i++) {
            monster = factory.generate(manager.getFloorMap().getRandomTile(), 
                    manager, 
                    manager.getRenderFactory());
            if (i == 10) {
                manager.increaseFloorLevel();
            } else if (i == CONSTANT_TWENTYFIVE) {
                manager.increaseFloorLevel();
            } else if (i == CONSTANT_THIRTYFIVE) {
                manager.increaseFloorLevel();
            } else if (i == CONSTANT_FORTYFIVE) {
                manager.increaseFloorLevel();
            }
            assertTrue(monsters.stream().anyMatch(p -> p.equals(monster.getTag())));
        }
        for (int i = 0; i < CONSTANT_TWELVE; i++) {
            manager.decreaseFloorLevel();
        }
    }
}
