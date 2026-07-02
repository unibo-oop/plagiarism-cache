package jwhale.model.engine.operations.object;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * It represents play back container operation configurator. 
 */
public final class ContainerPlayback extends AbstractObjectOpImpl<ContainerPlayback> {
    public ContainerPlayback() {
        super(Method.POST, EndPoint.CONTAINER);
    }
    /**
     * Define operation type.
     * @param operation
     *          operations type
     * @return
     *          itself
     */
    public ContainerPlayback setOperation(final Playback operation) {
        getOperation().setLastParam(operation.toString());
        setSetup();
        return this;
    }
    @Override
    public ContainerPlayback target(final String mandatoryParam) {
        getOperation().setPathParam("/" + mandatoryParam);
        return this;
    }
}
