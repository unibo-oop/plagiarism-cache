package fargoal.entity.monster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
 
import org.junit.jupiter.api.Test;

import fargoal.commons.api.Position;
import fargoal.model.core.GameEngine;
import fargoal.model.entity.monsters.api.Monster;
import fargoal.model.entity.monsters.api.MonsterFactory;
import fargoal.model.entity.monsters.impl.MonsterFactoryImpl;
import fargoal.model.manager.api.FloorManager;
import fargoal.model.manager.api.MatchType;
import fargoal.model.manager.impl.FloorManagerImpl;

/**
 * Test class to check if a monster will move when far from the player,
 * and otherwise if he won't move when he's near to the player (because 
 * he's supposed to attacks him).
 */
class TestMonsterMovement {

    private static final int MAX_MOVE_TEST = 1000;

    private static FloorManager manager = new FloorManagerImpl(new GameEngine(), MatchType.NORMAL);
    private static MonsterFactory factory = new MonsterFactoryImpl(1);
    private static Random random = new Random();

    @Test
    void moveMonster() {
        Monster monster;
        Position pos;
        List<Position> positions;
        do {
            pos = manager.getFloorMap().getRandomTile();
            final Monster monsterCreate = factory.generate(pos,
                manager, 
                manager.getRenderFactory());
            positions = Stream.of(new Position(-1, -1), new Position(0, -1), new Position(1, -1),
                    new Position(-1, 0), new Position(1, 0),
                    new Position(-1, 1), new Position(0, 1), new Position(1, 1))
                    .map(p -> p.add(monsterCreate.getPosition()))
                    .filter(p -> manager.getFloorMap().isTile(p))
                    .collect(Collectors.toList());
            monster = monsterCreate;
        } while (positions.isEmpty());
        manager.getPlayer().move(positions.get(random.nextInt(positions.size())));

        assertTrue(Math.abs(monster.getPosition().x() - manager.getPlayer().getPosition().x()) <= 1
                && Math.abs(monster.getPosition().y() - manager.getPlayer().getPosition().y()) <= 1);

        //dato che il player è vicino al mostro, il mostro muovendosi non deve cambiare posizione
        monster.update(manager);
        assertEquals(monster.getPosition(), pos);

        /* Controllo che ad ogni move la posizione del mostro cambi
        molto improbabile che non cambi, ma potrebbe accadere
        solo nel caso in cui è circondato da muri e mostri
        e non ha possibilità di muoversi, in quel caso
        rimarrebbe fermo. */
        Position lastPosition = monster.getPosition();
        for (int i = 0; i < MAX_MOVE_TEST; i++) {
            monster.move();
            assertNotEquals(lastPosition, monster.getPosition());
            lastPosition = monster.getPosition();
        }
    }
}
