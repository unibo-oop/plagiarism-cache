package org.gitgud.model.stage;

import java.util.Optional;

import org.gitgud.model.utils.Builder;
import org.gitgud.utils.Pair;

/**
 * build a new commit parameter.
 */
public interface CommitParamBuilder extends Builder<CommitParam> {

    /**
     * set the message of the commit.
     *
     * @param message
     *            message of the commit
     * @return this
     */
    CommitParamBuilder message(String message);

    /**
     * set if is an amend operation.
     *
     * @param isAmend
     *            true if is an amend operation
     * @return this
     */
    CommitParamBuilder amend(boolean isAmend);

    /**
     * set the author of the commit, if not set take the default from the repository.
     *
     * @param name
     *            author's name
     * @param email
     *            author's email
     * @return this
     */
    CommitParamBuilder author(String name, String email);

    /**
     * set the committer of the commit, if not set take the default from the repository.
     *
     * @param name
     *            committer's name
     * @param email
     *            committer's email
     * @return this
     */
    CommitParamBuilder committer(String name, String email);

    /**
     * set the path of the file/directory to commit.
     *
     * @param path
     *            the path of the file/directory to commit
     * @return this
     */
    CommitParamBuilder only(String path);

    /**
     * if set true, commit stages files that have been modified and deleted, but not newer files not in the repository.
     *
     * @param isAll
     *            if true, commit stages files that have been modified and deleted, but not newer files not in the
     *            repository.
     * @return this
     */
    CommitParamBuilder all(boolean isAll);

    /**
     * @return a new CommitParamBuilder
     */
    static CommitParamBuilder createCommitParamBuilder() {
        return new CommitParamBuilder() {

            private String message; // NOPMD: this field should not be capitalized
            private boolean amend = false; // NOPMD
            private boolean all = false; // NOPMD
            private Optional<Pair<String, String>> author = Optional.empty(); // NOPMD
            private Optional<Pair<String, String>> committer = Optional.empty(); // NOPMD
            private Optional<String> only = Optional.empty(); // NOPMD

            @Override
            public CommitParam build() {
                return new CommitParam() {

                    @Override
                    public boolean isAmend() {
                        return amend;
                    }

                    @Override
                    public Optional<Pair<String, String>> getAuthor() {
                        return author;
                    }

                    @Override
                    public Optional<Pair<String, String>> getCommitter() {
                        return committer;
                    }

                    @Override
                    public String getMessage() {
                        return message;
                    }

                    @Override
                    public Optional<String> getOnly() {
                        return only;
                    }

                    @Override
                    public boolean isAll() {
                        return all;
                    }
                };
            }

            @Override
            public CommitParamBuilder amend(final boolean isAmend) {
                this.amend = isAmend;
                return this;
            }

            @Override
            public CommitParamBuilder author(final String name, final String email) {
                this.author = Optional.of(new Pair<>(name, email));
                return this;
            }

            @Override
            public CommitParamBuilder committer(final String name, final String email) {
                this.committer = Optional.of(new Pair<>(name, email));
                return this;
            }

            @Override
            public CommitParamBuilder only(final String path) {
                this.only = Optional.of(path);
                return this;
            }

            @Override
            public CommitParamBuilder all(final boolean isAll) {
                this.all = isAll;
                return this;
            }

            @Override
            public CommitParamBuilder message(final String message) {
                this.message = message;
                return this;
            }
        };
    }
}
