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

/**
 * Allows to read and extract JSON informations about Locations and Fluids for
 * the game, it reads the "tank_shape_preset.json".
 *
 * @author Andrea Manoni
 *
 */
public class TankShapeJSONImpl implements TankShapeJSON {

    private final InputStream inputStream = getClass().getResourceAsStream("/tank_shape_preset.json");

    private final List<Vector2D> outlinePoints = new ArrayList<>();
    private final List<Vector2D> turretPoints = new ArrayList<>();
    private double explosionRadius;
    private boolean loadDone;

    @Override
    public final List<Vector2D> getOutlinePoints() throws IllegalStateException {
        if (loadDone) {
            return Collections.unmodifiableList(outlinePoints);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public final List<Vector2D> getTurretPoints() throws IllegalStateException {
        return Collections.unmodifiableList(turretPoints);
    }

    @Override
    public final double getExplosionRadius() throws IllegalStateException {
        return explosionRadius;
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

            final JSONArray outlinePointsJSON = myjson.getJSONArray("outlinePoints");
            final JSONArray turretPointsJSON = myjson.getJSONArray("turretPoints");
            explosionRadius = myjson.getDouble("explosionRadius");

            int size = outlinePointsJSON.length();

            for (int i = 0; i < size; i++) {

                outlinePoints.add(new Vector2D(outlinePointsJSON.getJSONObject(i).getDouble("x"),
                        outlinePointsJSON.getJSONObject(i).getDouble("y")));

            }
            size = turretPointsJSON.length();

            for (int i = 0; i < size; i++) {

                turretPoints.add(new Vector2D(turretPointsJSON.getJSONObject(i).getDouble("x"),
                        turretPointsJSON.getJSONObject(i).getDouble("y")));

            }
        } catch (final JSONException e) {
            e.printStackTrace();
        }
        loadDone = true;
    }

}
