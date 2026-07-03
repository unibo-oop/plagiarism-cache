package oop.raccoonhome.gui;

import com.jfoenix.controls.JFXDecorator;

import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *  A Class that starts the main stage of RaccoonHoome.
 */

public class GUIMain extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;
    @FXML
    private StackPane splashRoot;

    /**
     * @param args
     *            Create Primary Stage of the View.
     * @throws Exception
     *             Throws an Exception in case of createHandler load Fails
     */
    public void startGUI(final String[] args) {
        launch(args);


    }

    @Override
    public void start(final Stage stage) throws Exception, VetoException {
        Flow flow = new Flow(MainController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);
        flow.createHandler(flowContext).start(container);
        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setCustomMaximize(true);
        Scene scene = new Scene(decorator, WIDTH, HEIGHT);
        scene.getStylesheets().add(GUIMain.class.getResource("/resources/css/main.css").toExternalForm());
        stage.setMinWidth(HEIGHT);
        stage.setMinHeight(WIDTH);
        stage.setScene(scene);
        stage.show();
        //decorator.setOnCloseButtonAction(() -> Platform.exit());
    }

}