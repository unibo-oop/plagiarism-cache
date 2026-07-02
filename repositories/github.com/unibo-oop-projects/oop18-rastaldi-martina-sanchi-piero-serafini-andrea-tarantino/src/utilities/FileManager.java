package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * This class can be used to manage a file.
 * Andrea Serafini.
 *
 * @param <T> the type of element to be written on the file
 */
public final class FileManager<T extends Serializable> implements FileManagerInterface<T> {

    private static final String SP = File.separator;
    private static final String FORMAT = "UTF-8";
    private final String fileName;

    private T object;

    /**
     *
     * @param fileName
     *            the name of the file
     */
    public FileManager(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void deleteFile() {
        String jarPath = "";
        String completePath = "";
        try {
            jarPath = URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(),
                    FORMAT);
        } catch (final UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        completePath = jarPath.substring(0, jarPath.lastIndexOf('/')) + SP + this.fileName;
        final File f = new File(completePath);
        if (f.exists()) {
            try {
                f.delete();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public T get() {
        this.loadFile();
        return this.object;
    }

    @Override
    public boolean isPresent() {
        String jarPath = "";
        String completePath = "";
        try {
            jarPath = URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(),
                    FORMAT);
        } catch (final UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        completePath = jarPath.substring(0, jarPath.lastIndexOf('/')) + SP + this.fileName;
        final File f = new File(completePath);
        return f.exists();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadFile() {
        String jarPath = "";
        String completePath = "";
        try {
            jarPath = URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(),
                    FORMAT);

        } catch (final UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        completePath = jarPath.substring(0, jarPath.lastIndexOf('/')) + SP + this.fileName;
        final File f = new File(completePath);
        if (f.exists()) {
            try {
                final ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
                this.object = (T) in.readObject();
                in.close();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveFile(final T object) {
        this.object = object;
        this.deleteFile();
        String jarPath = "";
        try {
            jarPath = URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(),
                    FORMAT);
        } catch (final UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        final String completePath = jarPath.substring(0, jarPath.lastIndexOf('/')) + SP + this.fileName;
        final File f = new File(completePath);
        try {
            if (f.createNewFile()) {
                final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
                out.writeObject(this.object);
                out.close();
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFile(final T object) {
        this.object = object;
        String jarPath = "";
        try {
            jarPath = URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(),
                    FORMAT);
        } catch (final UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        final String completePath = jarPath.substring(0, jarPath.lastIndexOf('/')) + SP + this.fileName;
        final File f = new File(completePath);
        try {
            if (f.exists() || f.createNewFile()) {
                final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
                // out.writeObject("");
                out.writeObject(this.object);
                out.close();
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
