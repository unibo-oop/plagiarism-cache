package view.level.generator;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import view.level.settings.LevelSettings;


/**
 * This class is in charge of writing a matrix on a .txt file, 
 * it's used during the generation of new levels.
 */

public final class WritingLevelsOnTxtImpl {

    private WritingLevelsOnTxtImpl() {
    }


    /**
     * This public method is called whenever you need to generate a new level.
     * 
     * @param livello 
     *               the number of the level i need to generate
     * 
     * @param matrix 
     *               the matrix i need to write on the .txt
     */

    public static void writeOnTxt(final Integer livello, final char[][] matrix) {

        final String path = "src/assets/text/levels/level" + livello.toString() + ".txt";

        final ArrayList<String> bufferToWrite = fromMatrixToList(matrix);

        writeListOnTxt(bufferToWrite, path);
        }

    /**
     * This method transforms the matrix into a list of strings.
     * 
     * @param matrix 
     * 
     * @return listOfLines
     */

    private static ArrayList<String> fromMatrixToList(final char[][] matrix) {
        ArrayList<String> listOfLines = new ArrayList<String>();
        final LevelSettings variable = new LevelSettings();

        for (int x = 0; x < variable.getnBlockHeight(); x++) {
            String buff = "";
            for (int y = 0; y < variable.getnBlockWidth(); y++) {
                buff = buff + matrix[x][y];
                }
            listOfLines.add(x, buff);
            }

        return listOfLines;
        }

    /**
     * This method use a BufferedWriter in order to write the lines on a .txt file.
     * 
     *  @param path 
     *             the path of the file i need to write
     *
     *  @param bufferToWrite 
     *             the list of lines i want to write in the file
     */

    private static void writeListOnTxt(final ArrayList<String> bufferToWrite, final String path) {
        try (BufferedWriter w = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path)))) {
            for (int u = 0; u < bufferToWrite.size(); u++) {
                w.write(bufferToWrite.get(u));
                w.newLine();
                }
            w.flush();
            w.close();
            } catch (Exception e) {
                e.printStackTrace();
                }
        }
}
