package util.mapbuilder;

import java.util.Optional;

/**
 * 
 * the starting point for implementing CaseBuilder.
 * 
 * @param <X> the type of the CaseBuilder
 */
public abstract class AbstractCaseBuilder<X> implements CaseBuilder<X> {
    private Optional<String> topId;
    private Optional<String> bottomId;
    private Optional<String> borderId;

    /**
     * Initialize the class.
     */
    public AbstractCaseBuilder() {
        this.bottomId = Optional.empty();
        this.topId = Optional.empty();
        this.borderId = Optional.empty();
    }

    /** {@inheritDoc} **/
    @Override
    public abstract X build();

    /** {@inheritDoc} **/
    @Override
    public CaseBuilder<X> setTop(final String topLayerPath) {
        this.topId = Optional.of(topLayerPath);
        return this;
    }

    /** {@inheritDoc} **/
    @Override
    public CaseBuilder<X> setBorder(final String border) {
        this.borderId = Optional.of(border);
        return this;
    }

    /** {@inheritDoc} **/
    @Override
    public CaseBuilder<X> setBottom(final String bottom) {
        this.bottomId = Optional.of(bottom);
        return this;
    }

    /**
     * 
     * @return the top element Id
     */
    protected Optional<String> getTop() {
        return topId;
    }

    /**
     * 
     * @return the top element Id
     */
    protected Optional<String> getBottom() {
        return bottomId;
    }

    /**
     * 
     * @return the border element Id
     */
    protected Optional<String> getBorder() {
        return borderId;
    }
}
