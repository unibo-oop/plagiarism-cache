package javagotchi.model.needs;

/**
 * Class that defines the hunger of the javagotchi.
 * 
 * @author elisa
 *
 */
public class Hunger extends AbstractNeed {
    /**
     * 
     */
    private static final long serialVersionUID = -6456092539009377451L;
    private static final double INC = 0.25;
    private static final double DEC = 0.1;
    private static final double INIT = 0.7;

    /**
     * Constructor; it sets the initial value of the level.
     */
    public Hunger() {
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
