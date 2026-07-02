package jwhale.model.engine.operations.object;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * It represents delete container operation configurator. 
 */
public final class ContainerRemove extends AbstractObjectOpImpl<ContainerRemove> {
    private static final String EMPTY = "";
    public ContainerRemove() {
        super(Method.DELETE, EndPoint.CONTAINER);
    }
    @Override
    public ContainerRemove target(final String mandatoryParam) {
        getOperation().setPathParam("/" + mandatoryParam);
        getOperation().setLastParam(EMPTY);
        setSetup();
        return this;
    }
}
