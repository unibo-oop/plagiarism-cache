package jwhale.model.engine.operations.list;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * It represents volume list operation configurator. 
 */
public final class VolumeList extends AbstractListOpImpl<VolumeList> {
    private static final String LAST = "";
    public VolumeList() {
        super(Method.GET, EndPoint.VOLUME);
    }
    @Override
    public VolumeList baseSetup() {
        getOperation().setLastParam(LAST);
        setSetup();
        return this;
    }

}
