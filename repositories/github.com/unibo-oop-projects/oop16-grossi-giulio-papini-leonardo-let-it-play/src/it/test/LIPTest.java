package it.test;

import static it.lttply.model.domain.managers.SQLiteAdvanced.getDBManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.lttply.model.HomeMediaModel;
import it.lttply.model.HomeMediaModelConcrete;
import it.lttply.model.domain.Credits;
import it.lttply.model.domain.CreditsConcrete;
import it.lttply.model.domain.Episode;
import it.lttply.model.domain.EpisodeConcrete;
import it.lttply.model.domain.Movie;
import it.lttply.model.domain.MovieConcrete;
import it.lttply.model.domain.Person;
import it.lttply.model.domain.PersonConcrete;
import it.lttply.model.domain.Picture;
import it.lttply.model.domain.PictureConcrete;
import it.lttply.model.domain.PictureFormat;
import it.lttply.model.domain.Season;
import it.lttply.model.domain.SeasonConcrete;
import it.lttply.model.domain.Source;
import it.lttply.model.domain.TVSerie;
import it.lttply.model.domain.TVSerieConcrete;
import it.lttply.model.domain.managers.Environment;
import it.lttply.model.domain.managers.EnvironmentStandard;
import it.lttply.model.utils.APIUtils;
import it.lttply.model.utils.ConsolePrinter;
import it.lttply.model.utils.ThreadException;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

/**
 * Test class, useful to tests main application components, all methods are
 * called with one thread only. Since some of those tests will use real physical
 * files and folders into the standard (and temporary) locations, we recommend
 * to run them before running the real application instance.
 * <p>
 * You have been warned.
 */
public class LIPTest {
    private static final String ENVIRONMENT_MOVIE_TEST_FOLDER = System.getProperty("user.home") + File.separator
            + "letitplay-environment_only-test";
    private static final int SEASON_NUM = 5;
    private static final int MAX_INT_RANDOM_SIZE = 5000;

    /**
     * Checks whether the database works correctly simulating the file creation,
     * insertions and deletions, finally, simulates some entity creations
     * checking if the builders work properly.
     * <p>
     * Into this method some hard coded references to files in secondary memory
     * are made, but it causes no trouble because the referenced files aren't
     * really present, this is just a simulation.
     */
    @SuppressFBWarnings(value = "DMI_HARDCODED_ABSOLUTE_FILENAME", justification = "It causes no trouble because the referenced files aren't really present, this is just a simulation")
    public void database() {
        ConsolePrinter.getPrinter().printlnProcedureStarted("Testing DATABASE...");
        try {
            getDBManager();
            getDBManager().empty();
        } catch (Exception ex) {
            fail("Unexpected exception!");
        }

        // ============ \\
        // === TAGS === \\
        // ============ \\
        final Map<String, String> tagsAdventureAction = new HashMap<>();
        tagsAdventureAction.put("21", "Thriller");
        tagsAdventureAction.put("31", "Action");
        tagsAdventureAction.put("74", "Romance");
        tagsAdventureAction.put("33", "Adventure");

        final Map<String, String> tagsComedy = new HashMap<>();
        tagsComedy.put("74", "Romance");
        tagsComedy.put("2", "Family");
        tagsComedy.put("98", "Comedy");

        // ==================== \\
        // === PROFILE PICS === \\
        // ==================== \\
        Picture profileBruceWillis = null;
        Picture profileArnoldSchwarzenegger = null;
        boolean thrown = false;
        final Random random = new Random();
        try {
            profileBruceWillis = new PictureConcrete.PictureConcreteBuilder().id("123456")
                    .physicalLocation(Paths.get("C:\\bruceWillis.jpg")).releaseDate(APIUtils.DEFAULT_DATE)
                    .size(random.nextInt(MAX_INT_RANDOM_SIZE)).title("B. Willis Official Portrait").build();
        } catch (IllegalStateException ex) {
            thrown = true;
        }

        assertTrue("The picture creation shouldn't had to be accomplished!", thrown);
        assertNull("The Bruce Willis picture should be null!", profileBruceWillis);

        try {
            profileBruceWillis = new PictureConcrete.PictureConcreteBuilder().id("123456")
                    .physicalLocation(Paths.get("C:\\bruceWillis.jpg")).releaseDate(APIUtils.DEFAULT_DATE)
                    .size(random.nextInt(MAX_INT_RANDOM_SIZE)).title("B. Willis Official Portrait")
                    .format(PictureFormat.W_342).build();
        } catch (Exception ex) {
            fail("Required fields are set, no exception expected here!");
        }

        try {
            profileArnoldSchwarzenegger = new PictureConcrete.PictureConcreteBuilder().id("654321")
                    .physicalLocation(Paths.get("/home/foo/movies/pics/arnold.png")).releaseDate(APIUtils.DEFAULT_DATE)
                    .size(random.nextInt(MAX_INT_RANDOM_SIZE)).title("A. Schwarzenegger Funny Pic")
                    .format(PictureFormat.ORIGINAL).build();
        } catch (Exception ex) {
            fail("Required fields are set, no exception expected here!");
        }

        boolean check = false;
        try {
            profileArnoldSchwarzenegger.getPoster().get();
        } catch (UnsupportedOperationException ex) {
            check = true;
        }
        assertTrue("You shouldn't be able to get a poster from a Picture", check);

        // =============== \\
        // === PERSONS === \\
        // =============== \\
        final Person per1 = new PersonConcrete("PER_01", "Bruce Willis", profileBruceWillis);
        final Person per2 = new PersonConcrete("PER_44", "James Belushi", null);
        final Person per3 = new PersonConcrete("PER_45", "Larry Joe Campbell", null);
        final Person per4 = new PersonConcrete("PER_88", "Walter Hill", null);
        final Person per5 = new PersonConcrete("PER_99", "Arnold Schwarzenegger", profileArnoldSchwarzenegger);
        final Person per6 = new PersonConcrete("PER_32", "Jensen Ackles", null);
        final Person per7 = new PersonConcrete("PER_56", "Jared Padalecki", null);

        assertFalse("James Belushi doesn't have any picture!", per2.getPicture().isPresent());
        assertFalse("Jensen Ackles doesn't have any picture!", per6.getPicture().isPresent());

        // =============== \\
        // === CREDITS === \\
        // =============== \\
        final Credits crdDanko = new CreditsConcrete(per4, Arrays.asList(per5, per2));
        final Credits crdTheExpendables = new CreditsConcrete(null, Arrays.asList(per1, per5));
        final Credits crdAccordingToJim = new CreditsConcrete(null, Arrays.asList(per2, per3));
        final Credits crdSupernatural = new CreditsConcrete(null, Arrays.asList(per6, per7));

        assertNull("The Expendables doesn't have any creator!", crdTheExpendables.getCreator());
        assertFalse("According to Jim must have 2 actors!", crdAccordingToJim.getCast().isEmpty());

        // ========================= \\
        // === POSTER & BACKDROP === \\
        // ========================= \\
        final Picture posterTheExpendables = new PictureConcrete.PictureConcreteBuilder().id("POS_SDA")
                .physicalLocation(Paths.get("C:\\Users\\pippo\\expPstr.jpeg")).releaseDate(APIUtils.DEFAULT_DATE)
                .size(random.nextInt(MAX_INT_RANDOM_SIZE)).title("The Expendables Official Poster")
                .format(PictureFormat.W_HD_READY).author("Universal Pictures").tags(tagsAdventureAction)
                .rating(random.nextDouble()).overview("Back for war").build();

        final Picture backdropAccordingToJim = new PictureConcrete.PictureConcreteBuilder().id("BCK_JIM")
                .physicalLocation(Paths.get("C:\\Users\\tvserie\\jim.jpg")).releaseDate(APIUtils.DEFAULT_DATE)
                .size(random.nextInt(MAX_INT_RANDOM_SIZE)).title("According To Jim").format(PictureFormat.W_HD_READY)
                .author("Sony").tags(tagsComedy).rating(random.nextDouble()).build();

        try {
            assertEquals(tagsComedy, backdropAccordingToJim.getTags().get());
        } catch (NoSuchElementException ex) {
            fail("The Backdrop for According to Jim should contains tags!");
        }

        // ============== \\
        // === MOVIES === \\
        // ============== \\
        final Movie mvDanko = new MovieConcrete.MovieBuilderConcrete().id("MOV_DNK")
                .physicalLocation(Paths.get("D:\\danko.mkv")).releaseDate(APIUtils.DEFAULT_DATE)
                .size(random.nextInt(MAX_INT_RANDOM_SIZE)).title("Danko").tags(tagsAdventureAction)
                .countries(new HashSet<>(Arrays.asList("US", "RU"))).duration(random.nextInt(MAX_INT_RANDOM_SIZE))
                .rating(random.nextDouble()).credits(crdDanko).build();
        assertTrue("Expected title: \"Danko\", it was " + mvDanko.getTitle(), mvDanko.getTitle().equals("Danko"));

        final Movie mvExpendables = new MovieConcrete.MovieBuilderConcrete().id("MOV_EXP")
                .physicalLocation(Paths.get("D:\\the expendables.mkv")).releaseDate(APIUtils.DEFAULT_DATE)
                .size(random.nextInt(MAX_INT_RANDOM_SIZE)).title("The Expendables").tags(tagsAdventureAction)
                .countries(new HashSet<>(Arrays.asList("US"))).duration(random.nextInt(MAX_INT_RANDOM_SIZE))
                .rating(random.nextDouble()).credits(crdTheExpendables).poster(posterTheExpendables).build();

        // ================= \\
        // === TV SERIES === \\
        // ================= \\
        final Episode ep1 = new EpisodeConcrete.EpisodeConcreteBuilder().id("TV_EP_1")
                .physicalLocation(Paths.get("C:\\Users\\1x01.avi")).releaseDate(APIUtils.DEFAULT_DATE)
                .size(random.nextInt(MAX_INT_RANDOM_SIZE)).duration(random.nextInt(MAX_INT_RANDOM_SIZE))
                .title("Una pistola dal passato").progressiveNumber(1).build();

        final Episode ep2 = new EpisodeConcrete.EpisodeConcreteBuilder().id("TV_EP_2")
                .physicalLocation(Paths.get("C:\\Users\\1x02.avi")).releaseDate(APIUtils.DEFAULT_DATE)
                .size(random.nextInt(MAX_INT_RANDOM_SIZE)).duration(random.nextInt(MAX_INT_RANDOM_SIZE))
                .title("Nella spirale del gioco").progressiveNumber(2).build();

        final Season ssn1 = new SeasonConcrete.SeasonConcreteBuilder().id("TV_SEAS_" + SEASON_NUM)
                .releaseDate(APIUtils.DEFAULT_DATE).title("Season " + SEASON_NUM).progressiveNumber(SEASON_NUM)
                .elements(new HashSet<>(Arrays.asList(ep1, ep2))).build();
        final Set<Season> ssns = new HashSet<>(Arrays.asList(ssn1));
        assertTrue(String.valueOf(ssn1.getProgressiveNumber()).equals(String.valueOf(SEASON_NUM)));

        final TVSerie supernatural = new TVSerieConcrete.TVSerieConcreteBuilder().id("TVSERIE_SUP")
                .physicalLocation(Paths.get("C:\\Users\\")).releaseDate(APIUtils.DEFAULT_DATE).title("Supernatural")
                .elements(ssns).credits(crdSupernatural).build();

        try {
            getDBManager().addMovie(mvExpendables);
            getDBManager().addMovie(mvDanko);

            final Movie dnk = getDBManager().getMovie(mvDanko.getID());
            assertEquals(dnk.getID(), mvDanko.getID());
            assertEquals(dnk.getDuration(), mvDanko.getDuration());
            assertEquals(dnk.getCountries(), mvDanko.getCountries());
            assertEquals(dnk.getTitle(), mvDanko.getTitle());

            getDBManager().addTVSerie(supernatural);
        } catch (Exception ex) {
            fail("No exception expected here!");
        }

        boolean check2 = false;
        try {
            getDBManager().addMovie(mvDanko);
        } catch (SQLException e) {
            check2 = true;
        }
        assertTrue("Danko should be already present!", check2);

        check2 = false;
        try {
            getDBManager().addTVSerie(supernatural);
        } catch (SQLException e) {
            check2 = true;
        }
        assertTrue("Supernatural should be already present!", check2);

        try {
            getDBManager().empty();
        } catch (Exception e) {
            fail();
        }
        ConsolePrinter.getPrinter().printlnSuccess("DATABASE test result: PASS");

    }

    /**
     * 
     * Checks whether the {@link Environment} works correctly.
     * <p>
     * This test will use real physical files into the standard prefixed
     * locations, we recommend to run it before running the real application
     * instance, because it will probably delete everything.
     */
    @Test
    public void environment() {
        try {
            FileUtils.forceDelete(new File(EnvironmentStandard.getDatabasePreferredLocation()));
        } catch (FileNotFoundException ex) {
            ConsolePrinter.getPrinter().printlnDebug("Database file not present, skipping delete procedure...");
        } catch (IOException e3) {
            fail();
        }

        ConsolePrinter.getPrinter().printlnProcedureStarted("Testing ENVIRONMENT...");
        final Environment env = new EnvironmentStandard();

        try {
            env.initialize();
        } catch (IOException e2) {
            fail();
        }

        try {
            env.initialize();
        } catch (IllegalStateException e) {
            ConsolePrinter.getPrinter().printlnDebug("Environment already initialized, ok!");
        } catch (Exception e2) {
            fail();
        }

        boolean check = false;
        try {
            env.setMoviesTrackingFolder(new File(""));
        } catch (IllegalStateException ex) {
            check = true;
        } catch (Exception e) {
            fail();
        }
        // Is it possible to set an empty directory (resets the dirctory)
        assertFalse(check);

        try {
            // Calling the database test function
            this.database();
        } catch (Exception ex) {
            fail("No exceptions expected here!");
        }

        final File tmpTestFolder = new File(ENVIRONMENT_MOVIE_TEST_FOLDER);
        try {
            FileUtils.forceMkdir(tmpTestFolder);
            env.setMoviesTrackingFolder(tmpTestFolder);
        } catch (IOException e1) {
            fail();
        }

        // Creating fake files
        try {
            FileUtils.writeLines(new File(ENVIRONMENT_MOVIE_TEST_FOLDER + File.separator + "Terminator.mkv"),
                    new LinkedList<>());
            FileUtils.writeLines(new File(ENVIRONMENT_MOVIE_TEST_FOLDER + File.separator + "The Matrix.avi"),
                    new LinkedList<>());
            FileUtils.writeLines(new File(ENVIRONMENT_MOVIE_TEST_FOLDER + File.separator + "Dummy Movie.mp4"),
                    new LinkedList<>());
        } catch (IOException e1) {
            fail();
        }

        assertEquals(3, env.getTrackedMovies().size());

        try {
            getDBManager().empty();
        } catch (SQLException e) {
            fail();
        }

        // Resetting the tracked folder
        boolean check2 = false;
        try {
            env.setMoviesTrackingFolder(new File(""));
        } catch (IllegalStateException ex) {
            check2 = true;
        } catch (Exception e) {
            fail();
        }
        // Is it possible to set an empty directory (resets the dirctory)
        assertFalse(check2);

        ConsolePrinter.getPrinter().printlnSuccess("ENVIRONMENT test result: PASS");
    }

    /**
     * Checks whether the whole model (with its sub components) works correctly
     * together. Finally, checks whether the VLC libraries are present.
     */
    @Test
    public void model() {
        ConsolePrinter.getPrinter().printlnProcedureStarted("Testing the whole HomeModel...");
        if (!new NativeDiscovery().discover()) {
            fail();
        }

        HomeMediaModel model = null;
        try {
            model = new HomeMediaModelConcrete();
        } catch (IOException e) {
            fail();
        }

        assertNotNull(model);

        try {
            model.setMovieTrackingFolder(new File(ENVIRONMENT_MOVIE_TEST_FOLDER));
            model.reload(Source.SECONDARY, true);
        } catch (IOException | SQLException | InterruptedException | ThreadException e) {
            fail();
        }

        assertEquals(2, model.getMovies(null).size());
        assertEquals(ENVIRONMENT_MOVIE_TEST_FOLDER, model.getMoviesTrackingFolder());

        try {
            model.setMovieTrackingFolder(null);
        } catch (IOException | SQLException e) {
            fail();
        }
    }

}
