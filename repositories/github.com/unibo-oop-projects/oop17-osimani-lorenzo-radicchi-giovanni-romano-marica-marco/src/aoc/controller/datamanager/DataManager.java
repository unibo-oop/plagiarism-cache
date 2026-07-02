package aoc.controller.datamanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import aoc.controller.GameConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.Optional;

/**
 * This class represents the DataManager object ,using the Singleton Pattern.
 * It manages the save file of the game.
 */
public class DataManager {
    
    /**
     * Save file path.
     */
    private static final String SEPARATOR = System.getProperty("file.separator"); 
    private static final String SAVE_DIR = System.getProperty("user.home") + SEPARATOR + ".aoc";
    private static final String SAVEPATH = SAVE_DIR + SEPARATOR + "savefile.json";
    
    private int progress;
    private final File directory = new File(SAVE_DIR);
    private final File saveFile = new File(SAVEPATH);
    
    /**
     * It contains the SINGLETON, initialized at first use.
     */
    private static class LazyHolder {
        /**
         * Contains the reference to the Singleton.
         */
        private static final DataManager SINGLETON = new DataManager();
    }
    
    /**
     * DataManager Constructor.
     */
    private DataManager() {
	try {
	    if (saveFile.exists()) {
		progress = this.readProgress().get();
		this.checkSaveData();
	    } else {
		if (!directory.exists()) {
		    checkCondition(directory.mkdir(),"Couldn't create the save folder");
		}
		checkCondition(saveFile.createNewFile(), "Couldn't create a new save file");
		this.eraseData();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * Get the reference to the SINGLETON.
     * @return the SINGLETON
     */
    public static DataManager getDataManager() {
    	return LazyHolder.SINGLETON;
    }
    
    /**
     * {@inheritDoc}
     */
    public int getProgress() {
	return progress;
    }

    /**
     * {@inheritDoc}
     */
    public void eraseData() {
	progress = 1;
	this.updateSaveFile();
    }
    
    /**
     * Updates the progress in the Story Mode.
     */
    public void updateProgress() {
	try {
	    checkCondition(progress < GameConstants.N_LEVELS, "Can't progress further");
	    progress++;
	    this.updateSaveFile();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    
    /**
     * Read the savefile
     * @return an Optional representing the number of levels unlocked:
     *          if the Optional is empty, the savefile was not found.
     */
    private Optional<Integer> readProgress() {
	try (Reader r = new InputStreamReader(new FileInputStream(SAVEPATH))) {        
            final Gson gson = new GsonBuilder().create();
            final int index = gson.fromJson(r, new TypeToken<Integer>() {}.getType());
            return Optional.of(index);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	return Optional.empty();
    }
    
    /**
     * Updates the savefile with the current value of progress.
     */
    private void updateSaveFile() {
	try (Writer writer = new FileWriter(SAVEPATH)) {
            final Gson gson = new GsonBuilder().create();
            gson.toJson(this.progress, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method check if the save file is corrupted.
     * @throws IOException
     *          It is thrown if the save file is corrupted
     */
    private void checkSaveData() throws IOException {
	if (this.progress > GameConstants.N_LEVELS || this.progress < 1) {
	    this.eraseData();    
	    throw new IOException("The savefile is corrupted. Creating a new file");
	}	
    }
    
    /**
     * This method check if a condition is true;
     * if not it throws an Exception with the message passed as argument.
     * @param supplier
     *          the condition to check.
     * @param message
     *          the String of the eventual error
     * @throws IllegalArgumentException
     *          It is thrown in case the supplier is false.
     */
    private void checkCondition(final boolean supplier,final String message) throws IllegalArgumentException {
        if (!supplier) {
                throw new IllegalArgumentException(message);
        }
    }
}
