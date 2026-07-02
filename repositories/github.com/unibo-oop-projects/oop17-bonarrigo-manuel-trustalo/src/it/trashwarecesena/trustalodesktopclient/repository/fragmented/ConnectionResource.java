package it.trashwarecesena.trustalodesktopclient.repository.fragmented;

/**
 * The generic type holding the resource used to connect to a persistence
 * manager.
 * 
 * @author Manuel Bonarrigo
 *
 * @param <X>
 *            the actual type representing the technology used to pursue the
 *            connection
 */
public interface ConnectionResource<X> {

    /**
     * Obtain the instance of the technology used to pursue the connection to a
     * persistent data manager.
     * 
     * @return X, where it is the type this class has been initialized with
     */
    X getConnectionResource();

    /**
     * Obtain the runtime type of the technology.
     * 
     * @return a Class expressing such a type.
     */
    Class<X> getTechnologyType();

}
