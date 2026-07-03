package org.gitgud.model.stage;

/**
 * Staging area errors.
 */
public enum StageError {

    /**
     *
     */
    GIT_API("stage.api.title", "stage.api.message"),
    /**
     *
     */
    MISSING_MESSAGE("stage.missing_meggase.title", "stage.missing_meggase.message"),
    /**
     *
     */
    MISSING_AUTHOR("stage.missing_author.title", "stage.missing_author.message"),
    /**
     *
     */
    MISSING_HEAD("stage.missing_head.title", "stage.missing_head.message"),
    /**
     *
     */
    CONCURRENT_REF_UPDATE("stage.concurrent_ref_update.title", "stage.concurrent_ref_update.message"),
    /**
     *
     */
    WRONG_REPO("stage.wrong_repo.title", "stage.wrong_repo.message"),
    /**
     *
     */
    MISSING_WORK_TREE("stage.missing_working_tree.title", "stage.missing_working_tree.message"),
    /**
     *
     */
    CHECKOUT_CONFLICT("stage.checkout_conflict.title", "stage.checkout_conflict.message"),
    /**
     *
     */
    MISSING_PATHS("stage.missing_paths.title", "stage.missing_paths.message"),
    /**
     *
     */
    EMPTY_COMMIT("stage.empty_commit.title", "stage.empty_commit.message");

    private String title;
    private String message;

    StageError(final String title, final String message) {
        this.title = title;
        this.message = message;
    }

    /**
     * @return the description of the error
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return the name of the error
     */
    public String getTitle() {
        return this.title;
    }
}
