package it.trashwarecesena.trustalodesktopclient.repository.test.adapter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.FileConnectionStrategy;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness.SimpleFileConnectionStrategy;

/**
 * A set of test checking the {@link SimpleFileConnectionStrategy} behaviour.
 * <p>
 * Testing revolves around the ability of creating a {@link File} granted the
 * correctness of the filepath, and the re-throwing of an {@link IOException} in
 * whatsoever case this is not being possible (wrong filepath, or lack of
 * privileges).
 * <p>
 * This test also ensures that the correct level of system independence is being
 * pursued, even if this point needs to be ran over different systems to be
 * proven right.
 * 
 * @author Manuel Bonarrigo
 */
@RunWith(JUnitPlatform.class)
@FixMethodOrder(MethodSorters.JVM)
@SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
public final class SimpleFileConnectionStrategyTest {

    private static final String CURRENT_LOCATION = System.getProperty("user.dir");
    private static final String PACKAGE_FILEPATH = CURRENT_LOCATION
            + Arrays.asList("res", "document-based-persistence", "xml", "test", "strategy-test").stream()
                    .collect(Collectors.joining(File.separator, File.separator, File.separator));

    /**
     * Testing the ability to pursue a FileConnectionStrategy.
     */
    @Test
    public void validSimpleFileConnectionStrategy() {
        final File newDirectory = new File(PACKAGE_FILEPATH);
        if (newDirectory.mkdir()) {
            final FileConnectionStrategy strategy = new SimpleFileConnectionStrategy(PACKAGE_FILEPATH);
            File strategyFile;
            try {
                strategyFile = strategy.createConnection();
                assertTrue(strategyFile.exists());
                assertTrue(strategyFile.isDirectory());
                assertTrue(strategyFile.canWrite());
                assertTrue(strategyFile.canRead());
                assertFalse(strategyFile.isFile());
            } catch (IOException e) {
                fail("Unable to connect to file");
            }
        } else {
            fail("Unable to create directory");
        }
        newDirectory.delete();

    }
}
