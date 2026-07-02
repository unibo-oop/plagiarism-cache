package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.CharacterFactory;
import it.tbt.model.entities.items.factories.ArmorFactory;
import it.tbt.model.entities.items.factories.WeaponFactory;
import it.tbt.model.entities.npc.api.NPC;
import it.tbt.model.fight.api.FightModel;
import it.tbt.model.fight.impl.FightModelImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.tbt.model.entities.items.Item;
import it.tbt.model.shop.Shop;

import java.util.HashMap;
import java.util.Map;

class NPCFactoryTest {
    //constants to mitigate MagicNumber alerts
    static final int INT_CONST_10 = 10;
    static final int INT_CONST_20 = 20;
    static final int INT_CONST_30 = 30;
    static final int INT_CONST_50 = 50;
    static final int INT_CONST_10000 = 10_000;


    @Test
    void testCreateDialogueNPC() {
        // Create a dialogue NPC using the NPCFactory
        final NPC dialogueNPC = NPCFactory.createDialogueNPC("John", INT_CONST_10, INT_CONST_20,
                INT_CONST_50, INT_CONST_30, "Hello, how can I help you?");

        // Assert that the NPC is an instance of DialogueNPCImpl
        assertTrue(dialogueNPC instanceof DialogueNPCImpl);

        // Assert the properties of the NPC
        assertEquals("John", dialogueNPC.getName());
        assertEquals(INT_CONST_10, dialogueNPC.getX());
        assertEquals(INT_CONST_20, dialogueNPC.getY());
        assertEquals(INT_CONST_50, dialogueNPC.getHeight());
        assertEquals(INT_CONST_30, dialogueNPC.getWidth());
        assertEquals("Hello, how can I help you?",
                ((DialogueNPCImpl) dialogueNPC).getDialogue());
    }


    @Test
    void testCreateItemNPC() {
        // Create a map of items for the item NPC
        final Map<Item, Integer> items = new HashMap<>();
        items.put((Item) WeaponFactory.getInstance().getItems().toArray()[0], 1);
        items.put((Item) ArmorFactory.getInstance().getItems().toArray()[0], 2);

        // Create an item NPC using the NPCFactory
        final NPC itemNPC = NPCFactory.createItemNPC("Merchant",
                INT_CONST_10, INT_CONST_20, INT_CONST_50, INT_CONST_30, items);

        // Assert that the NPC is an instance of ItemNPCImpl
        assertTrue(itemNPC instanceof ItemNPCImpl);

        // Assert the properties of the NPC
        assertEquals("Merchant", itemNPC.getName());
        assertEquals(INT_CONST_10, itemNPC.getX());
        assertEquals(INT_CONST_20, itemNPC.getY());
        assertEquals(INT_CONST_50, itemNPC.getHeight());
        assertEquals(INT_CONST_30, itemNPC.getWidth());
        assertEquals(items, ((ItemNPCImpl) itemNPC).getItems());
    }

    @Test
    void testCreateShopNPC() {
        // Create a shop for the shop NPC
        final Map<Item, Integer> shopMap = new HashMap<>();
        shopMap.put((Item) WeaponFactory.getInstance().getItems().toArray()[0], 1);
        shopMap.put((Item) ArmorFactory.getInstance().getItems().toArray()[0], 2);
        final Shop shop = new Shop(shopMap, INT_CONST_10000);

        // Create a shop NPC using the NPCFactory
        final NPC shopNPC = NPCFactory.createShopNPC("Shopkeeper",
                INT_CONST_10, INT_CONST_20, INT_CONST_50, INT_CONST_30, shop);

        // Assert that the NPC is an instance of ShopNPCImpl
        assertTrue(shopNPC instanceof ShopNPCImpl);

        // Assert the properties of the NPC
        assertEquals("Shopkeeper", shopNPC.getName());
        assertEquals(INT_CONST_10, shopNPC.getX());
        assertEquals(INT_CONST_20, shopNPC.getY());
        assertEquals(INT_CONST_50, shopNPC.getHeight());
        assertEquals(INT_CONST_30, shopNPC.getWidth());
        assertEquals(shop, ((ShopNPCImpl) shopNPC).getShop());
    }

    @Test
    void testCreateHealerNPC() {
        // Create a healer NPC using the NPCFactory
        final NPC healerNPC = NPCFactory.createHealerNPC("Healer",
                INT_CONST_10, INT_CONST_20, INT_CONST_50, INT_CONST_30, INT_CONST_10);

        // Assert that the NPC is an instance of HealerNPCImpl
        assertTrue(healerNPC instanceof HealerNPCImpl);

        // Assert the properties of the NPC
        assertEquals("Healer", healerNPC.getName());
        assertEquals(INT_CONST_10, healerNPC.getX());
        assertEquals(INT_CONST_20, healerNPC.getY());
        assertEquals(INT_CONST_50, healerNPC.getHeight());
        assertEquals(INT_CONST_30, healerNPC.getWidth());
        assertEquals(INT_CONST_10, ((HealerNPCImpl) healerNPC).getHealAmount());
    }

    @Test
    void testCreateFightNPC() {
        // Create a fight model for the fight NPC
        final Map<Item, Double> drop = new HashMap<>();
        drop.put((Item) WeaponFactory.getInstance().getItems().toArray()[0], 0.5);
        drop.put((Item) ArmorFactory.getInstance().getItems().toArray()[0], 0.5);
        final FightModel fightModel = new FightModelImpl(INT_CONST_10, drop);

        // Create a fight NPC using the NPCFactory
        final NPC fightNPC = NPCFactory.createFightNPC("Enemy", INT_CONST_10,
                INT_CONST_20, INT_CONST_50, INT_CONST_30, fightModel);

        // Assert that the NPC is an instance of FightNPCImpl
        assertTrue(fightNPC instanceof FightNPCImpl);

        // Assert the properties of the NPC
        assertEquals("Enemy", fightNPC.getName());
        assertEquals(INT_CONST_10, fightNPC.getX());
        assertEquals(INT_CONST_20, fightNPC.getY());
        assertEquals(INT_CONST_50, fightNPC.getHeight());
        assertEquals(INT_CONST_30, fightNPC.getWidth());
        assertEquals(fightModel, ((FightNPCImpl) fightNPC).getFightModel());
    }

    @Test
    void testCreateAllyNPC() {
        // Create an ally for the ally NPC
        final Ally ally = CharacterFactory.createAlly("asd", INT_CONST_10, INT_CONST_10, INT_CONST_10);

        // Create an ally NPC using the NPCFactory
        final NPC allyNPC = NPCFactory.createAllyNPC("Friend", INT_CONST_10, INT_CONST_20, INT_CONST_50, INT_CONST_30, ally);

        // Assert that the NPC is an instance of AllyNPCImpl
        assertTrue(allyNPC instanceof AllyNPCImpl);

        // Assert the properties of the NPC
        assertEquals("Friend", allyNPC.getName());
        assertEquals(INT_CONST_10, allyNPC.getX());
        assertEquals(INT_CONST_20, allyNPC.getY());
        assertEquals(INT_CONST_50, allyNPC.getHeight());
        assertEquals(INT_CONST_30, allyNPC.getWidth());
        assertEquals(ally, ((AllyNPCImpl) allyNPC).getAlly());
    }
}
