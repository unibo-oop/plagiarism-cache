package model.effects.convolution;

import java.awt.image.ConvolveOp;

/**
 * Generic Implementation of {@link model.effects.convolution.Convolution}
 * effect.
 */
public class ConvolveOperation extends ConvolutionImpl {

    private static final long serialVersionUID = 1L;
    private static final String EFFECT_NAME = "Generic convolution effect";

    /**
     * Constructor of the class .
     */
    public ConvolveOperation() {
        super();
        super.setEffectName(EFFECT_NAME);
        super.setEdgeCondition(ConvolveOp.EDGE_NO_OP);
    }
}
