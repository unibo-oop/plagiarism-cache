package model.effects.convolution;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Represents a subclass of ConvolutionImpl .
 */
public class EdgeDetection extends ConvolutionImpl {

    private static final long serialVersionUID = 1L;
    private static final String EFFECT_NAME = "Edge Detection";
    private final EdgeDetectionGrade grade;
    private static final int ALPHA_THRESHOLD = 40;
    private final ConvolutionKernel lowKernel = new ConvolutionKernel(3, 3,
            new float[] { -1, -1, -1, -1, 8, -1, -1, -1, -1 }, 1);
    private final ConvolutionKernel mediumKernel = new ConvolutionKernel(3, 3,
            new float[] { 0, 1, 0, 1, -4, 1, 0, 1, 0 }, 1);
    private final ConvolutionKernel highKernel = new ConvolutionKernel(3, 3,
            new float[] { 1, 0, -1, 0, 0, 0, -1, 0, 1 }, 1);


    /**
     * @param grade the specified grade of edge detection
     */
    public EdgeDetection(final EdgeDetectionGrade grade) {
        super();
        switch (grade) {
        case LOW:
            super.setKernel(this.lowKernel);
            break;
        case MEDIUM:
            super.setKernel(this.mediumKernel);
            break;
        case HIGH:
            super.setKernel(this.highKernel);
            break;
        default:
            break;
        }

        super.setEdgeCondition(ConvolveOp.EDGE_ZERO_FILL);
        super.setEffectName(EFFECT_NAME);
        this.grade = grade;
    }

    /**
     * @see model.effects.Effect#apply() .
     */
    @Override
    public BufferedImage apply(final BufferedImage source) {

        if (source.getType() != BufferedImage.TYPE_INT_ARGB_PRE) {
            return super.apply(source);
        } else {
            final BufferedImage rgb = new BufferedImage(source.getWidth(), source.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            final BufferedImageOp colorConvert = new ColorConvertOp(null);
            colorConvert.filter(source, rgb);
            final BufferedImageOp convolve = new ConvolveOp(new Kernel(super.getKernel().getColumns(),
                    super.getKernel().getRows(), super.getKernel().getKernelMatrix()), super.getEdgeCondition(), null);

            final BufferedImage destination = new BufferedImage(rgb.getWidth(), rgb.getHeight(), rgb.getType());

            convolve.filter(rgb, destination);

            return setSourceAlpha(source, destination);
        }
    }

    /**
     * @return the edge detection effect grade
     */
    public EdgeDetectionGrade getEdgeDetectionGrade() {
        return this.grade;
    }

    private BufferedImage setSourceAlpha(final BufferedImage alphaChannel, final BufferedImage rgbChannels) {
        final BufferedImage bufferedImage = new BufferedImage(rgbChannels.getWidth(), rgbChannels.getHeight(),
                alphaChannel.getType());
        for (int x = 0; x < alphaChannel.getWidth(); x++) {
            for (int y = 0; y < alphaChannel.getHeight(); y++) {
                final Color dest = new Color(rgbChannels.getRGB(x, y));
                int alpha = new Color(alphaChannel.getRGB(x, y), true).getAlpha();
                if (dest.getRed() + dest.getGreen() + dest.getBlue() < ALPHA_THRESHOLD) {
                    alpha = 0x00;
                }
                final Color destColor = new Color(dest.getRed(), dest.getGreen(), dest.getBlue(), alpha);
                bufferedImage.setRGB(x, y, destColor.getRGB());
            }
        }

        return bufferedImage;
    }

}
