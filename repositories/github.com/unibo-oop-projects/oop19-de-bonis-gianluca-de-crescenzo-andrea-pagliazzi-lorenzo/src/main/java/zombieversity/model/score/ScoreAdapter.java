package zombieversity.model.score;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/**
 * Class to adapt an implementation of {@link Score} to the Gson library so it can save its state in a json file.
 */
public class ScoreAdapter implements JsonSerializer<Score>, JsonDeserializer<Score> {

    private static final String KILLS = "kills";
    private static final String TIME_PLAYED = "timePlayed";
    private static final String NICKNAME = "nickname";
    private static final String POSITION = "position";

    /**
     * {@inheritDoc}
     * 
     * In particular this is used to retrieve scores data saved as a json file.
     */
    @Override
    public final Score deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        final Score score = new ScoreImpl();

        try {
            final JsonObject obj = json.getAsJsonObject();

            score.setKills(obj.get(KILLS).getAsInt());
            score.setPosition(obj.get(POSITION).getAsInt());
            score.setTimePlayed(obj.get(TIME_PLAYED).getAsString());
            score.setNickname(obj.get(NICKNAME).getAsString());
        } catch (JsonParseException e) {
            e.printStackTrace();
        }

        return score;
    }

    /**
     * {@inheritDoc}
     * 
     * In particular this is used to save the relevant data of the Score implementation.
     */
    @Override
    public final JsonElement serialize(final Score src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject obj = new JsonObject();

        obj.addProperty(KILLS, src.getKills());
        obj.addProperty(POSITION, src.getPosition());
        obj.add(NICKNAME, new Gson().toJsonTree(src.getNickname()));
        obj.add(TIME_PLAYED, new Gson().toJsonTree(src.getTimePlayed()));
        return obj;
    }

}
