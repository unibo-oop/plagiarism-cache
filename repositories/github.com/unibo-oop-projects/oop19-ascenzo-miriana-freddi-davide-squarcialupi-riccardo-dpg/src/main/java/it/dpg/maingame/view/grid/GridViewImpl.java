package it.dpg.maingame.view.grid;

import it.dpg.maingame.controller.grid.GridObserver;
import it.dpg.maingame.controller.grid.GridObserverImpl;
import it.dpg.maingame.controller.gamecycle.GameCycle;
import it.dpg.maingame.model.character.Dice;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class GridViewImpl implements GridView {

    private static Stage pStage = new Stage();
    public Scene scene;
    private GridObserver obs;
    private Rectangle2D screenBounds = Screen.getPrimary().getBounds();

    public int movesLeft;
    public String currentPlayer;

    private BorderPane root = new BorderPane();
    private VBox upperGroup = new VBox(5);
    private Group gridGroup = new Group();

    private Label mainText = new Label();
    private Label movesText = new Label();
    private Label playerText = new Label();
    Button diceButton = new Button("Dice");

    //this map keeps track of the various cells (by graphic representation) and the coordinates of the cells connected to the Key Cell
    private Map<StackPane, Set<Pair<Integer, Integer>>> circlesList = new LinkedHashMap<>();
    //this map keeps track of each Cell gridPane by its coordinates
    private Map<Pair<Integer, Integer>, FlowPane> gridsList = new LinkedHashMap<>();

    //this map keeps track of the player'id, its corresponding graphic representation and the cell gridPane where it is sitting
    private Map<Integer, Pair<Rectangle, FlowPane>> playerList = new LinkedHashMap<>();

    private ViewNodesFactory nodes = new ViewNodesFactory();

    //these integers are constants that modify the position of a graphic element based on their coordinates
    private double Xmodifier = screenBounds.getWidth()/15;
    private double Ymodifier = screenBounds.getHeight()/12;

    public GridViewImpl(GameCycle gameCycle) {
        this.obs = new GridObserverImpl(gameCycle);
    }

    /**
     * this method creates the CirclesList filled with Circles and next Cell coordinates related to a Cell
     */
    public void makeCellList(Pair<Integer, Integer> coordinates, String type, Set<Pair<Integer, Integer>> nextCells) {

        Circle circle;

        //the color is dictated by the Cell Type
        switch (type) {
            case "START":
            case "END":
                circle = nodes.generateCell(Color.LIGHTBLUE);
                break;
            case "NORMAL":
                circle = nodes.generateCell(Color.LIGHTGREEN);
                break;
            case "GO_BACK":
                circle = nodes.generateCell(Color.PALEVIOLETRED);
                break;
            default:
                circle = nodes.generateCell(Color.WHITE);
                break;
        }

        /* A new StackPane is created to keep the Circle and the associated GridPane where the Players will sit */
        StackPane circlePane = new StackPane();
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setVgap(5);
        flowPane.setHgap(5);
        flowPane.setMaxWidth(circle.getRadius()*2);
        flowPane.setMaxHeight(circle.getRadius()*2);
        circlePane.getChildren().addAll(circle, flowPane);
        double left = coordinates.getLeft() * Xmodifier;
        double right = coordinates.getRight() * Ymodifier;
        circlePane.setLayoutX(left);
        circlePane.setLayoutY(right);
        circlePane.setAlignment(Pos.CENTER);

        gridsList.put(coordinates, flowPane);
        circlesList.put(circlePane, nextCells);

    }

    public void startGeneration() {

        StackPane mainTextLayout = new StackPane();
        StackPane diceLayout = new StackPane();
        Group movesLayout = new Group();

        /*
         Grid Group; everything is added to a Scroll Pane
         */
        ScrollPane sp = new ScrollPane();
        Group circleGroup = new Group();

        circlesList.forEach((key, value) -> {       //every circle is added to the Group
            circleGroup.getChildren().add(key);
        });
        gridGroup.getChildren().addAll(nodes.generateLines(circlesList, Xmodifier, Ymodifier), circleGroup);
        sp.setContent(gridGroup);

        /*
         * upper Group
         */
        Rectangle rectangle = new Rectangle(500, 60);
        rectangle.setFill(Color.WHITE);
        mainTextLayout.getChildren().addAll(rectangle, mainText);

        Rectangle diceBox = new Rectangle(100, 100);
        diceBox.setFill(Color.WHITE);
        diceButton.setDisable(true);
        diceButton.setShape(new Rectangle(1, 1));
        diceButton.setMinSize(60, 60);

        /* action handler */
        diceButton.setOnMousePressed(actionEvent -> obs.throwDiceHandler());

        diceLayout.getChildren().addAll(diceBox, diceButton);

        Rectangle LabelBox = new Rectangle(500, 60);
        LabelBox.setFill(Color.WHITE);
        VBox labels = new VBox();
        labels.getChildren().addAll(movesText, playerText);
        labels.setAlignment(Pos.CENTER);
        movesLayout.getChildren().addAll(LabelBox, labels);

        upperGroup.getChildren().addAll(mainTextLayout, diceLayout, movesLayout);
        upperGroup.setAlignment(Pos.CENTER);

        /*
        root layout
         */

        root.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));

        root.setTop(upperGroup);
        root.setCenter(sp);

        scene = new Scene(root, screenBounds.getWidth()/2, screenBounds.getHeight()/2, Color.AQUAMARINE);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                obs.KeyPressHandler();
            }
        });

        this.assignIds();

    }

    private void assignIds() {
        //to test the view, ids are assigned to a few view nodes
        mainText.setId("main_text");
        diceButton.setId("dice_button");
        for (var j : gridsList.entrySet()) {
            j.getValue().setId("pane"+j.getKey().getLeft()+j.getKey().getRight());
        }
    }

    @Override
    public void setView() {
        pStage.setScene(this.scene);
        pStage.setMaximized(true);
        pStage.show();
    }

    @Override
    public void setCurrentPlayerName(String name) {
        this.currentPlayer = name;
        this.playerText.setText("Currently playing: " +currentPlayer);
    }

    @Override
    public void setRemainingMoves(int moves) {
        this.movesLeft = moves;
    }

    @Override
    public void showText(String text) {
        mainText.setText(text);
    }

    @Override
    public void removeText() {
        mainText.setText("");
    }

    @Override
    public void enableDirectionChoice(Set<Pair<Integer, Integer>> cells) {

        //the method searches for the wanted fork inside the map, and creates and places buttons to the corresponding fork cells inside the Grid
        for (var i : cells) {
            Button button = new Button();
            button.setShape(new Circle(Xmodifier/5));
            button.setPrefWidth(Xmodifier/3);
            button.setPrefHeight(Xmodifier/3);
            button.setLayoutX(i.getLeft() * Xmodifier+Xmodifier/7);
            button.setLayoutY(i.getRight() * Ymodifier+Xmodifier/7);
            String arrow = "|\nV";
            button.setText(arrow);
            button.setTextAlignment(TextAlignment.CENTER);

            button.setOnMousePressed(actionEvent -> obs.choosePathHandler(i));

            gridGroup.getChildren().add(button);
        }

    }

    @Override
    public void disableDirectionChoice() {
        //the method removes every instance of buttons.
        gridGroup.getChildren().removeIf(i -> i instanceof Button);
    }

    @Override
    public void updatePlayers(Map<Integer, Pair<Integer, Integer>> players) {

        if (playerList.isEmpty()) {
            //if there's still no players, they are generated
            players.forEach((key, value) -> {
                Rectangle playerSquare = nodes.generatePlayer(key);
                FlowPane fp = gridsList.get(value);
                fp.getChildren().add(playerSquare);
                playerList.put(key, new ImmutablePair<>(playerSquare, fp));
            });
        }

        //the Players are added and removed to the gridPane corresponding to the Cell where the player is supposed to sit and from the old GridPane it was sitting on
        players.forEach((key, value) -> {
            Rectangle playerP = playerList.get(key).getLeft();
            FlowPane oldFlow = playerList.get(key).getRight();

            //searches for the corresponding rectangle and removes it from the old grid pane
            oldFlow.getChildren().remove(playerP);

            //adds the rectangle to the new Grid Pane (according to the @param coordinates)
            FlowPane newFlow = gridsList.get(value);
            newFlow.getChildren().add(playerP);
            //adds the new grid to the players list modifying the existing key
            playerList.put(key, new ImmutablePair<>(playerP, newFlow));
        });

        //afters players are created, ids are assigned for the sake of testing the view
        this.assignPlayersId();
    }

    private void assignPlayersId() {
        for (var i : playerList.entrySet()) {
            i.getValue().getLeft().setId("player"+i.getKey());
        }
    }

    @Override
    public void enableDiceThrow(Dice dice) {
        diceButton.setDisable(false);
        diceButton.setText("D" + dice.getFaces());
    }

    @Override
    public void disableDiceThrow() {
        diceButton.setDisable(true);
    }

    @Override
    public void closeView() {
        pStage.close();
    }
}
