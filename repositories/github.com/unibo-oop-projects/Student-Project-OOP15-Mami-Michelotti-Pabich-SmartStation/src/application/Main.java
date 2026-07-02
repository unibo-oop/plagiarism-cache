package application;

import application.controller.MainController;
import application.controller.MainControllerImpl;
import application.model.Station;
import application.model.StationImpl;
import application.view.MainContentImpl;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mnmlwindow.MinimalWindow;
import mnmlwindow.MinimalWindowImpl;


/**
 * Main class of the application that launch it.
 */
public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) {
        //First set all the models, views and controllers
        final Station model = new StationImpl();
        final MainContentImpl view = new MainContentImpl();
        final MainController controller = new MainControllerImpl();               
        
        //Set the view's controller
        view.setController(controller);
        
        //Set the controller's ref to model and view
        controller.setModel(model);
        controller.setView(view);
        
        //Load the configuration file
        controller.loadConfiguration();
                    
        //Creating the style for the custom winndow
        final MinimalWindow minimalWindow = new MinimalWindowImpl(primaryStage, 840, 700);
        minimalWindow.setContent(view);
        minimalWindow.setTitle("Smart Station");
        minimalWindow.setFooter("JavaFX Project  |  Smart Station  |  M: Mami  |  V: Pabich  |  C: Michelotti");     
        minimalWindow.setLogo(new Image("/resources/pump.png"));
                    
        //Finally, show the entire window
        minimalWindow.showWindow();                     
    }
        
    /**
     * main method of the Main class, that launches the app.
     * @param args Eventual arguments when the app starts
     */
    public static void main(final String[] args) {
        launch(args);
    }
        
    /**
     * Kill the application.    
     * @param eStatus Error given when the app is killed
     */
    public static void close(final ExitStatus eStatus) {
        System.exit(eStatus.getExitValue());
    }
}