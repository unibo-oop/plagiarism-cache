package it.unibo.aurea.model.api;

/**
 * Represents the possible characters in the game.
 * Each of them has a path of an image and a specific positive influence on one parameter:
 * -STUDENT ->  STUDENTS
 * -MUM -> REPUTATION
 * -PROFESSOR -> PROFESSORS
 * -BUSINESSMAN -> FINANCES
 */
public enum CharacterType {
    STUDENT("/student.png"),
    MUM("/mum.png"),
    PROFESSOR("/professor.png"),
    BUSINESSMAN("/businessman.png");

    private final String imagePath;

    CharacterType(final String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the path of the image in this project.
     */
    public String getImagePath() {
        return imagePath;
    }
}
