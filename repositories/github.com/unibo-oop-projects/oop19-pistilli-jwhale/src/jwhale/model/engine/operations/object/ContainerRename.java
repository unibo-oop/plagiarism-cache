package jwhale.model.engine.operations.object;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * It represents start container operation configurator. 
 */
public final class ContainerRename extends AbstractObjectOpImpl<ContainerRename> {
    private static final String RENAME = "/rename";
    private static final String DEF_NAME = "";
    private static final String NAME = "name";

    public ContainerRename() {
        super(Method.POST, EndPoint.CONTAINER);
    }
    /**
     * Sets a new name for selected container.
     * @param newName new name.
     * @return itself
     */
    public ContainerRename setNewName(final String newName) {
        getOperation().setQueryParams(NAME, newName);
        setSetup();
        return this;
    }
    @Override
    public ContainerRename target(final String mandatoryParam) {
        getOperation().setQueryParams(NAME, DEF_NAME);
        getOperation().setLastParam(RENAME);
        getOperation().setPathParam("/" + mandatoryParam);
        return this;
    }
}
