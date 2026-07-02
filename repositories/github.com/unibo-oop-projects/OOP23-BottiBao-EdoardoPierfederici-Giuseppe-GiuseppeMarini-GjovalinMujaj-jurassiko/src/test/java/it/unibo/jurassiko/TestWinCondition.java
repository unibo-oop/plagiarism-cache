package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.controller.api.MainController;
import it.unibo.jurassiko.controller.impl.MainControllerImpl;
import it.unibo.jurassiko.core.api.WinCondition;
import it.unibo.jurassiko.core.impl.WinConditionImpl;
import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.impl.ConquerContinentsObjective;
import it.unibo.jurassiko.model.objective.impl.ConquerTerritoriesObjective;
import it.unibo.jurassiko.model.objective.impl.DestroyArmyObjective;
import it.unibo.jurassiko.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.model.territory.api.Territory;

class TestWinCondition {

    private static final int DEFAULT_NUM_TERRITORIES = 12;

    private WinCondition winCondition;
    private Set<Objective> objectives;
    private Map<Territory, Pair<GameColor, Integer>> initialMap;
    private Player initialPlayer;
    private GameColor initialPlayerColor;
    private final Random random = new Random();

    @BeforeEach
    void init() {
        this.winCondition = new WinConditionImpl();
        this.objectives = new ObjectiveFactoryImpl().createObjectives();

        final MainController controller = new MainControllerImpl();
        this.initialMap = controller.getTerritoriesMap();
        this.initialPlayer = controller.getCurrentPlayer();
        this.initialPlayerColor = this.initialPlayer.getColor();
    }

    @Test
    void testDefaultConquerContinents() {
        final var defaultContinentsObjective = objectives.stream()
                .filter(ConquerContinentsObjective.class::isInstance)
                .map(ConquerContinentsObjective.class::cast)
                .filter(o -> !o.isSelectableContinent())
                .findFirst()
                .get();

        // At the beginning of the game winner must be empty
        assertEquals(Optional.empty(),
                this.winCondition.getWinner(this.initialMap, this.initialPlayer, defaultContinentsObjective));

        final Set<String> defaultContinents = defaultContinentsObjective.getContinents();
        final Map<Territory, Pair<GameColor, Integer>> testMap = new HashMap<>(this.initialMap);
        testMap.entrySet().forEach(e -> {
            final String continent = e.getKey().getContinent();
            if (defaultContinents.contains(continent)) {
                e.setValue(new Pair<>(this.initialPlayerColor, 1));
            }
        });
        assertEquals(Optional.of(this.initialPlayer),
                this.winCondition.getWinner(testMap, this.initialPlayer, defaultContinentsObjective));
    }

    @Test
    void testSelectableConquerContinents() {
        final var selectableContinentsObjective = objectives.stream()
                .filter(ConquerContinentsObjective.class::isInstance)
                .map(ConquerContinentsObjective.class::cast)
                .filter(o -> o.isSelectableContinent())
                .findFirst()
                .get();

        // At the beginning of the game winner must be empty
        assertEquals(Optional.empty(),
                this.winCondition.getWinner(this.initialMap, this.initialPlayer, selectableContinentsObjective));

        final Set<String> mandatoryContinents = selectableContinentsObjective.getContinents();

        final Map<Territory, Pair<GameColor, Integer>> testMap = new HashMap<>(this.initialMap);
        testMap.entrySet().forEach(e -> {
            final String continent = e.getKey().getContinent();
            if (mandatoryContinents.contains(continent)) {
                e.setValue(new Pair<>(this.initialPlayerColor, 1));
            }
        });

        // Only the specified continent has been conquered: any other one is needed
        assertEquals(Optional.empty(),
                this.winCondition.getWinner(testMap, this.initialPlayer, selectableContinentsObjective));

        final String additionalContinent = testMap.keySet().stream()
                .map(Territory::getContinent)
                .filter(c -> !mandatoryContinents.contains(c))
                .findFirst()
                .get();
        testMap.entrySet().forEach(e -> {
            final String continent = e.getKey().getContinent();
            if (additionalContinent.equals(continent)) {
                e.setValue(new Pair<>(this.initialPlayerColor, 1));
            }
        });

        assertEquals(Optional.of(this.initialPlayer),
                this.winCondition.getWinner(testMap, this.initialPlayer, selectableContinentsObjective));
    }

    @Test
    void testDefaultConquerTerritories() {
        final var territoriesObjective = objectives.stream()
                .filter(ConquerTerritoriesObjective.class::isInstance)
                .map(ConquerTerritoriesObjective.class::cast)
                .filter(o -> o.getMinDinos() == 0)
                .findFirst()
                .get();
        final int numTerritories = territoriesObjective.getNumTerritories();

        // At the beginning of the game winner must be empty
        assertEquals(Optional.empty(),
                this.winCondition.getWinner(this.initialMap, this.initialPlayer, territoriesObjective));

        var testMap = new HashMap<>(this.initialMap);
        final Set<Territory> alreadyOwnedTerritories = getOwnedTerritories(testMap, this.initialPlayerColor);
        final int alreadyOwnedTerritoriesAmount = alreadyOwnedTerritories.size();

        // Testing player owns one less than the required number
        int territoriesToAddAmount = numTerritories - alreadyOwnedTerritoriesAmount - 1;
        final Set<Territory> territoriesToAddLess = getUnownedTerritories(testMap,
                alreadyOwnedTerritories,
                territoriesToAddAmount);
        testMap.entrySet().forEach(e -> {
            if (territoriesToAddLess.contains(e.getKey())) {
                e.setValue(new Pair<>(this.initialPlayerColor, 1));
            }
        });
        assertEquals(Optional.empty(),
                this.winCondition.getWinner(testMap, this.initialPlayer, territoriesObjective));

        // Testing player owns the exact amount
        testMap = new HashMap<>(this.initialMap);
        territoriesToAddAmount = numTerritories - alreadyOwnedTerritoriesAmount;
        final Set<Territory> territoriesToAddEqual = getUnownedTerritories(testMap,
                alreadyOwnedTerritories,
                territoriesToAddAmount);
        testMap.entrySet().forEach(e -> {
            if (territoriesToAddEqual.contains(e.getKey())) {
                e.setValue(new Pair<>(this.initialPlayerColor, 1));
            }
        });
        assertEquals(Optional.of(this.initialPlayer),
                this.winCondition.getWinner(testMap, this.initialPlayer, territoriesObjective));

        this.winCondition = new WinConditionImpl(); // Resets the winner

        // Testing player one more territory than the exact amount
        testMap = new HashMap<>(this.initialMap);
        territoriesToAddAmount = numTerritories - alreadyOwnedTerritoriesAmount + 1;
        final Set<Territory> territoriesToAddMore = getUnownedTerritories(testMap,
                alreadyOwnedTerritories,
                territoriesToAddAmount);
        testMap.entrySet().forEach(e -> {
            if (territoriesToAddMore.contains(e.getKey())) {
                e.setValue(new Pair<>(this.initialPlayerColor, 1));
            }
        });
        assertEquals(Optional.of(this.initialPlayer),
                this.winCondition.getWinner(testMap, this.initialPlayer, territoriesObjective));
    }

    @Test
    void testMinDinosConquerTerritories() {
        final var territoriesObjective = objectives.stream()
                .filter(ConquerTerritoriesObjective.class::isInstance)
                .map(ConquerTerritoriesObjective.class::cast)
                .filter(o -> o.getMinDinos() != 0)
                .findFirst()
                .get();
        final int numTerritories = territoriesObjective.getNumTerritories();
        final int minDinos = territoriesObjective.getMinDinos();

        // At the beginning of the game winner must be empty
        assertEquals(Optional.empty(),
                this.winCondition.getWinner(this.initialMap, this.initialPlayer, territoriesObjective));

        var testMap = new HashMap<>(this.initialMap);
        final Set<Territory> alreadyOwnedTerritories = getOwnedTerritories(testMap, this.initialPlayerColor);
        final int alreadyOwnedTerritoriesAmount = alreadyOwnedTerritories.size();

        // Player owns the correct amount but with a single Dino for each territory
        final int territoriesToAddAmount = numTerritories - alreadyOwnedTerritoriesAmount;
        final Set<Territory> territoriesToAdd = getUnownedTerritories(testMap,
                alreadyOwnedTerritories,
                territoriesToAddAmount);
        testMap.entrySet().forEach(e -> {
            if (territoriesToAdd.contains(e.getKey())) {
                e.setValue(new Pair<>(this.initialPlayerColor, 1));
            }
        });
        assertEquals(Optional.empty(),
                this.winCondition.getWinner(testMap, this.initialPlayer, territoriesObjective));

        // Player owns the correct amount with the exact Dino amount
        testMap = new HashMap<>(this.initialMap);
        testMap.entrySet().forEach(e -> {
            if (territoriesToAdd.contains(e.getKey()) || alreadyOwnedTerritories.contains(e.getKey())) {
                e.setValue(new Pair<>(this.initialPlayerColor, minDinos));
            }
        });
        assertEquals(Optional.of(this.initialPlayer),
                this.winCondition.getWinner(testMap, this.initialPlayer, territoriesObjective));

        this.winCondition = new WinConditionImpl(); // Resets the winner

        // Player owns the correct amount with one additional Dino each
        testMap = new HashMap<>(this.initialMap);
        testMap.entrySet().forEach(e -> {
            if (territoriesToAdd.contains(e.getKey()) || alreadyOwnedTerritories.contains(e.getKey())) {
                e.setValue(new Pair<>(this.initialPlayerColor, minDinos + 1));
            }
        });
        assertEquals(Optional.of(this.initialPlayer),
                this.winCondition.getWinner(testMap, this.initialPlayer, territoriesObjective));
    }

    @Test
    void testDestroyArmy() {
        final var armyObjective = objectives.stream()
                .filter(DestroyArmyObjective.class::isInstance)
                .map(DestroyArmyObjective.class::cast)
                .filter(o -> !o.getArmyColor().equals(this.initialPlayerColor))
                .findFirst()
                .get();
        final GameColor armyColor = armyObjective.getArmyColor();

        // At the beginning of the game winner must be empty
        assertEquals(Optional.empty(),
                this.winCondition.getWinner(this.initialMap, this.initialPlayer, armyObjective));

        final var testMap = new HashMap<>(this.initialMap);
        testMap.entrySet().forEach(e -> {
            if (e.getValue().x().equals(armyColor)) {
                e.setValue(new Pair<>(getDifferentRandomColor(armyColor), 1));
            }
        });
        assertEquals(Optional.of(this.initialPlayer),
                this.winCondition.getWinner(testMap, this.initialPlayer, armyObjective));
    }

    @Test
    void testDefaultObjective() {
        // DestroyArmyObjective switches to default one
        final var armyObjective = objectives.stream()
                .filter(DestroyArmyObjective.class::isInstance)
                .map(DestroyArmyObjective.class::cast)
                .filter(o -> o.getArmyColor().equals(this.initialPlayerColor))
                .findFirst()
                .get();

        // At the beginning of the game winner must be empty
        assertEquals(Optional.empty(),
                this.winCondition.getWinner(this.initialMap, this.initialPlayer, armyObjective));

        // Testing player owns one less than the required number
        var testMap = new HashMap<>(this.initialMap);
        final Set<Territory> alreadyOwnedTerritories = getOwnedTerritories(testMap, this.initialPlayerColor);
        final int alreadyOwnedTerritoriesAmount = alreadyOwnedTerritories.size();
        int territoriesToAddAmount = DEFAULT_NUM_TERRITORIES - alreadyOwnedTerritoriesAmount - 1;
        final Set<Territory> territoriesToAddLess = getUnownedTerritories(testMap,
                alreadyOwnedTerritories,
                territoriesToAddAmount);
        testMap.entrySet().forEach(e -> {
            if (territoriesToAddLess.contains(e.getKey())) {
                e.setValue(new Pair<>(this.initialPlayerColor, 1));
            }
        });
        assertEquals(Optional.empty(),
                this.winCondition.getWinner(testMap, this.initialPlayer, armyObjective));

        // Testing player owns the exact amount
        testMap = new HashMap<>(this.initialMap);
        territoriesToAddAmount = DEFAULT_NUM_TERRITORIES - alreadyOwnedTerritoriesAmount;
        final Set<Territory> territoriesToAddEqual = getUnownedTerritories(testMap,
                alreadyOwnedTerritories,
                territoriesToAddAmount);
        testMap.entrySet().forEach(e -> {
            if (territoriesToAddEqual.contains(e.getKey())) {
                e.setValue(new Pair<>(this.initialPlayerColor, 1));
            }
        });
        assertEquals(Optional.of(this.initialPlayer),
                this.winCondition.getWinner(testMap, this.initialPlayer, armyObjective));
    }

    private Set<Territory> getOwnedTerritories(final Map<Territory, Pair<GameColor, Integer>> territoriesMap,
            final GameColor playerColor) {
        return territoriesMap.entrySet().stream()
                .filter(e -> e.getValue().x().equals(playerColor))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    private Set<Territory> getUnownedTerritories(final Map<Territory, Pair<GameColor, Integer>> territoriesMap,
            final Set<Territory> ownedTerritories,
            final int amount) {
        return territoriesMap.keySet().stream()
                .filter(t -> !ownedTerritories.contains(t))
                .limit(amount)
                .collect(Collectors.toSet());
    }

    private GameColor getDifferentRandomColor(final GameColor excluded) {
        GameColor randColor;
        do {
            randColor = GameColor.values()[this.random.nextInt(GameColor.values().length)];
        } while (randColor == excluded);
        return randColor;
    }
}
