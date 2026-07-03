package org.gitgud.model.stage;

import java.util.Optional;
import java.util.Set;

import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.gitgud.model.utils.Builder;

/**
 *
 */
public interface ResetParamBuilder extends Builder<ResetParam> {

    /**
     * @param paths
     *            the paths to reset
     * @return this
     */
    ResetParamBuilder paths(Set<String> paths);

    /**
     * @param ref
     *            the ref to reset to, defaults to HEAD if not specified
     * @return this
     */
    ResetParamBuilder ref(String ref);

    /**
     * @param mode
     *            the mode of the reset command
     * @return this
     */
    ResetParamBuilder mode(ResetCommand.ResetType mode);

    /**
     * @param disableRefLog
     *            if true disables writing a reflog entry for this reset command
     * @return this
     */
    ResetParamBuilder disableRefLog(boolean disableRefLog);

    /**
     * @return a new ResetParamBuilder
     */
    static ResetParamBuilder createResetParamBuilder() {
        return new ResetParamBuilder() {

            private Optional<Set<String>> paths = Optional.empty(); // NOPMD: this field should not be capitalized
            private Optional<String> ref = Optional.empty(); // NOPMD
            private Optional<ResetType> mode = Optional.empty(); // NOPMD
            private boolean disableRefLog = false; // NOPMD

            @Override
            public ResetParamBuilder paths(final Set<String> paths) {
                this.paths = Optional.of(paths);
                return this;
            }

            @Override
            public ResetParamBuilder ref(final String ref) {
                this.ref = Optional.of(ref);
                return this;
            }

            @Override
            public ResetParamBuilder mode(final ResetType mode) {
                this.mode = Optional.of(mode);
                return this;
            }

            @Override
            public ResetParamBuilder disableRefLog(final boolean disableRefLog) {
                this.disableRefLog = disableRefLog;
                return this;
            }

            @Override
            public ResetParam build() {
                return new ResetParam() {

                    @Override
                    public Optional<Set<String>> getPaths() {
                        return paths;
                    }

                    @Override
                    public Optional<String> getRef() {
                        return ref;
                    }

                    @Override
                    public Optional<ResetType> getMode() {
                        return mode;
                    }

                    @Override
                    public boolean isDisableRefLog() {
                        return disableRefLog;
                    }
                };
            }
        };
    }
}
