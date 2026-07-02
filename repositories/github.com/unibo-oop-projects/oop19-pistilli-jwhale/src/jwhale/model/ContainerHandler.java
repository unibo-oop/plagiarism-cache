package jwhale.model;

import java.util.HashSet;
import java.util.Set;
import jwhale.model.connector.ConnectionException;
import jwhale.model.connector.Connector;
import jwhale.model.connector.DaemonResponseException;
import jwhale.model.engine.operations.object.Playback;
/**
 * Helper class for environment handling.
 */
public class ContainerHandler {
    private static final int OP_CODE = 204;
    private static final int OK = 200;
    private final Connector connector;
    private final ResponseValidator containerValidator = new ResponseValidator();
    private final Set<Container> containers = new HashSet<>();
    /**
     * Create a container handler instance.
     * @param connector
     *          network connector.
     */
    public ContainerHandler(final Connector connector) {
        this.connector = connector;
    }
    /**
     * Get container's environment set.
     * @return
     *          set of containers.
     */
    public final Set<Container> getContainers() {
        return containers;
    }
    /**
     * Perform a playback operation.
     * @param containerName
     *          container's name.
     * @param op
     * @throws ConnectionException
     * @throws DaemonResponseException
     */
    public final void playBackOp(final String containerName, final Playback op) throws ConnectionException, 
    DaemonResponseException {
        containerValidator.setStatusCode(OP_CODE);
        final Container container = searchContainer(containerName);
        connector.sendRequest(container.playback(op).buildCall());
        if (containerValidator.validate(connector.getResponse())) {
            updateContainerStatus(op, container);
        } else {
            throw new DaemonResponseException(String.valueOf(connector.getResponse().statusCode()));
        }
    }
    /**
     * Perform a remove operation.
     * @param containerName
     *          container's name
     * @throws ConnectionException
     * @throws DaemonResponseException
     */
    public final void removeOp(final String containerName) throws ConnectionException, 
    DaemonResponseException {
        containerValidator.setStatusCode(OP_CODE);
        final Container toRemove = searchContainer(containerName);
        connector.sendRequest(toRemove.remove().buildCall());
        if (containerValidator.validate(connector.getResponse())) {
            containers.remove(toRemove);
        } else {
            throw new DaemonResponseException(String.valueOf(connector.getResponse().statusCode()));
        }
    }
    /**
     * Perform a rename operation.
     * @param containerName
     *          container's name
     * @param newName
     *          new name
     * @throws ConnectionException
     * @throws DaemonResponseException
     */
    public final void renameOp(final String containerName, final String newName) throws ConnectionException, 
    DaemonResponseException {
        containerValidator.setStatusCode(OP_CODE);
        final Container toRename = searchContainer(containerName);
        connector.sendRequest(toRename.rename(newName).buildCall());
        if (containerValidator.validate(connector.getResponse())) {
            toRename.updateName(newName);
        } else {
            throw new DaemonResponseException(String.valueOf(connector.getResponse().statusCode()));
        }
    }
    /**
     * Perform a inspect operation.
     * @param containerName
     *          container's 
     * @return
     *          container's information.
     * @throws ConnectionException
     * @throws DaemonResponseException
     */
    public final String inspect(final String containerName) throws ConnectionException, 
    DaemonResponseException {
        containerValidator.setStatusCode(OK);
        final Container toInspect = searchContainer(containerName);
        connector.sendRequest(toInspect.inspect().buildCall());
        if (containerValidator.validate(connector.getResponse())) {
            return connector.getResponse().body();
        } else {
            throw new DaemonResponseException(String.valueOf(connector.getResponse().statusCode()));
        }
    }
    /**
     * Push container operation.
     * @param toPush
     *          container to push 
     * @throws ConnectionException
     * @throws DaemonResponseException
     */
    public final void pushContainer(final Container toPush) throws ConnectionException {
        connector.sendRequest(toPush.push().buildCall());
        containers.add(toPush);
    }

    private void updateContainerStatus(final Playback op, final Container container) {
        if (op.equals(Playback.START) || op.equals(Playback.RESTART)) {
            container.updateStatus("Up");
        } else {
            container.updateStatus("Exited");
        }
    }

    private Container searchContainer(final String name) {
        return containers.stream()
                .filter(e -> e.getContainerName().equals(name))
                .findFirst()
                .get();
    }


}
