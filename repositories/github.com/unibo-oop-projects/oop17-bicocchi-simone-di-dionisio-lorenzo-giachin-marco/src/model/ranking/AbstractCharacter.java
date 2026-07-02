package model.ranking;

import model.Pair;

/**
 * 
 * 
 *
 */
public class AbstractCharacter extends Pair<String, Double> {

    /**
     * 
     */
    private static final long serialVersionUID = -5923558794648688312L;

    /**
     * 
     * @param first
     *            is the name of character
     * @param age
     *            is the age of character
     */
    public AbstractCharacter(final String first, final double age) {
        super(first);
        this.setSecond(age);
    }

    @Override
    public String toString() {
        return "AbstractCharacter [Character=" + getFirst() + ", age=" + getSecond() + " ]";
    }

}
