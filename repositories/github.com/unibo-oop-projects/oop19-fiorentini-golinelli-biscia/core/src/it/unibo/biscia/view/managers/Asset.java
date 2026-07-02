package it.unibo.biscia.view.managers;

/**
 * Interface for generic assets files.
 *
 * @param <T> The Type of the Asset
 */
//TODO: Maybe extract in package with interface
public interface Asset<T> {
    /**
     * @return The logical name of the asset.
     */
    String getName();

    /**
     * @return The physical path of the asset.
     */
    String getPath();

    /**
     * @return The asset's info implemented by its type T.
     */
    T getInfo();
}
