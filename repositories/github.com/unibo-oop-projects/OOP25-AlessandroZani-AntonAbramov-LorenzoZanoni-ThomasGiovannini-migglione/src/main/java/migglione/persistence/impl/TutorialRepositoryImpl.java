package migglione.persistence.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import migglione.persistence.api.TutorialRepository;

/**
 * Class designed to interact with the tutorial.txt file.
 * 
 * <p>
 * It will see if any text is written in the file, if that is the case
 * then the method haveTutorialBeenSeen will return true and the tutorial
 * will not be shown on the start of the application to the user
 * 
 * <p>
 * Ideally the method writeOnTutorial can write anything, it's not important
 * what is written on the file, as long as it can be read easily
 */
public final class TutorialRepositoryImpl implements TutorialRepository {

    private static final Logger LOGGER = Logger.getLogger(TutorialRepositoryImpl.class.getName());
    private final Path path = Paths.get(System.getProperty("user.home"), ".migglione", "tutorial.txt");

    @Override
    public boolean haveTutorialBeenSeen() {
        if (!Files.exists(path)) {
            return false;
        }

        try (BufferedReader read = Files.newBufferedReader(path)) {
            final String s = read.readLine();
            return s != null && !s.isEmpty();
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error while reading file", e);
            return false;
        }
    }

    @Override
    public void writeOnTutorial() {
        try {
            final Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write("Ok");
            } 
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error in writing in file", e);
        }
    }
}
