package javagotchi.model.needs;
/**
 * Class that defines the health of the javagotchi.
 * 
 * @author elisa
 */
public class Health extends AbstractNeed {
    /**
     * 
     */
    private static final long serialVersionUID = 8407461363824519229L;
    private static final double INC = 0.25;
    private static final double DEC = 0.4;

    /**
     * Constructor; it sets the initial value of the level.
     */
    public Health() {
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
