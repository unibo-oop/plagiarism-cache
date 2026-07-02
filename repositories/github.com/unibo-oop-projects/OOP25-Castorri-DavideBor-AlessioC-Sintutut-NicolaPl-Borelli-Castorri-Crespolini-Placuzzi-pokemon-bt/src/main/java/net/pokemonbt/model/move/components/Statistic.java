package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.StatMod;
import net.pokemonbt.model.pokemon.components.StatComponent;
import net.pokemonbt.utility.RandomUtility;

/**
 * A {@link Move} with this Behaviour applies one or multiple buffs/debuffs to the 
 * {@link Pokemon} using it.
 */
public final class Statistic extends AbstractBehaviourDecorator {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Float chance;
    private final Map<PokeStatType, StatMod> selfMods;
    private final Map<PokeStatType, StatMod> oppMods;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Statistic(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);
        this.selfMods = new EnumMap<>(PokeStatType.class);
        this.oppMods = new EnumMap<>(PokeStatType.class);

        this.chance = values.orElseThrow().get("chance").getAsFloat();

        for (final var item : values.orElseThrow().get("modifiers").getAsJsonArray()) {
            final var mod = item.getAsJsonObject();
            if (mod.get("self").getAsBoolean()) {
                this.selfMods.put(
                    PokeStatType.valueOf(mod.get("stat").getAsString()),
                    new StatMod(mod.get("stage").getAsInt(), false, true)
                );
            } else {
                this.oppMods.put(
                    PokeStatType.valueOf(mod.get("stat").getAsString()),
                    new StatMod(mod.get("stage").getAsInt(), false, true)
                );
            }
        }
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link Statistic} to take the values from.
     */
    public Statistic(final MoveBehaviour base, final Statistic toCopy) {
        super(base);

        this.chance = toCopy.chance;
        this.selfMods = Map.copyOf(toCopy.selfMods);
        this.oppMods = Map.copyOf(toCopy.oppMods);
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {
        if (this.selfMods.isEmpty() && this.oppMods.isEmpty()) {
          throw new IllegalStateException(
                this.getID().concat(" has the ")
                .concat(this.getClass().getSimpleName())
                .concat(" Decorator, but doesn't contain any stat modifier.")
            );
        }

        if (super.apply(user, opponent)) {
            if (RandomUtility.check(this.chance)) {

                this.selfMods.forEach((key, value) -> {
                    this.addModifierToComponent(
                        key, 
                        value, 
                        user.getStatComponent()
                    );
                });

                this.oppMods.forEach((key, value) -> {
                    this.addModifierToComponent(
                        key, 
                        value, 
                        opponent.getStatComponent()
                    );
                });
            }
            return true;
        }
        return false;
    }

    /**
     * Adds a modifier to a given stat component. If one with the same
     *      ID is already present it is updated accordingly.
     * 
     * @param type The {@link PokeStatType} to be modified.
     * @param mod The {@link StatMod} to be applied.
     * @param component The {@link StatComponent} of the {@link Pokemon}
     *      that will receive the statistic modifier.
     */
    private void addModifierToComponent(
        final PokeStatType type, 
        final StatMod mod, 
        final StatComponent component
    ) {
        //If the a Modifier is already present it needs to be updated.
        if (!component.addStatMod(type, this.getID(), mod)) {

            final StatMod oldMod = component
            .getStatMod(type, this.getID())
            .get();

            final var newMod = new StatMod(
                oldMod.stageLevel() + mod.stageLevel(), 
                oldMod.canBeTransferred(),
                oldMod.canBeRemoved()
            );

            component.updateStatMod(type, this.getID(), newMod);
        }
    }
}
