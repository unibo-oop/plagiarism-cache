package scores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * EditFile implementation.
 *
 */
public class EditFile implements EditFileInterface {

    /**
     * Separator that it's portable.
     */
    public static final String SEP = File.separator;

    /**
     * File with best time.
     */
    public static final String FILE_NAME_RANKING = System.getProperty("user.home") + SEP + "ranking";
    
    /**
     * Support file with times in seconds.
     */
    public static final String FILE_NAME_TIMES = System.getProperty("user.home") + SEP + "times";

    @Override
    public final void changeFileRanking(final int oldHour, final int oldMin, final int oldSec, final int newHour,
            final int newMin, final int newSec) {
        FileInputStream fstream = null;
        DataInputStream in = null;
        BufferedWriter out = null;

        try {
            fstream = new FileInputStream(FILE_NAME_RANKING);

            in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            final StringBuilder fileContent = new StringBuilder();

            while ((strLine = br.readLine()) != null) {
                if (strLine.equals(oldHour + ":" + oldMin + ":" + oldSec)) {
                    fileContent.append(newHour + ":" + newMin + ":" + newSec + System.getProperty("line.separator"));
                } else {
                    fileContent.append(strLine);
                    fileContent.append(System.getProperty("line.separator"));
                }
            }
            br.close();
            final FileWriter fstreamWrite = new FileWriter(FILE_NAME_RANKING);
            out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                fstream.close();
                out.flush();
                out.close();
                in.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final void changeFileTimes(final int oldTime, final int newTime) {
        FileInputStream fstream = null;
        DataInputStream in = null;
        BufferedWriter out = null;

        try {
            fstream = new FileInputStream(FILE_NAME_TIMES);

            in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            final StringBuilder fileContent = new StringBuilder();

            while ((strLine = br.readLine()) != null) {

                if (strLine.equals(toString(oldTime))) {
                    fileContent.append(newTime + System.getProperty("line.separator"));
                } else {
                    fileContent.append(strLine);
                    fileContent.append(System.getProperty("line.separator"));
                }
            }
            br.close();
            final FileWriter fstreamWrite = new FileWriter(FILE_NAME_TIMES);
            out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();

        } finally {
            try {
                fstream.close();
                out.flush();
                out.close();
                in.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final void changeFileFirstRanking(final String old, final int hour, final int min, final int sec) {
        FileInputStream fstream = null;
        DataInputStream in = null;
        BufferedWriter out = null;

        try {
            fstream = new FileInputStream(FILE_NAME_RANKING);

            in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            final StringBuilder fileContent = new StringBuilder();

            while ((strLine = br.readLine()) != null) {

                if (strLine.equals(old)) {
                    fileContent.append(hour + ":" + min + ":" + sec + System.getProperty("line.separator"));
                } else {
                    fileContent.append(strLine);
                    fileContent.append(System.getProperty("line.separator"));
                }
            }
            br.close();
            final FileWriter fstreamWrite = new FileWriter(FILE_NAME_RANKING);
            out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                fstream.close();
                out.flush();
                out.close();
                in.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final void changeFileFirstTimes(final String old, final int sum) {
        FileInputStream fstream = null;
        DataInputStream in = null;
        BufferedWriter out = null;

        try {
            fstream = new FileInputStream(FILE_NAME_TIMES);

            in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            final StringBuilder fileContent = new StringBuilder();

            while ((strLine = br.readLine()) != null) {

                if (strLine.equals(old)) {
                    fileContent.append(sum + System.getProperty("line.separator"));
                } else {
                    fileContent.append(strLine);
                    fileContent.append(System.getProperty("line.separator"));
                }
            }
            br.close();
            final FileWriter fstreamWrite = new FileWriter(FILE_NAME_TIMES);
            out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                fstream.close();
                out.flush();
                out.close();
                in.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private String toString(final int oldTime) {
        return ("" + oldTime);
    }
}
