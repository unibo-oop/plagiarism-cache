package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.npc.api.NPC;
import it.tbt.model.fight.api.FightModel;
import it.tbt.model.shop.Shop;

import java.util.Map;

/**
 * The {@code NPCFactory} class provides static methods for creating different types of NPCs.
 */
public final class NPCFactory {

    private NPCFactory() {
    }

    /**
     * Creates a dialogue NPC with the specified name, position, dimensions, and dialogue.
     *
     * @param name     the name of the dialogue NPC
     * @param x        the X coordinate of the NPC's position
     * @param y        the Y coordinate of the NPC's position
     * @param height   the height of the NPC
     * @param width    the width of the NPC
     * @param dialogue the dialogue of the NPC
     * @return the created dialogue NPC
     */
    public static NPC createDialogueNPC(final String name, final int x, final int y,
                                        final int height, final int width, final String dialogue) {
        return new DialogueNPCImpl(name, x, y, height, width, dialogue);
    }

    /**
     * Creates an item NPC with the specified name, position, dimensions, and items.
     *
     * @param name   the name of the item NPC
     * @param x      the X coordinate of the NPC's position
     * @param y      the Y coordinate of the NPC's position
     * @param height the height of the NPC
     * @param width  the width of the NPC
     * @param items  the map of items provided by the NPC and their quantities
     * @return the created item NPC
     */
    public static NPC createItemNPC(final String name, final int x, final int y,
                                    final int height, final int width, final Map<Item, Integer> items) {
        return new ItemNPCImpl(name, x, y, height, width, items);
    }

    /**
     * Creates a shop NPC with the specified name, position, dimensions, and shop.
     *
     * @param name   the name of the shop NPC
     * @param x      the X coordinate of the NPC's position
     * @param y      the Y coordinate of the NPC's position
     * @param height the height of the NPC
     * @param width  the width of the NPC
     * @param shop   the shop associated with the NPC
     * @return the created shop NPC
     */
    public static NPC createShopNPC(final String name, final int x, final int y,
                                    final int height, final int width, final Shop shop) {
        return new ShopNPCImpl(name, x, y, height, width, shop);
    }

    /**
     * Creates a healer NPC with the specified name, position, dimensions, and heal amount.
     *
     * @param name       the name of the healer NPC
     * @param x          the X coordinate of the NPC's position
     * @param y          the Y coordinate of the NPC's position
     * @param height     the height of the NPC
     * @param width      the width of the NPC
     * @param healAmount the amount of health the NPC can heal
     * @return the created healer NPC
     */
    public static NPC createHealerNPC(final String name, final int x, final int y,
                                      final int height, final int width, final int healAmount) {
        return new HealerNPCImpl(name, x, y, height, width, healAmount);
    }

    /**
     * Creates a fight NPC with the specified name, position, dimensions, and fight model.
     *
     * @param name       the name of the fight NPC
     * @param x          the X coordinate of the NPC's position
     * @param y          the Y coordinate of the NPC's position
     * @param height     the height of the NPC
     * @param width      the width of the NPC
     * @param fightModel the fight model associated with the NPC
     * @return the created fight NPC
     */
    public static NPC createFightNPC(final String name, final int x, final int y,
                                     final int height, final int width, final FightModel fightModel) {
        return new FightNPCImpl(name, x, y, height, width, fightModel);
    }

    /**
     * Creates an ally NPC with the specified name, position, dimensions, and ally.
     *
     * @param name   the name of the ally NPC
     * @param x      the X coordinate of the NPC's position
     * @param y      the Y coordinate of the NPC's position
     * @param height the height of the NPC
     * @param width  the width of the NPC
     * @param ally   the ally associated with the NPC
     * @return the created ally NPC
     */
    public static NPC createAllyNPC(final String name, final int x, final int y,
                                    final int height, final int width, final Ally ally) {
        return new AllyNPCImpl(name, x, y, height, width, ally);
    }
}
