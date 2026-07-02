package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import model.WaveInfo;
import model.WaveInfo.Difficulty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import resourcemanager.ResourceManagerAlpha;

public class ControlsMenuController implements Initializable{

    @FXML
    private Button back = new Button();
    
    @FXML
    private ComboBox<String> screenSize;
    
    @FXML
    private ComboBox<String> difficultyBox;
    
    @FXML
    private Button reset = new Button();
    
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private static Difficulty difficulty = WaveInfo.Difficulty.NORMAL;
    
    private ObservableList<String> sizeList = FXCollections.observableArrayList("1280x720", "1600x900", "1920x1080");
    private ObservableList<String> difficultyList = FXCollections.observableArrayList("Easy", "Normal", "Hard");
 
    public void handleBackButton() throws IOException {
        Stage mainStage = (Stage) this.back.getScene().getWindow();
        Utilities.load("MainMenu.fxml", mainStage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        difficultyBox.setItems(this.difficultyList);
        this.screenSize.setItems(this.sizeList);
        this.screenSize.setValue(ResourceManagerAlpha.getIstance().getSettingsAsObject().getWidth() + "x" + ResourceManagerAlpha.getIstance().getSettingsAsObject().getHeight());
        if(this.screen.getWidth() > 1920 && this.screenSize.getHeight() > 1080 && this.screen.getWidth() >= 2560 && this.screen.getHeight() >= 1440) {
        	this.screenSize.getItems().add("2560x1440");
        }
        if(this.screen.getWidth() > 1920 && this.screenSize.getHeight() > 1080 && this.screen.getWidth() >= 3840 && this.screen.getHeight() >= 2160) {
        	this.screenSize.getItems().add("3840x2560");
        }
        this.screenSize.setValue(ResourceManagerAlpha.getIstance().getSettingsAsObject().getWidth() + "x" + ResourceManagerAlpha.getIstance().getSettingsAsObject().getHeight());
    }
    
    public void changeRes() {
        if(this.screenSize.getValue()==this.sizeList.get(0)) {
            ResourceManagerAlpha.getIstance().getSettingsAsObject().setWindowSize(1280, 720);
        }
        else if(this.screenSize.getValue()==this.sizeList.get(1)) {
            ResourceManagerAlpha.getIstance().getSettingsAsObject().setWindowSize(1600, 900);
        }
        else if(this.screenSize.getValue()==this.sizeList.get(2)) {
        	ResourceManagerAlpha.getIstance().getSettingsAsObject().setWindowSize(1920, 1080);
        }
        else if(this.screenSize.getValue()=="2560x1440") {
        	ResourceManagerAlpha.getIstance().getSettingsAsObject().setWindowSize(2560, 1440);
        }
        else {
        	ResourceManagerAlpha.getIstance().getSettingsAsObject().setWindowSize(3840, 2560);
        }
        ResourceManagerAlpha.getIstance().saveSettings(ResourceManagerAlpha.getIstance().getSettingsAsObject());
    }
    
    public void resetDefault() {
        ResourceManagerAlpha.getIstance().setDefaultSettings();
        this.screenSize.setValue(this.sizeList.get(0));
        this.difficultyBox.setValue(this.difficultyList.get(1));
    }
    
    public void setDiff() {
        if(this.difficultyBox.getValue()==this.difficultyList.get(0)) {
            difficulty = WaveInfo.Difficulty.EASY;
        }
        else if(this.difficultyBox.getValue()==this.difficultyList.get(1)) {
            difficulty = WaveInfo.Difficulty.NORMAL;
        }
        else {
            difficulty = WaveInfo.Difficulty.HARD;
        }
    }
    
    public static Difficulty getDiff() {
        return difficulty;
    }
}
