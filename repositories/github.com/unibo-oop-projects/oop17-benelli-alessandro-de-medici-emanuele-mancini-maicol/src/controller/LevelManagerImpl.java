package controller;

import model.level.LevelImpl;
import utilities.Utilities;
import view.levels.LevelFive;
import view.levels.LevelFour;
import view.levels.LevelOne;
import view.levels.LevelThree;
import view.levels.LevelTwo;

/**
 * Class that manages which level has to be displayed and which one has to be
 * reset.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class LevelManagerImpl implements LevelManager {

    private static final LevelManagerImpl SINGLETON = new LevelManagerImpl();
    private int levelNumber;

    /**
     * Constructor.
     */
    private LevelManagerImpl() {
        this.levelNumber = Utilities.FIRST_LEVEL;
    }

    @Override
    public LevelImpl chooseLevel() {
        if (this.getLevelNumber() == 1) {
            LevelOne.get().setStepSoundPath("/songs/stepSound.mp3");
            LevelOne.get().setMusicPath("/songs/levelOneMusic.mp3");
            return LevelOne.get();
        }
        if (this.getLevelNumber() == 2) {
            LevelTwo.get().setStepSoundPath("/songs/stepGrass.mp3");
            LevelTwo.get().setMusicPath("/songs/levelTwoMusic.mp3");
            return LevelTwo.get();
        }
        if (this.getLevelNumber() == 3) {
            LevelThree.get().setStepSoundPath("/songs/stepSnow.mp3");
            LevelThree.get().setMusicPath("/songs/levelThreeMusic.mp3");
            return LevelThree.get();
        }
        if (this.getLevelNumber() == 4) {
            LevelFour.get().setStepSoundPath("/songs/stepSound.mp3");
            LevelFour.get().setMusicPath("/songs/levelFourMusic.mp3");
            return LevelFour.get();
        }
        if (this.getLevelNumber() == 5) {
            LevelFive.get().setStepSoundPath("/songs/stepSound.mp3");
            LevelFive.get().setMusicPath("/songs/levelFiveMusic.mp3");
            return LevelFive.get();
        }
        return LevelOne.get();
    }

    @Override
    public void resetLevel() {
        if (this.getLevelNumber() == 1) {
            LevelOne.get().resetGrid();
            LevelOne.get().resetCurrentLevel();
        }
        if (this.getLevelNumber() == 2) {
            LevelTwo.get().resetGrid();
            LevelTwo.get().resetCurrentLevel();
        }
        if (this.getLevelNumber() == 3) {
            LevelThree.get().resetGrid();
            LevelThree.get().resetCurrentLevel();
        }
        if (this.getLevelNumber() == 4) {
            LevelFour.get().resetGrid();
            LevelFour.get().resetCurrentLevel();
        }
        if (this.getLevelNumber() == 5) {
            LevelFive.get().resetGrid();
            LevelFive.get().resetCurrentLevel();
        }
    }

    @Override
    public int getLevelNumber() {
        return this.levelNumber;
    }

    @Override
    public void setLevelNumber(final int levelNumber) {
        this.levelNumber = levelNumber;
    }

    @Override
    public void nextLevel() {
        this.levelNumber++;
    }

    @Override
    public void resetLevelNumber() {
        this.levelNumber = Utilities.FIRST_LEVEL;
    }

    /**
     * Returns an instance of LevelManagerImpl.
     * 
     * @return an instance of LevelManagerImpl
     */
    public static LevelManagerImpl get() {
        return SINGLETON;
    }
}
