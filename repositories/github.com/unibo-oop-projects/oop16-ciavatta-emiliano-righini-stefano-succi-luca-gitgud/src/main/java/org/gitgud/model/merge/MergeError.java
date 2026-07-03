package org.gitgud.model.merge;

import java.util.Arrays;
import java.util.Optional;

import org.gitgud.utils.Log;

/**
 * All possible merge errors that may occur in the merge model.
 */
public enum MergeError {

    /**
     *
     */
    UNKNOWN_ERROR("merge.unknown", "merge.unknown");

    private String jgitMessage;
    private String errorKey;

    MergeError(final String jgitMessage, final String stringKey) {
        this.jgitMessage = jgitMessage;
        errorKey = stringKey;
    }

    /**
     * @param jgitMessage
     *            the jgit message
     * @return the merge error
     */
    public static MergeError getByJgitMessage(final String jgitMessage) {
        final Optional<MergeError> search = Arrays.stream(MergeError.values())
                .filter(re -> jgitMessage.contains(re.jgitMessage))
                .findAny();
        if (search.isPresent()) {
            return search.get();
        } else {
            Log.getLogger().warning("Unknown exception in MergeError: " + jgitMessage);
            return UNKNOWN_ERROR;
        }
    }

    /**
     * @return the error key
     */
    public String getErrorKey() {
        return errorKey;
    }

}
