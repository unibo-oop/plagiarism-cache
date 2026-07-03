package org.gitgud.model.remote;

/**
 *
 */
public interface RemoteModel {

    /**
     * @return the remote action model
     */
    RemoteAction getActions();

    /**
     * @return the remote operation model
     */
    RemoteOperation getOperations();

}
