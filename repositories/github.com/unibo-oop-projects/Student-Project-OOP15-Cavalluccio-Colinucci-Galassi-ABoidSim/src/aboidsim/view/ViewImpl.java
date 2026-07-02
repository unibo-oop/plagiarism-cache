package aboidsim.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import aboidsim.controller.Controller;
import aboidsim.util.InputInfo;
import aboidsim.util.Pair;
import aboidsim.util.Vector;
import javafx.application.Application;
import javafx.application.Platform;

/**
 * used to communicate with the controller.
 *
 */
public class ViewImpl implements View {

    private static Controller controller;
    private static SimulationScreen simulationScreen;

    @Override
    public void setController(final Controller control) {
        ViewImpl.settingController(control);
    }

    @Override
    public List<InputInfo> getInputs() {
        final List<InputInfo> list = InputHandler.getInputHandler().getInputs();
        InputHandler.getInputHandler().clearInputs();
        return list;
    }

    @Override
    public void start(final List<String> boids, final List<String> rules,
            final List<Pair<Integer, String>> listForImages, final List<String> envs) {
        BoidSelection.setBoids(boids);
        RulesSelection.setRules(rules);
        EnvironmentSelection.setEnvs(envs);
        DrawEntities.setImages(listForImages);
        Application.launch(MainWindow.class);

    }

    @Override
    public void drawEntities(final Set<Pair<Pair<Vector, Double>, Integer>> entities) {
        final List<Integer> levels = new ArrayList<>();
        entities.stream().forEach(e -> levels.add(e.getY()));
        Platform.runLater(() -> InfoBox.updateInfo(levels));
        Platform.runLater(() -> ViewImpl.simulationScreen.drawOnScreen(entities));

    }

    @Override
    public Pair<Integer, Integer> getScreenDimensions() {
        return new Pair<>(SimulationScreen.WIDTH - SimulationScreen.BOID_HEIGHT,
                SimulationScreen.HEIGHT - SimulationScreen.BOID_HEIGHT);
    }

    /**
     * Set simulation screen.
     *
     * @param screen
     */

    static void setSimulationScreen(final SimulationScreen screen) {
        ViewImpl.simulationScreen = screen;
    }

    /**
     *
     * @return simulation screen
     */
    static SimulationScreen getSimulationSceen() {
        return ViewImpl.simulationScreen;
    }

    /**
     * @return the controller
     */
    static Controller getController() {
        return ViewImpl.controller;
    }

    /**
     * This method sets the controller
     *
     * @param control
     */
    private static void settingController(final Controller control) {
        ViewImpl.controller = control;
    }
}
