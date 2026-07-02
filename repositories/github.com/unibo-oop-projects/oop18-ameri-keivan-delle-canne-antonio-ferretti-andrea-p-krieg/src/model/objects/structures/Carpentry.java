package model.objects.structures;

import java.util.LinkedList;
import java.util.List;

import model.resources.BasicResources;

/**
 * Produces Wood.
 */
public class Carpentry extends AbstractResourceProducer {

    private static final String TERRAIN = "Base";
    private static final int INITIAL_VALUE = 5000;
    private static final int PRODUCED_VALUE = 200;

    private final int total;
    private int produced;
    private int left;

    /**
     * Initialize the Structure.
     */
    public Carpentry() {
        super(BasicResources.WOOD);
        final List<String> correctTerrains;
        correctTerrains = new LinkedList<>();
        correctTerrains.add(TERRAIN);
        super.addBuildableTerrain(correctTerrains);
        this.total = INITIAL_VALUE;
        this.left = this.total;
    }

    /** {@inheritDoc} **/
    @Override
    public int produce(final double modifier) {
        super.checkOwner();
        produced = (int) (PRODUCED_VALUE * modifier);
        return produced;
    }

    /** {@inheritDoc} **/
    @Override
    public int getQuantity() {
        if (this.left - this.produced < 0) {
            this.produced = this.left;
            this.left = 0;
            return this.produced;
        }
        this.left -= this.produced;
        return this.produced;
    }

    /** {@inheritDoc} **/
    @Override
    public boolean isOver() {
        return this.left == 0;
    }

    /** {@inheritDoc} **/
    @Override
    public String getDescription() {
        return "Carpentry\n" + super.getOwnerName() + "\nLeft: " + this.left + " / " + this.total;
    }

}
