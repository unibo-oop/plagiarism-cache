package it.tbt.controller.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.Map;

import it.tbt.controller.SimpleLogger;

/**
 * Manage resource files in the user home directory.
 */
public class ResourceFilesManagerImpl extends ResourceManagerImpl implements ResourceFilesManager {
    private final String className = getClass().getName();
    private final Logger logger = SimpleLogger.getLogger(className);
    private final String userHomePath = System.getProperty("user.home");
    private final String osName = System.getProperty("os.name");
    private final String configFolderPath;

    /**
     * Default Constructor.
     * XDG Base Directory specification:
     * @see <a href="https://www.freedesktop.org/wiki/Software/xdg-user-dirs/">XDG User Dirs</a>
     * Windows (incomplete) specification:
     * @see <a href="https://learn.microsoft.com/en-us/windows/deployment/usmt/usmt-recognized-environment-variables
       #variables-that-are-recognized-only-in-the-user-context">recognized-environment-variables</a>
     * Mac/OS X specification:
     * @see <a href="https://developer.apple.com/library/archive/documentation/FileManagement/Conceptual/
       FileSystemProgrammingGuide/MacOSXDirectories/MacOSXDirectories.html">Mac OS X Directories</a>
     * @see <a href="https://developer.apple.com/library/archive/documentation/FileManagement/Conceptual/
       FileSystemProgrammingGuide/FileSystemOverview/FileSystemOverview.html">FileSystem Overview</a>
     */
    public ResourceFilesManagerImpl() {
        final Map<String, String> env = System.getenv();
        StringBuilder path = new StringBuilder(userHomePath); // default to userHomePath
        if (osName.toLowerCase(Locale.ENGLISH).contains("linux")) {
            // Properly handle XDG Base Directory specification
            final String val = env.get("XDG_CONFIG_HOME");
            if (val != null && val.trim().length() > 0) {
                path = new StringBuilder(val);
            } else {
                path.append(File.separator).append(".config");
            }
        } else if (osName.toLowerCase(Locale.ENGLISH).contains("windows")) {
            // Windows config directory
            final String val = System.getenv("LOCALAPPDATA");
            if (val != null && val.trim().length() > 0) {
                path = new StringBuilder(val);
            } else {
                path.append(File.separator).append("AppData")
                    .append(File.separator).append("Local");
            }
        } else if (
            osName.toLowerCase(Locale.ENGLISH).contains("mac")
            || osName.toLowerCase(Locale.ENGLISH).contains("os x")
        ) {
            // Mac/OS X config directory
            path.append(File.separator).append("Library/Application Support");
        }

        // Initialize configFolderPath
        this.configFolderPath = path + File.separator + "TurnBasedTunnels";

        // if configFolderPath does not exists, create it
        final File dir = new File(this.configFolderPath);
        if (!dir.exists() || !dir.isDirectory()) {
            try {
                Files.createDirectories(Paths.get(this.configFolderPath));
            } catch (IOException e) {
                logger.throwing(className, "ResourceFilesManagerImpl", e);
            }
        }
    }

    /**
     * Get the path for the folder containing the resource files.
     * @return folder path string
     */
    public String getConfigFolderPath() {
        return configFolderPath;
    }

    /**
     * Check if file exists in the config path.
     * @param filePath resource file path relative to the config directory
     * @return boolean
     */
    public boolean fileExistsInPath(final String filePath) {
        final File file = new File(configFolderPath + File.separator + filePath);
        return file.exists() && file.isFile();
    }

    /**
     * Check if the directory exists in the config path.
     * @param dirPath directory path relative to the config directory
     * @return boolean
     */
    public boolean dirExistsInPath(final String dirPath) {
        final File dir = new File(configFolderPath + File.separator + dirPath);
        return dir.exists() && dir.isDirectory();
    }

    /**
     * Check if the directory exists in the config path.
     * @param dirPath directory path relative to the config directory
     * @throws IOException
     */
    public void makeDirInPath(final String dirPath) throws IOException {
        final String path = configFolderPath + File.separator + dirPath;
        final File dir;
        if (path.endsWith("/")) {
            dir = new File(path);
        } else {
            dir = new File(path).getParentFile();
        }
        if (!dir.exists() || !dir.isDirectory()) {
            Files.createDirectories(Paths.get(dir.getPath()));
        }
    }

    /**
     * Get a BufferedInputStream to read from the file.
     * @param filePath resource file path relative to the config directory
     * @return BufferedInputStream
     */
    @Override
    protected BufferedInputStream getResourceInputStream(final String filePath) throws FileNotFoundException {
        final String path = configFolderPath + File.separator + filePath;
        return new BufferedInputStream(
            new FileInputStream(path)
        );
    }

    /**
     * Get the required resource file as a BufferedOutputStream.
     * @param filePath resource file path relative to the config directory
     * @return a BufferedOutputStream
     * @throws FileNotFoundException
     */
    protected BufferedOutputStream getResourceOutputStream(final String filePath) throws FileNotFoundException {
        final String path = configFolderPath + File.separator + filePath;
        return new BufferedOutputStream(
            new FileOutputStream(path)
        );
    }

    /**
     * Write the given string to the resource file.
     * @param filePath name of the resource file
     * @param content string that has to be written
     * @throws IOException
     */
    @Override
    public void writeResource(final String filePath, final byte[] content) throws IOException {
        try (BufferedOutputStream bufWriter = getResourceOutputStream(filePath)) {
            bufWriter.write(content);
        }
    }

    /**
     * Write the given InputStream to the resource file.
     * @param filePath name of the resource file
     * @param content InputStream that has to be written
     * @throws IOException
     */
    protected void writeResource(final String filePath, final InputStream content) throws IOException {
        try (BufferedOutputStream bufWriter = getResourceOutputStream(filePath)) {
            content.transferTo(bufWriter);
        }
    }
}
