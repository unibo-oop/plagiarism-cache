package it.lttply.model.domain.managers;

import static it.lttply.model.domain.managers.EnvironmentStandard.downloadMoviePicture;
import static it.lttply.model.domain.managers.EnvironmentStandard.downloadPersonProfileImage;
import static it.lttply.model.domain.managers.EnvironmentStandard.getFileOrFolderYear;
import static it.lttply.model.domain.managers.EnvironmentStandard.getFilenameFromFile;
import static it.lttply.model.domain.managers.EnvironmentStandard.getSizeFromFile;
import static it.lttply.model.domain.managers.EnvironmentStandard.getVideoDuration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.lttply.model.domain.Credits;
import it.lttply.model.domain.CreditsConcrete;
import it.lttply.model.domain.Movie;
import it.lttply.model.domain.MovieConcrete.MovieBuilderConcrete;
import it.lttply.model.domain.Person;
import it.lttply.model.domain.PersonConcrete;
import it.lttply.model.domain.PictureConcrete.PictureConcreteBuilder;
import it.lttply.model.domain.PictureFormat;
import it.lttply.model.domain.TVSerie;
import it.lttply.model.utils.APIUtils;
import it.lttply.model.utils.InternetConnectionException;
import it.lttply.model.utils.PictureType;

/**
 * This is an utility class useful to parse JSON and download metainfo and
 * images of {@link Movie}s and {@link TVSerie}, some of its functions depends
 * from {@link EnvironmentStandard} functions.
 */
public final class ParserStandard {

    private static final String NULL_STRING_API = "null";
    private static final int MAX_ACTORS = 5;

    private ParserStandard() {

    }

    /**
     * Gets a {@link Movie} starting from a movie file. This method will
     * download all needed metainfo and pictures into the prefixed folders.
     * 
     * @param movie
     *            the file to be analyzed
     * @return the {@link Movie} which references to the movie on secondary
     *         memory
     * @throws InternetConnectionException
     *             thrown if an exception occurs while downloading metadata
     * @throws InterruptedException
     *             thrown if a thread has been interrupted unexpectedly
     * @throws IOException
     *             thrown if an exception occurs while writing metadata on
     *             secondary memory
     */
    public static Movie getMovieFromJSON(final File movie)
            throws InternetConnectionException, InterruptedException, IOException {

        final String name = getFilenameFromFile(movie);
        final Optional<Integer> year = getFileOrFolderYear(movie);
        final Optional<String> json = APIUtils.getInstance().getJSONMovieSearch(name, year);
        if (!json.isPresent()) {
            return null;
        }

        // Retrieving the movie informations
        final JSONArray results = (JSONArray) new JSONObject(json.get()).get("results");

        final String id;
        try {
            id = String.valueOf(((JSONObject) results.get(0)).get("id"));
        } catch (JSONException ex) {
            return null;
        }
        final Optional<String> json2 = APIUtils.getInstance().getJSONMovieDetails(id);
        if (!json2.isPresent()) {
            return null;
        }

        // Creating MOVIE
        final JSONObject finalJSON = new JSONObject(json2.get());
        final MovieBuilderConcrete bld = new MovieBuilderConcrete();

        // Adding BACKDROP
        String bdPath = finalJSON.get("backdrop_path").toString();
        if (!bdPath.equals(NULL_STRING_API)) {
            bdPath = bdPath.substring(1);
            final URL urlBD = APIUtils.getInstance().getURLImage(bdPath, PictureFormat.ORIGINAL,
                    PictureType.IMAGE_BACKDROP);
            final File bdLocation = downloadMoviePicture(urlBD, PictureType.IMAGE_BACKDROP);
            final String bdTitle = "Backdrop for " + name;
            final long size = getSizeFromFile(bdLocation);
            bld.backdrop(new PictureConcreteBuilder().id(FilenameUtils.getBaseName(bdPath))
                    .physicalLocation(bdLocation.toPath()).title(bdTitle).format(PictureFormat.ORIGINAL)
                    .releaseDate(LocalDate.now()).size((int) size).build());
        }

        // Adding COUNTRIES
        final JSONArray prodCountries = finalJSON.getJSONArray("production_countries");
        final Set<String> countries = new HashSet<>();
        for (int i = 0; i < prodCountries.length(); i++) {
            countries.add(((JSONObject) prodCountries.get(i)).get("iso_3166_1").toString());
        }

        if (!countries.isEmpty()) {
            bld.countries(countries);
        }

        // Adding CREDITS
        final Credits credits = getMovieCredits(id);
        if (credits != null) {
            bld.credits(credits);
        }

        bld.duration((int) getVideoDuration(movie));
        bld.id(id);
        bld.overview(String.valueOf(finalJSON.get("overview")));
        bld.physicalLocation(Paths.get(movie.toString()));

        // Adding POSTER
        String psPath = finalJSON.get("poster_path").toString();
        if (!psPath.equals(NULL_STRING_API)) {
            psPath = psPath.substring(1);
            final URL urlPS = APIUtils.getInstance().getURLImage(psPath, PictureFormat.W_500, PictureType.IMAGE_POSTER);
            final File psLocation = downloadMoviePicture(urlPS, PictureType.IMAGE_POSTER);
            final String psTitle = "Poster for " + name;
            final long size = getSizeFromFile(psLocation);
            bld.poster(new PictureConcreteBuilder().id(FilenameUtils.getBaseName(psPath))
                    .physicalLocation(psLocation.toPath()).title(psTitle).format(PictureFormat.W_500)
                    .releaseDate(LocalDate.now()).size((int) size).build());
        }

        bld.rating(Double.valueOf(finalJSON.get("vote_average").toString()));
        if (finalJSON.getString("release_date").isEmpty()) {
            bld.releaseDate(APIUtils.DEFAULT_DATE);
        } else {
            bld.releaseDate(LocalDate.parse(finalJSON.getString("release_date").toString(),
                    DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
        }

        bld.size((int) getSizeFromFile(movie));

        // Aggiunta TAGS
        final JSONArray genres = finalJSON.getJSONArray("genres");
        final Map<String, String> tags = new HashMap<>();
        for (int i = 0; i < genres.length(); i++) {
            tags.put(((JSONObject) genres.get(i)).get("id").toString(),
                    ((JSONObject) genres.get(i)).get("name").toString());
        }
        if (!tags.isEmpty()) {
            bld.tags(tags);
        }

        bld.title(finalJSON.get("title").toString());
        return bld.build();
    }

    /**
     * Gets the {@link TVSerie} starting from a folder file. This method will
     * download all needed metainfo and pictures into the prefixed folders.
     * 
     * @param tvSerieRootFolder
     *            the folder to be analyzed
     * @return the {@link TVSerie} which references to the tv serie on secondary
     *         memory
     * @throws InternetConnectionException
     *             thrown if an exception occurs while downloading metadata
     * @throws InterruptedException
     *             thrown if a thread has been interrupted unexpectedly
     * @throws IOException
     *             thrown if an exception occurs while writing metadata on
     *             secondary memory
     * @throws JAXBException
     */
    public static TVSerie getTVSerieFromJSON(final File tvSerieRootFolder)
            throws InterruptedException, InternetConnectionException, IOException {
        throw new UnsupportedOperationException();

        /*
         * final TVSerie tvSerie = null; final String tvSeriesName =
         * getFilenameFromFile(tvSerieRootFolder); final Optional<Integer> year
         * = getFileOrFolderYear(tvSerieRootFolder); final Optional<String> json
         * = APIUtils.getInstance().getJSONTVSerieSearch(tvSeriesName, year); if
         * (!json.isPresent()) { return null; }
         * 
         * // Retrieving the tv series informations final JSONArray results =
         * (JSONArray) new JSONObject(json.get()).get("results");
         * 
         * final String id; try { id = String.valueOf(((JSONObject)
         * results.get(0)).get("id"));
         * 
         * } catch (JSONException ex) { return null; }
         * 
         * final Optional<String> json2 =
         * APIUtils.getInstance().getJSONTVSerieDetails(id); if
         * (!json2.isPresent()) { return null; }
         * 
         * // Creating MOVIE final JSONObject finalJSON = new
         * JSONObject(json2.get()); final TVSerieConcreteBuilder bld = new
         * TVSerieConcreteBuilder(); bld.id(id);
         * 
         * // Adding BACKDROP String bdPath =
         * finalJSON.get("backdrop_path").toString(); if
         * (!bdPath.equals(NULL_STRING_API)) { bdPath = bdPath.substring(1);
         * final URL urlBD = APIUtils.getInstance().getURLImage(bdPath,
         * PictureFormat.ORIGINAL, PictureType.IMAGE_BACKDROP); final File
         * bdLocation = downloadTVSeriePicture(urlBD,
         * PictureType.IMAGE_BACKDROP); final String bdTitle = "Backdrop for " +
         * tvSeriesName; final long size = getSizeFromFile(bdLocation);
         * bld.backdrop(new
         * PictureConcreteBuilder().id(FilenameUtils.getBaseName(bdPath))
         * .physicalLocation(bdLocation.toPath()).title(bdTitle).format(
         * PictureFormat.ORIGINAL) .releaseDate(LocalDate.now()).size((int)
         * size).build()); }
         * 
         * // Adding COUNTRIES final JSONArray prodCountries =
         * finalJSON.getJSONArray("origin_country"); final Set<String> countries
         * = new HashSet<>(); for (int i = 0; i < prodCountries.length(); i++) {
         * countries.add(prodCountries.get(i).toString()); }
         * 
         * if (!countries.isEmpty()) { bld.countries(countries); }
         * 
         * // Adding CREDITS final Credits credits = getTVSeriesCredits(id); if
         * (credits != null) { bld.credits(credits); }
         * 
         * bld.id(id); bld.overview(String.valueOf(finalJSON.get("overview")));
         * bld.physicalLocation(Paths.get(tvSerieRootFolder.toString()));
         * 
         * // Adding POSTER String psPath =
         * finalJSON.get("poster_path").toString(); if
         * (!psPath.equals(NULL_STRING_API)) { psPath = psPath.substring(1);
         * final URL urlPS = APIUtils.getInstance().getURLImage(psPath,
         * PictureFormat.W_500, PictureType.IMAGE_POSTER); final File psLocation
         * = downloadTVSeriePicture(urlPS, PictureType.IMAGE_POSTER); final
         * String psTitle = "Poster for " + tvSeriesName; final long size =
         * getSizeFromFile(psLocation); bld.poster(new
         * PictureConcreteBuilder().id(FilenameUtils.getBaseName(psPath))
         * .physicalLocation(psLocation.toPath()).title(psTitle).format(
         * PictureFormat.W_500) .releaseDate(LocalDate.now()).size((int)
         * size).build()); }
         * 
         * bld.rating(Double.valueOf(finalJSON.get("vote_average").toString()));
         * if (finalJSON.getString("first_air_date").isEmpty()) {
         * bld.releaseDate(APIUtils.DEFAULT_DATE); } else {
         * bld.releaseDate(LocalDate.parse(finalJSON.getString("first_air_date")
         * .toString(),
         * DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN))); }
         * 
         * // Aggiunta TAGS final JSONArray genres =
         * finalJSON.getJSONArray("genres"); final Map<String, String> tags =
         * new HashMap<>(); for (int i = 0; i < genres.length(); i++) {
         * tags.put(((JSONObject) genres.get(i)).get("id").toString(),
         * ((JSONObject) genres.get(i)).get("name").toString()); } if
         * (!tags.isEmpty()) { bld.tags(tags); }
         * 
         * bld.title(finalJSON.get("name").toString());
         * 
         * // TODO: arrivato qui
         * 
         * // ADDING SEASONS final Map<Integer, List<File>> episodesMap = new
         * HashMap<>(); FileUtils.listFiles(tvSerieRootFolder,
         * SUPPORTED_EXTENSIONS.toArray(new
         * String[SUPPORTED_EXTENSIONS.size()]), false).forEach(file -> { final
         * Pair<Integer, Integer> ssep =
         * getSeasonAndEpisodeValuesFromEpisode(file); if
         * (!episodesMap.containsKey(ssep.getKey())) {
         * episodesMap.put(ssep.getKey(), new
         * LinkedList<>(Arrays.asList(file))); } else {
         * episodesMap.get(ssep.getKey()).add(file); } });
         * 
         * System.out.println(episodesMap);
         * 
         * return bld.build();
         */
    }

    private static Credits getMovieCredits(final String movieID)
            throws InterruptedException, InternetConnectionException, IOException {
        final Optional<String> crd = APIUtils.getInstance().getJSONMovieCredits(movieID);
        if (!crd.isPresent()) {
            return null;
        }

        final JSONObject obj = new JSONObject(crd.get());
        final JSONArray castArr = obj.getJSONArray("cast");
        final List<Person> cast = new LinkedList<>();
        for (int i = 0; i < castArr.length() && i < MAX_ACTORS; i++) {
            final String name = ((JSONObject) castArr.get(i)).get("name").toString();
            final String id = ((JSONObject) castArr.get(i)).get("id").toString();
            final String profilePic = ((JSONObject) castArr.get(i)).get("profile_path").toString();

            if (!profilePic.equals(NULL_STRING_API)) {
                final URL urlPic = APIUtils.getInstance().getURLImage(profilePic, PictureFormat.ORIGINAL,
                        PictureType.IMAGE_PROFILE_PHOTO);
                final File picLocation = downloadPersonProfileImage(urlPic);
                cast.add(new PersonConcrete(id, name,
                        new PictureConcreteBuilder().id(FilenameUtils.getBaseName(profilePic))
                                .physicalLocation(picLocation.toPath()).format(PictureFormat.ORIGINAL)
                                .releaseDate(LocalDate.now()).size((int) getSizeFromFile(picLocation))
                                .title("Profile image for " + name).build()));
            } else {
                cast.add(new PersonConcrete(id, name, null));
            }

        }

        final JSONArray crewArr = obj.getJSONArray("crew");
        Person regist = null;

        boolean check = false;
        for (int i = 0; i < crewArr.length() && !check; i++) {
            final String job = ((JSONObject) crewArr.get(i)).get("job").toString();
            if (job.equals("Director")) {
                final String name = ((JSONObject) crewArr.get(i)).get("name").toString();
                final String id = ((JSONObject) crewArr.get(i)).get("id").toString();
                String profilePic = ((JSONObject) crewArr.get(i)).get("profile_path").toString();
                check = true;

                if (!profilePic.equals(NULL_STRING_API)) {
                    profilePic = profilePic.substring(1);
                    final URL urlPic = APIUtils.getInstance().getURLImage(profilePic, PictureFormat.ORIGINAL,
                            PictureType.IMAGE_PROFILE_PHOTO);
                    final File picLocation = downloadPersonProfileImage(urlPic);
                    regist = new PersonConcrete(id, name,
                            new PictureConcreteBuilder().id(FilenameUtils.getBaseName(profilePic))
                                    .physicalLocation(picLocation.toPath()).format(PictureFormat.ORIGINAL)
                                    .releaseDate(LocalDate.now()).size((int) getSizeFromFile(picLocation))
                                    .title("Profile image for " + name).build());
                } else {
                    regist = new PersonConcrete(id, name, null);
                }

            }
        }

        return new CreditsConcrete(regist, cast);

    }

    /*
     * private static Credits getTVSeriesCredits(final String movieID) throws
     * InterruptedException, InternetConnectionException, IOException { // TODO:
     * da fare, solo copiato final Optional<String> crd =
     * APIUtils.getInstance().getJSONTVSerieCredits(movieID); if
     * (!crd.isPresent()) { return null; }
     * 
     * final JSONObject obj = new JSONObject(crd.get()); final JSONArray castArr
     * = obj.getJSONArray("cast"); final List<Person> cast = new LinkedList<>();
     * for (int i = 0; i < castArr.length() && i < MAX_ACTORS; i++) { final
     * String name = ((JSONObject) castArr.get(i)).get("name").toString(); final
     * String id = ((JSONObject) castArr.get(i)).get("id").toString(); final
     * String profilePic = ((JSONObject)
     * castArr.get(i)).get("profile_path").toString();
     * 
     * if (!profilePic.equals(NULL_STRING_API)) { final URL urlPic =
     * APIUtils.getInstance().getURLImage(profilePic, PictureFormat.ORIGINAL,
     * PictureType.IMAGE_PROFILE_PHOTO); final File picLocation =
     * downloadPersonProfileImage(urlPic); cast.add(new PersonConcrete(id, name,
     * new PictureConcreteBuilder().id(FilenameUtils.getBaseName(profilePic))
     * .physicalLocation(picLocation.toPath()).format(PictureFormat.ORIGINAL)
     * .releaseDate(LocalDate.now()).size((int) getSizeFromFile(picLocation))
     * .title("Profile image for " + name).build())); } else { cast.add(new
     * PersonConcrete(id, name, null)); }
     * 
     * }
     * 
     * final JSONArray crewArr = obj.getJSONArray("crew"); Person regist = null;
     * 
     * boolean check = false; for (int i = 0; i < crewArr.length() && !check;
     * i++) { final String job = ((JSONObject)
     * crewArr.get(i)).get("job").toString(); if (job.equals("Director")) {
     * final String name = ((JSONObject) crewArr.get(i)).get("name").toString();
     * final String id = ((JSONObject) crewArr.get(i)).get("id").toString();
     * String profilePic = ((JSONObject)
     * crewArr.get(i)).get("profile_path").toString(); check = true;
     * 
     * if (!profilePic.equals(NULL_STRING_API)) { profilePic =
     * profilePic.substring(1); final URL urlPic =
     * APIUtils.getInstance().getURLImage(profilePic, PictureFormat.ORIGINAL,
     * PictureType.IMAGE_PROFILE_PHOTO); final File picLocation =
     * downloadPersonProfileImage(urlPic); regist = new PersonConcrete(id, name,
     * new PictureConcreteBuilder().id(FilenameUtils.getBaseName(profilePic))
     * .physicalLocation(picLocation.toPath()).format(PictureFormat.ORIGINAL)
     * .releaseDate(LocalDate.now()).size((int) getSizeFromFile(picLocation))
     * .title("Profile image for " + name).build()); } else { regist = new
     * PersonConcrete(id, name, null); }
     * 
     * } }
     * 
     * return new CreditsConcrete(regist, cast);
     * 
     * }
     */
}
