package org.gitgud.model.tag;

import java.util.Arrays;
import java.util.Optional;

import org.gitgud.utils.Log;

/**
 * All possible tag errors that may occur in the tag model.
 */
public enum TagError {

    /**
     *
     */
    UNKNOWN_ERROR("tag.unknown", "tag.unknown");

    private String jgitMessage;
    private String errorKey;

    TagError(final String jgitMessage, final String stringKey) {
        this.jgitMessage = jgitMessage;
        errorKey = stringKey;
    }

    /**
     * @param jgitMessage
     *            the message of the jgit library exception
     * @return the associated tag error
     */
    public static TagError getByJgitMessage(final String jgitMessage) {
        final Optional<TagError> search = Arrays.stream(TagError.values())
                .filter(re -> jgitMessage.contains(re.jgitMessage))
                .findAny();
        if (search.isPresent()) {
            return search.get();
        } else {
            Log.getLogger().warning("Unknown exception in TagError: " + jgitMessage);
            return UNKNOWN_ERROR;
        }
    }

    /**
     * @return the resource bundle key of the error
     */
    public String getErrorKey() {
        return errorKey;
    }

}
