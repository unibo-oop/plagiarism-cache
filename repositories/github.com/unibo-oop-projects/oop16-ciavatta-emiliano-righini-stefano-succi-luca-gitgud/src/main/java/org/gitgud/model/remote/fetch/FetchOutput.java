package org.gitgud.model.remote.fetch;

/**
 *
 *
 */
public interface FetchOutput {

    /**
     * @return the name used within this local repository
     */
    String getLocalName();

    /**
     * @return the new commit hash
     */
    String getNewCommitHash();

    /**
     * @return the old commit hash
     */
    String getOldCommitHash();

    /**
     * @return the name used within the remote repository
     */
    String getRemoteName();

}
