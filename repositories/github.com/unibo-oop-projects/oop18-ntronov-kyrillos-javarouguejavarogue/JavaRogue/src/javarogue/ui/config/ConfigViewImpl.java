package javarogue.ui.config;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javarogue.utility.Resolution;

public class ConfigViewImpl implements ConfigView {

	private ConfigController controller;
	private Stage stage;
	
	public ConfigViewImpl() {
		this.stage = new Stage();
	}
	
	@Override
	public void setController(ConfigController controller) {
		this.controller = controller;
	}

	@Override
	public void open() {
		this.stage.setScene(this.buildScene());
		this.stage.show();
	}

	@Override
	public void close() {
		this.stage.close();
	}

	private Scene buildScene() {
		// Make GridPane
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(25, 25, 25, 25));
		// Make resolutions title
		Text titleRes = new Text("Select Resolution");
		titleRes.setFont(Font.font("Tahoma", 20));
		root.add(titleRes, 0, 0);
		// Make Resolution List
		ListView<Resolution> listRes = new ListView<>();
		listRes.setItems(FXCollections.observableArrayList(controller.getResolutionList()));
		listRes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Resolution>() {
			@Override
			public void changed(ObservableValue<? extends Resolution> observable, Resolution oldValue, Resolution newValue) {
				controller.saveResolution(newValue);
			}
		});
		root.add(listRes, 0, 2);
		// Make tilesets title
		Text titleTiles = new Text("Select Tileset");
		titleTiles.setFont(Font.font("Tahoma", 20));
		root.add(titleTiles, 1, 0);
		// Make tilesets List
		ListView<String> listTiles = new ListView<>();
		listTiles.setItems(FXCollections.observableArrayList(controller.getTileSets()));
		listTiles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				File check = new File("res/tileset_" + newValue + ".png");
				if(check.exists()) {
					controller.saveTileSet(newValue);
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Warning!");
					alert.setHeaderText(null);
					alert.setContentText("res/tileset_" + newValue + ".png" + " does not exist");
					alert.showAndWait();
					listTiles.getSelectionModel().clearAndSelect(0);
				}
			}
		});
		root.add(listTiles, 1, 2);
		// Make Seed Text
		Text seedText = new Text("Select Seed");
		seedText.setFont(Font.font("Tahoma", 20));
		root.add(seedText, 1, 3);
		// Make Seed Textfield
		TextField seedField = new TextField();
		Long seed = this.controller.getRandomSeed();
		seedField.setText(seed.toString());
		seedField.textProperty().addListener((obs, oldText, newText) -> {
			try {
				this.controller.saveSeed(Long.parseLong(newText));
			} catch(Exception e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Warning!");
				alert.setHeaderText(null);
				alert.setContentText("Seed must be a numerical value.");
				alert.showAndWait();
				seedField.setText(oldText);
			}			
		});
		root.add(seedField, 1, 4);
		// Make fullscreen checkbox
		CheckBox fullscreenCheckBox = new CheckBox("Fullscreen");
		fullscreenCheckBox.setIndeterminate(false);
		fullscreenCheckBox.setSelected(false);
		fullscreenCheckBox.selectedProperty().addListener((observable, newVal, oldVal) -> {
			this.controller.saveFullscreen(fullscreenCheckBox.isSelected());
		});
		root.add(fullscreenCheckBox, 0, 3);
		// Make start button
		Button button = new Button("Start");
		button.setPrefWidth(300);
		button.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent action) {
				if(!controller.launchGame()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Warning!");
					alert.setHeaderText(null);
					alert.setContentText("Please, select all the options.");
					alert.showAndWait();
				} else {
					close();
				}
			}
		});
		root.add(button, 0, 4);
		// Set Scene
		return new Scene(root, 500, 400);
	}

}
