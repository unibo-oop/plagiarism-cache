package it.trashwarecesena.trustalodesktopclient.repository.crud;

import java.util.Set;

/**
 * Simple interface to be implemented by {@link RequestDispatcher} subclasses
 * who want to express their functionality through a chain of responsibility.
 *
 * @author Manuel Bonarrigo
 */
public interface RequestHandler extends RequestDispatcher {

    /**
     * States if the implementor is able of handling the type of the requests that
     * are being dispatched between {@link RequestDispatcher} implementors.
     * 
     * @param request
     *            The type-token of the request
     * @return a boolean representing the ability to handle or not the request
     */
    boolean canHandleRequest(Class<?> request);

    /**
     * A getter for debug purposes.
     * 
     * @return a set of Classes
     */
    Set<Class<?>> getHandlersSet();

}
