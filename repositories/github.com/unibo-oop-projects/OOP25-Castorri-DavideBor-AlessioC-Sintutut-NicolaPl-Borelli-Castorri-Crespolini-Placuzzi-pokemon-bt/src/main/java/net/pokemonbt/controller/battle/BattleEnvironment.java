package net.pokemonbt.controller.battle;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import net.pokemonbt.model.battle.BaseTrainer;
import net.pokemonbt.model.battle.Trainer;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.TeamType;

/**
 * Singleton structure used to represent the current battle environment and
 * state,
 * holding all the pokemons, weather and terrain. Also holds multiple
 * supporting methods for base game mechanics such as swapping pokemons
 * mid-battle.
 * The "singleton" pattern is used here in order to access the battlefield's
 * current state from anywhere.
 */
public final class BattleEnvironment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Trainer playerTrainer;
    private final Trainer enemyTrainer;

    /**
     * @param playerTrainer   The list of {@link Pokemon} the player trainer has.
     * @param enemyTrainer    The list of {@link Pokemon} the enemy trainer has.
     * @param playerCurrentID The ID of the {@link Pokemon} from the player's team
     *                        to set as the active one.
     * @param enemyCurrentID  The ID of the {@link Pokemon} from the enemy's team
     *                        to set as the active one.
     */
    public BattleEnvironment(
            final Collection<Pokemon> playerTrainer,
            final Collection<Pokemon> enemyTrainer,
            final String playerCurrentID,
            final String enemyCurrentID) {
        Objects.requireNonNull(playerTrainer);
        Objects.requireNonNull(enemyTrainer);

        if (playerTrainer.isEmpty()) {
            throw new IllegalArgumentException("Player trainer cannot have 0 pokemons.");
        }
        if (enemyTrainer.isEmpty()) {
            throw new IllegalArgumentException("Enemy trainer cannot have 0 pokemons.");
        }

        this.playerTrainer = new BaseTrainer(playerTrainer);
        enemyTrainer.forEach(x -> x.setTeamType(TeamType.ENEMY));
        this.enemyTrainer = new BaseTrainer(enemyTrainer);

        if (playerTrainer.stream().noneMatch(x -> x.getID().equals(playerCurrentID))) {
            throw new IllegalArgumentException(exceptionInvalidID(playerCurrentID));
        } else if (enemyTrainer.stream().noneMatch(x -> x.getID().equals(enemyCurrentID))) {
            throw new IllegalArgumentException(exceptionInvalidID(enemyCurrentID));
        }
        this.playerTrainer.setCurrentlyActive(playerCurrentID);
        this.enemyTrainer.setCurrentlyActive(enemyCurrentID);
    }

    /**
     * @param pokemonID The id of the pokemon.
     * @return The corresponding exception message.
     */
    private static String exceptionInvalidID(final String pokemonID) {
        return "The provided pokemonID ("
                .concat(pokemonID)
                .concat(") is invalid.");
    }

    /**
     * @param teamType The team to get the trainer of.
     * @return The list of pokemons associated with the provided team type.
     */
    private Trainer getTrainer(final TeamType teamType) {
        return teamType == TeamType.PLAYER
                ? this.playerTrainer
                : this.enemyTrainer;
    }

    /**
     * @param teamType The team to get the opposite trainer of.
     * @return The list of pokemons associated with the opposite of the provided
     *         team type.
     */
    private Trainer getOtherTrainer(final TeamType teamType) {
        return teamType == TeamType.ENEMY
                ? this.playerTrainer
                : this.enemyTrainer;
    }

    /**
     * @param teamType  The team to check.
     * @param pokemonID The ID of the pokemon to check.
     * @return True if the trainer has that pokemon in their team, false otherwise.
     */
    public boolean hasPokemon(final TeamType teamType, final String pokemonID) {
        return getTrainer(teamType).hasPokemon(pokemonID);
    }

    /**
     * @param teamType The team to get all the pokemons of.
     * @return A map of all the trainer's pokemons, mapping
     *         the pokemon's id to the pokemon itself.
     */
    public Map<String, Pokemon> getAllPokemons(final TeamType teamType) {
        return getTrainer(teamType).getAllPokemons();
    }

    /**
     * @param teamType The team to get all the alive pokemons of.
     * @return A map of all the alive trainer's pokemons, mapping
     *         the pokemon's id to the pokemon itself.
     */
    public Map<String, Pokemon> getAlivePokemons(final TeamType teamType) {
        return getTrainer(teamType).getAlivePokemons();
    }

    /**
     * @param teamType The team to get all the dead pokemons of.
     * @return A map of all the dead trainer's pokemons, mapping
     *         the pokemon's id to the pokemon itself.
     */
    public Map<String, Pokemon> getDeadPokemons(final TeamType teamType) {
        return getTrainer(teamType).getDeadPokemons();
    }

    /**
     * Tries to flag a specific pokemon deployed in battle as dead.
     *
     * @param teamType  The team of the pokemon to flag as dead.
     * @param pokemonID The ID of the pokemon to flag as dead.
     */
    public void flagPokemonAsDead(final TeamType teamType, final String pokemonID) {
        Objects.requireNonNull(teamType);
        Objects.requireNonNull(pokemonID);
        if (pokemonID.isBlank()) {
            throw new IllegalArgumentException("Provided pokemonID ("
                    .concat(pokemonID)
                    .concat(") is invalid."));
        }

        final Trainer trainer = getTrainer(teamType);
        if (!trainer.hasPokemon(pokemonID)) {
            throw new IllegalArgumentException("Provided pokemonID ("
                    .concat(pokemonID)
                    .concat(") is not present in the trainer's alive pokemon team."));
        }
        trainer.killPokemon(pokemonID);
    }

    /**
     * Gets the currently active {@link Pokemon} on the same team as the given one.
     *
     * @param teamType The given team.
     * @return The pokemon currently active.
     */
    public Pokemon getCurrentOwn(final TeamType teamType) {
        return getTrainer(teamType).getCurrentlyActive();
    }

    /**
     * Gets the currently active {@link Pokemon} on the opposite team as the given
     * one.
     *
     * @param teamType The given team.
     * @return The pokemon currently active.
     */
    public Pokemon getCurrentOther(final TeamType teamType) {
        return getOtherTrainer(teamType).getCurrentlyActive();
    }

    /**
     * Swaps the current pokemon of a trainer with a given new one on their team.
     *
     * @param teamType  The given team.
     * @param pokemonID The ID of the new pokemon.
     */
    public void swapPokemon(final TeamType teamType, final String pokemonID) {
        getTrainer(teamType).swapPokemon(pokemonID);
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
        final BattleEnvironment that = (BattleEnvironment) o;
        return Objects.equals(this.playerTrainer, that.playerTrainer)
                && Objects.equals(this.enemyTrainer, that.enemyTrainer);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                this.playerTrainer,
                this.enemyTrainer);
    }
}
