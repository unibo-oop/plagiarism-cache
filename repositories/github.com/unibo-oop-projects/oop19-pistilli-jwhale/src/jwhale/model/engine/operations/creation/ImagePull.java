package jwhale.model.engine.operations.creation;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * Pull image operation configurator.
 */
public final class ImagePull extends AbstractCreationalOpImpl<ImagePull> {
    //Default tag
    private static final String DEFAULT = "latest";
    public ImagePull() {
        super(Method.POST, EndPoint.IMAGE);
    }
    /**
     * Sets a specific image version. If not used, it will be
     * latest version.
     * @param version Imagine's version.
     * @return itself
     */
    public ImagePull setVersion(final String version) {
        getOperation().setQueryParams("tag", version);
        return this;
    }
    @Override
    public ImagePull create(final String mandatoryParam) {
        getOperation().setQueryParams("fromImage", mandatoryParam);
        //Set default query parameters for this operation
        getOperation().setQueryParams("tag", DEFAULT);
        getOperation().setLastParam("/create");
        setSetup();
        return this;
    }

}
