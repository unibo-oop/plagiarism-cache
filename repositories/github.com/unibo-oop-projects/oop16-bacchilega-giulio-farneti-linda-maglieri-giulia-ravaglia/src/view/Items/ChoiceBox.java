package view.Items;
import java.util.ArrayList;

import java.util.List;

import controller.ControllerImp;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.utilities.pawns.PawnTypes;
import utilities.Players;
import view.Chess;
import view.Utilities.Utilities;
import view.*;
/**
 * 
 * Author: Giulia Maglieri.
 *
 */
public class ChoiceBox  {
    private Stage stage;
    private List<Button> buttons;
    private Button btn;
    private StackPane anchor;
    private Scene scene;
    private HBox choosePawns;
    /**
     * 
     * @param p  the player received from the controller.
     */
    public  void show(final Players p) {
        stage = new Stage();
        anchor = new StackPane();
        scene = new Scene(anchor, Utilities.POPUP_WIDTH, Utilities.POPUP_HEIGHT);
        buttons = new ArrayList<>();
        choosePawns = new HBox(10);
        choosePawns.setAlignment(Pos.CENTER);
        /* inserisco i bottoni*/
        String s = p.equals(Players.playerWhite) ? "w" : "b";
        String path =  "/Pawns/" + this.getStyle() + "/" + s;
        for (int i = 0; i < 4; i++) {
            btn = new Button();
            buttons.add(btn);
            choosePawns.getChildren().add(btn);
            switch(i) {
            case 0 : btn.setGraphic(new ImageView(new Image(ChoiceBox.class.getResource(path + "_Rook.png").toExternalForm(), Utilities.DIMENSION, Utilities.DIMENSION, true, true)));
            break;
            case 1 : btn.setGraphic(new ImageView(new Image(ChoiceBox.class.getResource(path + "_Knight.png").toExternalForm(), Utilities.DIMENSION, Utilities.DIMENSION, true, true)));
            break;
            case 2 : btn.setGraphic(new ImageView(new Image(ChoiceBox.class.getResource(path + "_Bishop.png").toExternalForm(), Utilities.DIMENSION, Utilities.DIMENSION, true, true)));
            break;
            case 3 : btn.setGraphic(new ImageView(new Image(ChoiceBox.class.getResource(path + "_Queen.png").toExternalForm(), Utilities.DIMENSION, Utilities.DIMENSION, true, true)));
            break;
            default : btn.setGraphic(null);
            btn.setPrefSize(Utilities.BTN_WIDTH, Utilities.BTN_HEIGHT);
               }
                    btn.setOnAction(e -> {
                        this.actionPerformed(e);
                            stage.close();
                    });
            }
            anchor.getChildren().add(choosePawns);
            scene.getStylesheets().addAll(ChoiceBox.class.getResource("/view/choice.css").toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
    }
    /**
     * 
     * @return String
     */
    public String getStyle() {
        return Chess.getLog().getPawnStyle();
    }
    /**
     * 
     * @param e button's event
     */
    public void actionPerformed(final ActionEvent e) {
        int idx = buttons.indexOf(e.getSource());
        switch(idx) {
            case 0 : ControllerImp.getLog().pawnPromotion(PawnTypes.Rook);
            break;
            case 1 : ControllerImp.getLog().pawnPromotion(PawnTypes.Knight);
            break;
            case 2: ControllerImp.getLog().pawnPromotion(PawnTypes.Bishop);
            break;
            case 3 : ControllerImp.getLog().pawnPromotion(PawnTypes.Queen);
            break;
            default : break;
        }
}
}
