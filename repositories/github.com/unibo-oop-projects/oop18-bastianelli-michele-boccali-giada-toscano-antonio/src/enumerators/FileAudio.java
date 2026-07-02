package enumerators;

import common.CommonStrings;

public enum FileAudio {

    TRACK_1("Fireflies.mp3");

    private String fileName;

    private FileAudio(final String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return "music" + CommonStrings.FILE_SEPARATOR + fileName;
    }

}
