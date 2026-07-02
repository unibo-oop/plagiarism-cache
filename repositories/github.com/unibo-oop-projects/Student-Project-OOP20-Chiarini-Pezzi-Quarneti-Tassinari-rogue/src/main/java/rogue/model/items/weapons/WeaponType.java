package rogue.model.items.weapons;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.util.Pair;
import rogue.model.items.weapons.Weapon.Use;

/**
 * Represents an enumeration for declaring weapon types.
 * 
 * Each weapon type can be either thrown or used "by hand".
 * Depending on the use (modeled with the enumeration {@link Use}) the damage change.
 * The first field keeps track how the damage is calculated when used "by hand".
 * The second field keeps track how the damage is calculated when thrown.
 * The damage is an {@link Integer} obtained taking key random numbers from 1 to value
 * and then summing them.
 * 
 * The third field keeps track the armor's name.
 */
public enum WeaponType {

    /**
     * Mace.
     */
    MACE(new Pair<>(2, 4), new Pair<>(1, 3), "Mace"), 
    /**
     * Long sword.
     */
    LONG_SWORD(new Pair<>(3, 4), new Pair<>(1, 2), "Long sword"), 
    /**
     * Short bow.
     */
    SHORT_BOW(new Pair<>(1, 1), new Pair<>(1, 1), "Short Bow"),
    /**
     * Arrow.
     */
    ARROW(new Pair<>(1, 1), new Pair<>(2, 3), "Arrow"), 
    /**
     * Dagger.
     */
    DAGGER(new Pair<>(1, 6), new Pair<>(1, 4), "Dagger"), 
    /**
     * Two handed sword.
     */
    TWO_HAND_SWORD(new Pair<>(4, 4), new Pair<>(1, 2), "2 hand sword"),
    /**
     * Dart.
     */
    DART(new Pair<>(1, 1), new Pair<>(1, 3), "Dart"),
    /**
     * Crossbow.
     */
    CROSSBOW(new Pair<>(1, 1), new Pair<>(1, 1), "Crossbow"), 
    /**
     * Shuriken, a hidden hand blade.
     */
    SHURIKEN(new Pair<>(1, 2), new Pair<>(2, 5), "Shuriken"),
    /**
     * Spear.
     */
    SPEAR(new Pair<>(2, 3), new Pair<>(1, 6), "Spear");

    /**
     * The accuracy is the same for each weapon.
     */
    private static final int ACCURACY = 0;
    private final Map<Weapon.Use, Pair<Integer, Integer>> damageMap;
    private final String name;
    private final Random rnd = new Random();

    WeaponType(final Pair<Integer, Integer> handledDamage, 
            final Pair<Integer, Integer> throwableDamage, final String name) {
        this.name = name;
        this.damageMap = new EnumMap<>(Use.class);
        this.damageMap.put(Use.HANDLED, handledDamage);
        this.damageMap.put(Use.THROWN, throwableDamage);
    }

    /**
     * @param use 
     *          the use of the weapon
     * @return a damage supplier
     */
    protected Supplier<Integer> getDamageSupplier(final Use use) {
        return () -> IntStream.range(0, damageMap.get(use).getKey())
                .boxed()
                .collect(Collectors.summingInt(i -> rnd.nextInt(damageMap.get(use).getValue()) + 1));
    }

    /**
     * @return the weapon accuracy
     */
    protected int getAccuracy() {
        return ACCURACY;
    }

    /**
     * @return a String representing the weapon name
     */
    protected String getName() {
        return this.name;
    }

}
