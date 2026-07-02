package jwhale.model;

import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jwhale.commons.ItemType;

/**
 * Utility class for environment pulling operation.
 */
public final class EnvUtils {
    private static final String DEF_NET = "bridge";
    private static final String DEF_VOLUME = "data";
    private static ItemFactory factory = new ItemFactoryImpl();

    private EnvUtils() {
    }
    /**
     * Tracks all remote images.
     * @param imageResponse
     *          image's data recovered.
     * @param images
     *          data set to store images.
     */
    public static void trackImages(final String imageResponse, final Set<Item> images) {
        final JsonArray jsonArray = new Gson().fromJson(imageResponse, JsonArray.class);
        images.clear();
        jsonArray.forEach(e -> {
            final String name = e.getAsJsonObject().get("RepoTags")
                    .getAsJsonArray()
                    .get(0)
                    .getAsString();
            final String[] out = name.split(":");
            images.add(factory.specificItemCreate(out[0], out[1], ItemType.IMAGE));
        });
    }
    /**
     * Tracks all remote volumes.
     * @param volumeResponse
     *          volume's data recovered.
     * @param volumes
     *          data set to store volumes.
     */
    public static void trackVolumes(final String volumeResponse, final Set<Item> volumes) {
        final JsonObject jsonObject = new Gson().fromJson(volumeResponse, JsonObject.class);
        volumes.clear();
        jsonObject.get("Volumes").getAsJsonArray().forEach(e -> {
            volumes.add(factory.specificItemCreate(e.getAsJsonObject().get("Name").getAsString(), 
                    e.getAsJsonObject().get("Mountpoint").getAsString(), ItemType.VOLUME));
        });
    }
    /**
     * Tracks all remote containers.
     * @param containerResponse
     *          containers's data recovered.
     * @param containers
     *          data set to store containers.
     * @param images
     *          data set with images stored.
     * @param volumes
     *          data set with volumes stored.
     * @param networks
     *          data set with networks stored.
     */
    public static void trackContainers(final String containerResponse, final Set<Container> containers,
            final Set<Item> networks, final Set<Item> images, final Set<Item> volumes) {
        final JsonArray jsonArray = new Gson().fromJson(containerResponse, JsonArray.class);
        jsonArray.forEach(e -> {
            containers.add(new Container.Builder(networks, volumes, images)
                    .setName(nameParser(e))
                    .setStatus(statusParser(e))
                    .setCreationTime(creationTimeParser(e))
                    .setImage(imageNameParser(e))
                    .setNetwork(networkParser(e))
                    .setVolume(volumeParser(e))
                    .build());
        });
    }

    public static void trackNetworks(final String networkResponse, final Set<Item> networks) {
        final JsonArray jsonArray = new Gson().fromJson(networkResponse, JsonArray.class);
        jsonArray.forEach(e -> {
            final JsonObject obj = e.getAsJsonObject();
            networks.add(factory
                    .specificItemCreate(obj.getAsJsonObject().get("Name").getAsString(), 
                            obj.getAsJsonObject().get("Driver").getAsString(), ItemType.NETWORK));
        });
    }

    @SuppressWarnings("unchecked")
    public static <T extends Item> T searchItem(final String name, final Set<Item> set) {
        return (T) set.stream().filter(e -> e.getName().equals(name)).findFirst().get();
    }

    private static String nameParser(final JsonElement element)  {
        return element.getAsJsonObject().get("Names").getAsJsonArray().get(0).getAsString(); 
    }

    private static String imageNameParser(final JsonElement element)  {
        return element.getAsJsonObject().get("Image").getAsString();
    }

    private static String creationTimeParser(final JsonElement element) {
        return element.getAsJsonObject().get("Created").getAsString();
    }

    private static String statusParser(final JsonElement element) {
        return element.getAsJsonObject().get("Status").getAsString().split(" ")[0];
    }

    private static String networkParser(final JsonElement element) {
        String out = element.getAsJsonObject().get("HostConfig").getAsJsonObject()
                .get("NetworkMode").getAsString();
        if (out.equals("default")) {
            out = DEF_NET;
        }
        return out;
    }
    private static String volumeParser(final JsonElement element) {
        final JsonArray j = element.getAsJsonObject().get("Mounts").getAsJsonArray();
        if (j.size() != 0) {
            return j.get(0).getAsJsonObject().get("Name").getAsString();
        } else {
            return DEF_VOLUME;
        }
    }
}
