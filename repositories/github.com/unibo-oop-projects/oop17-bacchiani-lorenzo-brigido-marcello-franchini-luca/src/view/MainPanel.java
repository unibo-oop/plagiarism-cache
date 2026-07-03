package view;


import java.time.LocalDate;
import control.LoginControllerImpl;
import control.PersonalControllerImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class MainPanel extends Application {

    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("MainPanel.fxml"));
        final Scene scene = new Scene(root);
        stage.setTitle("Hospital Manager");
        // Set the application icon.
        stage.getIcons().add(new Image("file:res/dati/IconaPiccola.png"));

        stage.setScene(scene); 
        stage.show();


    }

    public static void main(final String[] args) {
        if (LoginControllerImpl.instance().getAccounts().size() == 0 
                && PersonalControllerImpl.instance().getWorkers().size() == 0) {
            PersonalControllerImpl
            .instance()
            .addWorker("Lorenzo", "Bacchiani", "BCCLNZ95R25C357N", "Cattolica", 
                    "Fanano", "M", "Primario", LocalDate.of(1995, 10, 25)); 
            LoginControllerImpl
            .instance()
            .addAccount(PersonalControllerImpl.instance()
                    .getWorkers()
                    .stream()
                    .filter(x -> x.getFiscalCode().equals("BCCLNZ95R25C357N")).findFirst().get(),
                    "LBacchiani", "LBacchiani");
        } else if(PersonalControllerImpl.instance().getWorkers().size() == 0) {
            PersonalControllerImpl
            .instance()
            .addWorker("Lorenzo", "Bacchiani", "BCCLNZ95R25C357N", "Cattolica", 
                    "Fanano", "M", "Primario", LocalDate.of(1995, 10, 25));            
        } else if (LoginControllerImpl.instance().getAccounts().size() == 0) {
            LoginControllerImpl
            .instance()
            .addAccount(PersonalControllerImpl.instance()
                    .getWorkers()
                    .stream()
                    .filter(x -> x.getFiscalCode().equals("BCCLNZ95R25C357N")).findFirst().get(),
                    "LBacchiani", "LBacchiani");
        } 
        launch(args);
    }
}
