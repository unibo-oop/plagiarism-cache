package org.gitgud.model.repository;

import java.util.Arrays;
import java.util.Optional;

import org.gitgud.utils.Log;

/**
 * All possible repository errors that may occur in the repository model.
 */
public enum RepositoryError {

    /**
     *
     */
    INVALID_PATH("repository not found", "repository.path.invalid"),
    /**
     *
     */
    PATH_ALREADY_EXISTS("already exists", "repository.path.exists"),
    /**
     *
     */
    INSUFFICIENT_PERMISSIONS("Creating directories for", "repository.permissions.insufficient"),
    /**
     *
     */
    INVALID_REMOTE("Invalid remote", "repository.remote.invalid"),
    /**
     *
     */
    TRANSPORT_ERROR("cannot open git-upload-pack", "repository.transport"),
    /**
     *
     */
    INVALID_ADVERTISEMENT("invalid advertisement", "repository.remote.invalid"),
    /**
     *
     */
    DOWNLOAD_CANCELLED("Download cancelled", "repository.download.cancelled"),
    /**
     *
     */
    AUTHENTICATION_REQUIRED("Authentication is required", "repository.authentication.required"),
    /**
     *
     */
    WRONG_CREDENTIALS("not authorized", "repository.wrong.credentials"),
    /**
     *
     */
    UNKNOWN_ERROR("Unknown error", "repository.unknown");

    private String jgitMessage;
    private String errorKey;

    RepositoryError(final String jgitMessage, final String stringKey) {
        this.jgitMessage = jgitMessage;
        errorKey = stringKey;
    }

    /**
     * @param jgitMessage
     *            the jgit message
     * @return the repository error
     */
    public static RepositoryError getByJgitMessage(final String jgitMessage) {
        final Optional<RepositoryError> search = Arrays.stream(RepositoryError.values())
                .filter(re -> jgitMessage.contains(re.jgitMessage))
                .findAny();
        if (search.isPresent()) {
            return search.get();
        } else {
            Log.getLogger().warning("Unknown exception in RepositoryError: " + jgitMessage);
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
