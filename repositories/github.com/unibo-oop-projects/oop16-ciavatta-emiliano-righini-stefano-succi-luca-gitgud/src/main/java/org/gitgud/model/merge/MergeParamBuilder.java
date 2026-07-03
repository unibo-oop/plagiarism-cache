package org.gitgud.model.merge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.gitgud.events.ProgressListener;
import org.gitgud.model.utils.Builder;

/**
 * A static factory used to create builders to create MergeParam.
 */
public interface MergeParamBuilder extends Builder<MergeParam> {

    /**
     * @return a new MergeParamBuilder
     */
    static MergeParamBuilder createMergeParamBuilder() {
        return new MergeParamBuilder() {

            private Optional<String> optMessage = Optional.empty(); // NOPMD
            private Optional<ProgressListener> optProgressListener = Optional.empty(); // NOPMD
            private boolean reqIsFastForwardOnly; // = false; // NOPMD
            private boolean reqIsMergeAlways; // = false; // NOPMD
            private final List<String> reqRefs = new ArrayList<>(); // NOPMD

            @Override
            public MergeParamBuilder addRef(final String ref) {
                Objects.requireNonNull(ref);
                reqRefs.add(ref);
                return this;
            }

            @Override
            public MergeParam build() {
                return new MergeParam() {

                    @Override
                    public Optional<String> getMessage() {
                        return optMessage;
                    }

                    @Override
                    public Optional<ProgressListener> getProgressListener() {
                        return optProgressListener;
                    }

                    @Override
                    public List<String> getRefs() {
                        return reqRefs;
                    }

                    @Override
                    public boolean isFastForwardOnly() {
                        return reqIsFastForwardOnly;
                    }

                    @Override
                    public boolean isMergeAlways() {
                        return reqIsMergeAlways;
                    }

                };
            }

            @Override
            public MergeParamBuilder fastForwardOnly(final boolean isFastForwardOnly) {
                reqIsFastForwardOnly = isFastForwardOnly;
                return this;
            }

            @Override
            public MergeParamBuilder mergeAlways(final boolean isMergeAlways) {
                reqIsMergeAlways = isMergeAlways;
                return this;
            }

            @Override
            public MergeParamBuilder message(final String message) {
                optMessage = Optional.ofNullable(message);
                return this;
            }

            @Override
            public MergeParamBuilder progressListener(final ProgressListener progressListener) {
                optProgressListener = Optional.ofNullable(progressListener);
                return this;
            }
        };
    }

    /**
     * @param ref
     *            the ref to add
     * @return this
     */
    MergeParamBuilder addRef(String ref);

    /**
     * @param isFastForwardOnly
     *            true to require fast forward only
     * @return this
     */
    MergeParamBuilder fastForwardOnly(boolean isFastForwardOnly);

    /**
     * @param isMergeAlways
     *            true to require merge
     * @return this
     */
    MergeParamBuilder mergeAlways(boolean isMergeAlways);

    /**
     * @param message
     *            the merge message
     * @return this
     */
    MergeParamBuilder message(String message);

    /**
     * @param progressListener
     *            the progress listener associated with the merge operation
     * @return this
     */
    MergeParamBuilder progressListener(ProgressListener progressListener);

}
