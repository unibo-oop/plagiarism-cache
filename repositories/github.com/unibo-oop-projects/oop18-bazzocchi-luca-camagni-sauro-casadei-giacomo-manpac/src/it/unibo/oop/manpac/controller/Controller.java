package it.unibo.oop.manpac.controller;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.model.ManpacImpl;
import it.unibo.oop.manpac.model.ModelForController;
import it.unibo.oop.manpac.model.score.PointsImpl;
import it.unibo.oop.manpac.view.screens.menu.ScreensMessenger;
import it.unibo.oop.manpac.model.score.Points;

/**
 * This class represent the Controller of the all game.
 */
public final class Controller implements ControllerObserver.OnlyGame, ControllerObserver.OnlySettings {

    // MODEL of the all game
    private final ModelForController model;

    // VIEW of the all game, in particular the active screen
    private Optional<ScreensMessenger> actualScreenOfView;

    // manager for the load/save the high score
    private final FileManager<Integer> fileSettingsManager;

    /**
     * The constructor of CONTROLLER.
     */
    public Controller() {
        this.model = new ManpacImpl();
        this.fileSettingsManager = new FileManager.FileManagerAdapter();
        this.actualScreenOfView = Optional.empty();

        requestResetGame();
    }

    @Override
    public void setCurrentObservable(final ScreensMessenger view) {
        if (!checkerNullParameter(view)) {
            throw new IllegalArgumentException("The view passed as input is null!");
        }
        if (this.actualScreenOfView.isPresent()) {
            this.actualScreenOfView.get().stopControllerObserving();
        }
        this.actualScreenOfView = Optional.of(view);
        this.actualScreenOfView.get().startControllerObserving(this);
    }

    @Override
    public void requestContinueGame() {
        this.model.resetAndContinue();
    }

    @Override
    public void requestResetGame() {
        this.model.resetGame();

        // requires to read the high score from file and set it in the model
        this.model.setHighScore(this.fileSettingsManager.readHighScore());
    }

    /**
     * @throws IllegalStateException Launch this exception if this method is called
     *                               up when the game is started (Pac-Man has
     *                               already eaten pills); call the game reset
     *                               first<br>
     */
    @Override
    public void requestSetNumberPills(final int numberPills) throws IllegalStateException {
        this.model.setPills(numberPills);
    }

    @Override
    public void requestResetBothScore() {
        this.model.resetScorePoints();
//        this.requestSaveHighScore();
    }

    @Override
    public void requestSaveHighScore() {
        this.fileSettingsManager.writeHighScore(this.model.getHighScore());
    }

    // ONLY SETTINGS VIEW ----------------------------------------------------------------
    /**
     * @throws IllegalStateException Launch this exception if this method is called
     *                               from a type of view wrong.
     */
    @Override
    public void requestHighScore() throws IllegalStateException {
        checkerViews(ScreensMessenger.TheSettings.class);
        final Points<Integer> highScore = this.model.getHighScore();
        ((ScreensMessenger.TheSettings) this.actualScreenOfView.get()).sendHighScore(highScore.getValue());
    }
    // -----------------------------------------------------------------------------------

    // ONLY GAME VIEW --------------------------------------------------------------------
    /**
     * @throws IllegalStateException Launch this exception if this method is called
     *                               from a type of view wrong.
     */
    @Override
    public void requestBothScore() throws IllegalStateException {
        checkerViews(ScreensMessenger.TheGame.class);
        final Points<Integer> highScore = this.model.getHighScore();
        final Points<Integer> currentScore = this.model.getCurrentScore();
        ((ScreensMessenger.TheGame) this.actualScreenOfView.get()).sendScore(currentScore.getValue(), highScore.getValue());
    }

    /**
     * @throws IllegalStateException Launch this exception if this method is called
     *                               from a type of view wrong.
     */
    @Override
    public void requestResetCurrentScore() throws IllegalStateException {
        checkerViews(ScreensMessenger.TheGame.class);
        this.model.resetCurrentScore();
    }

    /**
     * @throws IllegalStateException Launch this exception if this method is called
     *                               from a type of view wrong.
     */
    @Override
    public void requestPacManLives() {
        checkerViews(ScreensMessenger.TheGame.class);
        final int lives = this.model.getLives();
        ((ScreensMessenger.TheGame) this.actualScreenOfView.get()).sendPacManLives(lives);
    }

    /**
     * @throws IllegalStateException Launch this exception if this method is called
     *                               from a type of view wrong.
     * @implNote If the parameter is null, this method does nothing.
     */
    @Override
    public void pacmanInputDirection(final Directions newDirections) throws IllegalStateException {
        if (checkerNullParameter(newDirections)) {
            checkerViews(ScreensMessenger.TheGame.class);
            final Action action = this.model.inputDirection(newDirections);
            ((ScreensMessenger.TheGame) this.actualScreenOfView.get()).sendPacManDirectionWithAction(action);
        }
    }

    /**
     * @throws IllegalStateException Launch this exception if this method is called
     *                               from a type of view wrong.
     * @implNote If the parameter is null, this method does nothing.
     */
    @Override
    public void pacmanIsCollidingToEntity(final Entity entity) throws IllegalStateException {
        if (checkerNullParameter(entity)) {
            checkerViews(ScreensMessenger.TheGame.class);
            final Action action = this.model.pacmanCollision(entity);
            ((ScreensMessenger.TheGame) this.actualScreenOfView.get()).sendPacManAction(action);
        }
    }

    /**
     * @throws IllegalStateException Launch this exception if this method is called
     *                               from a type of view wrong.
     * @implNote If the parameter is null, this method does nothing.
     */
    @Override
    public void phantomIsCollidingToEntity(final Entity phantomName, final Entity entity) throws IllegalStateException {
        if (checkerNullParameter(phantomName, entity)) {
            checkerViews(ScreensMessenger.TheGame.class);
            final Action action = this.model.phantomCollision(phantomName, entity);
            ((ScreensMessenger.TheGame) this.actualScreenOfView.get()).sendPhantomAction(phantomName, action);
        }
    }

    /**
     * @throws IllegalStateException Launch this exception if this method is called
     *                               from a type of view wrong.
     */
    @Override
    public void stopTimerPower() throws IllegalStateException {
        checkerViews(ScreensMessenger.TheGame.class);
        this.model.stopPower();
    }
    // -----------------------------------------------------------------------------------

    /**
     * This method checks if the currently set view is the correct one to execute an
     * instruction. If it is not, an IllegalStateException is thrown.
     */
    private void checkerViews(final Class<? extends ScreensMessenger> typeOfScreen) throws IllegalStateException {
        if (!typeOfScreen.isInstance(this.actualScreenOfView.orElseThrow(() -> new IllegalStateException("The controller is not observing the view!")))) {
            throw new IllegalStateException(
                    "Operation not allowed: the view that made the request does not have permission to execute this method.");
        }
    }

    /**
     * Null checker for indefinite number of parameters.
     * @param params List of parameters to be checked.
     * @return True if all parameters are not null.
     */
    private boolean checkerNullParameter(final Object... params) {
        return Arrays.stream(params).allMatch(param -> Objects.nonNull(param));
    }


    /**
     * Settings file manager: it is responsible for uploading/saving the settings
     * and high score to file.
     *
     * @param <X> Type of score ({@link Points}) of high score
     */
    private interface FileManager<X extends java.io.Serializable> {

        /**
         * Reads the highest score from the file.
         * 
         * @return The score ({@link Points}) of high score
         */
        Points<X> readHighScore();

        /**
         * Writes the highest score into file.
         * 
         * @param point The high score ({@link Points}) to save on file
         */
        void writeHighScore(Points<X> point);

        //other method for future settings

        /**
         * Implementation of {@link FileManager} which already uses the existing
         * {@link FileResources} for the reading/writing scores of type int (using the
         * pattern Adapter).
         */
        class FileManagerAdapter implements FileManager<Integer> {

            // the default value of high score
            private static final int HIGHSCORE_START_DEFAULT = 0;

            private final FileResources intPointsSaver;

            private Optional<Points<Integer>> highScore;
//        private Optional<SettingsFile> settingsFile;

            /**
             * Constructor of Adapter.
             */
            FileManagerAdapter() {
                this.intPointsSaver = new FileResourcesImpl();
                this.highScore = Optional.empty();
//            this.settingsFile = Optional.empty();
            }

            @Override
            public Points<Integer> readHighScore() {
                if (!this.highScore.isPresent()) {
                    this.highScore = Optional
                            .of(new PointsImpl<>(this.intPointsSaver.startResources(HIGHSCORE_START_DEFAULT)));
                }
                return this.highScore.get().getCopy();
            }

            @Override
            public void writeHighScore(final Points<Integer> point) {
                this.highScore = Optional.ofNullable(point);
                this.intPointsSaver
                        .setHighScore(this.highScore.orElse(new PointsImpl<>(HIGHSCORE_START_DEFAULT)).getValue());
            }

        }
    }
}
