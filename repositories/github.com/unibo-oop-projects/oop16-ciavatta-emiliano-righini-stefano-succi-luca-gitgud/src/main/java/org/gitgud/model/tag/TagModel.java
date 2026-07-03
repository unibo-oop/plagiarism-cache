package org.gitgud.model.tag;

import java.util.List;

import org.gitgud.model.utils.CommandStatus;

/**
 * The model of the git tags.
 */
public interface TagModel {

    /**
     * Add a new tag.
     *
     * @param message
     *            the tag message
     * @param start
     *            the tag ref
     * @return the command status of the operation
     */
    CommandStatus addTag(String message, String start);

    /**
     * @return the command status of the operation
     */
    CommandStatus deleteTag();

    /**
     * @return a list of all tags
     */
    List<String> getTags();

}
