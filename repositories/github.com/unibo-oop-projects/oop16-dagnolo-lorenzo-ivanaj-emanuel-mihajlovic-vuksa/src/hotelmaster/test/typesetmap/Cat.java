package hotelmaster.test.typesetmap;

/**
 * 
 */
public class Cat extends Animal {

    /**
     * 
     * @param name
     *            value
     */
    public Cat(final String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[cat] " + super.toString();
    }

}
