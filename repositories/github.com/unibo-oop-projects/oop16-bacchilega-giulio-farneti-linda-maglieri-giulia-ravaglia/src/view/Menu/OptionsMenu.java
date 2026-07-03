package view.Menu;

import java.util.ArrayList;
import java.util.List;

import controller.ControllerImp;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Pair;
import view.BoardLayout;
import view.Chess;
import view.Utilities.Utilities;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * 
 * Author: Linda Farneti.
 *
 */
public class OptionsMenu extends Application implements MenuInterface {

    private Stage stage;
    private Scene scene;
    private GridPane gridBase;
    private GridPane gridOptions;
    private GridPane gridPawns;
    private GridPane gridBoards;

    private Text scenetitle;
    private List<Label> labelList = new ArrayList<>();
    private List<CheckBox> checkBoxes = new ArrayList<>();

    private RadioButton brownChoice = new RadioButton();
    private RadioButton blueChoice = new RadioButton();
    private ImageView brownBoard = new ImageView();
    private ImageView blueBoard = new ImageView();
    private List<Pair<ImageView, RadioButton>> pawnStyle;

    private Button home = new Button();

    private static OptionsMenu OPTIONSMENU;

    @Override
    public void start(final Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("CheckMate!");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Menu/chessIcon.png")));

        gridBase = new GridPane();
        gridOptions = new GridPane();
        gridPawns = new GridPane();
        gridBoards = new GridPane();

        gridBase.add(gridOptions, 0, 1);
        gridBase.add(gridBoards, 1, 2);
        gridBase.add(gridPawns, 0, 2);

        gridBase.setAlignment(Pos.CENTER);
        gridBase.setHgap(2);
        gridBase.setVgap(2);

        scenetitle = new Text("Options");
        scenetitle.setId("title-text");
        gridBase.add(scenetitle, 0, 0);

        gridOptions.setAlignment(Pos.TOP_LEFT);
        gridOptions.setHgap(4);
        gridOptions.setVgap(2);

        for (int i = 0; i < 3; i++) {
            Label label = new Label();
            labelList.add(label);
            CheckBox checkB = new CheckBox();
            checkBoxes.add(checkB);

            switch(i) {
            case 0: 
                label.setText("Timer : ");
                checkB.setSelected(ControllerImp.getLog().getTime());
                break;
            case 1:
                label.setText("Possible moves: ");
                checkB.setSelected(ControllerImp.getLog().getMoves());
                break;
            case 2:
                label.setText("Threatened pawns: ");
                checkB.setSelected(ControllerImp.getLog().getThreat());
                break;
            default:
            }
            gridOptions.add(label, 0, i);
            gridOptions.add(checkB, 1, i);
        }

        final ToggleGroup pawnsGroup = new ToggleGroup();
        pawnStyle = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String s = "";
            ImageView image;
            RadioButton choice = new RadioButton();
            switch(i) {
                case 0: 
                    s = "/Pawns/Standard/b_Knight.png";
                    break;
                case 1:
                    s = "/Pawns/Shadow/b_Knight.png";
                    break;
                default:
             }
            image = new ImageView(new Image(OptionsMenu.class.getResourceAsStream(s), Utilities.HBOX_HEIGHT, Utilities.HBOX_HEIGHT, true, true));
            gridPawns.add(image, i, 0);
            gridPawns.add(choice, i, 1);
            choice.setToggleGroup(pawnsGroup);
            pawnStyle.add(new Pair<>(image, choice));
        }

        pawnStyle.get(0).getValue().setSelected(true);

        final ToggleGroup boardsGroup = new ToggleGroup();
        gridBoards.setVgap(3);
        brownBoard = new ImageView(new Image(OptionsMenu.class.getResourceAsStream("/Menu/brownBoard.png"), Utilities.HBOX_HEIGHT, Utilities.HBOX_HEIGHT, true, true));
        gridBoards.add(brownBoard, 0, 0);
        blueBoard = new ImageView(new Image(OptionsMenu.class.getResourceAsStream("/Menu/blueBoard.png"), Utilities.HBOX_HEIGHT, Utilities.HBOX_HEIGHT, true, true));
        gridBoards.add(blueBoard, 2, 0);
        gridBoards.add(brownChoice, 0, 1);
        brownChoice.setToggleGroup(boardsGroup);
        brownChoice.setSelected(true);
        gridBoards.add(blueChoice, 1, 1);
        blueChoice.setToggleGroup(boardsGroup);

        home = new Button();
        home.setGraphic(new ImageView(new Image(OptionsMenu.class.getResourceAsStream("/Menu/home.png"), Utilities.MENU_ICON, Utilities.MENU_ICON, true, true)));
        gridBase.add(home, 1, 0);
        home.setOnAction(e -> this.actionPerformed(e));

        scene = new Scene(gridBase, Utilities.WIDTH_MENU, Utilities.HEIGHT_MENU);
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
        for (int i = 0; i < 3; i++) {
            if (checkBoxes.get(i).isSelected()) {
                switch(i) {
                case 0: 
                    ControllerImp.getLog().timeCheck();
                    break;
                case 1:
                    ControllerImp.getLog().movesCheck();
                    break;
                case 2:
                    ControllerImp.getLog().threatCheck();
                    break;
                default:
                }
            }
        }
        BoardLayout.getLog().setColor(OptionsMenu.this.boardChoice());
        Chess.getLog().setPawnsStyle(OptionsMenu.this.pawnsChoice());
        MainMenu mainMenuInterface = new MainMenu();
        mainMenuInterface.start(stage);
    }

    /**
     * Method that implements singleton pattern.
     * 
     * @return OPTIONSMENU
     */
    public static OptionsMenu getLog() {
        if (OPTIONSMENU == null) {
            OPTIONSMENU = new OptionsMenu();
        }
        return OPTIONSMENU;
    }

    /**
     * Method that returns the choice made.
     * 
     * @return string
     */
    public String pawnsChoice() {
        if (pawnStyle.get(0).getValue().isSelected()) {
            return "Standard";
        }
        return "Shadow";
    }

    /**
     * Method that returns the choice made.
     * 
     * @return string
     */
    public String boardChoice() {
        if (brownChoice.isSelected()) {
            return "brown";
        }
        return "blue";
    }

    /**
     * Method that return the primaryStage.
     * 
     * @return stage
     */
    public Stage getPrimaryStage() {
        return this.stage;
    }
}
