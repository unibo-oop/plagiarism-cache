package model.data_access;

/**
 * Manages the manipulation of player data.
 * @param <X> is the data type to operate upon
 *
 */
public interface FileDataAccessManager<X> extends DataAccessManager<X> {
    /**
     * @return the file name
     */
    String getFileName();
    /**
     * reload the file.
     */
    void reloadFromDataSet();
}
