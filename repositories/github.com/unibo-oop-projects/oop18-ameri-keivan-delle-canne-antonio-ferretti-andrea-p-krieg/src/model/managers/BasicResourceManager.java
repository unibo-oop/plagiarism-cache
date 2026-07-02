package model.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.player.Player;
import model.resources.BasicResources;
import model.resources.Resource;

/**
 * The ResourceManager for the Basic Resources.
 */
public class BasicResourceManager extends AbstractResourceManager {

    /**
     * @param players       the players to be managed
     * 
     * @param initialValues the initial values of the resources
     */
    public BasicResourceManager(final List<Player> players, final int initialValues) {
        super(players, initialValues);
    }

    /** @{inheritDoc} **/
    @Override
    protected Map<Resource, Integer> getResource(final int value) {
        final Map<Resource, Integer> resourcesMap = new HashMap<>();
        for (final Resource r : BasicResources.values()) {
            if (r.getName().equals("Population")) {
                resourcesMap.put(r, 0);
            } else {
                resourcesMap.put(r, value);
            }
        }
        return resourcesMap;
    }

    /** @{inheritDoc} **/
    @Override
    public void decreaseMax(final Player player, final int quantity) {
        for (final Entry<Resource, Integer> e : this.getMaxResources(player).entrySet()) {
            this.setMaxResourceQuantity(player, e.getKey(), e.getValue() - quantity * e.getKey().getModifier().orElse(1));
            if (!e.getKey().getName().equals("Population") && this.getGlobalResources(player).get(e.getKey()) > this.getMaxResources(player).get(e.getKey())) {
                this.setGlobalResourceQuantity(player, e.getKey(), e.getValue());
            }
        }
    }


}
