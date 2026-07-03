package org.gitgud.model.repository;

import java.util.Optional;

import org.gitgud.events.repository.RepositoryChangedListener;
import org.gitgud.events.repository.RepositoryUpdatedListener;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.Pair;

/**
 *
 */
public interface RepositoryModel {

    /**
     * Register a listener that has to be notified when the repository change.
     *
     * @param rcl
     *            the object that has to be notified
     */
    void addRepositoryChangedListener(RepositoryChangedListener rcl);

    /**
     * @param rul
     *            the object that has to be notified
     */
    void addRepositoryUpdatedListener(RepositoryUpdatedListener rul);

    /**
     * @param cloneParam
     *            the clone param
     * @return the command status of the operation
     */
    CommandStatus cloneRepository(CloneParam cloneParam);

    /**
     *
     */
    void closeRepository();

    /**
     * @return the auto fetch interval
     */
    int getAutoFetchInterval();

    /**
     * @return the repository error
     */
    RepositoryError getError();

    /**
     * @return the global identity
     */
    Optional<Pair<String, String>> getGlobalIdentity();

    /**
     * @return the repository name
     */
    String getName();

    /**
     * @return true if there is a repository opened
     */
    boolean hasRepositoryOpened();

    /**
     * @param initParam
     *            the init param
     * @return the command status of the operation
     */
    CommandStatus initRepository(InitParam initParam);

    /**
     * @param openParam
     *            the open param
     * @return the command status of the operation
     */
    CommandStatus openRepository(OpenParam openParam);

    /**
     *
     */
    void sendManualRepositoryUpdate();

    /**
     * @param interval
     *            the auto fetch interval
     * @return the command status of the operation
     */
    CommandStatus setAutoFetchInterval(int interval);

    /**
     * @param username
     *            the username
     * @param password
     *            the password
     * @return the command status of the operation
     */
    CommandStatus setCredentials(String username, String password);

    /**
     * @param name
     *            the name
     * @param email
     *            the email
     * @return the command status of the operation
     */
    CommandStatus setGlobalIdentiry(String name, String email);

}
