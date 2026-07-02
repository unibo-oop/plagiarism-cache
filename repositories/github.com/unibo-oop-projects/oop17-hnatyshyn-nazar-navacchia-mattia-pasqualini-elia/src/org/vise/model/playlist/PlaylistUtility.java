package org.vise.model.playlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.vise.model.playlist.song.Song;
import org.vise.model.playlist.song.SongImpl;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * Utility class for save data and mainteining the session.
 *
 */
public final class PlaylistUtility {

        private static JSONObject playlistObject;
        private static JSONObject songObject;
        private static Map<String, Object> jsonMap = new HashMap<>();
        private static Map<String, Object> playlistElem = new HashMap<>();
        private static Map<String, Object> songElem = new HashMap<>();
        private static List<Object> playlistArray = new ArrayList<>();
        private static JSONArray playlistJSONArray = new JSONArray();
        private static JSONArray songJSONArray = new JSONArray();
        private static final String SAVE_DIR_NAME = "Vise";
        private static final String SAVE_FILE_NAME = "VisePlaylist";
        private static final String HOME = "user.home";
        private static final String SEPARATOR = "file.separator";
        private static final String DEF_EXTENSION = ".json";
        private static Song song;
        private static File file = new File(getDefaultSavePath() + System.getProperty(SEPARATOR) + SAVE_FILE_NAME + DEF_EXTENSION);

        private PlaylistUtility() { } 
   /**
     * Create a JSON when close the session.
     * 
     * @param playlist
     *          the list of playlist that are created
     * @throws IOException 
     *         if there is an I/O error
     * @return
     *        the json created to save the session
     */
    public static JSONObject createJSON(final List<Playlist> playlist) throws IOException {
        jsonMap.put("playlist", playlistArray);

        for (int i = 0; i < playlist.size(); i++) {
            final List<Object> songArray = new ArrayList<>();
            final List<Song> songList = new ArrayList<>(playlist.get(i).getPlaylistContent());
                    playlistElem.put("author", playlist.get(i).getAuthor().toString());
                    playlistElem.put("name", playlist.get(i).getName().toString());
                    playlistElem.put("UUID", playlist.get(i).getPlaylistID().toString());
                    playlistElem.put("songs", songArray);
                    playlistObject = new JSONObject(playlistElem);
                    playlistArray.add(playlistObject);

            for (int j = 0; j < songList.size(); j++) {
                 songElem.put("audioPath", songList.get(j).getAudioPath().toString());
                 songElem.put("title", songList.get(j).getTitle().toString());
                 songElem.put("artist", songList.get(j).getArtist().toString());
                 songElem.put("album", songList.get(j).getAlbum().toString());
                 songObject = new JSONObject(songElem);
                 songArray.add(songObject);
                 }
        }
        return new JSONObject(jsonMap);
    }

    /**
     * Save the JSON.
     * 
     * @param playlist
     *        the list of playlist that are created
     * @throws IOException
     *         if there is an I/O error
     */
    public static void save(final List<Playlist> playlist) throws IOException {
        if (!isDefaultSaveDirectoryPresent()) {
            createSaveDir();
        }

        try (FileWriter fileToWrite = new FileWriter(file, false)) { 
            fileToWrite.write(createJSON(playlist).toString());
            fileToWrite.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonMap.clear();
        playlistArray.clear();
    }

   /**
     * Read a JSON from a file.
     * 
     * @param plAd
     *        the PlaylistAdministrator to use when read the json
     * @throws ParseException
     *           if the file doesn't exist
     */
    public static void readJSON(final PlaylistAdministrator plAd) throws ParseException {
        final JSONParser parser = new JSONParser();

        try {
          if (!isDefaultSaveDirectoryPresent()) {
                createSaveDir();
            }
      } catch (IOException e1) {
          e1.printStackTrace();
      }
       if (file.exists()) {
        try {
            final Object obj = parser.parse(new FileReader(file));

            final JSONObject json = (JSONObject) obj;
            playlistJSONArray = (JSONArray) json.get("playlist");

            for (int i = 0; i < playlistJSONArray.size(); i++) {
                playlistObject = (JSONObject) playlistJSONArray.get(i);
                plAd.addPlaylist(UUID.fromString((String) playlistObject.get("UUID")), (String) playlistObject.get("name"));
                songJSONArray = (JSONArray) playlistObject.get("songs");
                for (int j = 0; j < songJSONArray.size(); j++) {
                    songObject = (JSONObject) songJSONArray.get(j);
                    song = new SongImpl((String) songObject.get("audioPath"));
                    song.updateMeta((String) songObject.get("title"), 
                                    (String) songObject.get("artist"), 
                                    (String) songObject.get("album"));
                   plAd.addSongToPlaylist(song);

                 }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
            }
       }
      }

    /**
     * Check if the directory to save JSON exist or not.
     * 
     * @return
     *         a boolean that explain if the directory is present
     * @throws IOException
     *         if there is an I/O error
     */

    private static boolean isDefaultSaveDirectoryPresent() throws IOException {
        final Path userHomeDir  = Paths.get(System.getProperty(HOME));
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(userHomeDir)) {
            for (final Path fileP : stream) {
               if (Files.exists(fileP) && Files.isDirectory(fileP) && fileP.getFileName().toString().equals(SAVE_DIR_NAME)) {
                   return true;
               }
            }
        } 
        return false;
    }

    /**
     * Create a directory to save JSON.
     * 
     * @throws IOException
     *         if there is an I/O error
     */

    private static void createSaveDir() throws IOException {
        Files.createDirectory(getDefaultSavePath());
    }

    /**
     * Get the Path of the directory where the JSON is saved.
     * 
     * @return 
     *        the path of the directory created before
     */

    private static Path getDefaultSavePath() {
        return Paths.get(System.getProperty(HOME), System.getProperty(SEPARATOR), SAVE_DIR_NAME);
    }

}
