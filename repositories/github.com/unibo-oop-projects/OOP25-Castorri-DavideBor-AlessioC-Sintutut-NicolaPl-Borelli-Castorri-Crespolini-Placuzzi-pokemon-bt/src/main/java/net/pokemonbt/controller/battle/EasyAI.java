package net.pokemonbt.controller.battle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.pokemonbt.model.move.PersonalMove;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;
import net.pokemonbt.model.move.Move;

/**
 * A class that makes the choices for the enemy randomly without any logic.
 */
public class EasyAI extends AbstractEnemyAI {

    static final float SWAP_CHANCE = 0.15f;

    /**
     * @param be {@inheritDoc}
     */
    public EasyAI(final BattleEnvironment be) {
        super(be);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean turnChoice() {
        return !RandomUtility.check(SWAP_CHANCE) || getBattleEnv().getAlivePokemons(BATTLE_ROLE).keySet().size() <= 1;
    }

    /**
     * Randomly chooses the pokemon to switch-in.
     * 
     * @return {@inheritDoc}
     */
    @Override
    public String swapPokemon() {
        if (getCurrentTeam().isEmpty()) {
            return "End of battle";
        }
        final int availablePokemonsNumber = getBattleEnv().getAlivePokemons(BATTLE_ROLE).keySet().size() - 1;
        final Map<String, Pokemon> team = super.getCurrentTeam();
        team.remove(getEnemyActive().getID());
        final int randomPokemon = RandomUtility.nextInteger(availablePokemonsNumber);
        int counter = 0;
        for (final String pokemonID : team.keySet()) {
            if (counter == randomPokemon) {
                return pokemonID;
            }
            counter++;
        }
        throw new IllegalStateException("Reached illegal point");
    }

    /**
     * Randomly chooses the move to use.
     * 
     * @return {@inheritDoc}
     */
    @Override
    public Optional<Move> chooseMove() {
        final Optional<Move> forcedMove = super.chooseMove();
        if (forcedMove.isPresent()) {
            return forcedMove;
        }

        final List<PersonalMove> availableMoves = getEnemyActive()
        .getMoveComponent()
        .getMoveSet()
        .stream()
        .filter(PersonalMove::isAvailable)
        .toList();

        final int randomMove = RandomUtility.nextInteger(availableMoves.size());

        return Optional.of(availableMoves.get(randomMove));
    }
}
