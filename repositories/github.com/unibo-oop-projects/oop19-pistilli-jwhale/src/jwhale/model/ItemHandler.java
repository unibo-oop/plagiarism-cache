package jwhale.model;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

import jwhale.commons.ItemType;
import jwhale.model.connector.ConnectionException;
import jwhale.model.connector.Connector;
import jwhale.model.connector.DaemonResponseException;
import jwhale.model.engine.operations.creation.ImageBuild;
import jwhale.model.engine.operations.creation.ImagePull;
import jwhale.model.engine.operations.creation.NetworkCreate;
import jwhale.model.engine.operations.creation.VolumeCreate;

public class ItemHandler {

    private static final int OP_CODE = 204;
    private static final int DEF_CREATION = 200;
    private static final int ALT_CREATION = 201;
    private static final String NETWORK = "Network";
    private static final String IMAGE = "Image";
    private final Connector connector;
    private final ResponseValidator v = new ResponseValidator();
    private final ItemFactory factory = new ItemFactoryImpl();
    private final Set<Item> networks = new HashSet<>();
    private final Set<Item> images = new HashSet<>();
    private final Set<Item> volumes = new HashSet<>();
    private String defMountPoint;

    public ItemHandler(final Connector connector) {
        this.connector = connector;
    }

    public final Set<Item> getSet(final ItemType type) {
        return type.toString().equals(NETWORK) ? networks 
                : (type.toString().equals(IMAGE) ? images : volumes); 
    }

    public final void removeOperation(final String itemName, final ItemType t) throws ConnectionException, 
    DaemonResponseException {
        final Set<Item> set = t.toString().equals(NETWORK) ? networks 
                : (t.toString().equals(IMAGE) ? images : volumes);
        final Item target = EnvUtils.searchItem(itemName, set);
        v.setStatusCode(t.toString().equals(IMAGE) ? DEF_CREATION : OP_CODE);
        connector.sendRequest(target.remove().buildCall());
        if (v.validate(connector.getResponse())) {
            set.remove(target);
        } else {
            throw new DaemonResponseException(String.valueOf(connector.getResponse().statusCode()));
        }
    }

    public final void createOperation(final String itemName, final ItemType t) 
            throws ConnectionException, DaemonResponseException {
        final Item item = factory.defaultItemCreate(itemName, t);
        final Set<Item> set = t.toString().equals(NETWORK) ? networks : images;
        if (t.toString().equals(NETWORK)) {
            connector.sendRequest(new NetworkCreate().create(item.getName())
                    .configOperation()
                    .buildCall());
            v.setStatusCode(ALT_CREATION);
        } else {
            v.setStatusCode(DEF_CREATION);
            connector.sendRequest(new ImagePull().create(item.getName())
                    .configOperation()
                    .buildCall());
        }
        itemCreateSupport(connector.getResponse(), set, item);
    }

    public final void createOperation(final String itemName, final String feature, final ItemType type) 
            throws ConnectionException, DaemonResponseException {
        final Item defaultItem = factory.specificItemCreate(itemName, feature, type);
        final Set<Item> itemSet = getSet(type);
        if (defaultItem instanceof Network) {
            v.setStatusCode(ALT_CREATION);
            connector.sendRequest(new NetworkCreate().create(defaultItem.getName())
                    .setDriver(defaultItem.getFeature())
                    .configOperation()
                    .buildCall());
        } else if (defaultItem instanceof Volume) {
            v.setStatusCode(ALT_CREATION);
            connector.sendRequest(new VolumeCreate().create(defaultItem.getName())
                    .configOperation()
                    .buildCall());
        } else {
            v.setStatusCode(DEF_CREATION);
            connector.sendRequest(new ImagePull().create(defaultItem.getName())
                    .setVersion(defaultItem.getFeature())
                    .configOperation()
                    .buildCall());
        }
        itemCreateSupport(connector.getResponse(), itemSet, defaultItem);
    }

    public final void imageBuild(final String tag, final String archivePath) throws ConnectionException, 
    IOException, DaemonResponseException {
        v.setStatusCode(DEF_CREATION);
        connector.sendRequest(new ImageBuild().create(tag).getArchive(archivePath)
                .configOperation().buildCall());
        if (v.validate(connector.getResponse())) {
            images.add(factory.defaultItemCreate(tag, ItemType.IMAGE));
        } else {
            throw new DaemonResponseException(String.valueOf(connector.getResponse().statusCode()));
        }
    }
    /**
     * 
     * @param def
     */
    public final void setDefMountPoint(final String def) {
        this.defMountPoint = def;
    }

    public final String getMountPointPath(final String name) {
        return defMountPoint.replace("/data/", "/" + name + "/");
    }

    private void itemCreateSupport(final HttpResponse<String> res, final Set<Item> set, final Item item) 
            throws ConnectionException, DaemonResponseException {
        if (v.validate(res)) {
            set.add(item);
        } else {
            throw new DaemonResponseException(String.valueOf(res.statusCode()));
        }
    }

}
