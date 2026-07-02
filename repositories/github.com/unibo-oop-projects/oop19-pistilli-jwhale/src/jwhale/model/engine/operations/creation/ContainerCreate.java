package jwhale.model.engine.operations.creation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * Container creation operation configurator.
 */
public final class ContainerCreate extends AbstractCreationalOpImpl<ContainerCreate> {
    private static final String LAST = "/create";
    private static final String NAME = "name";
    private static final String EXT_STORAGE = "volume";
    private final JsonObject body = new JsonObject();
    private final JsonObject exposedPorts = new JsonObject();
    private final JsonObject hostConfig = new JsonObject();
    private String imageName;

    public ContainerCreate() {
        super(Method.POST, EndPoint.CONTAINER);
    }

    @Override
    public ContainerCreate create(final String mandatoryParam) {
        imageName = mandatoryParam;
        init();
        setSetup();
        return this;
    }
    /**
     * Sets container custom name. If not used, Docker daemon will
     * assign a random name.
     * @param name Container name.
     * @return itself
     */
    public ContainerCreate setName(final String name) {
        getOperation().setQueryParams(NAME, name);
        return this;
    }
    /**
     * Exposes local port, mapping it to one of the host.
     * @param localPort Container port
     * @param hostPort Host port
     * @param protocol Network protocol (TCP/UDP).
     * @return itself
     */
    public ContainerCreate exposePort(final String localPort, final String hostPort, final String protocol) {
        body.get(CreationParam.EXP.getParam()).getAsJsonObject()
            .add(localPort, new JsonObject());
        this.getBindSection().getAsJsonObject()
            .add(localPort + "/" + protocol, getPortMap(hostPort));
        updateBody();
        return this;
    }
    /**
     * Sets Docker network mode. If not used, it will be "bridge".
     * @param network
     * @return itself
     */
    public ContainerCreate setNetwork(final String network) {
        body.get(CreationParam.HOST_CONF.getParam())
            .getAsJsonObject()
            .addProperty(CreationParam.NET_MODE.getParam(), network);
        updateBody();
        return this;
    }
    /**
     * Sets a volume.
     * @param volume Volume's name
     * @param target Container's path.
     * @return itself
     */
    public ContainerCreate setVolume(final String target, final String volume) {
        body.get(CreationParam.HOST_CONF.getParam())
            .getAsJsonObject()
            .add(CreationParam.VOLUMES.getParam(), getMountArray(target, volume));
        updateBody();
        return this;
    }
    private void init() {
        getOperation().setLastParam(LAST);
        body.addProperty("Image", imageName);
        body.add(CreationParam.EXP.getParam(), exposedPorts);
        body.add(CreationParam.HOST_CONF.getParam(), hostConfig);
        body.get(CreationParam.HOST_CONF.getParam())
            .getAsJsonObject()
            .add(CreationParam.PORT_BIND.getParam(), new JsonObject());
        updateBody();
    }

    private JsonElement getBindSection() {
        return body.get(CreationParam.HOST_CONF.getParam())
                .getAsJsonObject().get(CreationParam.PORT_BIND.getParam());
    }
    private JsonArray getPortMap(final String hostPort) {
        final JsonArray out = new JsonArray();
        final JsonObject obj = new JsonObject();
        obj.addProperty(CreationParam.HOST_PORT.getParam(), hostPort);
        out.add(obj);
        return out;
    }
    private JsonArray getMountArray(final String target, final String volume) {
        final JsonArray out = new JsonArray();
        final JsonObject obj = new JsonObject();
        obj.addProperty(CreationParam.VOL_TARGET.getParam(), target);
        obj.addProperty(CreationParam.VOL_SOURCE.getParam(), volume);
        obj.addProperty(CreationParam.VOL_TYPE.getParam(), EXT_STORAGE);
        out.add(obj);
        return out;
    }
    private void updateBody() {
        getOperation().setBodyRequest(body.toString());
    }

}
