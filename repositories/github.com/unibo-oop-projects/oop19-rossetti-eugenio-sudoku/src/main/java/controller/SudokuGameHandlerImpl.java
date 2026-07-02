package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.SudokuLogic;
import model.SudokuLogicImpl;
import utilities.Difficulty;
import utilities.Scenes;
import view.View;

/**
 * Implementation of SudokuGameHandler. 
 */

public class SudokuGameHandlerImpl implements SudokuGameHandler {
    private static final int SUDOKU_SIZE = 9;
    private static final int SUDOKU_SQUARE_SIZE = 3;
    private static final int INITIAL_LINE = 0;
    private static final int SOLUTION_LINE = 1;
    private static final String SUDOKU_SEPARATOR = ",";
    private static final String FORMAT = ".txt";
    private static final String SAVE_FORMAT = ".obj";
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String SAVE_PATH = System.getProperty("user.home") + SEPARATOR + ".SuDoKu" + SEPARATOR + "GameData" + SAVE_FORMAT;
    private static final String GRID_PATH = "/sudokugrid/";
    private final Random rand = new Random();
    private final Settings settings;
    private final View view;
    private SudokuLogic model;

    /**
     * @param view of the game
     */
    public SudokuGameHandlerImpl(final View view) {
        this.settings = new SettingsImpl();
        this.view = view;
    }

    @Override
    public final void newGame(final Difficulty diff) throws IOException {
        final String[] selected = selectSudoku(loadFromFile(selectPath(diff, GRID_PATH).concat(FORMAT)));
        final int[] i1 = Stream.of(selected[INITIAL_LINE].split("")).mapToInt(Integer::parseInt).toArray();
        final int[] i2 = Stream.of(selected[SOLUTION_LINE].split("")).mapToInt(Integer::parseInt).toArray(); 
        this.model = new SudokuLogicImpl(i1, i2, SUDOKU_SIZE, SUDOKU_SQUARE_SIZE);
        this.view.setScene(Scenes.GAME);
    }

    @Override
    public final void resumeGame() throws IOException {
        final File file = new File(SAVE_PATH);
        try (
          ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))
        ) {
            final SudokuLogic board = (SudokuLogic) inputStream.readObject();
            model = board;
            inputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        this.view.setScene(Scenes.GAME);
        this.view.boardUpdate(this.model.getValues());

    }

    @Override
    public final void saveGame() throws IOException {
        final File file = new File(SAVE_PATH);
        saveFile(file, model);
        this.view.setScene(Scenes.HOME);
    }

    @Override
    public final boolean previousGameExist() {
        final File file = new File(SAVE_PATH);
        return file.exists();
    }

    @Override
    public final void hitCell(final int x, final int y, final int value) {
        this.model.hit(x, y, value);
        this.view.boardUpdate(this.model.getValues());
    }

    @Override
    public final void removeCell(final int x, final int y) {
        this.model.remove(x, y);
        this.view.boardUpdate(this.model.getValues());
    }
    @Override
    public final Settings getSettings() {
        return this.settings;
    }

    @Override
    public final SudokuLogic getLogic() {
        return this.model;
    }

    /**
     * Save object in a certain file.
     * @param file File chosen
     * @param obj Object to save
     * @throws IOException 
     */
    private void saveFile(final File file, final Object obj) throws IOException {
        if (file.getParentFile().mkdirs() || file.exists()) {
            try (
              ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))
            ) {
                outputStream.writeObject(obj);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Concat difficulty name with chosen path.
     * @param diff Difficulty name to concat
     * @param path Path to concat
     * @return Final path
     */
    private String selectPath(final Difficulty diff, final String path) {
        return path + diff.toString().toLowerCase(Locale.ENGLISH);
    }

    /**
     * Load all lines from a file.
     * @param path Path of the file
     * @return List of lines
     * @throws IOException
     * @throws URISyntaxException 
     */
    private List<String> loadFromFile(final String path) throws IOException {
        List<String> list = new ArrayList<>();
        try (
          BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)))
        ) {
            list = reader.lines().collect(Collectors.toList());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return list;
    }

    /**
     * Select and split a random string of a list.
     * @param list List o
     * @return Splitted string into array 
     */
    private String[] selectSudoku(final List<String> list) {
        return list.get(rand.nextInt(list.size() - 1)).split(SUDOKU_SEPARATOR);
    }


}
