package jwhale.model.engine.operations.creation;

import com.google.gson.JsonObject;
import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * It represents volume list operation configurator. 
 */
public final class VolumeCreate extends AbstractCreationalOpImpl<VolumeCreate> {
    private static final String LAST = "/create";
    private static final String NAME = "Name";
    private final JsonObject bodyRequest = new JsonObject();

    public VolumeCreate() {
        super(Method.POST, EndPoint.VOLUME);
    }
    @Override
    public VolumeCreate create(final String mandatoryParam) {
        getOperation().setLastParam(LAST);
        bodyRequest.addProperty(NAME, mandatoryParam);
        setSetup();
        getOperation().setBodyRequest(bodyRequest.toString());
        return this;
    }
}
