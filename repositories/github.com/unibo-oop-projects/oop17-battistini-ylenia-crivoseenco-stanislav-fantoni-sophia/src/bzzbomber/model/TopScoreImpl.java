package bzzbomber.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.LinkedList;
import java.util.List;


/**
 * it contains a list of Score and has the method to get the best Score, to read
 * from file and write on file.
 * 
 */
public final class TopScoreImpl implements TopScore {

    /**
     * the number of elements to save, we filter only the top ten.
     */
    public static final int MAXNUM = 10;
    private final List<Score> list;
    /**
     * The path when the file to save the top Score is created.
     */
    public static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator")
            + TopScoreImpl.class.getSimpleName() + ".txt";

    /**
     * the constructor, initialize the list of Score.
     */
    public TopScoreImpl() {
        this.list = new LinkedList<>();
    }

    @Override
    public void addScore(final Score score) {
        this.list.add(score);
    }

    @Override
    public List<Score> getScore() {
        this.list.sort((a, b) -> b.getPoint() - a.getPoint());
        setBestScore();
        return list;
    }

    private void setBestScore() {
        list.stream().limit(TopScoreImpl.MAXNUM);
        while (this.list.size() > TopScoreImpl.MAXNUM) {
            list.remove(list.size() - 1);
        }
    }

    @Override
    public void saveOnFile(final String fileName) throws IllegalStateException, IOException {
        getScore();
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (final Score s : list) {
                os.writeObject(s);
            }
        } catch (InvalidClassException e1) {
            e1.printStackTrace();
            System.out.println("InvalidClassException");
        } catch (StreamCorruptedException e2) {
            e2.printStackTrace();
            System.out.println("StreamCorruptException");
        } catch (OptionalDataException e3) {
            e3.printStackTrace();
            System.out.println("OptionalDataExcception");
        } catch (IOException e4) {
            e4.printStackTrace();
            System.out.println("IOException save on file");
        }

    }

    @Override
    public void readFromFile(final String fileName) {
        if (!new File(fileName).exists()) {
            try {
                this.saveOnFile(fileName);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName))) {
            for (Object obj = is.readObject(); obj != null; obj = is.readObject()) {
                final Score s = new Score((Score) obj);
                this.list.add(s);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ClassNotFound");
        } catch (InvalidClassException e1) {
            e1.printStackTrace();
            System.out.println("InvalidClassException");
        } catch (StreamCorruptedException e2) {
            e2.printStackTrace();
            System.out.println("StreamCorruptException");
        } catch (OptionalDataException e3) {
            e3.printStackTrace();
            System.out.println("OptionalDataExcception");
        } catch (EOFException e4) {
            System.out.println();
        } catch (IOException e5) {
            e5.printStackTrace();
            System.out.println("IOException");
        }
        setBestScore();
    }

}
