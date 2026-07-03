package org.gitgud.model.branch;

import java.util.Iterator;
import java.util.Optional;

import org.gitgud.model.commons.Commit;

/**
 * The LogManager is used to interrogate the repository history, or simply get the wanted commit.
 */
public interface LogManager {

    /**
     * @param identifier
     *            - a string that identifies a commit
     * @return An Optional with the wanted commit inside.
     */
    Optional<Commit> getCommit(String identifier);

    /**
     * @return An iterator over the Commits
     */
    Iterator<Commit> getLogs();

    /**
     * You must mark a start point before call getLogs().
     *
     * @param identifier
     *            - a string that identifies a commit.
     * @return This LogManager.
     */
    LogManager markStart(String identifier);

    /**
     * Default Max value is 100.
     *
     * @param max
     *            - the max number of commits you want to put out.
     * @return This LogManager.
     */
    LogManager setMaxCount(int max);

}
