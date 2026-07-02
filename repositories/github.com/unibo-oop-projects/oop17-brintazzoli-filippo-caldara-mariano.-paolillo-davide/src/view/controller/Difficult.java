package view.controller;

/**
 * Enumeration that describe the difficult of the game.
 * The difficult depend to the frequency of shot of the enemy tank.
 */
public enum Difficult {

    /**
     * The easy difficult of the game.
     */
    EASY("Easy") {

        @Override
        public double getTimeShot() {
            return TimeToShot.EASY_TIME.getTime();
        }

    },

    /**
     * The medium diffiuclt of the game.
     */
    MEDIUM("Medium") {

        @Override
        public double getTimeShot() {
            return TimeToShot.MEDIUM_TIME.getTime();
        }

    },

    /**
     * The hard difficult of the game.
     */
    HARD("Hard") {

        @Override
        public double getTimeShot() {
            return TimeToShot.HARD_TIME.getTime();
        }

    };

    private String difficultName;

    /**
     * Private constructor.
     * 
     * @param difficultName
     *            the name of the difficult.
     */
    Difficult(final String difficultName) {
        this.difficultName = difficultName;
    }

    /**
     * Getter of the difficult name.
     * 
     * @return the difficult name.
     */
    public String getName() {
        return this.difficultName;
    }

    /**
     * Getter of the frequency time of shot of the enemy {@link Tank} according to the enum {@link TimeToShot}.
     * 
     * @return the period of shot in milliseconds.
     */
    public abstract double getTimeShot();

}
