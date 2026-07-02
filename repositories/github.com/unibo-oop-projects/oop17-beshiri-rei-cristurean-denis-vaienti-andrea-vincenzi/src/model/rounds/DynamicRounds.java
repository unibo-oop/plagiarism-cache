package model.rounds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.animated.Animated;
import model.animated.EnemyFactory;
import model.animated.EnemyFactoryImpl;
import model.animated.EnemyType;
import model.animated.EntityStats;
import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;
import model.utility.ModelUtility;
import model.utility.Spawn;
import utility.Command;

/**
 * Dynamic Round class. This class will mainly create a list of Enemy in a loop.
 * Every time the player get in a higher rounds there will be more monsters, the
 * max number of monsters in a single round is 7.
 */
public class DynamicRounds implements RoundsGenerator {

    private static final int MAXENEMY = 7;

    private final List<EnemyType> listEnemy;
    private final List<Command> listCommand;
    private final List<Spawn> listSpawns;
    private final List<Animated> listReturnEnemy;

    /**
     * Constructor for this class. Add to the list all the possible enemies, and the
     * spawns.
     */
    public DynamicRounds() {
        listEnemy = new ArrayList<>();
        listCommand = new ArrayList<>();
        listSpawns = new ArrayList<>();
        listReturnEnemy = new ArrayList<>();
    }

    /**
     * Generate a random Monster.
     */
    @Override
    public List<Animated> generateMonster() {
        fullList();
        final int enemyToSpawn = numberOfEnemyToGenerate();
        final EnemyFactory enemy = new EnemyFactoryImpl();
        Spawn spawn;
        Collections.shuffle(listSpawns);
        for (int i = 0; i < enemyToSpawn; i++) {
            Collections.shuffle(listEnemy);
            Collections.shuffle(listCommand);
            spawn = listSpawns.remove(0);
            if (listEnemy.get(0).equals(EnemyType.SIMPLE)) {
                final HitBox hb = new CircleHitBox(spawn.getX(), spawn.getY(),
                        EntityStats.STATIC_ENEMY.getEntityRadius());
                final Animated en = enemy.createStaticSimpleDirectionShotEnemy(hb, listCommand.get(0));
                listReturnEnemy.add(en);
            } else if (listEnemy.get(0).equals(EnemyType.SIMPLEMOVE)) {
                final HitBox hb = new CircleHitBox(spawn.getX(), spawn.getY(),
                        EntityStats.MOVEABLE_ENEMY.getEntityRadius());
                final Animated en = enemy.createSimpleDirectionMovedEnemy(hb, listCommand.get(0), listCommand.get(1));
                listReturnEnemy.add(en);
            } else if (listEnemy.get(0).equals(EnemyType.SIMPLEAIMED)) {
                final HitBox hb = new CircleHitBox(spawn.getX(), spawn.getY(),
                        EntityStats.STATIC_ENEMY.getEntityRadius());
                final Animated en = enemy.createStaticAimedBulletEnemy(hb);
                listReturnEnemy.add(en);
            } else if (listEnemy.get(0).equals(EnemyType.FOLLOWED)) {
                final HitBox hb = new CircleHitBox(spawn.getX(), spawn.getY(),
                        EntityStats.STATIC_ENEMY.getEntityRadius());
                final Animated en = enemy.createStaticEnemyFollowPlayerBullet(hb);
                listReturnEnemy.add(en);
            } else if (listEnemy.get(0).equals(EnemyType.DIAGONAL)) {
                final HitBox hb = new CircleHitBox(spawn.getX(), spawn.getY(),
                        EntityStats.STATIC_ENEMY.getEntityRadius());
                final Animated en = enemy.createStaticEnemyFourWayDiagonalProjectile(hb);
                listReturnEnemy.add(en);
            } else if (listEnemy.get(0).equals(EnemyType.STRAIGHT)) {
                final HitBox hb = new CircleHitBox(spawn.getX(), spawn.getY(),
                        EntityStats.STATIC_ENEMY.getEntityRadius());
                final Animated en = enemy.createStaticEnemyFourWayStraightProjectile(hb);
                listReturnEnemy.add(en);
            }
        }
        listEnemy.clear();
        listSpawns.clear();
        listCommand.clear();
        return listReturnEnemy;
    }

    /**
     * @return the number of monsters to generate.
     */
    private int numberOfEnemyToGenerate() {
        return getCurrentRound() < DynamicRounds.MAXENEMY ? getCurrentRound() : DynamicRounds.MAXENEMY;
    }

    /**
     * @return the current rounds, so the difficulty can be adapted.
     */
    private int getCurrentRound() {
        return ModelUtility.getCurrentRound();
    }

    /**
     * Full the lists with the needed parameters.
     */
    private void fullList() {
        listReturnEnemy.clear();
        listEnemy.add(EnemyType.SIMPLEAIMED);
        listEnemy.add(EnemyType.SIMPLEMOVE);
        listEnemy.add(EnemyType.SIMPLE);
        listEnemy.add(EnemyType.FOLLOWED);
        listEnemy.add(EnemyType.DIAGONAL);
        listEnemy.add(EnemyType.STRAIGHT);
        listSpawns.add(Spawn.A);
        listSpawns.add(Spawn.B);
        listSpawns.add(Spawn.C);
        listSpawns.add(Spawn.D);
        listSpawns.add(Spawn.E);
        listSpawns.add(Spawn.F);
        listSpawns.add(Spawn.G);
        listSpawns.add(Spawn.H);
        listCommand.add(Command.UP);
        listCommand.add(Command.RIGHT);
        listCommand.add(Command.DOWN);
        listCommand.add(Command.LEFT);
    }
}
