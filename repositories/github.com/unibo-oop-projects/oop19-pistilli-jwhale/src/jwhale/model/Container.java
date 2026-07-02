package jwhale.model;

import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import jwhale.model.engine.Operation;
import jwhale.model.engine.operations.creation.ContainerCreate;
import jwhale.model.engine.operations.object.ContainerInspect;
import jwhale.model.engine.operations.object.ContainerPlayback;
import jwhale.model.engine.operations.object.ContainerRemove;
import jwhale.model.engine.operations.object.ContainerRename;
import jwhale.model.engine.operations.object.ObjectOp;
import jwhale.model.engine.operations.object.Playback;
/**
 * It represents container entity.
 */
public final class Container {
    private String name;
    private final String creationTime;
    private String status;
    private final Optional<Map<Integer, Integer>> ports;
    private final Image image;
    private final Volume volume;
    private final Network network;
    private final ObjectOp<ContainerPlayback> playBackConfig = new ContainerPlayback();
    private final ObjectOp<ContainerRemove> removerConfig = new ContainerRemove();
    private final ObjectOp<ContainerRename> renamerConfig = new ContainerRename();
    private final ObjectOp<ContainerInspect> inspectConfig = new ContainerInspect();

    private Container(final String name, final long creationTime, final String status,
            final Optional<Map<Integer, Integer>> ports, final Image image, final Volume volume, 
            final Network network) {
        this.name = name.replaceAll("/", "");
        this.creationTime = String.valueOf(Instant
                .ofEpochSecond(creationTime)
                .atZone(ZoneId.systemDefault()));
        this.status = status;
        this.ports = ports;
        this.image = image;
        this.volume = volume;
        this.network = network;
    }
    /**
     * Get container's name.
     * @return
     *          container's name.
     */
    public String getContainerName() {
        return name;
    }
    /**
     * Get creation time according to local system time zone.
     * @return
     *          container's creation time.
     */
    public String getCreationTime() {
        return creationTime;
    }
    /**
     * Get container status.
     * @return
     *          container's status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * Get image instance from which this container derives.
     * @return
     *          image instance.
     */
    public Image getImage() {
        return image;
    }
    /**
     * Get volume instance connected to the container.
     * @return
     *          container's volume.
     */
    public Volume getVolume() {
        return volume;
    }
    /**
     * Get container's network.
     * @return
     *  container's network.
     */
    public Network getNetwork() {
        return network;
    }
    /**
     * Get exposed ports.
     * @return
     *          Map with ports if exposed, empty otherwise.
     */
    public Optional<Map<Integer, Integer>> getPorts() {
        return ports;
    }
    /**
     * Update container status.
     * @param status
     *          new status.
     */
    public void updateStatus(final String status) {
        this.status = status;
    }
    /**
     * Update name after a rename operation.
     * @param name
     *          new name.
     */
    public void updateName(final String name) {
        this.name = name;
    }
    /**
     * Get an operation instance for play back operation.
     * @param operation
     *          Playback operation from Playback enumeration.
     * @return
     *          operation for connector.
     */
    public Operation playback(final Playback operation) {
        return playBackConfig.target(name)
                .setOperation(operation)
                .configOperation();
    }
    /**
     * Get an operation instance for remove operation.
     * @return
     *          operation for connector.
     */
    public Operation remove() {
        return removerConfig.target(name)
                .configOperation();
    }
    /**
     * Get an operation instance for rename operation.
     * @param newName
     *          new name.
     * @return
     *          operation for connector.
     */
    public Operation rename(final String newName) {
        return renamerConfig.target(name)
                .setNewName(newName)
                .configOperation();
    }
    /**
     * Get an operation instance for inspect operation.
     * @return
     *          operation for connector.
     */
    public Operation inspect() {
        return inspectConfig.target(name).configOperation();
    }
    /**
     * Get an operation instance for push container to remote source.
     * @return
     *          operation for connector.
     */
    public Operation push() {
        final ContainerCreate o = new ContainerCreate()
              .create(image.getName())
              .setName(name)
              .setNetwork(network.getName())
              .setVolume(volume.getFeature(), volume.getName());
        ports.ifPresent(e -> {
            e.entrySet().forEach(f -> {
                o.exposePort(String.valueOf(f.getKey()), String.valueOf(f.getValue()), "tcp");
            });
        });
        return o.configOperation();
    }

    public static class Builder {
        private String name;
        private String status = "Created";
        private long creationTime;
        private Optional<Map<Integer, Integer>> ports = Optional.empty();
        private final Set<Item> images;
        private final Set<Item> networks;
        private final Set<Item> volumes;
        private Image image;
        private Volume volume;
        private Network network;
        /**
         * Builder to generate a new container.
         * @param networks
         *      networks set of environment.
         * @param volumes
         *      volumes set of environment.
         * @param images
         *      images set of environment.
         */
        public Builder(final Set<Item> networks, final Set<Item> volumes, final Set<Item> images) {
            this.networks = networks;
            this.volumes = volumes;
            this.images = images;
            this.network = EnvUtils.searchItem("bridge", networks);
            this.volume = EnvUtils.searchItem("data", volumes);
        }
        /**
         * Set container name.
         * @param name
         *      container's name.
         * @return
         *      itself
         */
        public final Builder setName(final String name) {
            this.name = name;
            return this;
        }
        /**
         * Set container creation time.
         * @param creationTime
         *      container's creation time.
         * @return
         *      itself
         */
        public final Builder setCreationTime(final String creationTime) {
            this.creationTime = Long.parseLong(creationTime);
            return this;
        }
        /**
         * Set container's status.
         * @param status
         *      container's status.
         * @return
         *      itself
         */
        public final Builder setStatus(final String status) {
            this.status = status;
            return this;
        }
        /**
         * Set container's image.
         * @param imageName
         *      container's image.
         * @return
         *      itself
         */
        public final Builder setImage(final String imageName) {
            this.image = EnvUtils.searchItem(imageName, images);
            return this;
        }
        /**
         * Set container volume.
         * @param volumeName
         *      container's volume.
         * @return
         *      itself
         */
        public final Builder setVolume(final String volumeName) {
            this.volume = EnvUtils.searchItem(volumeName, volumes);
            return this;
        }
        /**
         * Set container network.
         * @param networkName
         *      container's network.
         * @return
         *      itself
         */
        public final Builder setNetwork(final String networkName) {
            this.network = EnvUtils.searchItem(networkName, networks);
            return this;
        }
        /**
         * Expose container ports.
         * @param privatePort
         *      container's port.
         * @param publicPort
         *      host port.
         * @return
         *      itself
         */
        public final Builder addPorts(final Integer privatePort, final Integer publicPort) {
            this.ports.ifPresentOrElse(e -> e.put(privatePort, publicPort),  () -> {
                        ports = Optional.of(new HashMap<>());
                        ports.get().put(privatePort, publicPort);
                    });
            return this;
        }
        /**
         * Set exposed port from an Optional Map.
         * @param portMapping
         *      container's port mapping
         * @return
         *      itself
         */
        public final Builder setPorts(final Optional<Map<Integer, Integer>> portMapping) {
            this.ports = portMapping;
            return this;
        }
        /**
         * Build container.
         * @return
         *      Container instance.
         */
        public final Container build() {
            return new Container(name, creationTime, status, ports, image, volume, network);
        }


    }
}
