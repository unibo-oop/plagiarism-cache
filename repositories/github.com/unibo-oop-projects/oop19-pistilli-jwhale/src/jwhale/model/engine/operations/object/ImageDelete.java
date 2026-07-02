package jwhale.model.engine.operations.object;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * It represents list image operation configurator. 
 */
public final class ImageDelete extends AbstractObjectOpImpl<ImageDelete> {

    public ImageDelete() {
        super(Method.DELETE, EndPoint.IMAGE);
    }

    @Override
    public ImageDelete target(final String mandatoryParam) {
        getOperation().setLastParam(mandatoryParam);
        setSetup();
        return this;
    }
}
