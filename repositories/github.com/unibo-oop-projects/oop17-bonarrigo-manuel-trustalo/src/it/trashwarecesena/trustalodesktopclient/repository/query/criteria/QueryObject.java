package it.trashwarecesena.trustalodesktopclient.repository.query.criteria;

import java.util.List;

/**
 * The main component of the system querying "language". Classes implementing this interface are expected to carry with
 * them all the information needed to retrieve a set of instances of the same Class through the concatenation of one or
 * more Criterion.
 * @author Manuel Bonarrigo
 *
 */
public interface QueryObject {

    /**
     * Retrieve what kind of instances this QueryObject is expected to retrieve.
     * @return a {@link Class} expressing the desired family of objects.
     */
    Class<?> getDesiredHandler();

    /**
     * Returns all the {@link Criterion} this QueryObject is expected to satisfy presented as a {@link List}.
     * @return the List of Criterion to be satisfied against the persistence module
     */
    List<Criterion> getCriterionList();

}
