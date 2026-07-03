package oop.raccoonhome.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import oop.raccoonhome.controller.AllController;
import oop.raccoonhome.device.Device;
import oop.raccoonhome.device.Lamp;
import oop.raccoonhome.device.OpenCloseDevice;
import oop.raccoonhome.home.ERoom;
import oop.raccoonhome.home.HomeConfiguration;
import oop.raccoonhome.home.Room;
import oop.raccoonhome.util.Log;

/**
 * A Class that controls the View of BedRoom.
 */
@FXMLController(value = "/resources/fxml/BedRoom.fxml", title = "Set BedRoom Controller")

public class BedRoomController implements Initializable, DialogInterface {

    private static final int RIGHT_LAMP = 0;
    private static final int LEFT_LAMP = 1;
    private static final int CEILING_LAMP = 2;
    private static final int SHUTTERS_SHUTTER = 3;
    private static final String RIGHT_BED_LAMP = "Right Bed Lamp";
    private static final String LEFT_BED_LAMP = "Left Bed Lamp";
    private static final String CEILING_BED_LAMP = "Ceiling Bed Lamp";
    private static final String SHUTTER_BED = "Shutter Bed";
    private static final String ALL_LAMP_ON = "All light in Bed - On";
    private static final String ALL_LAMP_OFF = "All light in Bed - Off";
    private static final String GENERAL_INTESITY_SET = "All light in Bed - set Intensity";

    private Room bedRoom = HomeConfiguration.getIstance().getHosue().getRoom(ERoom.CAMERADALETTO).get(0);
    private AllController bedController = new AllController();

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private JFXButton turnOnRightBedSideTable; // Turn On Right BedSide Table
                                               // Bulb

    @FXML
    private JFXButton turnOffRightBedSideTable; // Turn Off Right BedSide Table
                                                // Bulb

    @FXML
    private JFXButton turnOnLeftBedSideTable; // Turn On Left BedSide Table Bulb

    @FXML
    private JFXButton turnOffLeftBedSideTable; // Turn Off Left BedSide Table
                                               // Bulb

    @FXML
    private JFXButton turnOnCeiling; // Turn On Ceiling Bulb

    @FXML
    private JFXButton turnOffCeiling; // Turn Off Ceiling Bulb

    @FXML
    private JFXButton openShutter; // Open Window Shutter

    @FXML
    private JFXButton closeShutter; // Open Window Shutter

    @FXML
    private JFXToggleButton generalPower; // Manage General Light Control

    @FXML
    private JFXSlider bulbDimmer; // Manage Intensity Light Control

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

        JFXDialog dialog = new JFXDialog();
        JFXDialog allOff = new JFXDialog();
        JFXDialog open = new JFXDialog();
        JFXDialog close = new JFXDialog();
        loadDialog(close, "/resources/dialog/ShutterDown.fxml");
        loadDialog(open, "/resources/dialog/ShutterUp.fxml");
        loadDialog(allOff, "/resources/dialog/LightOff.fxml");
        loadDialog(dialog, "/resources/dialog/LightOnYet.fxml");

        turnOnRightBedSideTable.setOnAction((e) -> {
            if (bedRoom.getDevice(RIGHT_LAMP).isOn()) {
                dialog.setTransitionType(DialogTransition.CENTER);
                dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(RIGHT_BED_LAMP);
                bedController.turnOnLamp(bedRoom.getDevice(RIGHT_LAMP));
            }
        });

        turnOffRightBedSideTable.setOnAction((e) -> {
            if (bedRoom.getDevice(RIGHT_LAMP).isOn()) {
                Log.getIstance().write(RIGHT_BED_LAMP);
                bedController.turnOffLamp(bedRoom.getDevice(RIGHT_LAMP));
            } else {
                allOff.setTransitionType(DialogTransition.CENTER);
                allOff.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        turnOnLeftBedSideTable.setOnAction((e) -> {
            if (bedRoom.getDevice(LEFT_LAMP).isOn()) {
                dialog.setTransitionType(DialogTransition.CENTER);
                dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(LEFT_BED_LAMP);
                bedController.turnOnLamp(bedRoom.getDevice(LEFT_LAMP));
            }
        });

        turnOffLeftBedSideTable.setOnAction((e) -> {
            if (bedRoom.getDevice(LEFT_LAMP).isOn()) {
                Log.getIstance().write(LEFT_BED_LAMP);
                bedController.turnOffLamp(bedRoom.getDevice(LEFT_LAMP));
            } else {
                allOff.setTransitionType(DialogTransition.CENTER);
                allOff.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        turnOnCeiling.setOnAction((e) -> {
            if (bedRoom.getDevice(CEILING_LAMP).isOn()) {
                dialog.setTransitionType(DialogTransition.CENTER);
                dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(CEILING_BED_LAMP);
                bedController.turnOnLamp(bedRoom.getDevice(CEILING_LAMP));
            }
        });

        turnOffCeiling.setOnAction((e) -> {
            if (bedRoom.getDevice(CEILING_LAMP).isOn()) {
                Log.getIstance().write(CEILING_BED_LAMP);
                bedController.turnOffLamp(bedRoom.getDevice(CEILING_LAMP));
            } else {
                allOff.setTransitionType(DialogTransition.CENTER);
                allOff.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        openShutter.setOnAction((e) -> {
            if (((OpenCloseDevice) (bedRoom.getDevice(SHUTTERS_SHUTTER))).isOpen()) {
                open.setTransitionType(DialogTransition.CENTER);
                open.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(SHUTTER_BED);
                bedController.openShutter(((OpenCloseDevice) bedRoom.getDevice(SHUTTERS_SHUTTER)));
            }
        });

        closeShutter.setOnAction((e) -> {
            if (((OpenCloseDevice) (bedRoom.getDevice(SHUTTERS_SHUTTER))).isOpen()) {
                Log.getIstance().write(SHUTTER_BED);
                bedController.closeShutter(((OpenCloseDevice) bedRoom.getDevice(SHUTTERS_SHUTTER)));
            } else {
                close.setTransitionType(DialogTransition.CENTER);
                close.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        generalPower.setOnAction((e) -> {
            if (generalPower.isSelected()) {
                Log.getIstance().write(ALL_LAMP_ON);
                for (final Device deviceOn : bedRoom.getDevice()) {
                    if (deviceOn instanceof Lamp) {
                        bedController.turnOnLamp(deviceOn);
                    }
                }
            } else {
                Log.getIstance().write(ALL_LAMP_OFF);
                for (final Device deviceOff : bedRoom.getDevice()) {
                    if (deviceOff instanceof Lamp) {
                        bedController.turnOffLamp(deviceOff);
                    }
                }
            }
        });

        bulbDimmer.setOnMouseClicked((e) -> {
            for (final Device deviceSetIntensity : bedRoom.getDevice()) {
                if (deviceSetIntensity instanceof Lamp) {
                    Log.getIstance().write(GENERAL_INTESITY_SET);
                    bedController.setIntensiryDevice((Lamp) deviceSetIntensity, bulbDimmer.getValue());
                }
            }
        });
    }

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
