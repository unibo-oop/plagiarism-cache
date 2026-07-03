package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class AboutUsController implements Initializable {

    @FXML
    private Hyperlink hMenù;

    @FXML
    void hlBack(final ActionEvent event) throws IOException {
        final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/MainPanel.fxml"));
        final Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }

    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {
    // TODO Auto-generated method stub

    }

}
