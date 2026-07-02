package it.unibo.biscia.view.managers;

/**
 * Implementation of Asset Interface.
 *
 * @param <T> The type of the asset.
 */
public class AssetImpl<T> implements Asset<T> {

    private final String path;
    private final String name;
    private final T info;

    /**
     * @param path the file's path.
     * @param name the file's logical name (can be different from its real name).
     *             NOTE: many different logical name's and therefore many different
     *             Assets can point to the same physical file. This is made because
     *             there can be for example many different Font Asset with different
     *             names and sizes but made with the same .ttf file.
     * @param info the file's information instance based on his type T.
     */
    // The constructor is protected so nobody except the View can create assets!
    protected AssetImpl(final String path, final String name, final T info) {
        this.path = path;
        // adding the extension to the file's logical name
        this.name = name + path.substring(path.lastIndexOf('.'));
        this.info = info;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getPath() {
        return this.path;
    }

    @Override
    public final T getInfo() {
        return info;
    }

}
