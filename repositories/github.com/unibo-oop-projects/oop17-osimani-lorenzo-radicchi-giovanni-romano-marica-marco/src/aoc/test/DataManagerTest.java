package aoc.test;

import static org.junit.Assert.assertEquals;
import aoc.controller.GameConstants;
import aoc.controller.datamanager.DataManager;
import java.util.stream.Stream;
import org.junit.Test;



public class DataManagerTest {

    @Test
    public void dataTest() {
	final DataManager dataManager = DataManager.getDataManager();
	//Erasing data
	dataManager.eraseData();
	assertEquals(1, dataManager.getProgress());
	//Updating the progress
	dataManager.updateProgress();
	assertEquals(2, dataManager.getProgress());
	Stream.generate(() -> null).limit(GameConstants.N_LEVELS - 2)
	    .forEach(x -> dataManager.updateProgress());
	//Should print an error, can't progress more
	dataManager.updateProgress();
	assertEquals(GameConstants.N_LEVELS, dataManager.getProgress());
    }
}
