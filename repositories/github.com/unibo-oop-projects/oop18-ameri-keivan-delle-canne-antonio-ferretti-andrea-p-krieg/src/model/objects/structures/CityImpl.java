package model.objects.structures;

import java.util.Optional;

import model.objects.AbstractGameObject;
import model.objects.terrains.Terrain;
import model.player.Player;
import model.resources.Resource;
import model.resources.BasicResources;

/**
 * The CityImpl class extends AbstractGameObject and implements the marker
 * interface City. This class represent an implementation of the game object
 * city.
 */
public class CityImpl extends AbstractGameObject implements City {

    /**
     * POPULATION is a static variable that indicates the basic given population. It
     * is also used in CapitalImpl as a basis to calculate the capitals population
     * production.
     */
    protected static final int POPULATION = 5;
    private static final String TERRAIN = "Base";

    private final Resource resource;
    private Optional<Player> conqueror = Optional.empty();

    /**
     * CityImpl constructor.
     * 
     * @param player is the player that own the city. If it isn't present, the city will be neutral.
     */
    public CityImpl(final Optional<Player> player) {
        this.resource = BasicResources.POPULATION;
        if (player.isPresent()) {
            setOwner(player.get());
        }
    }
    /**
     * CityImpl empty constructor.
     * Builds a neutral city.
     */
    public CityImpl() {
        this(Optional.empty());
    }

    /** {@inheritDoc} **/
    @Override
    public int getQuantity() {
        return POPULATION;
    }

    /** {@inheritDoc} **/
    @Override
    public Resource getResource() {
        return this.resource;
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canBeBuilt(final Terrain terrain) {
        return terrain.getId().equals(TERRAIN);
    }

    /** {@inheritDoc} **/
    @Override
    public String getDescription() {
        return "City" + "\nOwner: " + getOwnerName();
    }

    /** {@inheritDoc} **/
    @Override
    public void initiateConquer(final Player player) {
        this.conqueror = Optional.of(player);
    }

    /** {@inheritDoc} **/
    @Override
    public void endConquer() {
        this.conqueror = Optional.empty();
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Player> getConqueror() {
        return this.conqueror;
    }
}
