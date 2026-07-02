package model.effects.convolution;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import model.effects.EffectType;

/**
 * Implementation of {@link model.effects.convolution.Convolution} effect.
 */
public abstract class ConvolutionImpl implements Convolution {

    private static final long serialVersionUID = 1L;
    private ConvolutionKernel kernel;
    private String effectName;
    private int edgeCondition = ConvolveOp.EDGE_NO_OP;

    /**
     * @see model.effects.Effect#apply() .
     */
    @Override
    public BufferedImage apply(final BufferedImage source) {
        final BufferedImage rgb = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);
        final BufferedImageOp colorConvert = new ColorConvertOp(null);
        colorConvert.filter(source, rgb);
        final BufferedImageOp convolve = new ConvolveOp(
                new Kernel(kernel.getColumns(), kernel.getRows(), kernel.getKernelMatrix()), edgeCondition, null);

        final BufferedImage destination = new BufferedImage(rgb.getWidth(), rgb.getHeight(), rgb.getType());

        convolve.filter(rgb, destination);

        return setSourceAlpha(source, destination);
    }

    /**
     * @see model.effects.Effect#getEffectName() .
     */
    @Override
    public String getEffectName() {
        return this.effectName;
    }

    /**
     * @see model.effects.Effect#setEffectName() .
     */
    @Override
    public void setEffectName(final String name) {
        this.effectName = name;
    }

    /**
     * @see model.effects.Effect#getEffectType() .
     */
    @Override
    public EffectType getEffectType() {
        return EffectType.Convolution;
    }

    /**
     * @see model.effects.convolution.Convolution#setKernel() .
     */
    @Override
    public Convolution setKernel(final ConvolutionKernel kernel) {
        this.kernel = kernel;
        return this;
    }

    /**
     * @see model.effects.convolution.Convolution#setEdgeCondition() .
     */
    @Override
    public Convolution setEdgeCondition(final int edgeCondition) {
        this.edgeCondition = edgeCondition;
        return this;
    }

    /**
     * @see model.effects.convolution.Convolution#getKernel() .
     */
    @Override
    public ConvolutionKernel getKernel() {
        return this.kernel;
    }

    /**
     * @see model.effects.convolution.Convolution#getEdgeCondition() .
     */
    @Override
    public int getEdgeCondition() {
        return this.edgeCondition;
    }

    private BufferedImage setSourceAlpha(final BufferedImage alphaChannel, final BufferedImage rgbChannels) {
        final BufferedImage bufferedImage = new BufferedImage(rgbChannels.getWidth(), rgbChannels.getHeight(),
                alphaChannel.getType());
        for (int x = 0; x < alphaChannel.getWidth(); x++) {
            for (int y = 0; y < alphaChannel.getHeight(); y++) {
                final Color src = new Color(alphaChannel.getRGB(x, y), true);
                final Color dest = new Color(rgbChannels.getRGB(x, y));
                bufferedImage.setRGB(x, y,
                        new Color(dest.getRed(), dest.getGreen(), dest.getBlue(), src.getAlpha()).getRGB());
            }
        }

        return bufferedImage;
    }

}
