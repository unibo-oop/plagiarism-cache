package jwhale.model;

import jwhale.model.engine.Operation;
/**
 * Item is a characterizer element of a container. It could be
 * a network, an image or a volume.
 */
public interface Item {
    /**
     * Get item name.
     * @return
     *          item name.
     */
    String getName();
    /**
     * Get item feature.
     * @return
     *         characterizer element
     */
    String getFeature();
    /**
     * Get a configurator object to remove an item.
     * @return
     *          operation suitable for network connector.
     */
    Operation remove();

}
