package ala.models;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;

/**
 *  This class contains a int that indicate your current level in the game and the list of all levels in the game.
 */
public final class CurrentLevelModel {
    /**
     * cosa rappresentano.
     */
    public enum LEVELS { UNO, DUE, TRE, QUATTRO, CINQUE, SEI }
    private static int currentLevel;
    private static List<Button> levelButtonList = new ArrayList<Button>();

    //Constructor
    /**
     * Set your current level to 0 (Level 1).
     * 
     * @param levelButtonList
     */
    public CurrentLevelModel(final List<Button> levelButtonList) {
        setLevelButtonList(levelButtonList);
        CurrentLevelModel.forceSetCurrentLevel0();
        unlock();
    }

    //Getter & Setter
    public static int getCurrentLevel() {
        return CurrentLevelModel.currentLevel;
    }

   public List<Button> getLevelButtonList() {
        return levelButtonList;
    }

    public static void setLevelButtonList(final List<Button> levelButtonList) {
        CurrentLevelModel.levelButtonList = levelButtonList;
    }

/**
     * Set your level to 0.
     */
    public static void forceSetCurrentLevel0() {
        CurrentLevelModel.currentLevel = 2 * 3;
    }

    /**
     * Update your level only if it higher than your current level.
     * 
     * @param updateLevel
     */
    public static void setCurrentLevel(final LEVELS updateLevel) {
        if (CurrentLevelModel.getCurrentLevel() <= updateLevel.ordinal()) {
            CurrentLevelModel.currentLevel = updateLevel.ordinal();
            System.out.println(updateLevel.ordinal());
        }
    }

    //Methods:
    //Unlock all buttons
        /**
         * Unlock the levels based on your current game progression.
         */
        public static void unlock() {
            CurrentLevelModel.levelButtonList.forEach(e -> {
                            if (getCurrentLevel() >= CurrentLevelModel.levelButtonList.indexOf(e)) {
                                e.setDisable(false);
                        } else {
                            e.setDisable(true);
                            }
                        });
                    }
}
