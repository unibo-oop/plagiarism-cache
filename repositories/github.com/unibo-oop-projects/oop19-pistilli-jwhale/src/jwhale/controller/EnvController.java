package jwhale.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jwhale.commons.ItemType;
import jwhale.model.Container;
import jwhale.model.ContainerHandler;
import jwhale.model.Environment;
import jwhale.model.Item;
import jwhale.model.ItemHandler;
import jwhale.model.Container.Builder;
import jwhale.model.connector.ConnectionException;
import jwhale.model.connector.DaemonResponseException;
import jwhale.model.engine.operations.object.Playback;
/**
 * Environment controller. It controls completely an environment.
 *
 */
public class EnvController {
    private final String name;
    private final Environment env;
    private final ItemHandler itemHandler;
    private final ContainerHandler containerHandler;
    private final Builder containerBuilder;
    private final List<ContainerPreView> list = new LinkedList<>();
    /**
     * Creates an EnvController instance, which controls environment.
     * @param env
     *          controlled environment.
     */
    public EnvController(final Environment env) {
        this.env = env;
        this.name = env.getEnvName();
        itemHandler = env.getItemHandler();
        containerHandler = env.getContainerHandler();
        containerBuilder = env.getContainerBuilder();
        containerHandler.getContainers().forEach(e -> {
            list.add(new ContainerPreView(e));
        });
    }
    /**
     * Get controller name. It's equal to environment name.
     * @return
     *          controller's name.
     */
    public final String getControllerName() {
        return name;
    }
    /**
     * Get environment's url.
     * @return
     *          remote url as string.
     */
    public final String getUrl() {
        return env.getUrl();
    }
    /**
     * Get remote TCP port on which Docker daemon listens.
     * @return
     *          port number as string.
     */
    public final String getPort() {
        return env.getPort();
    }
    /**
     * Get all containers of an environment.
     * @return
     *          all containers in the environment.
     */
    public final Set<Container> getContainers() {
        return Collections.unmodifiableSet(containerHandler.getContainers());
    }
    /**
     * Get a set with images, networks and volumes of the environment.
     * @param t
     *          Item type from ItemType enumeration.
     * @return
     *          set of Item.
     */
    public final Set<Item> getItems(final ItemType t) {
        return Collections.unmodifiableSet(itemHandler.getSet(t));
    }
    /**
     * Orders to perform a play back operation on a container.
     * It can be started, stopped or restarted.
     * @param containerName
     *          container on which perform operation.
     * @param op
     *          operation to perform, from Playback enumeration.
     * @throws ConnectionException
     *          the daemon is unreachable.
     * @throws DaemonResponseException
     *          the operation was not successful.
     */
    public final void playBackContainer(final String containerName, final Playback op) throws ConnectionException, 
    DaemonResponseException {
        containerHandler.playBackOp(containerName, op);
    }
    /**
     * Orders to perform a remove operation.
     * @param containerName
     *          container to remove.
     * @throws ConnectionException
     *          the daemon is unreachable.
     * @throws DaemonResponseException
     *          the operation was not successful.
     */
    public final void removeContainer(final String containerName) throws ConnectionException, 
    DaemonResponseException {
        containerHandler.removeOp(containerName);
        list.removeIf(p -> p.getName().equals(containerName));
    }
    /**
     * Orders to perform a rename operation.
     * @param containerName
     *          container to rename.
     * @param newName
     *          new name.
     * @throws ConnectionException
     *          the daemon is unreachable.
     * @throws DaemonResponseException
     *          the operation was not successful.
     */
    public final void renameContainer(final String containerName, final String newName) throws ConnectionException, 
    DaemonResponseException {
        containerHandler.renameOp(containerName, newName);
    }
    /**
     * Orders to remove an item from the environment.
     * @param itemName
     *          item's name to remove.
     * @param t
     *          item's type to remove, from ItemType enumeration.
     * @throws ConnectionException
     *          the daemon is unreachable.
     * @throws DaemonResponseException
     *          the operation was not successful.
     */
    public final void removeItem(final String itemName, final ItemType t) throws ConnectionException, 
    DaemonResponseException {
        itemHandler.removeOperation(itemName, t);
    }
    /**
     * Orders to create an item from the environment.
     * @param itemName
     *          item's name to create.
     * @param t
     *          item's type to create, from ItemType enumeration.
     * @throws ConnectionException
     *          the daemon is unreachable.
     * @throws DaemonResponseException
     *          the operation was not successful.
     */
    public final void createItem(final String itemName, final ItemType t) throws ConnectionException, 
    DaemonResponseException {
        itemHandler.createOperation(itemName, t);
    }
    /**
     * Orders to create an item from the environment, with a feature.
     * @param itemName
     *          item's name to remove.
     * @param t
     *          item's type to remove, from ItemType enumeration.
     * @param feature 
     *          characterizer of item.
     * @throws ConnectionException
     *          the daemon is unreachable.
     * @throws DaemonResponseException
     *          the operation was not successful.
     */
    public final void createItem(final String itemName, final String feature, final ItemType t) throws ConnectionException, 
    DaemonResponseException {
        itemHandler.createOperation(itemName, feature, t);
    }
    /**
     * Get default mountpath volumes.
     * @param name
     *          volume's name.
     * @return
     *          volume's mount point.
     */
    public final String getMountPath(final String name) {
        return itemHandler.getMountPointPath(name);
    }
    /**
     * Build an image from a tar archive.
     * @param tag
     *          image name.
     * @param archivePath
     *          archive path.
     * @throws ConnectionException
     *          the daemon is unreachable.
     * @throws DaemonResponseException
     *          the operation was not successful.
     * @throws IOException 
     *          can't get archive.
     */
    public final void imageBuild(final String tag, final String archivePath) throws ConnectionException, 
    IOException, DaemonResponseException {
        itemHandler.imageBuild(tag, archivePath);
    }
    /**
     * During container's creation process, it orders to set a container name.
     * @param containerName
     *          container name.
     */
    public final void setContainerName(final String containerName) {
        containerBuilder.setName(containerName);
    }
    /**
     * During container's creation process, it orders to set a network.
     * @param networkName
     *          network name to set.
     */
    public final void setContainerNetwork(final String networkName) {
        containerBuilder.setNetwork(networkName);
    }
    /**
     * Set from which image the container will derive.
     * @param imageName
     */
    public final void setContainerImage(final String imageName) {
        containerBuilder.setImage(imageName);
    }
    /**
     * During container's creation process, it sets a volume.
     * @param volumeName
     *          volume to set.
     */
    public final void setContainerVolume(final String volumeName) {
        containerBuilder.setVolume(volumeName);
    }
    /**
     * Expose container's port.
     * @param publicPort
     *          Host port number.
     * @param privatePort
     *          Container host number.
     */
    public final void exposeContainerPort(final Integer publicPort, final Integer privatePort) {
        containerBuilder.addPorts(privatePort, publicPort);
    }
    /**
     * Push new container to remote source.
     * @throws ConnectionException
     *          the daemon is unreachable.
     */
    public final void pushContainer() throws ConnectionException {
        containerBuilder.setCreationTime(String.valueOf(Instant.now().getEpochSecond()));
        containerHandler.pushContainer(containerBuilder.build());
        env.cleanBuilder();
    }
    /**
     * Get detailed container information.
     * @param containerName
     *          container to inspet.
     * @return
     *          string inspect data.
     * @throws ConnectionException
     * @throws DaemonResponseException
     */
    public final String inspectContainer(final String containerName) throws ConnectionException, 
    DaemonResponseException {
        return containerHandler.inspect(containerName);
    }
    /**
     * Get a specific type of item as a string list.
     * @param type
     *          Item type to get.
     * @return
     *          item list of strings.
     */
    public final List<String> getItemsAsList(final ItemType type) {
        return itemHandler.getSet(type)
                .stream()
                .map(f -> f.getName())
                .collect(Collectors.toList());
    }
    /**
     * Get a list of wrapped container.
     * @return
     *          wrapped list.
     */
    public final List<ContainerPreView> getPreViewList() {
        return list;
    }
}
