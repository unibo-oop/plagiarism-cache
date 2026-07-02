package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.impl.PlayerImpl;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

/**
 * Test class to test Player.
 */
class TestPlayer {

    private Player player;
    private final Set<Territory> territory = new TerritoryFactoryImpl().createTerritories();
    private final Set<Objective> objective = new ObjectiveFactoryImpl().createObjectives();

    @BeforeEach
    void initPlayer() {
        player = new PlayerImpl(Player.GameColor.RED, objective.stream().findFirst().get(), new HashSet<>());
    }

    @Test
    void testTerritories() {
        final var iterator = territory.iterator();
        while (iterator.hasNext()) {
            final var temp = iterator.next();
            player.removePlayerTerritory(temp);
            if ("Cina".equals(temp.getName())) {
                player.addPlayerTerritory(temp);
                assertEquals(player.getOwnedTerritories(), Set.of(temp));
                player.removePlayerTerritory(temp);
                assertEquals(player.getOwnedTerritories(), Set.of());
            }
        }
    }

    @Test
    void testGetPlayer() throws CloneNotSupportedException {
        assertEquals(player.getColor(), Player.GameColor.RED);
        final Player temp = player.getPlayer();
        assertEquals(temp.getColor(), Player.GameColor.RED);
        assertNotEquals(temp, player);
    }

    @Test
    void testGetObjective() {
        final var temp = player.getObjective();
        assertNotEquals(temp, objective.stream().findFirst().get());
        assertNotEquals(temp, player.getObjective());
    }

    @Test
    void testGetBonus() {
        player.addPlayerTerritory(getTerritory("Groenlandia"));
        player.addPlayerTerritory(getTerritory("Canada"));
        assertEquals(1, player.getBonusGroundDino());
        player.addPlayerTerritory(getTerritory("Messico"));
        assertEquals(1, player.getBonusGroundDino());
        player.addPlayerTerritory(getTerritory("Appalachia"));
        // CHECKSTYLE: MagicNumber OFF
        // Test purpuse
        assertEquals(5, player.getBonusGroundDino());
        // CHECKSTYLE: MagicNumber ON
    }

    /**
     * @param name name of the Territory
     * @return the territory based of the name
     */
    private Territory getTerritory(final String name) {
        final var result = territory.stream()
                .filter(e -> e.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
                .findFirst();
        return result.get();
    }
}
