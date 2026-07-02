package com.biaren.utility;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.biaren.sportclubmanager.utility.enums.MavenDirLayoutFolder;

/**
 * Utility class for paths, include some static method for retrieve a certain directory
 * in project, in accord to specific directory structure used.
 * @author nbrunetti
 *
 */
public final class BiarenPathHandler {

    private final static int WINDOWS_SO = 1;
    private final static int UNIX_SO = 2;
    private final static String WIN_SO_PREFIX = "windows";
    
    private BiarenPathHandler() {
       
    }
    
    /**
     * Renaming the method.
     * @return root dir path
     */
    public static String getRootDirPath() {
        return System.getProperty("user.dir");
    }
    
    /**
     * Useful method to convert windows file separator to unix file separator.
     * @param path {@link String} to convert
     * @return String path with correct url
     */
    public static String winToUnixUri(final String path) {
        return path.replace("\\", "/");
    }
    
    /**
     * Useful method to convert unix file separator to windows file separator.
     * @param path {@link String} to convert
     * @return String path with correct url
     */
    public static String unixToWinUri(final String path) {
        return path.replace("/", "\\");
    }
    
    /**
     * Create and return the main directory path with the utility method in {@link Paths}.
     * @return a {@link Path} that represent the main directory
     */
    public static Path getMainPath() {
        return Paths.get(MavenDirLayoutFolder.SRC_DIR.toString(), MavenDirLayoutFolder.MAIN_DIR.toString());
    }
    
    /**
     * Get the main path in {@link String}.
     * @return a {@link String} that represent main directory path
     */
    public static String getMainPathString() {
        return getMainPath().toString();
    }
    
    /**
     * Get the absolute path of main folder in {@link String}.
     * @return a {@link String} that represent the absolute path of main directory
     */
    public static String getMainAbsolutePathString() {
        return getMainPath().toAbsolutePath().toString();
    }
    
    /**
     * Create and return the resources directory path with the utility method in {@link Paths}.
     * Use the main path and concatenate the right directory name, specifies in {@link MavenDirLayoutFolder}.
     * @return a {@link Path} that represent the resources directory
     */
    public static Path getResourcesPath() {
        return Paths.get(getMainPathString(), MavenDirLayoutFolder.RESOURCES_DIR.toString().toString());
    }
    
    /**
     * Get the resources directory path in {@link String}.
     * @return a {@link String} that represent the resources directory
     */
    public static String getResourcesPathString() {
        return getResourcesPath().toString();
    }
    
    /**
     * Get the absolute path of resources folder in {@link String}.
     * @return a {@link String} that represent the absolute path of resources directory
     */
    public static String getResourcesAbsolutePathString() {
        return getResourcesPath().toAbsolutePath().toString();
    }
    
    /**
     * Create and return the main/java directory path with the utility method in {@link Paths}.
     * Use the main path and concatenate the right directory name, specifies in {@link MavenDirLayoutFolder}.
     * @return a {@link Path} that represents the main/java directory
     */
    public static Path getMainJavaPath() {
        return Paths.get(getMainPathString(), MavenDirLayoutFolder.JAVA_DIR.toString());
    }
    
    /**
     * Get the main/java path in {@link String}.
     * @return a {@link String} that represent the main/java directory
     */
    public String getMainJavaPathString() {
        return getMainJavaPath().toString();
    }
    
    /**
     * Get the absolute path of main/java directory in {@link String}.
     * @return a {@link String} that represent the absolute path of main/java directory
     */
    public String getMainJavaAbsolutePath() {
        return getMainJavaPath().toAbsolutePath().toString();
    }
    
    /**
     * Create and return the resources directory path with the utility method in {@link Paths}.
     * Use the main path and concatenate the right directory name, specifies in {@link MavenDirLayoutFolder}.
     * @return a {@link Path} that represent the resources directory
     */
    public static Path getCsvResourcePath() {
        return Paths.get(getResourcesPathString(), MavenDirLayoutFolder.CSV_DIR.toString());
    }
    
    /**
     * Get the resources directory path in {@link String}.
     * @return a {@link String} that represent the resources directory
     */
    public static String getCsvResourcesPathString() {
        return getCsvResourcePath().toString();
    }
    
    /**
     * Get the absolute path of resources folder in {@link String}.
     * @return a {@link String} that represent the absolute path of resources directory
     */
    public static String getCsvResourcesAbsolutePathString() {
        return getCsvResourcePath().toAbsolutePath().toString();
    }
    
    /**
     * Create and return the resources directory path with the utility method in {@link Paths}.
     * Use the main path and concatenate the right directory name, specifies in {@link MavenDirLayoutFolder}.
     * @return a {@link Path} that represent the resources directory
     */
    public static Path getJsonResourcePath() {
        return Paths.get(getResourcesPathString(), MavenDirLayoutFolder.JSON_DIR.toString());
    }
    
    /**
     * Get the resources directory path in {@link String}.
     * @return a {@link String} that represent the resources directory
     */
    public static String getJsonResourcesPathString() {
        return getJsonResourcePath().toString();
    }
    
    /**
     * Get the absolute path of resources folder in {@link String}.
     * @return a {@link String} that represent the absolute path of resources directory
     */
    public static String getJsonResourcesAbsolutePathString() {
        return getJsonResourcePath().toAbsolutePath().toString();
    }
    
    /**
     * Create and return the resources directory path with the utility method in {@link Paths}.
     * Use the main path and concatenate the right directory name, specifies in {@link MavenDirLayoutFolder}.
     * @return a {@link Path} that represent the resources directory
     */
    public static Path getPDFResourcePath() {
        return Paths.get(getResourcesPathString(), MavenDirLayoutFolder.PDF_DIR.toString());
    }
    
    /**
     * Get the resources directory path in {@link String}.
     * @return a {@link String} that represent the resources directory
     */
    public static String getPDFResourcesPathString() {
        return getPDFResourcePath().toString();
    }
    
    /**
     * Get the absolute path of resources folder in {@link String}.
     * @return a {@link String} that represent the absolute path of resources directory
     */
    public static String getPDFResourcesAbsolutePathString() {
        return getPDFResourcePath().toAbsolutePath().toString();
    }
    
    /**
     * Create and return the resources directory path with the utility method in {@link Paths}.
     * Use the main path and concatenate the right directory name, specifies in {@link MavenDirLayoutFolder}.
     * @return a {@link Path} that represent the resources directory
     */
    public static Path getLogoResourcePath() {
        return Paths.get(getResourcesPathString(), MavenDirLayoutFolder.LOGO_DIR.toString());
    }
    
    /**
     * Get the resources directory path in {@link String}.
     * @return a {@link String} that represent the resources directory
     */
    public static String getLogoResourcesPathString() {
        return getLogoResourcePath().toString();
    }
    
    /**
     * Get the absolute path of resources folder in {@link String}.
     * @return a {@link String} that represent the absolute path of resources directory
     */
    public static String getLogoResourcesAbsolutePathString() {
        return getLogoResourcePath().toAbsolutePath().toString();
    }
    
    /**
     * Check System SO.
     * @return WIN_SO_PREFIX if Windows, UNIS_SO either.
     */
    public static int checkSystemSO() {
        return System.getProperty("os.name").toLowerCase().startsWith(WIN_SO_PREFIX) ? WINDOWS_SO : UNIX_SO;
    }
}
