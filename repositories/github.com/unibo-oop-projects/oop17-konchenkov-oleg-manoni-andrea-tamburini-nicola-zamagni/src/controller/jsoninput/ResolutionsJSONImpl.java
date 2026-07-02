package controller.jsoninput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.resolution.Resolution;
import model.resolution.ResolutionImpl;

/**
 * Allows to read and extract JSON informations about Resolutions for the game,
 * it reads the "resolution_preset.json".
 *
 * @author Andrea Manoni
 *
 */
public class ResolutionsJSONImpl implements ResolutionsJSON {
    private final InputStream inputStream = getClass().getResourceAsStream("/resolution_preset.json");

    private final List<Resolution> resolutionList = new ArrayList<>();
    private boolean loadDone;

    @Override
    public final List<Resolution> getResolutionList() {
        if (loadDone) {
            return Collections.unmodifiableList(resolutionList);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public final void load() {
        final StringBuilder contentBuilder = new StringBuilder();
        final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        try {
            line = br.readLine();
            while (line != null) {
                contentBuilder.append(line);
                line = br.readLine();
            }
        } catch (final IOException e1) {

            e1.printStackTrace();
        }
        final String str = contentBuilder.toString();
        try {
            final JSONObject myjson = new JSONObject(str);

            final JSONArray resolutionArray = myjson.getJSONArray("resolutions");

            final int size = resolutionArray.length();
            for (int i = 0; i < size; i++) {
                final JSONObject item = resolutionArray.getJSONObject(i);
                resolutionList
                        .add(new ResolutionImpl(item.getString("name"), item.getDouble("x"), item.getDouble("y")));

            }

        } catch (final JSONException e) {
            e.printStackTrace();
        }
        loadDone = true;
    }

}
