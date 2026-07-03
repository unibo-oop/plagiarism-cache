package oop.raccoonhome.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXDialog.DialogTransition;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import oop.raccoonhome.controller.AllController;
import oop.raccoonhome.device.Door;
import oop.raccoonhome.home.ERoom;
import oop.raccoonhome.home.HomeConfiguration;
import oop.raccoonhome.home.Room;
import oop.raccoonhome.util.Log;

/**
 * A class that controls Garage.
 *
 */

@FXMLController(value = "/resources/fxml/Garage.fxml", title = "Set GarageController")

public class GarageController implements Initializable, DialogInterface {

    private static final int BULB_LAMP = 0;
    private static final int DOOR_SHUTTER = 1;
    private static final String BULB_GARAGE_LAMP = "Bulb Garage";
    private static final String DOOR_GARAGE = "Door Garage";
    private static final String ACCEPT_PW = "Password";
    private AllController garageController = new AllController();

    private Room garage = HomeConfiguration.getIstance().getHosue().getRoom(ERoom.GARAGE).get(0);
    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private JFXButton turnOnBulb; // Turn On

    @FXML
    private JFXButton turnOffBulb; // Turn Off

    @FXML
    private JFXButton openDoor; // Open Main door

    @FXML
    private JFXButton closeDoor; // Close Main Door

    @FXML
    private JFXPasswordField textPassword;

    @FXML
    private JFXButton acceptButton;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        JFXDialog dialog = new JFXDialog();
        JFXDialog allOff = new JFXDialog();
        JFXDialog login = new JFXDialog();
        JFXDialog open = new JFXDialog();
        JFXDialog close = new JFXDialog();
        loadDialog(close, "/resources/dialog/DoorClosed.fxml");
        loadDialog(open, "/resources/dialog/DoorOpen.fxml");
        loadDialog(allOff, "/resources/dialog/LightOff.fxml");
        loadDialog(dialog, "/resources/dialog/LightOnYet.fxml");
        loadDialog(login, "/resources/dialog/Login.fxml");

        Door door = (Door) garage.getDevice(DOOR_SHUTTER);
        // set Open Door Button disable for password check
        openDoor.setDisable(true);

        /*
         * Turn on the Main lightBulb in Garage
         */

        turnOnBulb.setOnAction((e) -> {
            if (garage.getDevice(BULB_LAMP).isOn()) {
                dialog.setTransitionType(DialogTransition.CENTER);
                dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(BULB_GARAGE_LAMP);
                garageController.turnOnLamp(garage.getDevice(BULB_LAMP));
            }
        });
        /*
         * Turn off the Main lightBulb in Garage
         */

        turnOffBulb.setOnAction((e) -> {
            if (garage.getDevice(BULB_LAMP).isOn()) {
                Log.getIstance().write(BULB_GARAGE_LAMP);
                garageController.turnOffLamp(garage.getDevice(BULB_LAMP));
            } else {
                allOff.setTransitionType(DialogTransition.CENTER);
                allOff.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });
        /*
         * Open the main garage Door and ask for authentication to open it due
         * safety conditions.
         */

        openDoor.setOnAction((e) -> {
            if (door.isOn()) {
                open.setTransitionType(DialogTransition.CENTER);
                open.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(DOOR_GARAGE);
                garageController.openDoor(door);
                openDoor.setDisable(true);
            }
        });
        /*
         * Close the main garage Door
         */

        closeDoor.setOnAction((e) -> {
            if (door.isOn()) {
                Log.getIstance().write(DOOR_GARAGE);
                garageController.closeDoor(door);
                door.close();
            } else {
                close.setTransitionType(DialogTransition.CENTER);
                close.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });
        /*
         * Button for accept Password
         */
        acceptButton.setOnAction((e) -> {
            if (garageController.checkPassword(textPassword.getText())) {
                Log.getIstance().write(ACCEPT_PW);
                openDoor.setDisable(false);
                door.open();
                textPassword.clear();
            } else {
                System.out.println("Password sbagliata - scrivere RaccoonTeam");
                login.setTransitionType(DialogTransition.CENTER);
                login.show((StackPane) context.getRegisteredObject("ContentPane"));
                door.close();
            }
        });
    }

    /*
     * A method that loads FXML file into a Dialog
     */

    @Override
    public void loadDialog(final JFXDialog dialog, final String resource) {
        try {
            AnchorPane prova = FXMLLoader.load(getClass().getResource(resource));
            dialog.setContent(prova);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
