package jwhale.model.engine.operations.creation;
/**
 * Constants for body request sections in creation container operation.
 * 
 */
public enum CreationParam {
    /**
     * Environment variables body section.
     */
    ENV("Env"),
    /**
     * Exposed ports body section.
     */
    EXP("ExposedPorts"),
    /**
     * Configuration section for host parameters.
     */
    HOST_CONF("HostConfig"),
    /**
     * Section for container/host port mapping.
     */
    PORT_BIND("PortBindings"),
    /**
     * Section for network mode.
     */
    NET_MODE("NetworkMode"),
    /**
     * Section for exposed host port.
     */
    HOST_PORT("HostPort"),
    /**
     * Section for volume configuration.
     */
    VOLUMES("Mounts"),
    /**
     * Path value to export into volume.
     */
    VOL_TARGET("Target"),
    /**
     * Volume name value.
     */
    VOL_SOURCE("Source"),
    /**
     * External storage type.
     */
    VOL_TYPE("Type");
    private String param;

    CreationParam(final String param) {
        this.param = param;
    }
    /**
     * Getter for section name.
     * @return section name as String
     */
    public String getParam() {
        return this.param;
    }

}
