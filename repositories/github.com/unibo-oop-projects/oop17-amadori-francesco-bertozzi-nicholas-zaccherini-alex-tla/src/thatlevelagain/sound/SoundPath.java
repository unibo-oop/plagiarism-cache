package thatlevelagain.sound;



/**
 * 
 */
public enum SoundPath {
    /**
     * 
     */
    CATSCREAMPATH("/sound/meow.wav", 0),
    /**
     * 
     */
    JUMPPATH("/sound/jump.wav", 1),
    /**
     * 
     */
    DOORSQUEAKPATH("/sound/doorSqueak.wav", 2),
    /**
     * 
     */
    EVILLAUGHPATH("/sound/ahah.wav", 3),
    /**
     * 
     */
    CHOOSEPATH("/sound/choose.wav", 4),
    /**
     * 
     */
    CLICKYPATH("/sound/click.wav", 5),
    /**
     * 
     */
    ELECTRICITYPATH("/sound/scossa.wav", 6),
    /**
     * 
     */
    SELECTPATH("/sound/select.wav", 7),
    /**
     * 
     */
    DOGBARKPATH("/sound/wakeUp.wav", 8),
    /**
     * 
     */
    SNOREPATH("/sound/zzz.wav", 9),
    /**
     * 
     */
    RETRO("/sound/retroCar.wav", 10);

    private final String path;
    private final int position;

    SoundPath(final String pathFile, final int position) {
        this.path = pathFile;
        this.position = position;
    }

    /**
     * @return the String of the path of the file.
     */
    public String getPathSound() {
        return this.path;
    }

    /**
     * @return the position of the sound inside the List.
     */
    public int getPosition() {
        return this.position;
    }

}
