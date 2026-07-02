package migglione.view.api.music;

/**
 * Enum used to store the names of the different tracks used.
 */
public enum MusicTracks {
    ENA("/soundtracks/ENA Dream BBQ.wav"),
    SMASH("/soundtracks/Trophy Gallery.wav"),
    DELTARUNE("/soundtracks/Air Waves.wav");

    private final String track;

    MusicTracks(final String track) {
        this.track = track;
    }

    /**
     * Simple getter.
     * 
     * @return the path of the track chosen.
     */
    public String getTrackPath() {
        return this.track;
    }
}
