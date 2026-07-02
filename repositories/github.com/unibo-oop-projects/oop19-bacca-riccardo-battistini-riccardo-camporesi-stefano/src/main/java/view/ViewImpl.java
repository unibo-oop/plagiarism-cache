package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.entities.trafficlight.PhaseManager;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.vehicle.Vehicle;
import model.environment.cell.Cell;
import model.environment.grid.Grid;
import view.controllers.UIController;
import view.loader.stagefactory.StageFactory;
import view.utils.ControllerVariablesNames;
import view.utils.images.ImageManager;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

import static view.utils.ViewConstraints.TITLE;

/**
 * Concrete implementation of the View.
 */
public class ViewImpl implements View {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewImpl.class);

    private Controller controller;
    private final Stage stage;
    private final StageFactory sceneFactory;
    private final ImageManager imageManager;
    private UIController mainController;
    private FXMLLoader loader;

    public ViewImpl(final Stage stageToLoad) {
        this.stage = stageToLoad;
        this.sceneFactory = new StageFactory();
        this.imageManager = ImageManager.getImageLoaderInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void launch(final Controller controller) {
        this.controller = controller;
        this.loadMainScene();
        this.setMainController();
    }

    private void loadMainScene() {
        final Parent root;
        final Scene scene;

        this.stage.getIcons().addAll(this.imageManager.getIconsList());

        // System.out.println("prova3");
        this.loader = new FXMLLoader();
        this.loader.setLocation(ClassLoader.getSystemResource("layouts/base.fxml"));
        try {
            root = this.loader.load();
            scene = new Scene(root);
            scene.setUserData(this.loader);
            this.stage.setScene(scene);
        } catch (IOException e) {
            LOGGER.info("Fxml file loading failed", e);
        }
        this.stage.setResizable(false);
        this.stage.setTitle(TITLE);
        this.stage.show();
    }

    private void setMainController() {
        this.mainController = (UIController) this.loader.getController();
        this.mainController.initUIController(this);
        // this.mainController = new UIController();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadBackground(final Grid<Cell> grid) {
        this.mainController.loadBackground(grid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Set<Vehicle> vehicles, final Set<TrafficLight> trafficLights) {
        this.mainController.render(vehicles, trafficLights);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StageFactory getStageFactory() {
        return this.sceneFactory;
    }

    /**
     * This method is called by {@link UIController} when the user changes some
     * parameters.
     */
    @Override
    public Map<ControllerVariablesNames, Double> getParameters() {
        return this.mainController.getUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadScenario() {
        this.controller.loadSimulation();
        this.controller.startSimulation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startSimulation() {
        this.controller.startSimulation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pauseSimulation() {
        this.controller.pauseSimulation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateReport(final Set<Vehicle> vehicles, final PhaseManager<Phases> phMan, final double time) {
        this.mainController.updateReport(vehicles, phMan, time);
    }

}
