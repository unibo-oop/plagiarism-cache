package it.tbt.model.entities.npc.api;

import it.tbt.model.shop.Shop;

/**
 * The {@code ShopNPC} interface represents an NPC that operates a shop in the game.
 * It extends the {@link NPC} interface.
 */
public interface ShopNPC extends NPC {

    /**
     * Retrieves the shop associated with the NPC.
     *
     * @return the shop operated by the NPC
     */
    Shop getShop();
}
