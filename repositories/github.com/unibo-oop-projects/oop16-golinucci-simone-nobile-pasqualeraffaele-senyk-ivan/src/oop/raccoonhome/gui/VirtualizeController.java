package oop.raccoonhome.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import io.datafx.controller.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oop.raccoonhome.home.HomeConfiguration;

/**
 * 
 * 
 *
 */
@FXMLController(value = "/resources/fxml/Virtualize.fxml", title = "Set Virtualize Controller")

public class VirtualizeController implements Initializable {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    @FXML
    private JFXButton test;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/VirtualHome.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        JFXDecorator decorator = new JFXDecorator(stage, root, false, false, false);
        Scene scene = new Scene(decorator, WIDTH, HEIGHT);
        scene.getStylesheets().add(GUIMain.class.getResource("/resources/css/main.css").toExternalForm());
        stage.setResizable(false);
        stage.setTitle("Virtualization Window");
        stage.setScene(scene);
        if (HomeConfiguration.getIstance().isSimulationActive()) {
            test.setDisable(true);
        } else {
            test.setDisable(false);
        }
        test.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

            if (!HomeConfiguration.getIstance().isSimulationActive()) {
                HomeConfiguration.getIstance().setSimActive(true);
                System.out.println("Set the simulation to true");
                test.setDisable(true);
                stage.showAndWait();
                HomeConfiguration.getIstance().setSimActive(false);
                System.out.println("Set the simulation to False");
                test.setDisable(false);
            }
        });

    }

}
