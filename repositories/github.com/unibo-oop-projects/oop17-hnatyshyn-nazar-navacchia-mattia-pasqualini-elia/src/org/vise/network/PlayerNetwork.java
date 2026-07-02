package org.vise.network;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vise.model.playlist.Playlist;
import org.vise.model.playlist.PlaylistAdministrator;
import org.vise.model.playlist.PlaylistImpl;
import org.vise.model.playlist.song.RemoteSong;
import org.vise.model.playlist.song.Song;
import org.vise.model.playlist.song.SongImpl;
import org.vise.model.user.FriendUser;
import org.vise.model.user.FriendUserImpl;

import javafx.util.Pair;

/**
 * 
 * Class that connect the application with the online Server.
 *
 */
public final class PlayerNetwork {

    private static final String UUID_FLAG = "uuid";

    private PlayerNetwork() { }

    /**
     * Add an activity of an user to the server.
     * 
     * @param idUser
     *          The id of the user that load an activity.
     * @param descriptionActivity
     *          The description of the activity.
     * @return 
     *          A boolean with error massage.
     * @throws IOException
     *          If the URL is not correct.
     * @throws JSONException
     *          If there is a syntax error in the source string or a duplicated key.
     */
    public static Pair<Boolean, String> addActivity(final int idUser, final String descriptionActivity) throws IOException, JSONException {
       String error = null;
       final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "addActivity.php?idUser=" + idUser + "&descrizione=" + descriptionActivity);
       final boolean success = json.getBoolean("success");
       if (!success) {
           error = json.getJSONObject("error").getString("message");
       }
       return new Pair<>(success, error);
    }

    /**
     * Remove an activity from the server.
     * 
     * @param idUser
     *          The id of the user that load an activity.
     * @return 
     *          A boolean with error massage.
     * @throws IOException
     *          If the URL is not correct.
     * @throws JSONException
     *          If there is a syntax error in the source string or a duplicated key.
     */
    public static Pair<Boolean, String> removeActivity(final int idUser) throws IOException, JSONException {
        String error = null;
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "removeActivity.php?idUser=" + idUser);
        final boolean success = json.getBoolean("success");
        if (!success) {
            error = json.getJSONObject("error").getString("message");
        }
        return new Pair<>(success, error);
    }

    /**
     * Get all the activity of friends.
     * 
     * @param idUser
     *          The id of the current user.
     * @return
     *          The list of the friends that are listening to something in this moment.
     * @throws IOException
     *          IOException.
     * @throws JSONException
     *          JSONException.
     */
    public static List<Pair<String, String>> getActivityFriends(final String idUser) throws IOException, JSONException {
        final List<Pair<String, String>> listActivity = new ArrayList<>();
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "getActivityFriends.php?idUser=" + idUser);
        final JSONArray activities = json.getJSONArray("attivita");
        for (int i = 0; i < activities.length(); i++) {
            final JSONObject a = activities.getJSONObject(i);
            listActivity.add(new Pair<>(a.getString("nome"), a.getString("descrizione")));
        }
        return listActivity;
    }

    /**
     * Get the playlist of an user.
     * 
     * @param idUser
     *          The current user ID.
     * @param playlistAdministrator
     *          PlaylistAdministrator to add playlists saved in remote.
     * @return
     *          The list of playlists.
     * @throws IOException
     *          IOException.
     * @throws JSONException
     *          JSONException.
     */
    public static Pair<Boolean, Pair<String, List<Playlist>>> getPlaylists(final String idUser, final PlaylistAdministrator playlistAdministrator) throws IOException, JSONException {
        String error = null;
        final List<Playlist> playlists = new ArrayList<>();
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "getAllPlaylist.php?idUser=" + idUser);
        final boolean success = json.getBoolean("success");
        if (!success) {
            error = json.getJSONObject("error").getString("message");
        } else {
            final JSONArray plays = json.getJSONArray("playlist");
            for (int i = 0; i < plays.length(); i++) {
                final JSONObject jsonPlaylist = plays.getJSONObject(i);
                playlistAdministrator.addPlaylist(UUID.fromString(jsonPlaylist.getString(PlayerNetwork.UUID_FLAG)), jsonPlaylist.getString("name"));
                final JSONArray jsonSongs = jsonPlaylist.getJSONArray("song");
                for (int k = 0; k < jsonSongs.length(); k++) {
                    final JSONObject jsonSong = jsonSongs.getJSONObject(k);
                    final String songPath = jsonSong.getString("absolutePath");
                    if (new File(songPath).exists()) {
                        playlistAdministrator.addSongToPlaylist(new SongImpl(UUID.fromString((String) jsonSong.getString(PlayerNetwork.UUID_FLAG)), 
                                                                                songPath));
                    } else {
                       playlistAdministrator.addSongToPlaylist(new RemoteSong(UUID.fromString((String) jsonSong.getString(PlayerNetwork.UUID_FLAG)), 
                                                                               jsonSong.getString("title"), 
                                                                               jsonSong.getString("author"), 
                                                                               jsonSong.getString("album")));
                    }
                }
            }
        }

        return new Pair<>(success, new Pair<>(error, playlists));
    }

    /**
     * Search of user containing search results.
     * 
     * @param userID
     *          The current user ID.
     * @param searchString
     *          A search query string.
     * @return
     *          The list of users containing search query.
     * @throws JSONException
     *          IOException.
     * @throws IOException
     *          JSONException.
     */
    public static Pair<Boolean, Pair<String, List<FriendUser>>> search(final int userID, final String searchString) throws JSONException, IOException {
        String error = null;
        final List<FriendUser> friendUsers = new ArrayList<>();
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "search.php?idUtente=" + userID + "&parola=" + searchString);
        final boolean success = json.getBoolean("success");
        if (!success) {
            error = json.getJSONObject("error").getString("message");
        } else {
            final JSONArray users =  json.getJSONArray("result");
            for (int i = 0; i < users.length(); i++) {
                final JSONObject jsonElem = users.getJSONObject(i);
                final List<Playlist> playlists = new ArrayList<>();
                try {
                    final JSONArray jsonPlaylists = jsonElem.getJSONArray("playlist");
                    for (int j = 0; j < jsonPlaylists.length(); j++) {
                        final JSONObject jsonPlaylist = jsonPlaylists.getJSONObject(j);
                        final Playlist playlist = new PlaylistImpl(UUID.fromString((String) jsonPlaylist.getString(PlayerNetwork.UUID_FLAG)),
                                                             jsonPlaylist.getString("name"),
                                                             jsonPlaylist.getString("author"));
                       try {
                            final JSONArray jsonSongs = jsonPlaylist.getJSONArray("song");
                            for (int k = 0; k < jsonSongs.length(); k++) {
                                final JSONObject jsonSong = jsonSongs.getJSONObject(k);
                                playlist.addSong(new RemoteSong(UUID.fromString((String) jsonSong.getString(PlayerNetwork.UUID_FLAG)), 
                                                                jsonSong.getString("title"), 
                                                                jsonSong.getString("author"), 
                                                                jsonSong.getString("album")));
                            }
                        } catch (Exception e) {
                            e.printStackTrace(); //playlist without songs.
                        }
                        playlists.add(playlist);
                    }
                } catch (Exception e) {
                    e.printStackTrace(); //user without any playlist.
                }
                friendUsers.add(new FriendUserImpl.FriendUserBuilder()
                                                    .id(jsonElem.getInt("idUtente"))
                                                    .username(jsonElem.getString("username"))
                                                    .playlists(playlists)
                                                    .isFriend(jsonElem.getBoolean("amicizia"))
                                                    .build());
            }
        }
        return new Pair<>(success, new Pair<>(error, friendUsers));
    }

    /**
     * Add an user as a friend on the server.
     * 
     * @param idUserA
     *          User A UUID.
     * @param idUserB
     *          User B UUID.
     * @return 
     *          A boolean with error massage.
     * @throws IOException
     *          IOException.
     * @throws JSONException
     *          JSONException.
     */
    public static Pair<Boolean, String> addFriendship(final int idUserA, final int idUserB) throws IOException, JSONException {
        String error = null;
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "addFriendship.php?idUserA=" + idUserA + "&idUserB=" + idUserB);
        final boolean success = json.getBoolean("success");
        if (!success) {
            error = json.getJSONObject("error").getString("message");
        }
        return new Pair<>(success, error);
    }

    /**
     * Remove a friend from the server.
     * 
     * @param idUserA
     *          User A UUID.
     * @param idUserB
     *          User B UUID.
     * @return 
     *          A boolean with error massage.
     * @throws IOException
     *          IOException.
     * @throws JSONException
     *          JSONException.
     */
    public static Pair<Boolean, String> removeFriendship(final int idUserA, final int idUserB) throws IOException, JSONException {
        String error = null;
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "removeFriendship.php?idUserA=" + idUserA + "&idUserB=" + idUserB);
        final boolean success = json.getBoolean("success");
        if (!success) {
            error = json.getJSONObject("error").getString("message");
        }
        return new Pair<>(success, error);
    }

    /**
     * Add a playlist on the server.
     * 
     * @param playlist
     *          Playlist.
     * @param idUser
     *           User UUID.
     * @return 
     *          A boolean with error massage.
     * @throws IOException
     *          IOException.
     * @throws JSONException
     *          JSONException.
     */
    public static Pair<Boolean, String> addPlaylist(final Playlist playlist, final int idUser) throws IOException, JSONException {
        String error = null;
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "addPlaylist.php?uuid=" + playlist.getPlaylistID() + "&idUser=" + idUser + "&name=" +  playlist.getName() + "&author=" + playlist.getAuthor());
        final boolean success = json.getBoolean("success");
        if (!success) {
            error = json.getJSONObject("error").getString("message");
        }
        return new Pair<>(success, error);
    }

    /**
     * Remove a playlist from the server.
     * 
     * @param uuid
     *          Playlist UUID.
     * @param idUser
     *          User UUID.
     * @return 
     *          A boolean with error massage.
     * @throws IOException
     *          IOException.
     * @throws JSONException
     *          JSONException.
     */
    public static Pair<Boolean, String> removePlaylist(final String uuid, final int idUser) throws IOException, JSONException {
        String error = null;
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "removePlaylist.php?uuid=" + uuid + "&idUser=" + idUser);
        final boolean success = json.getBoolean("success");
        if (!success) {
            error = json.getJSONObject("error").getString("message");
        }
        return new Pair<>(success, error);
    }

    /**
     * Add a song to a playlist on the server.
     * 
     * @param song
     *          Song.
     * @param playlist
     *          Playlist.
     * @return 
     *          A boolean with error massage.
     * @throws IOException
     *          IOException.
     * @throws JSONException
     *          JSONException.
     */
    public static Pair<Boolean, String> addSongToPlaylist(final Song song, final Playlist playlist) throws IOException, JSONException {
        String error = null;
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "addSongToPlaylist.php?uuid=" + song.getSongID() + "&uuidPlaylist=" + playlist.getPlaylistID() + "&title=" + song.getTitle() + "&author=" + song.getArtist() + "&album=" + song.getAlbum() + "&absolutePath=" + song.getAudioPath());
        final boolean success = json.getBoolean("success");
        if (!success) {
            error = json.getJSONObject("error").getString("message");
        }
        return new Pair<>(success, error);
    }

    /**
     * Remove a song from the playlist on the server.
     * 
     * @param uuid
     *          Song UUID.
     * @param uuidPlaylist
     *          Playlist UUID.
     * @return 
     *          A boolean with error massage.
     * @throws IOException
     *          IOException.
     * @throws JSONException
     *          JSONException.
     */
    public static Pair<Boolean, String> removeSongFromPlaylist(final UUID uuid, final UUID uuidPlaylist) throws IOException, JSONException {
        String error = null;
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "removeSongFromPlaylist.php?uuid=" + uuid + "&uuidPlaylist=" + uuidPlaylist);
        final boolean success = json.getBoolean("success");
        if (!success) {
            error = json.getJSONObject("error").getString("message");
        }
        return new Pair<>(success, error);
    }
}
