package it.trashwarecesena.trustalodesktopclient.repository.query.criteria;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the {@link Criteria} interface backed up by a {@link Set}.
 * <p>
 * Construction is pursued both by a builder and by public constructor, to
 * ensure the ability of starting a creation of a CriteriaImpl even if not all
 * the {@link Criterion} are fully known or using an already known Set of
 * Criterion.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class CriteriaImpl implements Criteria {

    private final Set<Criterion> selectors;

    /**
     * Builds a CriteriaImpl over the given {@link Set} of {@link Criterion}.
     * 
     * @param selectors
     *            a Set of Criterion carrying the way of fetching the desired
     *            informations
     */
    public CriteriaImpl(final Set<Criterion> selectors) {
        this.selectors = new HashSet<>(selectors);
    }

    @Override
    public Set<Criterion> getCriterionAsSet() {
        return new HashSet<>(this.selectors);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((selectors == null) ? 0 : selectors.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CriteriaImpl other = (CriteriaImpl) obj;
        if (selectors == null) {
            if (other.selectors != null) {
                return false;
            }
        } else if (!selectors.equals(other.selectors)) {
            return false;
        }
        return true;
    }

    /**
     * A builder to create a CriteriaImpl through steps.
     * @author Manuel Bonarrigo
     *
     */
    public static final class Builder {
        private final Set<Criterion> buildSelectors;

        /**
         * Initialize an empty {@link Set} of {@link Criterion} to build the
         * CriteriaImpl on.
         */
        public Builder() {
            this.buildSelectors = new HashSet<>();
        }

        /**
         * Add a {@link Criterion} to the creational backing up {@link Set}.
         * @param criterion a {@link Criterion} of any form.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder addCriterion(final Criterion criterion) {
            this.buildSelectors.add(criterion);
            return this;
        }

        /**
         * Instantiate a CriteriaImpl over the built {@link Set} of {@link Criterion}.
         * 
         * @return a fully instantiated CriteriaImpl
         */
        public CriteriaImpl build() {
            return new CriteriaImpl(buildSelectors);
        }
    }
}
