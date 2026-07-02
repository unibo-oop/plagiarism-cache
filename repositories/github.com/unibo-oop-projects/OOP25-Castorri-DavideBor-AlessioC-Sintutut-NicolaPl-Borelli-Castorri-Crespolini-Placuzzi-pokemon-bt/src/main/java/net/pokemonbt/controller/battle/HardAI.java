package net.pokemonbt.controller.battle;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.move.MoveCategory;
import net.pokemonbt.model.move.PersonalMove;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;
import net.pokemonbt.utility.Pair;

/**
 * An class that makes the choices for the enemy 
 * weighting the choices based on what should be the best choice. 
 */
public class HardAI extends AbstractEnemyAI {

    private static final float FREE_SWITCH = 0.15f;
    private static final float RANDOM_COMPONENT = 0.3f;
    private final List<Pair<Integer, Pokemon>> weightedPokemon = new LinkedList<>();

    /**
     * @param be {@inheritDoc}
     */
    public HardAI(final BattleEnvironment be) {
        super(be);
        this.fillWeights();
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean turnChoice() {
        updateWeights();
        final Pokemon enemy = getEnemyActive();

        if (enemy.getStatComponent().getHP() 
            < enemy.getStatComponent().getBaseStat(PokeStatType.HP_MAX) * FREE_SWITCH) {
            return true;
        }

        final Pokemon player = getPlayerActive();
        final float matchup = DamageUtils.getTypeMultiplier(
                    enemy.getStatComponent().getPrimaryType(),
                    player.getStatComponent().getPrimaryType(),
                    player.getStatComponent().getSecondaryType()) 
                    * DamageUtils.getTypeMultiplier(
                    enemy.getStatComponent().getSecondaryType(),
                    player.getStatComponent().getPrimaryType(),
                    player.getStatComponent().getSecondaryType());

        final boolean isSlower = enemy.getStatComponent().getStat(PokeStatType.SPE)
                        < player.getStatComponent().getStat(PokeStatType.SPE);
        if (matchup >= 2) {
            return true;
        }
        return !(isSlower || matchup < 1) || isBestOfTeam() || RandomUtility.check(RANDOM_COMPONENT);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String swapPokemon() {
        if (getCurrentTeam().isEmpty()) {
            return "End of battle";
        }
        Pokemon bestPokemon = null;
        int bestWeight = Integer.MIN_VALUE;
        for (final Pair<Integer, Pokemon> pair : weightedPokemon) {
            if (pair.first() > bestWeight && !pair.second().getID().equals(this.getEnemyActive().getID())) {
                bestWeight = pair.first();
                bestPokemon = pair.second();
            }
        }
        return bestPokemon.getID();
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
        final Pokemon enemy = getEnemyActive();
        final Pokemon player = getPlayerActive();

        final boolean isSlower = enemy.getStatComponent().getStat(PokeStatType.SPE)
                        < player.getStatComponent().getStat(PokeStatType.SPE);
        if (enemy.getStatComponent().getHP() 
            < enemy.getStatComponent().getBaseStat(PokeStatType.HP_MAX) * FREE_SWITCH
            && isSlower) {

            final Optional<PersonalMove> prioMove = availableMoves.stream()
                                    .filter(m -> m.getPriority() > 0)
                                    .findFirst();

            if (prioMove.isPresent()) {
                return prioMove.get();
            }
        }

        int weightSum = 0;
        for (final Move personalMove : availableMoves) {
            int moveWeight = 1;
            moveWeight += (int) DamageUtils.getTypeMultiplier(personalMove.getType(),
            player.getStatComponent().getPrimaryType(),
            player.getStatComponent().getSecondaryType()) * 4;

            if (personalMove.getType() == enemy.getStatComponent().getPrimaryType()
                || personalMove.getType() == enemy.getStatComponent().getSecondaryType()
                && moveWeight > 4) {
                    moveWeight += 4;
            }
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

    /**
     * Gives each {@link Pokemon} a weight based on the player's active pokemon.
     */
    private void weightPokemon() {
        final PokeType playerType1 = this.getPlayerActive().getStatComponent().getPrimaryType();
        final PokeType playerType2 = this.getPlayerActive().getStatComponent().getSecondaryType();

        for (final Pair<Integer, Pokemon> pair : weightedPokemon) {
            final PokeType enemyType1 = pair.second().getStatComponent().getPrimaryType();
            final PokeType enemyType2 = pair.second().getStatComponent().getSecondaryType();

            final float defensiveWeight = DamageUtils.getTypeMultiplier(playerType1, enemyType1, enemyType2) 
                                  + DamageUtils.getTypeMultiplier(playerType2, enemyType1, enemyType2);

            final float offensiveWeight = DamageUtils.getTypeMultiplier(enemyType1, playerType1, playerType2)
                                  + DamageUtils.getTypeMultiplier(enemyType2, playerType1, playerType2);

            pair.first((int) ((offensiveWeight - defensiveWeight) * 4));
        }
    }

    /**
     * Removes form the weight map dead pokemons.
     */
    private void updateWeights() {
        final Map<String, Pokemon> deadPokemons = getBattleEnv().getDeadPokemons(BATTLE_ROLE);
        for (final var pokemon : deadPokemons.entrySet()) {
            weightedPokemon.removeIf(p -> p.second().getID().equals(pokemon.getKey()));
        }
        weightPokemon();
    }

    /**
     * Fills the Map with the pokemon puts each weight equal to 0. 
     */
    private void fillWeights() {
        final Map<String, Pokemon> pokemonTeam = getBattleEnv().getAllPokemons(BATTLE_ROLE);
        for (final var pokemon : pokemonTeam.entrySet()) {
            weightedPokemon.add(new Pair<>(0, pokemon.getValue()));
        }
    }

    /**
     * @return if the current active pokemon is the best of the team to have on the field
     */
    private boolean isBestOfTeam() {
        final int activeWheight = weightedPokemon.stream()
        .filter(p -> p.second().getID().equals(getEnemyActive().getID()))
        .map(Pair::first)
        .findFirst()
        .orElse(0);
        boolean isBest = true;
        for (final Pair<Integer, Pokemon> pair : weightedPokemon) {
            if (activeWheight < pair.first()) {
                isBest = false;
            }
        }

        return isBest;
    }
}
