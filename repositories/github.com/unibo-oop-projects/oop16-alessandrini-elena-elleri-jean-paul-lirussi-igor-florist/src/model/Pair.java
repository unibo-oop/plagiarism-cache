package model;

/**
 * class Pair used for dates, from the slides.
 * @param <X> the first element
 * @param <Y> the second element
 */
public class Pair<X, Y> {

    private final X first;
    private final Y second;

    /**
     * constructor for class pair.
     * @param first first element
     * @param second second element
     */
     public Pair(final X first, final Y second) {
     this.first = first;
     this.second = second;
     }

     /**
      * 
      * @return the first
      */
     public X getFirst() {
     return this.first;
     }

     /**
      * 
      * @return the second
      */
     public Y getSecond() {
     return this.second;
     }

     /**
      * @return the string of the pair
      */
     public String toString() {
     return "<" + this.first + "," + this.second + ">";
     }

}
