package it.trashwarecesena.trustalodesktopclient.repository.query.criteria;

import java.util.Set;

/**
 * A wrapper around a Collection of {@link Criterion}, to ease and enforce
 * construction time while allowing the reuse of the same Criterion.
 * 
 * @author Manuel Bonarrigo
 *
 */
public interface Criteria {

    /**
     * Retrieve all the {@link Criterion} expressed by this Criteria as a
     * {@link Set}.
     * 
     * @return a Set containing all the Criterion which are desired to be fetched
     *         against the persistence storage
     */
    Set<Criterion> getCriterionAsSet();

}
