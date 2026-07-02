package controller.leaderboard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import controller.base.BaseController;

/**
 * This is the implementation of {@link LeaderboardController} interface.
 *
 */
public class LeaderboardControllerImpl implements LeaderboardController {
    private final BaseController baseController;
    private static final String SEP = File.separator;
    private final File file = new File(
            System.getProperty("user.home") + SEP + "tegole" + SEP + "leaderboard" + SEP + "leaderboard.txt");

    public LeaderboardControllerImpl(final BaseController baseController) {
        this.baseController = baseController;
    }

    @Override
    public final List<String> loadScore() {
        final List<String> stringlist = new ArrayList<>();
        try {
            Files.lines(file.toPath()).forEach(l -> stringlist.add(l));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringlist;

    }

    @Override
    public final boolean isFileThere() {
        return file.exists();
    }

    @Override
    public final void goBackHit() {
        this.baseController.startProgram();
    }

}
