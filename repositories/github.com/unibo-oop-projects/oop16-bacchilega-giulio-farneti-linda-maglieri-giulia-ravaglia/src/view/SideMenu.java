package view;
import java.util.ArrayList;
import java.util.List;
import controller.ControllerImp;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Menu.OptionsGame;
import view.Popup.ConfirmBox;
import view.RulesBook.RulesBookLoading;
import view.Utilities.Utilities;
import view.Utilities.ScreenDimension.ScreenDim;
import view.Popup.*;
/**
 * 
 * Author: Giulia Maglieri.
 *
 */
public class SideMenu {
    private List<Button> buttons  = new ArrayList<>();
    private ImageView iv;
    private Scene scene;
    /* create object MainMenu*/

    private OptionsGame options = new OptionsGame();
    /**
     * 
     * @param title String
     * @return Node
     */
    public Node display(final String title) {
        VBox sideMenu = new VBox();
            sideMenu.setPrefWidth(Utilities.VBOX_WIDTH);
            sideMenu.setId("vertical-menu");
            for (int i = 0; i < Utilities.BUTTONS; i++) {
                Button btn = new Button();
                buttons.add(btn);
                btn.setPrefSize(Utilities.BTN_WIDTH, Utilities.BTN_HEIGHT);
                btn.setId("button");
                iv = new ImageView(new Image(SideMenu.class.getResource("/image/" + String.valueOf(i) + ".png").toExternalForm()));
                this.buttons.get(i).setGraphic(iv);
                sideMenu.getChildren().add(btn);
                btn.setOnAction(e -> this.actionPerformed(e));
                this.menuDecorator(btn);
            }
                sideMenu.setAlignment(Pos.CENTER);
                scene = new Scene(sideMenu, Utilities.VBOX_WIDTH, ScreenDim.getHeight());
                scene.getStylesheets().addAll(this.getClass().getResource("blueBoard.css").toExternalForm());
                return sideMenu;
    }
    /**
     * 
     * @param e button's event
     */
    private void actionPerformed(final ActionEvent e) {
        int idx = buttons.indexOf(e.getSource());
            switch (idx) {
            case 0 : options.start(new Stage());
            break;
            case 1 :  RulesBookLoading.getLog().start(new Stage());
            break;
            case 2 : ControllerImp.getLog().undoMove();
            break;
            case 3 : 
                    ConfirmBox.display("Confirm", "Are you sure you want to close the application?");
            break;
            default : break;
            }
}
    /**
     * method for settings action listener to the button on the sideMenu.
     * @param btn button
     */
    private void menuDecorator(final Button btn) {
        btn.setOnMouseEntered(e -> { 
            btn.setId("button-listener");
         });
        btn.setOnMouseExited(e -> { 
            btn.setId("button");
         });
     }

}
