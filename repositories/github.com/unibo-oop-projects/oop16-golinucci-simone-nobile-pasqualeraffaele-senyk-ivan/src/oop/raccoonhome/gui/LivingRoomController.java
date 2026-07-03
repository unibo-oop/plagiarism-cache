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
 * 
 *
 */
@FXMLController(value = "/resources/fxml/LivingRoom.fxml", title = "Set LivingRoom Controller")

public class LivingRoomController implements Initializable, DialogInterface {

    private static final int CEILING_LAMP = 0;
    private static final int TV_LIGHT = 1;
    private static final int KITCHEN_LAMP = 2;
    private static final int SHUTTER = 3;
    private static final String CEILING_LIVING_LAMP = "Ceiling Living Bulb";
    private static final String TV_LIVING_LAMP = "Tv Bajour Living";
    private static final String KITCHEN_LIVING_LAMP = "Kitchen Lamp Living";
    private static final String SHUTTER_LIVING = "Shutter Living";
    private static final String ALL_LAMP_ON = "All light in Living - On";
    private static final String ALL_LAMP_OFF = "All light in Living - Off";
    private static final String GENERAL_INTESITY_SET = "All light in Living - set Intensity";
    private AllController livingController = new AllController();

    private Room livingRoom = HomeConfiguration.getIstance().getHosue().getRoom(ERoom.SALOTTO).get(0);

    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private StackPane root;

    @FXML
    private JFXSlider bulbDimmer; // Set the TV bajour Light Bulb Intensity

    @FXML
    private JFXButton turnOnCeiling; // turn on Ceiling Bulb

    @FXML
    private JFXButton turnOffCeiling; // turn off Ceiling Bulb

    @FXML
    private JFXButton turnOnTVLight; // turn on TV bajour

    @FXML
    private JFXButton turnOffTVLight; // turn off TV bajour

    @FXML
    private JFXButton turnOnKitchen; // turn on kitchen Light Bulb

    @FXML
    private JFXButton turnOffKitchen; // turn off kitchen Light Bulb

    @FXML
    private JFXButton openShutter; // open the Main Shutter

    @FXML
    private JFXButton closeShutter; // Close the Main Shutter

    @FXML
    private JFXToggleButton generalPower; // General kill Switch

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

        bulbDimmer.setOnMouseClicked((e) -> {
            for (final Device deviceSetIntensity : livingRoom.getDevice()) {
                if (deviceSetIntensity instanceof Lamp) {
                    Log.getIstance().write(GENERAL_INTESITY_SET);
                    livingController.setIntensiryDevice((Lamp) deviceSetIntensity, bulbDimmer.getValue());
                }
            }
        });

        turnOnCeiling.setOnAction((e) -> {
            if (livingRoom.getDevice(CEILING_LAMP).isOn()) {
                dialog.setTransitionType(DialogTransition.CENTER);
                dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(CEILING_LIVING_LAMP);
                livingController.turnOnLamp(livingRoom.getDevice(CEILING_LAMP));
            }
        });

        turnOffCeiling.setOnAction((e) -> {
            if (livingRoom.getDevice(CEILING_LAMP).isOn()) {
                Log.getIstance().write(CEILING_LIVING_LAMP);
                livingController.turnOffLamp(livingRoom.getDevice(CEILING_LAMP));
            } else {
                allOff.setTransitionType(DialogTransition.CENTER);
                allOff.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        turnOnTVLight.setOnAction((e) -> {
            if (livingRoom.getDevice(TV_LIGHT).isOn()) {
                dialog.setTransitionType(DialogTransition.CENTER);
                dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(TV_LIVING_LAMP);
                livingController.turnOnLamp(livingRoom.getDevice(TV_LIGHT));
            }
        });

        turnOffTVLight.setOnAction((e) -> {
            if (livingRoom.getDevice(TV_LIGHT).isOn()) {
                Log.getIstance().write(TV_LIVING_LAMP);
                livingController.turnOffLamp(livingRoom.getDevice(TV_LIGHT));
            } else {
                allOff.setTransitionType(DialogTransition.CENTER);
                allOff.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        turnOnKitchen.setOnAction((e) -> {
            if (livingRoom.getDevice(KITCHEN_LAMP).isOn()) {
                dialog.setTransitionType(DialogTransition.CENTER);
                dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(KITCHEN_LIVING_LAMP);
                livingController.turnOnLamp(livingRoom.getDevice(KITCHEN_LAMP));
            }
        });

        turnOffKitchen.setOnAction((e) -> {
            if (livingRoom.getDevice(KITCHEN_LAMP).isOn()) {
                Log.getIstance().write(KITCHEN_LIVING_LAMP);
                livingController.turnOffLamp(livingRoom.getDevice(KITCHEN_LAMP));
            } else {
                allOff.setTransitionType(DialogTransition.CENTER);
                allOff.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        openShutter.setOnAction((e) -> {
            if (((OpenCloseDevice) (livingRoom.getDevice(SHUTTER))).isOpen()) {
                open.setTransitionType(DialogTransition.CENTER);
                open.show((StackPane) context.getRegisteredObject("ContentPane"));
            } else {
                Log.getIstance().write(SHUTTER_LIVING);
                livingController.openShutter(((OpenCloseDevice) livingRoom.getDevice(SHUTTER)));
            }
        });

        closeShutter.setOnAction((e) -> {
            if (((OpenCloseDevice) (livingRoom.getDevice(SHUTTER))).isOpen()) {
                Log.getIstance().write(SHUTTER_LIVING);
                livingController.closeShutter(((OpenCloseDevice) livingRoom.getDevice(SHUTTER)));
            } else {
                close.setTransitionType(DialogTransition.CENTER);
                close.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        generalPower.setOnAction((e) -> {
            if (generalPower.isSelected()) {
                Log.getIstance().write(ALL_LAMP_ON);
                for (final Device deviceOn : livingRoom.getDevice()) {
                    if (deviceOn instanceof Lamp) {
                        livingController.turnOnLamp(deviceOn);
                    }
                }
            } else {
                Log.getIstance().write(ALL_LAMP_OFF);
                for (final Device deviceOff : livingRoom.getDevice()) {
                    if (deviceOff instanceof Lamp) {
                        livingController.turnOffLamp(deviceOff);
                    }
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
