package org.gitgud.application.repository;

import org.gitgud.application.node.Box;

/**
 * The repository controller relative to the repository box.
 */
public interface RepositoryBoxController extends Box {

    /**
     * @param url
     *            the remote URL
     * @param destination
     *            the destination path
     * @param username
     *            the username to login
     * @param password
     *            the password to login
     */
    void cloneRepository(String url, String destination, String username, String password); // NOPMD

    /**
     * @param menu
     *            the section to open
     */
    void commandOpenBox(String menu);

    /**
     * @param path
     *            the destination path
     * @param gitignore
     *            the gitignore template
     * @param license
     *            the license type
     */
    void initRepository(String path, String gitignore, String license);

    /**
     * @param sourceKey
     *            the source caller key
     */
    void openDirectoryChooser(String sourceKey);

    /**
     * @param repository
     *            the repository path
     */
    void openRepository(String repository);

    /**
     * @param sourceKey
     *            the source caller key
     */
    void pasteFromClipboard(String sourceKey);

    /**
     * @param isWaiting
     *            true to set the waiting state
     */
    void setWaitingState(boolean isWaiting);

}
