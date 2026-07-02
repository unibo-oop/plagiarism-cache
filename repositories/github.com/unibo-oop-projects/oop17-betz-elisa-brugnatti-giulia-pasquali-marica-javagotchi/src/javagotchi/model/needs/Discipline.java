package javagotchi.model.needs;

/**
 * Class that defines the discipline of the javagotchi.
 * 
 * @author elisa
 */
public class Discipline extends AbstractNeed {
    /**
     * 
     */
    private static final long serialVersionUID = 8908416313745849999L;
    private static final double INC = 0.2;
    private static final double DEC = 0.3;
    private static final double INIT = 0.6;

    /**
     * Constructor; it sets the initial value of the level.
     */
    public Discipline() {
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
