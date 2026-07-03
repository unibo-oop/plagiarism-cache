package org.gitgud.model.remote.pull;

import java.util.Optional;

import org.gitgud.events.ProgressListener;
import org.gitgud.model.utils.Builder;

/**
 * A static factory used to create builders to create FetchParam.
 */
public interface PullParamBuilder extends Builder<PullParam> {

    /**
     * @return a new FetchParamBuilder
     */
    static PullParamBuilder createPullParamBuilder() {
        return new PullParamBuilder() {

            private Optional<String> optRemoteName = Optional.empty(); // NOPMD
            private Optional<ProgressListener> optProgressListener = Optional.empty(); // NOPMD
            private boolean reqIsOnlyFastForward; // = false; // NOPMD
            private boolean reqIsAlwaysMerge; // = false; // NOPMD
            private Optional<String> optCommitMessage; // NOPMD
            private String reqRemoteBranch; // NOPMD

            @Override
            public PullParamBuilder alwaysMerge(final boolean isAlwaysMerge) {
                reqIsAlwaysMerge = isAlwaysMerge;
                return this;
            }

            @Override
            public PullParam build() {
                return new PullParam() {

                    @Override
                    public Optional<String> getCommitMessage() {
                        return optCommitMessage;
                    }

                    @Override
                    public Optional<ProgressListener> getProgressListener() {
                        return optProgressListener;
                    }

                    @Override
                    public String getRemoteBranch() {
                        return reqRemoteBranch;
                    }

                    @Override
                    public Optional<String> getRemoteName() {
                        return optRemoteName;
                    }

                    @Override
                    public boolean isFastForwardOnly() {
                        return reqIsOnlyFastForward;
                    }

                    @Override
                    public boolean isMergeAlways() {
                        return reqIsAlwaysMerge;
                    }
                };
            }

            @Override
            public PullParamBuilder commitMessage(final String commitMessage) {
                optCommitMessage = Optional.ofNullable(commitMessage);
                return this;
            }

            @Override
            public PullParamBuilder onlyFastForward(final boolean isOnlyFastForward) {
                reqIsOnlyFastForward = isOnlyFastForward;
                return this;
            }

            @Override
            public PullParamBuilder progressListener(final ProgressListener progressListener) {
                optProgressListener = Optional.ofNullable(progressListener);
                return this;
            }

            @Override
            public PullParamBuilder remoteBranch(final String remoteBranch) {
                reqRemoteBranch = remoteBranch;
                return this;
            }

            @Override
            public PullParamBuilder remoteName(final String remoteName) {
                optRemoteName = Optional.ofNullable(remoteName);
                return this;
            }
        };
    }

    /**
     * @param isAlwaysMerge
     *            true if a merge commit should always been created, even if unnecessary. Default value is false
     * @return this
     */
    PullParamBuilder alwaysMerge(boolean isAlwaysMerge);

    /**
     * @param commitMessage
     *            the commit message to use for merge commit, if commit merge should be created
     * @return this
     */
    PullParamBuilder commitMessage(String commitMessage);

    /**
     * @param isOnlyFastForward
     *            true if only fast forward merge is accepted. Default value is false
     * @return this
     */
    PullParamBuilder onlyFastForward(boolean isOnlyFastForward);

    /**
     * @param progressListener
     *            the progress listener associated with the pull operation
     * @return this
     */
    PullParamBuilder progressListener(ProgressListener progressListener);

    /**
     * @param remoteBranch
     *            the remote branch which local branch points to
     * @return this
     */
    PullParamBuilder remoteBranch(String remoteBranch);

    /**
     * @param remoteName
     *            the remote name to use in the pull action. If not set, it is used the default remote (origin)
     * @return this
     */
    PullParamBuilder remoteName(String remoteName);

}
