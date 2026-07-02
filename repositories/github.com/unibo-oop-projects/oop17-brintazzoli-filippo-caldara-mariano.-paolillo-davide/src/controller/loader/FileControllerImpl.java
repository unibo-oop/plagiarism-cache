package controller.loader;

import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import controller.levels.Levels;
import model.Model;
import model.utility.Pair;

/**
 * Concrete implementation of {@link FileController} interface.
 */
public class FileControllerImpl implements FileController {

    private static final String PATH = "/res/levels/";
    private static final String EXTENSION = ".json";
    private final JSONParser json;
    private final Model world;

    /**
     * Instance a new FileController.
     * 
     * @param world
     *            the {@link Model} of the game.
     */
    public FileControllerImpl(final Model world) {
        super();
        this.world = world;
        this.json = new JSONParser();
    }

    @Override
    public final void loadLevel(final Levels level) {
        Object obj;
        JSONObject jsonObject;
        try {
            obj = this.json.parse(new InputStreamReader(getClass().getResourceAsStream(PATH + level.getName() + EXTENSION)));
            jsonObject = (JSONObject) obj;
            final JSONObject playerFields = (JSONObject) jsonObject.get("playerTank");
            final JSONObject enemyFields = (JSONObject) jsonObject.get("enemyTank");

            this.world.configPlayerTank(
                    new Pair<Double, Double>((Double) playerFields.get(InitialStateFields.POSX.getName()),
                            (Double) playerFields.get(InitialStateFields.POSY.getName())),
                    ((Long) playerFields.get(InitialStateFields.LIFES.getName())).intValue());
            this.world.configEnemyTank(
                    new Pair<Double, Double>((Double) enemyFields.get(InitialStateFields.POSX.getName()),
                            (Double) enemyFields.get(InitialStateFields.POSY.getName())),
                    ((Long) enemyFields.get(InitialStateFields.LIFES.getName())).intValue(),
                    (Double) enemyFields.get(InitialStateFields.SPEED.getName()),
                    (Double) enemyFields.get(InitialStateFields.P_SPEED.getName()));

        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }

}
