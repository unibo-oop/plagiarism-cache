package controller.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.construction.Building;
import model.construction.Construction;
import model.construction.ConstructionType;
import model.game.GameImpl;
import utility.GamePropertiesHelper;
import utility.PositionControllerHelper;
import utility.SystemPropertiesHelper;
//import view.scenecreator.AchievementsList;
import view.scenecreator.PositionBuilding;
import view.scenecreator.ResourcesMenuLauncher;

/**
* Controller of the viewGamePlan.
*/
public class GamePlanController {

    @FXML // fx:id="factory"
    private Button factory; // Value injected by FXMLLoader

    @FXML // fx:id="shoppingCenter"
    private Button shoppingCenter; // Value injected by FXMLLoader

    @FXML // fx:id="achievements"
    private Button achievements; // Value injected by FXMLLoader

    @FXML // fx:id="lowResidential"
    private Button lowResidential; // Value injected by FXMLLoader

    @FXML // fx:id="casino"
    private Button casino; // Value injected by FXMLLoader

    @FXML // fx:id="resources"
    private Button resources; // Value injected by FXMLLoader

    @FXML // fx:id="demolish"
    private Button demolish; // Value injected by FXMLLoader

    @FXML // fx:id="fireFighters"
    private Button fireFighters; // Value injected by FXMLLoader

    @FXML // fx:id="policeStation"
    private Button policeStation; // Value injected by FXMLLoader

    @FXML // fx:id="exit"
    private Button exit; // Value injected by FXMLLoader

    @FXML // fx:id="windTurbine"
    private Button windTurbine; // Value injected by FXMLLoader

    @FXML // fx:id="street"
    private Button street; // Value injected by FXMLLoader

    @FXML // fx:id="powerPlant"
    private Button powerPlant; // Value injected by FXMLLoader

    @FXML // fx:id="waterTower"
    private Button waterTower; // Value injected by FXMLLoader

    @FXML // fx:id="stadium"
    private Button stadium; // Value injected by FXMLLoader

    @FXML // fx:id="mediumResidential"
    private Button mediumResidential; // Value injected by FXMLLoader

    @FXML // fx:id="highResidential"
    private Button highResidential; // Value injected by FXMLLoader

    @FXML // fx:id="rubbishDump"
    private Button rubbishDump; // Value injected by FXMLLoader

    @FXML // fx:id="hospital"
    private Button hospital; // Value injected by FXMLLoader

    @FXML // fx:id="gameMap"
    private GridPane gameMap; // Value injected by FXMLLoader

    @FXML // fx:id="park"
    private Button park; // Value injected by FXMLLoader

    /**
     * Close the application.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void closeApplication(final ActionEvent event) {
        Runtime.getRuntime().exit(0);
    }

    /**
     * Open the Resources Menu.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void openResourcesMenu(final ActionEvent event) {
        new ResourcesMenuLauncher(new Stage());
    }

    /**
     * Open the list of Achievements.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void openAchievements(final ActionEvent event) {
//        new AchievementsList();
    }

    /**
     * Add the sprite of the new building to the gridPane.
     * @param building
     *          the new building to add to the gridPane
     */
    private void setNewImage(final Construction building) {
        final ImageView tmp = new ImageView(new Image(building.getType().getSpritePath()));
        final Tooltip t = new Tooltip("Riga: " + (building.getPosition().getKey() + 1) + " Colonna: " + (building.getPosition().getValue() + 1)
                + "\n" + building.getType().getType() + "\n" + building.getType().getDescription()
                + "\nDenaro: " + building.getType().getMoneyProduction() + "\nEnergia: " +  building.getType().getEnergyInfluence()
                + "\nAcqua: " + building.getType().getWaterInfluence());
        Tooltip.install(tmp, t);
        tmp.setFitHeight(SystemPropertiesHelper.PREFERRED_HEIGHT_RESOLUTION / GamePropertiesHelper.ROW_NUMBER);
        tmp.setFitWidth(gameMap.getWidth() / GamePropertiesHelper.COLUMN_NUMBER);
        gameMap.add(tmp, building.getPosition().getValue(), building.getPosition().getKey());
    }

    /**
     * Try to build the new building.
     * @param building
     *          the new building to be built
     */
    private void building(final ConstructionType building) {
        if (PositionControllerHelper.isValidPosition()) {
            final Pair<Integer, Integer> position = PositionControllerHelper.getPosition();
                final Construction edifice = new Building(building, position);
                if (GameImpl.getGameImpl().createBuilding(edifice, position)) {
                    setNewImage(edifice);
                }
        }
    }

    /**
     * Build low residential.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildLowResidential(final ActionEvent event) {
        final ConstructionType building = ConstructionType.APPARTAMENTO;
        new PositionBuilding(building);
        building(building);
    }

    /**
     * Build medium residential.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildMediumResidential(final ActionEvent event) {
        final ConstructionType building = ConstructionType.CONDOMINIO;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build high residential.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildHighResidential(final ActionEvent event) {
        final ConstructionType building = ConstructionType.GRATTACIELO;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build shopping center.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildShoppingCenter(final ActionEvent event) {
        final ConstructionType building = ConstructionType.COMMERCIALE;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build factory.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public  void buildFactory(final ActionEvent event) {
        final ConstructionType building = ConstructionType.FABBRICA;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build power plant.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildPowerPlant(final ActionEvent event) {
        final ConstructionType building = ConstructionType.TERMOELETTRICA;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build wind turbine.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildWindTurbine(final ActionEvent event) {
        final ConstructionType building = ConstructionType.TURBINA;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build water tower.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildWaterTower(final ActionEvent event) {
        final ConstructionType building = ConstructionType.IDRICA;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build rubbish dump.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildRubbishDump(final ActionEvent event) {
        final ConstructionType building = ConstructionType.DISCARICA;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build fire fighters.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildFireFighters(final ActionEvent event) {
        final ConstructionType building = ConstructionType.POMPIERI;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build police station.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildPoliceStation(final ActionEvent event) {
        final ConstructionType building = ConstructionType.POLIZIA;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build hospital.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildHospital(final ActionEvent event) {
        final ConstructionType building = ConstructionType.OSPEDALE;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build casino.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildCasino(final ActionEvent event) {
        final ConstructionType building = ConstructionType.CASINO;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build stadium.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildStadium(final ActionEvent event) {
        final ConstructionType building = ConstructionType.STADIO;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build street.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildStreet(final ActionEvent event) {
        final ConstructionType building = ConstructionType.STRADA;
        new PositionBuilding(building);
        building(building);
    }
    /**
     * Build park.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void buildPark(final ActionEvent event) {
        final ConstructionType building = ConstructionType.PARCO;
        new PositionBuilding(building);
        building(building);
    }
}
