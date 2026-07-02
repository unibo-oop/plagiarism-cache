package controller.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import com.google.gson.Gson;

import model.settings.GameSettingsImpl;
import model.settings.Settings;

/**
 *  Class that allows to print and read to external file the game's settings.
 */
public final class IOSettings {
    private static final Gson GSON  = new Gson();
    private static GameSettingsImpl jsonSettings;

    private IOSettings() {

    }

    /**
     *  Method that allows to print to external file game's settings in jsonformat.
     *  @param filePath - represent the file path where the settings will print.
     *  @param gameSettings - represent the settings entity that needs to be converted.
     */
    public static void printInJsonFormat(final String filePath, final Settings gameSettings) {

        //Serialize in json
        final String gsonStringFormat = GSON.toJson(gameSettings);

        //Print File
        final Charset charset = Charset.forName("UTF-8");
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(filePath, charset));) {
            wr.write(gsonStringFormat);
            wr.flush();
            wr.close();
        } catch (IOException ioExcept) {
            ioExcept.printStackTrace();
        }
    }

    /**
     *  Method that allows to read game's settings in file in jsonformat.
     *  @param filePath - represent the file path where the settings was saved.
     *  @return a map that represent the entity settings.
     */
    public static Settings readSettings(final String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            final String data = reader.readLine();
            reader.close();
            jsonSettings = GSON.fromJson(data, GameSettingsImpl.class);

        } catch (FileNotFoundException fileExcept) {
            fileExcept.printStackTrace();
        } catch (IOException ioExcept) {
            ioExcept.printStackTrace();
        }
        return jsonSettings;
    }
}
