package model.effects.convolution;

import java.awt.image.ConvolveOp;

/**
 * Represents a subclass of ConvolutionImpl .
 */
public class Sharpen extends ConvolutionImpl {

    private static final long serialVersionUID = 1L;
    private static final String EFFECT_NAME = "Sharpen";
    private final ConvolutionKernel sharpenKernel = new ConvolutionKernel(3, 3,
            new float[] { 0, -1, 0, -1, 5, -1, 0, -1, 0 }, 1);

    /**
     * Constructor of the class that initialize fields.
     */
    public Sharpen() {
        super();
        super.setEffectName(EFFECT_NAME);
        super.setKernel(this.sharpenKernel).setEdgeCondition(ConvolveOp.EDGE_NO_OP);
    }

}
