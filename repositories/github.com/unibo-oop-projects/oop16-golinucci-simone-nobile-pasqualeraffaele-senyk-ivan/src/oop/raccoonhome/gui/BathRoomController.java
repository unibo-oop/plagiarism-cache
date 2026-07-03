package oop.raccoonhome.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
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
import oop.raccoonhome.device.Device;
import oop.raccoonhome.device.Lamp;
import oop.raccoonhome.device.OpenCloseDevice;
import oop.raccoonhome.home.ERoom;
import oop.raccoonhome.home.HomeConfiguration;
import oop.raccoonhome.home.Room;
import oop.raccoonhome.util.Log;

/**
 * A class that controls Bath Room events.
 */
@FXMLController(value = "/resources/fxml/BathRoom.fxml", title = "Set BathRoom Controller")

public class BathRoomController implements Initializable, DialogInterface {

    private static final int MIRROR_LAMP = 0;
    private static final int MAIN_LAMP = 1;
    private static final int SHUTTERS_SHUTTER = 2;
    private static final String MIRROR_LIGHT = "Mirror Light in Bath";
    private static final String MAIN_LIGHT = "Main Light in Bath";
    private static final String SHUTTER_CONTROL = "Shutter in Bath";
    private static final String ALL_LAMP_ON = "All light in Bath - On";
    private static final String ALL_LAMP_OFF = "All light in Bath - Off";
    private static final String GENERAL_INTESITY_SET = "All light in Bath - set Intensity";
    private AllController bathController = new AllController();

    private Room bathRoom = HomeConfiguration.getIstance().getHosue().getRoom(ERoom.BAGNO).get(0);
    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private JFXSlider lightIntensity; // Set Intensity of Main Light

    @FXML
    private JFXButton mirrorLightOn; // Mirror On

    @FXML
    private JFXButton mirrorLightOff; // Mirror Off

    @FXML
    private JFXButton mainLightOn; // Main On

    @FXML
    private JFXButton mainLightOff; // Mirror Off

    @FXML
    private JFXButton openShutter; // Open Bath Shutter

    @FXML
    private JFXButton closeShutter; // Close Bath

    @FXML
    private JFXToggleButton mainSwitch; // Kill Switch

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

        mirrorLightOn.setOnAction((e) -> {
            if (bathRoom.getDevice(MIRROR_LAMP).isOn()) {
                dialog.setTransitionType(DialogTransition.CENTER);
                dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(MIRROR_LIGHT);
                bathController.turnOnLamp(bathRoom.getDevice(MIRROR_LAMP));
            }
        });

        mirrorLightOff.setOnAction((e) -> {
            if (bathRoom.getDevice(MIRROR_LAMP).isOn()) {
                Log.getIstance().write(MIRROR_LIGHT);
                bathController.turnOffLamp(bathRoom.getDevice(MIRROR_LAMP));
            } else {
                allOff.setTransitionType(DialogTransition.CENTER);
                allOff.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        mainLightOn.setOnAction((e) -> {
            if (bathRoom.getDevice(MAIN_LAMP).isOn()) {
                dialog.setTransitionType(DialogTransition.CENTER);
                dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(MAIN_LIGHT);
                bathController.turnOnLamp(bathRoom.getDevice(MAIN_LAMP));

            }
        });

        mainLightOff.setOnAction((e) -> {
            if (bathRoom.getDevice(MAIN_LAMP).isOn()) {
                Log.getIstance().write(MAIN_LIGHT);
                bathController.turnOffLamp(bathRoom.getDevice(1));
            } else {
                allOff.setTransitionType(DialogTransition.CENTER);
                allOff.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        openShutter.setOnAction((e) -> {
            if (((OpenCloseDevice) (bathRoom.getDevice(SHUTTERS_SHUTTER))).isOpen()) {
                open.setTransitionType(DialogTransition.CENTER);
                open.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(SHUTTER_CONTROL);
                bathController.openShutter(((OpenCloseDevice) bathRoom.getDevice(SHUTTERS_SHUTTER)));
            }
        });

        closeShutter.setOnAction((e) -> {
            if (((OpenCloseDevice) (bathRoom.getDevice(SHUTTERS_SHUTTER))).isOpen()) {
                Log.getIstance().write(SHUTTER_CONTROL);
                bathController.closeShutter(((OpenCloseDevice) bathRoom.getDevice(SHUTTERS_SHUTTER)));
            } else {
                close.setTransitionType(DialogTransition.CENTER);
                close.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        mainSwitch.setOnAction((e) -> {
            if (mainSwitch.isSelected()) {
                Log.getIstance().write(ALL_LAMP_ON);
                for (final Device deviceOn : bathRoom.getDevice()) {
                    if (deviceOn instanceof Lamp) {
                        bathController.turnOnLamp(deviceOn);
                    }
                }
            } else {
                Log.getIstance().write(ALL_LAMP_OFF);
                for (final Device deviceOff : bathRoom.getDevice()) {
                    if (deviceOff instanceof Lamp) {
                        bathController.turnOffLamp(deviceOff);
                    }
                }
            }
        });

        lightIntensity.setOnMouseClicked((e) -> {
            for (final Device deviceSetIntensity : bathRoom.getDevice()) {
                if (deviceSetIntensity instanceof Lamp) {
                    Log.getIstance().write(GENERAL_INTESITY_SET);
                    bathController.setIntensiryDevice((Lamp) deviceSetIntensity, lightIntensity.getValue());
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
