package org.gitgud.model.remote.push;

import java.util.Optional;

import org.gitgud.events.ProgressListener;
import org.gitgud.model.utils.Builder;

/**
 *
 */
public interface PushParamBuilder extends Builder<PushParam> {

    /**
     * @return a new push param builder
     */
    static PushParamBuilder createPushParamBuilder() {
        return new PushParamBuilder() {

            private boolean reqIsDryRun; // = false; NOPMD - these are not static final fields, they should not be
                                         // capitalized
            private boolean reqIsForce; // = false; NOPMD
            private boolean reqIsPushAll; // = false; NOPMD
            private boolean reqIsPushTags; // = false; NOPMD
            private Optional<ProgressListener> optProgressListener = Optional.empty(); // NOPMD

            @Override
            public PushParam build() {
                return new PushParam() {

                    @Override
                    public Optional<ProgressListener> getProgressListener() {
                        return optProgressListener;
                    }

                    @Override
                    public boolean isDryRun() {
                        return reqIsDryRun;
                    }

                    @Override
                    public boolean isForce() {
                        return reqIsForce;
                    }

                    @Override
                    public boolean isPushAll() {
                        return reqIsPushAll;
                    }

                    @Override
                    public boolean isPushTags() {
                        return reqIsPushTags;
                    }
                };
            }

            @Override
            public PushParamBuilder dryRun(final boolean isDryRun) {
                reqIsDryRun = isDryRun;
                return this;
            }

            @Override
            public PushParamBuilder force(final boolean isForce) {
                reqIsForce = isForce;
                return this;
            }

            @Override
            public PushParamBuilder progressListener(final ProgressListener progressListener) {
                optProgressListener = Optional.ofNullable(progressListener);
                return this;
            }

            @Override
            public PushParamBuilder pushAll(final boolean isPushAll) {
                reqIsPushAll = isPushAll;
                return this;
            }

            @Override
            public PushParamBuilder pushTags(final boolean isPushTags) {
                reqIsPushTags = isPushTags;
                return this;
            }

        };
    }

    /**
     * @param isDryRun
     *            true to set dry run
     * @return this
     */
    PushParamBuilder dryRun(boolean isDryRun);

    /**
     * @param isForce
     *            true to set force
     * @return this
     */
    PushParamBuilder force(boolean isForce);

    /**
     * @param progressListener
     *            the progress listener
     * @return this
     */
    PushParamBuilder progressListener(ProgressListener progressListener);

    /**
     * @param isPushAll
     *            true to set push all
     * @return this
     */
    PushParamBuilder pushAll(boolean isPushAll);

    /**
     * @param isPushTags
     *            true to set push tags
     * @return this
     */
    PushParamBuilder pushTags(boolean isPushTags);

}
