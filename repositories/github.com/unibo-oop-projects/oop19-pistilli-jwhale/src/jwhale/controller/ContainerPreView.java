package jwhale.controller;

import jwhale.model.Container;
/**
 * Helper class to wrap container representation. It'useful for fetch
 * container information.
 */
public class ContainerPreView {
    private final Container container;
    /**
     * Simple public constructor.
     * @param container
     *          container object to wrap in.
     */
    public ContainerPreView(final Container container) {
        this.container = container;
    }
    /**
     * Get container name.
     * @return
     *          container name.
     */
    public final String getName() {
        return container.getContainerName();
    }
    /**
     * Get container creation time.
     * @return
     *          container creation time as string, according to system local time zone.
     */ 
    public final String getCreationTime() {
        return container.getCreationTime();
    }
    /**
     * Get container status. It could be Up, Exited or Created.
     * @return
     *          container status as string.
     */
    public final String getStatus() {
        return container.getStatus();
    }
    /**
     * Get image name from which the container derives.
     * @return
     *          image name.
     */
    public final String getImage() {
        return container.getImage().getName();
    }
    /**
     * Get image version.
     * @return
     *          specific image version or "latest". 
     */
    public final String getImageVersion() {
        return container.getImage().getFeature();
    }
    /**
     * Get network name.
     * @return
     *          network name.
     */
    public final String getNetwork() {
        return container.getNetwork().getName();
    }
    /**
     * Get volume name.
     * @return
     *          volume name.
     */
    public final String getVolume() {
        return container.getVolume().getName();
    }
}
