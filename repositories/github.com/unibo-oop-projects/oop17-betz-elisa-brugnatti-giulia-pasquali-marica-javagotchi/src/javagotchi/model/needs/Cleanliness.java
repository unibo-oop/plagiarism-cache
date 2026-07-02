package javagotchi.model.needs;

/**
 * Class that defines the cleanliness of the javagotchi.
 * 
 * @author elisa
 */
public class Cleanliness extends AbstractNeed {

    /**
     * 
     */
    private static final long serialVersionUID = 444385910981678831L;
    private static final double INC = 0.3;
    private static final double DEC = 0.1;

    /**
     * Constructor; it sets the initial value of the level.
     */
    public Cleanliness() {
        super();
        this.setLevel(MAX_LEVEL);
    }

    /**
     * Method to increase the level.
     */
    @Override
    protected final void incLevel() {
        this.setLevel(this.getLevel() + INC);
    }

    /**
     * Method to decrease the level.
     */
    @Override
    protected void decLevel() {
        this.setLevel(this.getLevel() - DEC);
    }

}
