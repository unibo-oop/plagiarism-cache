package javagotchi.model.needs;

/**
 * Class that defines the happiness of the javagotchi.
 * 
 * @author elisa
 */
public class Happiness extends AbstractNeed {
    /**
     * 
     */
    private static final long serialVersionUID = 975475124336401378L;
    private static final double INC = 0.2;
    private static final double DEC = 0.25;
    private static final double INIT = 0.8;

    /**
     * Constructor; it sets the initial value of the level.
     */
    public Happiness() {
        super();
        this.setLevel(INIT);
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
