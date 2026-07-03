package org.gitgud.model.remote;

import java.util.Arrays;
import java.util.Optional;

import org.gitgud.utils.Log;

/**
 * All possible remote errors that may occur in the remote model.
 */
public enum RemoteError {

    /**
     *
     */
    AUTHENTICATION_REQUIRED("Authentication is required", "remote.authentication.required"), NOT_AUTHORIZED("not authorized", "remote.not.authorized"), NAME_EMPTY("remote.name.empty", "remote.name.empty"), NAME_ALREADY_EXISTS("remote.name.exists", "remote.name.exists"), NAME_INEXISTENT("remote.name.inexistent", "remote.name.inexistent"), URI_PARSE_ERROR("remote.uri.parse", "remote.uri.parse"), NO_UPSTREAM_SET("remote.no.upstream.set", "remote.no.upstream.set"), UNKNOWN_ERROR("remote.unknown", "remote.unknown");

    private String jgitMessage;
    private String errorKey;

    RemoteError(final String jgitMessage, final String stringKey) {
        this.jgitMessage = jgitMessage;
        errorKey = stringKey;
    }

    /**
     * @param jgitMessage
     *            the jgit message
     * @return the remote error
     */
    public static RemoteError getByJgitMessage(final String jgitMessage) {
        final Optional<RemoteError> search = Arrays.stream(RemoteError.values())
                .filter(re -> jgitMessage.contains(re.jgitMessage)).findAny();
        if (search.isPresent()) {
            return search.get();
        } else {
            Log.getLogger().warning("Unknown exception in RemoteError: " + jgitMessage);
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
