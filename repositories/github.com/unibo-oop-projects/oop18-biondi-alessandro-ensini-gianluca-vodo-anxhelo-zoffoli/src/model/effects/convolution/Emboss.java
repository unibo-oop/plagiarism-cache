package model.effects.convolution;

import java.awt.image.ConvolveOp;

/**
 * Represents a subclass of ConvolutionImpl .
 */
public class Emboss extends ConvolutionImpl {

    private static final long serialVersionUID = 1L;
    private static final String EFFECT_NAME = "Emboss";
    private final ConvolutionKernel embossKernel = new ConvolutionKernel(3, 3,
            new float[] { -2, -1, 0, -1, 1, 1, 0, 1, 2 }, 1);

    /**
     * Constructor of the class that initialize fields .
     */
    public Emboss() {
        super();
        super.setEffectName(EFFECT_NAME);
        super.setKernel(this.embossKernel).setEdgeCondition(ConvolveOp.EDGE_NO_OP);
    }

}
