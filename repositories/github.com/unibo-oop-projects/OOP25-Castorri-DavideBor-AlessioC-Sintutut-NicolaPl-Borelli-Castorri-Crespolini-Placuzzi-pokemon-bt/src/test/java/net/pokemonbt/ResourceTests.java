package net.pokemonbt;

import com.google.gson.JsonElement;

import net.pokemonbt.controller.battle.BattleEnvironment;
import net.pokemonbt.controller.battle.DamageUtils;
import net.pokemonbt.controller.resources.LoadoutManager;
import net.pokemonbt.controller.resources.ResourceManager;
import net.pokemonbt.model.battle.DamageTypeMultiplier;
import net.pokemonbt.model.pokemon.BasePokemon;
import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.Pokemon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Class for resource tests.
 */
final class ResourceTests {
    private static final String POKEMON_PATH = "json/pokemons.json";
    private static final String TEST_MOVE_1 = "struggle";
    private static final List<Integer> TEAM1 = List.of(1, 2, 3, 4);
    private static final List<Integer> TEAM2 = List.of(5, 10, 15, 20);
    private static final List<Integer> TEAM3 = List.of(7, 12, 18, 19);

    private ResourceManager rm;

    /**
     * Initializes all tests.
     */
    @BeforeEach
    public void initializeTest() {
        this.rm = new ResourceManager();
        this.rm.initializeMoveList("json/moves.json");
    }

    /**
     * Tests the creation of the moves list.
     */
    @Test
    void testMoveList() {
        /* Checks if the given move has been actually created. */
        Assertions.assertNotNull(ResourceManager.getMove(TEST_MOVE_1));
    }

    /**
     * Tests the creation of the pokemon list.
     */
    @Test
    void testPokeList() {
        final List<Pokemon> pokeList = this.rm.getPokemonList(POKEMON_PATH);
        Assertions.assertFalse(pokeList.isEmpty());
        for (int i = 0; i < pokeList.size(); i++) {
            Assertions.assertNotNull(pokeList.get(i));
            for (int j = 0; j < pokeList.size(); j++) {
                if (i != j) {
                    /* Checks that no pokemon gets initialized twice. */
                    Assertions.assertNotEquals(pokeList.get(i), pokeList.get(j));
                }
            }
        }

    }

    /**
     * Tests the type multiplier.
     */
    @Test
    void testMult() {
        final Optional<JsonElement> typeMult = ResourceManager.getResourceAsJsonElement("json/type_mult.json");
        Assertions.assertTrue(typeMult.isPresent());
        Assertions.assertTrue(typeMult.get().isJsonObject());
        DamageUtils.initialize(typeMult.get().getAsJsonObject());
        /* Checks for the validity of certain arbitrary PokeType pairs. */
        Assertions.assertEquals(DamageUtils.getTypeMultiplier(PokeType.GHOST, PokeType.GHOST),
                DamageTypeMultiplier.EFFECTIVE.getMultiplier());
        Assertions.assertEquals(DamageUtils.getTypeMultiplier(PokeType.DRAGON, PokeType.FAIRY),
                DamageTypeMultiplier.NULL.getMultiplier());
        Assertions.assertEquals(DamageUtils.getTypeMultiplier(PokeType.BUG, PokeType.GRASS),
                DamageTypeMultiplier.EFFECTIVE.getMultiplier());
        Assertions.assertEquals(DamageUtils.getTypeMultiplier(PokeType.FIRE, PokeType.NORMAL),
                DamageTypeMultiplier.NORMAL.getMultiplier());
        /* Checks that the type "NONE" has no modifications in the type chart. */
        for (final PokeType type : PokeType.values()) {
            Assertions.assertEquals(DamageUtils.getTypeMultiplier(PokeType.NONE, type),
                    DamageTypeMultiplier.NORMAL.getMultiplier());
        }
    }

    /**
     * Tests the copyOf method.
     */
    @Test
    void testCopyOf() {
        final List<Pokemon> pokeList = this.rm.getPokemonList(POKEMON_PATH);
        final BasePokemon starting = (BasePokemon) pokeList.getFirst();
        final Pokemon ending = starting.copyOf();

        Assertions.assertNotSame(starting, ending);
        Assertions.assertNotSame(
                starting.getMoveComponent(),
                ending.getMoveComponent());
        Assertions.assertNotSame(
                starting.getStatComponent(),
                ending.getStatComponent());
        Assertions.assertNotSame(
                starting.getConditionComponent(),
                ending.getConditionComponent());
        Assertions.assertNotSame(
                starting.getItemComponent(),
                ending.getItemComponent());

    }

    /**
     * Tests the equals and hashCode overridden methods.
     */
    @Test
    void testEqualsHashCode() {
        final List<Pokemon> pokeList = this.rm.getPokemonList(POKEMON_PATH);
        final BasePokemon starting = (BasePokemon) pokeList.getFirst();
        final BasePokemon ending = starting.copyOf();

        /* Assert that two identical copies of pokemons
         * (but not the same object) have the same components
         * and the same hashCode.
         */
        Assertions.assertEquals(
                starting.getMoveComponent(),
                ending.getMoveComponent());
        Assertions.assertEquals(
                starting.getMoveComponent().hashCode(),
                ending.getMoveComponent().hashCode());

        Assertions.assertEquals(
                starting.getStatComponent(),
                ending.getStatComponent());
        Assertions.assertEquals(
                starting.getStatComponent().hashCode(),
                ending.getStatComponent().hashCode());

        Assertions.assertEquals(
                starting.getConditionComponent(),
                ending.getConditionComponent());
        Assertions.assertEquals(
                starting.getConditionComponent().hashCode(),
                ending.getConditionComponent().hashCode());

        Assertions.assertEquals(
                starting.getItemComponent(),
                ending.getItemComponent());
        Assertions.assertEquals(
                starting.getItemComponent().hashCode(),
                ending.getItemComponent().hashCode());

        /* Check the same but with the pokemon themselves. */
        Assertions.assertEquals(starting, ending);
        Assertions.assertEquals(starting.hashCode(), ending.hashCode());
        Assertions.assertNotSame(starting, ending);
    }

    /**
     * Tests the correct functioning of the LoadoutManager with the save file.
     */
    @Test
    void testLoadout() {
        LoadoutManager.loadSettings();
        LoadoutManager.deleteSaveFile();

        final List<Pokemon> pokeList = this.rm.getPokemonList(POKEMON_PATH);

        final List<Pokemon> userTeam = new LinkedList<>();
        final List<Pokemon> enemyTeam = new LinkedList<>();

        for (final int index : TEAM1) {
            userTeam.add(pokeList.get(index));
        }
        for (final int index : TEAM2) {
            enemyTeam.add(pokeList.get(index));
        }

        final BattleEnvironment be1 = new BattleEnvironment(
            userTeam, 
            enemyTeam,
            userTeam.getFirst().getID(),
            enemyTeam.getFirst().getID()
        );

        // Assertion for the save of the game
        Assertions.assertTrue(LoadoutManager.saveFileMissing());
        LoadoutManager.saveGame(be1);
        Assertions.assertFalse(LoadoutManager.saveFileMissing());

        // Assertion for the first environment
        Assertions.assertEquals(be1, LoadoutManager.loadGame().orElseThrow());

        userTeam.clear();
        for (final int index : TEAM3) {
            userTeam.add(pokeList.get(index));
        }

        // Create different environment
        final BattleEnvironment be2 = new BattleEnvironment(
            userTeam, 
            enemyTeam,
            userTeam.getFirst().getID(),
            enemyTeam.getFirst().getID()
        );

        LoadoutManager.saveGame(be2);

        // Assertion that the new environment is different from the old one.
        Assertions.assertNotEquals(be1, be2);
        // The current save file contains a different environment from the old one.
        Assertions.assertNotEquals(be1, LoadoutManager.loadGame().orElseThrow());
        Assertions.assertEquals(be2, LoadoutManager.loadGame().orElseThrow());
    }
}
