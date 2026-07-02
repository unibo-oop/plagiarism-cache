package rogue.model.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import rogue.model.items.armor.ArmorImpl;
import rogue.model.items.armor.ArmorType;
import rogue.model.items.food.FoodImpl;
import rogue.model.items.food.FoodType;
import rogue.model.items.potion.PotionImpl;
import rogue.model.items.potion.PotionType;
import rogue.model.items.rings.RingImpl;
import rogue.model.items.rings.RingType;
import rogue.model.items.scroll.ScrollImpl;
import rogue.model.items.scroll.ScrollType;
import rogue.model.items.weapons.BaseWeapon;
import rogue.model.items.weapons.WeaponType;

/**
 * An implementation for an {@link ItemFactory}.
 *
 */
public final class ItemFactoryImpl implements ItemFactory {

    private static final int MIN_ITEMS = 15;
    private static final int MAX_ITEMS = 20;
    private static final int ITEM_COUNT_DIFF = MAX_ITEMS - MIN_ITEMS;

    private static final Random RAND = new Random();

    enum Items {
        ARMOR(0, () -> new ArmorImpl(Arrays.asList(ArmorType.values()).get(RAND.nextInt(ArmorType.values().length)))),
        FOOD(1, () -> new FoodImpl(Arrays.asList(FoodType.values()).get(RAND.nextInt(FoodType.values().length)))),
        POTION(2, () -> new PotionImpl(Arrays.asList(PotionType.values()).get(RAND.nextInt(PotionType.values().length)))),
        RING(3, () -> new RingImpl(Arrays.asList(RingType.values()).get(RAND.nextInt(RingType.values().length)))), 
        SCROLL(4, () -> new ScrollImpl(Arrays.asList(ScrollType.values()).get(RAND.nextInt(ScrollType.values().length)))),
        WEAPON(5, () -> new BaseWeapon(Arrays.asList(WeaponType.values()).get(RAND.nextInt(WeaponType.values().length))));

        private final int value;
        private final Supplier<Item> itemSupplier;

        Items(final int value, final Supplier<Item> itemSupplier) {
            this.value = value;
            this.itemSupplier = itemSupplier;
        }
    }

    /**
     * Creates a list containing random items.
     * @param quantity of wanted items.
     * @return List containing randomly generated items.
     */
    public List<Item> getItems(final int quantity) {
        final ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            final int itemIndex = RAND.nextInt(Items.values().length);
            Arrays.asList(Items.values()).stream()
                .filter(o -> o.value == itemIndex)
                .forEach(o -> items.add(o.itemSupplier.get()));
        }
        return items;
    }

    /**
     * Creates a List of random items with a random quantity.
     * @return a list of random items.
     */
    public List<Item> getItems() {
        final var itemCount = RAND.nextInt(ITEM_COUNT_DIFF);
        return getItems(MIN_ITEMS + itemCount);
    }
}
