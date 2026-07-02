package model.effects.convolution;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;

/**
 * Represents a subclass of ConvolutionImpl .
 */
public class FastBlur extends ConvolutionImpl {

    private static final long serialVersionUID = 1L;
    private static final int MAX_PIXEL_VALUE = 256;
    private static final int HEX_RED_BYTE = 0xff0000;
    private static final int HEX_GREEN_BYTE = 0x00ff00;
    private static final int HEX_BLUE_BYTE = 0x0000ff;
    private static final int HEX_ALPHA_BYTE = 0xff000000;
    private final int[] bitMasks = new int[] { 0xFF0000, 0xFF00, 0xFF, 0xFF000000 };
    private static final String FAST_BLUR_NAME = "Fast Blur";
    private final ConvolutionKernel fastBlurKernel = new ConvolutionKernel(3, 3,
            new float[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 9);
    private int amount = 1;

    /**
     * @param amount the specified blur amount.
     */
    public FastBlur(final int amount) {
        super();
        super.setEffectName(FAST_BLUR_NAME);
        super.setKernel(this.fastBlurKernel);
        super.setEdgeCondition(ConvolveOp.EDGE_ZERO_FILL);
        if (amount < 1) {
            this.amount = 1;
        } else {
            this.amount = amount;
        }
    }

    /**
     * @see model.effects.Effect#apply() .
     */
    @Override
    public BufferedImage apply(final BufferedImage source) {
        BufferedImage bufferedImage = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());

        bufferedImage = deepCopy(source);
        // 3 passes of box blur tends to Gaussian blur
        bufferedImage = fastblur(bufferedImage, this.amount);
        bufferedImage = fastblur(bufferedImage, this.amount);
        bufferedImage = fastblur(bufferedImage, this.amount);

        if (source.getType() != BufferedImage.TYPE_INT_ARGB_PRE) {
            return bufferedImage;
        } else {
            return setSourceAlpha(source, bufferedImage);
        }
    }

    private BufferedImage deepCopy(final BufferedImage bi) {
        final ColorModel cm = bi.getColorModel();
        final boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        final WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    private BufferedImage fastblur(final BufferedImage data, final int radius) {
        final int w = data.getWidth();
        final int h = data.getHeight();
        final int wm = w - 1;
        final int hm = h - 1;
        final int wh = w * h;
        final int div = radius + radius + 1;
        int[] r = new int[wh];
        int[] g = new int[wh];
        int[] b = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, p1, p2, yp, yi, yw;
        int[] vmin = new int[Math.max(w, h)];
        int[] vmax = new int[Math.max(w, h)];
        int[] pix = ((DataBufferInt) data.getRaster().getDataBuffer()).getData();

        int[] dv = new int[MAX_PIXEL_VALUE * div];
        for (i = 0; i < MAX_PIXEL_VALUE * div; i++) {
            dv[i] = (i / div);
        }
        yw = 0;
        yi = 0;

        for (y = 0; y < h; y++) {
            rsum = 0;
            gsum = 0;
            bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                rsum += (p & HEX_RED_BYTE) >> 16;
                gsum += (p & HEX_GREEN_BYTE) >> 8;
                bsum += p & HEX_BLUE_BYTE;
            }
            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                    vmax[x] = Math.max(x - radius, 0);
                }
                p1 = pix[yw + vmin[x]];
                p2 = pix[yw + vmax[x]];

                rsum += ((p1 & HEX_RED_BYTE) - (p2 & HEX_RED_BYTE)) >> 16;
                gsum += ((p1 & HEX_GREEN_BYTE) - (p2 & HEX_GREEN_BYTE)) >> 8;
                bsum += (p1 & HEX_BLUE_BYTE) - (p2 & HEX_BLUE_BYTE);
                yi++;
            }
            yw += w;
        }

        for (x = 0; x < w; x++) {
            rsum = 0;
            gsum = 0;
            bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;
                rsum += r[yi];
                gsum += g[yi];
                bsum += b[yi];
                yp += w;
            }
            yi = x;
            for (y = 0; y < h; y++) {
                pix[yi] = HEX_ALPHA_BYTE | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];
                if (x == 0) {
                    vmin[y] = Math.min(y + radius + 1, hm) * w;
                    vmax[y] = Math.max(y - radius, 0) * w;
                }
                p1 = x + vmin[y];
                p2 = x + vmax[y];

                rsum += r[p1] - r[p2];
                gsum += g[p1] - g[p2];
                bsum += b[p1] - b[p2];

                yi += w;
            }
        }

        final SinglePixelPackedSampleModel sm = new SinglePixelPackedSampleModel(DataBuffer.TYPE_INT, data.getWidth(),
                data.getHeight(), bitMasks);
        final DataBufferInt db = new DataBufferInt(pix, pix.length);
        final WritableRaster wr = Raster.createWritableRaster(sm, db, new Point());
        return new BufferedImage(ColorModel.getRGBdefault(), wr, false, null);
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
