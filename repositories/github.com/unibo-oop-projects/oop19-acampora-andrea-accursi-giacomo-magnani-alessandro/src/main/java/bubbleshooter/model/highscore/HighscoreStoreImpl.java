package bubbleshooter.model.highscore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bubbleshooter.model.game.level.LevelType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class used for save and read all the scores from a file.
 * Implements the {@link HighscoreStore} interface.
 */
public class HighscoreStoreImpl implements HighscoreStore {

    private static final String SEP = System.getProperty("file.separator");
    private static final String DIR_PATH = System.getProperty("user.home") + SEP + ".Bubbleshooter";
    private static final String FILE_PATH = SEP + "Highscores.txt";
    private static final String END_HIGH = "END_OF_HIGH_END_OF_HIGH";
    private static final String END_FILE = "END_OF_FILE_END_OF_FILE";
    private static final String BASIC = "BASIC_MODE_HIGHSCORES...";
    private static final String SURVIVAL = "SURVIVAL_MODE_HIGHSCORES...";
    private static final String NEW_LINE = "\n";
    private static final char SPACE = ' ';
    private static final int CAPACITY = 10;
    private final File file;
    private Map<LevelType, List<HighscoreStructure>> mapOfItems;

    /**
     * Constructor of the {@link HighscoreStore} used for create the file if necessary and
     * for allocate the HashMap.
     */
    public HighscoreStoreImpl() {
        this.file = new File(DIR_PATH + FILE_PATH);

        if (!file.getParentFile().exists() && !this.createDirectory()) {
            System.out.println("Can't create the directory !!!");
        }
        if (!file.exists()) {
            this.createFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))) {
                bw.write("HIGHSCORES!!" + NEW_LINE + NEW_LINE);
                bw.write(BASIC + NEW_LINE);
                bw.write(END_HIGH + NEW_LINE + NEW_LINE);
                bw.write(SURVIVAL + NEW_LINE);
                bw.write(END_HIGH + NEW_LINE + NEW_LINE);
                bw.write(END_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.mapOfItems = new HashMap<>();
    }

    /**
     * Method for get the file where scores are saved.
     * @return the file where the scores are saved.
     */
    @Override
    public final File getFile() {
        return this.file;
    }

    /**
     * Method used for clean file.
     */
    @Override
    public void cleanFile() {
        this.mapOfItems.clear();
        this.mapOfItems.put(LevelType.BASICMODE, new ArrayList<HighscoreStructure>());
        this.mapOfItems.put(LevelType.SURVIVALMODE, new ArrayList<HighscoreStructure>());
        this.reWriteFile();
    }

    /**
     * Method to have a list of scores for a specific game modality.
     * @param gameMode The game modality which we want the scores.
     * @return the scores for a game modality.
     */
    @Override
    public final ObservableList<HighscoreStructure> getHighscoresForModality(final LevelType gameMode) {
        final ObservableList<HighscoreStructure> result = FXCollections.observableArrayList();
        this.mapOfItems = readFile();
        if (this.mapOfItems.containsKey(gameMode)) {
            result.addAll(this.mapOfItems.get(gameMode));
        }
        return result;
    }

    /**
     * Method for add a score for a game modality.
     * @param score The current {@link HighscoreStructure} to save.
     */
    @Override
    public final void addScore(final HighscoreStructure score) {

        this.mapOfItems = readFile();

        this.mapOfItems.get(score.getGameMode()).add(score);

        sort(this.mapOfItems.get(score.getGameMode()));

        clean(this.mapOfItems.get(score.getGameMode()));

        reWriteFile();
    }

    /**
     * Private method used for create the file.
     */
    private void createFile() {
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.println("Can't create file !!! Exception --> " + e);
        }
    }

    /**
     * Private method used for create the directory.
     */
    private boolean createDirectory() {
        return this.file.getParentFile().mkdirs();
    }

    /**
     * Private method used for delete the file.
     */
    private boolean deleteFile() {
        return this.file.delete();
    }

    /**
     * Private method used for put in map every the type of scores saved.
     * @return the map contains the different scores.
     */
    private Map<LevelType, List<HighscoreStructure>> readFile() {
        final Map<LevelType, List<HighscoreStructure>> map = new HashMap<>();
        for (final LevelType tipo : LevelType.values()) {
            map.put(tipo, readFromFile(tipo));
        }
        return map;
    }

    /**
     * Private method used for read from file a list of scores for a specific game modality.
     * @param gameMode The game modality.
     * @return the list of scores for the specific game modality.
     */
    private List<HighscoreStructure> readFromFile(final LevelType gameMode) {

        final List<HighscoreStructure> itemsSet = new ArrayList<>();
        final String modality = whichMod(gameMode);
        String readString;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            readString = br.readLine();
            while (readString != null && !readString.equals(END_FILE)) {
                readString = br.readLine();
                if (readString != null && readString.equals(modality)) {
                    readString = br.readLine();
                    while (readString != null && !readString.equals(END_HIGH)) {
                        itemsSet.add(generateHighscore(readString, gameMode));
                        readString = br.readLine();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error in reading of file !!! Exception --> " + e);
        }
        return itemsSet;
    }

    /**
     * Private method used for convert the @param gameMode to a String.
     * @param gameMode The game modality we want to convert.
     * @return the String for the game modality.
     */
    private String whichMod(final LevelType gameMode) {
        switch (gameMode) {
        case BASICMODE:
            return BASIC;
        case SURVIVALMODE:
            return SURVIVAL;
        default:
            return null;
        }
    }

    /**
     * Private method used for generate a {@link HighscoreStructure} for add into a list.
     * @param stringa  Thw line read from file.
     * @param gameMode The game modality.
     * @return the {@link HighscoreStructure} created.
     */
    private HighscoreStructure generateHighscore(final String readString, final LevelType gameMode) {
        final StringBuilder nameBuilder = new StringBuilder("");
        final StringBuilder scoreBuilder = new StringBuilder("");
        boolean flag = true;


        for (int i = 0; i < readString.length(); i++) {

            if (!flag) {
                scoreBuilder.append(readString.charAt(i));
            }

            if (readString.charAt(i) != SPACE && flag) {
                nameBuilder.append(readString.charAt(i));
            } else {
                flag = false;
            }
        }
        return new HighscoreStructure(nameBuilder.toString(), Integer.parseInt(scoreBuilder.toString()), gameMode);
    }

    /**
     * Private method used for sort a list of {@link HighscoreStructure}.
     * @param itemsSet The list of {@link HighscoreStructure}.
     */
    private void sort(final List<HighscoreStructure> itemsSet) {
        final Comparator<HighscoreStructure> comp = new Comparator<>() {
            @Override
            public int compare(final HighscoreStructure o1, final HighscoreStructure o2) {
                return o2.getScore() - o1.getScore();
            }
        };
        Collections.sort(itemsSet, comp);
    }

    /**
     * Private method used for clean a list if necessary.
     * @param itemsSet The list to clean.
     */
    private void clean(final List<HighscoreStructure> itemsSet) {
        for (int i = 0; i < itemsSet.size(); i++) {
            if (i >= CAPACITY) {
                itemsSet.remove(i);
            }
        }
    }

    /**
     * Private method used for re-writing the file with the new modify.
     */
    private void reWriteFile() {
        String stringToWrite;
        if (!this.deleteFile()) {
            System.out.println("Can't delete the file !!!");
        }
        if (!file.getParentFile().exists() && !this.createDirectory()) {
            System.out.println("Can't create the directory !!!");
        }
        if (!file.exists()) {
            this.createFile();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))) {
            bw.write("HIGHSCORES!!\n\n");
            bw.write(BASIC + NEW_LINE);
            for (final HighscoreStructure o : this.mapOfItems.get(LevelType.BASICMODE)) {
                stringToWrite = o.getName() + SPACE + o.getScore() + NEW_LINE;
                bw.write(stringToWrite);
            }
            bw.write(END_HIGH + NEW_LINE + NEW_LINE);
            bw.write(SURVIVAL + NEW_LINE);
            for (final HighscoreStructure o : this.mapOfItems.get(LevelType.SURVIVALMODE)) {
                stringToWrite = o.getName() + SPACE + o.getScore() + NEW_LINE;
                bw.write(stringToWrite);
            }
            bw.write(END_HIGH + NEW_LINE + NEW_LINE);
            bw.write(END_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
