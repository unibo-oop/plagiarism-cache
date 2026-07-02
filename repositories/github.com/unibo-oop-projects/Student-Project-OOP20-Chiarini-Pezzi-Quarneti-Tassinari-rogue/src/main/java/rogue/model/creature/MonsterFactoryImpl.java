package rogue.model.creature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An implementation for a {@link MonsterFactory}. 
 *
 */
public class MonsterFactoryImpl implements MonsterFactory {

    private static final int HIGH_SPAWN_RATE = 60;
    private static final int MEDIUM_SPAWN_RATE = 40;
    private static final int LOW_SPAWN_RATE = 20;

    private static final int PLAYER_LEVEL_1 = 1;
    private static final int PLAYER_LEVEL_2 = 2;
    private static final int PLAYER_LEVEL_3 = 3;
    private static final int PLAYER_LEVEL_4 = 4;
    private static final int PLAYER_LEVEL_5 = 5;
    private static final int PLAYER_LEVEL_6 = 6;
    private static final int PLAYER_LEVEL_7 = 7;
    private static final int PLAYER_LEVEL_8 = 8;
    private static final int PLAYER_LEVEL_9 = 9;
    private static final int PLAYER_LEVEL_10 = 10;
    private static final int PLAYER_LEVEL_11 = 11;
    private static final int PLAYER_LEVEL_12 = 12;
    private static final int PLAYER_LEVEL_13 = 13;
    private static final int PLAYER_LEVEL_14 = 14;
    private static final int PLAYER_LEVEL_15 = 15;
    private static final int PLAYER_LEVEL_16 = 16;
    private static final int PLAYER_LEVEL_17 = 17;
    private static final int PLAYER_LEVEL_18 = 18;
    private static final int PLAYER_LEVEL_19 = 19;
    private static final int PLAYER_LEVEL_20 = 20;


    private static boolean isSpanwned(final int spawnRate) {
        final int casuale = ThreadLocalRandom.current().nextInt(0, 100);
        return casuale <= spawnRate;
    }

    /** 
     * {@inheritDoc}
     */ 
    @Override
   public List<Monster> createMonsterList(final int playerLevel) {
        final List<Monster> monsterList = new ArrayList<>();
        switch (playerLevel) {
            case PLAYER_LEVEL_1:
                monsterList.add(new MonsterImpl(MonsterType.BAT));
                monsterList.add(new MonsterImpl(MonsterType.KOBOLD));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.BAT));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.KOBOLD));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.SNAKE));
                }
                return monsterList;

            case PLAYER_LEVEL_2:
                monsterList.add(new MonsterImpl(MonsterType.SNAKE));
                monsterList.add(new MonsterImpl(MonsterType.KOBOLD));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.EMU));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.EMU));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.HOBGOBLIN));
                }
                return monsterList;

            case PLAYER_LEVEL_3:
                monsterList.add(new MonsterImpl(MonsterType.EMU));
                monsterList.add(new MonsterImpl(MonsterType.HOBGOBLIN));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.SNAKE));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.HOBGOBLIN));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.ICE_CUBE));
                }
                return monsterList;

            case PLAYER_LEVEL_4:
                monsterList.add(new MonsterImpl(MonsterType.ORC));
                monsterList.add(new MonsterImpl(MonsterType.ICE_CUBE));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.ORC));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                     monsterList.add(new MonsterImpl(MonsterType.ICE_CUBE));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.ZOMBIE));
                }
                return monsterList;

            case PLAYER_LEVEL_5:
                monsterList.add(new MonsterImpl(MonsterType.ZOMBIE));
                monsterList.add(new MonsterImpl(MonsterType.ICE_CUBE));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.ZOMBIE));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.RATFOLK));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.LYCANTHROPE));
                }
                return monsterList;

            case PLAYER_LEVEL_6:
                monsterList.add(new MonsterImpl(MonsterType.RATFOLK));
                monsterList.add(new MonsterImpl(MonsterType.ZOMBIE));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.LYCANTHROPE));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.RATFOLK));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.QUAQQA));
                }
                return monsterList;

            case PLAYER_LEVEL_7:
                monsterList.add(new MonsterImpl(MonsterType.LYCANTHROPE));
                monsterList.add(new MonsterImpl(MonsterType.RATFOLK));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.LYCANTHROPE));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.QUAQQA));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.CENTAUR));
                }
                return monsterList;
            case PLAYER_LEVEL_8:
                monsterList.add(new MonsterImpl(MonsterType.QUAQQA));
                monsterList.add(new MonsterImpl(MonsterType.LYCANTHROPE));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.CENTAUR));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.QUAQQA));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.AIR_ELEMENTAL));
                }
                return monsterList;

            case PLAYER_LEVEL_9:
                monsterList.add(new MonsterImpl(MonsterType.CENTAUR));
                monsterList.add(new MonsterImpl(MonsterType.QUAQQA));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.AIR_ELEMENTAL));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.CENTAUR));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.NYMPH));
                }
                return monsterList;

            case PLAYER_LEVEL_10:
                monsterList.add(new MonsterImpl(MonsterType.AIR_ELEMENTAL));
                monsterList.add(new MonsterImpl(MonsterType.CENTAUR));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.NYMPH));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.AIR_ELEMENTAL));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.YETI));
                }
                return monsterList;

            case PLAYER_LEVEL_11:
                monsterList.add(new MonsterImpl(MonsterType.AIR_ELEMENTAL));
                monsterList.add(new MonsterImpl(MonsterType.NYMPH));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.YETI));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.NYMPH));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.WRAITH));
                }
                return monsterList;

            case PLAYER_LEVEL_12:
                monsterList.add(new MonsterImpl(MonsterType.NYMPH));
                monsterList.add(new MonsterImpl(MonsterType.YETI));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.WRAITH));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.YETI));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.FIRE_ELEMENTAL));
                }
                return monsterList;

            case PLAYER_LEVEL_13:
                monsterList.add(new MonsterImpl(MonsterType.YETI));
                monsterList.add(new MonsterImpl(MonsterType.WRAITH));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.FIRE_ELEMENTAL));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.WRAITH));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.XILL));
                }
                return monsterList;

            case PLAYER_LEVEL_14:
                monsterList.add(new MonsterImpl(MonsterType.WRAITH));
                monsterList.add(new MonsterImpl(MonsterType.FIRE_ELEMENTAL));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.XILL));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.FIRE_ELEMENTAL));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.TROLL));
                }
                return monsterList;

            case PLAYER_LEVEL_15:
                monsterList.add(new MonsterImpl(MonsterType.FIRE_ELEMENTAL));
                monsterList.add(new MonsterImpl(MonsterType.XILL));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.TROLL));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.XILL));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.UNICORN));
                }
                return monsterList;

            case PLAYER_LEVEL_16:
                monsterList.add(new MonsterImpl(MonsterType.XILL));
                monsterList.add(new MonsterImpl(MonsterType.TROLL));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.UNICORN));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.TROLL));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.PHANTHOM));
                }
                return monsterList;

            case PLAYER_LEVEL_17:
                monsterList.add(new MonsterImpl(MonsterType.TROLL));
                monsterList.add(new MonsterImpl(MonsterType.PHANTHOM));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.MEDUSA));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.UNICORN));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.VAMPIRE));
                }
                return monsterList;

            case PLAYER_LEVEL_18:
                monsterList.add(new MonsterImpl(MonsterType.MEDUSA));
                monsterList.add(new MonsterImpl(MonsterType.PHANTHOM));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.VAMPIRE));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.MEDUSA));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.GRIFFIN));
                }
                return monsterList;

            case PLAYER_LEVEL_19:
                monsterList.add(new MonsterImpl(MonsterType.VAMPIRE));
                monsterList.add(new MonsterImpl(MonsterType.MEDUSA));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.GRIFFIN));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.VAMPIRE));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.JABBERWOCK));
                }
                return monsterList;

            case PLAYER_LEVEL_20:
                monsterList.add(new MonsterImpl(MonsterType.VAMPIRE));
                monsterList.add(new MonsterImpl(MonsterType.GRIFFIN));
                if (isSpanwned(HIGH_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.JABBERWOCK));
                }
                if (isSpanwned(MEDIUM_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.GRIFFIN));
                }
                if (isSpanwned(LOW_SPAWN_RATE)) {
                    monsterList.add(new MonsterImpl(MonsterType.DRAGON));
                }
                return monsterList;

            default:
                return monsterList;
        }
    }
}
