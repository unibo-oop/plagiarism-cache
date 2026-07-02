package fargoal.model.entity.monsters.impl;

import java.util.Random;

import fargoal.commons.api.Position;
import fargoal.model.entity.monsters.api.Monster;
import fargoal.model.entity.monsters.api.MonsterFactory;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;

/**
 * A class that implements every method of the MonsterFactory,
 * and which methods will be called to generate monsters.
 */
public class MonsterFactoryImpl implements MonsterFactory {

    private static final int ROGUE = 1;
    private static final int BARBARIAN = 2;
    private static final int MONK = 3;
    private static final int WAR_LORD = 4;
    private static final int SPIDER = 5;
    private static final int MAGE = 6;
    private static final int ASSASSIN = 7; 
    private static final int MAX_FLOOR = 9;
    private static final int MAGE_FLOOR = 7;
    private static final int SPIDER_FLOOR = 5;
    private static final int MONK_FLOOR = 4;
    private static final int MIN_FLOOR = 3;
    private final Integer level;
    private final Random random = new Random();

    /**
     * Initialize the only field in this class.
     * 
     * @param level - to set the current level
     */
    public MonsterFactoryImpl(final Integer level) {
        this.level = level;
    }

    /** {@inheritDoc} */
    @Override
    public Monster generate(final Position position,
            final FloorManager floorManager,
            final RenderFactory renderFactory) {
        final int num;
        if (floorManager.getFloorLevel() >= MAX_FLOOR) {
            num = random.nextInt(4) + 4;
            if (num == WAR_LORD) {
                return generateWarLord(position, level, floorManager, renderFactory);
            } else if (num == SPIDER) {
                return generateSpider(position, level, floorManager, renderFactory);
            } else if (num == MAGE) {
                return generateMage(position, level, floorManager, renderFactory);
            } else if (num == ASSASSIN) {
                return generateAssassin(position, level, floorManager, renderFactory);
            }
        } else if (floorManager.getFloorLevel() >= MAGE_FLOOR) {
            num = random.nextInt(3) + 4;
            if (num == WAR_LORD) {
                return generateWarLord(position, level, floorManager, renderFactory);
            } else if (num == SPIDER) {
                return generateSpider(position, level, floorManager, renderFactory);
            } else if (num == MAGE) {
                return generateMage(position, level, floorManager, renderFactory);
            }
        } else if (floorManager.getFloorLevel() >= SPIDER_FLOOR) {
            num = random.nextInt(3) + 3;
            if (num == MONK) {
                return generateMonk(position, level, floorManager, renderFactory);
            } else if (num == WAR_LORD) {
                return generateWarLord(position, level, floorManager, renderFactory);
            } else if (num == SPIDER) {
                return generateSpider(position, level, floorManager, renderFactory);
            }
        } else if (floorManager.getFloorLevel() == MONK_FLOOR) {
            return generateMonk(position, level, floorManager, renderFactory);
        } else if (floorManager.getFloorLevel() <= MIN_FLOOR) {
            num = random.nextInt(2) + 1;
            if (num == ROGUE) {
                return generateRogue(position, level, floorManager, renderFactory);
            } else if (num == BARBARIAN) {
                return generateBarbarian(position, level, floorManager, renderFactory);
            }
        }
        return generateBarbarian(position, level, floorManager, renderFactory);
    }

    /**
     * Generate a Rogue.
     * 
     * @param position - the starting position
     * @param level - the level of the floor where the monster is located
     * @param floorManager - to get infos also about other entities
     * @param renderFactory - to give a render to the Rogue
     * @return a new Rogue
     */
    private Monster generateRogue(
            final Position position, 
            final Integer level,
            final FloorManager floorManager,
            final RenderFactory renderFactory) {
        return new Rogue(position, level, floorManager, renderFactory);
    }

    /**
     * Generate a Barbarian.
     * 
     * @param position - the starting position
     * @param level - the level of the floor where the monster is located
     * @param floorManager - to get infos also about other entities
     * @param renderFactory - to give a render to the Barbarian
     * @return a new Barbarian
     */
    private Monster generateBarbarian(
                final Position position,
                final Integer level,
                final FloorManager floorManager,
                final RenderFactory renderFactory) {
        return new Barbarian(position, level, floorManager, renderFactory);
    }

    /**
     * Generate a Monk.
     * 
     * @param position - the starting position
     * @param level - the level of the floor where the monster is located
     * @param floorManager - to get infos also about other entities
     * @param renderFactory - to give a render to the Monk
     * @return a new Monk
     */
    private Monster generateMonk(
                final Position position, 
                final Integer level,
                final FloorManager floorManager,
                final RenderFactory renderFactory) {
        return new Monk(position, level, floorManager, renderFactory);
    }

    /**
     * Generate an Assassin.
     * 
     * @param position - the starting position
     * @param level - the level of the floor where the monster is located
     * @param floorManager - to get infos also about other entities
     * @param renderFactory - to give a render to the Assassin
     * @return a new Assassin
     */
    private Monster generateAssassin(
                final Position position, 
                final Integer level,
                final FloorManager floorManager,
                final RenderFactory renderFactory) {
        return new Assassin(position, level, floorManager, renderFactory);
    }

    /**
     * Generate a War Lord.
     * 
     * @param position - the starting position
     * @param level - the level of the floor where the monster is located
     * @param floorManager - to get infos also about other entities
     * @param renderFactory - to give a render to the War Lord
     * @return a new War Lord
     */
    private Monster generateWarLord(
                final Position position, 
                final Integer level,
                final FloorManager floorManager,
                final RenderFactory renderFactory) {
        return new WarLord(position, level, floorManager, renderFactory);
    }

    /**
     * Generate a Mage.
     * 
     * @param position - the starting position
     * @param level - the level of the floor where the monster is located
     * @param floorManager - to get infos also about other entities
     * @param renderFactory - to give a render to the Mage
     * @return a new Mage
     */
    private Monster generateMage(
                final Position position, 
                final Integer level,
                final FloorManager floorManager,
                final RenderFactory renderFactory) {
        return new Mage(position, level, floorManager, renderFactory);
    }

    /**
     * Generate a Spider.
     * 
     * @param position - the starting position
     * @param level - the level of the floor where the monster is located
     * @param floorManager - to get infos also about other entities
     * @param renderFactory - to give a render to the Spider
     * @return a new Spider
     */
    private Monster generateSpider(
                final Position position, 
                final Integer level,
                final FloorManager floorManager,
                final RenderFactory renderFactory) {
        return new Spider(position, level, floorManager, renderFactory);
    }
}
