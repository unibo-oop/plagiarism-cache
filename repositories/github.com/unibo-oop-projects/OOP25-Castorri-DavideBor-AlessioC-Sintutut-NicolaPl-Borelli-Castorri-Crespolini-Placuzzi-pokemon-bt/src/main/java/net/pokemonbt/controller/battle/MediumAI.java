package net.pokemonbt.controller.battle;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.move.MoveCategory;
import net.pokemonbt.model.move.PersonalMove;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;
import net.pokemonbt.utility.Pair;

/**
 * A class that makes the choices for the enemy in a 
 * pseudo-random way.
 */
public class MediumAI extends AbstractEnemyAI {

    /**
     * @param be {@inheritDoc}
     */
    public MediumAI(final BattleEnvironment be) {
        super(be);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean turnChoice() {

        return !((getEnemyActive().getStatComponent().getHP() 
                < this.getEnemyActive().getStatComponent().getBaseStat(PokeStatType.HP_MAX) / 2
                || DamageUtils.getTypeMultiplier(
                    this.getPlayerActive().getStatComponent().getPrimaryType(),
                    this.getEnemyActive().getStatComponent().getPrimaryType(),
                    this.getEnemyActive().getStatComponent().getSecondaryType()) >= 1)
                && getBattleEnv().getAlivePokemons(BATTLE_ROLE).size() > 1);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String swapPokemon() {
        if (getCurrentTeam().isEmpty()) {
            return "End of battle";
        }
        final Map<String, Pokemon> team = super.getCurrentTeam();
        float multiplier;
        team.remove(this.getEnemyActive().getID());
        String toSwitchID = "";
        for (final var pokemon : team.entrySet()) {
            multiplier = DamageUtils.getTypeMultiplier(getPlayerActive().getStatComponent().getPrimaryType(),
            getEnemyActive().getStatComponent().getPrimaryType(),
            getEnemyActive().getStatComponent().getSecondaryType());
            if (multiplier > 1) {
                toSwitchID = pokemon.getKey();
            } 
            if (toSwitchID.isBlank()) {
                toSwitchID = pokemon.getKey();
            }
        }

        return toSwitchID;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public Optional<Move> chooseMove() {
        final Optional<Move> forcedMove = super.chooseMove();
        if (forcedMove.isPresent()) {
            return forcedMove;
        }
        final List<PersonalMove> availableMoves = this.getEnemyActive().getMoveComponent()
                                                    .getMoveSet()
                                                    .stream()
                                                    .filter(PersonalMove::isAvailable)
                                                    .toList();

        return Optional.of(findBestMove(availableMoves));
    }

    /**
     * @param availableMoves a list of {@link Move} witch are available to use for the pokemon
     * @return the best {@link Move} to use in the context
     */
    private Move findBestMove(final List<PersonalMove> availableMoves) {
        final List<Pair<Integer, Move>> moveWeightList = new LinkedList<>();
        int weightSum = 0;

        for (final Move personalMove : availableMoves) {
            int moveWeight = 0;
            moveWeight += (int) (DamageUtils.getTypeMultiplier(personalMove.getType(),
            getPlayerActive().getStatComponent().getPrimaryType(),
            getPlayerActive().getStatComponent().getSecondaryType()) * 4);

            if (personalMove.getCategory() != MoveCategory.STATUS) {
                moveWeight += 1;
            }
            moveWeightList.add(new Pair<>(moveWeight, personalMove));
            weightSum += moveWeight;
        }
        int randomNumber = RandomUtility.nextInteger(weightSum);
        for (final Pair<Integer, Move> pair : moveWeightList) {
            randomNumber -= pair.first();
            if (randomNumber <= 0) {
                return pair.second();
            }
        }
        return null;
    }
}
