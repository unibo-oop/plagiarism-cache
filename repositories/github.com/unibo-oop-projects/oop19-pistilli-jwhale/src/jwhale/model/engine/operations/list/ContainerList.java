package jwhale.model.engine.operations.list;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * It represents list container operation configurator. 
 */
public final class ContainerList extends AbstractListOpImpl<ContainerList> {
    private static final String ALL_PARAM = "all";
    private static final String NOT_RUNNING = "1";
    private static final String LAST = "/json";

    public ContainerList() {
        super(Method.GET, EndPoint.CONTAINER);
    }

    @Override
    public ContainerList baseSetup() {
        //Set default query parameters for this operation
        getOperation().setQueryParams(ALL_PARAM, NOT_RUNNING);
        getOperation().setLastParam(LAST);
        setSetup();
        return this;
    }


}
