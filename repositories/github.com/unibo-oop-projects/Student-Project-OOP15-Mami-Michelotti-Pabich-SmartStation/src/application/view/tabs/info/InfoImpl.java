package application.view.tabs.info;

import application.ExitStatus;
import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * Simple view of the info, no need of interfaces.
 * @author Marcin Pabich
 */
public class InfoImpl extends BorderPane {

    /**
     * Constructor for the Info that loads the content.
     */
    public InfoImpl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Info.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
     
        try {
            fxmlLoader.load();
        } catch (Exception exception) {
            ExitStatus.showErrorDialog("FXML Loading Exception", "Info.fxml could not be loaded", "Exception message: " + exception.getMessage());
            Main.close(ExitStatus.FXMLLoadingExcp);
        }     
    }

       
    
}
