package org.gitgud.model.branch;

import java.util.Arrays;
import java.util.Optional;

import org.gitgud.utils.Log;

/**
 * This enum should catalog all error types regarding org.gitgud.core.branches package.
 */
public enum BranchError {
    /**
     *
     */
    BRANCH_ALREADY_EXISTS("already exists", "branch.remote.exists"),
    /**
     *
     */
    BRANCH_IN_USE("The branch you want to delete is checked out right now", "branch.delete.impossible"),
    /**
     *
     */
    BRANCH_NAME_IN_USE("Already exists a branch with this name", "branch.create.exists"),
    /**
     *
     */
    BRANCH_NOT_FOUND("The branch was not found", "branch.rename.not_found"),
    /**
     *
     */
    BRANCH_NOT_MERGED("One or more of the branches you want to delete, have not been merged into the currently checked out branch", "branch.delete.not_merged"),
    /**
     *
     */
    NO_ERROR("There is no error registered.", "branch.status.no_error"),
    /**
     *
     */
    UNKNOWN_ERROR("unkown api error occurred", "branch.api.unkown");

    private final String code;

    private final String message;

    /**
     * @param jgitMessage
     *            the error message from the Api
     * @return The message listed in the enum that corresponds to the jgit exception message.
     */
    public static BranchError getByJgitMessage(final String jgitMessage) {
        final Optional<BranchError> search = Arrays.stream(BranchError.values())
                .filter(er -> jgitMessage.contains(er.message))
                .findAny();
        if (search.isPresent()) {
            return search.get();
        } else {
            Log.getLogger().warning("Unknown exception in RemoteError: " + jgitMessage);
            return UNKNOWN_ERROR;
        }
    }

    BranchError(final String message, final String code) {
        this.message = message;
        this.code = code;
    }

    /**
     * @return the error code
     */
    public String getErrorCode() {
        return code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}
