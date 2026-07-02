package ballblast.controller.files;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import ballblast.controller.DirectoryManager;
import ballblast.model.data.Leaderboard;

/**
 * Implements the {@link LeaderboardManager}.
 */
public class SimpleLeaderboardManager implements LeaderboardManager {

    @Override
    public final Optional<Leaderboard> loadSurvivalLeaderboard() {
        return this.load(DirectoryManager.SURVIVAL_FILE);
    }

    @Override
    public final boolean saveSurvivalLeaderboard(final Leaderboard lb) {
        return this.save(lb, DirectoryManager.SURVIVAL_FILE);
    }

    private boolean save(final Leaderboard lb, final String filePath) {
        try (FileOutputStream fos = new FileOutputStream(new File(filePath)); XMLEncoder enc = new XMLEncoder(fos)) {
            enc.writeObject(lb);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Optional<Leaderboard> load(final String path) {
        if (!Files.exists(Paths.get(path))) {
            final Leaderboard leaderboard = new Leaderboard();
            leaderboard.setRecordList(leaderboard.initList());
            this.save(leaderboard, path);
            return Optional.of(leaderboard);
        }
        try (FileInputStream fis = new FileInputStream(new File(path)); XMLDecoder dec = new XMLDecoder(fis)) {
            return Optional.of((Leaderboard) dec.readObject());
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
