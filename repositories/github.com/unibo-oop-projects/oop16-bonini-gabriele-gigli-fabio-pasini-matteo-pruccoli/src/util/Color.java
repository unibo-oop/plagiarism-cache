package util;

/** Classe Color. */
public final class Color {

    private static final int RED_MASK = 0xff0000;
    private static final int GREEN_MASK = 0xff00;
    private static final int BLUE_MASK = 0xff;
    private static final int MAX_RGB = 255;

    private Color() {

    }

    /**
     * Funzione utilizzate per effettuare aplha blending tra il colore passato
     * come parametro ed un altro colore passato come parametro.
     * 
     * @param color
     *            Colore a cui applicare alpha blending.
     * @param alpha
     *            Valore di trasparenza del colore.
     * @param lightColor
     *            Colore base alphaBlending.
     * @return Colore a cui è stato applicato alpha blending con il colore passato come
     *         parametro
     */
    public static int alphaBlending(final int color, final int alpha, final int lightColor) {

        if (alpha > MAX_RGB || alpha < 0) {
            throw new IllegalArgumentException("Il valore alpha deve essere compreso tra 0 e 255");
        }

        final int[] rgb = extractRGB(color);
        final int[] rgbLight = extractRGB(lightColor);

        if (alpha == 0) {
            return createColor(rgb);
        }

        return changeColor(rgb, alpha, rgbLight);
    }

    private static int[] extractRGB(final int color) {
        return new int[] { (color & RED_MASK) >> 16, (color & GREEN_MASK) >> 8, (color & BLUE_MASK) };
    }

    private static int createColor(final int[] rgb) {
        return rgb[0] << 16 | rgb[1] << 8 | rgb[2];
    }

    private static int changeColor(final int[] rgb, final int alpha, final int[] rgbLight) {
        final int[] rgbColor = new int[] { ((rgbLight[0] * alpha) + (rgb[0] * (MAX_RGB - alpha))) >> 8,
                ((rgbLight[1] * alpha) + (rgb[1] * (MAX_RGB - alpha))) >> 8,
                ((rgbLight[2] * alpha) + (rgb[2] * (MAX_RGB - alpha))) >> 8 };

        return createColor(rgbColor);
    }

}
