package model;

/**
 * 
 * This is an implementation of {@link model.Room}.
 * 
 */
public  class DiscountImpl implements Discount {

    private final int nStudents;
    private final int nUnder14;

    /**
     * Builds a new {@link DiscountImpl}.
     * 
     * @param nUnder14
     *             the number of people with an age under 14 years
     * @param nStudents
     *             the number of people who are students
     */
    public DiscountImpl(final int nUnder14, final int nStudents) {
        this.nUnder14 = nUnder14;
        this.nStudents = nStudents;
    }

    @Override
    public int getNumberStudents() {
        return nStudents;
    }

    @Override
    public int getNumberUnder14() {
        return nUnder14;
    }

}
