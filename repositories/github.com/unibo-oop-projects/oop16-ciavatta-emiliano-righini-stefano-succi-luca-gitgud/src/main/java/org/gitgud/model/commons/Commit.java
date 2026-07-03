package org.gitgud.model.commons;

import java.util.Date;
import java.util.List;

/**
 * The high level representation of a git Commit.
 */
public interface Commit {

    /**
     * @return The Author.
     */
    Author getAuthor();

    /**
     * @return THe FullMessage.
     */
    String getFullMessage();

    /**
     * @return The sha ID.
     */
    String getID();

    /**
     * @return The sha IDs of the commit's parents.
     */
    List<String> getParentsId();

    /**
     * @return The short message.
     */
    String getShortMessage();

    /**
     * @return The date of creation of this commit.
     */
    Date getWhen();

    @Override
    String toString();

}
