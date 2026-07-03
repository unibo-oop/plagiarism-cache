package org.gitgud.model.remote;

import java.util.Map;

import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.Pair;

/**
 *
 */
public interface RemoteOperation {

    /**
     * @param name
     *            the name of the remote to add
     * @param url
     *            the url of the remote to add
     * @return the command status of the operation
     */
    CommandStatus addRemote(String name, String url);

    /**
     * @param name
     *            the name of the remote to edit
     * @param newUrl
     *            the new url of the remote to edit
     * @return the command status of the operation
     */
    CommandStatus editRemote(String name, String newUrl);

    /**
     * @return a map with the ahead and behind status of all local branches
     */
    Map<String, Pair<Integer, Integer>> getAheadBehindStatus();

    /**
     * @return the remote error
     */
    RemoteError getError();

    /**
     * @return a map of all remotes
     */
    Map<String, Pair<String, String>> getRemotes();

    /**
     * @param name
     *            the remote to remove
     * @return the command status of the operation
     */
    CommandStatus removeRemote(String name);

    /**
     * @param branch
     *            the branch to set upstream
     * @param remoteName
     *            the remote name
     * @param remoteBranch
     *            the branch name
     * @return the command status of the operation
     */
    CommandStatus setUpstream(String branch, String remoteName, String remoteBranch);

}
