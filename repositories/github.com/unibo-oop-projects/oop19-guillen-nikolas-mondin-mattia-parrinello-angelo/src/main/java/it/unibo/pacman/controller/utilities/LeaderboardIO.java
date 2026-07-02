package it.unibo.pacman.controller.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import it.unibo.pacman.model.utilities.Difficulty;

/**
 * Class used for I/O of the leaderboard to a txt file.
 */
public final class LeaderboardIO {
    private static BufferedReader content;
    private static final String SEP = System.getProperty("file.separator");
    private static final String PATH = System.getProperty("user.home") + SEP + "pacmanRes" + SEP + "Leaderboards" + SEP;

    private LeaderboardIO() {
    }

    /**
     * Used to create a buffered reader for the correct leaderboard file.
     * 
     * @param difficulty used to select the appropriate leaderboard file. Currently
     *                   we do not have a difficulty selector, but it might be
     *                   implemented in the future with different leaderboards for
     *                   each difficulty level.
     * @throws FileNotFoundException if the file is not found in the directory
     */
    public static void readLeaderboard(final Difficulty difficulty) {
        try {
            content = new BufferedReader(new FileReader(PATH + "Ranking_" + difficulty.toString() + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeLeaderboard(final String player, final int score, final Difficulty difficulty) {
        Optional<PrintWriter> out = Optional.empty();
        try {
            out = Optional.of(new PrintWriter(
                    new FileOutputStream(new File(PATH + "Ranking_" + difficulty.toString() + ".txt"), true)));
            out.get().println(player);
            out.get().println(score);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.get().close();
    }

    /**
     * Used to get the next line of ranking file.
     * 
     * @return a line of the file
     * @throws IOException if the file is not found
     */
    public static String getNext() throws IOException {
        return content.readLine();
    }

    public static String getPath() {
        return PATH;
    }
}
