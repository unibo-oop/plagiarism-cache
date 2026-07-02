package sound;

/**
 * Enum to menage all the addresses of the sounds.
 *
 */
public enum SoundsAddress {
    /**
     * 
     */
    MAINSOUND("/seaSound.wav"),
    /**
     * 
     */
    MAINSOUND2("/seaSound2.wav"),
    /**
     * 
     */
    DEEPSOUND("/booble.wav"),
    /**
     * 
     */
    PICKTREASURE("/rod.wav"),
    /**
     * 
     */
    RELEASETREASURE("/rod2.wav"),
    /**
     * 
     */
    WINNER("/applause.wav"),
    /**
     * 
     */
    DICESROLL("/dices.wav"),
    /**
     * 
     */
    NEXTPLAYER("/booble2.wav");

    private String soundAddress;

    SoundsAddress(final String soundAddress) {
        this.soundAddress = soundAddress;
    }

    /**
     * @return A string rapresentation of image address.
     */
    public String getAddress() {
        return soundAddress;
    }
}

