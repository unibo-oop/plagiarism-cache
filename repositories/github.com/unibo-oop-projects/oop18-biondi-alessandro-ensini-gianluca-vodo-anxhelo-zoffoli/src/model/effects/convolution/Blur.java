package model.effects.convolution;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Represents a subclass of ConvolutionImpl .
 */
public class Blur extends ConvolutionImpl {

    private static final long serialVersionUID = 1L;
    private int amount = 1;
    private final BlurType blurType;
    private final ConvolutionKernel boxBlurKernel = new ConvolutionKernel(3, 3,
            new float[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 9);
    private final ConvolutionKernel gaussianBlurKernel = new ConvolutionKernel(3, 3,
            new float[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 }, 16);
    private static final String GAUSSIAN_BLUR_NAME = "Gaussian Blur";
    private static final String BOX_BLUR_NAME = "Box Blur";

    /**
     * @param amount   the specified blur amount.
     * @param blurType the specified blur effect type
     */
    public Blur(final int amount, final BlurType blurType) {
        super();
        switch (blurType) {
        case GAUSSIAN_BLUR:
            super.setEffectName(GAUSSIAN_BLUR_NAME);
            super.setKernel(this.gaussianBlurKernel);
            break;
        case BOX_BLUR:
            super.setEffectName(BOX_BLUR_NAME);
            super.setKernel(this.boxBlurKernel);
        default:
            break;
        }

        super.setEdgeCondition(ConvolveOp.EDGE_ZERO_FILL);
        this.amount = amount;
        this.blurType = blurType;
    }

    /**
     * @see model.effects.Effect#apply() .
     */
    @Override
    public BufferedImage apply(final BufferedImage source) {
        BufferedImage bufferedImage = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());

        final ConvolveOp conv = new ConvolveOp(new Kernel(super.getKernel().getColumns(), super.getKernel().getRows(),
                super.getKernel().getKernelMatrix()), super.getEdgeCondition(), null);

        conv.filter(source, bufferedImage);

        for (int i = 0; i < this.amount - 1; i++) {
            bufferedImage = conv.filter(bufferedImage, null);
        }

        return bufferedImage;
    }

    /**
     * @return the amount of blur
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * @param amount the specified blur amount
     */
    public void setAmount(final int amount) {
        this.amount = amount;
    }

    /**
     * @return the blur effect type
     */
    public BlurType getBlurType() {
        return this.blurType;
    }
}
