package jwhale.model.engine.operations.creation;

import com.google.gson.JsonObject;
import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * Network creation operation configurator.
 */
public final class NetworkCreate extends AbstractCreationalOpImpl<NetworkCreate> {
    private static final String LAST = "/create";
    private static final String NAME = "Name";
    private static final String DRIVER = "Driver";
    private final JsonObject bodyRequest = new JsonObject();

    public NetworkCreate() {
        super(Method.POST, EndPoint.NETWORK);
    }
    @Override
    public NetworkCreate create(final String mandatoryParam) {
        getOperation().setLastParam(LAST);
        bodyRequest.addProperty(NAME, mandatoryParam);
        getOperation().setBodyRequest(bodyRequest.toString());
        setSetup();
        return this;
    }
    /**
     * Sets network driver. If not used network will have "bridge" driver.
     * @param driver
     * @return itself
     */
    public NetworkCreate setDriver(final String driver) {
        bodyRequest.addProperty(DRIVER, driver);
        getOperation().setBodyRequest(bodyRequest.toString());
        return this;
    }
}
