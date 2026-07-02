package it.unibo.pacman.controller.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Class for I/O of the game map.
 */
public final class MapIO {
    private static int rows;
    private static int columns;
    private static BufferedReader content;
    private static final String SEP = System.getProperty("file.separator");
    private static final String PATH = System.getProperty("user.home") + SEP + "pacmanRes" + SEP + "Maps" + SEP;
    private static final int SCALE = 30;

    private MapIO() {
    }

    /**
     * Used to read the map from a file.
     * 
     * @param mapName is file's name
     * @throws IOException if the file is not found
     */
    public static void readMap(final String mapName) throws IOException {
        content = new BufferedReader(new FileReader(PATH + mapName));
        columns = Integer.parseInt(getNext());
        rows = Integer.parseInt(getNext());
    }

    /**
     * 
     * @return a list of all the maps
     */
    public static List<String> getMapNames() {
        final File directoryPath = new File(PATH);
        final Optional<String[]> list = Optional.of(directoryPath.list());
        return Arrays.asList(list.get());
    }

    /**
     * Write the map with the given map name.
     * 
     * @param mapName is file's name
     * @param map     contains all the information of the map
     * @throws IOException if the file is not found
     */
    public static void writeMap(final String mapName, final String map) {
        Optional<PrintWriter> out  = Optional.empty();
        try {
            out = Optional.of(new PrintWriter(PATH + mapName + ".txt"));
            out.get().print(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.get().close();
    }

    /**
     * Used to get the number of rows of the selected map.
     * 
     * @return the number of rows
     */
    public static int getRows() {
        return rows;
    }

    /**
     * Used to get the number of columns of the selected map.
     * 
     * @return the number of columns
     */
    public static int getColumns() {
        return columns;
    }

    /**
     * Used to get the content of the next row of the map.
     * 
     * @return a string representing the content of the rows of the map
     * @throws IOException if the file is not found
     */
    public static String getNext() throws IOException {
        return content.readLine();
    }

    public static int getScale() {
        return SCALE;
    }

    public static String getPath() {
        return PATH;
    }
}
