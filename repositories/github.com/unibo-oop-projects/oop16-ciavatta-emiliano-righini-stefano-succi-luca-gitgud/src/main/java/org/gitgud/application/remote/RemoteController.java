package org.gitgud.application.remote;

/**
 * The remote controller relative to the remote operations.
 */
public interface RemoteController {

    /**
     * Add a new remote.
     */
    void addRemote();

    /**
     * Authenticate the user.
     *
     * @param wrongCredentials
     *            true to advise the user that last credentials used are wrong
     */
    void authenticate(boolean wrongCredentials);

    /**
     * Authenticate the user.
     *
     * @param operation
     *            the name of the operation that should be done after authentication
     * @param afterAuthentication
     *            what should be done after authentication
     * @param wrongCredentials
     *            to advise the user that last credentials used are wrong
     */
    void authenticate(String operation, Runnable afterAuthentication, boolean wrongCredentials);

    /**
     * Edit an existing remote.
     *
     * @param remoteName
     *            the existing remote name
     */
    void editRemote(String remoteName);

    /**
     * Fetch a remote.
     *
     * @param remoteName
     *            the remote name to fetch
     */
    void fetchRemote(String remoteName);

    /**
     * Pull the HEAD.
     */
    void pull();

    /**
     * Push the HEAD.
     */
    void push();

    /**
     * Remove an existing remote.
     *
     * @param remoteName
     *            the existing remote name
     */
    void removeRemote(String remoteName);

    /**
     * @param branchName
     *            the branch name
     */
    void setUpstream(String branchName);

}
