package it.unibo.jurassiko.model.player.impl;

import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

/**
 * Implementation of the interface {@link Player}.
 */
public class PlayerImpl implements Player, Cloneable {

    private final Logger logger = LoggerFactory.getLogger(PlayerImpl.class);

    private final GameColor color;
    private final Objective objective;
    private final Set<Territory> territories;
    private final Set<Territory> totalTerritories = new TerritoryFactoryImpl().createTerritories();
    private static final Pair<String, Integer> NORD_AMERICA = new Pair<>("Nord America", 3);
    private static final Pair<String, Integer> GONDWANA_OCCIDENTALE = new Pair<>("Gondwana Occidentale", 5);
    private static final Pair<String, Integer> GONDWANA_ORIENTALE = new Pair<>("Gondwana Orientale", 3);
    private static final Pair<String, Integer> EUROASIA = new Pair<>("Eurasia", 6);

    /**
     * Constructor for the player.
     * 
     * @param color       player's color
     * @param objective   player's objective
     * @param territories player's owned territories
     */
    public PlayerImpl(final GameColor color,
            final Objective objective,
            final Set<Territory> territories) {
        this.color = color;
        Objects.requireNonNull(objective);
        this.objective = objective.getClone();
        this.territories = new HashSet<>(Objects.requireNonNull(territories));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameColor getColor() {
        return color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Objective getObjective() {
        return objective.getClone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayerTerritory(final Territory territory) {
        territories.add(territory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePlayerTerritory(final Territory territory) {
        if (territories.contains(territory)) {
            territories.remove(territory);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Territory> getOwnedTerritories() {
        return Set.copyOf(territories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBonusGroundDino() {
        int result = 0;
        result += bonusContinent(
                new Pair<Set<Territory>, Integer>(getContinent(NORD_AMERICA.x()), NORD_AMERICA.y()));
        result += bonusContinent(
                new Pair<Set<Territory>, Integer>(getContinent(GONDWANA_OCCIDENTALE.x()), GONDWANA_OCCIDENTALE.y()));
        result += bonusContinent(
                new Pair<Set<Territory>, Integer>(getContinent(GONDWANA_ORIENTALE.x()), GONDWANA_ORIENTALE.y()));
        result += bonusContinent(
                new Pair<Set<Territory>, Integer>(getContinent(EUROASIA.x()), EUROASIA.y()));
        return territories.size() / 2 + result;
    }

    /**
     * Used to check if the player has all the territory of a certain continent.
     * 
     * @param pair Pair of a Set or Territory and integer
     * @return the value of the bonus ground dino based on the continent you have
     */
    private int bonusContinent(final Pair<Set<Territory>, Integer> pair) {
        int result = 0;
        final Set<String> temp = territories.stream().map(t -> t.getName().toLowerCase(Locale.ROOT))
                .collect(Collectors.toSet());
        if (temp.containsAll(pair.x().stream()
                .map(t -> t.getName().toLowerCase(Locale.ROOT))
                .collect(Collectors.toSet()))) {
            result = result + pair.y();
        }
        return result;
    }

    /**
     * @param name name of the continent
     * @return Set of the territory with the same continent name
     */
    private Set<Territory> getContinent(final String name) {
        return totalTerritories.stream()
                .filter(e -> e.getContinent().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBonusWaterDino() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        try {
            return (Player) this.clone();
        } catch (CloneNotSupportedException e) {
            this.logger.error("Cannot create a copy of the player");
        }
        throw new IllegalStateException("Failed to create a copy of the player");
    }

}
