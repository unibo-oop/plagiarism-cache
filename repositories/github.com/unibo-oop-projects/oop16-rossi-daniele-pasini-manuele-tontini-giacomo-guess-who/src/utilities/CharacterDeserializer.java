package utilities;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import model.Character.Colors;
import model.Character;

/***/
public class CharacterDeserializer implements JsonDeserializer<Character> {

    private static final String FIELD = "value";

    @Override
    public Character deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject characterObject = json.getAsJsonObject();

        /* Values that could be null (optional) */

        JsonObject obj;

        Colors hairColor;
        Colors beardColor;
        Colors mustacheColor;

        obj = characterObject.get("hairColor").getAsJsonObject();
        if (obj.has(FIELD)) {
            hairColor = Character.Colors.valueOf(obj.get(FIELD).getAsString());
        } else {
            hairColor = null;
        }

        obj = characterObject.get("beardColor").getAsJsonObject();
        if (obj.has(FIELD)) {
            beardColor = Character.Colors.valueOf(obj.get(FIELD).getAsString());
        } else {
            beardColor = null;
        }

        obj = characterObject.get("mustacheColor").getAsJsonObject();
        if (obj.has(FIELD)) {
            mustacheColor = Character.Colors.valueOf(obj.get(FIELD).getAsString());
        } else {
            mustacheColor = null;
        }


        return new Character.CharacterBuilder()
                        .setName(characterObject.get("name").getAsString())
                        .setIsMale(characterObject.get("male").getAsBoolean())
                        .setEyeColor(Character.Colors.valueOf(characterObject.get("eyeColor").getAsString()))
                        .setSkinColor(Character.Colors.valueOf(characterObject.get("skinColor").getAsString()))
                        .setHairColor(hairColor)
                        .setBeardColor(beardColor)
                        .setMustacheColor(mustacheColor)
                        .setHasHat(characterObject.get("hat").getAsBoolean())
                        .setHasEarrings(characterObject.get("earrings").getAsBoolean())
                        .setHasGlasses(characterObject.get("glasses").getAsBoolean())
                        .setHairType(Character.Hair.valueOf(characterObject.get("hairType").getAsString()))
                        .setDressType(Character.Dress.valueOf(characterObject.get("dressType").getAsString()))
                        .setPicPath(characterObject.get("picPath").getAsString())
                        .build();
    }
}