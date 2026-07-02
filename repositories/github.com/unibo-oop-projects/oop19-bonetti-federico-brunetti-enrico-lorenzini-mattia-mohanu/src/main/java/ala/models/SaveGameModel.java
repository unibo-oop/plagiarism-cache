package ala.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ala.models.CurrentLevelModel.LEVELS;

/**
 *  This class takes care of saving and loading data 
 *  It save and load a int that indicate the level you have unlocked in the game.
 */
public final class SaveGameModel {

        //file where to save current game
        private static File file;

        //Constructor
        /**
         * Create the file saved_game in the game folder where to save your progress.
         */
        public SaveGameModel() {
           setFile(new File("saved_game"));
        }
        //----------------------------------------------

        //Getters&Setters:
        public static File getFile() {
            return file;
        }

        public static void setFile(final File file) {
            SaveGameModel.file = file;
        }

            //Save game method
            /**
             * Save your progress in the file saved_game.
             */
            public void saveGame() {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(CurrentLevelModel.getCurrentLevel());
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Load game method
            /**
             * Load your progress from file saved_game, if there is no file it will set your progress to 0 (level 1) and throw FileNotFoundException.
             * 
             * @throws FileNotFoundException
             */
            private static BufferedReader br;
            public static void loadGame() throws FileNotFoundException {
                int i = 0;
                try {
                    br = new BufferedReader(new FileReader(file));
                    i = br.read();
                    switch (i) {
                        case 1: CurrentLevelModel.setCurrentLevel(LEVELS.UNO); break;
                        case 2: CurrentLevelModel.setCurrentLevel(LEVELS.DUE); break;
                        case 3: CurrentLevelModel.setCurrentLevel(LEVELS.TRE); break;
                        case 4: CurrentLevelModel.setCurrentLevel(LEVELS.QUATTRO); break;
                        case 4 + 1: CurrentLevelModel.setCurrentLevel(LEVELS.CINQUE); break;
                        case 4 + 2: CurrentLevelModel.setCurrentLevel(LEVELS.SEI); break;
                        default:break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
            }
      }
}
