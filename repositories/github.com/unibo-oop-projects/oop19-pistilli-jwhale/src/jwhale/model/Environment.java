package jwhale.model;

import java.util.Collections;
import java.util.Set;
import jwhale.commons.ItemType;
import jwhale.model.Container.Builder;
import jwhale.model.connector.ConnectionException;
import jwhale.model.connector.Connector;
import jwhale.model.connector.DaemonResponseException;
import jwhale.model.connector.NetworkConnector;
import jwhale.model.engine.operations.list.ContainerList;
import jwhale.model.engine.operations.list.ImageList;
import jwhale.model.engine.operations.list.NetworkList;
import jwhale.model.engine.operations.list.VolumeList;
/**
 * It represents a Docker environment.

 */
public final class Environment {
    private static final int LIST_CODE = 200;
    private final String name;
    private final String port;
    private final String url;
    private final Set<Container> containers;
    private final Set<Item> networks;
    private final Set<Item> images;
    private final Set<Item> volumes;
    private final Connector connector;
    private final ContainerHandler containerHandler;
    private final ItemHandler itemHandler;
    private final ResponseValidator validator = new ResponseValidator();
    private Builder builder;

    /**
     * Create a local representation of a remote Docker environment.
     * @param url
     *          environment address
     * @param port
     *          environment port
     * @param name
     *          environment name
     * @throws ConnectionException
     * @throws DaemonResponseException
     */
    public Environment(final String url, final String port, final String name) throws ConnectionException, 
    DaemonResponseException {
        connector = new NetworkConnector(url, port);
        connector.init();
        this.name = name;
        this.port = port;
        this.url = url;
        itemHandler = new ItemHandler(connector);
        containerHandler = new ContainerHandler(connector);
        networks = itemHandler.getSet(ItemType.NETWORK);
        images = itemHandler.getSet(ItemType.IMAGE);
        volumes = itemHandler.getSet(ItemType.VOLUME);
        containers = containerHandler.getContainers();
        fetchEnv();
    }
    /**
     * Get environment name.
     * @return
     *          environment name as string.
     */
    public String getEnvName() {
        return name;
    }
    /**
     * Get environment address.
     * @return
     *          environment address
     */
    public String getUrl() {
        return url;
    }
    /**
     * Get environment port.
     * @return
     *          environment port.
     */
    public String getPort() {
        return port;
    }
    /**
     * Get environment items as a specific set.
     * @param type
     *          item type according to ITemType enumeration.
     * @return
     *          set of item (network, images or volumes)
     */
    public Set<Item> getItemSet(final ItemType type) {
        return type.toString().equals("Network") ? Collections.unmodifiableSet(networks) 
                : (type.toString().equals("Image") ? Collections.unmodifiableSet(images) 
                        : Collections.unmodifiableSet(volumes));
    }
    /**
     * Get item handler object, useful for perform operations on items.
     * @return
     *          item operation handler.
     */
    public ItemHandler getItemHandler() {
        return itemHandler;
    }
    /**
     * Get container handler object, useful for perform operations on containers.
     * @return
     *          container operation handler.
     */
    public ContainerHandler getContainerHandler() {
        return containerHandler;
    }
    /**
     * Get container builder to generate containers.
     * @return
     *          Container builder.
     */
    public Builder getContainerBuilder() {
        return builder;
    }
    /**
     * Clean container builder instance, overwriting the existent one.
     */
    public void cleanBuilder() {
        builder = new Container.Builder(networks, volumes, images);
    }
    private void fetchEnv() throws ConnectionException, DaemonResponseException {
        validator.setStatusCode(LIST_CODE);
        //Get all networks
        connector.sendRequest(new NetworkList().baseSetup().configOperation().buildCall());
        validator.validate(connector.getResponse());
        EnvUtils.trackNetworks(connector.getResponse().body(), networks);
        //Get all images
        connector.sendRequest(new ImageList().baseSetup().configOperation().buildCall());
        validator.validate(connector.getResponse());
        EnvUtils.trackImages(connector.getResponse().body(), images);
        //Get all volumes
        connector.sendRequest(new VolumeList().baseSetup().configOperation().buildCall());
        validator.validate(connector.getResponse());
        EnvUtils.trackVolumes(connector.getResponse().body(), volumes);
        //Get all containers
        connector.sendRequest(new ContainerList().baseSetup().configOperation().buildCall());
        validator.validate(connector.getResponse());
        EnvUtils.trackContainers(connector.getResponse().body(), containers, networks, images, volumes);
        builder = new Container.Builder(networks, volumes, images);
        itemHandler.setDefMountPoint(EnvUtils.searchItem("data", volumes).getFeature());
    }
}
