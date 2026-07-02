package it.unibo.biscia.utils.fileIO.Settings;

import it.unibo.biscia.core.Controller;
import it.unibo.biscia.core.Controller.Speed;
import it.unibo.biscia.utils.Pair;
import it.unibo.biscia.utils.fileIO.FileIO;
import it.unibo.biscia.utils.fileIO.FileIOImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link SettingsIO}. This class uses {@link FileIO} to
 * write and read the setting's file.
 *
 */
public class SettingsIOImpl implements SettingsIO {

    private final FileIO fileIO;

    public SettingsIOImpl() {
        this.fileIO = new FileIOImpl("Settings");
    }

    @Override
    public final List<Pair<String, ?>> getSettings() {
        return Collections.unmodifiableList(new ArrayList<>() {
            private static final long serialVersionUID = -8222397824425405332L;
            {
                this.add(fileIO.getOrDefaultValue("Players", Integer.class, 1));
                this.add(fileIO.getOrDefaultValue("Name player1", String.class, "AAA"));
                this.add(fileIO.getOrDefaultValue("Name player2", String.class, "AAA"));
                this.add(fileIO.getOrDefaultValue("Initial speed", Controller.Speed.class, Controller.Speed.SPEED1));
                this.add(fileIO.getOrDefaultValue("Increasing speed", Boolean.class, false));
                this.add(fileIO.getOrDefaultValue("Music", Boolean.class, true));
                this.add(fileIO.getOrDefaultValue("Sounds", Boolean.class, true));
            }
        });
    }

    @Override
    public final void addSettings(final Map<String, ?> settings) {
        fileIO.addAll(settings);
        fileIO.build();
    }

    @Override
    public final Pair<String, Integer> getNumberOfPlayers() {
        return fileIO.getOrDefaultValue("Players", Integer.class, 1);
    }

    @Override
    public final Pair<String, String> getNamePlayer1() {
        return fileIO.getOrDefaultValue("Name player1", String.class, "AAA");
    }

    @Override
    public final Pair<String, String> getNamePlayer2() {
        return fileIO.getOrDefaultValue("Name player2", String.class, "AAA");
    }

    @Override
    public final Pair<String, Speed> getInitialSpeed() {
        return fileIO.getOrDefaultValue("Initial speed", Controller.Speed.class, Controller.Speed.SPEED1);
    }

    @Override
    public final Pair<String, Boolean> getIncreasingSpeed() {
        return fileIO.getOrDefaultValue("Increasing speed", Boolean.class, false);
    }

    @Override
    public final Pair<String, Boolean> getMusic() {
        return fileIO.getOrDefaultValue("Music", Boolean.class, true);
    }

    @Override
    public final Pair<String, Boolean> getSounds() {
        return fileIO.getOrDefaultValue("Sounds", Boolean.class, true);
    }
}
