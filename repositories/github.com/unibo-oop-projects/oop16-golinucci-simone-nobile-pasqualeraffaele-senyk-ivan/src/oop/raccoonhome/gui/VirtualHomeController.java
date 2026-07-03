package oop.raccoonhome.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import io.datafx.controller.FXMLController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import oop.raccoonhome.controller.AllController;
import oop.raccoonhome.device.OpenCloseDevice;
import oop.raccoonhome.home.ERoom;
import oop.raccoonhome.home.HomeConfiguration;

/**
 * 
 * 
 *
 */
@FXMLController(value = "/resources/fxml/VirtualHome.fxml", title = "Virtualize")
public final class VirtualHomeController extends AllController implements Initializable {

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private static final int FOURTH = 3;

    @FXML
    private JFXRadioButton rightBedTableLight;

    @FXML
    private JFXRadioButton leftBedTableLight;

    @FXML
    private JFXRadioButton bedRoomCeiling;

    @FXML
    private JFXRadioButton livingRoomCeiling;

    @FXML
    private JFXRadioButton bajourTV;

    @FXML
    private JFXRadioButton kitcherLight;

    @FXML
    private JFXRadioButton mirrorLight;

    @FXML
    private JFXRadioButton bathCeiling;

    @FXML
    private JFXRadioButton garageLight;

    @FXML
    private JFXRadioButton garageDoor;

    @FXML
    private JFXRadioButton bathShutter;

    @FXML
    private JFXRadioButton livingRoomShutter;

    @FXML
    private JFXRadioButton bedRoomShutter;

    @FXML
    private JFXButton test;

    /**
     * 
     */
    public void update() {

        leftBedTableLight.setSelected(HomeConfiguration.getIstance()
                .getHosue()
                .getRoom(ERoom.CAMERADALETTO)
                .get(0)
                .getDevice(SECOND)
                .isOn());
        rightBedTableLight.setSelected(HomeConfiguration
                .getIstance()
                .getHosue()
                .getRoom(ERoom.CAMERADALETTO)
                .get(0)
                .getDevice(FIRST)
                .isOn());
        bedRoomCeiling.setSelected(HomeConfiguration.getIstance()
                .getHosue()
                .getRoom(ERoom.CAMERADALETTO)
                .get(0)
                .getDevice(THIRD)
                .isOn());
        livingRoomCeiling.setSelected(HomeConfiguration
                .getIstance()
                .getHosue()
                .getRoom(ERoom.SALOTTO)
                .get(0)
                .getDevice(FIRST)
                .isOn());
        bajourTV.setSelected(HomeConfiguration
                .getIstance()
                .getHosue()
                .getRoom(ERoom.SALOTTO)
                .get(0)
                .getDevice(SECOND)
                .isOn());
        kitcherLight.setSelected(HomeConfiguration
                .getIstance()
                .getHosue()
                .getRoom(ERoom.SALOTTO)
                .get(0)
                .getDevice(THIRD)
                .isOn());
        mirrorLight.setSelected(HomeConfiguration
                .getIstance()
                .getHosue()
                .getRoom(ERoom.BAGNO)
                .get(0)
                .getDevice(FIRST)
                .isOn());
        bathCeiling.setSelected(HomeConfiguration
                .getIstance()
                .getHosue()
                .getRoom(ERoom.BAGNO)
                .get(0)
                .getDevice(SECOND)
                .isOn());
        garageLight.setSelected(HomeConfiguration
                .getIstance()
                .getHosue()
                .getRoom(ERoom.GARAGE)
                .get(0)
                .getDevice(FIRST)
                .isOn());
        garageDoor.setSelected(((OpenCloseDevice) HomeConfiguration
                .getIstance()
                .getHosue()
                .getRoom(ERoom.GARAGE)
                .get(0)
                .getDevice(SECOND))
                .isOn());
        bathShutter.setSelected(((OpenCloseDevice) HomeConfiguration
                .getIstance()
                .getHosue()
                .getRoom(ERoom.BAGNO)
                .get(0)
                .getDevice(THIRD))
                .isOpen());
         livingRoomShutter.setSelected(((OpenCloseDevice) HomeConfiguration //Da sistemare
                 .getIstance()
                 .getHosue()
                 .getRoom(ERoom.SALOTTO)
                 .get(0)
                 .getDevice(FOURTH))
                 .isOpen());
         bedRoomShutter.setSelected(((OpenCloseDevice) HomeConfiguration //Da sistemare
                 .getIstance()
                 .getHosue()
                 .getRoom(ERoom.CAMERADALETTO)
                 .get(0)
                 .getDevice(FOURTH))
                 .isOpen());
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        update();
        test.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            update();
        });
    }
}
