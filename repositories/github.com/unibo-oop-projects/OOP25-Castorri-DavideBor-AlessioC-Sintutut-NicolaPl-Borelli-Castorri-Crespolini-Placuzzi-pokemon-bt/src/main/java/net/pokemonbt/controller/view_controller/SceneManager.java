package net.pokemonbt.controller.view_controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.pokemonbt.controller.battle.BattleEnvironment;
import net.pokemonbt.controller.resources.GameSession;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.Refreshable;
import net.pokemonbt.view.LetterboxPane;

/**
 * Main manager and loader of the javafx scenes.
 */
public final class SceneManager {

    public static final String POKE_INFO_PATH = "/layouts/pokemonInfo.fxml";
    public static final String BATTLE_PATH = "/layouts/battle.fxml";
    private static final int MIN_HEIGHT = 300;
    private static final int MIN_WIDTH = 400;
    private static Stage stage;

    private static Map<String, Parent> cache = new HashMap<>();
    private static Map<String, Object> controllerCache = new HashMap<>();

    private SceneManager() {
    }

    /**
     * Sets up the new stage.
     * 
     * @param newStage the stage to be set.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_STATIC_REP2", justification = "Stage needs to be changed globally for scene changing.")
    public static void setStage(final Stage newStage) {
        stage = newStage;
        stage.setTitle("Pokemon bt");
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
    }

    /**
     * Closes the application.
     */
    public static void closeStage() {
        stage.close();
    }

    /**
     * Sets up the scene using the default no-args constructor for the controller.
     * 
     * @param scenePath the path of the scene to be set.
     */
    public static void setScene(final String scenePath) {
        loadSceneInternal(scenePath, null);
    }

    /**
     * Sets up the scene and passes a BattleEnvironment to the controller's
     * constructor.
     * 
     * @param scenePath the path of the scene to be set.
     * 
     * @param be        the BattleEnvironment to pass to the controller.
     */
    public static void setSceneWithBE(final String scenePath, final BattleEnvironment be) {
        loadSceneInternal(scenePath, type -> {
            try {
                return type.getConstructor(BattleEnvironment.class).newInstance(be);
            } catch (final NoSuchMethodException e) {
                try {
                    return type.getDeclaredConstructor().newInstance();
                } catch (final SecurityException | InstantiationException | IllegalAccessException
                        | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException ex) {
                    ex.addSuppressed(e);
                    throw new IllegalArgumentException(ex);
                }
            } catch (final SecurityException | InstantiationException | IllegalAccessException
                    | InvocationTargetException e) {
                throw new IllegalArgumentException("Error during the BattleController instancing", e);
            }
        });
    }

    /**
     * Handles the change of scene.
     * 
     * @param scenePath         the path of the scene to be set.
     * @param controllerFactory the factory for controller initialization.
     */
    private static void loadSceneInternal(final String scenePath, final Callback<Class<?>, Object> controllerFactory) {
        try {
            final Parent root;
            final Object controller;

            if (cache.containsKey(scenePath)) {
                System.out.println("Recovering scene from cache: " + scenePath); // NOPMD
                root = cache.get(scenePath);
                controller = controllerCache.get(scenePath);
            } else {
                System.out.println("Loading new scene: " + scenePath); // NOPMD
                final FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(scenePath));

                if (controllerFactory != null) {
                    loader.setControllerFactory(controllerFactory);
                }

                root = loader.load();
                controller = loader.getController();

                cache.put(scenePath, root);
                controllerCache.put(scenePath, controller);
            }

            if (controller instanceof Refreshable) {
                ((Refreshable) controller).refresh();
            }

            if (stage.getScene() == null) {
                final LetterboxPane letterbox = new LetterboxPane(root);
                stage.setScene(new Scene(letterbox));
            } else {
                final Parent currentRoot = stage.getScene().getRoot();

                if (currentRoot instanceof LetterboxPane) {
                    ((LetterboxPane) currentRoot).setView(root);
                } else {
                    stage.getScene().setRoot(new LetterboxPane(root));
                }
            }

            stage.show();

        } catch (final IOException e) {
            throw new IllegalStateException(scenePath, e);
        }
    }

    /**
     * Initializes the battle enviroment, the turn controller and loads the battle
     * scene.
     * 
     * @param enemyTeam the team of the enemy.
     */
    public static void startBattle(final List<Pokemon> enemyTeam) {
        clearAllCache();
        setSceneWithBE(BATTLE_PATH, new BattleEnvironment(GameSession.getCopyOfTeam(), enemyTeam,
                GameSession.getCopyOfTeam().getFirst().getID(), enemyTeam.getFirst().getID()));
    }

    /**
     * Removes the specified scene from cache and controller cache.
     * 
     * @param scenePath where to find the fxml layout.
     */
    public static void clearFromCache(final String scenePath) {
        if (cache.containsKey(scenePath)) {
            cache.remove(scenePath);
            controllerCache.remove(scenePath);
        }
    }

    /**
     * Clears controller and scene cache.
     */
    public static void clearAllCache() {
        cache.clear();
        controllerCache.clear();
    }

    /**
     * @return true if the battle scene is already loaded in cache, false otherwise.
     */
    public static boolean isInBattle() {
        Objects.requireNonNull(cache);
        return cache.containsKey("/layouts/battle.fxml");
    }
}
