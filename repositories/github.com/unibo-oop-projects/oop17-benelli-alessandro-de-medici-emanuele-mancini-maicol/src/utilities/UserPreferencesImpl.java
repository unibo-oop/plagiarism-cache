package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class that saves and loads user's preferences on file.
 *
 */
public final class UserPreferencesImpl implements UserPreferences {

    private static final UserPreferencesImpl SINGLETON = new UserPreferencesImpl();
    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String APP_DIRECTORY = ".watcha-way";
    private static final String DEFAULT_FILE = "settings";
    private static final String DEFAULT_EXT = ".properties";
    private static final File FILE = new File(
            HOME + SEPARATOR + APP_DIRECTORY + SEPARATOR + DEFAULT_FILE + DEFAULT_EXT);

    @Override
    public String loadProperty(final String key) throws IOException {
        this.checkFile();

        final Properties properties = new Properties();

        try {
            final FileInputStream fileInputStream = new FileInputStream(FILE);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            throw new IOException("Unable to load user settings");
        }

        return properties.getProperty(key);
    }

    @Override
    public void saveProperty(final String key, final String value) throws IOException {
        this.checkFile();

        final Properties properties = new Properties();

        properties.setProperty(key, value);

        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(FILE);
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new IOException("Unable to save user settings");
        }
    }

    // Checks if the file and his parent directory exists.
    private void checkFile() throws IOException {
        if (!FILE.exists()) {
            final File parent = new File(HOME + SEPARATOR + APP_DIRECTORY);
            if (!parent.exists()) {
                final boolean ret = parent.mkdir();
                if (ret) {
                    this.createFile();
                }
            } else {
                this.createFile();
            }
        }
    }

    // Creates the file.
    private void createFile() throws IOException {
        try {
            FILE.createNewFile();
        } catch (IOException e) {
            throw new IOException("An error occurred during the creation of the user settings' file");
        }
    }

    /**
     * Returns an instance of UserPreferencesImpl.
     * 
     * @return an instance of UserPreferencesImpl
     */
    public static UserPreferencesImpl get() {
        return SINGLETON;
    }
}
