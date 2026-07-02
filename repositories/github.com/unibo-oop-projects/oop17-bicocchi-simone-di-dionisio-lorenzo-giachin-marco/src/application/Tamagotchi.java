package application;

import controller.TamagotchiController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelImpl;
import view.View;
import view.ViewImpl;

/**
 * 
 * TemporaryMain.
 *
 */
public class Tamagotchi extends Application {

    /**
     * @param args 
     * 
     */
    public static void main(final String[] args) {
        Tamagotchi.launch(Tamagotchi.class);
    }

    /**
     * @throws Exception 
     * @param primaryStage 
     */
    public void start(final Stage primaryStage) throws Exception {
        startApplication();
    }

    /**
     * 
     */
    public static void startApplication() {
        Model model = new ModelImpl();
        TamagotchiController controller = new TamagotchiController(model);
        View view = new ViewImpl();
        controller.setViewManager(view);
        view.attachViewManager(controller.getViewManager());
        view.start();
    }
}
