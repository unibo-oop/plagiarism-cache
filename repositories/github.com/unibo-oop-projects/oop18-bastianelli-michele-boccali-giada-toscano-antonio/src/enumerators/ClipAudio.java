package enumerators;

public enum ClipAudio {

    TRACK_2("mario_02.wav");

    private String fileName;

    private ClipAudio(final String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return "resources/music/" + fileName;
    }
}
