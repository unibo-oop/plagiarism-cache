package net.pokemonbt.model.battle;

import net.pokemonbt.model.pokemon.Pokemon;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * Base interface for all trainers, representing a list of
 * {@link Pokemon} currently deployed in battle.
 */
public interface Trainer extends Serializable {
    @Serial
    long serialVersionUID = 1L;

    /**
     * @return A map of pokemonID-{@link Pokemon} the trainer has.
     */
    Map<String, Pokemon> getAllPokemons();

    /**
     * @return A map of pokemonID-{@link Pokemon} the trainer has that
     *         currently have more than 0 hp.
     */
    Map<String, Pokemon> getAlivePokemons();

    /**
     * @return A map of pokemonID-{@link Pokemon} the trainer has that
     *         currently have less than or equal to 0 hp.
     */
    Map<String, Pokemon> getDeadPokemons();

    /**
     * @return The {@link Pokemon} that is currently active.
     */
    Pokemon getCurrentlyActive();

    /**
     * @param pokemonID The id of the {@link Pokemon} that will be currently active.
     */
    void setCurrentlyActive(String pokemonID);

    /**
     * Attempts to swap out the currently active pokemon with
     * the provided one.
     * Fails if the pokemon is not present in the team, if
     * the desired pokemon is the same as the currently active
     * one or if the desired pokemon is currently dead.
     *
     * @param pokemonID The ID of the pokemon that will be
     *                  replacing the currently active one.
     */
    void swapPokemon(String pokemonID);

    /**
     * @param pokemonID The ID of the pokemon to check for.
     * @return True if the trainer has the provided pokemon, false otherwise.
     */
    boolean hasPokemon(String pokemonID);

    /**
     * @param pokemonID The ID of the pokemon to check for.
     * @return True if the pokemon is currently dead, false otherwise.
     */
    boolean isDead(String pokemonID);

    /**
     * Moves the provided pokemon from the "alive" group to the "dead" group.
     * Fails if the trainer doesn't have the provided pokemon or if the
     * given pokemon is already dead.
     *
     * @param pokemonID The ID of the pokemon to kill.
     */
    void killPokemon(String pokemonID);
}
