package net.pokemonbt.model.condition.components;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import net.pokemonbt.utility.Pair;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.pokemonbt.model.battle.BattleEvent;
import net.pokemonbt.model.battle.MoveDamageInstance;
import net.pokemonbt.model.condition.BaseCondition;
import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.condition.Condition;
import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 *  Factory for the creation and storage of the conditions.
 */
public final class ConditionDispenser {

    static final Map<String, Condition> CONDITION_CACHE = new LinkedHashMap<>();

    private ConditionDispenser() { }

    /**
     * @param condition the json object containing the condition data
     */
    public static void createCondition(final JsonObject condition) {
        Objects.requireNonNull(condition);
        final String conditionId = condition.get("id").getAsString();
        final String eventTrigger = condition.get("eventTrigger").getAsString();
        final Boolean permanent = condition.get("permanent").getAsBoolean();
        final List<PokeType> immunities = new ArrayList<>();
        if (condition.has("immune")) {

        condition.get("immune").getAsJsonArray().asList().stream()
        .forEach(type -> immunities.add(PokeType.stringToType(type.getAsString()))); 

        }

        final ConditionBehaviour condBehaviour = createBehaviour(conditionId, 
            condition.get("behaviour")
            .getAsJsonArray()
            .asList().stream()
                .map(JsonElement::getAsString)
                .map(x -> new Pair<>(x, Optional.ofNullable(condition.get(lowerFirst(x)))))
                .toList());

        final Condition newCondition = new BaseCondition(
            conditionId, 
            condBehaviour, 
            BattleEvent.fromString(eventTrigger), 
            permanent, 
            immunities, 
            condition.get("displayName").getAsString(), 
            condition.get("description").getAsString());

        CONDITION_CACHE.put(conditionId, newCondition);
    }

    /**
     * Creates the condition behaviour by reading the json file and applying the decorators.
     * 
     * @param id the id of the condition to create
     * @param behaviours the list of behaviours to apply to the condition
     * @return the created behaviour
     */
    private static ConditionBehaviour createBehaviour(final String id, 
        final List<Pair<String, Optional<JsonElement>>> behaviours) {

        ConditionBehaviour base = new ConditionBehaviour() {
            @Override
            public boolean trigger(final Pokemon player, final Pokemon opponent) {
                return true;
            }

            @Override
            public boolean modifyMove(final MoveDamageInstance damage, final boolean isUser) {
                return true;
            }

            @Override
            public String getID() {
                return id;
            }

            @Override
            public boolean onRemove(final Pokemon pokemon) {
                return true;
            }

        };

        for (final var behaviour : behaviours) {
            final String behaviourName = behaviour.first();

            final Optional<JsonObject> behaviourValues = behaviour.second().isPresent()
            ? Optional.of(behaviour.second().get().getAsJsonObject())
            : Optional.empty();

            try {
                final Class<?> behaviourClass = Class.forName(
                    AbstractConditionDecorator.class
                    .getPackageName()
                    .concat(".".concat(behaviourName))
                );

                base = (ConditionBehaviour) behaviourClass.getDeclaredConstructor(
                    ConditionBehaviour.class,
                Optional.class)
                .newInstance(
                    base,
                    behaviourValues);

            } catch (final ReflectiveOperationException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return base;
    }

    /**
     * @param condID the Id of the condition wanted
     * @return a copy of the condition created by the factory
     */
    public static Condition get(final String condID) {
        return Condition.copyOf(CONDITION_CACHE.get(condID));
    }

    /**
     * @param string the name of the behaviour to lower
     * @return the string with the first letter lowered
     */
    public static String lowerFirst(final String string) {
        return string.substring(0, 1).toLowerCase(Locale.ROOT) 
        + string.substring(1); 
    }
}
