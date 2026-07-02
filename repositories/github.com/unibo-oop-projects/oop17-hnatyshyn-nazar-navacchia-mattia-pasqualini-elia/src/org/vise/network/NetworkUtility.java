package org.vise.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.vise.model.user.CurrentUser;
import org.vise.model.user.CurrentUserImpl;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 
 * Network utility class.
 *
 */
public final class NetworkUtility {
    /**
     * A server API URL.
     */
    public static final String URL = "http://www.idiplomati.it/ViseAPI/API/";
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static Map<String, Object> jsonMap = new HashMap<>();
    private static final String SAVE_DIR_NAME = "Vise";
    private static final String SAVE_FILE_NAME = "ViseUser";
    private static final String HOME = "user.home";
    private static final String SEPARATOR = "file.separator";
    private static final String DEF_EXTENSION = ".json";
    private static File file = new File(getDefaultSavePath() + System.getProperty(SEPARATOR) + SAVE_FILE_NAME + DEF_EXTENSION);

    private NetworkUtility() { }

      /**
       * Create a connection with the server.
       * 
       * @return
       *         the JSON replaced by the site of the registration
       * @param url
       *         The url used to make the registration
       * @throws IOException
       *         if the URL is not correct
       * @throws JSONException
       *         if the things in JSON are amiss
       */
      public static JSONObject run(final String url) throws IOException, JSONException {
          if (isNetworkingAvailable()) {
              String finalResponse;
              final Request request = new Request.Builder()
                      .url(url)
                      .build();
              try (Response response = CLIENT.newCall(request).execute()) {
                finalResponse = response.body().string();
                return new JSONObject(finalResponse);
              }
          }
          System.out.println("No connection");
          return new JSONObject();
      }

      /**
       * Check if there is a connection.
       * 
       * @return
       *          true - if connection is available.
       */
      public static boolean isNetworkingAvailable() {
          final String url = URL.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.
          final int timeout = 1000;

          try {
              final HttpURLConnection connection = (HttpURLConnection) new java.net.URL(url).openConnection();
              connection.setConnectTimeout(timeout);
              connection.setReadTimeout(timeout);
              connection.setRequestMethod("HEAD");
              final int responseCode = connection.getResponseCode();
              return (HttpURLConnection.HTTP_OK <= responseCode && responseCode <= HttpURLConnection.HTTP_BAD_METHOD);
          } catch (IOException exception) {
              return false;
          }
      }

      /**
       * Create a JSON when open the session.
       * 
       * @param user
       *         the user that access to Vise
       * @throws IOException 
       *         if there is an I/O error
       * @return
       *         the json created to save the user
       */
      public static JSONObject createJSON(final CurrentUser user) throws IOException {
          jsonMap.put("username", user.getUsername());
          jsonMap.put("email", user.getEmail());
          jsonMap.put("password", user.getPassword());
          jsonMap.put("id", user.getUserID());
          return new JSONObject(jsonMap);
      }

      /**
       * Save the JSON.
       * 
       * @param user
       *         the user that access to Vise
       * @throws IOException
       *         if there is an I/O error
       */
      public static void save(final CurrentUser user) throws IOException {
          if (!isDefaultSaveDirectoryPresent()) {
              createSaveDir();
          }

          try (FileWriter fileToWrite = new FileWriter(file, false)) { 
              fileToWrite.write(createJSON(user).toString());
              fileToWrite.flush();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }

      /**
       * Read the JSON from a file.
       * 
       * @throws ParseException
       *         if the file doesn't exist
       * @return
       *         the user that is read from the json
       */
      public static CurrentUser readJSON() throws ParseException {
          final JSONParser parser = new JSONParser();
          CurrentUser user = null;
          try {
            if (!isDefaultSaveDirectoryPresent()) {
                  createSaveDir();
              }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
         if (file.exists()) {
          try {
              final FileReader fileReader = new FileReader(file);
              final Object obj = parser.parse(fileReader);

              final org.json.simple.JSONObject read = (org.json.simple.JSONObject) obj;
              user = new CurrentUserImpl.UserBuilder()
                                  .id(Integer.parseInt(read.get("id").toString()))
                                  .username((String) read.get("username"))
                                  .email((String) read.get("email"))
                                  .password((String) read.get("password"))
                                  .build();

              fileReader.close();
          } catch (NumberFormatException | IllegalStateException | FileNotFoundException e) {
              e.printStackTrace();
            } catch (IOException e) {
              e.printStackTrace();
              }
         }
         return user;
      }

     /**
       * Delete the JSON created to save username, email and password.
       * @return
       *       if the file doesn't exist any more
       */
      public static boolean delete() {
          if (file.exists()) {
              return file.delete();
          }
          return false;
      }

     /**
       * Check if the JSON is already present.
       * 
       * @return
       *      a boolean that explain if the file exist or not
       */
      public static Boolean isFilePresent() {
          if (file.exists()) {
              return true;
          }
          return false;
      }

      /**
       * Create the directory where the JSON will be saved.
       * 
       * @throws IOException
       *         if there is an I/O error
       */
      private static void createSaveDir() throws IOException {
          Files.createDirectory(getDefaultSavePath());
      }

      /**
       * Get the path of the JSON.
       * 
       * @return
       *        the path of the JSON
       */
      private static Path getDefaultSavePath() {
          return Paths.get(System.getProperty(HOME), System.getProperty(SEPARATOR), SAVE_DIR_NAME);
      }

      /**
       * Check if there is already the directory where the JSON will be saved.
       * 
       * @return
       *         a boolean that explain if the directory exist or not
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
}
