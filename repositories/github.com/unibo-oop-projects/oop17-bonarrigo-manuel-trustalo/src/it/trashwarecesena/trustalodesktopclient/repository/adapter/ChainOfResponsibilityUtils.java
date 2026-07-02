package it.trashwarecesena.trustalodesktopclient.repository.adapter;

import java.util.Optional;

/**
 * The basic utility abilities of a chain of a responsibility, such as adding or
 * deleting a new link.
 * 
 *
 * @param <X>
 *            the main type of the chain of responsibility
 * @param <Y>
 *            the type X is identified by.
 * @author Manuel Bonarrigo
 */
public interface ChainOfResponsibilityUtils<X, Y> {
    /**
     * Tells if the PersistenceAdapter is the last one in the chain of
     * responsibility.
     * 
     * @return a boolean representing such state
     */
    boolean isLastLink();

    /**
     * Creates a new link in the chain of responsibility.
     * 
     * @param newLink
     *            the new generic link in the chain
     */
    void dispatchNewLink(X newLink);

    /**
     * Delete a link in the chain of responsibility.
     * 
     * @param deletee
     *            an object used for identifying the link to be deleted
     * @return the {@link Optional} reference to the link next to the one just
     *         deleted.
     */
    Optional<X> deleteLink(Y deletee);

}
