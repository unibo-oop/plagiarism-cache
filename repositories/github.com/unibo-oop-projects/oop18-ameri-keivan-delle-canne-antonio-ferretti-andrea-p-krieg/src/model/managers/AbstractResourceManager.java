package model.managers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.player.Player;
import model.resources.Resource;

/**
 * The manager of the resources.
 */
public abstract class AbstractResourceManager implements ResourceManager {

    private final Map<Player, Map<Resource, Integer>> globalResources;
    private final Map<Player, Map<Resource, Integer>> maxResources;

    /**
     * @param players       the players in game
     * 
     * @param initialValues the initial values for each resource
     */
    public AbstractResourceManager(final List<Player> players, final int initialValues) {
        this.globalResources = new HashMap<>();
        this.maxResources = new HashMap<>();
        players.forEach(p -> this.globalResources.put(p, this.getResource(initialValues)));
        players.forEach(p -> this.maxResources.put(p, this.getResource(initialValues)));
    }

    /** @{inheritDoc} **/
    @Override
    public void increaseResource(final Player player, final Resource resource, final int quantity) {
        final Map<Resource, Integer> provisory = this.globalResources.get(player);
        if (provisory.get(resource) + quantity > this.maxResources.get(player).get(resource)) {
            provisory.put(resource, maxResources.get(player).get(resource));
        } else {
            provisory.put(resource, provisory.get(resource) + quantity);
        }
    }

    /**
     * @param player the player to get resources from
     * 
     * @return the map of the resources of the player
     */
    protected Map<Resource, Integer> getGlobalResources(final Player player) {
        return Collections.unmodifiableMap(globalResources.get(player));
    }

    /**
     * @param player the player to be set the resource at
     * 
     * @param res the resource to be set
     * 
     * @param quantity the quantity of the resource
     */
    protected void setGlobalResourceQuantity(final Player player, final Resource res, final int quantity) {
        this.globalResources.get(player).put(res, quantity);
    }

    /**
     * @param player the player to get resources from
     * 
     * @return the map of the max resources of the player
     */
    protected Map<Resource, Integer> getMaxResources(final Player player) {
        return Collections.unmodifiableMap(maxResources.get(player));
    }

    /**
     * @param player the player to be set the resource at
     * 
     * @param res the resource to be set
     * 
     * @param quantity the quantity of the resource
     */
    protected void setMaxResourceQuantity(final Player player, final Resource res, final int quantity) {
        this.maxResources.get(player).put(res, quantity);
    }

    /** @{inheritDoc} **/
    @Override
    public void decreaseResource(final Player player, final Resource resource, final int quantity) {
        final Map<Resource, Integer> provisory = this.globalResources.get(player);
        if (provisory.get(resource) - quantity < 0) {
            throw new IllegalArgumentException();
        } else {
            provisory.put(resource, provisory.get(resource) - quantity);
        }
    }

    /** @{inheritDoc} **/
    @Override
    public Map<Resource, Integer> getPlayerResourceMap(final Player player) {
        return Collections.unmodifiableMap(this.globalResources.get(player));
    }

    /** @{inheritDoc} **/
    @Override
    public Map<Resource, Integer> getPlayerMaxResourceMap(final Player player) {
        return Collections.unmodifiableMap(this.maxResources.get(player));
    }

    /**
     * @param value the value to be initialized.
     * 
     * @return the map initialized with every Resource implemented
     */
    protected abstract Map<Resource, Integer> getResource(int value);

    /** @{inheritDoc} **/
    @Override
    public String getPlayerResourcesInfo(final Player player) {
        String returnString = "";
        final Map<Resource, Integer> provisory = this.globalResources.get(player);
        final Map<Resource, Integer> prov = this.maxResources.get(player);
        for (final Resource r : provisory.keySet()) {
            returnString += r.getName() + ": " + provisory.get(r) + " / " + prov.get(r) + "\n";
        }
        return returnString;
    }

    /** @{inheritDoc} **/
    @Override
    public void increaseMax(final Player player, final int quantity) {
        for (final Entry<Resource, Integer> e : this.maxResources.get(player).entrySet()) {
            e.setValue(e.getValue() + quantity * e.getKey().getModifier().orElse(1));
        }
    }

    /** @{inheritDoc} **/
    @Override
    public abstract void decreaseMax(Player player, int quantity);

    /** @{inheritDoc} **/
    @Override
    public void resetMax(final Player player) {
        this.maxResources.get(player).entrySet().forEach(e -> e.setValue(0));
    }
}
