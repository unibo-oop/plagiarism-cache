package javagotchi.controller.minigame.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javagotchi.view.minigame.component.GameButton;

/**
 * 
 * @author marica
 *
 */
public class SavedDataImpl implements SavedData {

    private static final String BESTSCOREPATH = System.getProperty("user.home") + System.getProperty("file.separator")
            + "BestScore.txt";
    private final String savedGameFile;

    /**
     * 
     * Constructor for the SavedDataImpl.
     * 
     * @param nameAvatar
     *            name of javagotchi chosen.
     */
    public SavedDataImpl(final String nameAvatar) {
        savedGameFile = System.getProperty("user.home") + System.getProperty("file.separator") + "SaveGame" + nameAvatar
                + ".txt";
    }

    @Override
    public final String getSaveGame() {
        return savedGameFile;
    }

    @Override
    public final boolean existFileSaveGame() {
        return existFile(savedGameFile);
    }

    @Override
    public final void writeGame(final Integer score, final List<GameButton> gameButtons, final Integer sec) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(savedGameFile)))) {
            objectOutputStream.writeObject(score);
            objectOutputStream.writeObject(gameButtons);
            objectOutputStream.writeObject(sec);

            objectOutputStream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public final List<Object> readGameSaved() {
        final List<Object> list = new ArrayList<>();
        boolean check = true;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(savedGameFile)))) {
            while (check) {
                try {
                    list.add(objectInputStream.readObject());
                } catch (EOFException e) {
                    check = false;
                }
            }
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public final void deleteGameSaved() {
        if (existFileSaveGame()) {
            createFile(savedGameFile).delete();
        }
    }

    @Override
    public final String getFileBestScore() {
        return BESTSCOREPATH;
    }

    @Override
    public final boolean existFileBestScore() {
        return existFile(BESTSCOREPATH);
    }

    private static File createFile(final String path) {
        return new File(path);
    }

    private static boolean existFile(final String path) {
        return createFile(path).exists();
    }

    @Override
    public final Map<String, Integer> readBestScore() {
        final Map<String, Integer> map = new LinkedHashMap<>();
        try (DataInputStream dstream = new DataInputStream(new FileInputStream(BESTSCOREPATH))) {
            while (dstream.available() > 0) {
                map.put(dstream.readUTF(), dstream.readInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public final void writeBestScore(final Map<String, Integer> bestScoreMap) {
        try (DataOutputStream dstream = new DataOutputStream(new FileOutputStream(BESTSCOREPATH))) {
            bestScoreMap.entrySet().forEach(e -> {
                try {
                    dstream.writeUTF(e.getKey());
                    dstream.writeInt(e.getValue());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            dstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
