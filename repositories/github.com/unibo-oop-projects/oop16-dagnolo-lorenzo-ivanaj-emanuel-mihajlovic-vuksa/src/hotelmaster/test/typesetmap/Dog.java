package hotelmaster.test.typesetmap;

/**
 * 
 */
public class Dog extends Animal {

    private final boolean largeBreed;

    /**
     * 
     * @param name
     *            value
     * @param largeBreed
     *            subclass-specific value
     */
    public Dog(final String name, final boolean largeBreed) {
        super(name);
        this.largeBreed = largeBreed;
    }

    /**
     * Some subclass-specific method.
     * 
     * @return value
     */
    public boolean isLargeBreed() {
        return largeBreed;
    }

    @Override
    public String toString() {
        return "[dog] " + (largeBreed ? "[L] " : "") + super.toString();
    }
}
