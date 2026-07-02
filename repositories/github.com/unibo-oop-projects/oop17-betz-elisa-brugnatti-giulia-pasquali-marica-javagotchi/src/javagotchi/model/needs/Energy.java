package javagotchi.model.needs;

/**
 * Class that defines the energy of the javagotchi.
 * 
 * @author elisa
 */
public class Energy extends AbstractNeed {
    /**
     * 
     */
    private static final long serialVersionUID = 194853816908502252L;
    private static final double INC = 0.3;
    private static final double DEC = 0.15;

    /**
     * Constructor; it sets the initial value of the level.
     */
    public Energy() {
        super();
        this.setLevel(MAX_LEVEL);
    }

    /**
     * Method to increase the level.
     */

    @Override
    protected void incLevel() {
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
