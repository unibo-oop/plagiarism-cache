package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import common.Log;

public class FileManagerImpl<T> implements FileManager<T> {

    @Override
    public Set<T> load(final String fileName) {

        try {
            final FileInputStream fis = new FileInputStream(fileName);
            final ObjectInputStream ois = new ObjectInputStream(fis);

            @SuppressWarnings("unchecked")
            final Set<T> set = (Set<T>) ois.readObject();

            ois.close();
            return set;
        } catch (FileNotFoundException ex) {
            Log.add(ex.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new HashSet<>();
    }

    @Override
    public void save(final String fileName, final Set<T> set) {
        try {
            final FileOutputStream fos = new FileOutputStream(fileName);
            final ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(set);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}