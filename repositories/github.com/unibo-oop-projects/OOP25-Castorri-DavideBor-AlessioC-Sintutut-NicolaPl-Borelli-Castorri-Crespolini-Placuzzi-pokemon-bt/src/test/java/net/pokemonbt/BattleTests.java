package net.pokemonbt;

import net.pokemonbt.controller.battle.BattleEnvironment;
import net.pokemonbt.controller.battle.DamageUtils;
import net.pokemonbt.controller.resources.ResourceManager;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.TeamType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for battle tests.
 */
final class BattleTests {
    private static final int PLAYER_POKELIST_FROM = 1;
    private static final int PLAYER_POKELIST_TO = 5;
    private static final int ENEMY_POKELIST_FROM = 11;
    private static final int ENEMY_POKELIST_TO = 15;

    private ResourceManager rm;
    private List<Pokemon> pokeList;
    private BattleEnvironment env;

    /**
     * Initializes all tests.
     */
    @BeforeEach
    public void initializeTest() {
        rm = new ResourceManager();
        rm.initializeMoveList("json/moves.json");
        ResourceManager.initializeConditionList("json/conditions.json");
        DamageUtils.initialize(
            ResourceManager.getResourceAsJsonElement("json/type_mult.json")
            .orElseThrow()
            .getAsJsonObject()
        );

        pokeList = new LinkedList<>(rm.getPokemonList("json/pokemons.json"));
        final List<Pokemon> list1 = new LinkedList<>();
        final List<Pokemon> list2 = new LinkedList<>();

        list1.add(pokeList.getFirst());
        for (int i = PLAYER_POKELIST_FROM; i <= PLAYER_POKELIST_TO; i++) {
            list1.add(pokeList.get(i));
        }

        list2.add(pokeList.getLast());
        for (int i = ENEMY_POKELIST_FROM; i <= ENEMY_POKELIST_TO; i++) {
            list2.add(pokeList.get(i));
        }

        env = new BattleEnvironment(list1, list2, list1.getFirst().getID(), list2.getFirst().getID());
        Assertions.assertNotNull(env);
    }

    /**
     * Tests the {@link BattleEnvironment}.
     */
    @Test
    void testEnv() {
        Assertions.assertNotNull(env);
        Assertions.assertNotNull(env.getCurrentOwn(TeamType.PLAYER));
        Assertions.assertNotNull(env.getCurrentOwn(TeamType.ENEMY));
    }

    /**
     * Tests all moves' consumption and damage.
     */
    @Test
    void testMoveUses() {
        final Pokemon practiceBuddy = rm.getPokemonList("json/practice.json").getFirst();
        for (final var pokemon : pokeList) {

            pokemon.getMoveComponent()
            .getMoveMap()
            .forEach((moveId, m) -> {
                final int startingHP = practiceBuddy.getStatComponent().getHP();
                boolean hitAtLeastOnce = false;

                // Consume all moves.
                while (m.currentPP() > 0) {
                    final boolean hit = pokemon.getMoveComponent()
                    .useMove(moveId, practiceBuddy);

                    // Save first hit status.
                    if (!hitAtLeastOnce && hit) {
                        hitAtLeastOnce = hit;
                    }

                    // Remove any condition the user may have applied to themselves or the buddy.
                    pokemon.getConditionComponent()
                    .removePermanentCondition();
                    pokemon.getConditionComponent()
                    .removeVolatileConditions();

                    // Heal any possible Recoil dmg the current pokemon could
                    // have dealt to themselves.
                    pokemon.getStatComponent()
                    .increaseHealth(pokemon.getStatComponent().getStat(PokeStatType.HP_MAX));
                }

                // Assert a Move that deals damage actually removes health.
                if (hitAtLeastOnce && m.getPower() > 0) {
                    Assertions.assertNotEquals(startingHP, practiceBuddy.getStatComponent().getHP());
                }
            });

            Assertions.assertTrue(
                pokemon.getMoveComponent()
                .getMoveMap()
                .entrySet()
                .stream()
                .allMatch(move -> "struggle".equals(move.getKey()))
            );
        }
    }
}
