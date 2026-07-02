package thatlevelagain.view.map;

/**
 * create a RGB pixel.
 *
 */
public class Pixel {

    private final int rosso, verde, blu;
    /**
     * constructor.
     * @param rosso
     *         red value
     * @param verde
     *         green value
     * @param blu
     *         blue value
     */
    public Pixel(final int rosso, final int verde, final int blu) {
        this.rosso = rosso;
        this.verde = verde;
        this.blu = blu;
    }

    /**
     * compare Color RGB with pixel RGB.
     * @param colore
     *         color to compare with.
     * @return
     *         if they are equal.
     */
    public boolean compare(final Colore colore) {
        return this.rosso == colore.getRosso() && this.blu == colore.getBlu() && this.verde == colore.getVerde();
    }
}
