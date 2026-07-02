package net.pokemonbt.controller.battle;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.TeamType;

/**
 * An abstract class with general methods used by the AIs.
 */
public abstract class AbstractEnemyAI {

    static final TeamType BATTLE_ROLE = TeamType.ENEMY;
    private final BattleEnvironment battleEnv;

    /**
     * @param be the battle environment the AI works on
     */
    public AbstractEnemyAI(final BattleEnvironment be) {
        this.battleEnv = be;
    }

    /**
     * @return true if the AI chose to use a move, false if the AI chose to swap
     */
    abstract boolean turnChoice();

    /**
     * @return the id of the pokemon to swap-in
     */
    abstract String swapPokemon();

    /**
     * @return the {@link Move} the enemy is going to use
     */
    protected Optional<Move> chooseMove() {
        final Move struggle = getEnemyActive().getMoveComponent().getMoveMap().get("struggle");
        if (struggle != null) {
            getEnemyActive().getMoveComponent()
            .changeAvailability(struggle.getID(), true);

            return Optional.of(struggle);
        }
        final Optional<String> moveID = battleEnv
                .getCurrentOwn(BATTLE_ROLE)
                .getMoveComponent()
                .getForcedMove();

        if (moveID.isPresent()) {
            return Optional.of(battleEnv
            .getCurrentOwn(BATTLE_ROLE)
            .getMoveComponent()
            .getMoveMap()
            .get(moveID.get()));
        }
        return Optional.empty();
    }

    /**
     * @return returns the enemy team available pokemons
     */
    protected Map<String, Pokemon> getCurrentTeam() {
        return new LinkedHashMap<>(battleEnv.getAlivePokemons(BATTLE_ROLE));
    }

    /**
     * @return the player's active {@link Pokemon}
     */
    protected final Pokemon getEnemyActive() {
        return battleEnv.getCurrentOwn(BATTLE_ROLE);
    }

    /**
     * @return the enemy's active {@link Pokemon}
     */
    protected final Pokemon getPlayerActive() {
        return battleEnv.getCurrentOther(BATTLE_ROLE);
    }

    /**
     * @return the battle environment
     */
    protected final BattleEnvironment getBattleEnv() {
        return battleEnv;
    }
}
