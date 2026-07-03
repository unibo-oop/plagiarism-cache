package org.gitgud.model.diff;

import java.util.Optional;

import org.gitgud.model.utils.Builder;

/**
 *
 */
public interface DiffParamBuilder extends Builder<DiffParam> {

    /**
     * @return a new DiffParamBuilder
     */
    static DiffParamBuilder createDiffParamBuilder() {
        return new DiffParamBuilder() {

            private boolean diffCommits;                         // NOPMD - these are not static final fields,
                                                                 // they should not be capitalized
            private boolean diffUnstaged;                        // NOPMD
            private boolean diffStaged;                          // NOPMD
            private Optional<String> optNewCommitHash = Optional.empty(); // NOPMD
            private Optional<String> optOldCommitHash = Optional.empty(); // NOPMD

            @Override
            public DiffParam build() {
                if ((diffCommits ? 1 : 0) + (diffUnstaged ? 1 : 0) + (diffStaged ? 1 : 0) != 1) {
                    throw new IllegalStateException("Set only one mode");
                } else if (diffCommits && (!optNewCommitHash.isPresent() || !optOldCommitHash.isPresent())) {
                    throw new IllegalStateException("optNewCommitHash and optOldCommitHash must be setted");
                }

                return new DiffParam() {

                    @Override
                    public Optional<String> getNewCommitHash() {
                        return optNewCommitHash;
                    }

                    @Override
                    public Optional<String> getOldCommitHash() {
                        return optOldCommitHash;
                    }

                    @Override
                    public boolean isDiffCommits() {
                        return diffCommits;
                    }

                    @Override
                    public boolean isDiffStaged() {
                        return diffStaged;
                    }

                    @Override
                    public boolean isDiffUnstaged() {
                        return diffUnstaged;
                    }

                };
            }

            @Override
            public DiffParamBuilder newCommitHash(final String newCommitHash) {
                optNewCommitHash = Optional.ofNullable(newCommitHash);
                diffCommits = true;
                return this;
            }

            @Override
            public DiffParamBuilder oldCommitHash(final String oldCommitHash) {
                optOldCommitHash = Optional.ofNullable(oldCommitHash);
                diffCommits = true;
                return this;
            }

            @Override
            public DiffParamBuilder setDiffStaged() {
                diffStaged = true;
                return this;
            }

            @Override
            public DiffParamBuilder setDiffUnstaged() {
                diffUnstaged = true;
                return this;
            }
        };
    }

    /**
     * @param newCommitHash
     *            - the commit hash of the newer stage to compare
     * @return this
     */
    DiffParamBuilder newCommitHash(String newCommitHash); // OPTIONAL

    /**
     * @param oldCommitHash
     *            - the commit hash of the older stage to compare
     * @return this
     */
    DiffParamBuilder oldCommitHash(String oldCommitHash); // OPTIONAL

    /**
     * Calculate the differences between staged files and head.
     *
     * @return this
     */
    DiffParamBuilder setDiffStaged();

    /**
     * Calculate the differences between unstaged files and staged files.
     *
     * @return this
     */
    DiffParamBuilder setDiffUnstaged();
}
