package controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import view.ExceptionDialog;

/**
 * ScoreList manage the score of the games.
 */
public class ScoreList {
    /**
     * 
     */

    private static final int NUMBEROFENTRIES = 3;
    /**
     * 
     */
    private static List<Score> scores = new ArrayList<Score>();
    /**
     * 
     */
    private final String resAddr;
    /**
     * 
     */
    private final String saveLocal = System.getProperty("user.home") + File.separator + ".DSAsave";
    /**
     * This entity handle the Class List<Score> type.
     */
    private final Type scoresType = new TypeToken<List<Score>>() {
    }.getType();
    /**
     * 
     */
    private final Gson gson;

    /**
     * @param resAddr
     *                    The address of resource json file.
     * @throws IOException
     *                         If can't handle the creation of file on the system.
     */
    public ScoreList(final String resAddr) throws IOException {
        this.resAddr = resAddr;
        this.gson = new Gson();
        this.chargeList();
    }

    /**
     * @throws IOException
     *                         If can't handle the creation of file on the system.
     */
    public final void chargeList() throws IOException {

        final File localFile = new File(saveLocal);
        if (!localFile.exists()) {

            try {
                localFile.createNewFile();
            } catch (IOException e) {
                new ExceptionDialog(e.getMessage());
                e.printStackTrace();
            }

            final BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(getClass().getResourceAsStream(resAddr)));
            // Istanzio vuote le due stringhe per non avere problemi con Gson/Json
            String aDataRow = "";
            String aBuffer = "";
            // for perchè il while non lo acceta
            for (aDataRow = myReader.readLine(); aDataRow != null; aDataRow = myReader.readLine()) {
                aBuffer += aDataRow;
            }

            myReader.close();

            scores = gson.fromJson(aBuffer, scoresType);
            this.save();

        } else {

            final File myFile = new File(localFile.getAbsolutePath());
            final FileInputStream fIn = new FileInputStream(myFile);

            final BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = ""; // Holds the text
            for (aDataRow = myReader.readLine(); aDataRow != null; aDataRow = myReader.readLine()) {
                aBuffer += aDataRow;
            }

            myReader.close();

            scores = gson.fromJson(aBuffer, scoresType);
        }
    }

    private void sort() {
        scores.sort(new Comparator<Score>() {
            @Override
            public int compare(final Score score1, final Score score2) {
                final Integer int1 = score1.getScore();
                final Integer int2 = score2.getScore();
                return int2.compareTo(int1);
            }
        });
    }

    /**
     * @return The scores list.
     */
    public final List<Score> getScores() {
        return scores;
    }

    /**
     * Add a score to scorelist.
     * 
     * @param score
     *                  The score that should be aded to list of bestscores.
     * @throws IOException
     */
    public final void addScore(final Score score) {
        scores.add(score);
        sort();
        save();

    }

    /**
     * Save the scorelist in txt file.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public final void save() {
        final File localFile = new File(saveLocal);

        scores = scores.stream().limit(NUMBEROFENTRIES).collect(Collectors.toList());


        final String gSave = gson.toJson(scores, scoresType);

        try {
            final FileOutputStream outputStream = new FileOutputStream(localFile);
            final OutputStreamWriter myOutWriter = new OutputStreamWriter(outputStream);
            myOutWriter.append(gSave);
            myOutWriter.close();
            outputStream.close();
        } catch (IOException e1) {
            new ExceptionDialog(e1.getMessage());
            e1.printStackTrace();
        }

    }

}
