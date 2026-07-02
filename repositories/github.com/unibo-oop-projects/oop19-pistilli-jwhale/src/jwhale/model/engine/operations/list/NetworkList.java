package jwhale.model.engine.operations.list;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * It represents network list operation configurator. 
 */
public final class NetworkList extends AbstractListOpImpl<NetworkList> {
    private static final String EMPTY = "";

    public NetworkList() {
        super(Method.GET, EndPoint.NETWORK);
    }
    @Override
    public NetworkList baseSetup() {
        getOperation().setLastParam(EMPTY);
        setSetup();
        return this;
    }
}
