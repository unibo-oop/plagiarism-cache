package view;
/**
 * Enum for che View color.
 *
 */
public enum ViewColor {
    /**
     * the light blue.
     */
     light(224, 242, 241),
     /**
      * more dark light blue.
      */
     lightblue(77, 182, 173),
     /**
      * dark blue.
      */
     dark(00, 105, 92);
    private final int r, g, b;
    ViewColor(final int r, final  int g, final  int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        }
    /**
     * red color.
     * @return r
     *          red
     */
    public int getRed() {
        return r;
        }
    /**
     * green color.
     * @return g
     *         green.
     */
    public int getGreen() {
        return g;
        }
    /**
     * blue color.
     * @return b
     *         blue
     */
    public int getBlue() { 
        return b;
        }
}
