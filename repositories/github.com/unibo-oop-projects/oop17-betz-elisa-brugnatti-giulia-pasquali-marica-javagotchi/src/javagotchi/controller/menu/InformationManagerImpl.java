package javagotchi.controller.menu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javagotchi.model.Javagotchi;

/**
 * This class is meant to save Javagotchi's object list on a file in the home directory of the user.
 * It can also read the previous Javagotchi's object list object from the eventually existing file.
 */
public class InformationManagerImpl implements InformationManager {
    private final String javagotchiFile;
    private static final String FILENAME = "InfoTamagotchi.dat";
    private List<Javagotchi> list;
    private Object o;
    /**
     * it the constructor with argument for this class.
     */
    public InformationManagerImpl() {
        this.list = new ArrayList<>();
        this.o = null;
        javagotchiFile = System.getProperty("user.home") + System.getProperty("file.separator") + FILENAME;
    }
    /**
     * this is the constructor with argument for this class.
     * @param name **this is the name of the file**
     */
    public InformationManagerImpl(final String name) {
        super();
        javagotchiFile = System.getProperty("user.home") + System.getProperty("file.separator") + name + FILENAME;
    }

    @Override
    public final List<?> resumeFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(javagotchiFile)))) {
            System.out.println("[JavagotchiManager] information file found.");
            while ((o = objectInputStream.readObject()) != null) {
                this.list.add((Javagotchi) o);
                o = null;
            }
        } catch (FileNotFoundException e2) {
            System.out.println("[JavagotchiManager] file not found. Trying to create a new one...");
            deleteOldInfoFile();
            this.writeNewInfoFile();
        } catch (ClassNotFoundException e2) {
            System.out.println("[StatsManager] Old file found, but corrupted");
            deleteOldInfoFile();
            writeNewInfoFile();
        } catch (EOFException eof) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.list;
    }

    @Override
    public final void writeNewInfoFile() {
        try {
            final File file = new File(javagotchiFile);
            file.createNewFile();
            System.out.println("[JavagotchiManager] Created new Information file in " + javagotchiFile);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void deleteOldInfoFile() {
        final File file = new File(javagotchiFile);
        file.delete();
        System.out.println("[StatsManager] Old stats file deleted.");
    }

    /**
     * this method ask to the infoManager to write on the file the Javagotchi list in order to save it.
     * @param list **this is the list to write on the file**
     */
    @SuppressWarnings("unchecked")
    @Override
    public final void writeOnFile(final List<?> list) {
        this.list = (List<Javagotchi>) list;
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(javagotchiFile)))) {
            list.forEach(e -> {
                try {
                    objectOutputStream.writeObject(list.get(this.list.indexOf(e)));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            });
            objectOutputStream.close();
            System.out.println("[JavagotchiManager] Writing Info object to " + javagotchiFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}