package model.levels;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Model;
import model.entitiesutil.EntityDirection;

/**
 * The utility class to manage {@link GameLevel}s.
 */
public final class LevelUtils {

    private static final String PATH = "/levels/level";
    private static final String EXTENSION = ".json";

    private LevelUtils() {
    }

    /**
     * Returns the {@link InputStream} referring of a level.
     * 
     * @param levelNumber to be linked
     * @return the stream pointing to the level JSON file
     */
    public static InputStream getLevelStream(final int levelNumber) {
        return Object.class.getResourceAsStream(PATH + levelNumber + EXTENSION);
    }

    /**
     * Read the entities from the level JSON file and import them inside a new.
     * {@link GameLevel} instance
     * 
     * @param model       which requires to import the {@link GameLevel}
     * @param levelNumber to be imported
     * @return the level instance
     */
    public static GameLevel importLevel(final Model model, final int levelNumber) {
        final GameLevel level = new GameLevelImpl(model, levelNumber);
        final JSONParser parser = new JSONParser();
        
        JSONArray arrayParsed;
        try {
            // Read the level JSON file and import the entities
            arrayParsed = (JSONArray) parser.parse(new BufferedReader(new InputStreamReader(getLevelStream(levelNumber)))
                    .lines().collect(Collectors.joining("\n")));
            int minEntityX = -1, minEntityY = -1;
            for (final Object o : arrayParsed) {
                final JSONObject localEntity = (JSONObject) o;
                final int localX = ((Long) localEntity.get("x")).intValue();
                final int localY = ((Long) localEntity.get("y")).intValue();
                if (localX < 0 || localY < 0) {
                    model.getController().handleError("Can't load level " + levelNumber
                            + ": there are entities in less-than-zero positions (x or y)", true);
                }
                if (minEntityX == -1 || localX < minEntityX) {
                    minEntityX = localX;
                }
                if (minEntityY == -1 || localY < minEntityY) {
                    minEntityY = localY;
                }
            }
            for (final Object o : arrayParsed) {
                final JSONObject localEntity = (JSONObject) o; // For each JSONObject parse the input and puts it into
                                                               // the level
                final String type = (String) localEntity.get("type");
                final int x = ((Long) localEntity.get("x")).intValue() - minEntityX;
                final int y = ((Long) localEntity.get("y")).intValue() - minEntityY;
                final int width = ((Long) localEntity.get("width")).intValue();
                final int height = ((Long) localEntity.get("height")).intValue();
                final EntityDirection direction = Objects.nonNull(localEntity.get("direction"))
                        ? EntityDirection.valueOf((String) localEntity.get("direction"))
                        : null;
                level.addEntity(type, x, y, width, height, direction);
            }
            return level;
        } catch (final ParseException e) {
            model.getController()
                    .handleError("Can't open level " + levelNumber + " file (" + getLevelStream(levelNumber) + ")", true);
        }
        return null;
    }

}
