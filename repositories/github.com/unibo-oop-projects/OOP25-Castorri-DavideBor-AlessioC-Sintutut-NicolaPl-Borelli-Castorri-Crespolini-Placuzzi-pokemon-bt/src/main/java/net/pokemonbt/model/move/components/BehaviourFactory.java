package net.pokemonbt.model.move.components;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.utility.Pair;

/**
 * Static Factory for an easier creation of decorated Behaviour.
 */
public final class BehaviourFactory {

    private BehaviourFactory() { }

    /**
     * Creates a new {@link MoveBahviour} from it's 'id' and 'values'.
     * 
     * @param id The {@link Move} id.
     * @param behaviours A list of ordered behaviours as described in the Resource file. The 'String'
     *      indicates the decoration class, the 'Optional' contains the values that decoration may need
     *      to work correctly.
     * @return The {@link MoveBehaviour} decorated accordingly to the List passed.
     */
    public static MoveBehaviour get(final String id, final List<Pair<String, Optional<JsonElement>>> behaviours) {

        MoveBehaviour base = new Base(id);

        for (final var behaviour : behaviours) {
            String className = behaviour.first();

            if ("Special".equals(className)) {
                // Formatting the Behaviour Name to fit 'special' Behaviour file organization.
                final String classId = behaviour.second().get().getAsString();

                className = className.toLowerCase(Locale.ROOT).concat(".".concat(classId));
                behaviour.second(Optional.empty());
            }

            final Optional<JsonObject> values = behaviour.second().isPresent()
                ? Optional.of(behaviour.second().get().getAsJsonObject())
                : Optional.empty();

            try {
                // Get the Class corresponding the Behaviour by getting the package,
                // and adding the correct class name passed for the creation.
                final Class<?> behaviourClass = Class.forName(
                    AbstractBehaviourDecorator.class
                    .getPackageName()
                    .concat(".".concat(className))
                );

                // Get a specific Constructor of the Behaviour, and create a new Instance.
                base = (MoveBehaviour) behaviourClass.getDeclaredConstructor(
                    MoveBehaviour.class,
                    Optional.class
                )
                .newInstance(
                    base,
                    values
                );
            } catch (final ReflectiveOperationException e) {
                throw new IllegalArgumentException(e);
            }
        }

        return base;
    }
}
