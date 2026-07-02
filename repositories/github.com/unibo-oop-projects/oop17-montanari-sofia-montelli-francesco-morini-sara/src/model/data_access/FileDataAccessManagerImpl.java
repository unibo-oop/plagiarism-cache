package model.data_access;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that manages the player save file.
 * @param <X> a generic object for which data is to be managed
 */
public final class FileDataAccessManagerImpl<X> implements FileDataAccessManager<X> {
    /**
     * set of saved X.
     */
    private Set<X> dataSet = new HashSet<>();
    /**
     * name of the save file.
     */
    private final String fileName;
    /**
     * field for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(FileDataAccessManagerImpl.class.getName());

    /**
     * Constructor.
     * @param fileName the name's file
     */
    public FileDataAccessManagerImpl(final String fileName) {
        LOGGER.trace("Create a new file");
        this.fileName = fileName;
        final File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                deleteFileContent();
            } else {
                reloadFromDataSet();
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    private void deleteFileContent() {
        try {
            final ObjectOutputStream buffer = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            buffer.writeObject(new HashSet<X>());
            buffer.close();
            LOGGER.trace("Delete the content of the file {}", fileName);
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void reloadFromDataSet() {
        ObjectInputStream fileIn;
        try {
            fileIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            dataSet = (Set<X>) fileIn.readObject();
            fileIn.close();
            LOGGER.trace("Reload the content of the file");
        } catch (IOException e) {
            LOGGER.error(e);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Set<X> getSet() {
        return dataSet;
    }

    @Override
    public void saveNewElement(final X x) {
        LOGGER.trace("Save a new element");
        dataSet.add(x);
        updateFile();
    }

    private void updateFile() {
        try {
            deleteFileContent();
            final ObjectOutputStream fileOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            fileOut.writeObject(dataSet);
            fileOut.close();
            LOGGER.trace("Update the file data");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void updateElement(final X x) {
        LOGGER.trace("Add a new element in the file");
        if (dataSet.contains(x)) {
            dataSet.remove(x);
            dataSet.add(x);
            updateFile();
        }
    }

    @Override
    public void removeElement(final X x) {
        LOGGER.trace("Remove a new element in the file");
        dataSet.remove(x);
        updateFile();
    }
}
