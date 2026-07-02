package unibo.exiled.model.item.utilities;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.factory.ItemFactory;
import unibo.exiled.model.item.factory.ItemFactoryImpl;
import unibo.exiled.utilities.ConstantsAndResourceLoader;

/**
 * A container of every existent item in the game.
 */
public final class ItemsContainer {
    private static final ItemFactory ITEM_FACTORY = new ItemFactoryImpl();
    private static final Random RANDOM = new Random();
    private static final Set<Item> ITEMS = Set.of(
            ITEM_FACTORY.createHealingItem(ItemNames.HEALTH_POTION.getName(),
                    "Restores health points.", 20.0),
            ITEM_FACTORY.createHealingItem(ItemNames.APPLE.getName(),
                    "A natural remedy for minor injuries.", 15.0),
            ITEM_FACTORY.createHealingItem(ItemNames.BANDAGE.getName(),
                    "A simple bandage to stop bleeding.", 10.0),
            ITEM_FACTORY.createPowerUpItem(ItemNames.STRENGTH_POTION.getName(),
                    "Increases strength permanently.",
                    0.3, AttributeIdentifier.ATTACK),
            ITEM_FACTORY.createPowerUpItem(ItemNames.DEFENSE_POTION.getName(),
                    "Boosts defense against attacks permanently.",
                    0.3, AttributeIdentifier.DEFENSE),
            ITEM_FACTORY.createUnUsableItem(ItemNames.BOLT_CRYSTAL.getName(),
                    "The bolt redemption crystal"),
            ITEM_FACTORY.createUnUsableItem(ItemNames.FIRE_CRYSTAL.getName(),
                    "The fire redemption crystal."),
            ITEM_FACTORY.createUnUsableItem(ItemNames.GRASS_CRYSTAL.getName(),
                    "The grass redemption crystal."),
            ITEM_FACTORY.createUnUsableItem(ItemNames.WATER_CRYSTAL.getName(),
                    "The water redemption crystal.")
    );

    private ItemsContainer() {
    }

    /**
     * Retrieves an item by its name from the container.
     *
     * @param name The name of the item to retrieve.
     * @return An Optional containing the item if found, or an empty Optional if not
     * found.
     */
    public static Optional<Item> getItemByName(final String name) {
        return ITEMS.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst();
    }

    /**
     * Retrieves a list of all items in the container.
     *
     * @return A set containing all items in the container.
     */
    public static Set<Item> getAllItems() {
        return ITEMS;
    }

    /**
     * Retrieves a random item of a specified type from the container.
     *
     * @param type The type of the item to retrieve.
     * @return An Optional containing a random item of the specified type if found,
     * or an empty Optional if no such item is found.
     */
    public static Optional<Item> getRandomItemByType(final ItemType type) {
        final List<Item> itemByType = ITEMS.stream()
                .filter(items -> items.getType().equals(type))
                .toList();

        if (itemByType.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(itemByType.get(RANDOM.nextInt(itemByType.size())));
    }

    /**
     * Retrieves a random item from the container.
     *
     * @return An Optional containing a random item of a random type if found,
     * or randomly also an empty Optional.
     */
    public static Optional<Item> getRandomItem() {
        final List<Item> healthItems = ITEMS.stream()
                .filter(item -> item.getType().equals(ItemType.HEALTH))
                .toList();

        final List<Item> powerupItems = ITEMS.stream()
                .filter(item -> item.getType().equals(ItemType.POWERUP))
                .toList();

        final double healthProbability = ConstantsAndResourceLoader.RANDOM_PROBABILITY_HEALING;
        final double emptyProbability = ConstantsAndResourceLoader.RANDOM_PROBABILITY_EMPTY;

        final double randomValue = RANDOM.nextDouble();

        if (randomValue < healthProbability) {
            return getRandomItemFromList(healthItems);
        } else if (randomValue < healthProbability + emptyProbability) {
            return Optional.empty();
        } else {
            return getRandomItemFromList(powerupItems);
        }
    }

    private static Optional<Item> getRandomItemFromList(final List<Item> itemList) {
        if (itemList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(itemList.get(RANDOM.nextInt(itemList.size())));
        }
    }

}
