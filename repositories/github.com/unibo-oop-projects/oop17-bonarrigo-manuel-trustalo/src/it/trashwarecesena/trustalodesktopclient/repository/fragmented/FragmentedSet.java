package it.trashwarecesena.trustalodesktopclient.repository.fragmented;

import java.util.Set;

/**
 * A Typesafe Heterogeneous Container of {@link Set} designed to prevent the
 * erasure system from deleting the critical information of what kind of Set has
 * been instantiated, making the client able to retrieve such an information
 * during run-time.
 * 
 * @author Manuel Bonarrigo
 */
public interface FragmentedSet {

    /**
     * Retrieve a {@link Set} fully identifiable by its compile-time type.
     * 
     * @param <X>
     *            The dynamic type of the Set
     * @return A dynamically dispatchable Set
     * @throws ClassCastException
     *             if the implementation fails at ensuring the validity of the
     *             dynamic casts made while reconstructing the original Set
     */
    <X> Set<X> getUnerasedSet() throws ClassCastException;

    /**
     * Retrieve the run-time type-token of the {@link Set} instantiated.
     * 
     * @param <X>
     *            The dynamic type of the Class
     * @return A {@link Class} representing the run-time type of the inner Set
     * 
     */
    <X> Class<X> getBoundedClass();

}
