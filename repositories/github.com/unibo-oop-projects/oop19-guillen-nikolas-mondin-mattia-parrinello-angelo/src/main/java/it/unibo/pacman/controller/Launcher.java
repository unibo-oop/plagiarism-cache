package it.unibo.pacman.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import it.unibo.pacman.controller.utilities.LeaderboardIO;
import it.unibo.pacman.controller.utilities.MapIO;
import it.unibo.pacman.model.utilities.Difficulty;
import it.unibo.pacman.view.MainMenuView;

/**
 * Start the application.
 *
 */
public final class Launcher {
    private static final String TITLE = "PACMAN";
    private static final String STANDARD_MAP_PATH = "Game/Maps/standard.txt";
    private static String map = "";

    /**
     * Empty constructor.
     */
    private Launcher() {
    }

    /**
     * Main method.
     * 
     * @param args unused
     * @throws IOException 
     */
    public static void main(final String[] args) throws IOException {
        final Launcher launcher = new Launcher();
        if (new File(MapIO.getPath()).mkdirs()) {
            new File(MapIO.getPath() + "standard.txt");
            launcher.createStandardMap();
            MapIO.writeMap("standard", map);
        }
        if (new File(LeaderboardIO.getPath()).mkdirs()) {
            if (new File(LeaderboardIO.getPath() + "Ranking_" + Difficulty.HARD.toString() + ".txt").createNewFile()) {
                System.out.println("Hard ranking successfully created");
            }
            if (new File(LeaderboardIO.getPath() + "Ranking_" + Difficulty.NORMAL.toString() + ".txt").createNewFile()) {
                System.out.println("Normal ranking successfully created");
            }
        }
        new MainMenuView(TITLE).show();
    }

    private void createStandardMap() throws IOException {
        final Optional<BufferedReader> content = Optional.of(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(STANDARD_MAP_PATH))));
        final int width = Integer.parseInt(content.get().readLine());
        final int height = Integer.parseInt(content.get().readLine());
        map = map.concat(Integer.toString(width) + "\n" + Integer.toString(height) + "\n");
        String current;
        for (int i = 0; i < height; i++) {
            current = content.get().readLine();
            map = map.concat(current + "\n");
        }
        content.get().close();
    }
}
