package net.pokemonbt.model.move.components.special;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.move.components.AbstractBehaviourDecorator;
import net.pokemonbt.model.move.components.MoveBehaviour;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Special Behaviour exclusive to the {@link Move} "Photon Geyser".
 */
public class PhotonGeyser extends AbstractBehaviourDecorator {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public PhotonGeyser(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);

        values.ifPresent(val -> {
            throw new IllegalArgumentException("values must be an empty Optional.");
        });
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link PhotonGeyser} to take the values from.
     */
    public PhotonGeyser(final MoveBehaviour base, final PhotonGeyser toCopy) {
        super(base);
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {

        this.modifyDamageStat(
            () -> Optional.of(Math.max(
                user.getStatComponent().getStat(PokeStatType.ATK),
                user.getStatComponent().getStat(PokeStatType.SPA)
            ))
        );

        return super.apply(user, opponent);
    }
}
