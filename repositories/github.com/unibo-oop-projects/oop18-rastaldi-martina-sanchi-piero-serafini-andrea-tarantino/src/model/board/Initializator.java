package model.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.util.Pair;
import model.elements.ElementFactory;
import model.elements.Hero;
import model.elements.Minotaurus;
import model.elements.Wall;
import model.settings.SettingsManager;
import model.settings.SettingsManagerInterface;
import utilities.Colors;
import utilities.Status;

/**
 * This class initialize every aspect of the board. Andrea Serafini.
 *
 */
public final class Initializator {

    private static final String FILE_MURI = "muri.txt";
    private static final String FILE_SIEPI = "siepi.txt";
    private static final String FILE_EROI = "eroi.txt";
    private static final String FILE_TEMPIO = "tempio.txt";
    private static final String FILE_ARRIVO = "arrivo.txt";

    private static final int EASY_LIMIT = 19;
    private static final String EASY_PATH = "/boardTxts/Easy/";
    private static final int MEDIUM_LIMIT = 29;
    private static final String MEDIUM_PATH = "/boardTxts/Medium/";
    private static final int HARD_LIMIT = 29;
    private static final String HARD_PATH = "/boardTxts/Hard/";
    private static final int WILD_LIMIT = 29;
    private static final String WILD_PATH = "/boardTxts/Wild/";

    private static Initializator gameInitializator;

    private final int limit;
    private final String path;

    private final ElementFactory factory = new ElementFactory();

    private Integer id = 1;
    private final SettingsManagerInterface manager = SettingsManager.getLog();
    private Map<Pair<Integer, Integer>, Pair<Status, Colors>> occupationList = new HashMap<>();
    private Map<Pair<Integer, Integer>, Hero> heroMap = new HashMap<>();

    private Map<Pair<Integer, Integer>, Wall> wallMap = new HashMap<>();

    private Minotaurus minotaurus;

    private Initializator() {

        switch (this.manager.getSettings().getMaze()) {
        case 1:
            limit = EASY_LIMIT;
            path = EASY_PATH;
            break;
        case 2:
            limit = MEDIUM_LIMIT;
            path = MEDIUM_PATH;
            break;
        case 3:
            limit = HARD_LIMIT;
            path = HARD_PATH;
            break;
        case 4:
            limit = WILD_LIMIT;
            path = WILD_PATH;
            break;

        default:
            limit = MEDIUM_LIMIT;
            path = MEDIUM_PATH;
            break;
        }

        this.manager.getSettings().setBoardLimit(limit);
        this.starter();
    }

    private Pair<Integer, Integer> getCoordAtQuarter(final int x, final int y, final int i) {
        switch (i) {
        case 1:
            return new Pair<>(limit - x, y);
        case 2:
            return new Pair<>(x, y);
        case 3:
            return new Pair<>(x, limit - y);
        case 4:
            return new Pair<>(limit - x, limit - y);
        default:
            return new Pair<>(x, y);
        }

    }

    /**
     *
     * @return the hero map for the game.
     */
    public Map<Pair<Integer, Integer>, Hero> getHeroMap() {
        return this.heroMap;
    }

    /**
     *
     * @return minotaurus
     */
    public Minotaurus getMinotaurus() {
        return this.minotaurus;
    }

    /**
     *
     * @return occupation list
     */
    public Map<Pair<Integer, Integer>, Pair<Status, Colors>> getOccupationList() {
        return this.occupationList;
    }

    /**
     *
     * @return wall map
     */
    public Map<Pair<Integer, Integer>, Wall> getWallMap() {
        return this.wallMap;
    }

    private void initialize(final BufferedReader file, final Status state, final Colors color) throws IOException {

        final Scanner scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            try {
                final int x = scanner.nextInt();
                final int y = scanner.nextInt();
                this.occupationList.put(this.getCoordAtQuarter(x, y, 1), new Pair<>(state, color));
                this.occupationList.put(this.getCoordAtQuarter(x, y, 2), new Pair<>(state, color));
                this.occupationList.put(this.getCoordAtQuarter(x, y, 3), new Pair<>(state, color));
                this.occupationList.put(this.getCoordAtQuarter(x, y, 4), new Pair<>(state, color));
            } catch (final NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        scanner.close();
    }

    private void initializeColoured(final BufferedReader file, final Status state) throws IOException {

        final Scanner scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            try {
                final int x = scanner.nextInt();
                final int y = scanner.nextInt();
                this.occupationList.put(this.getCoordAtQuarter(x, y, 1), new Pair<>(state, Colors.Blue));
                this.occupationList.put(this.getCoordAtQuarter(x, y, 2), new Pair<>(state, Colors.Red));
                this.occupationList.put(this.getCoordAtQuarter(x, y, 3), new Pair<>(state, Colors.Yellow));
                this.occupationList.put(this.getCoordAtQuarter(x, y, 4), new Pair<>(state, Colors.White));
            } catch (final NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        scanner.close();
    }

    private void initializeHeroMap(final BufferedReader file) throws IOException {

        this.manager.getSettings().getMatchPlayers();
        final Scanner scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            try {
                final int x = scanner.nextInt();
                final int y = scanner.nextInt();

                this.manager.getSettings().getMatchPlayers().stream().forEach(e -> {
                    switch (e.getColor()) {
                    case Blue:
                        this.heroMap.put(this.getCoordAtQuarter(x, y, 1),
                                this.factory.getHero(this.getCoordAtQuarter(x, y, 1), e, this.id));
                        break;

                    case Red:
                        this.heroMap.put(this.getCoordAtQuarter(x, y, 2),
                                this.factory.getHero(this.getCoordAtQuarter(x, y, 2), e, this.id));
                        break;

                    case Yellow:
                        this.heroMap.put(this.getCoordAtQuarter(x, y, 3),
                                this.factory.getHero(this.getCoordAtQuarter(x, y, 3), e, this.id));
                        break;

                    case White:
                        this.heroMap.put(this.getCoordAtQuarter(x, y, 4),
                                this.factory.getHero(this.getCoordAtQuarter(x, y, 4), e, this.id));
                        break;

                    default:
                        break;
                    }
                });

                this.id++;
            } catch (final NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        scanner.close();
    }

    private void initializeWallMap(final BufferedReader file) throws IOException {

        final Scanner scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            try {
                final int x1 = scanner.nextInt();
                final int y1 = scanner.nextInt();

                final int x2 = scanner.nextInt();
                final int y2 = scanner.nextInt();

                final Wall wall1 = this.factory.getWall(this.getCoordAtQuarter(x1, y1, 1),
                        this.getCoordAtQuarter(x2, y2, 1));
                final Wall wall2 = this.factory.getWall(this.getCoordAtQuarter(x1, y1, 2),
                        this.getCoordAtQuarter(x2, y2, 2));
                final Wall wall3 = this.factory.getWall(this.getCoordAtQuarter(x1, y1, 3),
                        this.getCoordAtQuarter(x2, y2, 3));
                final Wall wall4 = this.factory.getWall(this.getCoordAtQuarter(x1, y1, 4),
                        this.getCoordAtQuarter(x2, y2, 4));
                this.wallMap.put(this.getCoordAtQuarter(x1, y1, 1), wall1);
                this.wallMap.put(this.getCoordAtQuarter(x2, y2, 1), wall1);
                this.wallMap.put(this.getCoordAtQuarter(x1, y1, 2), wall2);
                this.wallMap.put(this.getCoordAtQuarter(x2, y2, 2), wall2);
                this.wallMap.put(this.getCoordAtQuarter(x1, y1, 3), wall3);
                this.wallMap.put(this.getCoordAtQuarter(x2, y2, 3), wall3);
                this.wallMap.put(this.getCoordAtQuarter(x1, y1, 4), wall4);
                this.wallMap.put(this.getCoordAtQuarter(x2, y2, 4), wall4);

            } catch (final NumberFormatException ex) {
                ex.printStackTrace();
            }

        }
        scanner.close();
    }

    /**
     * Restore settings.
     *
     */
    public void restoreBoard() {

        this.id = 1;
        this.heroMap = new HashMap<>();
        this.wallMap = new HashMap<>();
        this.occupationList = new HashMap<>();
        this.starter();
    }

    private void starter() {
        BufferedReader muri;
        BufferedReader siepi;
        BufferedReader eroi;
        BufferedReader arrivo;
        BufferedReader partenza;
        BufferedReader tempio;

        muri = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream((this.path + FILE_MURI))));
        siepi = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream((this.path + FILE_SIEPI))));
        eroi = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream((this.path + FILE_EROI))));
        arrivo = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream((this.path + FILE_ARRIVO))));
        partenza = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream((this.path + FILE_EROI))));
        tempio = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream((this.path + FILE_TEMPIO))));

        try {
            this.initialize(siepi, Status.SIEPE, Colors.Green);
            this.initialize(tempio, Status.TEMPIO, Colors.Black);
            this.initializeColoured(arrivo, Status.ARRIVO);
            this.initializeColoured(partenza, Status.PARTENZA);

            this.initializeHeroMap(eroi);
            this.initializeWallMap(muri);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        this.minotaurus = this.factory.getMinotaurus(SettingsManager.getLog().getSettings().getMinotaurusSteps());

    }

    /**
     * Return the Singleton of this class.
     *
     * @return Initializator
     */
    public static synchronized Initializator getLog() {
        if (gameInitializator == null) {
            gameInitializator = new Initializator();
        }
        return gameInitializator;
    }
}
