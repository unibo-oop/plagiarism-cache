package net.pokemonbt.model.battle;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.Pair;

import java.io.Serial;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Base class for all {@link Trainer}, representing those
 * that have no special or particular behaviour.
 */
public class BaseTrainer implements Trainer {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String SPOTBUGS_EI = "EI_EXPOSE_REP";
    private static final String SPOTBUGS_EI_COMPONENT_MESS = "This value need to be a reference to the original"
            + "component as the architecture needs to call its methods.";
    private static final int MAX_TEAM_SIZE = 6;

    private final Map<String, Pair<Pokemon, Integer>> alive;
    private final Map<String, Pair<Pokemon, Integer>> dead;

    private final Map<String, Pokemon> flattened;
    private Pokemon currentlyActive;

    private boolean isDirty;

    /**
     * @param pokemons The list of {@link Pokemon} this trainer has.
     */
    public BaseTrainer(final Collection<Pokemon> pokemons) {
        Objects.requireNonNull(pokemons);
        if (pokemons.isEmpty() || pokemons.size() > MAX_TEAM_SIZE) {
            throw new IllegalArgumentException("Provided list of pokemon is too big: ("
                    .concat(String.valueOf(pokemons.size()))
                    .concat(") has to be between 1 and ")
                    .concat(String.valueOf(MAX_TEAM_SIZE)));
        }

        this.alive = new LinkedHashMap<>();
        this.dead = new LinkedHashMap<>();
        this.flattened = new LinkedHashMap<>();
        int i = 0;
        for (final Pokemon poke : pokemons) {
            if (poke.getStatComponent().getHP() == 0) {
                this.dead.put(poke.getID(), new Pair<>(poke, i));
            } else {
                this.alive.put(poke.getID(), new Pair<>(poke, i));
            }
            i++;
        }
        this.setupFlattened();
    }

    /**
     * @param pokemonID The id of the pokemon.
     * @return The corresponding exception message.
     */
    private String exceptionIdNotInTeam(final String pokemonID) {
        return "Provided pokemonID ("
                .concat(pokemonID)
                .concat(") is not contained in the trainer's team.");
    }

    /**
     * @param pokemonID The id of the pokemon.
     * @return The corresponding exception message.
     */
    private String exceptionInvalidId(final String pokemonID) {
        return "Provided pokemonID ("
                .concat(pokemonID)
                .concat(") is invalid.");
    }

    /**
     * @return The corresponding exception message.
     */
    private String exceptionEmptyId() {
        return "Provided pokemonID cannot be empty.";
    }

    /**
     * @param pokemonID The id of the pokemon.
     * @return The corresponding exception message.
     */
    private String exceptionCannotBeDead(final String pokemonID) {
        return "Provided Pokemon (id: "
                .concat(pokemonID)
                .concat(") cannot be a dead pokemon.");
    }

    /**
     * @param pokemonID {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean hasPokemon(final String pokemonID) {
        Objects.requireNonNull(pokemonID);
        return this.flattened.containsKey(pokemonID);
    }

    /**
     * @param pokemonID {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isDead(final String pokemonID) {
        Objects.requireNonNull(pokemonID);
        if (!this.hasPokemon(pokemonID)) {
            throw new IllegalArgumentException(this.exceptionIdNotInTeam(pokemonID));
        }
        return this.dead.containsKey(pokemonID);
    }

    /**
     * {@inheritDoc}
     * 
     * @param pokemonID {@inheritDoc}
     */
    @Override
    public void killPokemon(final String pokemonID) {
        Objects.requireNonNull(pokemonID);
        if (pokemonID.isBlank()) {
            throw new IllegalArgumentException(this.exceptionEmptyId());
        }

        if (!this.alive.containsKey(pokemonID)) {
            throw new IllegalArgumentException(this.exceptionInvalidId(pokemonID));
        }

        final Pair<Pokemon, Integer> poke = this.alive.remove(pokemonID);
        this.dead.put(poke.first().getID(), poke);
        this.isDirty = true;
    }

    /**
     * Sets up "flattened".
     */
    private void setupFlattened() {
        //final List<Map.Entry<String, Pair<Pokemon, Integer>>> list = new LinkedList<>(this.alive.entrySet());
        final List<Pair<String, Pair<Pokemon, Integer>>> list = new LinkedList<>();
        for (final var aliveEntry : this.alive.entrySet()) {
            list.add(new Pair<>(aliveEntry.getKey(), aliveEntry.getValue()));
        }
        for (final var deadEntry : this.dead.entrySet()) {
            list.add(new Pair<>(deadEntry.getKey(), deadEntry.getValue()));
        }
        /* Sort list of pokemons by their position in the team (Pair.second()) */
        list.sort((o1, o2) -> {
            if (o1.second().second() > o2.second().second()) {
                return 1;
            }
            return o1.second().second().equals(o2.second().second()) ? 0 : -1;
        });
        for (final var poke : list) {
            this.flattened.put(poke.first(), poke.second().first());
        }
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = SPOTBUGS_EI, justification = SPOTBUGS_EI_COMPONENT_MESS)
    public Map<String, Pokemon> getAllPokemons() {
        if (isDirty) {
            setupFlattened();
            this.isDirty = false;
        }
        return flattened;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public Map<String, Pokemon> getAlivePokemons() {
        return this.alive.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        x -> x.getValue().first()));
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public Map<String, Pokemon> getDeadPokemons() {
        return this.dead.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        x -> x.getValue().first()));
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "This value need to be a reference to the original pokemon as"
                    + "they interact with the environment this way."
    )
    public Pokemon getCurrentlyActive() {
        return this.currentlyActive;
    }

    /**
     * @param pokemonID {@inheritDoc}
     */
    @Override
    public void setCurrentlyActive(final String pokemonID) {
        Objects.requireNonNull(pokemonID);
        if (pokemonID.isBlank()) {
            throw new IllegalArgumentException(this.exceptionEmptyId());
        }
        if (!this.hasPokemon(pokemonID)) {
            throw new IllegalArgumentException(this.exceptionIdNotInTeam(pokemonID));
        }
        this.currentlyActive = this.flattened.get(pokemonID);
    }

    /**
     * {@inheritDoc}
     *
     * @param pokemonID {@inheritDoc}
     */
    @Override
    public void swapPokemon(final String pokemonID) {
        Objects.requireNonNull(pokemonID);
        if (pokemonID.isBlank()) {
            throw new IllegalArgumentException(this.exceptionEmptyId());
        }
        if (!this.hasPokemon(pokemonID)) {
            throw new IllegalArgumentException(this.exceptionIdNotInTeam(pokemonID));
        }
        if (this.isDead(pokemonID)) {
            throw new IllegalArgumentException(this.exceptionCannotBeDead(pokemonID));
        }
        this.currentlyActive.getMoveComponent().switchOut();
        this.currentlyActive.getConditionComponent().removeVolatileConditions();

        this.currentlyActive = this.alive.get(pokemonID).first();

        this.currentlyActive.getMoveComponent().switchIn();
    }

    /**
     * {@inheritDoc}
     *
     * @param o {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BaseTrainer that = (BaseTrainer) o;
        return Objects.equals(this.alive, that.alive)
                && Objects.equals(this.dead, that.dead)
                && Objects.equals(this.currentlyActive, that.currentlyActive);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                this.alive,
                this.dead,
                this.currentlyActive);
    }
}
