package model.objects.structures;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.objects.AbstractGameObject;
import model.objects.terrains.Terrain;
import model.player.Player;
import model.resources.Resource;

/**
 * Class that defines the standard behavior for the ResourceProducers.
 */
public abstract class AbstractResourceProducer extends AbstractGameObject implements ResourceProducerScalable {

    private final Resource resource;
    private final List<String> correctTerrains = new LinkedList<>();
    private Optional<Player> conqueror = Optional.empty();

    /**
     * @param resource the resource that this ResourceProducer will produce
     */
    public AbstractResourceProducer(final Resource resource) {
        this.resource = resource;
    }

    /**
     * @param list the list of terrains on which this structure can be built
     */
    public void addBuildableTerrain(final List<String> list) {
        this.correctTerrains.addAll(list);
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canBeBuilt(final Terrain terrain) {
        return this.correctTerrains.contains(terrain.getId());
    }

    /** {@inheritDoc} **/
    @Override
    public abstract int produce(double modifier);

    /** {@inheritDoc} **/
    @Override
    public abstract int getQuantity();

    /** {@inheritDoc} **/
    @Override
    public Resource getResource() {
        return this.resource;
    }

    /** {@inheritDoc} **/
    @Override
    public abstract boolean isOver();

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

    /**
     * Checks whether the structure has an owner.
     */
    protected void checkOwner() {
        if (!this.getOwner().isPresent()) {
            throw new IllegalStateException("Can't produce without an owner!");
        }
    }

}
