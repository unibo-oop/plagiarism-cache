package rogue.model.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import rogue.model.Entity;
import rogue.model.creature.MonsterFactory;
import rogue.model.creature.MonsterFactoryImpl;
import rogue.model.creature.Player;
import rogue.model.items.ItemFactory;
import rogue.model.items.ItemFactoryImpl;

/**
 * the default world implemetation.
 */
public class WorldImpl implements World {
    private static final MonsterFactory MONSTER_FACTORY = new MonsterFactoryImpl();
    private static final ItemFactory ITEM_FACTORY = new ItemFactoryImpl();

    private Level currentLevel;
    private Player player;

    /**
     * change to the next level.
     */
    private final Runnable nextLevel = () -> {
        final List<Entity> entityList = new ArrayList<>();
        entityList.add(player);
        entityList.addAll(MONSTER_FACTORY.createMonsterList(player.getLife().getLevel()));
        entityList.addAll(ITEM_FACTORY.getItems());

        currentLevel = new LevelImpl(entityList, player);
    };

    public final Stream<Tile> getTiles() {
        return currentLevel.getTileStream();
    }

    public final Map<Entity, Tile> getEntityMap() {
        return currentLevel.getEntityMap();
    }

    public final boolean round(final Direction direction) {
        boolean nextlvl = false;
        if (currentLevel.moveEntities(direction)) {
            nextLevel.run(); // change level
            nextlvl = true;
        }
        if (player.getInventory().getScrollContainer().getActiveScroll().isPresent()) {
            player.getInventory().getScrollContainer().updateEffectDuration(1);
        }

        return nextlvl;
    }

    public final int getWidth() {
        return currentLevel.getWidth();
    }

    public final int getHeight() {
        return currentLevel.getHeight();
    }

    public final Player getPlayer() {
        return this.player;
    }

    /**
     * @param player the player instance
     */
    public WorldImpl(final Player player) {
        this.player = player;
        nextLevel.run();
    }
}
