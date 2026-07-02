package controller.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;


import com.google.common.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 *  Class that allows to print and read to external file a leaderboard.
 */
public final class IOLeaderboard {

    private static final Gson GSON  = new Gson();
    private static Map<String, Integer> jsonMap = new HashMap<>();

    private IOLeaderboard() {

    }

    /**
     *  Method that allows to print to external file a Leaderboard in jsonformat.
     *  @param filePath - represent the file path where the leaderboard will print.
     *  @param leaderboard - represent the map that needs to be converted.
     */
    public static void printInJsonFormat(final String filePath, final Map<String, Integer> leaderboard) {

        //Serialize in json
        final String gsonStringFormat = GSON.toJson(leaderboard);

        //Print File
        final Charset charset = Charset.forName("UTF-8");
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(filePath, charset))) {
            wr.write(gsonStringFormat);
            wr.flush();
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not write");
        }

    }

    /**
     *  Method that allows to read leaderboard in file in jsonformat.
     *  @param filePath - represent the file path where the leaderboard was saved.
     *  @return a map that represent the leaderboard.
     */
    public static Map<String, Integer> readLeaderboard(final String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            final String data = reader.readLine();
            reader.close();
            //Mapping data
            final Type mapType = new TypeToken<Map<String, Integer>>() {
                    private static final long serialVersionUID = -437203752652190282L;
            }.getType();

            jsonMap = GSON.fromJson(data, mapType);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IOExcept");
        }
        return jsonMap;
    }
}
