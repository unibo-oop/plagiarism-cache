package jwhale.model;

import jwhale.model.engine.Operation;
import jwhale.model.engine.operations.object.NetworkRemove;
import jwhale.model.engine.operations.object.ObjectOp;
/**
 * Network representation.
 */
public class Network implements Item {
    private final String networkName;
    private final String driver;
    private final ObjectOp<NetworkRemove> removerConfig = new NetworkRemove();
    /**
     * Create an instance of a network with specific driver.
     * @param name
     *          network name.
     * @param driver
     *          driver name.
     */
    public Network(final String name, final String driver) {
        this.networkName = name;
        this.driver = driver;
    }
    /**
     * Create an instance of a network with default bridge driver.
     * @param name
     *          network name.
     */
    public Network(final String name) {
        this.networkName = name;
        driver = "bridge";
    }
    @Override
    public final String getName() {
        return networkName;
    }
    @Override
    public final String getFeature() {
        return driver;
    }

    public final Operation remove() {
        return removerConfig.target(networkName)
                .configOperation();
    }

}
