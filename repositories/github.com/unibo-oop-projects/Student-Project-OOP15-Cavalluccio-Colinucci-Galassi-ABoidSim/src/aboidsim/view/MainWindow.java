package aboidsim.view;

import aboidsim.util.Input;
import aboidsim.util.InputInfo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * the main window of the program, it contains all the elements of the
 * interface.
 *
 */
public class MainWindow extends Application {

    private Stage mainStage = new Stage();

    /**
     * start method of the application, creates all the elements of the
     * interface and it puts them together.
     */
    @Override
    public void start(final Stage stage) throws Exception {

        this.mainStage = stage;
        this.mainStage.setTitle("aBoidSim");
        stage.sizeToScene();
        stage.setResizable(false);
        final HBox totalLayout = new HBox();
        final SimulationScreen boidsScreen = new SimulationScreen();
        ViewImpl.setSimulationScreen(boidsScreen);

        final VBox selections = new VBox();
        final RulesSelection rulesSelection = new RulesSelection();
        final BoidSelection boidSelection = new BoidSelection();
        final EnvironmentSelection envSelection = new EnvironmentSelection();
        final Button pres = new Button("Show Presentation");
        pres.setOnAction(e -> new PresentationWindow());

        final InfoBox infoBox = new InfoBox();
        selections.getChildren().addAll(rulesSelection, new Separator(), boidSelection, new Separator(), infoBox,
                new Separator(), envSelection, new Separator(), pres);
        selections.setStyle("-fx-background-color: WHITE;");
        selections.setPadding(new Insets(10));
        selections.setSpacing(10);

        totalLayout.getChildren().addAll(boidsScreen, selections);

        final Scene scene = new Scene(totalLayout);
        stage.setScene(scene);
        scene.getStylesheets().add("style.css");
        stage.setOnCloseRequest(e -> {
            e.consume();
            InputHandler.getInputHandler().addInput(new InputInfo(Input.CLOSE));

        });

        new PresentationWindow();

        stage.show();

    }

}
