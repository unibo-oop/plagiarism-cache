package oop.lit.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import oop.lit.model.GameFactory;
import oop.lit.model.GameModel;
import oop.lit.model.game.Game;
import oop.lit.view.ViewRequests;

/**
 * Utility class for getting GameModels or GameFactories.
 */
public final class Games {
    private static final Logger LOGGER = LogManager.getLogger("GameInitLog");

    private static final String FILE_ERR_FNF_MSG = "Can't read provided file.";
    private static final String FILE_ERR_IO_MSG = "Error while reading provided file.";
    private static final String FILE_ERR_WRONG_MSG = "Provided file can't be used for this.";
    private static final String UNEXPECTED_ERR_MSG = "An unexpected error as occured.";
    private static final String NO_FACTORY_VALID_MSG = "(none of the gameFactory contained in the provided file can be instantiated)";

    private Games() {
    }

    /**
     * @param view
     *      the used view viewRequests class.
     * @return
     *      the default game.
     */
    public static GameModel getDefaultGame(final ViewRequests view) {
        return GameModel.DEFAULT_GAME_FACTORY.getGame(view);
    }

    /**
     * Load a game save from the provided file.
     * @param view
     *      the used view viewRequests class.
     * @param file
     *      a file containing a game save.
     * @return
     *      the loaded game.
     *
     * @throws IllegalArgumentException
     *      if something goes wrong while reading the file, or the file content is not a recognised save.
     */
    public static GameModel loadGameSave(final ViewRequests view, final File file) {
        try (ObjectInputStream stream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(file)))) {
            final Object readObject = stream.readObject();

            if (readObject instanceof Game<?, ?>) {
                return new GameModel((Game<?, ?>) readObject);
            } else {
                throw new IllegalArgumentException(FILE_ERR_WRONG_MSG);
            }
        } catch (FileNotFoundException e1) {
            throw new IllegalArgumentException(FILE_ERR_FNF_MSG);
        } catch (IOException e1) {
            throw new IllegalArgumentException(FILE_ERR_IO_MSG);
        } catch (ClassNotFoundException e1) {
            throw new IllegalArgumentException(FILE_ERR_WRONG_MSG);
        }
    }

    /**
     * Gets the game factories contained in the provided file.
     * @param file
     *      a file containing a lit game.
     * @return
     *      a list of all GameFactory contained in the provided jar.
     *
     * @throws IllegalArgumentException
     *      if something goes wrong while reading the file, or the file content is not a lit game.
     */
    public static Set<GameFactory> getGameFactories(final File file) {
        URLClassLoader urlCL;
        try {
            urlCL = new URLClassLoader(new URL[]{file.toURI().toURL()}, GameFactory.class.getClassLoader());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(UNEXPECTED_ERR_MSG);
        }
        final Reflections reflections = new Reflections(
          new ConfigurationBuilder().setUrls(
            ClasspathHelper.forClassLoader(urlCL)
          ).addClassLoader(urlCL)
        );

        final Set<Class<? extends GameFactory>> subClasses = reflections.getSubTypesOf(GameFactory.class);
        if (subClasses.isEmpty()) {
            throw new IllegalArgumentException(FILE_ERR_WRONG_MSG);
        }
        final Set<GameFactory> res = new HashSet<>();
        for (final Class<? extends GameFactory> subClass : subClasses) {
                try {
                    res.add(subClass.newInstance());
                } catch (InstantiationException e) {
                    LOGGER.warn("Class " + subClass.getName() + " can't be instantiated.");
                } catch (IllegalAccessException e) {
                    LOGGER.warn("Can't access class " + subClass.getName() + " constructor.");
                }
        }
        if (res.isEmpty()) {
            throw new IllegalArgumentException(FILE_ERR_WRONG_MSG + "\n" + NO_FACTORY_VALID_MSG);
        }
        return res;
    }
}
