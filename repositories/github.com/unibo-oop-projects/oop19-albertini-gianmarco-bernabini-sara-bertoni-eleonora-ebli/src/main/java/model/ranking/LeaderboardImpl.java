package model.ranking;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

/**
 * 
 * Models the leaderboard for the game.
 *
 */
public class LeaderboardImpl implements Leaderboard {

    private final File directory = new File(System.getProperty("user.home"), ".unitype");
    private final File file = new File(directory, FILE_NAME);
    private static final String FILE_NAME = "unitype.json";

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayer(final Player player) {
        final Player p = Objects.requireNonNull(player, "I can't add the invisible man to the leaderboard");
        final Gson gson = new GsonBuilder()
                            .excludeFieldsWithoutExposeAnnotation()
                            .create();
        final List<Player> playersList = this.getPlayersList();
        playersList.add(p);
        final String json = gson.toJson(playersList);
        this.checkFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json);
            writer.flush();
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getSortedPlayersList() {
        final List<Player> sortedPlayersList = this.getPlayersList();
        sortedPlayersList.sort((p1, p2) -> p1.compareTo(p2));
        return sortedPlayersList;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteLeaderboard() {
        return this.file.delete();
    }

    private void createFile() {
        if (directory.mkdir() || !file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkFile() {
        if (!file.exists()) {
            this.createFile();
        }
    }

    private List<Player> getPlayersList() {
        final Gson gson = new Gson();
        List<Player> playersList = new ArrayList<>();
        this.checkFile();
        try (JsonReader jsonReader = new JsonReader(new FileReader(file))) {
            final JsonElement js = JsonParser.parseReader(jsonReader);
            if (js.isJsonObject()) {
                final JsonObject jsonObject = js.getAsJsonObject();
                playersList.add(gson.fromJson(jsonObject, PlayerImpl.class));
            } else if (js.isJsonArray()) {
                playersList = gson.fromJson(js,
                        new TypeToken<List<PlayerImpl>>() { }.getType());
            }
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            e.printStackTrace();
        }
        return playersList;
    }
}
