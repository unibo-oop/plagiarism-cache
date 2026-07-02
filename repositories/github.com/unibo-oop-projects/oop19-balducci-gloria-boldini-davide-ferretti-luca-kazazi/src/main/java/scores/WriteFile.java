package scores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Implementation for FileWriter.
 * 
 *
 */
public class WriteFile implements WriteFileInterface {

    private final String separator = System.getProperty("line.separator");

    /**
     * Separator that it's portable.
     */
    public static final String SEP = File.separator;
    
    /**
     * File with best time.
     */
    public static final String FILE_NAME_RANKING = System.getProperty("user.home") + SEP + "ranking";

    /**
     * File that shows scores.
     */
    public static final String FILE_NAME_SCORES = System.getProperty("user.home") + SEP + "scores";

    /**
     * File with times in seconds.
     */
    public static final String FILE_NAME_TIMES = System.getProperty("user.home") + SEP + "times";

    @Override
    public final void createRankingFile() {
        try (FileWriter fw = new FileWriter(FILE_NAME_RANKING, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.print("");
            bw.close();
            out.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_NAME_RANKING));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            if (br.readLine() == null) {
                System.out.println("No errors, and file empty");
                try (FileWriter fw = new FileWriter(FILE_NAME_RANKING, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.print("Top 3 of level 1" + this.separator + "no time for first lv.1" + this.separator
                            + "no time for second lv.1" + this.separator + "no time for third lv.1" + this.separator
                            + this.separator + "Top 3 of level 2" + this.separator + "no time for first lv.2"
                            + this.separator + "no time for second lv.2" + this.separator + "no time for third lv.2"
                            + this.separator + this.separator + "Top 3 of level 3" + this.separator
                            + "no time for first lv.3" + this.separator + "no time for second lv.3" + this.separator
                            + "no time for third lv.3" + this.separator + this.separator + "Top 3 of level 4"
                            + this.separator + "no time for first lv.4" + this.separator + "no time for second lv.4"
                            + this.separator + "no time for third lv.4" + this.separator + this.separator
                            + "Top 3 of level 5" + this.separator + "no time for first lv.5" + this.separator
                            + "no time for second lv.5" + this.separator + "no time for third lv.5");
                    out.close();
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            br.close();
        } catch (IOException | NullPointerException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public final void createTimesFile() {
        try (FileWriter fw = new FileWriter(FILE_NAME_TIMES, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.print("");
            out.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_NAME_TIMES));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            if (br.readLine() == null) {
                try (FileWriter fw = new FileWriter(FILE_NAME_TIMES, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.print("no time for first lv.1" + this.separator + "no time for second lv.1" + this.separator
                            + "no time for third lv.1" + this.separator + this.separator

                            + "no time for first lv.2" + this.separator + "no time for second lv.2" + this.separator
                            + "no time for third lv.2" + this.separator + this.separator

                            + "no time for first lv.3" + this.separator + "no time for second lv.3" + this.separator
                            + "no time for third lv.3" + this.separator + this.separator

                            + "no time for first lv.4" + this.separator + "no time for second lv.4" + this.separator
                            + "no time for third lv.4" + this.separator + this.separator

                            + "no time for first lv.5" + this.separator + "no time for second lv.5" + this.separator
                            + "no time for third lv.5");
                    out.close();
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            br.close();
        } catch (IOException | NullPointerException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public final void createScoresFile() {
        try (FileWriter fw = new FileWriter(FILE_NAME_SCORES, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.print("");
            bw.close();
            out.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
