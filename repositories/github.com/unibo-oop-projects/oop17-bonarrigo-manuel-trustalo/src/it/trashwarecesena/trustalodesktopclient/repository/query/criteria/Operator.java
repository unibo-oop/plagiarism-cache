package it.trashwarecesena.trustalodesktopclient.repository.query.criteria;

/**
 * Express all the possible semantics for a {@link Criterion} to have.
 * 
 * @author Manuel Bonarrigo
 *
 */
public enum Operator implements Comparable<Operator> {

    /**
     * The <i>ALL</i> operator is meant to be used to retrieve the whole content of a Class related persistence
     * storage.
     * This may be very heavy to perform, and such an operation might be not allowed to be performed on certain
     * persistence storage.
     * <p>
     * The meaning of any other operator which may be used in conjunction with this will be hidden, since 
     * the "all" semantic ignores any kind of filtering.
     */
    ALL(false),
    /**
     * The <i>IS NULL</i> operator is meant to be used to retrieve group of objects with the common characteristic
     * of having the value of a particular field unknown, or not set. 
     * <p>
     * During usage, this operator will be put in an AND relationship with the other ones, exception made for the 
     * usage in combination with the ALL Operator, which will mask it.
     */
    IS_NULL(true),
    /**
     * The <i>MATCH</i> operator is meant to be used to retrieve group of objects with the common characteristic 
     * of having the value of a particular field containing a user-chosen value in any position.
     * <p>
     * During usage, this operator will be put in an AND relationship with the other ones, exception made for the 
     * usage in combination with the ALL Operator, which will mask it.
     */
    MATCH(true),
    /**
     * The <i>EQUALS</i> operator is meant to be used to retrieve group of objects with the common characteristic 
     * of having the value of a particular field perfectly equal to a user-chosen value.
     * <p>
     * During usage, this operator will be put in an AND relationship with the other ones, exception made for the 
     * usage in combination with the ALL Operator, which will mask it.
     */
    EQUALS(true);

    private final boolean selective;

    Operator(final boolean selective) {
        this.selective = selective;
    }

    /**
     * Tells if the operator semantic will influence the evaluation of any other parameter which may be used in 
     * conjunction with it. 
     * @return true if the semantic of the operator is such that particular attention should be given to other
     * parameters used, false otherwise.
     */
    public boolean isSelective() {
        return this.selective;
    }

}
