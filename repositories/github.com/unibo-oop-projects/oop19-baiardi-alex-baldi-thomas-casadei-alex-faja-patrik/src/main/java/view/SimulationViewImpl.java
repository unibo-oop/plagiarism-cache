package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.dyn4j.collision.Fixture;
import org.dyn4j.geometry.AABB;

import controller.SimulationController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.people.Person;
import model.people.Status;

/**
 * 
 */
public class SimulationViewImpl implements SimulationView, Initializable {

    @FXML
    private Pane mapPane;

    @FXML
    private CheckBox chkAlertState;

    @FXML
    private Slider slHomeTendencyIncrement;

    @FXML
    private Slider slSimulationSpeed;

    @FXML
    private Label lblHomeTendencyValue;

    @FXML
    private Label lblSimulationSpeedValue;

    @FXML
    private Label lblPeopleAtHome;

    @FXML
    private Label lblPeopleOutside;

    @FXML
    private Label lblPeopleMeetingPlace;

    @FXML
    private Label lblPeopleHospital;

    @FXML
    private Label lblInfected;

    @FXML
    private Label lblHealthy;

    @FXML
    private Label lblVirusDeath;

    @FXML
    private Label lblRecovered;

    private SimulationController simulationController;

    private double scale;

    private final List<ImageView> peopleImage = new ArrayList<>();

    /**
     * 
     */
    @Override
    public void initializeMap(final Fixture home, final Fixture hospital, final List<Fixture> meetingPlaces) {
        this.mapPane.autosize();
        this.scale = this.mapPane.getWidth() / this.simulationController.getMapSize();
        this.mapPane.getChildren().add(createImageView(PlaceImage.HOME.getImage(), home.getShape().createAABB()));
        this.mapPane.getChildren()
                .add(createImageView(PlaceImage.HOSPTITAL.getImage(), hospital.getShape().createAABB()));
        meetingPlaces.forEach(mp -> this.mapPane.getChildren()
                .add(createImageView(PlaceImage.MEETING_PLACE.getImage(), mp.getShape().createAABB())));
    }

    /**
     * 
     */
    @Override
    public void updatePeoplePosition(final List<Fixture> people) {
        final Map<Status, List<AABB>> peopleToDraw = new EnumMap<>(Status.class);
        peopleToDraw.put(Status.SUSCEPTIBLE, new LinkedList<>());
        peopleToDraw.put(Status.INFECTED, new LinkedList<>());
        peopleToDraw.put(Status.RECOVERED, new LinkedList<>());
        people.forEach(p -> peopleToDraw.computeIfPresent(((Person) p.getUserData()).getStatus(), (k, v) -> {
            v.add(p.getShape().createAABB());
            return v;
        }));
        Platform.runLater(() -> {
            this.mapPane.getChildren().removeAll(this.peopleImage);
            this.peopleImage.clear();
            peopleToDraw.forEach((status, position) -> position.forEach(
                    p -> peopleImage.add(createImageView(PersonImage.valueOf(status.toString()).getImage(), p))));
            this.mapPane.getChildren().addAll(peopleImage);

        });
    }

    /**
     * Creates the ImageView representing the shape with its image.
     * 
     * @param img
     *              the image to draw
     * @param shape 
     *              shape to represent with image
     * @return 
     *              the ImageView to add in Pane
     */
    private ImageView createImageView(final Image img, final AABB shape) {
        final ImageView imgView = new ImageView(img);
        imgView.setPreserveRatio(true);
        imgView.setFitHeight(shape.getHeight() * this.scale);
        imgView.relocate(shape.getMinX() * this.scale, shape.getMinY() * this.scale);
        return imgView;
    }

    /**
     * Action on checkbox.
     */
    @FXML
    public void checkAlertState() {
        if (chkAlertState.isSelected()) {
            simulationController.notifyAlert(slHomeTendencyIncrement.getValue());
        } else {
            simulationController.notifyAlertOff();
        }
    }

    /**
     * initialize the label for visualize the value of the slider.
     * 
     * @param slider the slider who change value
     * @param label  the label that have to display the value of the slider
     */
    private void initSlider(final Slider slider, final Label label) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(Double.toString(newValue.intValue()));
        });
    }

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        initSlider(slHomeTendencyIncrement, lblHomeTendencyValue);
        initSlider(slSimulationSpeed, lblSimulationSpeedValue);
        slSimulationSpeed.setOnMouseReleased(event -> {
            simulationController.setSimulationSpeed((int) slSimulationSpeed.getValue());
        });
    }


    /**
     * 
     */
    @Override
    public void setController(final SimulationController sc) {
        this.simulationController = sc;
        this.simulationController.setSimulationSpeed((int) slSimulationSpeed.getValue());
    }

    /**
     * 
     */
    @Override
    public void updateSimulationInfo(final int home, final int outside, final  int meetingPlace, final int hospital, final int infected, final int healthy,
            final int virusDeath, final int recovered) {
        Platform.runLater(() -> {
            this.lblPeopleAtHome.setText(String.valueOf(home));
            this.lblPeopleOutside.setText(String.valueOf(outside));
            this.lblPeopleMeetingPlace.setText(String.valueOf(meetingPlace));
            this.lblPeopleHospital.setText(String.valueOf(hospital));
            this.lblInfected.setText(String.valueOf(infected));
            this.lblHealthy.setText(String.valueOf(healthy));
            this.lblVirusDeath.setText(String.valueOf(virusDeath));
            this.lblRecovered.setText(String.valueOf(recovered));
        });

    }

}
