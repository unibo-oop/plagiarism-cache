package controller.jsoninput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.scene.paint.Color;
import model.battlefield.BattlefieldComponent;
import model.battlefield.Fluid;
import model.battlefield.Location;

/**
 * Allows to read and extract JSON informations about Locations and Fluids for
 * the game, it reads the "environment_preset.json".
 *
 * @author Andrea Manoni
 *
 */
public class LocationJSONImpl implements LocationJSON {

    private final InputStream inputStream = getClass().getResourceAsStream("/location_preset.json");

    private final List<BattlefieldComponent> locationList = new ArrayList<>();
    private boolean loadDone;

    @Override
    public final List<BattlefieldComponent> getLocationList() throws IllegalStateException {
        if (loadDone) {
            return Collections.unmodifiableList(locationList);
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

            final JSONArray locations = myjson.getJSONArray("locations");
            final JSONArray fluids = new JSONArray();

            final int size = locations.length();

            for (int i = 0; i < size; i++) {
                fluids.put(locations.getJSONObject(i).getJSONArray("fluids"));
                final JSONArray fluidSearch = fluids.getJSONArray(i);
                final List<Fluid> fluidList = new ArrayList<>();
                Color terrainColor = null;

                for (int j = 0; j < fluidSearch.length(); j++) {
                    switch (fluidSearch.getJSONObject(j).getString("name")) {
                    case "Air":
                        terrainColor = Color.FORESTGREEN;
                        break;
                    case "Water":
                        terrainColor = Color.BURLYWOOD;
                        break;
                    case "None":
                        terrainColor = Color.SILVER;
                        break;
                    case "CO2":
                        terrainColor = Color.PERU;
                        break;
                    default:
                        break;
                    }
                    fluidList.add(new Fluid(fluidSearch.getJSONObject(j).getString("name"),
                            fluidSearch.getJSONObject(j).getDouble("density"), terrainColor,
                            fluidSearch.getJSONObject(j).getDouble("maxFluidSpeed"),
                            fluidSearch.getJSONObject(j).getInt("maxProjectileInitialSpeed"),
                            fluidSearch.getJSONObject(j).getString("backgroundImage")));
                }
                locationList.add(new Location(locations.getJSONObject(i).getString("name"),
                        new Vector2D(0, -locations.getJSONObject(i).getDouble("surfaceGravity")),
                        locations.getJSONObject(i).getInt("battlefieldWidth"),
                        locations.getJSONObject(i).getInt("battlefieldHeight"),
                        locations.getJSONObject(i).getInt("battlefieldWaveLength"),
                        Collections.unmodifiableList(fluidList)));

            }
        } catch (final JSONException e) {
            e.printStackTrace();
        }
        loadDone = true;
    }

}
