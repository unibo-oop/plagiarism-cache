package net.pokemonbt.model.move;

import java.util.List;
import java.util.Objects;
import java.util.Locale.Category;

import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.Unique;

/**
 * An object that contains all the generic values usefull
 * to conduct a Battle.
 */
public interface Move extends Unique {

    /**
     * @return The {@link PokeType}.
     */
    PokeType getType();

    /**
     * @return The {@link Category} of the {@link Move}, it indicates how the
     *      Damage behaves.
     */
    MoveCategory getCategory();

    /**
     * @return A {@link List} containing all {@link SubCategory}. Often empty.
     */
    List<SubCategory> getSubcategory();

    /**
     * @param sub The subCathegory to check.
     * @return 'true' if it present, 'false' otherwise.
     */
    boolean isSubcathegory(SubCategory sub);

    /**
     * @return 'true' when this {@link Move} makes contact of the opponent. 'false' otherwise.
     */
    boolean isContact();

    /**
     * @return The 'priority' of this {@link Move}, going from '-7' to '+5'
     *      indicating a special ordering of the action of this move over the opponent's.
     */
    int getPriority();

    /**
     * @return The starting PPs of this {@link Move}.
     */
    int getMaxPP();

    /**
     * @return The base 'power' of this {@link Move}.
     */
    int getPower();

    /**
     * @return The 'accuracy' of this {@link Move}: between '0.0' and '1.0' when the it needs to
     *      be calculated wheter it hits or not. '1.1' otherwise.
     */
    float getAccuracy();

    /**
     * @param user The {@link Pokemon} using this {@link Move}
     * @param opponent The target of the Attack.
     * @return 'true' if the Move is successful, 'false' if it misses for any reason.
     */
    boolean use(Pokemon user, Pokemon opponent);

    /**
     * Returns a copy of the given {@link Move}.
     * 
     * @param object An Instance of {@link Move} to copy using the its Contructor.
     * @return A new instance of the same {@link Move} subclass and 
     *      with the same values.
     *      A call on .equals() between the objet the the returned value will 
     *      result 'true'.
     */
    static Move copyOf(final Move object) {
        Objects.requireNonNull(object);

        if (SimpleMove.class.isInstance(object)) {
            return new SimpleMove.Builder()
            .prepareCopy(SimpleMove.class.cast(object))
            .build();
        } else {
            try {
                final Class<? extends Move> copyClass = object.getClass();
                return copyClass.getDeclaredConstructor(copyClass)
                .newInstance(
                    copyClass.cast(object)
                );
            } catch (final ReflectiveOperationException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
