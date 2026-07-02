package jwhale.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jwhale.model.Model;
import jwhale.model.connector.ConnectionException;
import jwhale.model.connector.DaemonResponseException;
/**
 * Controller interface implementation. It represents the system's controller.
 */
public class ControllerImpl implements Controller {
    private static final String DATA = "data.json";
    private static final String URL = "url";
    private static final String PORT = "port";
    private static final String NAME = "name";
    private static final String EMPTY = "";
    private final List<EnvController> envControllers = new LinkedList<>();
    private final List<EnvPreView> loadableEnvs = new LinkedList<>();
    private final Gson parser = new Gson();
    private String currentEnvName = EMPTY;
    private final Model model;
    private JsonArray savedEnvs = new JsonArray();

    public ControllerImpl(final Model model) throws IOException {
        getEnvsFromFile();
        this.model = model;
    }
    @Override
    public final List<EnvPreView> getEnvsList() {
        return Collections.unmodifiableList(loadableEnvs);
    }

    @Override
    public final EnvController getEnvController(final String envName) {
        return envControllers.stream()
                .filter(e -> e.getControllerName().equals(envName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public final void loadEnv(final String url, final String port, final String name) 
            throws ConnectionException, DaemonResponseException, IOException {
        if (!envControllers.stream().anyMatch(e -> e.getControllerName().equals(name))) {
            updateEnv(url, port, name);
        } else {
            envControllers.removeIf(f -> f.getControllerName().equals(name));
            updateEnv(url, port, name);
        }
        final JsonObject newEnv = new JsonObject();
        newEnv.addProperty(URL, url);
        newEnv.addProperty(PORT, port);
        newEnv.addProperty(NAME, name);
        if (!savedEnvs.contains(newEnv)) {
            loadableEnvs.add(new EnvPreView(url, port, name));
            savedEnvs.getAsJsonArray().add(newEnv);
            updateEnvsFile(); 
        }
    }

    @Override
    public final void removeEnv(final String envName) throws IOException {
        model.removeEnv(envName);
        envControllers.removeIf(f -> f.getControllerName().equals(envName));
        removeFromEnvsArray(envName);
        loadableEnvs.removeIf(f -> f.getName().equals(envName));
        updateEnvsFile();
    }

    @Override
    public final String getCurrentEnvName() {
        return currentEnvName;
    }

    @Override
    public final void setCurrentEnvName(final String name) {
        this.currentEnvName = name;
    }

    private void getEnvsFromFile() throws IOException {
        final JsonArray content = new JsonParser().parse(fromInputStream()).getAsJsonArray();
        if (!content.isJsonNull()) {
            savedEnvs = parser.fromJson(content, JsonArray.class); 
            savedEnvs.forEach(e -> {
                loadableEnvs.add(new EnvPreView(e.getAsJsonObject().get("url").getAsString(), 
                        e.getAsJsonObject().get("port").getAsString(), 
                        e.getAsJsonObject().get("name").getAsString()));
            });
        }
    }

    private void updateEnvsFile() throws IOException {
        Files.write(Paths.get(DATA), parser.toJson(savedEnvs).getBytes());
    }

    private void removeFromEnvsArray(final String toRemoveName) {
        savedEnvs.deepCopy().forEach(e -> {
            if (e.getAsJsonObject().get("name").getAsString().equals(toRemoveName)) {
                savedEnvs.remove(e.getAsJsonObject());
            }
        });
    }

    private String fromInputStream() throws IOException {
        if (!new File(DATA).exists()) {
            try (PrintWriter fileWriter = new PrintWriter(DATA, "UTF-8")) {
                fileWriter.println("[]");
                fileWriter.close();
            }
        }
        return Files.readString(Paths.get(DATA));
    }

    private void updateEnv(final String url, final String port, final String name) throws ConnectionException, 
    DaemonResponseException {
        model.createEnv(url, port, name);
        envControllers.add(new EnvController(model.getEnv(name)));
    }
}
