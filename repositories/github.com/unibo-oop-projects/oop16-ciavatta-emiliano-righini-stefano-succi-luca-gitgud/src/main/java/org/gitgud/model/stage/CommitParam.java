package org.gitgud.model.stage;

import java.util.Optional;

import org.gitgud.utils.Pair;

/**
 * contains the information of the commit.
 */
public interface CommitParam {

    /**
     * 
     * @return
     *          true if is an amend operation,
     *          false if is a commit operation
     */
    boolean isAmend();

    /**
     * 
     * @return
     *          if present the author of the commit
     */
    Optional<Pair<String, String>> getAuthor();

    /**
     * 
     * @return
     *          if present the committer of the commit
     */
    Optional<Pair<String, String>> getCommitter();

    /**
     * 
     * @return
     *          the message of the commit
     */
    String getMessage();

    /**
     * 
     * @return
     *          if present the specific path to commit
     */
    Optional<String> getOnly();

    /**
     * 
     * @return
     *          if true commit stages files that have been modified and deleted, 
     *                  but not newer files not in the repo
     */
    boolean isAll();
}
