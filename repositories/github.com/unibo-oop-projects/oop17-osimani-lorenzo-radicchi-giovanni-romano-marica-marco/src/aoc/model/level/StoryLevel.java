package aoc.model.level;

import aoc.model.Model.GameStatus;
import aoc.model.entity.child.Children;
import aoc.model.level.spawner.ChildSpawner;
import aoc.utilities.Pair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This class represent a Story Level, an specialization of BasicLevel.
 */
public class StoryLevel extends BasicLevel {
    
    /**
     * Constructor for StoryLevel.
     * @param index
     * 		The level selected.
     */
    public StoryLevel(final int index) {
	super();
	final String levelPath = "res/levels/level" + index + ".json";
	this.setCurrentLevel(index);
	this.setSpawner(new ChildSpawner(loadLevel(levelPath), this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void win() {
	this.setStatus(GameStatus.WON);
    }
    
    /**
     * Loads the data of all the children that need to be
     * spawned in the selected level.
     * 
     * @return all the children in form of list.
     */
    private List<Pair<Map<Children, Integer>, Double>> loadLevel(final String path) {
	List<Pair<Map<Children, Integer>, Double>> map = Collections.emptyList();
	try (Reader r = new InputStreamReader(new FileInputStream(path))) {        
            final Gson gson = new GsonBuilder().create();
            map = gson.fromJson(r, new TypeToken<List<Pair<Map<Children, Integer>, Double>>>(){}.getType());
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	return map;
    }
    
}
