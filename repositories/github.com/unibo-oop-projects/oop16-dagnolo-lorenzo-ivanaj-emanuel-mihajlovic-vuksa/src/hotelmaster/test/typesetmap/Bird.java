package hotelmaster.test.typesetmap;

/**
 * 
 */
public class Bird extends Animal {

    /**
     * 
     * @param name
     *            value
     */
    public Bird(final String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[bird] " + super.toString();
    }

}
