package jwhale.model.engine.operations.object;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * Network remove operation configurator.
 */
public final class NetworkRemove extends AbstractObjectOpImpl<NetworkRemove> {
    public NetworkRemove() {
        super(Method.DELETE, EndPoint.NETWORK);
    }
    @Override
    public NetworkRemove target(final String mandatoryParam) {
        getOperation().setPathParam("/" + mandatoryParam);
        setSetup();
        return this;
    }
}
