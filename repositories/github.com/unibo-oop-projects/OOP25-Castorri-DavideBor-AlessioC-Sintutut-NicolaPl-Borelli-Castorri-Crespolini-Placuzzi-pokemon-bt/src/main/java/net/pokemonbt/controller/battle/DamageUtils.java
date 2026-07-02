package net.pokemonbt.controller.battle;

import com.google.gson.JsonObject;
import net.pokemonbt.model.battle.DamageTypeMultiplier;
import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.Pokemon;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Objects;

/**
 * Class containing various damage-related utilities.
 */
public final class DamageUtils {
    private static final int INSTAKILL = 9999;
    private static Map<PokeType, Map<PokeType, DamageTypeMultiplier>> typeMult;

    /**
     * Private no-argument constructor to prevent instantiation.
     */
    private DamageUtils() { }

    /**
     * @return a damage to instantly kill a {@link Pokemon}
     */
    public static int getInstaKill() {
        return INSTAKILL;
    }

    /**
     * @param obj The JSON object of the type multipliers.
     */
    public static void initialize(final JsonObject obj) {
        typeMult = new LinkedHashMap<>();

        for (final PokeType type1 : PokeType.values()) {
            typeMult.put(type1, new LinkedHashMap<>());
            final String key1 = type1.name().toLowerCase(Locale.ROOT);
            for (final PokeType type2 : PokeType.values()) {
                if (type1 == PokeType.NONE) {
                    typeMult.get(type1).put(type2, DamageTypeMultiplier.NORMAL);
                } else {
                    final String key2 = type2.name().toLowerCase(Locale.ROOT);

                    final JsonObject firstTypeValues = obj.get(key1).getAsJsonObject();
                    typeMult.get(type1).put(type2,
                            firstTypeValues.has(key2)
                                    ? DamageTypeMultiplier.intToTypeMult(firstTypeValues.get(key2).getAsInt())
                                    : DamageTypeMultiplier.NORMAL
                    );
                }
            }
        }
    }

    /**
     * @param attackType The {@link PokeType} of the damage.
     * @param targetType1 The {@link PokeType} of the {@link Pokemon} receiving the damage.
     * @return The final type multiplier.
     */
    public static float getTypeMultiplier(final PokeType attackType, final PokeType targetType1) {
        return getTypeMultiplier(attackType, targetType1, PokeType.NONE);
    }

    /**
     * @param attackType The {@link PokeType} of the damage.
     * @param targetType1 The first {@link PokeType} of the {@link Pokemon} receiving the damage.
     * @param targetType2 The second {@link PokeType} of the {@link Pokemon} receiving the damage.
     *                    If the pokemon doesn't have one, set it to {@code PokeType.NONE}.
     * @return The final type multiplier.
     */
    public static float getTypeMultiplier(
            final PokeType attackType,
            final PokeType targetType1,
            final PokeType targetType2
    ) {
        Objects.requireNonNull(attackType);
        Objects.requireNonNull(targetType1);
        Objects.requireNonNull(targetType2);

        /* Sum the multipliers' values as if they were "stages" */
        final int value1 = typeMult.get(attackType).get(targetType1).getValue();
        final int value2 = targetType2 == PokeType.NONE ? 0
                : typeMult.get(attackType).get(targetType2).getValue();
        if (value1 == DamageTypeMultiplier.NULL.getValue()
                || value2 == DamageTypeMultiplier.NULL.getValue()) {
            return DamageTypeMultiplier.NULL.getMultiplier();
        } else {
            return DamageTypeMultiplier.getMultiplier(value1 + value2);
        }
    }
}
