package jwhale.model.engine.operations.object;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * Volume remove operation configurator.
 */
public final class VolumeRemove extends AbstractObjectOpImpl<VolumeRemove> {
    public VolumeRemove() {
        super(Method.DELETE, EndPoint.VOLUME);
    }
    @Override
    public VolumeRemove target(final String mandatoryParam) {
        getOperation().setPathParam("/" + mandatoryParam);
        setSetup();
        return this;
    }
}
