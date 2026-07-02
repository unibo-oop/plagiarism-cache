package view.scene;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import controller.Controller;
import controller.GameStatus;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import model.battlefield.Fluid;
import model.battlefield.Location;
import model.projectile.ProjectileType;

/**
 *
 * @author Andrea Manoni
 *
 */
public class GameController extends AbstractController {
    private static final String PANETOT = "paneTot";
    private static final int STARTINPOWERVALUE = 0;
    private static final int STARTINGANGLEVALUE = 0;
    private static final int MAXPOWERVALUE = 100;
    private static final int MAXANGLEVALUE = 180;
    private static final int MINPOWERVALUE = 0;
    private static final int MINANGLEVALUE = 0;
    private static final int INCREMENT = 1;
    private static final int DECREMENT = -1;
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private SceneMainController mainController;
    @FXML
    private Slider angleSlider;
    @FXML
    private Slider powerSlider;
    @FXML
    private Rectangle rectButtons;
    @FXML
    private Button btnShoot;
    @FXML
    private ComboBox<ProjectileType> comboInventory;
    @FXML
    private Label lblPowerChange;
    @FXML
    private Label lblAngleChange;
    @FXML
    private Label lblProjectileNumber;
    private ImageView shootButton;
    @FXML
    private Label lblWind;
    private boolean disable;
    private int angleValue;
    private int powerValue;
    private Controller controller;
    private URL imgURL;

    /* DIALOG */
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;
    @FXML
    private ImageView yesButton;
    @FXML
    private ImageView noButton;
    @FXML
    private Button btnQuit;
    @FXML
    private Button btnContinue;
    @FXML
    private ImageView quitButton;
    @FXML
    private ImageView continueButton;
    @FXML
    private Label textQuit;
    private Pane paneDialog;
    @FXML
    private Rectangle rectExitScreen;
    /* END DIALOG */

    @Override
    public final void setMainApp(final SceneMainController mainController) {
        this.mainController = mainController;
        angleSlider = (Slider) getNodeNested(mainController, "angleSlider", PANETOT);
        powerSlider = (Slider) getNodeNested(mainController, "powerSlider", PANETOT);
        btnShoot = (Button) getNodeNested(mainController, "btnShoot", PANETOT);
        angleValue = STARTINGANGLEVALUE;
        powerValue = STARTINPOWERVALUE;
        rectButtons = (Rectangle) getNode(mainController, "rectButtons");
        shootButton = (ImageView) getNodeNested(mainController, "shootButton", PANETOT);
        setListener();
        setKeyboardListener();
        setImages();
        setDialog();
    }

    private void actionShoot() {
        disableNodes();
        controller.update(GameStatus.SHOOT_PROJECTILE);
        mainController.getSoundShoot().play();
    }

    private void balanceLabelPower() {
        powerSlider.setValue(powerValue);
        lblPowerChange.setText(getPowerValuePerc() + "%");
    }

    private void balanceLabelAngle() {
        angleSlider.setValue(angleValue);
        lblAngleChange.setText(angleValue + "\u00BA");
    }

    private void changedPowerMouse() {
        powerValue = (int) Math.round(powerSlider.getValue());
        balanceLabelPower();
    }

    private void changedAngleMouse() {
        angleValue = (int) Math.round(angleSlider.getValue());
        balanceLabelAngle();
    }

    /**
     * Controls if the value to the PowerSlider is correct.
     */
    private void changedPower() {
        if (powerValue > MAXPOWERVALUE) {
            powerValue = MAXPOWERVALUE;
        } else if (powerValue < MINPOWERVALUE) {
            powerValue = MINPOWERVALUE;
        }
    }

    /**
     * Controls if the value to the AngleSlider is correct.
     */
    private void changedAngle() {
        if (angleValue > MAXANGLEVALUE) {
            angleValue = MAXANGLEVALUE;
        } else if (angleValue < MINANGLEVALUE) {
            angleValue = MINANGLEVALUE;
        }
    }

    /**
     * Sets the changes to the PowerSlider.
     *
     * @param value
     *            value
     */
    public void changedPowerKey(final double value) {
        powerValue += value;
        changedPower();
        powerSlider.setValue(powerValue);
        lblPowerChange.setText(String.valueOf(getPowerValuePerc() + "%"));
    }

    /**
     * Sets the changes to the AngleSlider.
     *
     * @param value
     *            value
     */
    public void changedAngleKey(final double value) {
        angleValue += value;
        changedAngle();
        angleSlider.setValue(angleValue);
        lblAngleChange.setText(angleValue + "\u00BA");
    }

    /**
     *
     * @param controller
     *            controller
     */
    public void setController(final Controller controller) {
        this.controller = controller;
    }

    /**
     *
     * @return angle of turret.
     */
    public int getAngleValue() {
        return angleValue;
    }

    /**
     *
     * @return speed of projectile
     */
    public int getPowerValue() {
        return powerValue;
    }

    /**
     *
     * @return percentage of power
     */
    public int getPowerValuePerc() {
        return (int) Math.round((double) powerValue / MAXPOWERVALUE * 100);
    }

    /**
     *
     * @return inventory selected item
     *
     *
     */
    public ProjectileType getInventoryValue() {
        return comboInventory.getSelectionModel().getSelectedItem();
    }

    /**
     * Set the slider angle with the values in input.
     *
     * @param lastAngle
     *            lastAngle
     */
    public void setSliderAngle(final double lastAngle) {
        angleValue = (int) Math.round(lastAngle);
        balanceLabelAngle();
    }

    /**
     * Set the slider power with the values in input.
     *
     * @param lastShootPower
     *            lstShootPower
     */
    public void setSliderPower(final double lastShootPower) {
        powerValue = (int) Math.round(lastShootPower);
        balanceLabelPower();
    }

    /**
     * Enables certain nodes.
     */
    public void enableNodes() {
        angleSlider.setDisable(false);
        powerSlider.setDisable(false);
        comboInventory.setDisable(false);
        btnShoot.setDisable(false);
        disable = false;
    }

    private void disableNodes() {
        angleSlider.setDisable(true);
        powerSlider.setDisable(true);
        comboInventory.setDisable(true);
        btnShoot.setDisable(true);
        disable = true;
    }

    /**
     *
     * @param name
     *            name
     * @param color
     *            color
     */
    public void setNameTurn(final String name, final Color color) {
        ((Label) getNodeNested(mainController, "lblName", "paneUp")).setText(name);
        ((Label) getNodeNested(mainController, "lblName", "paneUp")).setTextFill(color);
    }

    /**
     *
     * @param fluidVelocity
     *            fluidVelocity
     */
    public void setWindTurn(final double fluidVelocity) {
        final double currentSpeed = fluidVelocity;
        final double maxspeed;
        maxspeed = mainController.getSceneOn().equals("Game.fxml") ? mainController.getFluid().getMaxFluidSpeed()
                : ((Fluid) ((Location) mainController.getLocationsList().get(0)).getChild().get(0)).getMaxFluidSpeed();
        final int speed = maxspeed > 0 ? (int) Math.round(currentSpeed / maxspeed * 100.0) : 0;
        lblWind.setText(String.valueOf(Math.abs(speed) + "% " + (speed < 0 ? "\u25c0" : speed > 0 ? "\u25b6" : "")));
    }

    private void setListener() {

        angleSlider.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            changedAngleMouse();
            rectButtons.requestFocus();
            controller.update(GameStatus.SET_PROJECTILE_ELEVATION_ANGLE);
        });
        angleSlider.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            changedAngleMouse();
            rectButtons.requestFocus();
            controller.update(GameStatus.SET_PROJECTILE_ELEVATION_ANGLE);
        });
        powerSlider.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            changedPowerMouse();
            rectButtons.requestFocus();
        });
        powerSlider.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            changedPowerMouse();
            rectButtons.requestFocus();
        });

        btnShoot.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionShoot());
        btnShoot.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = GameController.class.getResource("/game_fire_on.png");
            shootButton.setImage(new Image(imgURL.toString()));
        });
        btnShoot.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = GameController.class.getResource("/game_fire_off.png");
            shootButton.setImage(new Image(imgURL.toString()));
        });
        mainController.getPrimaryStage().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                if (!paneDialog.isVisible()) {
                    sceneDialog();
                    e.consume();
                } else {
                    actionContinueDialog();
                    e.consume();
                }
            }
        });
        mainController.getPrimaryStage().setOnCloseRequest(e -> {
            sceneDialog();
            e.consume();
        });
    }

    private void setKeyboardListener() {
        mainController.getPrimaryStage().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if ((mainController.getSceneOn().equals("Game.fxml") || mainController.getSceneOn().equals("Demo.fxml"))
                    && !disable) {
                switch (e.getCode()) {
                case DOWN:
                    powerSlider.requestFocus();
                    changedPowerKey(DECREMENT);
                    changedPower();
                    break;
                case UP:
                    powerSlider.requestFocus();
                    changedPowerKey(INCREMENT);
                    changedPower();
                    break;
                case LEFT:
                    angleSlider.requestFocus();
                    changedAngleKey(INCREMENT);
                    controller.update(GameStatus.SET_PROJECTILE_ELEVATION_ANGLE);
                    break;
                case RIGHT:
                    angleSlider.requestFocus();
                    changedAngleKey(DECREMENT);
                    controller.update(GameStatus.SET_PROJECTILE_ELEVATION_ANGLE);
                    break;
                case SPACE:
                    btnShoot.requestFocus();
                    actionShoot();
                    break;
                default:
                    break;
                }
            }
        });
        comboInventory.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.UP) || e.getCode().equals(KeyCode.DOWN)) {
                e.consume();
            }
        });
    }

    private void setImages() {
        imgURL = GameController.class.getResource("/game_fire_off.png");
        shootButton.setImage(new Image(imgURL.toString()));
    }

    /**
     * Sets the comboInventory every turn for the selected player.
     *
     * @param inventory
     *            inventory
     * @param projectileSelected
     *            projectileSelected
     */
    public void setComboInventory(final List<Pair<ProjectileType, Integer>> inventory,
            final ProjectileType projectileSelected) {
        comboInventory.setItems(FXCollections
                .observableArrayList(inventory.stream().map(p -> p.getKey()).collect(Collectors.toList())));
        comboInventory.setOnAction(e -> {
            if (!comboInventory.isDisable()) {
                rectButtons.requestFocus();
                mainController.getSoundReload().play();
            }
        });
        setLabelProjectile(inventory);
        comboInventory.getSelectionModel().select(projectileSelected);
    }

    private void setLabelProjectile(final List<Pair<ProjectileType, Integer>> inventory) {
        comboInventory.setOnAction(e -> {
            final Optional<Pair<ProjectileType, Integer>> projectile = inventory.stream()
                    .filter(p -> p.getKey().equals(comboInventory.getSelectionModel().getSelectedItem())).findFirst();
            if (projectile.isPresent()) {
                lblProjectileNumber.setText(projectile.get().getValue().equals(MAX_VALUE) ? "\u221E"
                        : projectile.get().getValue().toString());
            }
            mainController.getSoundReload().play();
        });
    }

    /**
     *
     * @return comboInventory
     */
    public ComboBox<ProjectileType> getComboInventory() {
        return comboInventory;
    }

    /**
     * Sets comboInventory every turn.
     *
     * @param projectile
     *            projectile
     */
    public void setInventoryNewTurn(final ProjectileType projectile) {
        comboInventory.getSelectionModel().select(projectile);
    }

    /* DIALOG */
    @FXML
    private void actionYesDialog() {
        controller.update(GameStatus.STOP);
        enableNodes();
        mainController.sceneMenu();

    }

    @FXML
    private void actionNoDialog() {
        btnYes.setVisible(false);
        btnNo.setVisible(false);
        textQuit.setVisible(false);
        yesButton.setVisible(false);
        noButton.setVisible(false);
    }

    @FXML
    private void actionContinueDialog() {
        paneDialog.setVisible(false);
        rectExitScreen.setVisible(false);
        enableNodes();
    }

    @FXML
    private void actionQuitDialog() {
        btnYes.setVisible(true);
        btnNo.setVisible(true);
        textQuit.setVisible(true);
        yesButton.setVisible(true);
        noButton.setVisible(true);
    }

    private void setImagesDialog() {
        imgURL = GameController.class.getResource("/dialog_yes_off.png");
        yesButton.setImage(new Image(imgURL.toString()));
        imgURL = GameController.class.getResource("/dialog_no_off.png");
        noButton.setImage(new Image(imgURL.toString()));
        imgURL = GameController.class.getResource("/dialog_back_off.png");
        quitButton.setImage(new Image(imgURL.toString()));
        imgURL = GameController.class.getResource("/dialog_continue_off.png");
        continueButton.setImage(new Image(imgURL.toString()));
    }

    private void setListenerDialog() {
        btnYes.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionYesDialog());
        btnNo.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionNoDialog());
        btnContinue.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionContinueDialog());
        btnQuit.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionQuitDialog());
        btnYes.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = GameController.class.getResource("/dialog_yes_on.png");
            yesButton.setImage(new Image(imgURL.toString()));
        });
        btnYes.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = GameController.class.getResource("/dialog_yes_off.png");
            yesButton.setImage(new Image(imgURL.toString()));
        });
        btnNo.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = GameController.class.getResource("/dialog_no_on.png");
            noButton.setImage(new Image(imgURL.toString()));
        });
        btnNo.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = GameController.class.getResource("/dialog_no_off.png");
            noButton.setImage(new Image(imgURL.toString()));
        });
        btnContinue.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = GameController.class.getResource("/dialog_continue_on.png");
            continueButton.setImage(new Image(imgURL.toString()));
        });
        btnContinue.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = GameController.class.getResource("/dialog_continue_off.png");
            continueButton.setImage(new Image(imgURL.toString()));
        });
        btnQuit.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = GameController.class.getResource("/dialog_back_on.png");
            quitButton.setImage(new Image(imgURL.toString()));
        });
        btnQuit.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = GameController.class.getResource("/dialog_back_off.png");
            quitButton.setImage(new Image(imgURL.toString()));
        });
        btnYes.setVisible(false);
        btnNo.setVisible(false);
        yesButton.setVisible(false);
        noButton.setVisible(false);
    }

    /**
     * Sets the dialog for this Scene.
     */
    public void setDialog() {
        paneDialog = (Pane) getNode(mainController, "paneDialog");
        setListenerDialog();
        setImagesDialog();
    }

    private void sceneDialog() {
        if (mainController.getSceneOn().equals("Game.fxml") || mainController.getSceneOn().equals("Demo.fxml")) {
            controller.update(GameStatus.STOP);
            paneDialog.setVisible(true);
            paneDialog.setOpacity(1);
            if (!mainController.isFullScreen()) {
                paneDialog.setLayoutX(mainController.getResolution().getX() / 2 - paneDialog.getPrefWidth() / 2);
                paneDialog.setLayoutY(mainController.getResolution().getY() / 2 - paneDialog.getPrefHeight() / 2);
            } else {
                paneDialog.setLayoutX(mainController.getBounds().getWidth() / 2 - paneDialog.getPrefWidth() / 2);
                paneDialog.setLayoutY(mainController.getBounds().getHeight() / 2 - paneDialog.getPrefHeight() / 2);
            }
        }
        paneDialog.toFront();
        rectExitScreen.setVisible(true);
        disableNodes();

    }

}
