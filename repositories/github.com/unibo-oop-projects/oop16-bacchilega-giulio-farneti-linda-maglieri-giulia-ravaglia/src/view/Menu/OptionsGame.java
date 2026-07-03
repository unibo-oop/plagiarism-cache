package view.Menu;

import java.util.ArrayList;
import java.util.List;

import controller.ControllerImp;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.Utilities.Utilities;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Class that shows the various game options.
 * 
 * Author: Linda Farneti
 *
 */
public class OptionsGame extends Application implements MenuInterface {

    private Stage stage;
    private GridPane gridBase;
    private GridPane gridOptions;
    private Text scenetitle;
    private Button home;

    private List<Label> labelList;
    private List<CheckBox> checkBoxes;


    @Override
    public void start(final Stage primaryStage) {
        stage = primaryStage;
        gridBase = new GridPane();
        gridOptions = new GridPane();
        stage.setTitle("CheckMate!");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Menu/chessIcon.png")));
        stage.setResizable(false);

        gridBase.setId("game-root");

        gridBase.add(gridOptions, 0, 1);

        gridBase.setAlignment(Pos.CENTER);
        gridBase.setHgap(3);
        gridBase.setVgap(2);

        scenetitle = new Text("Options\n");
        scenetitle.setId("title-text");
        gridBase.add(scenetitle, 0, 0);

        gridOptions.setAlignment(Pos.TOP_LEFT);
        gridOptions.setHgap(3);
        gridOptions.setVgap(2);


        labelList = new ArrayList<>();
        checkBoxes = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Label label = new Label();
            labelList.add(label);
            CheckBox checkB = new CheckBox();
            checkBoxes.add(checkB);

            switch(i) {
            case 0:
                label.setText("Possible moves: ");
                checkB.setSelected(ControllerImp.getLog().getMoves());
                break;
            case 1:
                label.setText("Threatened pawns: ");
                checkB.setSelected(ControllerImp.getLog().getThreat());
                break;
            default:
            }
            gridOptions.add(label, 0, i + 1);
            gridOptions.add(checkB, 1, i + 1);
        }



        home = new Button("Back to the game");
        gridBase.add(home, 2, 2);

        home.setOnAction(e -> this.actionPerformed(e));

        Scene scene = new Scene(gridBase, Utilities.WIDTH_MENU, Utilities.HEIGHT_MENU);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        scene.getStylesheets().add(MainMenu.class.getResource("InitialMenu.css").toExternalForm());
        primaryStage.show();
    }

    /**
     * Useful method for button's actionListener.
     * 
     * @param e event
     */
     private void actionPerformed(final ActionEvent e) {
        if ((checkBoxes.get(0).isSelected() && !ControllerImp.getLog().getMoves()) || (!checkBoxes.get(0).isSelected() && ControllerImp.getLog().getMoves())) {
                ControllerImp.getLog().movesCheck();
        }
        if ((checkBoxes.get(1).isSelected() && !ControllerImp.getLog().getThreat()) || (!checkBoxes.get(1).isSelected() && ControllerImp.getLog().getThreat())) {
                ControllerImp.getLog().threatCheck();
        }
        stage.close();
    }

    /**
     * Method that return the primaryStage.
     * 
     * @return stage
     */
    public Stage getPrimaryStage() {
        return stage;
    }
}
