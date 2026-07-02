package view.level.generator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import view.level.settings.LevelSettings;

/**
 * This class is responsible of reading a matrix from a .txt file,
 * which contains one of the predefined patterns describing a level.
 */

public final class MapTracesLoaderImpl implements MapTracesLoader {

    MapTracesLoaderImpl() {
    }

    /**
     * This method is used from outside the class to read a matrix contained in a .txt file.
     * 
     * @param fileToReadPath
     *                      the path of the map trace i want to read
     * @return matrix
     * 
     * @throws IOException 
     */


    public char[][] readFromTxt(final String fileToReadPath) throws IOException {
        return fromStringLinesToMatrix(fromTxtToListOfLines(fileToReadPath));
        }

    /**
     * This method reads the file contained in the string Path one line at a time.
     * 
     * @param path
     *
     * @return listOfLines
     *
     * @throws IOException
     */

    private static ArrayList<String> fromTxtToListOfLines(final String path) throws IOException {
        final ArrayList<String> listOfLines = new ArrayList<>();
        final LevelSettings variable = new LevelSettings();
        try {
            final BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String buffer = null;
            int count = variable.getnBlockHeight();
            while (count >= 0) {
                buffer = r.readLine();
                listOfLines.add(buffer);
                count--;
                }
            r.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                }
        return listOfLines;
        }

    /**
     * This method transforms the list of lines into a matrix [Char][Char].
     * 
     * @param linesList
     * 
     * @return matrix
     */

    private static char[][] fromStringLinesToMatrix(final ArrayList<String> linesList) {
        final LevelSettings variable = new LevelSettings();
        char[][] matrix = new char[variable.getnBlockHeight()][variable.getnBlockWidth()];
        for (int x = 0; x < variable.getnBlockHeight(); x++) {
            final char[] bufferArray = linesList.get(x).toCharArray();
            for (int y = 0; y < variable.getnBlockWidth(); y++) {
                matrix[x][y] = bufferArray[y];
                }
            }
        return matrix;
        }
    }
