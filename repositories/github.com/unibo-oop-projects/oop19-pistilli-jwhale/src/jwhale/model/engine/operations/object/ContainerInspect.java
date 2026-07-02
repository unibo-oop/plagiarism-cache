package jwhale.model.engine.operations.object;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * Inspect operation instance.
 */
public class ContainerInspect extends AbstractObjectOpImpl<ContainerInspect> {
    private static final String LAST_PARAM = "/json";

    public ContainerInspect() {
        super(Method.GET, EndPoint.CONTAINER);
    }

    @Override
    public final ContainerInspect target(final String mandatoryParam) {
        getOperation().setPathParam("/" + mandatoryParam);
        getOperation().setLastParam(LAST_PARAM);
        setSetup();
        return this;
    }

}
