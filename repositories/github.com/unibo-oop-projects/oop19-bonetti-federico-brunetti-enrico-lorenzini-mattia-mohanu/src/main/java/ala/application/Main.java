package ala.application;
 
import ala.views.DisplayManagerView;
import javafx.application.Application;
import javafx.stage.Stage;


public final class Main extends Application {
    @Override
    public void start(final Stage primaryStage) {
        try {
            DisplayManagerView menuManagerView = new DisplayManagerView(primaryStage);

            // show stage
            primaryStage.setTitle("Apocalypsis Lucifer Acclamat");
            primaryStage.setScene(menuManagerView.getMainMenuScene());
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.show();

         } catch (Exception e) {
                e.printStackTrace();
         }
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
