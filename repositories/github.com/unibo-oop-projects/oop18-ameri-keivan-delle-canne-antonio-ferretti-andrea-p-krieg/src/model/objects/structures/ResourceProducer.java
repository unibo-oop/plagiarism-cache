package model.objects.structures;

import model.resources.Resource;

/**
 * Models every Structure that can produce a specific Resource.
 */
public interface ResourceProducer extends OwnableStructure {
    /**
     * @return the quantity produced
     */
    int getQuantity();

    /**
     * @return the resource produced
     */
    Resource getResource();
}
