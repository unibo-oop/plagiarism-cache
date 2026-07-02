package it.tbt.model.world.impl;

import it.tbt.model.entities.EndEntity;
import it.tbt.model.entities.characters.CharacterFactory;
import it.tbt.model.entities.characters.skills.Skill;
import it.tbt.model.entities.characters.skills.SkillFactory;
import it.tbt.model.entities.items.Armor;
import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.items.Weapon;
import it.tbt.model.entities.items.factories.AntidoteFactory;
import it.tbt.model.entities.items.factories.ArmorFactory;
import it.tbt.model.entities.items.factories.PotionFactory;
import it.tbt.model.entities.items.factories.WeaponFactory;
import it.tbt.model.entities.npc.impl.NPCFactory;
import it.tbt.model.fight.impl.FightModelImpl;
import it.tbt.model.roomlink.RoomLink;
import it.tbt.model.roomlink.RoomLinkImpl;
import it.tbt.model.shop.Shop;
import it.tbt.model.world.api.Room;
import it.tbt.model.world.api.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

/**
 * Factory with static methods for creation of World objects.
 */
public final class WorldFactory {
    private static final Random RND = new Random();
    private static final int STANDARD_ENTITY_HEIGHT = 50,
            STANDARTD_ENTITY_WIDTH = 50,
            TOP_LEFT_X = RoomImpl.DEFAULT_WIDTH_ROOM / 6,
            TOP_LEFT_Y = RoomImpl.DEFAULT_HEIGHT_ROOM / 6 * 5,
            TOP_RIGHT_X = RoomImpl.DEFAULT_WIDTH_ROOM / 6 * 5,
            TOP_RIGHT_Y = RoomImpl.DEFAULT_HEIGHT_ROOM / 6,
            BOTTOM_RIGHT_X = RoomImpl.DEFAULT_WIDTH_ROOM / 6 * 5,
            BOTTOM_RIGHT_Y = RoomImpl.DEFAULT_HEIGHT_ROOM / 6 * 5,
            BOTTOM_LEFT_X = RoomImpl.DEFAULT_WIDTH_ROOM / 6,
            BOTTOM_LEFT_Y = RoomImpl.DEFAULT_HEIGHT_ROOM / 6 * 5,
            CENTER_X = RoomImpl.DEFAULT_WIDTH_ROOM / 6 * 3,
            CENTER_Y = RoomImpl.DEFAULT_WIDTH_ROOM / 6 * 3;

    //constants to suppress checkstyle
    private static final int INT_CONST_10 = 10;
    private static final int INT_CONST_70 = 70;

    private static final int INT_CONST_50 = 50;
    private static final int INT_CONST_10000 = 10_000;

    /**
     * Utility class should not have public constructor.
     */
    private WorldFactory() { }

    /**
     * Create World implementation with default implementation.
     *
     * @return world object.
     */
    public static World createWorldDefault() {
        final World w = new WorldImpl();
        final Room allyRoom = new RoomImpl(RoomImpl.DEFAULT_WIDTH_ROOM, RoomImpl.DEFAULT_HEIGHT_ROOM);
        final Room itemRoom = new RoomImpl(RoomImpl.DEFAULT_WIDTH_ROOM, RoomImpl.DEFAULT_HEIGHT_ROOM);
        final Room shopRoom = new RoomImpl(RoomImpl.DEFAULT_WIDTH_ROOM, RoomImpl.DEFAULT_HEIGHT_ROOM);
        final Room fightRoom = new RoomImpl(RoomImpl.DEFAULT_WIDTH_ROOM, RoomImpl.DEFAULT_HEIGHT_ROOM);
        final Room healRoom = new RoomImpl(RoomImpl.DEFAULT_WIDTH_ROOM, RoomImpl.DEFAULT_HEIGHT_ROOM);
        final Room endRoom = new RoomImpl(RoomImpl.DEFAULT_WIDTH_ROOM, RoomImpl.DEFAULT_HEIGHT_ROOM);
        final RoomLink roomLink1 = new RoomLinkImpl("link", CENTER_X, CENTER_Y, STANDARTD_ENTITY_WIDTH,
                STANDARD_ENTITY_HEIGHT, allyRoom, itemRoom);
        allyRoom.addEntity(roomLink1);
        final RoomLink roomLink2 = new RoomLinkImpl("link2", CENTER_X, CENTER_Y, STANDARTD_ENTITY_WIDTH,
                STANDARD_ENTITY_HEIGHT, itemRoom, shopRoom);
        itemRoom.addEntity(roomLink2);
        final RoomLink roomLink3 = new RoomLinkImpl("link3", CENTER_X, CENTER_Y, STANDARTD_ENTITY_WIDTH,
                STANDARD_ENTITY_HEIGHT, shopRoom, fightRoom);
        shopRoom.addEntity(roomLink3);
        final RoomLink roomLink4 = new RoomLinkImpl("link4", CENTER_X, CENTER_Y, STANDARTD_ENTITY_WIDTH,
                STANDARD_ENTITY_HEIGHT, fightRoom, healRoom);
        fightRoom.addEntity(roomLink4);
        final RoomLink roomLink5 = new RoomLinkImpl("link5", CENTER_X, CENTER_Y, STANDARTD_ENTITY_WIDTH,
                STANDARD_ENTITY_HEIGHT, healRoom, endRoom);
        healRoom.addEntity(roomLink5);
        endRoom.addEntity(new EndEntity("EntityEnd", CENTER_X, CENTER_Y, STANDARTD_ENTITY_WIDTH, STANDARD_ENTITY_HEIGHT,
                "You finished the game!\nPress any key to quit."));
        final Map<Item, Double> drops = new HashMap<>();
        for (final Item item : PotionFactory.getInstance().getItems()) {
            drops.put(item, 0.5);
        }

        fightRoom.addEntity(NPCFactory.createFightNPC("Roberto", TOP_LEFT_X, TOP_LEFT_Y, STANDARD_ENTITY_HEIGHT,
                STANDARTD_ENTITY_WIDTH, new FightModelImpl(INT_CONST_70, drops)));

        // shop
        final Map<Item, Integer> shopItems = new HashMap<>();
        for (final Item item : PotionFactory.getInstance().getItems()) {
            shopItems.put(item, INT_CONST_10); // 10 of each potions
        }
        shopItems.put(AntidoteFactory.getInstance().getAntidote(), INT_CONST_10);
        final Set<Weapon> weapons = WeaponFactory.getInstance().getItems();
        shopItems.put(weapons.stream().skip(RND.nextInt(weapons.size() - 1)).findFirst().get(), 1); // a random weapon
        shopItems.put(weapons.stream().skip(RND.nextInt(weapons.size() - 1)).findFirst().get(), 1); // a random weapon
        final Set<Armor> armors = ArmorFactory.getInstance().getItems();
        shopItems.put(armors.stream().skip(RND.nextInt(armors.size() - 1)).findFirst().get(), 1); // a random weapon
        shopItems.put(armors.stream().skip(RND.nextInt(armors.size() - 1)).findFirst().get(), 1); // a random weapon
        final int wallet = INT_CONST_10000;
        shopRoom.addEntity(NPCFactory.createShopNPC("Merchant", TOP_RIGHT_X, TOP_RIGHT_Y, STANDARD_ENTITY_HEIGHT,
                STANDARTD_ENTITY_WIDTH, new Shop(shopItems, wallet)));

        // AllyNPC
        final List<Skill> skills = new ArrayList<>();
        skills.add((Skill) SkillFactory.getFactory().getSkills().toArray()[0]);
        allyRoom.addEntity(NPCFactory.createAllyNPC("Ally", BOTTOM_LEFT_X, BOTTOM_LEFT_Y, STANDARD_ENTITY_HEIGHT,
                STANDARTD_ENTITY_WIDTH, CharacterFactory.createAlly("Pippo", INT_CONST_50, INT_CONST_50, INT_CONST_50, skills)));

        // Heal
        healRoom.addEntity(NPCFactory.createHealerNPC("Healer", BOTTOM_LEFT_X, BOTTOM_LEFT_Y, STANDARD_ENTITY_HEIGHT,
                STANDARTD_ENTITY_WIDTH, INT_CONST_10));

        // Item
        final Map<Item, Integer> itemsMap = new HashMap<>();
        itemsMap.put((Armor) ArmorFactory.getInstance().getItems().toArray()[0], 1);
        itemRoom.addEntity(NPCFactory.createItemNPC("Gifter", BOTTOM_RIGHT_X, BOTTOM_RIGHT_Y, STANDARD_ENTITY_HEIGHT,
                STANDARTD_ENTITY_WIDTH, itemsMap));

        // Room links
        w.addRoom(fightRoom);
        w.addRoom(healRoom);
        w.addRoom(allyRoom);
        w.addRoom(itemRoom);
        w.addRoom(shopRoom);
        w.addRoom(endRoom);
        w.setStartRoom(allyRoom);
        return w;
    }
}
