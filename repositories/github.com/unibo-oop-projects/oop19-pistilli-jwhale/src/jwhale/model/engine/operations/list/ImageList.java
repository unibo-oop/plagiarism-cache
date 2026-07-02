package jwhale.model.engine.operations.list;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * It represents list image operation configurator. 
 */
public final class ImageList extends AbstractListOpImpl<ImageList> {
    private static final String LAST = "/json";

    public ImageList() {
        super(Method.GET, EndPoint.IMAGE);
    }
    @Override
    public ImageList baseSetup() {
        getOperation().setLastParam(LAST);
        getOperation().setQueryParams("all", "1");
        setSetup();
        return this;
    }
}
