package org.gitgud.model.stage;

import java.util.Optional;
import java.util.Set;

import org.gitgud.model.utils.Builder;

/**
 *
 */
public interface CleanParamBuilder extends Builder<CleanParam> {

    /**
     * if set, only these paths are affected by the cleaning, if empty remove all untracked files.
     *
     * @param paths
     *            the path to clean
     * @return this
     */
    CleanParamBuilder paths(Set<String> paths);

    /**
     * if true, in addition to files, also clean directories.
     *
     * @param isDirs
     *            if true, in addition to files, also clean directories
     * @return this
     */
    CleanParamBuilder dirs(boolean isDirs);

    /**
     * if true, directories that are git repositories will also be deleted.
     *
     * @param isForce
     *            if true, directories that are git repositories will also be deleted
     * @return this
     */
    CleanParamBuilder force(boolean isForce);

    /**
     * if true, don't report/clean files/directories that are ignored by a .gitignore.
     *
     * @param isIgnore
     *            if true, don't report/clean files/directories that are ignored by a .gitignore
     * @return this
     */
    CleanParamBuilder ignore(boolean isIgnore);

    /**
     * @return a new CleanParamBuilder
     */
    static CleanParamBuilder createCleanParamBuilder() {
        return new CleanParamBuilder() {

            private Optional<Set<String>> paths = Optional.empty(); // NOPMD: this field should not be capitalized
            private boolean dirs = false; // NOPMD
            private boolean force = false; // NOPMD
            private boolean ignore = false; // NOPMD

            @Override
            public CleanParamBuilder paths(final Set<String> paths) {
                this.paths = Optional.of(paths);
                return this;
            }

            @Override
            public CleanParamBuilder dirs(final boolean isDirs) {
                this.dirs = isDirs;
                return this;
            }

            @Override
            public CleanParamBuilder force(final boolean isForce) {
                this.force = isForce;
                return this;
            }

            @Override
            public CleanParamBuilder ignore(final boolean isIgnore) {
                this.ignore = isIgnore;
                return this;
            }

            @Override
            public CleanParam build() {
                return new CleanParam() {

                    @Override
                    public Optional<Set<String>> getPaths() {
                        return paths;
                    }

                    @Override
                    public boolean isDirs() {
                        return dirs;
                    }

                    @Override
                    public boolean isForce() {
                        return force;
                    }

                    @Override
                    public boolean isIgnore() {
                        return ignore;
                    }
                };
            }
        };
    }
}
