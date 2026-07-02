package ballblast.settings;

/**
 * 
 * 
 */
public enum Framerates {
    /**
     * 30 FPS.
     */
    FPS_30(30),
    /**
     * 60 FPS.
     */
    FPS_60(60),
    /**
     * 120 FPS.
     */
    FPS_120(120);

    private final int fps;

    /**
     * 
     * @param period the period used to generate selected fps.
     */
    Framerates(final int fps) {
        this.fps = fps;
    }

    /**
     * 
     * @return period to generate selected FPS.
     */
    public int getFPS() {
        return this.fps;
    }
}
