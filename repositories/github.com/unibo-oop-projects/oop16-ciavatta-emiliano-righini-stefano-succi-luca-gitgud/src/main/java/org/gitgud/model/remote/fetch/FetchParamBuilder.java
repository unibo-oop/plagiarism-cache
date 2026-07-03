package org.gitgud.model.remote.fetch;

import java.util.Optional;

import org.gitgud.events.ProgressListener;
import org.gitgud.model.utils.Builder;

/**
 * A static factory used to create builders to create FetchParam.
 */
public interface FetchParamBuilder extends Builder<FetchParam> {

    /**
     * @return a new FetchParamBuilder
     */
    static FetchParamBuilder createFetchParamBuilder() {
        return new FetchParamBuilder() {

            private Optional<String> optRemoteName = Optional.empty(); // NOPMD
            private Optional<ProgressListener> optProgressListener = Optional.empty(); // NOPMD
            private boolean reqIsDryRun; // = false; // NOPMD
            private boolean reqCheckValidity; // = false; // NOPMD

            @Override
            public FetchParam build() {
                return new FetchParam() {

                    @Override
                    public boolean checkValidity() {
                        return reqCheckValidity;
                    }

                    @Override
                    public Optional<ProgressListener> getProgressListener() {
                        return optProgressListener;
                    }

                    @Override
                    public Optional<String> getRemoteName() {
                        return optRemoteName;
                    }

                    @Override
                    public boolean isDryRun() {
                        return reqIsDryRun;
                    }
                };
            }

            @Override
            public FetchParamBuilder checkValidity(final boolean isValidityChecked) {
                reqCheckValidity = false;
                return this;
            }

            @Override
            public FetchParamBuilder isDryRun(final boolean isDryRun) {
                reqIsDryRun = isDryRun;
                return this;
            }

            @Override
            public FetchParamBuilder progressListener(final ProgressListener progressListener) {
                optProgressListener = Optional.ofNullable(progressListener);
                return this;
            }

            @Override
            public FetchParamBuilder remoteName(final String remoteName) {
                optRemoteName = Optional.ofNullable(remoteName);
                return this;
            }
        };
    }

    /**
     * @param isValidityChecked
     *            true if the objects received will be checked for validity
     * @return this
     */
    FetchParamBuilder checkValidity(boolean isValidityChecked);

    /**
     * @param isDryRun
     *            true if the fetch operation should be a dry run
     * @return this
     */
    FetchParamBuilder isDryRun(boolean isDryRun);

    /**
     * @param progressListener
     *            the progress listener associated with the fetch operation
     * @return this
     */
    FetchParamBuilder progressListener(ProgressListener progressListener);

    /**
     * @param remoteName
     *            the remote name to use in the fetch action. If not set, it is used the default remote (origin)
     * @return this
     */
    FetchParamBuilder remoteName(String remoteName);

}
