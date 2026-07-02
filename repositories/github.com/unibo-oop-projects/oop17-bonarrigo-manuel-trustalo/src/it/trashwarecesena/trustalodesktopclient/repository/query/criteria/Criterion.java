package it.trashwarecesena.trustalodesktopclient.repository.query.criteria;

import java.util.Optional;

/**
 * A Criterion is the atomic form of selection of a group of objects from any
 * form of persistence storage. The evaluation of a Criterion will need to be
 * translated to the enquiring language specific to the persistence technology.
 * 
 * @author Manuel Bonarrigo
 *
 */
public interface Criterion extends Comparable<Criterion> {

    /**
     * Tells if the Criterion semantic will influence the evaluation of any other
     * parameter which will be used in conjunction with it.
     * 
     * @return true if the semantic of the Criterion is such that particular
     *         attention should be given to other used parameters, false otherwise.
     */
    boolean isSelective();

    /**
     * Retrieve the {@link Operator} which gave semantic to the Criterion in the
     * first place.
     * 
     * @return a member of the Operator enumeration
     */
    Operator getOperator();

    /**
     * Retrieve the selector used to refer to a particular enquired field of a
     * Class, if any.
     * 
     * @return an {@link Optional}{@literal <}String{@literal >} containing the name
     *         of the selector, or Optional.empty otherwise
     */
    Optional<String> getSelector();

    /**
     * Retrieve the value a selective Criterion is asked to be tested against, if
     * any.
     * 
     * @return an {@link Optional}{@literal <}Object{@literal >} containing the
     *         value represented as an Object, or Optional.empty otherwise
     */
    Optional<Object> getValue();

    /**
     * Retrieve the Class chosen for the getValue() result to be handled.
     * 
     * @return an {@link Optional}{@literal <}Class{@literal >} containing the
     *         handler of this Criterion value, or Optional.empty otherwise
     */
    Optional<Class<?>> getValueHandler();

}
